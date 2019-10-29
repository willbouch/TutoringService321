package ca.mcgill.ecse321.tutoringservice321.controllertests;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

import org.junit.Test;

import ca.mcgill.ecse321.tutoringservice321.controller.TutoringService321RestController;
import ca.mcgill.ecse321.tutoringservice321.service.TutoringService321Service;

public class SessionControllerTests {

	private TutoringService321RestController controller;
	
	private static final Date DATE = Date.valueOf("2019-12-01");
	private static final LocalTime START_TIME = LocalTime.of(12, 00, 00);
	private static final LocalTime END_TIME = LocalTime.of(13, 00, 00);
	private static final String TUTOR_EMAIL="katie@gmail.com";
	private static final String TUTOR_NAME="Katie Younge";
	private static final String TUTOR_PASSWORD="password";
	private static final String TUTOR_PHONE="8193296836";
	private static final int TUTOR_HOURLY_RATE=15;
	private static final String REVIEW = "Good session, thanks";
	
	@Test
	public void testCancelSession() {
		controller.cancelSession(TUTOR_EMAIL, DATE, START_TIME, END_TIME);
	}

}
