package com.example.nrs.dto;

import com.example.nrs.entity.Note;
import com.example.nrs.entity.Rating;
import com.example.nrs.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.annotation.security.DenyAll;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RatingDto {

    private Integer  id;

    private Note note;

    private User user;

    private float stars;
    private boolean isRated=false;

    public RatingDto(Rating rating){
        this.id=rating.getId();
        this.note=rating.getNote();
        this.user=rating.getUser();
        this.stars=rating.getStars();
        this.isRated=rating.isRated();

    }
}
