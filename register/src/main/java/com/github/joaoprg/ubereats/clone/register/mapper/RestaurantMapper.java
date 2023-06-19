package com.github.joaoprg.ubereats.clone.register.mapper;

import com.github.joaoprg.ubereats.clone.register.entity.Restaurant;
import com.github.joaoprg.ubereats.clone.register.model.RestaurantCreate;
import com.github.joaoprg.ubereats.clone.register.model.RestaurantRead;
import com.github.joaoprg.ubereats.clone.register.model.RestaurantUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RestaurantMapper {


    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "dishes", ignore = true)
    Restaurant toRestaurant(RestaurantCreate restaurantCreate);

    @Mapping(target = "createdAt", dateFormat = "dd/MM/yyyy HH:mm:ss")
    RestaurantRead toRestaurantRead(Restaurant restaurant);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "dishes", ignore = true)
    void toRestaurant(RestaurantUpdate restaurantUpdate, @MappingTarget Restaurant restaurant);
}
