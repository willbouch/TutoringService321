package ca.mcgill.ecse321.tutoringservice321.dto;

public class ReviewDto {
	
	private String textualReview;
	private String authorEmail;
	private int reviewID;
	
	public ReviewDto(String textualReview, String authorEmail, int reviewID) {
		this.textualReview = textualReview;
		this.authorEmail = authorEmail;
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
	public int getReviewID() {
		return reviewID;
	}
	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}
}
