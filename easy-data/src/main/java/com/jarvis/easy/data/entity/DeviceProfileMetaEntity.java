package com.jarvis.easy.data.entity;

import javax.persistence.*;

/**
 * @author lixiaofei
 */
@Entity
@Table(name = "device_profile_meta")
public class DeviceProfileMetaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
