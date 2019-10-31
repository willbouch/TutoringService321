package ca.mcgill.ecse321.tutoringservice321.dto;

import java.util.Set;

public class SubjectDto {
	private String subjectName;
	private Set<CourseDto> courses;

	public SubjectDto() {

	}

	public SubjectDto(String subjectName, Set<CourseDto> courses) {
		this.subjectName = subjectName;
		this.courses = courses;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}


	public Set<CourseDto> getCourses() {
		return courses;
	}

	public void setCourses(Set<CourseDto> courses) {
		this.courses = courses;
	}
}
