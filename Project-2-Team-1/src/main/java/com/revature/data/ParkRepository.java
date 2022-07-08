package com.revature.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Park;


@Repository
public interface ParkRepository extends JpaRepository<Park, Integer>{
	
	Optional<Park> findByParkName(String username);

}
