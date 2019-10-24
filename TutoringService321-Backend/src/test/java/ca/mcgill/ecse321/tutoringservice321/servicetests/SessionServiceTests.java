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

import ca.mcgill.ecse321.tutoringservice321.dao.SessionRepository;
import ca.mcgill.ecse321.tutoringservice321.model.Session;
import ca.mcgill.ecse321.tutoringservice321.model.Tutor;
import ca.mcgill.ecse321.tutoringservice321.service.TutoringService321Service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;

@RunWith(MockitoJUnitRunner.class)
public class SessionServiceTests {

	@Mock
	private SessionRepository sessionDao;

	@InjectMocks
	private TutoringService321Service service;

	private Session session;

	private static final String SESSION_KEY = "TestSession";
	private static final String NONEXISTING_KEY = "NotAPSession";

	@Before
	public void setMockOutput() {
		when(sessionDao.findSessionByTutorAndDateAndStartTime(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(SESSION_KEY)) {
				Session session = new Session();
				session.setName(SESSION_KEY);
				return session;
			} else {
				return null;
			}
		});
	}



	@Test
	public void testCreateSession() {

		String tutorEmail = "test@gmail.com";
		assertEquals(0, service.getAllTutorAvailabilities(tutorEmail).size());
		
		Date date = Date.valueOf("2019-10-01");
		Time startTime = Time.valueOf("12:00:00");
		Time endTime = Time.valueOf("16:00:00");



		try {
			session = service.createSession(tutorEmail, date, startTime, endTime);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}

		assertEquals(tutorEmail, session.getSessionID());
	}

	@Test
	public void testApproveSession() {

		String tutorEmail = "test@gmail.com";
		assertEquals(0, service.getAllTutorAvailabilities(tutorEmail).size());
		
		Date qdate = Date.valueOf("2019-10-01");
		Time qstartTime = Time.valueOf("12:00:00");
		Time qendTime = Time.valueOf("16:00:00");

		Date cDate = Date.valueOf("2019-10-01");
		Time cStartTime = Time.valueOf("12:00:00");
		Time cEndTime = Time.valueOf("16:00:00");


		try {
			session = service.approveSession(tutorEmail, qdate, qstartTime, qendTime, cDate, cStartTime, cEndTime);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}

		assertEquals(tutorEmail, session.getSessionID());
	}
	
	@Test
	public void testApproveSessionNullEmail() {

		String tutorEmail = null;
		assertEquals(0, service.getAllTutorAvailabilities(tutorEmail).size());
		
		Date qdate = Date.valueOf("2019-10-01");
		Time qstartTime = Time.valueOf("12:00:00");
		Time qendTime = Time.valueOf("16:00:00");

		Date cDate = Date.valueOf("2019-10-01");
		Time cStartTime = Time.valueOf("12:00:00");
		Time cEndTime = Time.valueOf("16:00:00");
		
		String error = null;


		try {
			session = service.approveSession(tutorEmail, qdate, qstartTime, qendTime, cDate, cStartTime, cEndTime);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			e.getMessage();
		}
		
		assertEquals("A tutor must be signed in to approve a session", error);
		//assertEquals(tutorEmail, session.getSessionID());
	}
	
	public void testApproveSessionEmptyEmail() {

		String tutorEmail = " ";
		assertEquals(0, service.getAllTutorAvailabilities(tutorEmail).size());
		
		Date qdate = Date.valueOf("2019-10-01");
		Time qstartTime = Time.valueOf("12:00:00");
		Time qendTime = Time.valueOf("16:00:00");

		Date cDate = Date.valueOf("2019-10-01");
		Time cStartTime = Time.valueOf("12:00:00");
		Time cEndTime = Time.valueOf("16:00:00");
		
		String error = null;


		try {
			session = service.approveSession(tutorEmail, qdate, qstartTime, qendTime, cDate, cStartTime, cEndTime);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			e.getMessage();
		}
		
		assertEquals("A tutor must have a valid email to approve a session!", error);
		//assertEquals(tutorEmail, session.getSessionID());
	}
	
	public void testApproveSessionNullqDate() {

		String tutorEmail = "test@gmail.com";
		assertEquals(0, service.getAllTutorAvailabilities(tutorEmail).size());
		
		Date qdate = null;
		Time qstartTime = Time.valueOf("12:00:00");
		Time qendTime = Time.valueOf("16:00:00");

		Date cDate = Date.valueOf("2019-10-01");
		Time cStartTime = Time.valueOf("12:00:00");
		Time cEndTime = Time.valueOf("16:00:00");
		
		String error = null;


		try {
			session = service.approveSession(tutorEmail, qdate, qstartTime, qendTime, cDate, cStartTime, cEndTime);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			e.getMessage();
		}
		
		assertEquals("Requested Date cannot be empty!", error);
		//assertEquals(tutorEmail, session.getSessionID());
	}
	
	public void testApproveSessionNullcDate() {

		String tutorEmail = "test@gmail.com";
		assertEquals(0, service.getAllTutorAvailabilities(tutorEmail).size());
		
		Date qdate = Date.valueOf("2019-10-01");
		Time qstartTime = Time.valueOf("12:00:00");
		Time qendTime = Time.valueOf("16:00:00");

		Date cDate = null;
		Time cStartTime = Time.valueOf("12:00:00");
		Time cEndTime = Time.valueOf("16:00:00");
		
		String error = null;


		try {
			session = service.approveSession(tutorEmail, qdate, qstartTime, qendTime, cDate, cStartTime, cEndTime);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			e.getMessage();
		}
		assertEquals("Confirmed date cannot be empty!", error);
		
		//assertEquals(tutorEmail, session.getSessionID());
	}
	
	public void testApproveSessionNullqstartTime() {

		String tutorEmail = "test@gmail.com";
		assertEquals(0, service.getAllTutorAvailabilities(tutorEmail).size());
		
		Date qdate = Date.valueOf("2019-10-01");
		Time qstartTime = null;
		Time qendTime = Time.valueOf("16:00:00");

		Date cDate = Date.valueOf("2019-10-01");
		Time cStartTime = Time.valueOf("12:00:00");
		Time cEndTime = Time.valueOf("16:00:00");
		
		String error = null;


		try {
			session = service.approveSession(tutorEmail, qdate, qstartTime, qendTime, cDate, cStartTime, cEndTime);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			e.getMessage();
		}
		assertEquals("Start Time cannot be empty!", error);
		
		
		//assertEquals(tutorEmail, session.getSessionID());
	}
	
	public void testApproveSessionNullcStartTime() {

		String tutorEmail = "test@gmail.com";
		assertEquals(0, service.getAllTutorAvailabilities(tutorEmail).size());
		
		Date qdate = Date.valueOf("2019-10-01");
		Time qstartTime = Time.valueOf("12:00:00");
		Time qendTime = Time.valueOf("16:00:00");
		
		Date cDate = Date.valueOf("2019-10-01");
		Time cStartTime = null;
		Time cEndTime = Time.valueOf("16:00:00");
		
		String error = null;


		try {
			session = service.approveSession(tutorEmail, qdate, qstartTime, qendTime, cDate, cStartTime, cEndTime);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			e.getMessage();
		}
		
		assertEquals("Confirmed start time cannot be empty!", error);
		//assertEquals(tutorEmail, session.getSessionID());
	}
	
	public void testApproveSessionqEndTime() {

		String tutorEmail = "test@gmail.com";
		assertEquals(0, service.getAllTutorAvailabilities(tutorEmail).size());
		
		Date qdate = Date.valueOf("2019-10-01");
		Time qstartTime = Time.valueOf("12:00:00");
		Time qendTime = null;

		Date cDate = Date.valueOf("2019-10-01");
		Time cStartTime = Time.valueOf("12:00:00");
		Time cEndTime = Time.valueOf("16:00:00");
		
		String error = null;


		try {
			session = service.approveSession(tutorEmail, qdate, qstartTime, qendTime, cDate, cStartTime, cEndTime);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			e.getMessage();
		}
		
		assertEquals("Requested end time cannot be empty!", error);
		//assertEquals(tutorEmail, session.getSessionID());
	}
	
	public void testApproveSessionNullcEndTime() {

		String tutorEmail = "test@gmail.com";
		assertEquals(0, service.getAllTutorAvailabilities(tutorEmail).size());
		
		Date qdate = Date.valueOf("2019-10-01");
		Time qstartTime = Time.valueOf("12:00:00");
		Time qendTime = Time.valueOf("16:00:00");

		Date cDate = Date.valueOf("2019-10-01");
		Time cStartTime = Time.valueOf("12:00:00");
		Time cEndTime = null;
		
		String error = null;


		try {
			session = service.approveSession(tutorEmail, qdate, qstartTime, qendTime, cDate, cStartTime, cEndTime);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			e.getMessage();
		}
		assertEquals("Confirmed end time cannot be empty!", error);
		
		
		//assertEquals(tutorEmail, session.getSessionID());
	}
	
	public void testApproveNullSession() {

		String tutorEmail = "test@gmail.com";
		assertEquals(0, service.getAllTutorAvailabilities(tutorEmail).size());
		
		Date qdate = null;
		Time qstartTime = null;
		Time qendTime = null;

		Date cDate = Date.valueOf("2019-10-01");
		Time cStartTime = Time.valueOf("12:00:00");
		Time cEndTime = Time.valueOf("16:00:00");
		
		String error = null;


		try {
			session = service.approveSession(tutorEmail, qdate, qstartTime, qendTime, cDate, cStartTime, cEndTime);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			e.getMessage();
		}
		
		assertEquals("You cannot approve a session that does not exist!", error);
		//assertEquals(tutorEmail, session.getSessionID());
	}
	
}
