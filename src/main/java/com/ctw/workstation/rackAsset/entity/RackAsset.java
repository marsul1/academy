package com.ctw.workstation.rackAsset.entity;

import com.ctw.workstation.rack.entity.Rack;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "R_RACK_ASSET")
public class RackAsset extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "rackAssetIdGenerator")
    @SequenceGenerator(name = "rackAssetIdGenerator", sequenceName = "SEQ_RACK_ASSET_ID")
    public Long id;

    @Column (name = "asset_tag")
    public String assetTag;

    @ManyToOne
    public Rack rack;

}
