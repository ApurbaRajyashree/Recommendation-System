package com.example.nrs.repository;

import com.example.nrs.entity.Note;
import com.example.nrs.entity.Status;
import org.springframework.core.io.buffer.LimitedDataBufferList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepo extends JpaRepository<Note,Integer> {

    List<Note> findAllByUserId(Integer id);

    @Query(value = "select * from note where course_id=?1 and status=?2",nativeQuery = true)
    List<Note> findAllByCourse_IdAndNoteStatus(Integer id, String noteStatus);

    List<Note> findAllByNoteStatus(String status);
}
