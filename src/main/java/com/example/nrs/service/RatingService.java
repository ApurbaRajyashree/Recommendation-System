package com.example.nrs.service;

import com.example.nrs.dto.RatingDto;
import com.example.nrs.entity.Note;
import com.example.nrs.entity.Rating;
import com.example.nrs.entity.User;

public interface RatingService {

    RatingDto createRating(RatingDto ratingDto);

    Rating findByUserAndNote(User user, Note note);

}
