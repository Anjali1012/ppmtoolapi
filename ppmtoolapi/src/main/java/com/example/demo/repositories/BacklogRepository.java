package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.*;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long> {
	
	Backlog findByProjectIdentifier(String projectIdentifier);

}
