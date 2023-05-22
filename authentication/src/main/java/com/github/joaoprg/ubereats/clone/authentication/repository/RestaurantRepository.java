package com.github.joaoprg.ubereats.clone.authentication.repository;

import com.github.joaoprg.ubereats.clone.authentication.entity.Restaurant;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class RestaurantRepository implements PanacheRepositoryBase<Restaurant, UUID> { }
