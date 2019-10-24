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
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.tutoringservice321.dao.AvailabilityRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.TutorRepository;
import ca.mcgill.ecse321.tutoringservice321.model.Availability;
import ca.mcgill.ecse321.tutoringservice321.model.Tutor;
import ca.mcgill.ecse321.tutoringservice321.service.TutoringService321Service;
import java.sql.Date;
import java.sql.Time;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
	
	@InjectMocks
	private TutoringService321Service service;

	private static final String AVAILABILITY_KEY = "TestAvailability";
	private static final String NONEXISTING_KEY = "NotAnAvailability";
	private static final Tutor TUTOR = new Tutor();
	private static final Date DATE = Date.valueOf("2019-12-01");
	private static final Time START_TIME = Time.valueOf("16:00:00");
	private static final Time END_TIME = Time.valueOf("16:00:00");
	private static final String TUTOR_EMAIL="katie@gmail.com";
	private static final String TUTOR_NAME="Katie Younge";
	private static final String TUTOR_PASSWORD="password";
	private static final String TUTOR_PHONE="8193296836";
	private static final int TUTOR_HOURLY_RATE=15;

	private String tutorEmail="katie@gmail.com";
	private String tutorEmail2="will@hotmail.ca";
//	private String name="Katie Younge";
//	private String password="password";
//	private String phoneNumber="8193296836";
//	private int hourlyRate=15;

	private Date date=Date.valueOf("2019-12-01");
	private Time startTime = Time.valueOf("10:00:00");
	private Time endTime = Time.valueOf("16:00:00");

	private Date newDate=Date.valueOf("2019-12-01");
	private Time newStartTime = Time.valueOf("9:00:00");
	private Time newEndTime = Time.valueOf("17:00:00");

	private Date nullDate=null;
	private Time nullStartTime = null;
	private Time nullEndTime = null;

	private Time invalidStartTime = Time.valueOf("22:00:00");
	private Time invalidEndTime = Time.valueOf("7:00:00");


	Availability availability;
	Tutor tutor;
	
	@Before
	public void setUp() {
		when(tutorDao.findTutorByEmail(anyString())).thenAnswer(
				(InvocationOnMock invocation) -> {
					if(invocation.getArgument(0).equals(TUTOR_EMAIL)) {
						Tutor tutor = new Tutor();
						tutor.setEmail(TUTOR_EMAIL);
						tutor.setName(TUTOR_NAME);
						tutor.setPassword(TUTOR_PASSWORD);
						tutor.setHourlyRate(TUTOR_HOURLY_RATE);
						tutor.setPhoneNumber(TUTOR_PHONE);
						return tutor;
					} else {
						return null;
					}
				});
		
		when(availabilityDao.findAvailabilityByTutorAndDateAndStartTime(any(Tutor.class), any(Date.class), any(Time.class))).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(TUTOR)) {
				Availability availability = new Availability();
				
				availability.setTutor(TUTOR);
				availability.setDate(DATE);
				availability.setStartTime(START_TIME);
				availability.setEndTime(END_TIME);
				return availability;
			} else {
				return null;
			}
		});
		
	}

	@Test
	public void testAddAvailability() {

		

		try {
			availability=service.addAvailability(tutorEmail, date, startTime, endTime);
		}
		catch(IllegalArgumentException e){
			fail();
		}

		assertEquals(date, availability.getDate());
		assertEquals(startTime, availability.getStartTime());
		assertEquals(endTime, availability.getEndTime());
	}

	@Test
	public void testAddAvailabilityNullDate() {
		String error = null;

		

		try {
			service.addAvailability(tutorEmail, nullDate, startTime, endTime);
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
			service.addAvailability(tutorEmail, date, nullStartTime, endTime);
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
			service.addAvailability(tutorEmail, date, startTime, nullEndTime);
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
			service.addAvailability(tutorEmail, date, invalidStartTime, endTime);
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
			service.addAvailability(tutorEmail, date, startTime, invalidEndTime);
		}
		catch(IllegalArgumentException e){
			error=e.getMessage();
		}

		assertEquals(error,"End time must be between 9 am and 9 pm.");
	}

	@Test
	public void testAddAlreadyExistingAvailability() {
		String error = null;

		

		try {
			service.addAvailability(tutorEmail, date, startTime, endTime);
			service.addAvailability(tutorEmail, date, startTime, endTime);
		}
		catch(IllegalArgumentException e){
			error=e.getMessage();
		}

		assertEquals(error,"Availability already exists.");
	}

	@Test
	public void testAddConflictingAvailability() {
		String error = null;

		

		try {
			service.addAvailability(tutorEmail, date, startTime, endTime);
			service.addAvailability(tutorEmail, newDate, newStartTime, newEndTime);
		}
		catch(IllegalArgumentException e){
			error=e.getMessage();
		}

		assertEquals(error,"Availability conflicts with already existing availability.");
	}

	@Test
	public void testUpdateAvailabilityAllFields() {

		

		try {
			service.addAvailability(tutorEmail, date, startTime, endTime);
			service.updateAvailability(tutorEmail, date, startTime, endTime, newDate, newStartTime, newEndTime);

		}
		catch(IllegalArgumentException e){
			fail();
		}

		assertEquals(newDate, availability.getDate());
		assertEquals(newStartTime, availability.getStartTime());
		assertEquals(newEndTime, availability.getEndTime());
	}

	@Test
	public void testUpdateAvailabilityNewDate() {

		

		try {
			service.addAvailability(tutorEmail, date, startTime, endTime);
			service.updateAvailability(tutorEmail, date, startTime, endTime, newDate, startTime, endTime);

		}
		catch(IllegalArgumentException e){
			fail();
		}

		assertEquals(newDate, availability.getDate());
		assertEquals(startTime, availability.getStartTime());
		assertEquals(endTime, availability.getEndTime());
	}

	@Test
	public void testUpdateAvailabilityNewStartTime() {

		

		try {
			service.addAvailability(tutorEmail, date, startTime, endTime);
			service.updateAvailability(tutorEmail, date, startTime, endTime, date, newStartTime, endTime);

		}
		catch(IllegalArgumentException e){
			fail();
		}

		assertEquals(date, availability.getDate());
		assertEquals(newStartTime, availability.getStartTime());
		assertEquals(endTime, availability.getEndTime());
	}

	@Test
	public void testUpdateAvailabilityNewEndTime() {

		

		try {
			service.addAvailability(tutorEmail, date, startTime, endTime);
			service.updateAvailability(tutorEmail, date, startTime, endTime, date, startTime, newEndTime);

		}
		catch(IllegalArgumentException e){
			fail();
		}

		assertEquals(date, availability.getDate());
		assertEquals(startTime, availability.getStartTime());
		assertEquals(newEndTime, availability.getEndTime());
	}

	@Test
	public void testUpdateAvailabilityDoesNotExist() {
		String error = null;

		

		try {
			service.updateAvailability(tutorEmail, date, startTime, endTime, nullDate, startTime, endTime);
		}
		catch(IllegalArgumentException e){
			error=e.getMessage();
		}

		assertEquals(error,"Availability cannot be found.");
	}

	@Test
	public void testUpdateAvailabilityNullDate() {
		String error = null;

		

		try {
			service.addAvailability(tutorEmail, date, startTime, endTime);
			service.updateAvailability(tutorEmail, date, startTime, endTime, nullDate, startTime, endTime);
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
			service.addAvailability(tutorEmail, date, startTime, endTime);
			service.updateAvailability(tutorEmail, date, startTime, endTime, date, nullStartTime, endTime);
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
			service.addAvailability(tutorEmail, date, startTime, endTime);
			service.updateAvailability(tutorEmail, date, startTime, endTime, date, startTime, nullEndTime);
		}
		catch(IllegalArgumentException e){
			error=e.getMessage();
		}

		assertEquals(error,"End time cannot be empty.");
	}

	@Test
	public void testUpdateAvailabilitySessionAlreadyBooked() {
		String error = null;

		

		try {
			service.addAvailability(tutorEmail, date, startTime, endTime);
			service.createSession(tutorEmail, date, startTime, endTime);
			service.updateAvailability(tutorEmail, date, startTime, endTime, newDate, newStartTime, newEndTime);
		}
		catch(IllegalArgumentException e){
			error=e.getMessage();
		}

		assertEquals(error,"Already a session at that time and date.");
	}

	@Test
	public void testUpdateAvailabilityTutorNotFound() {
		String error = null;

		

		try {
			service.addAvailability(tutorEmail, date, startTime, endTime);
			service.updateAvailability(tutorEmail2, date, startTime, endTime, newDate, newStartTime, newEndTime);
		}
		catch(IllegalArgumentException e){
			error=e.getMessage();
		}

		assertEquals(error,"Tutor could not be found.");
	}

	@Test
	public void testDeleteAvailability() {
		

		try {
			service.addAvailability(tutorEmail, date, startTime, endTime);
			service.deleteAvailability(tutorEmail, date, startTime, endTime);
		}
		catch(IllegalArgumentException e){
			fail();
		}

		
	}

	@Test
	public void testDeleteAvailabilityTutorNotFound() {

		String error = null;


		try {
			service.addAvailability(tutorEmail, date, startTime, endTime);
			service.deleteAvailability(tutorEmail2, date, startTime, endTime);
		}
		catch(IllegalArgumentException e){
			error=e.getMessage();
		}

		assertEquals(error,"Tutor could not be found.");
	}
}
