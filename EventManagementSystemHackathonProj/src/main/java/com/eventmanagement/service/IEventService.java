package com.eventmanagement.service;

import java.util.List;
import java.util.Optional;

import com.eventmanagement.entity.Attendees;
import com.eventmanagement.entity.Event;

public interface IEventService {
	
	 public List<Event> getAllEvents();
	 public Optional<Event> getEventById(int id);
	 public String createEvent(Event event);
	 public String deleteEvent(int id);
	 public String updateEvent(Event event);
	 public List<Attendees> getAttendees();
	 
}
