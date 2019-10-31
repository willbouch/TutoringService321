package ca.mcgill.ecse321.tutoringservice321.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review {
	private String textualReview;
	private String authorEmail;
	private Session session;
	private float rating;
	private int reviewID;
	
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
	@ManyToOne(optional=false)
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	@Id
	public int getReviewID() {
		return reviewID;
	}
	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	
	
}
