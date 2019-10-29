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
	private static final Time START_TIME = Time.valueOf("12:00:00");
	private static final Time END_TIME = Time.valueOf("13:00:00");
	private static final Date DATE = Date.valueOf("2019-10-01");
	private static final String TUTOR_NAME="Kyjauna Marshall";
	private static final String TUTOR_PASSWORD="password";
	private static final String TUTOR_PHONE="250-510-2578";
	private static final int TUTOR_HOURLY_RATE=14;
	
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
				Session session = new Session();
				session.setTutor(TUTOR);
				session.setDate(DATE);
				session.setStarTime(START_TIME);
				session.setEndTime(END_TIME);

				Set<Session> sessions = new HashSet<Session>();
				sessions.add(session);

				return sessions;
			} else {
				return null;
			}
		});

		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		when(sessionDao.save(any(Session.class))).thenAnswer(returnParameterAsAnswer);
	}

	
	@Test
	public void testCreateSession() {

		try {
			session=service.createSession(TUTOR_EMAIL, DATE, Time.valueOf("9:00:00"), Time.valueOf("10:00:00"));
		}
		catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
			fail();
		}

		assertEquals(DATE, session.getDate());
		assertEquals(Time.valueOf("9:00:00"), session.getStarTime());
		assertEquals(Time.valueOf("10:00:00"), session.getEndTime());
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
	public void testGetExistingSession() {
		assertEquals(DATE, service.getSession(TUTOR_EMAIL, DATE, START_TIME, END_TIME).getDate());
		assertEquals(START_TIME, service.getSession(TUTOR_EMAIL, DATE, START_TIME, END_TIME).getStarTime());
		assertEquals(END_TIME, service.getSession(TUTOR_EMAIL, DATE, START_TIME, END_TIME).getEndTime());
		
	
	}	
	
		public void testGetNonExistingSession() {
			assertNull(service.getSession(TUTOR_EMAIL, DATE, START_TIME, END_TIME));
	}
	
	
}