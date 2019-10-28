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

import ca.mcgill.ecse321.tutoringservice321.dao.ReviewRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.SessionRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.TutorRepository;
import ca.mcgill.ecse321.tutoringservice321.model.Course;
import ca.mcgill.ecse321.tutoringservice321.model.Review;
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
public class ReviewServiceTests {

	@Mock
	private TutorRepository tutorDao;

	@Mock
	private SessionRepository sessionDao;

	@Mock
	private ReviewRepository reviewDao;

	@InjectMocks
	private TutoringService321Service service;

	private static final Tutor TUTOR = new Tutor();
	private static final Date DATE = Date.valueOf("2019-12-01");
	private static final Time START_TIME = Time.valueOf("12:00:00");
	private static final Time END_TIME = Time.valueOf("13:00:00");
	private static final String TUTOR_EMAIL="katie@gmail.com";
	private static final String TUTOR_NAME="Katie Younge";
	private static final String TUTOR_PASSWORD="password";
	private static final String TUTOR_PHONE="8193296836";
	private static final int TUTOR_HOURLY_RATE=15;
	private static final String REVIEW = "Good session, thanks";

	Review review;

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
						return tutor;
					} else {
						return null;
					}
				});

		when(sessionDao.findSessionByTutorAndDate(any(Tutor.class), any(Date.class))).thenAnswer( (InvocationOnMock invocation) -> {
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
		
		when(sessionDao.findSessionByTutor(any(Tutor.class))).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(TUTOR)) {
				Session session = new Session();
				session.setTutor(TUTOR);
				session.setDate(DATE);
				session.setStarTime(START_TIME);
				session.setEndTime(END_TIME);
				
				Set<Review> reviews = new HashSet<Review>();
				Review review1 = new Review();
				review1.setAuthorEmail("1@gmail.com");
				review1.setTextualReview("Good");
				reviews.add(review1);
				Review review2 = new Review();
				review2.setAuthorEmail("2@gmail.com");
				review2.setTextualReview("Good");
				reviews.add(review2);
				Review review3 = new Review();
				review3.setAuthorEmail(TUTOR_EMAIL);
				review3.setTextualReview("Good");
				reviews.add(review3);
				
				session.setReview(reviews);

				Set<Session> sessions = new HashSet<Session>();
				sessions.add(session);

				return sessions;
			} else {
				return null;
			}
		});
		
		when(reviewDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			Set<Review> reviews = new HashSet<Review>();
			Review review1 = new Review();
			review1.setAuthorEmail("1@gmail.com");
			review1.setTextualReview("Good");
			reviews.add(review1);
			Review review2 = new Review();
			review2.setAuthorEmail("2@gmail.com");
			review2.setTextualReview("Good");
			reviews.add(review2);
			Review review3 = new Review();
			review3.setAuthorEmail(TUTOR_EMAIL);
			review3.setTextualReview("Good");
			reviews.add(review3);
			
			return reviews;
		});

		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		when(reviewDao.save(any(Review.class))).thenAnswer(returnParameterAsAnswer);
	}

	@Test
	public void testSubmitTutorReview() {

		try {
			review = service.submitTutorReview(REVIEW, TUTOR_EMAIL, DATE, START_TIME, END_TIME);
		} catch(IllegalArgumentException e) {
			fail();
		}

		assertEquals(REVIEW, review.getTextualReview());
		assertEquals(TUTOR_EMAIL, review.getAuthorEmail());
	}

	@Test
	public void testSubmitTutorReviewNullReview() {
		String error= null;

		try {
			review = service.submitTutorReview(null, TUTOR_EMAIL, DATE, START_TIME, END_TIME);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Review cannot be empty.", error);
	}

	@Test
	public void testSubmitTutorReviewEmptyReview() {
		String error= null;

		try {
			review = service.submitTutorReview("", TUTOR_EMAIL, DATE, START_TIME, END_TIME);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Review cannot be empty.", error);
	}
	
	@Test
	public void testSubmitTutorReviewNullTutorEmail() {
		String error= null;

		try {
			review = service.submitTutorReview(REVIEW, null, DATE, START_TIME, END_TIME);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Tutor Email cannot be empty.", error);
	}

	@Test
	public void testSubmitTutorReviewEmptyTutorEmail() {
		String error= null;

		try {
			review = service.submitTutorReview(REVIEW, "", DATE, START_TIME, END_TIME);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Tutor Email cannot be empty.", error);
	}
	
	@Test
	public void testSubmitTutorReviewNullDate() {
		String error= null;

		try {
			review = service.submitTutorReview(REVIEW, TUTOR_EMAIL, null, START_TIME, END_TIME);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Date cannot be empty.", error);
	}

	@Test
	public void testSubmitTutorReviewNullStartTime() {
		String error= null;

		try {
			review = service.submitTutorReview(REVIEW, TUTOR_EMAIL, DATE, null, END_TIME);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Start Time cannot be empty.", error);
	}
	
	@Test
	public void testSubmitTutorReviewNullEndTime() {
		String error= null;

		try {
			review = service.submitTutorReview(REVIEW, TUTOR_EMAIL, DATE, START_TIME, null);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("End Time cannot be empty.", error);
	}
	
	@Test
	public void testSubmitTutorReviewTutorNotFound() {
		String error= null;

		try {
			review = service.submitTutorReview(REVIEW, "aaa@gmail.com", DATE, START_TIME, END_TIME);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Tutor could not be found.", error);
	}

	@Test
	public void testSubmitTutorReviewSessionNotFoundByStartTime() {
		String error= null;

		try {
			review = service.submitTutorReview(REVIEW, TUTOR_EMAIL, DATE, Time.valueOf("15:00:00"), END_TIME);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Session could not be found.", error);
	}
	
	@Test
	public void testSubmitTutorReviewSessionNotFoundByEndTime() {
		String error= null;

		try {
			review = service.submitTutorReview(REVIEW, TUTOR_EMAIL, DATE, START_TIME, Time.valueOf("20:00:00"));
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Session could not be found.", error);
	}
	
	@Test
	public void testSubmitTutorReviewSessionNotFoundByDate() {
		String error= null;

		try {
			review = service.submitTutorReview(REVIEW, TUTOR_EMAIL, Date.valueOf("2018-04-23"), START_TIME, END_TIME);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Sessions with that date and tutor could not be found.", error);
	}
	
	@Test
	public void testGetAllTutorReviews() {
		assertEquals(2, service.getAllTutorReviews(TUTOR_EMAIL).size());
	}
	
	@Test
	public void testGetAllTutorTutorNotFound() {
		String error = null;
		
		try {
			service.getAllTutorReviews("hey");
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Tutor could not be found.", error);
	}
	
	@Test
	public void testGetAllReviews() {
		assertEquals(3, service.getAllReviews().size());
	}
	
}
