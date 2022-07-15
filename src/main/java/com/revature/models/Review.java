package com.revature.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="reviews")
@Data @AllArgsConstructor @NoArgsConstructor
public class Review {
	
	@Id
	@Column(name="review_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Min(value = 0) @Max(value = 5)
	private int rating;
	
	@Length(max=300)
	private String content;
	
	@Column(name="date_reviewed")
	private Timestamp dateReviewed;
	
	@Column(name="park_code")
	private String parkCode;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;

	public Review(@NotBlank int rating, @Length(max = 300) String content, Timestamp dateReviewed) {
		super();
		this.rating = rating;
		this.content = content;
		this.dateReviewed = dateReviewed;
	}
	
	

}



















