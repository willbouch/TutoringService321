package ca.mcgill.ecse321.tutoringservice321.servicetests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;
import ca.mcgill.ecse321.tutoringservice321.controller.TutoringService321RestController;
import ca.mcgill.ecse321.tutoringservice321.model.Availability;
import ca.mcgill.ecse321.tutoringservice321.model.Review;
import ca.mcgill.ecse321.tutoringservice321.model.Subject;
import ca.mcgill.ecse321.tutoringservice321.model.Tutor;
import ca.mcgill.ecse321.tutoringservice321.service.TutoringService321Service;
import ca.mcgill.ecse321.tutoringservice321.dao.*;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class SubjectServiceTests {
	
	@Mock
	private SubjectRepository subjectDao;
	
	@Mock
	private TutorRepository tutorDao;

	@InjectMocks
	private TutoringService321Service service;
	
	private static final String SUBJECT_KEY = "Maths";
	private static final String NONEXISTING_KEY = "NotAPerson";
	private static final String TUTOR_EMAIL="katie@gmail.com";
	private static final String TUTOR_NAME="Katie Younge";
	private static final String TUTOR_PASSWORD="password";
	private static final String TUTOR_PHONE="8193296836";
	private static final int TUTOR_HOURLY_RATE=15;
	private static final Tutor TUTOR = new Tutor();
	
	@Before
	public void setMockOutput() {
		when(subjectDao.findSubjectBySubjectName(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(SUBJECT_KEY)) {
				Subject subject = new Subject();
				subject.setSubjectName(SUBJECT_KEY);
				return subject;
			} else {
				return null;
			}
		});
		
		when(tutorDao.findTutorByEmail(anyString())).thenAnswer(
				(InvocationOnMock invocation) -> {
					if(invocation.getArgument(0).equals(TUTOR_EMAIL)) {
						Tutor tutor = TUTOR;
						tutor.setEmail(TUTOR_EMAIL);
						tutor.setName(TUTOR_NAME);
						tutor.setPassword(TUTOR_PASSWORD);
						tutor.setHourlyRate(TUTOR_HOURLY_RATE);
						tutor.setPhoneNumber(TUTOR_PHONE);
						tutor.setSubject(new HashSet<Subject>());
						return tutor;
					} else {
						return null;
					}
				});
		
		when(subjectDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			Set<Subject> subjects = new HashSet<>();
			Subject subject1 = new Subject();
			Subject subject2 = new Subject();
			
			subjects.add(subject1);
			subjects.add(subject2);
			
			return subjects;
		});
	}
   
	@Test
	public void testGetExistingSubject() {
		assertEquals(SUBJECT_KEY, service.getSubject(SUBJECT_KEY).getSubjectName());
	}

	@Test
	public void testGetNonExistingSubject() {
		assertNull(service.getSubject(NONEXISTING_KEY));
	}
	
	@Test
	public void testDeleteSubject() {
		try {
			service.deleteSubject(SUBJECT_KEY);
		} catch(IllegalArgumentException e) {
			fail();
		}
	}
	
	@Test
	public void testDeleteSubjectNotFound() {
		String error = null;
		
		try {
			service.deleteSubject("Java Programming");
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Subject could not be found.", error);
	}
	
	@Test
	public void testRemoveSubjectFromTutor() {
		
		try {
			service.removeSubjectFromTutor(SUBJECT_KEY, TUTOR_EMAIL);
		} catch(IllegalArgumentException e) {
			fail();
		}
	}
	
	@Test
	public void testRemoveSubjectFromTutorTutorNotFound() {
		String error = null;
		
		try {
			service.removeSubjectFromTutor(SUBJECT_KEY, "hey");
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Tutor could not be found.", error);
	}
	
	@Test
	public void testRemoveSubjectFromTutorSubjectNotFound() {
		String error = null;
		
		try {
			service.removeSubjectFromTutor("h", TUTOR_EMAIL);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Subject could not be found.", error);
	}
	
	@Test
	public void getAllSubjects() {
		assertEquals(2, service.getAllSubjects().size());
	}
}
