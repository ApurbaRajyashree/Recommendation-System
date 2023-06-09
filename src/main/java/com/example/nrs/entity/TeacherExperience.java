package com.example.nrs.entity;

public enum TeacherExperience {
    Less_than_a_year( "Less then a year"),
    One_year( "One year"),
    Two_year( "Two year"),
    Three_year( "Three year"),
    Four_year( "Four year"),
    Five_year( "Five year"),
    More_than_Five_year( "More  than 5 year");

    public final String experience;


    TeacherExperience(String experience) {
        this.experience = experience;
    }
}
