package com.example.nrs.service;

import com.example.nrs.dto.NoteDto;
import com.example.nrs.entity.Status;
import org.apache.tika.exception.TikaException;

import java.io.IOException;
import java.util.List;

public interface NoteService {
    NoteDto createNote(NoteDto noteDto) throws TikaException, IOException;

    List<NoteDto> getAllNotes();

    List<NoteDto> getAllNotesByUserEmail(String email);

    List<NoteDto> getAllNotesByCourseIdAndStatus(Integer id, Status status);

}
