package com.classroom.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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


import com.classroom.models.Student;
import com.classroom.models.StudentNotFoundException;
import com.classroom.repository.StudentRepository;

@RestController
public class StudentResource {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping("/students")
	public List<Student> retrieveAllStudents(){
		return studentRepository.findAll();
	}
	
	@GetMapping("/test")
	public String teste() {
		return "Testando...";
	}
	
	@GetMapping("/students/{alumniCode}")
	public Student retrieveStudent(@PathVariable long alumniCode) {
		Optional<Student> student = studentRepository.findById(alumniCode);

		if (!student.isPresent())
			throw new StudentNotFoundException("alumniCode-" + alumniCode);

		return student.get();
	}
	
	@DeleteMapping("/students/{alumniCode}")
	public void deleteStudent(@PathVariable long alumniCode) {
		studentRepository.deleteById(alumniCode);
	}
	
	@PostMapping("/students")
	public ResponseEntity<Object> createStudent(@RequestBody Student student) {
		Student savedStudent = studentRepository.save(student);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{alumniCode}")
				.buildAndExpand(savedStudent.getAlumniCode()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/students/{alumniCode}")
	public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long alumniCode) {
		
		Optional<Student> studentOptional = studentRepository.findById(alumniCode);
		
		if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();
		
		student.setAlumniCode(alumniCode);
		
		studentRepository.save(student);
		return ResponseEntity.noContent().build();
	}
	
}
