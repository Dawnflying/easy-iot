package com.jarvis.easy.data.entity;

import javax.persistence.*;

/**
 * @author lixiaofei
 */
@Entity
@Table(name = "device_meta")
public class DeviceMetaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
