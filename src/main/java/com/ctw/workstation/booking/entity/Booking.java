package com.ctw.workstation.booking.entity;


import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.rack.entity.RackStatus;
import com.ctw.workstation.rackAsset.entity.RackAsset;
import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.teammember.entity.TeamMember;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="T_BOOKING")
public class Booking extends PanacheEntityBase  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "bookingIdGenerator")
    @SequenceGenerator(name = "bookingIdGenerator", sequenceName = "SEQ_BOOKING_ID")
    public Long id;

    @ManyToOne
    @JoinColumn(name = "rack_id")
    public Rack rack;

    @ManyToOne
    @JoinColumn(name = "requester_id")
    public TeamMember teamMember;

    @Column(name = "modified_at")
    public LocalDate modifiedAt;

    @Column(name = "created_at")
    public LocalDate createdAt;

    @Column(name = "book_from")
    public LocalDate bookFrom;

    @Column(name = "book_to")
    public LocalDate bookTo;
    
}
