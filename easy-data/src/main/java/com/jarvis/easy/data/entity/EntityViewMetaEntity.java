package com.jarvis.easy.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "entity_view_meta")
public class EntityViewMetaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
