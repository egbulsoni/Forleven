package com.classroom.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;

import com.classroom.models.Student;
import com.classroom.resources.StudentResource;
import com.fasterxml.jackson.databind.ObjectMapper;

//@AutoConfigureMockMvc
@ContextConfiguration(classes = StudentResource.class)
@WebMvcTest(controllers = StudentResource.class)
@ActiveProfiles("test")
public class StudentTests {
	@MockBean
	StudentResource studentResource;
	
	@Autowired
	private MockMvc mockMvc; 
	
	private List<Student> studentList;	
	
	@BeforeEach
	void setUp() {
		this.studentList = new ArrayList<>();
		this.studentList.add(new Student(42001, "Alan", "Santos"));
		this.studentList.add(new Student(42002, "Eduardo", "Bulsoni"));
		
	}

	@Test
	void testShouldGetAllStudents() throws Exception {
		given(studentResource.retrieveAllStudents()).willReturn(studentList);
		
		MvcResult result = this.mockMvc.perform(get("/students"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(2)))
				.andReturn();
		String content = result.getResponse().getContentAsString();
		assertNotNull(content);
		
	}
	
	
	@Test
    void shouldUpdateStudent() throws Exception {
        Long alumniCode = 42002L;
        Long newAlumniCode = 42013L;
        Student student = new Student(newAlumniCode, "Cleber", "Bambam");
        studentResource.updateStudent(student, alumniCode);
        
    }
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	@Test
    void shouldAddUser() throws Exception {
    final Student student = new Student(42005L, "Joao", "Silva");
    
    this.mockMvc.perform(MockMvcRequestBuilders
            .post("/students", student)
            .content(asJsonString(student))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    

    }
	
	@Test
    void shouldDeleteUser() throws Exception {
		long num = 42001L;
		this.mockMvc.perform(MockMvcRequestBuilders
            .delete("/students/{alumniCode}", num))
            .andExpect(status().isOk());

    }
	

}
