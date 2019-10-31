package ca.mcgill.ecse321.tutoringservice321.servicetests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.tutoringservice321.dao.AvailabilityRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.SessionRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.TutorRepository;
import ca.mcgill.ecse321.tutoringservice321.model.Session;
import ca.mcgill.ecse321.tutoringservice321.model.Tutor;
import ca.mcgill.ecse321.tutoringservice321.service.TutoringService321Service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class SessionServiceTests {

	@Mock
	private SessionRepository sessionDao;

	@Mock
	private TutorRepository tutorDao;

	@Mock
	private AvailabilityRepository availabilityDao;

	@InjectMocks
	private TutoringService321Service service;

	private static final Tutor TUTOR = new Tutor();
	private static final String TUTOR_EMAIL = ("tutor@gmail.com");
	private static final Time START_TIME = Time.valueOf("11:00:00");
	private static final Time END_TIME = Time.valueOf("11:30:00");
	private static final Date DATE = Date.valueOf("2019-10-01");
	private static final String TUTOR_NAME="Kyjauna Marshall";
	private static final String TUTOR_PASSWORD="password";
	private static final String TUTOR_PHONE="250-510-2578";
	private static final int TUTOR_HOURLY_RATE=14;
	private static final Date NON_EXISTING_DATE = Date.valueOf("1999-11-01");

	Session session;

	@Before
	public void setUp() {
		when(tutorDao.findTutorByEmail(anyString())).thenAnswer(
				(InvocationOnMock invocation) -> {
					if(invocation.getArgument(0).equals(TUTOR_EMAIL)) {
						Tutor tutor = TUTOR;
						tutor.setEmail(TUTOR_EMAIL);
						tutor.setName(TUTOR_NAME);
						tutor.setPassword(TUTOR_PASSWORD);
						tutor.setHourlyRate(TUTOR_HOURLY_RATE);
						tutor.setPhoneNumber(TUTOR_PHONE);
						tutor.setSession(new HashSet<Session>());
						return tutor;
					} else {
						return null;
					}
				});		
		when(sessionDao.findSessionByTutorAndDate(any(Tutor.class),any(Date.class))).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(TUTOR) && invocation.getArgument(1).equals(DATE)) {
				Session session0 = new Session();
				session0.setTutor(TUTOR);
				session0.setDate(DATE);
				session0.setStarTime(Time.valueOf("12:00:00"));
				session0.setEndTime(Time.valueOf("13:00:00"));
				session0.setIsApproved(true);
				
				Session session = new Session();
				session.setTutor(TUTOR);
				session.setDate(DATE);
				session.setStarTime(START_TIME);
				session.setEndTime(END_TIME);
				session.setIsApproved(false);
				
				Session session2 = new Session();
				session2.setTutor(TUTOR);
				session2.setDate(DATE);
				session2.setStarTime(Time.valueOf("11:00:00"));
				session2.setEndTime(Time.valueOf("12:30:00"));
				session2.setIsApproved(false);

				Session session3 = new Session();
				session3.setTutor(TUTOR);
				session3.setDate(DATE);
				session3.setStarTime(Time.valueOf("12:30:00"));
				session3.setEndTime(Time.valueOf("14:00:00"));
				session3.setIsApproved(false);

				Session session4 = new Session();
				session4.setTutor(TUTOR);
				session4.setDate(DATE);
				session4.setStarTime(Time.valueOf("12:30:00"));
				session4.setEndTime(Time.valueOf("12:40:00"));
				session4.setIsApproved(false);
				
				Session session5 = new Session();
				session5.setTutor(TUTOR);
				session5.setDate(DATE);
				session5.setStarTime(Time.valueOf("9:00:00"));
				session5.setEndTime(Time.valueOf("10:00:00"));
				session5.setIsApproved(false);

				Set<Session> sessions = new HashSet<Session>();
				sessions.add(session);
				sessions.add(session2);
				sessions.add(session3);
				sessions.add(session4);
				sessions.add(session5);
				sessions.add(session0);

				return sessions;
			} 
			
			else {
				return null;
			}
		});
		
		when(sessionDao.findAll()).thenAnswer( (InvocationOnMock invocation) -> {
			Session session0 = new Session();
			session0.setTutor(TUTOR);
			session0.setDate(DATE);
			session0.setStarTime(Time.valueOf("12:00:00"));
			session0.setEndTime(Time.valueOf("13:00:00"));
			session0.setIsApproved(true);
			
			Session session = new Session();
			session.setTutor(TUTOR);
			session.setDate(DATE);
			session.setStarTime(START_TIME);
			session.setEndTime(END_TIME);
			session.setIsApproved(false);
			
			Session session2 = new Session();
			session2.setTutor(TUTOR);
			session2.setDate(DATE);
			session2.setStarTime(Time.valueOf("11:00:00"));
			session2.setEndTime(Time.valueOf("12:30:00"));
			session2.setIsApproved(false);

			Session session3 = new Session();
			session3.setTutor(TUTOR);
			session3.setDate(DATE);
			session3.setStarTime(Time.valueOf("12:30:00"));
			session3.setEndTime(Time.valueOf("14:00:00"));
			session3.setIsApproved(false);

			Session session4 = new Session();
			session4.setTutor(TUTOR);
			session4.setDate(DATE);
			session4.setStarTime(Time.valueOf("12:30:00"));
			session4.setEndTime(Time.valueOf("12:40:00"));
			session4.setIsApproved(false);
			
			Session session5 = new Session();
			session5.setTutor(TUTOR);
			session5.setDate(DATE);
			session5.setStarTime(Time.valueOf("9:00:00"));
			session5.setEndTime(Time.valueOf("10:00:00"));
			session5.setIsApproved(false);

			Set<Session> sessions = new HashSet<Session>();
			sessions.add(session);
			sessions.add(session2);
			sessions.add(session3);
			sessions.add(session4);
			sessions.add(session5);
			sessions.add(session0);

			return sessions;
		});
	}

	@Test
	public void testApproveSession() {

		try {	
			session=service.approveSession(TUTOR_EMAIL, DATE, START_TIME, END_TIME);
		}
		catch(IllegalArgumentException e){
			fail();
		}

		assertEquals(true, session.getIsApproved());
	}
	
	@Test
	public void testApproveSession2() {

		try {	
			session=service.approveSession(TUTOR_EMAIL, DATE, Time.valueOf("9:00:00"), Time.valueOf("10:00:00"));
		}
		catch(IllegalArgumentException e){
			fail();
		}

		assertEquals(true, session.getIsApproved());
	}

	@Test
	public void testApproveSessionNullEmail() {

		String error = null;

		try {
			session = service.approveSession(null, DATE, START_TIME, END_TIME);

		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("Tutor could not be found.", error);
	}

	@Test

	public void testApproveSessionEmptyEmail() {

		String error = null;

		try {
			session = service.approveSession("", DATE, START_TIME, END_TIME);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Tutor could not be found.", error);
	}



	@Test
	public void testApproveSessionNullDate() {

		String error = null;

		try {
			session = service.approveSession(TUTOR_EMAIL, null, START_TIME, END_TIME);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("The session to approve could not be found.", error);
	}




	@Test	
	public void testApproveSessionNullStartTime() {

		String error = null;

		try {
			session = service.approveSession(TUTOR_EMAIL, DATE, null, END_TIME);
		} catch (IllegalArgumentException e) {
			error =e.getMessage();
		}
		assertEquals("The session to approve could not be found.", error);

	}


	@Test	
	public void testApproveSessionNullEndTime() {

		String error = null;

		try {
			session = service.approveSession(TUTOR_EMAIL, DATE, START_TIME, null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("The session to approve could not be found.", error);
	}


	@Test	
	public void testApproveNonExistingSession() {

		String error = null;

		try {
			session = service.approveSession(TUTOR_EMAIL, DATE, Time.valueOf("18:00:00"), Time.valueOf("19:00:00"));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("The session to approve could not be found.", error);
	}

	@Test	
	public void testApproveSessionTutorNotFound() {

		String error = null;

		try {
			session = service.approveSession("max@gmail.com", DATE, Time.valueOf("18:00:00"), Time.valueOf("19:00:00"));
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Tutor could not be found.", error);
	}

	@Test
	public void testApproveSessionConflictingSessionInfringeBefore() {
		String error = null;

		try {
			service.approveSession(TUTOR_EMAIL, DATE, Time.valueOf("11:00:00"), Time.valueOf("12:30:00"));
		}
		catch(IllegalArgumentException e){
			error=e.getMessage();
		}

		assertEquals(error,"This session would overlap with an existing approved session.");
	}

	@Test
	public void testApproveConflictingSessionInfringeMiddle() {
		String error = null;

		try {
			service.approveSession(TUTOR_EMAIL, DATE, Time.valueOf("12:30:00"), Time.valueOf("12:40:00"));
		}
		catch(IllegalArgumentException e){
			error=e.getMessage();
		}

		assertEquals(error,"This session would overlap with an existing approved session.");
	}

	@Test
	public void testApproveSessionConflictingSessionInfringeEnd() {
		String error = null;

		try {
			service.approveSession(TUTOR_EMAIL, DATE, Time.valueOf("12:30:00"), Time.valueOf("14:00:00"));
		}
		catch(IllegalArgumentException e){
			error=e.getMessage();
		}

		assertEquals(error,"This session would overlap with an existing approved session.");
	}

	@Test
	public void testGetExistingSession() {
		assertEquals(DATE, service.getSession(TUTOR_EMAIL, DATE, START_TIME, END_TIME).getDate());
		assertEquals(START_TIME, service.getSession(TUTOR_EMAIL, DATE, START_TIME, END_TIME).getStarTime());
		assertEquals(END_TIME, service.getSession(TUTOR_EMAIL, DATE, START_TIME, END_TIME).getEndTime());
	}

	@Test
	public void testGetNonExistingSessionByDate() {
		assertNull(service.getSession(TUTOR_EMAIL, NON_EXISTING_DATE, START_TIME, END_TIME));
	}
	
	@Test
	public void testGetNonExistingSessionByStartTime() {
		assertNull(service.getSession(TUTOR_EMAIL, DATE, Time.valueOf("9:00:00"), END_TIME));
	}
	
	@Test
	public void testGetNonExistingSessionByEndTime() {
		assertNull(service.getSession(TUTOR_EMAIL, DATE, START_TIME, Time.valueOf("14:00:00")));
	}
	
	@Test
	public void testGetSessionTutorNotFound() {
		String error = null;
		
		try {
			service.getSession("ppp@gmail.com", DATE, START_TIME, END_TIME);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals(error , "Tutor could not be found.");
	}
	
	@Test
	public void testGetAllSessions() {
		assertEquals(6, service.getAllSessions().size());
	}
}