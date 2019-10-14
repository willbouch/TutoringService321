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
public class TestAvailability {


	@Autowired
	TutoringService321Service service;

	@Autowired
	AvailabilityRepository availabilityRepository;

	@After
	public void clearDatabase() {
		availabilityRepository.deleteAll();
	}

	@Test
	public void testWriteAvailability() {
		assertEquals(0, service.getAllAvailabilities().size());

		String tutorEmail = "h@gmail.com";

		Date date = Date.valueOf("2019-12-01");
		Time startTime = Time.valueOf("10:00:00");
		Time endTime = Time.valueOf("16:00:00");

		try {
			service.createTutor(tutorEmail, "Hadi", "123", "514356241", 20);
			service.createAvailability(tutorEmail, date, startTime, endTime);

			List <Availability> allAvailabilities = service.getAllAvailabilities();

			assertEquals(1, allAvailabilities.size());
			assertEquals(date, allAvailabilities.get(0).getDate());
			assertEquals(startTime, allAvailabilities.get(0).getStartTime());
			assertEquals(endTime, allAvailabilities.get(0).getEndTime());
		} catch (IllegalArgumentException e) {
			fail();
		}

	}
	
	
	@Test
	public void testViewAvailabilityNullDate() {
		assertEquals(0, service.getAllAvailabilities().size());

		Date date = (Date) null;
		Time startTime = Time.valueOf("10:00:00");
		Time endTime = Time.valueOf("16:00:00");

		String tutorEmail = "h@gmail.com";
		
		try {
			service.createTutor(tutorEmail, "Hadi", "123", "514356241", 20);
			service.createAvailability(tutorEmail, date, startTime, endTime);
		} catch (IllegalArgumentException e) {
			assertEquals(0, service.getAllAvailabilities().size());
		}
	}
	
	
	@Test
	public void testViewAvailabilityNullStartTime() {
		assertEquals(0, service.getAllAvailabilities().size());

		Date date = Date.valueOf("2019-12-01");
		Time startTime = (Time) null;
		Time endTime = Time.valueOf("16:00:00");

		String tutorEmail = "h@gmail.com";
		
		try {
			service.createTutor(tutorEmail, "Hadi", "123", "514356241", 20);
			service.createAvailability(tutorEmail, date, startTime, endTime);
		} catch (IllegalArgumentException e) {
			assertEquals(0, service.getAllAvailabilities().size());
		}
	}

	
	@Test
	public void testViewAvailabilityNullEndTime() {
		assertEquals(0, service.getAllAvailabilities().size());

		Date date = Date.valueOf("2019-12-01");
		Time startTime = Time.valueOf("10:00:00");
		Time endTime = (Time) null;

		String tutorEmail = "h@gmail.com";
		
		try {
			service.createTutor(tutorEmail, "Hadi", "123", "514356241", 20);
			service.createAvailability(tutorEmail, date, startTime, endTime);
		} catch (IllegalArgumentException e) {
			assertEquals(0, service.getAllAvailabilities().size());
		}
	}

}

