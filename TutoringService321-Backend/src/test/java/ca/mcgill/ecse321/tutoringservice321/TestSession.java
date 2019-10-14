package ca.mcgill.ecse321.tutoringservice321;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.tutoringservice321.model.*;
import ca.mcgill.ecse321.tutoringservice321.service.TutoringService321Service;
import ca.mcgill.ecse321.tutoringservice321.dao.*;
import ca.mcgill.ecse321.tutoringservice321.service.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSession {


	@Autowired
	TutoringService321Service service;

	@Autowired
	SessionRepository sessionRepository;

	@Before
	public void setUp(){
		// Session needs a tutor instance
		service.createTutor("k@gmail.com", "Katie", "123", "432", 34);
	}

	@After
	public void clearDatabase() {
		sessionRepository.deleteAll();
	}
	
	@Test
	public void testWriteSession() {
		assertEquals(0, service.getAllSessions().size());
				
		Date date = Date.valueOf("2019-11-13");
		Time startTime = Time.valueOf("10:00:00");
		Time endTime = Time.valueOf("16:00:00");
		
		String tutorEmail = "k@gmail.com";
		
		try {
			service.createSession(tutorEmail, date, endTime, startTime);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		List<Session> allSessions = service.getAllSessions();
		
		assertEquals(1, allSessions.size());
		assertEquals(date, allSessions.get(0).getDate());
		assertEquals(startTime, allSessions.get(0).getStarTime());
		assertEquals(endTime, allSessions.get(0).getEndTime());
	}
	
	@Test
	public void testWriteSessionNullDate() {
		assertEquals(0, service.getAllSessions().size());
		
		Date date = (Date) null;
		Time startTime = Time.valueOf("10:00:00");
		Time endTime = Time.valueOf("16:00:00");
		
		String tutorEmail = "k@gmail.com";
		
		try {
			service.createSession(tutorEmail, date, endTime, startTime);
		} catch (IllegalArgumentException e) {
			assertEquals(0, service.getAllSessions().size());
		}
	}
	
	@Test
	public void testWriteSessionNullStartTime() {
		assertEquals(0, service.getAllSessions().size());
		
		Date date = Date.valueOf("2020-01-10");
		Time startTime = (Time) null;
		Time endTime = Time.valueOf("16:00:00");
		
		String tutorEmail = "k@gmail.com";
		
		try {
			service.createSession(tutorEmail, date, endTime, startTime);
		} catch (IllegalArgumentException e) {
			assertEquals(0, service.getAllSessions().size());
		}
	}

	@Test
	public void testWriteSessionNullEndTime() {
		assertEquals(0, service.getAllSessions().size());
		
		Date date = Date.valueOf("2020-01-10");
		Time startTime = Time.valueOf("10:00:00");
		Time endTime = (Time) null;
		
		String tutorEmail = "k@gmail.com";
		
		try {
			service.createSession(tutorEmail, date, endTime, startTime);
		} catch (IllegalArgumentException e) {
			assertEquals(0, service.getAllSessions().size());
		}
	}

}