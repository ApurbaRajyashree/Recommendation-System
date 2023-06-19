package com.example.nrs.service.serviceImpl;

import com.example.nrs.algorithm.CosineSimilarity;
import com.example.nrs.component.FileStoreUtils;
import com.example.nrs.dto.NoteDto;
import com.example.nrs.entity.Note;
import com.example.nrs.entity.Status;
import com.example.nrs.entity.User;
import com.example.nrs.repository.NoteRepo;
import com.example.nrs.repository.RatingRepo;
import com.example.nrs.repository.UserRepo;
import com.example.nrs.service.NoteService;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepo noteRepo;
    private final UserRepo userRepo;

    private final FileStoreUtils fileStoreUtils;

    private final RatingRepo ratingRepo;

    public NoteServiceImpl(NoteRepo noteRepo, UserRepo userRepo, FileStoreUtils fileStoreUtils, RatingRepo ratingRepo) {
        this.noteRepo = noteRepo;
        this.userRepo = userRepo;
        this.fileStoreUtils = fileStoreUtils;
        this.ratingRepo = ratingRepo;
    }

    @Override
    public NoteDto createNote(NoteDto noteDto) throws TikaException, IOException {
        if(noteDto.getCourse()==null){
            throw new RuntimeException("Course must be selected");
        }

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/YYYY -- E H:m a");
        String myDate = localDateTime.format(df);
        System.out.println(myDate);

        Note note =
                Note.builder()
                        .id(noteDto.getId())
                        .noteTitle(noteDto.getNoteTitle())
                        .noteDescription(noteDto.getNoteDescription())
                        .course(noteDto.getCourse())
                        .dateOfNoteCreation(myDate)
                        .filePath(fileStoreUtils.saveMultipartFile(noteDto.getMultipartFile()))
                        .user(noteDto.getUser())
                        .noteStatus(Status.SUBMITTED)
                        .build();
        note = noteRepo.save(note);
        return new NoteDto(note);
    }

    @Override
    public List<NoteDto> getAllNotes() {
        List<Note> notes=noteRepo.findAll();
        return  notes.stream().map(x->new NoteDto(x)).collect(Collectors.toList());
    }

    @Override
    public List<NoteDto> getAllNotesByUserEmail(String email) {
        User user=userRepo.findByUserEmail(email);
        List<Note> notes=noteRepo.findAllByUserId(user.getId());
        return  notes.stream().map(x->new NoteDto(x)).collect(Collectors.toList());
    }

    @Override
    public List<NoteDto> getAllNotesByUser(User user) {
       List<Note> notes=noteRepo.findAllByUser(user);
        return  notes.stream().map(x->new NoteDto(x)).collect(Collectors.toList());
    }

    @Override
    public List<NoteDto> getAllNotesByCourseIdAndStatus(Integer id, String status) {
        List<Note> notes=noteRepo.findAllByCourse_IdAndNoteStatus(id,status);
        return notes.stream().map(x-> new NoteDto(x)).collect(Collectors.toList());
    }

    @Override
    public List<NoteDto> getAllNotesByStatus(Status status) {
        List<Note> notes=noteRepo.findAllByNoteStatus(status);
        return notes.stream().map(x-> new NoteDto(x)).collect(Collectors.toList());
    }

    @Override
    public String acceptNote(Integer id) {
        Optional<Note> note=noteRepo.findById(id);
        if(note.isPresent()){
            Note note1=note.get();
            note1.setNoteStatus(Status.APPROVED);
            noteRepo.save(note1);
        }else {
            throw new RuntimeException("Note doesnot exist");
        }
        return "Note Accepted!!";
    }

    @Override
    public String rejectNote(Integer id) {
        Optional<Note> note=noteRepo.findById(id);
        if(note.isPresent()){
            Note note1=note.get();
            note1.setNoteStatus(Status.REJECTED);
            noteRepo.save(note1);
        }else {
            throw new RuntimeException("Note doesnot exist");
        }
        return "Note Rejected!!";
    }

    @Override
    public String deleteNote(Integer id) {
        noteRepo.deleteById(id);
        return "Deleted successfully";
    }


    @Override
    public List<NoteDto> recommendNoteForUser(Principal principal, int numRecommendation) {
        User loggedInUser=userRepo.findByUserEmail(principal.getName());

        CosineSimilarity collaborativeFiltering=new CosineSimilarity(userRepo,noteRepo,ratingRepo);
        List<Integer> noteId=collaborativeFiltering.recommendNotes(loggedInUser.getId(),numRecommendation);
        List<Note> notes=new ArrayList<>();
        for (Integer eachNoteId:noteId
        ) {
            Note retrivedNotes=noteRepo.findById(eachNoteId).get();
            notes.add(retrivedNotes);
        }

        return notes.stream().map(x->new NoteDto(x)).collect(Collectors.toList());
    }

}
