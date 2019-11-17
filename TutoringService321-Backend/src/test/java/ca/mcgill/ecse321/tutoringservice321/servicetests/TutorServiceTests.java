package ca.mcgill.ecse321.tutoringservice321.servicetests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.tutoringservice321.TutoringService321Application;
import ca.mcgill.ecse321.tutoringservice321.dao.TutorRepository;
import ca.mcgill.ecse321.tutoringservice321.model.Tutor;
import ca.mcgill.ecse321.tutoringservice321.service.TutoringService321Service;

@RunWith(MockitoJUnitRunner.class)
public class TutorServiceTests {	

	@Mock
	private TutorRepository tutorDao;

	@InjectMocks
	private TutoringService321Service service;

	private static final String TUTOR_EMAIL = "william@gmail.com";
	private static final String TUTOR_NAME = "TestTutor";
	private static final String TUTOR_PASSWORD = "TestPassword";
	private static final String TUTOR_PHONE = "6666666666";
	private static final int TUTOR_HOURLYRATE = 10;
	private Tutor tutor;

	@Before
	public void setMockOutput() {
		when(tutorDao.findTutorByEmail(anyString())).thenAnswer(
				(InvocationOnMock invocation) -> {
					if(invocation.getArgument(0).equals(TUTOR_EMAIL)) {
						Tutor tutor = new Tutor();
						tutor.setEmail(TUTOR_EMAIL);
						tutor.setName(TUTOR_NAME);
						tutor.setPassword(TUTOR_PASSWORD);
						tutor.setHourlyRate(TUTOR_HOURLYRATE);
						tutor.setPhoneNumber(TUTOR_PHONE);
						return tutor;
					} else {
						return null;
					}
				});
		when(tutorDao.findAll()).thenAnswer(
				(InvocationOnMock invocation) -> {
					Tutor tutor = new Tutor();
					tutor.setEmail(TUTOR_EMAIL);
					tutor.setName(TUTOR_NAME);
					tutor.setPassword(TUTOR_PASSWORD);
					tutor.setHourlyRate(TUTOR_HOURLYRATE);
					tutor.setPhoneNumber(TUTOR_PHONE);

					List<Tutor> tutors = new ArrayList<Tutor>();
					tutors.add(tutor);
					return tutors;

				});
		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		when(tutorDao.save(any(Tutor.class))).thenAnswer(returnParameterAsAnswer);
	}

	@Test
	public void testCreateTutor() {

		String name = "William";
		String email = "w@gmail.com";
		String phoneNumber = "4185730193";
		String password = "youguysarekillingme";
		int hourlyRate = 15;

		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
		} catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			fail();
		}

		assertEquals(name, tutor.getName());
		assertEquals(email, tutor.getEmail());
		assertEquals(phoneNumber, tutor.getPhoneNumber());
		assertEquals(password, tutor.getPassword());
		assertEquals(hourlyRate, tutor.getHourlyRate());
	}

	@Test
	public void testCreateTutorNameNull() {

		String name = null;
		String email = "w@gmail.com";
		String phoneNumber = "4185730193";
		String password = "youguysarekillingme";
		int hourlyRate = 15;
		String error = null;

		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Name cannot be empty.", error);
	}

	@Test
	public void testCreateTutorNameEmpty() {

		String name = "";
		String email = "w@gmail.com";
		String phoneNumber = "4185730193";
		String password = "youguysarekillingme";
		int hourlyRate = 15;
		String error = null;

		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Name cannot be empty.", error);
	}

	@Test
	public void testCreateTutorEmailNull() {

		String name = "William";
		String email = null;
		String phoneNumber = "4185730193";
		String password = "youguysarekillingme";
		int hourlyRate = 15;
		String error = null;

		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Email cannot be empty.", error);
	}

	@Test
	public void testCreateTutorEmailEmpty() {

		String name = "William";
		String email = "";
		String phoneNumber = "4185730193";
		String password = "youguysarekillingme";
		int hourlyRate = 15;
		String error = null;

		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Email cannot be empty.", error);
	}

	@Test
	public void testCreateTutorPhoneNumberNull() {

		String name = "William";
		String email = "w@gmail.com";
		String phoneNumber = null;
		String password = "youguysarekillingme";
		int hourlyRate = 15;
		String error = null;

		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Phone number cannot be empty.", error);
	}

	@Test
	public void testCreateTutorPhoneNumberEmpty() {

		String name = "William";
		String email = "w@gmail.com";
		String phoneNumber = "";
		String password = "youguysarekillingme";
		int hourlyRate = 15;
		String error = null;

		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Phone number cannot be empty.", error);
	}

	@Test
	public void testCreateTutorPasswordNull() {

		String name = "William";
		String email = "w@gmail.com";
		String phoneNumber = "4185730193";
		String password = null;
		int hourlyRate = 15;
		String error = null;

		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Password cannot be empty.", error);
	}

	@Test
	public void testCreateTutorPasswordEmpty() {

		String name = "William";
		String email = "w@gmail.com";
		String phoneNumber = "4185730193";
		String password = "";
		int hourlyRate = 15;
		String error = null;

		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Password cannot be empty.", error);
	}

	@Test
	public void testCreateTutorHourlyRateNegative() {

		String name = "William";
		String email = "w@gmail.com";
		String phoneNumber = "4185730193";
		String password = "youguysarekillingme";
		int hourlyRate = -15;
		String error = null;

		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Hourly has to be a positive number.", error);
	}

	@Test
	public void testCreateTutorWithEmailThatAlreadyExists() {

		String name = "William";
		String email = "william@gmail.com";
		String phoneNumber = "4185730193";
		String password = "youguysarekillingme";
		int hourlyRate = 15;
		String error = null;

		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("A tutor with the same email already exists.", error);
	}

	@Test
	public void testCreateTutorWithInvalidEmailWithNoAtSymbol() {

		String name = "William";
		String email = "invalidEmail.com";
		String phoneNumber = "4185730193";
		String password = "youguysarekillingme";
		int hourlyRate = 15;
		String error = null;

		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("The email should be in the format of <example@something.ca/com/etc.>.", error);
	}

	@Test
	public void testCreateTutorWithInvalidEmailWithNoDotSomething() {

		String name = "William";
		String email = "invalidEmail@gmail";
		String phoneNumber = "4185730193";
		String password = "youguysarekillingme";
		int hourlyRate = 15;
		String error = null;

		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("The email should be in the format of <example@something.ca/com/etc.>.", error);
	}

	@Test
	public void testCreateTutorWithInvalidPassword() {

		String name = "William";
		String email = "w@gmail.com";
		String phoneNumber = "4185730193";
		String password = "12345";
		int hourlyRate = 15;
		String error = null;

		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Password has to be at least 8 characters long.", error);
	}

	@Test
	public void testCreateTutorWithInvalidPhoneNumber() {

		String name = "William";
		String email = "w@gmail.com";

		String phoneNumber = "418573";
		String password = "youguysarekillingme";
		int hourlyRate = 15;
		String error = null;

		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Phone number has to be 10 character long.", error);
	}

	//=================================

	@Test
	public void testUpdateTutor() {

		String email = "william@gmail.com";
		String nameNew = "Kyjauna";
		String phoneNumberNew = "2505102578";
		int hourlyRateNew = 20;

		try {
			tutor = service.updateTutor(email, nameNew, phoneNumberNew, hourlyRateNew);
		} catch(IllegalArgumentException e) {
			fail();
		}

		assertEquals(nameNew, tutor.getName());
		assertEquals(phoneNumberNew, tutor.getPhoneNumber());
		assertEquals(hourlyRateNew, tutor.getHourlyRate());
	}

	@Test
	public void testUpdateTutorEmailNull() {

		String email = "william@gmail.com";
		String nameNew = "Kyjauna";
		String phoneNumberNew = "2505102578";
		int hourlyRateNew = 20;
		String error = null;

		try {
			email = null;
			tutor = service.updateTutor(email, nameNew, phoneNumberNew, hourlyRateNew);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("The tutor with that email could not be found.", error);
	}

	@Test
	public void testUpdateTutorEmailEmpty() {

		String email = "william@gmail.com";
		String nameNew = "Kyjauna";
		String phoneNumberNew = "2505102578";
		int hourlyRateNew = 20;
		String error = null;

		try {
			email = "";
			tutor = service.updateTutor(email, nameNew, phoneNumberNew, hourlyRateNew);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("The tutor with that email could not be found.", error);
	}

	@Test
	public void testUpdateTutorNameNull() {

		String email = "william@gmail.com";
		String nameNew = null;
		String phoneNumberNew = "2505102578";
		int hourlyRateNew = 20;
		String error = null;

		try {
			tutor = service.updateTutor(email, nameNew, phoneNumberNew, hourlyRateNew);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Name cannot be empty.", error);
	}

	@Test
	public void testUpdateTutorNameEmpty() {

		String email = "william@gmail.com";
		String nameNew = "";
		String phoneNumberNew = "2505102578";
		int hourlyRateNew = 20;
		String error = null;

		try {
			tutor = service.updateTutor(email, nameNew, phoneNumberNew, hourlyRateNew);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Name cannot be empty.", error);
	}

	@Test
	public void testUpdateTutorPhoneNumberNull() {

		String email = "william@gmail.com";
		String nameNew = "Kyjauna";
		String phoneNumberNew = null;
		int hourlyRateNew = 20;
		String error = null;

		try {
			tutor = service.updateTutor(email, nameNew, phoneNumberNew, hourlyRateNew);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Phone number cannot be empty.", error);
	}

	@Test
	public void testUpdateTutorPhoneNumberEmpty() {

		String email = "william@gmail.com";
		String nameNew = "Kyjauna";
		String phoneNumberNew = "";
		int hourlyRateNew = 20;
		String error = null;

		try {
			tutor = service.updateTutor(email, nameNew, phoneNumberNew, hourlyRateNew);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Phone number cannot be empty.", error);
	}

	@Test
	public void testUpdateTutorHourlyRateNegative() {

		String email = "william@gmail.com";
		String nameNew = "Kyjauna";
		String phoneNumberNew = "2505102578";
		int hourlyRateNew = -20;
		String error = null;

		try {
			tutor = service.updateTutor(email, nameNew, phoneNumberNew, hourlyRateNew);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Hourly has to be a positive number.", error);
	}

	@Test
	public void testUpdateTutorWithInvalidPhoneNumber() {

		String email = "william@gmail.com";
		String nameNew = "Kyjauna";
		String phoneNumberNew = "2134";
		int hourlyRateNew = 20;
		String error = null;

		try {
			tutor = service.updateTutor(email, nameNew, phoneNumberNew, hourlyRateNew);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Phone number has to be 10 character long.", error);
	}

	@Test
	public void testChangePassword() {

		String email = "william@gmail.com";
		String password = TUTOR_PASSWORD;

		String newPassword = "aaaaaaaaaaa";

		try {
			tutor = service.changePassword(email, password, newPassword);
		} catch(IllegalArgumentException e) {
			fail();
		}

		assertEquals(newPassword, tutor.getPassword());
	}
	
	@Test
	public void testChangePasswordTutorNotFound() {

		String email = "ppp@gmail.com";
		String password = TUTOR_PASSWORD;

		String newPassword = "aaaaaaaaaaa";

		String error = null;
		
		try {
			tutor = service.changePassword(email, password, newPassword);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Tutor could not be found.", error);
	}

	@Test
	public void testChangePasswordNull() {

		String email = "william@gmail.com";
		String password = TUTOR_PASSWORD;
		String newPassword = null;

		String error = null;

		try {
			tutor = service.changePassword(email, password, newPassword);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Password cannot be empty.", error);
	}

	@Test
	public void testChangePasswordEmpty() {

		String email = "william@gmail.com";
		String password = TUTOR_PASSWORD;

		String newPassword = "";

		String error = null;

		try {
			tutor = service.changePassword(email, password, newPassword);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Password cannot be empty.", error);
	}

	@Test
	public void testChangePasswordInvalidPassword() {

		String email = "william@gmail.com";
		String password = TUTOR_PASSWORD;

		String newPassword = "111111";

		String error = null;

		try {
			tutor = service.changePassword(email, password, newPassword);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Password has to be at least 8 characters long.", error);
	}

	@Test
	public void testChangePasswordWrongOldPassword() {

		String email = "william@gmail.com";

		String newPassword = "111111";

		String error = null;

		try {
			tutor = service.changePassword(email, "123456789", newPassword);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("That is not the correct password.", error);
	}

	@Test
	public void testDeleteTutor() {

		String email = "william@gmail.com";

		try {
			service.deleteTutor(email);
		} catch(IllegalArgumentException e) {
			fail();
		}
	}

	@Test
	public void testDeleteTutorNotFound() {
		String email = "w@gmail.com";
		String error = null;
		
		try {
			service.deleteTutor(email);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Tutor could not be found.", error);
	}
	
	@Test
	public void testGetExistingTutor() {
		assertEquals(TUTOR_EMAIL, service.getTutor(TUTOR_EMAIL).getEmail());
	}

	@Test
	public void testGetNonExistingTutor() {
		assertNull(service.getTutor("Marwan"));
	}
	
	@Test 
	public void testLogin() {
		
		try {
			tutor = service.loginAsTutor(TUTOR_EMAIL, TUTOR_PASSWORD);
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(TutoringService321Service.getLoggedUser(), tutor);
	}
	
	@Test 
	public void testLoginNullEmail() {
		String error = null;
		
		try {
			tutor = service.loginAsTutor(null, TUTOR_PASSWORD);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Email cannot be empty.", error);
	}
	
	@Test 
	public void testLoginEmptyEmail() {
		String error = null;
		
		try {
			tutor = service.loginAsTutor("", TUTOR_PASSWORD);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Email cannot be empty.", error);
	}
	
	@Test 
	public void testLoginNullPassword() {
		String error = null;
		
		try {
			tutor = service.loginAsTutor(TUTOR_EMAIL, null);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Password cannot be empty.", error);
	}
	
	@Test 
	public void testLoginTutorNotFoundByPassword() {
		String error = null;
		
		try {
			tutor = service.loginAsTutor(TUTOR_EMAIL, "aaaaaaaa");
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Could not find any corresponding tutor account.", error);
	}
	
	@Test 
	public void testLoginTutorNotFoundByEmail() {
		String error = null;
		
		try {
			tutor = service.loginAsTutor("aaa@gmail.com", TUTOR_PASSWORD);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Could not find any corresponding tutor account.", error);
	}
	
	@Test 
	public void testLoginEmptyPassword() {
		String error = null;
		
		try {
			tutor = service.loginAsTutor(TUTOR_EMAIL, "");
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Password cannot be empty.", error);
	}
	
	@Test
	public void testLogout() {

		try {
			service.logout();
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(null, TutoringService321Service.getLoggedUser());
	}
}