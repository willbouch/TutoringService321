package ca.mcgill.ecse321.tutoringservice321.dto;

public class ReviewDto {
	
	private String textualReview;
	private String authorEmail;
	private SessionDto session;
	private int reviewID;
	
	public ReviewDto(String textualReview, String authorEmail, SessionDto session, int reviewID) {
		this.textualReview = textualReview;
		this.authorEmail = authorEmail;
		this.session = session;
		this.reviewID = reviewID;
	}
	
	public String getTextualReview() {
		return textualReview;
	}
	public void setTextualReview(String textualReview) {
		this.textualReview = textualReview;
	}
	public String getAuthorEmail() {
		return authorEmail;
	}
	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}
	public SessionDto getSession() {
		return session;
	}
	public void setSession(SessionDto session) {
		this.session = session;
	}
	public int getReviewID() {
		return reviewID;
	}
	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}
}
