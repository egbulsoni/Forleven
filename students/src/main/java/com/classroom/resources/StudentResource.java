package com.classroom.resources;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.classroom.models.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.classroom.repository.StudentRepository;


@RestController
public class StudentResource {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping("/students")
	public List<Student> retrieveAllStudents(){
		return studentRepository.findAll();
	}
	
	@GetMapping("/students/{alumniCode}")
	public  ResponseEntity<Student> retrieveStudent(@PathVariable Long alumniCode) {
		Optional<Student> student = studentRepository.findById(alumniCode);
		
		 if (student.isEmpty()) {
             return ResponseEntity.notFound().build();
		 }
		 
		 return ResponseEntity.accepted().body(student.get());

	}
	
	@DeleteMapping("/students/{alumniCode}")
	public ResponseEntity<Student> deleteStudent(@PathVariable Long alumniCode) {
		Optional<Student> student = studentRepository.findById(alumniCode);
		if (student.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		studentRepository.deleteById(alumniCode);
		return ResponseEntity.ok().build();
	}

	
	@PostMapping("/students")
	public ResponseEntity<Object> createStudent(@Valid @RequestBody Student student) {
		Student savedStudent = studentRepository.save(student);
	
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getAlumniCode()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/students/{alumniCode}")
	public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable Long alumniCode) {
		
		Optional<Student> studentOptional = studentRepository.findById(alumniCode);
		
		if (studentOptional.isEmpty())
			return ResponseEntity.notFound().build();

		studentRepository.delete(studentOptional.get());
		studentRepository.save(student);
		return ResponseEntity.noContent().build();
	}
	
}
