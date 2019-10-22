package ca.mcgill.ecse321.tutoringservice321.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import ca.mcgill.ecse321.tutoringservice321.service.TutoringService321Service;

@CrossOrigin(origins = "*")
@RestController
public class TutoringService321RestController {
	
	@Autowired
	private TutoringService321Service service;

}
