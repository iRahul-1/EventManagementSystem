package com.eventmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventmanagement.entity.Attendees;

public interface AttendeesRepository extends JpaRepository<Attendees, Integer> {

}
