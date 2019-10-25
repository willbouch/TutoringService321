package ca.mcgill.ecse321.tutoringservice321.servicetests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.tutoringservice321.dao.TutorRepository;
import ca.mcgill.ecse321.tutoringservice321.model.Tutor;
import ca.mcgill.ecse321.tutoringservice321.service.TutoringService321Service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TutorServiceTests {

	@Mock
	private TutorRepository tutorDao;
	
	@InjectMocks
	private TutoringService321Service service;
	
	private static final String TUTOR_EMAIL = "Test@email.com";
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
	}
	
	@Test
	public void testCreateTutor() {
		assertEquals(0, service.getAllTutors().size());
		
		String name = "William";
		String email = "will@gmail.com";
		String phoneNumber = "4185730193";
		String password = "youguysarekillingme";
		int hourlyRate = 15;
		
		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
		} catch(IllegalArgumentException e) {
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
		assertEquals(0, service.getAllTutors().size());
		
		String name = null;
		String email = "will@gmail.com";
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
		assertEquals(0, service.getAllTutors().size());
		
		String name = "";
		String email = "will@gmail.com";
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
		assertEquals(0, service.getAllTutors().size());
		
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
		assertEquals(0, service.getAllTutors().size());
		
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
		assertEquals(0, service.getAllTutors().size());
		
		String name = "William";
		String email = "william@gmail.com";
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
		assertEquals(0, service.getAllTutors().size());
		
		String name = "William";
		String email = "william@gmail.com";
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
		assertEquals(0, service.getAllTutors().size());
		
		String name = "William";
		String email = "william@gmail.com";
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
		assertEquals(0, service.getAllTutors().size());
		
		String name = "William";
		String email = "william@gmail.com";
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
		assertEquals(0, service.getAllTutors().size());
		
		String name = "William";
		String email = "william@gmail.com";
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
		assertEquals(0, service.getAllTutors().size());
		
		Tutor tutor2;
		
		String name = "William";
		String email = "william@gmail.com";
		String phoneNumber = "4185730193";
		String password = "youguysarekillingme";
		int hourlyRate = 15;
		String error = null;
		
		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
			tutor2 = service.createTutor(email, name, password, phoneNumber, hourlyRate);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("A tutor with the same email already exists.", error);
	}
	
	@Test
	public void testCreateTutorWithInvalidEmailWithNoAtSymbol() {
		assertEquals(0, service.getAllTutors().size());
				
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
		assertEquals(0, service.getAllTutors().size());
				
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
		assertEquals(0, service.getAllTutors().size());
				
		String name = "William";
		String email = "invalidEmail.com";
		String phoneNumber = "4185730193";
		String password = "12345";
		int hourlyRate = 15;
		String error = null;
		
		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Password has to be at least 8 character long.", error);
	}
	
	@Test
	public void testCreateTutorWithInvalidPhoneNumber() {
		assertEquals(0, service.getAllTutors().size());
				
		String name = "William";
		String email = "w@email.com";
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
		assertEquals(0, service.getAllTutors().size());
		
		String name = "William";
		String email = "will@gmail.com";
		String phoneNumber = "4185730193";
		String password = "youguysarekillingme";
		int hourlyRate = 15;
		
		String nameNew = "Kyjauna";
		String phoneNumberNew = "2505102578";
		int hourlyRateNew = 20;
		
		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
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
		assertEquals(0, service.getAllTutors().size());
		
		String name = "William";
		String email = "will@gmail.com";
		String phoneNumber = "4185730193";
		String password = "youguysarekillingme";
		int hourlyRate = 15;
		
		String nameNew = "Kyjauna";
		String phoneNumberNew = "2505102578";
		int hourlyRateNew = 20;
		String error = null;
		
		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
			email = null;
			tutor = service.updateTutor(email, nameNew, phoneNumberNew, hourlyRateNew);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("The tutor with that email could not be found.", error);
	}
	
	@Test
	public void testUpdateTutorEmailEmpty() {
		assertEquals(0, service.getAllTutors().size());
		
		String name = "William";
		String email = "will@gmail.com";
		String phoneNumber = "4185730193";
		String password = "youguysarekillingme";
		int hourlyRate = 15;
		
		String nameNew = "Kyjauna";
		String phoneNumberNew = "2505102578";
		int hourlyRateNew = 20;
		String error = null;
		
		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
			email = "";
			tutor = service.updateTutor(email, nameNew, phoneNumberNew, hourlyRateNew);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("The tutor with that email could not be found.", error);
	}
	
	@Test
	public void testUpdateTutorNameNull() {
		assertEquals(0, service.getAllTutors().size());
		
		String name = "William";
		String email = "will@gmail.com";
		String phoneNumber = "4185730193";
		String password = "youguysarekillingme";
		int hourlyRate = 15;
		
		String nameNew = null;
		String phoneNumberNew = "2505102578";
		int hourlyRateNew = 20;
		String error = null;
		
		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
			tutor = service.updateTutor(email, nameNew, phoneNumberNew, hourlyRateNew);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Name cannot be empty.", error);
	}
	
	@Test
	public void testUpdateTutorNameEmpty() {
		assertEquals(0, service.getAllTutors().size());
		
		String name = "William";
		String email = "will@gmail.com";
		String phoneNumber = "4185730193";
		String password = "youguysarekillingme";
		int hourlyRate = 15;
		
		String nameNew = "";
		String phoneNumberNew = "2505102578";
		int hourlyRateNew = 20;
		String error = null;
		
		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
			tutor = service.updateTutor(email, nameNew, phoneNumberNew, hourlyRateNew);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Name cannot be empty.", error);
	}
	
	@Test
	public void testUpdateTutorPhoneNumberNull() {
		assertEquals(0, service.getAllTutors().size());
		
		String name = "William";
		String email = "will@gmail.com";
		String phoneNumber = "4185730193";
		String password = "youguysarekillingme";
		int hourlyRate = 15;
		
		String nameNew = "Kyjauna";
		String phoneNumberNew = null;
		int hourlyRateNew = 20;
		String error = null;
		
		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
			tutor = service.updateTutor(email, nameNew, phoneNumberNew, hourlyRateNew);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Phone number cannot be empty.", error);
	}
	
	@Test
	public void testUpdateTutorPhoneNumberEmpty() {
		assertEquals(0, service.getAllTutors().size());
		
		String name = "William";
		String email = "will@gmail.com";
		String phoneNumber = "4185730193";
		String password = "youguysarekillingme";
		int hourlyRate = 15;
		
		String nameNew = "Kyjauna";
		String phoneNumberNew = "";
		int hourlyRateNew = 20;
		String error = null;
		
		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
			tutor = service.updateTutor(email, nameNew, phoneNumberNew, hourlyRateNew);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Phone number cannot be empty.", error);
	}
	
	@Test
	public void testUpdateTutorHourlyRateNegative() {
		assertEquals(0, service.getAllTutors().size());
		
		String name = "William";
		String email = "will@gmail.com";
		String phoneNumber = "4185730193";
		String password = "youguysarekillingme";
		int hourlyRate = 15;
		
		String nameNew = "Kyjauna";
		String phoneNumberNew = "2505102578";
		int hourlyRateNew = -20;
		String error = null;
		
		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
			tutor = service.updateTutor(email, nameNew, phoneNumberNew, hourlyRateNew);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Hourly has to be a positive number.", error);
	}
	
	@Test
	public void testUpdateTutorWithInvalidPhoneNumber() {
		assertEquals(0, service.getAllTutors().size());
				
		String name = "William";
		String email = "will@gmail.com";
		String phoneNumber = "4185730193";
		String password = "youguysarekillingme";
		int hourlyRate = 15;
		
		String nameNew = "Kyjauna";
		String phoneNumberNew = "2134";
		int hourlyRateNew = 20;
		String error = null;
		
		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
			tutor = service.updateTutor(email, nameNew, phoneNumberNew, hourlyRateNew);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Phone number has to be 10 character long.", error);
	}
	
	@Test
	public void testChangePassword() {
		assertEquals(0, service.getAllTutors().size());
				
		String name = "William";
		String email = "will@gmail.com";
		String phoneNumber = "4185730193";
		String password = "youguysarekillingme";
		int hourlyRate = 15;
		
		String newPassword = "aaaaaaaaaaa";
		
		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
			tutor = service.changePassword(email, password, newPassword);
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(newPassword, tutor.getPassword());
	}
	
	@Test
	public void testChangePasswordNull() {
		assertEquals(0, service.getAllTutors().size());
				
		String name = "William";
		String email = "will@gmail.com";
		String phoneNumber = "4185730193";
		String password = "youguysarekillingme";
		int hourlyRate = 15;
		
		String newPassword = null;
		
		String error = null;
		
		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
			tutor = service.changePassword(email, password, newPassword);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Password cannot be empty.", error);
	}
	
	@Test
	public void testChangePasswordEmpty() {
		assertEquals(0, service.getAllTutors().size());
				
		String name = "William";
		String email = "will@gmail.com";
		String phoneNumber = "4185730193";
		String password = "youguysarekillingme";
		int hourlyRate = 15;
		
		String newPassword = "";
		
		String error = null;
		
		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
			tutor = service.changePassword(email, password, newPassword);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Password cannot be empty.", error);
	}
	
	@Test
	public void testChangePasswordInvalidPassword() {
		assertEquals(0, service.getAllTutors().size());
				
		String name = "William";
		String email = "will@gmail.com";
		String phoneNumber = "4185730193";
		String password = "youguysarekillingme";
		int hourlyRate = 15;
		
		String newPassword = "111111";
		
		String error = null;
		
		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
			tutor = service.changePassword(email, password, newPassword);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Password has to be at least 8 characters long.", error);
	}
	
	@Test
	public void testChangePasswordWrongOldPassword() {
		assertEquals(0, service.getAllTutors().size());
				
		String name = "William";
		String email = "will@gmail.com";
		String phoneNumber = "4185730193";
		String password = "youguysarekillingme";
		int hourlyRate = 15;
		
		String newPassword = "111111";
		
		String error = null;
		
		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
			tutor = service.changePassword(email, "123456789", newPassword);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("That is not the correct password.", error);
	}
}
