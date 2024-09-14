package com.eventmanagement.controller;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventmanagement.email.SendEmailToUserImple;
import com.eventmanagement.entity.Attendees;
import com.eventmanagement.entity.Event;
import com.eventmanagement.service.EventService;

@Controller
public class EventController {
    
    @Autowired
    private EventService eventService;
    
    @Autowired
    private SendEmailToUserImple eMail;
    
    @GetMapping
    public String home() {
        return "home";
    }

    @PostMapping("/create")
    public String createEvent(@ModelAttribute Event event,
                              @RequestParam(value = "attendees[0].name", required = false) List<String> names,
                              @RequestParam(value = "attendees[0].email", required = false) List<String> emails,
                              @RequestParam("datetime") LocalDateTime date,
                              Model model) {

        // Create a list of Attendees
        List<Attendees> attendeesList = new ArrayList<>();
        if (names != null && emails != null && names.size() == emails.size()) {
            for (int i = 0; i < names.size(); i++) {
                Attendees attendee = new Attendees();
                attendee.setName(names.get(i));
                attendee.setEmail(emails.get(i));
                attendee.setEvent(event);  // Set the event reference
                attendeesList.add(attendee);
            }
        }

        // Set the list of attendees to the event
        event.setList(attendeesList);
        event.setDateTime(date); // Adjust this if needed

        // Save the event (and its attendees) to the database
        String msg = eventService.createEvent(event);

        // Add a success message and return view
        model.addAttribute("message", "Event created successfully with ID: " + event.getEid());

        // Send emails to all attendees
        try {
            if (msg.equalsIgnoreCase("Event Saved")) {
                for (Attendees attendee : event.getList()) {
                    eMail.mailUser("Event created successfully", attendee.getEmail());
                }
                System.out.println("Mail sent to all attendees");
            } else {
                System.out.println("Event creation failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "home"; // Return the appropriate view name
    }

    @GetMapping("/events")
    public String showEvents(Map<String, Object> map) {
        List<Event> allEvents = eventService.getAllEvents();
        map.put("allEvents", allEvents);
        return "eventpage";
    }
    @GetMapping("/data_delete")
    public String deleteEvent(@RequestParam("id")int id,RedirectAttributes model) {
    	String msg = eventService.deleteEvent(id);
    	model.addFlashAttribute("msg",msg);
    	return "redirect:./"; 
    }
    
    
    @GetMapping("/data_edit")
    public String updateEvent(@RequestParam("id") int id, Model model) {
        Optional<Event> eventById = eventService.getEventById(id);
        if(eventById.isPresent()) {
        	model.addAttribute("event",eventById.get());
        	return"edit";
        }else  return "redirect:/home";
    }
    @PostMapping("/update")
    public String updateEvent(@ModelAttribute Event event, RedirectAttributes redirectAttributes) {
        String msg = eventService.updateEvent(event);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/";
    }
    @GetMapping("/attendeesdata")
     public String getAttendeesData(Model model) {
    	 List<Attendees> attendees = eventService.getAttendees();
    	 model.addAttribute("attendees",attendees);
    	 return"attendees";
     }
    
}
