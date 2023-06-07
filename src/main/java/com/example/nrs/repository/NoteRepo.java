package com.example.nrs.repository;

import com.example.nrs.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepo extends JpaRepository<Note,Integer> {

    List<Note> findAllByUserId(Integer id);
}
