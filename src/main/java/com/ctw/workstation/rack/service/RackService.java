package com.ctw.workstation.rack.service;


import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.rack.entity.RackDtoIn;
import com.ctw.workstation.rack.entity.RackDtoOut;
import com.ctw.workstation.rack.entity.RackStatus;
import com.ctw.workstation.rack.repository.RackRepository;
import com.ctw.workstation.team.entity.Team;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RackService {

    @Inject
    RackRepository rackRepository;
    @Transactional
    public RackDtoOut addRack(RackDtoIn rackDtoIn) {
        Log.info("Creating new rack");

        RackStatus rackStatus;
        if (rackDtoIn.status() != null)  {
            rackStatus =  rackDtoIn.status();
        } else {
            rackStatus =  RackStatus.ACTIVE;
        }
        Team team = Team.findById(rackDtoIn.teamId());
        if (team == null) {
            throw new IllegalArgumentException("Team with id " + rackDtoIn.teamId() + " not found");
        }
        Rack rack = new Rack();
        rack.serialNumber = rackDtoIn.serialNumber();
        rack.team = team;
        rack.status = rackStatus;
        rack.defaultLocation = rackDtoIn.defaultLocation();
        rack.createdAt = LocalDate.now();
        rackRepository.persistAndFlush(rack);
        return new RackDtoOut(
                rack.id,
                rack.serialNumber,
                rack.status,
                rack.team.id,
                rack.defaultLocation,
                rack.createdAt,
                rack.modifiedAt
        );
    }
    @Transactional
    public RackDtoOut findRackById(long id) {
        Rack rack = rackRepository.findById(id);
        if (rack == null) {
            throw new IllegalArgumentException("Rack with id " + id + " not found");
        }
        return new RackDtoOut(
                rack.id,
                rack.serialNumber,
                rack.status,
                rack.team.id,
                rack.defaultLocation,
                rack.createdAt,
                rack.modifiedAt
        );
    }
    private List<RackDtoOut> findAllRacks() {
        List<Rack> racks = new ArrayList<Rack>(rackRepository.listAll());
        return racks.stream()
                .map(rack -> new RackDtoOut(
                        rack.id,
                        rack.serialNumber,
                        rack.status,
                        rack.team.id,
                        rack.defaultLocation,
                        rack.createdAt,
                        rack.modifiedAt
                )).toList();
    }

    @Transactional
    public List<RackDtoOut> findAllRacksByStatus(String status) {
        if (status == null) {
            return findAllRacks();
        }
        RackStatus rackStatus = RackStatus.valueOf(status);
        List<Rack> racks = new ArrayList<Rack>(rackRepository.listAll());
        return racks.stream()
                .filter(rack -> rack.status == rackStatus)
                .map(rack -> new RackDtoOut(
                rack.id,
                rack.serialNumber,
                rack.status,
                rack.team.id,
                rack.defaultLocation,
                rack.createdAt,
                rack.modifiedAt
        )).toList();
    }

    private List<RackDtoOut> parseRackDtoList(List<Rack> racks) {
        return racks.stream().map(rack -> new RackDtoOut(
                rack.id,
                rack.serialNumber,
                rack.status,
                rack.team.id,
                rack.defaultLocation,
                rack.createdAt,
                rack.modifiedAt
        )).toList();
    }

    @Transactional
    public boolean deleteRack(long id) {
        return rackRepository.deleteById(id);
    }

    @Transactional
    public RackDtoOut updateRack(long id, RackDtoIn rackDtoIn) {
        Rack rack  = rackRepository.findById(id);
        if (rack == null) {
            throw new IllegalArgumentException("Rack with id " + id + "not found");
        }
        Team team = Team.findById(rackDtoIn.teamId());
        if (team == null) {
            throw new IllegalArgumentException("Team with id " + id + "not found");
        }
        rack.serialNumber = rackDtoIn.serialNumber();
        rack.status = rackDtoIn.status();
        rack.defaultLocation = rackDtoIn.defaultLocation();
        rack.modifiedAt = LocalDate.now();
        rack.team = team;
        rackRepository.persistAndFlush(rack);
        return new RackDtoOut(
                rack.id,
                rack.serialNumber,
                rack.status,
                rack.team.id,
                rack.defaultLocation,
                rack.createdAt,
                rack.modifiedAt
        );
    }

}