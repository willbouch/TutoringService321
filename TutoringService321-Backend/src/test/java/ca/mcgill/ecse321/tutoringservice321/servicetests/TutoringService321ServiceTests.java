package ca.mcgill.ecse321.tutoringservice321.servicetests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AvailabilityServiceTests.class, CourseServiceTests.class, SessionServiceTests.class,
		SubjectServiceTests.class, TutorServiceTests.class, ReviewServiceTests.class })
public class TutoringService321ServiceTests {

}
