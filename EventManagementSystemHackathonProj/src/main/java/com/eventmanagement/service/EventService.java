package com.eventmanagement.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.eventmanagement.email.SendEmailToUserImple;
import com.eventmanagement.entity.Attendees;
import com.eventmanagement.entity.Event;
import com.eventmanagement.repository.AttendeesRepository;
import com.eventmanagement.repository.EventRepository;

@Service
public class EventService implements IEventService{
	@Autowired
	private EventRepository eventRepo;
	@Autowired
	private AttendeesRepository attnedeesRepo;
	@Autowired
    private SendEmailToUserImple eMail;
	
	
	@Scheduled(cron = "0 0 9 * * ?") // Runs every day at 9 AM
    public void sendEventNotifications() throws Exception {
        List<Event> upcomingEvents = eventRepo.findAll().stream()
                .filter(event -> event.getDateTime().isAfter(LocalDateTime.now()) &&
                                 event.getDateTime().isBefore(LocalDateTime.now().plusDays(1)))
                .collect(Collectors.toList());

        for (Event event : upcomingEvents) {
            List<Attendees> rsvps = event.getList();
            for (Attendees rsvp : rsvps) {
            	eMail.mailUser("Reminder: "+rsvp.getEvent().getDescription(), rsvp.getEmail()); 
            }
        }
    }
	
	@Override // it helps to get all the events
	public List<Event> getAllEvents() {
		List<Event> AllEvent = eventRepo.findAll();
		return AllEvent;
	}

	@Override
	public Optional<Event> getEventById(int id) {
		Optional<Event> event = eventRepo.findById(id);
		return event;
	}

	@Override
	public String createEvent(Event event) {
		 eventRepo.save(event);
		return "Event Saved";
	}

	@Override
	public String deleteEvent(int id) {
		eventRepo.deleteById(id);
		return "Event Deleted";
	}

	@Override
	public List<Attendees> getAttendees() {
		List<Attendees> allAttendees = attnedeesRepo.findAll();
		return allAttendees; 
	}
	@Override
	public String updateEvent(Event event) {
		Optional<Event> findById = eventRepo.findById(event.getEid());
		if(findById!=null) {
			eventRepo.save(event);
		}else {
			return "not found";
		}
		return "Event updated";
	}
}
