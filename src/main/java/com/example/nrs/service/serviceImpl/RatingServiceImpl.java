package com.example.nrs.service.serviceImpl;

import com.example.nrs.dto.RatingDto;
import com.example.nrs.entity.Note;
import com.example.nrs.entity.Rating;
import com.example.nrs.entity.User;
import com.example.nrs.repository.RatingRepo;
import com.example.nrs.service.RatingService;
import org.springframework.stereotype.Controller;


@Controller
public class RatingServiceImpl implements RatingService {

    private final RatingRepo ratingRepo;

    public RatingServiceImpl(RatingRepo ratingRepo) {
        this.ratingRepo = ratingRepo;
    }

    @Override
    public RatingDto createRating(RatingDto ratingDto) {
        if(ratingDto.getUser()==null || ratingDto.getNote()==null){
            throw new RuntimeException("Sorry something went wrong!!!");
        }
        Rating rating=new Rating(ratingDto);
        rating.setStars(ratingDto.getStars());
        rating.setRated(true);
        ratingRepo.save(rating);
        return new RatingDto(rating);
    }

    @Override
    public Rating findByUserAndNote(User user, Note note) {
        return ratingRepo.findByUserAndNote(user,note);
    }
}
