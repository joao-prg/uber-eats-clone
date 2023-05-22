package com.github.joaoprg.ubereats.clone.authentication.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.Instant;
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
    @GenericGenerator(name = "uuid", strategy = "uuid4")
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @NotBlank(message = "Owner cannot be blank")
    public String owner;

    @NotNull(message = "CNPJ cannot be null")
    public String cnpj;

    @NotNull(message = "Name cannot be null")
    @Size(max = 50)
    public String name;

    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @Valid
    public Address address;

    @Column(name = "created_at")
    @PastOrPresent
    public Instant createdAt;

    @Column(name = "updated_at")
    @PastOrPresent
    public Instant updatedAt;
}
