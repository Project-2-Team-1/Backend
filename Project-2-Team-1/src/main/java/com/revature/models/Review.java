package com.revature.models;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="reviews")
@Data @AllArgsConstructor @NoArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Review {
	
	@Id
	@Column(name="review_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	private int rating;
	
	@Length(max=300)
	private String content;
	
	@Column(name="date_reviewed")
	private Timestamp dateReviewed;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="park_id")
	private int park_id;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private int user_id;

	public Review(@NotBlank int rating, @Length(max = 300) String content, Timestamp dateReviewed) {
		super();
		this.rating = rating;
		this.content = content;
		this.dateReviewed = dateReviewed;
	}
	
	

}



















