package com.github.joaoprg.ubereats.clone.authentication.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})

@Entity
@Table(name = "address", uniqueConstraints = { @UniqueConstraint(columnNames = { "latitude", "longitude" }) })
public class Address {

    @Id
    @Column(name = "restaurant_id")
    private UUID id;

    @NotNull(message = "Latitude cannot be null")
    public Double latitude;

    @NotNull(message = "Longitude cannot be null")
    public Double longitude;

    @OneToOne
    @MapsId
    @JoinColumn(name = "restaurant_id", nullable = false)
    public Restaurant restaurant;
}