package com.eventmanagement.entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "Attendess_db")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Attendees {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "atttendees_seq")
    @SequenceGenerator(name = "atttendees_seq", sequenceName = "atttendees_seq", allocationSize = 1)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String email;
    
    private boolean rsvp;

    @ManyToOne(targetEntity = Event.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "Event_id",referencedColumnName = "eid")
    private Event event;
}
