package com.example.nrs.service;

import com.example.nrs.dto.NoteDto;
import com.example.nrs.entity.Status;
import com.example.nrs.entity.User;
import org.apache.tika.exception.TikaException;

import java.io.IOException;
import java.util.List;

public interface NoteService {
    NoteDto createNote(NoteDto noteDto) throws TikaException, IOException;

    List<NoteDto> getAllNotes();

    List<NoteDto> getAllNotesByUserEmail(String email);
    List<NoteDto> getAllNotesByUser(User user);


    List<NoteDto> getAllNotesByCourseIdAndStatus(Integer id, String status);

    List<NoteDto> getAllNotesByStatus(Status status);

    String acceptNote(Integer id);
    String rejectNote(Integer id);

    String deleteNote(Integer id);

}
