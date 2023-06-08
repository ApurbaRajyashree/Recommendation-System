package com.example.nrs.entity;

public enum TeacherExperience {
    Less_than_a_year(0, "Less then a year"),
    One_year(1, "One year"),
    Two_year(2, "Two year"),
    Three_year(3, "Three year"),
    Four_year(4, "Four year"),
    Five_year(5, "Five year"),
    More_than_Five_year(6, "More  than 5 year");

    public final String experience;
    public final int code;


    private TeacherExperience(int code, String experience) {
        this.experience = experience;
        this.code = code;
    }
}
