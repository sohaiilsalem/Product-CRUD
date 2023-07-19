package com.example.irrigation.db.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="plot")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class Plot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plot_name")
    private String plotName;

    @Column(name = "crop_type")
    private String cropType;

    @Column(name = "plot_size")
    private Double plotSize;

    @Column(name="needs_irrigations")
    private boolean needsIrrigation;
    @Column(name="moisture_thresh")
    private Double moistureThresh;
    @Column(name="time_slot")
    private Long timeSlot;
    @Column(name="valve_id")
    private Long valveId;
}
