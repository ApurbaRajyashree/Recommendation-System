package com.example.nrs.entity;

public enum EducationQualification {
    Bachelors_Degree("Bachelor's Degree"),
    Masters_Degree("Master's Degree"),
    PhD("PhD");

    public final String qualification;

    EducationQualification(String qualification) {
        this.qualification = qualification;
    }
}
