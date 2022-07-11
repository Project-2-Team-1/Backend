package com.revature.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="parks")
@Data @AllArgsConstructor @NoArgsConstructor
public class Park {
	
	@Id
	@Column(name="park_id")
	private int id;
	
	private String parkName;
	
	@OneToMany(mappedBy="park_id")
	private int review_id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private int user_favorite_id;
	

}
