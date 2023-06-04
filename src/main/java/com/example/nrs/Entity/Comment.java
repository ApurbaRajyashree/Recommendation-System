package com.example.nrs.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "comment", nullable = false)
    private String comment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference(value = "note")
    @JoinColumn(name = "note_id", referencedColumnName = "id", nullable = false)
    private Note note;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
