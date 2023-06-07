package com.example.nrs.service.serviceImpl;

import com.example.nrs.component.FileStoreUtils;
import com.example.nrs.dto.NoteDto;
import com.example.nrs.entity.Note;
import com.example.nrs.entity.Status;
import com.example.nrs.entity.User;
import com.example.nrs.repository.NoteRepo;
import com.example.nrs.repository.UserRepo;
import com.example.nrs.service.NoteService;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepo noteRepo;
    private final UserRepo userRepo;

    private final FileStoreUtils fileStoreUtils;

    public NoteServiceImpl(NoteRepo noteRepo, UserRepo userRepo, FileStoreUtils fileStoreUtils) {
        this.noteRepo = noteRepo;
        this.userRepo = userRepo;
        this.fileStoreUtils = fileStoreUtils;
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
                        .isActive(true)
                        .noteStatus(Status.SUBMITTED)
                        .build();
        note = noteRepo.save(note);
        return new NoteDto(note.getId());
    }

    @Override
    public List<NoteDto> getAllNotes() {
        List<Note> notes=noteRepo.findAll();
        return  notes.stream().map(x->new NoteDto(x.getId())).collect(Collectors.toList());
    }

    @Override
    public List<NoteDto> getAllNotesByUserEmail(String email) {
        User user=userRepo.findByUserEmail(email);
        List<Note> notes=noteRepo.findAllByUserId(user.getId());
        return  notes.stream().map(x->new NoteDto(x.getId())).collect(Collectors.toList());
    }
}
