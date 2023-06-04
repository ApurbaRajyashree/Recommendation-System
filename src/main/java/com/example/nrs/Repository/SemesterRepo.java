package com.example.nrs.Repository;

import com.example.nrs.Entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRepo extends JpaRepository<Semester,Integer> {
}
