package com.ctw.workstation.rack.entity;


import com.ctw.workstation.booking.entity.Booking;
import com.ctw.workstation.rackAsset.entity.RackAsset;
import com.ctw.workstation.team.entity.Team;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.ws.rs.Path;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


/*
create table T_RACK (
                        id uuid DEFAULT gen_random_uuid() primary key ,
                        serial_number varchar(20) not null,
                        team_id uuid not null references T_TEAM(id),
                        status varchar(20) check( status in ( 'AVAILABLE', 'BOOKED','UNAVAILABLE') ),
                        created_at timestamp,
                        modified_at timestamp,
                        default_location varchar (50)
);

 */
@Entity
@Table(name = "T_RACK")
public class Rack extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rackIdGenerator")
    @SequenceGenerator(name = "rackIdGenerator", sequenceName = "SEQ_RACK_ID")
    public Long id;

    @Column(name = "serial_number")
    public String serialNumber;

    @Column(columnDefinition = "rack_status_enum")
    @Enumerated(EnumType.STRING)
    public RackStatus status;

    @Column(name = "modified_at")
    public LocalDate modifiedAt;

    @Column(name = "created_at")
    public LocalDate createdAt;

    @Column(name = "default_location", length = 50)
    public String defaultLocation;

    @ManyToOne
    @JoinColumn(name = "team_id")
    public Team team;

    @OneToMany(mappedBy = "rack", fetch = FetchType.LAZY)
    public List<RackAsset> assets;

    @OneToMany(mappedBy = "rack", fetch = FetchType.LAZY)
    public List<Booking> bookings;

    public static List<Rack> findByTeamId(long teamId) {
        return find("team.id", teamId).list();
    }
}