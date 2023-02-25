package com.jarvis.easy.data.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author lixiaofei
 */
@Entity
@Table(name = "device_meta")
@Data
public class DeviceMetaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", columnDefinition = "VARCHAR(64)")

    private String name;
    @Column(name = "device_profile_id", columnDefinition = "VARCHAR(64)")

    private String deviceProfileId;
    @Column(name = "description", columnDefinition = "VARCHAR(1024)")

    private String description;
    @Column(name = "create_ts", columnDefinition = "timestamp")

    private String createTs;
    @Column(name = "modified_ts", columnDefinition = "timestamp")

    private String modifiedTs;
    @Column(name = "status", columnDefinition = "SMALLINT")
    private int status;

    @Column(name = "attributes", columnDefinition = "json")
    private String attributes;

    @Column(name = "tenant_id", columnDefinition = "VARCHAR(64)")
    private String tenantId;
}
