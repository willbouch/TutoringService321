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

	@Before
	public void setUp(){
		// Availability needs to be connected to a tutor
		service.createTutor("k@gmail.com", "Kyjauna", "123", "514356241", 20);
	}

	@After
	public void clearDatabase() {
		availabilityRepository.deleteAll();
	}

	@Test
	// Failed
	public void testWriteAvailability() {
		assertEquals(0, service.getAllAvailabilities().size());


		String tutorEmail = "hadi@gmail.com";

		Calendar c = Calendar.getInstance();
		c.set(2019, c.DECEMBER, 01, 12, 00);
		Date date = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(16, 00);
		Time endTime = new Time(c.getTimeInMillis());

		try {
			service.createAvailability(tutorEmail, date, startTime, endTime);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List <Availability> allAvailabilities = service.getAllAvailabilities();

		assertEquals(1, allAvailabilities.size());
		assertEquals(date, allAvailabilities.get(0).getDate());
		assertEquals(startTime, allAvailabilities.get(0).getStartTime());
		assertEquals(endTime, allAvailabilities.get(0).getEndTime());

	}

	@Test
	// Failed
	public void testViewAvailability() {
		assertEquals(0, service.getAllAvailabilities().size());

		String tutorEmail = "hadi@gmail.com";

		Calendar c = Calendar.getInstance();
		c.set(2019, c.DECEMBER, 01, 12, 00);
		Date date = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(16, 00);
		Time endTime = new Time(c.getTimeInMillis());

		try {
			//First create a tutor since an availability has to have a tutor that is not null
			service.createTutor(tutorEmail, "Will", "1", "3", 3);
			service.createAvailability(tutorEmail, date, startTime, endTime);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List <Availability> allAvailabilities = service.getAllAvailabilities();

		assertEquals(1, allAvailabilities.size());
		assertEquals(startTime, allAvailabilities.get(0).getStartTime());

	}
}

