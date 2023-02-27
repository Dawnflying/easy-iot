package com.jarvis.easy.data.timeseries;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "time_series_data")
@Data
public class TimeSeriesDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "timestamp")
    private Instant timestamp;

    @Column(name = "value", columnDefinition = "jsonb")
    private JsonNode data;

}
