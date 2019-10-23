package ca.mcgill.ecse321.tutoringservice321.dto;

import java.util.Set;

public class CourseDto {
	private String description;
	private String school;
	private String courseCode;
	private Set<SubjectDto> subjects;
	
	public CourseDto() {
		
	}
	
	public CourseDto(String description, String school, String courseCode, Set<SubjectDto> subjects) {
		this.description = description;
		this.school = school;
		this.courseCode = courseCode;
		this.subjects = subjects;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getSchool() {
		return school;
	}
	
	public void setSchool(String school) {
		this.school = school;
	}
	
	public String getCourseCode() {
		return courseCode;
	}
	
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	
	public Set<SubjectDto> getSubject() {
		return subjects;
	}
	
	public void setSubject(Set<SubjectDto> subjects) {
		this.subjects = subjects;
	}
}
