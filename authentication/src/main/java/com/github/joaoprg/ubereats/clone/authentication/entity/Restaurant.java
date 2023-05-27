package com.github.joaoprg.ubereats.clone.authentication.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})

@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @Column(name = "restaurant_id", columnDefinition = "uuid")
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @NotBlank(message = "Owner cannot be blank")
    public String owner;

    @NotNull(message = "Name cannot be null")
    @Size(max = 50)
    public String name;

    @OneToOne(
            mappedBy = "restaurant",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @PrimaryKeyJoinColumn
    @Valid
    public Address address;

    @Column(name = "created_at")
    @CreationTimestamp
    public Instant createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    public Instant updatedAt;

    @OneToMany(
            mappedBy="restaurant",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    public Set<@Valid Dish> dishes;
}
