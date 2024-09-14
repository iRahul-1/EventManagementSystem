package com.eventmanagement.testcases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.eventmanagement.entity.Attendees;
import com.eventmanagement.entity.Event;
import com.eventmanagement.repository.AttendeesRepository;
import com.eventmanagement.repository.EventRepository;
import com.eventmanagement.service.EventService;

@SpringBootTest
public class EventServiceTest {

    @Mock
    private EventRepository eventRepo;

    @Mock
    private AttendeesRepository attendeesRepo;

    @InjectMocks
    private EventService eventService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEvents() {
        // Arrange
        Event event1 = new Event();
        Event event2 = new Event();
        List<Event> events = Arrays.asList(event1, event2);
        when(eventRepo.findAll()).thenReturn(events);

        // Act
        List<Event> result = eventService.getAllEvents();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(eventRepo, times(1)).findAll();
    }

    @Test
    public void testGetEventById() {
        // Arrange
        Event event = new Event();
        when(eventRepo.findById(1)).thenReturn(Optional.of(event));

        // Act
        Optional<Event> result = eventService.getEventById(1);

        // Assert
        assertNotNull(result);
        assertEquals(event, result.get());
        verify(eventRepo, times(1)).findById(1);
    }

    @Test
    public void testCreateEvent() {
        // Arrange
        Event event = new Event();
        when(eventRepo.save(event)).thenReturn(event);

        // Act
        String result = eventService.createEvent(event);

        // Assert
        assertEquals("Event Saved", result);
        verify(eventRepo, times(1)).save(event);
    }

    @Test
    public void testDeleteEvent() {
        // Act
        String result = eventService.deleteEvent(1);

        // Assert
        assertEquals("Event Deleted", result);
        verify(eventRepo, times(1)).deleteById(1);
    }

    @Test
    public void testGetAttendees() {
        // Arrange
        Attendees attendee1 = new Attendees();
        Attendees attendee2 = new Attendees();
        List<Attendees> attendees = Arrays.asList(attendee1, attendee2);
        when(attendeesRepo.findAll()).thenReturn(attendees);

        // Act
        List<Attendees> result = eventService.getAttendees();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(attendeesRepo, times(1)).findAll();
    }

    @Test
    public void testUpdateEvent() {
        // Arrange
        Event event = new Event();
        when(eventRepo.findById(1)).thenReturn(Optional.of(event));

        // Act
        String result = eventService.updateEvent(event);

        // Assert
        assertEquals("Event updated", result);
        verify(eventRepo, times(1)).save(event);
    }

    @Test
    public void testUpdateEventNotFound() {
        // Arrange
        Event event = new Event();
        when(eventRepo.findById(1)).thenReturn(Optional.empty());

        // Act
        String result = eventService.updateEvent(event);

        // Assert
        assertEquals("not found", result);
        verify(eventRepo, never()).save(event);
    }
}

