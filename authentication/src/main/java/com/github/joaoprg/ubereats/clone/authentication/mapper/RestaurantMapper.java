package com.github.joaoprg.ubereats.clone.authentication.mapper;

import com.github.joaoprg.ubereats.clone.authentication.entity.Restaurant;
import com.github.joaoprg.ubereats.clone.authentication.model.RestaurantCreate;
import com.github.joaoprg.ubereats.clone.authentication.model.RestaurantRead;
import com.github.joaoprg.ubereats.clone.authentication.model.RestaurantUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RestaurantMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "dishes", ignore = true)
    public Restaurant toRestaurant(RestaurantCreate restaurantCreate);

    @Mapping(target = "createdAt", dateFormat = "dd/MM/yyyy HH:mm:ss")
    public RestaurantRead toRestaurantRead(Restaurant restaurant);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "dishes", ignore = true)
    public void toRestaurant(RestaurantUpdate restaurantUpdate, @MappingTarget Restaurant restaurant);
}
