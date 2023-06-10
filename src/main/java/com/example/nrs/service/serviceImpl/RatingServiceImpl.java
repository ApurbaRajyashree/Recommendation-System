package com.example.nrs.service.serviceImpl;

import com.example.nrs.dto.RatingDto;
import com.example.nrs.entity.Rating;
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
        Rating rating=new Rating(ratingDto);
        ratingRepo.save(rating);
        return new RatingDto(rating);
    }
}
