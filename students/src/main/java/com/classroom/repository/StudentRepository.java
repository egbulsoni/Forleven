package com.classroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.classroom.models.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	

}
