package com.example.nrs.entity;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;


@Getter
public enum SemesterName {
    FIRST_SEMESTER(1, "First Semester"),
    SECOND_SEMESTER(2, "Second Semester"),
    THIRD_SEMESTER(2, "Third Semester"),
    FOURTH_SEMESTER(4, "Fourth Semester"),
    FIFTH_SEMESTER(5, "Fifth Semester"),
    SIXTH_SEMESTER(6, "Sixth Semester"),
    SEVENTH_SEMESTER(7, "Seventh Semester"),
    EIGHTH_SEMESTER(8, "Eighth Semester");

    public final String semesterName;
    public final int semesterNumber;


    private SemesterName(int semesterNumber, String semesterName) {
        this.semesterName = semesterName;
        this.semesterNumber = semesterNumber;
    }


    private static final Map<String, SemesterName> BY_SEMESTER_NAME = new HashMap<>();
    private static final Map<Integer, SemesterName> BY_SEMESTER_NUMBER = new HashMap<>();

    static {
        for (SemesterName e : values()) {
            BY_SEMESTER_NAME.put(e.semesterName, e);
            BY_SEMESTER_NUMBER.put(e.semesterNumber, e);
        }
    }

    public static SemesterName valueOfSemesterName(String semesterName) {
        return BY_SEMESTER_NAME.get(semesterName);
    }

    public static SemesterName valueOfSemesterNumber(int semesterNumber) {
        return BY_SEMESTER_NUMBER.get(semesterNumber);
    }

}
