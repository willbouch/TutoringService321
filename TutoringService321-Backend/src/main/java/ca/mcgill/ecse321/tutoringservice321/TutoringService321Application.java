package ca.mcgill.ecse321.tutoringservice321;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutoringservice321.model.ServiceUser;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
public class TutoringService321Application {

	private static ServiceUser loggedUser = null;
	
	public static void main(String[] args) {
		SpringApplication.run(TutoringService321Application.class, args);
	}

	@RequestMapping("/")
	public String greeting() {
		return "Hello";
	}

	public static ServiceUser getLoggedUser() {
		return loggedUser;
	}

	public static void setLoggedUser(ServiceUser aLoggedUser) {
		loggedUser = aLoggedUser;
	}
}
