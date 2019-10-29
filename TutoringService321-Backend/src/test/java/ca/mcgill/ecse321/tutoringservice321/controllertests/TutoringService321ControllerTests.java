package ca.mcgill.ecse321.tutoringservice321.controllertests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AvailabilityControllerTests.class, CourseControllerTests.class, ReviewControllerTests.class,
		SessionControllerTests.class, SubjectControllerTests.class, TutorControllerTests.class })
public class TutoringService321ControllerTests {

}
