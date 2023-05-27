package com.github.joaoprg.ubereats.clone.authentication.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})

@Entity
@Table(name = "dish")
@IdClass(DishKey.class)
public class Dish {

    @Id
    @Column(name = "dish_id", columnDefinition = "uuid")
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @Id
    @Column(name = "restaurant_id")
    private UUID restaurantId;

    @NotNull(message = "Name cannot be null")
    @Size(max = 50)
    public String name;

    @Size(max = 200)
    public String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("restaurantId")
    @JoinColumn(name = "restaurant_id", nullable=false)
    @Valid
    public Restaurant restaurant;

    @PositiveOrZero
    public Double price;
}