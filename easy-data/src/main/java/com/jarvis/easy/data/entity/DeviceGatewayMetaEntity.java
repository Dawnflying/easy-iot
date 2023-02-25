package com.jarvis.easy.data.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "device_gateway_meta")
@Data
public class DeviceGatewayMetaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(64)")
    private String name;

    @Column(name = "description", columnDefinition = "VARCHAR(1024)")
    private String description;

    @Column(name = "create_ts", columnDefinition = "timestamp")
    private String createTs;

    @Column(name = "modified_ts", columnDefinition = "timestamp")
    private String modifiedTs;

    @Column(name = "status", columnDefinition = "SMALLINT")
    private int status;

    @Column(name = "edge", columnDefinition = "SMALLINT")
    private int edge;

    @Column(name = "attributes", columnDefinition = "json")
    private String attributes;

    @Column(name = "tenant_id", columnDefinition = "VARCHAR(64)")
    private String tenantId;

}
