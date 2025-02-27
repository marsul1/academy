package com.ctw.workstation.team.entity;


import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.teammember.entity.TeamMember;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "T_TEAM")
public class Team extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "teamIdGenerator")
    @SequenceGenerator(name = "teamIdGenerator", sequenceName = "SEQ_TEAM_ID")
    public Long id;

    @Column(length = 50)
    public String name;

    @Column(length = 50)
    public String product;

    @Column(name = "modified_at")
    public LocalDate modifiedAt;

    @Column(name = "created_at")
    public LocalDate createdAt;

    @Column(name = "default_location", length = 50)
    public String defaultLocation;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    public List<TeamMember> members;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    public List<Rack> racks;

}
