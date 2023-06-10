package com.example.nrs.repository;

import com.example.nrs.entity.Note;
import com.example.nrs.entity.Rating;
import com.example.nrs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepo extends JpaRepository<Rating,Integer> {

    List<Rating> findAllByUser(User user);

    Rating findByUserAndNote(User user, Note note);
}
