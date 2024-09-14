package com.eventmanagement.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Id;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Events_db")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Event {
	@Id  
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq")
    @SequenceGenerator(name = "event_seq", sequenceName = "event_seq", allocationSize = 1)
    private int eid;
	@NonNull
    private String title;
	@NonNull
    private String description;
	@NonNull
    private String location;
	@NonNull
    private LocalDateTime dateTime;
	@OneToMany(targetEntity = Attendees.class,cascade = CascadeType.ALL,mappedBy = "event")
	private List<Attendees> list;
	@Override
	public String toString() {
		return "Event [id=" + eid + ", title=" + title + ", description=" + description + ", location=" + location
				+ ", dateTime=" + dateTime + "]";
	}
	
	
}
