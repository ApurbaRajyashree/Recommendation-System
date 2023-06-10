package com.example.nrs.entity;

import com.example.nrs.dto.RatingDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer  id;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="note_id",referencedColumnName = "id")
    @JsonBackReference(value = "note")
    private Note note;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonBackReference(value = "users")
    private User user;

    private float stars;
    private boolean isRated=false;

    public Rating(RatingDto rating){
        this.id=rating.getId();
        this.note=rating.getNote();
        this.user=rating.getUser();
        this.stars=rating.getStars();
        this.isRated=rating.isRated();

    }
}
