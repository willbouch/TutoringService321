package ca.mcgill.ecse321.tutoringservice321.servicetests;

import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.tutoringservice321.dao.AvailabilityRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.SessionRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.TutorRepository;
import ca.mcgill.ecse321.tutoringservice321.model.Availability;
import ca.mcgill.ecse321.tutoringservice321.model.Session;
import ca.mcgill.ecse321.tutoringservice321.model.Tutor;
import ca.mcgill.ecse321.tutoringservice321.service.TutoringService321Service;
import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AvailabilityServiceTests {

	@Mock
	private AvailabilityRepository availabilityDao;

	@Mock
	private TutorRepository tutorDao;

	@Mock
	private SessionRepository sessionDao;

	@InjectMocks
	private TutoringService321Service service;

	private static final Tutor TUTOR = new Tutor();
	private static final Date DATE = Date.valueOf("2019-12-01");
	private static final Time START_TIME = Time.valueOf("9:00:00");
	private static final Time END_TIME = Time.valueOf("10:00:00");
	private static final String TUTOR_EMAIL="katie@gmail.com";
	private static final String TUTOR_NAME="Katie Younge";
	private static final String TUTOR_PASSWORD="password";
	private static final String TUTOR_PHONE="8193296836";
	private static final int TUTOR_HOURLY_RATE=15;

	Availability availability;

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
						tutor.setAvailability(new HashSet<Availability>());
						return tutor;
					} else {
						return null;
					}
				});

		when(availabilityDao.findAvailabilityByDateAndTutor(any(Date.class), any(Tutor.class))).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(DATE)) {
				Availability availability = new Availability();
				availability.setTutor(TUTOR);
				availability.setDate(DATE);
				availability.setStartTime(Time.valueOf("16:00:00"));
				availability.setEndTime(Time.valueOf("19:00:00"));
				
				Availability availability2 = new Availability();
				availability2.setTutor(TUTOR);
				availability2.setDate(DATE);
				availability2.setStartTime(Time.valueOf("12:00:00"));
				availability2.setEndTime(Time.valueOf("15:00:00"));

				Set<Availability> availabilities = new HashSet<Availability>();
				availabilities.add(availability);
				availabilities.add(availability2);

				return availabilities;
			} else {
				return null;
			}
		});

		when(availabilityDao.findAll()).thenAnswer( (InvocationOnMock invocation) -> {
			Tutor tutor1 = new Tutor();
			tutor1.setEmail("test@gmail.com");
			Tutor tutor2 = new Tutor();
			tutor2.setEmail(TUTOR_EMAIL);
			
			Availability availability1 = new Availability();
			availability1.setTutor(tutor2);
			availability1.setDate(DATE);
			availability1.setStartTime(Time.valueOf("16:00:00"));
			availability1.setEndTime(Time.valueOf("19:00:00"));
			
			Availability availability2 = new Availability();
			availability2.setTutor(tutor2);
			availability2.setDate(DATE);
			availability2.setStartTime(Time.valueOf("13:00:00"));
			availability2.setEndTime(Time.valueOf("14:00:00"));
			
			Availability availability3 = new Availability();
			availability3.setTutor(tutor1);
			availability3.setDate(DATE);
			availability3.setStartTime(Time.valueOf("9:00:00"));
			availability3.setEndTime(Time.valueOf("12:00:00"));

			Set<Availability> availabilities = new HashSet<Availability>();
			availabilities.add(availability1);
			availabilities.add(availability2);
			availabilities.add(availability3);

			return availabilities;
		});

		when(sessionDao.findSessionByTutorAndDate(any(Tutor.class), any(Date.class))).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(TUTOR) && invocation.getArgument(1).equals(DATE)) {
				Session session = new Session();
				session.setTutor(TUTOR);
				session.setDate(DATE);
				session.setStarTime(Time.valueOf("12:00:00"));
				session.setEndTime(Time.valueOf("13:00:00"));

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
		when(availabilityDao.save(any(Availability.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	@Test
	public void testAddAvailability() {

		try {
			availability=service.addAvailability(TUTOR_EMAIL, DATE, START_TIME, END_TIME);
		}
		catch(IllegalArgumentException e){
			fail();
		}

		assertEquals(DATE, availability.getDate());
		assertEquals(START_TIME, availability.getStartTime());
		assertEquals(END_TIME, availability.getEndTime());
	}

	@Test
	public void testAddAvailability2() {

		try {
			availability=service.addAvailability(TUTOR_EMAIL, DATE, Time.valueOf("20:00:00"), Time.valueOf("21:00:00"));
		}
		catch(IllegalArgumentException e){
			fail();
		}

		assertEquals(DATE, availability.getDate());
		assertEquals(Time.valueOf("20:00:00"), availability.getStartTime());
		assertEquals(Time.valueOf("21:00:00"), availability.getEndTime());
	}

	@Test
	public void testAddAvailabilityNullTutor() {
		String error = null;

		try {
			availability=service.addAvailability(null, DATE, START_TIME, END_TIME);
		}
		catch(IllegalArgumentException e){
			error=e.getMessage();
		}

		assertEquals(error,"Tutor cannot be empty.");
	}

	@Test
	public void testAddAvailabilityNullDate() {
		String error = null;

		try {
			availability=service.addAvailability(TUTOR_EMAIL, null, START_TIME, END_TIME);
		}
		catch(IllegalArgumentException e){
			error=e.getMessage();
		}

		assertEquals(error,"Date cannot be empty.");
	}

	@Test
	public void testAddAvailabilityNullStartTime() {
		String error = null;

		try {
			availability=service.addAvailability(TUTOR_EMAIL, DATE, null, END_TIME);
		}
		catch(IllegalArgumentException e){
			error=e.getMessage();
		}

		assertEquals(error,"Start time cannot be empty.");
	}

	@Test
	public void testAddAvailabilityNullEndTime() {
		String error = null;

		try {
			availability=service.addAvailability(TUTOR_EMAIL, DATE, START_TIME, null);
		}
		catch(IllegalArgumentException e){
			error=e.getMessage();
		}

		assertEquals(error,"End time cannot be empty.");
	}

	@Test
	public void testAddAvailabilityInvalidStartTime() {
		String error = null;

		try {
			availability=service.addAvailability(TUTOR_EMAIL, DATE, Time.valueOf("8:00:00"), END_TIME);
		}
		catch(IllegalArgumentException e){
			error=e.getMessage();
		}

		assertEquals(error,"Start time must be between 9 am and 9 pm.");
	}

	@Test
	public void testAddAvailabilityInvalidEndTime() {
		String error = null;

		try {
			availability=service.addAvailability(TUTOR_EMAIL, DATE, START_TIME, Time.valueOf("22:00:00"));		}
		catch(IllegalArgumentException e){
			error=e.getMessage();
		}

		assertEquals(error,"End time must be between 9 am and 9 pm.");
	}

	@Test
	public void testAddConflictingAvailabilityInfringeBefore() {
		String error = null;

		try {
			service.addAvailability(TUTOR_EMAIL, DATE, Time.valueOf("14:00:00"), Time.valueOf("17:00:00"));
		}
		catch(IllegalArgumentException e){
			error=e.getMessage();
		}

		assertEquals(error,"Availability conflicts with already existing availability.");
	}

	@Test
	public void testAddConflictingAvailabilityInfringeMiddle() {
		String error = null;

		try {
			service.addAvailability(TUTOR_EMAIL, DATE, Time.valueOf("17:00:00"), Time.valueOf("18:00:00"));
		}
		catch(IllegalArgumentException e){
			error=e.getMessage();
		}

		assertEquals(error,"Availability conflicts with already existing availability.");
	}

	@Test
	public void testAddConflictingAvailabilityInfringeEnd() {
		String error = null;

		try {
			service.addAvailability(TUTOR_EMAIL, DATE, Time.valueOf("18:00:00"), Time.valueOf("20:00:00"));
		}
		catch(IllegalArgumentException e){
			error=e.getMessage();
		}

		assertEquals(error,"Availability conflicts with already existing availability.");
	}

	@Test
	public void testAddAvailabilityStartTimeAfterEndTime() {
		String error = null;

		try {
			service.addAvailability(TUTOR_EMAIL, DATE, Time.valueOf("20:00:00"), Time.valueOf("18:00:00"));
		}
		catch(IllegalArgumentException e){
			error=e.getMessage();
		}

		assertEquals(error,"Start time must be before End time.");
	}

	@Test
	public void testUpdateAvailability() {

		try {
			availability = service.updateAvailability(TUTOR_EMAIL, DATE, Time.valueOf("16:00:00"), Time.valueOf("19:00:00"), Date.valueOf("2019-12-02"), Time.valueOf("18:00:00"), Time.valueOf("20:00:00"));
		}
		catch(IllegalArgumentException e) {
			fail();
		}

		assertEquals(Date.valueOf("2019-12-02"), availability.getDate());
		assertEquals(Time.valueOf("18:00:00"), availability.getStartTime());
		assertEquals(Time.valueOf("20:00:00"), availability.getEndTime());
	}
	
	@Test
	public void testUpdateAvailability2() {

		try {
			availability = service.updateAvailability(TUTOR_EMAIL, DATE, Time.valueOf("12:00:00"), Time.valueOf("15:00:00"), Date.valueOf("2019-12-02"), Time.valueOf("18:00:00"), Time.valueOf("20:00:00"));
		}
		catch(IllegalArgumentException e) {
			fail();
		}

		assertEquals(Date.valueOf("2019-12-02"), availability.getDate());
		assertEquals(Time.valueOf("18:00:00"), availability.getStartTime());
		assertEquals(Time.valueOf("20:00:00"), availability.getEndTime());
	}

	@Test
	public void testUpdateAvailabilityNewDate() {

		try {
			availability = service.updateAvailability(TUTOR_EMAIL, DATE, Time.valueOf("16:00:00"), Time.valueOf("19:00:00"), Date.valueOf("2019-12-02"), START_TIME, END_TIME);
		}
		catch(IllegalArgumentException e) {
			fail();
		}

		assertEquals(Date.valueOf("2019-12-02"), availability.getDate());
		assertEquals(START_TIME, availability.getStartTime());
		assertEquals(END_TIME, availability.getEndTime());
	}

	@Test
	public void testUpdateAvailabilityNewStartTimeAndEndTime() {

		try {
			availability = service.updateAvailability(TUTOR_EMAIL, DATE, Time.valueOf("16:00:00"), Time.valueOf("19:00:00"), DATE, START_TIME, END_TIME);
		}
		catch(IllegalArgumentException e) {
			fail();
		}

		assertEquals(DATE, availability.getDate());
		assertEquals(START_TIME, availability.getStartTime());
		assertEquals(END_TIME, availability.getEndTime());
	}

	@Test
	public void testUpdateAvailabilityNullDate() {
		String error = null;

		try {
			availability = service.updateAvailability(TUTOR_EMAIL, DATE, Time.valueOf("16:00:00"), Time.valueOf("19:00:00"), null, START_TIME, END_TIME);
		}
		catch(IllegalArgumentException e){
			error=e.getMessage();
		}

		assertEquals(error,"Date cannot be empty.");
	}

	@Test
	public void testUpdateAvailabilityNullStartTime() {
		String error = null;

		try {
			availability = service.updateAvailability(TUTOR_EMAIL, DATE, Time.valueOf("16:00:00"), Time.valueOf("19:00:00"), DATE, null, END_TIME);
		}
		catch(IllegalArgumentException e){
			error=e.getMessage();
		}

		assertEquals(error,"Start time cannot be empty.");
	}

	@Test
	public void testUpdateAvailabilityNullEndTime() {
		String error = null;

		try {
			availability = service.updateAvailability(TUTOR_EMAIL, DATE, Time.valueOf("16:00:00"), Time.valueOf("19:00:00"), DATE, START_TIME, null);
		}
		catch(IllegalArgumentException e){
			error=e.getMessage();
		}

		assertEquals(error,"End time cannot be empty.");
	}

	@Test
	public void testUpdateAvailabilityTutorNotFound() {
		String error = null;

		try {
			availability = service.updateAvailability("ppp@gmail.com", DATE, Time.valueOf("16:00:00"), Time.valueOf("19:00:00"), DATE, START_TIME, null);
		}
		catch(IllegalArgumentException e){
			error=e.getMessage();
		}

		assertEquals(error,"Tutor could not be found.");
	}

	@Test
	public void testUpdateAvailabilitySessionAlreadyBooked() {
		String error = null;

		try {
			availability = service.updateAvailability(TUTOR_EMAIL, DATE, Time.valueOf("12:00:00"), Time.valueOf("13:00:00"), DATE, START_TIME, END_TIME);
		}
		catch(IllegalArgumentException e){
			error=e.getMessage();
		}

		assertEquals(error,"Already a session at that time and date.");
	}

	@Test
	public void testDeleteAvailability() {

		try {
			service.deleteAvailability(TUTOR_EMAIL, DATE, Time.valueOf("16:00:00"), Time.valueOf("19:00:00"));
		}
		catch(IllegalArgumentException e){
			fail();
		}
	}

	@Test
	public void testDeleteAvailabilityNotFound() {
		String error = null;

		try {
			service.deleteAvailability(TUTOR_EMAIL, DATE, START_TIME, END_TIME);
		}
		catch(IllegalArgumentException e){
			error = e.getMessage();
		}

		assertEquals("Availability could not be found.", error);
	}

	@Test
	public void testGetExistingAvailability() {
		assertEquals(DATE, service.getAvailability(TUTOR_EMAIL, DATE, Time.valueOf("16:00:00"), Time.valueOf("19:00:00")).getDate());
		assertEquals(Time.valueOf("16:00:00"), service.getAvailability(TUTOR_EMAIL, DATE, Time.valueOf("16:00:00"), Time.valueOf("19:00:00")).getStartTime());
		assertEquals(Time.valueOf("19:00:00"), service.getAvailability(TUTOR_EMAIL, DATE, Time.valueOf("16:00:00"), Time.valueOf("19:00:00")).getEndTime());

	}

	@Test
	public void testGetNonExistingAvailability() {
		assertNull(service.getAvailability(TUTOR_EMAIL, Date.valueOf("2019-12-02"), START_TIME, END_TIME));
	}

	@Test
	public void testGetNonExistingAvailability2() {
		assertNull(service.getAvailability(TUTOR_EMAIL, DATE, Time.valueOf("16:00:00"), END_TIME));
	}

	@Test
	public void testGetAvailabilityTutorNotFound() {
		String error = null;

		try {
			availability = service.getAvailability("ppp@gmail.com", null, null, null);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Tutor could not be found.", error);
	}

	@Test
	public void testGetAllTutorAvailabilities() {
		assertEquals(2, service.getAllTutorAvailabilities(TUTOR_EMAIL).size());
	}
} 
