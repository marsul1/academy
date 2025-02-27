package com.ctw.workstation.teammember.entity;
import com.ctw.workstation.booking.entity.Booking;
import com.ctw.workstation.team.entity.Team;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "T_TEAM_MEMBER")
public class TeamMember extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teamMemberIdGenerator")
    @SequenceGenerator(name = "teamMemberIdGenerator", sequenceName = "SEQ_TEAM_MEMBER_ID")
    public Long id;

    @Column(name = "ctw_id", length = 20, nullable = false)
    public String ctwId;

    @Column(length = 50, nullable = false)
    public String name;

    @Column(name = "modified_at")
    public LocalDate modifiedAt;

    @Column(name = "created_at")
    public LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    public Team team;

    @OneToMany
    public List<Booking> bookings;
}

