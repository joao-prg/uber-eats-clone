package com.github.joaoprg.ubereats.clone.register.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
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

    @NotNull(message = "Name cannot be null")
    @Size(max = 50)
    public String name;

    @Size(max = 200)
    public String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @MapsId("restaurantId")
    @JoinColumn(name = "restaurant_id", nullable = false)
    @Valid
    public Restaurant restaurant;

    @PositiveOrZero
    public Double price;

    @Id
    @Column(name = "dish_id", columnDefinition = "uuid")
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @Id
    @Column(name = "restaurant_id")
    private UUID restaurantId;
}