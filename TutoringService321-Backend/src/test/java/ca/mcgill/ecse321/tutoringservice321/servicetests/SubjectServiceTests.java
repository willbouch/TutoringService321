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
import ca.mcgill.ecse321.tutoringservice321.model.Subject;
import ca.mcgill.ecse321.tutoringservice321.service.TutoringService321Service;
import ca.mcgill.ecse321.tutoringservice321.dao.*;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SubjectServiceTests {
	
	@Mock
	private SubjectRepository subjectDao;

	@InjectMocks
	private TutoringService321Service service;
	
	private static final String SUBJECT_KEY = "TestPerson";
	//private static final String NONEXISTING_KEY = "NotAPerson";

	
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
	}
   
	private Subject subject;

	@Test
	public void testCreateSubject() {
		assertEquals(0, service.getAllSubjects().size());
		
		String name = "Mathematics";
		
		try {
			 subject = service.createSubject(name);
		}catch (IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(name,subject.getSubjectName());
		
	}
	
	
	@Test
	public void testCreateSubjectNull() {
		String name= null;
		String error= null;
		
		try {
			 subject = service.createSubject(name);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("The name cannot be empty.", error );
	}

}
