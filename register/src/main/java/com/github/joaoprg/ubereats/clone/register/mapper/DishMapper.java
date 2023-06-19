package com.github.joaoprg.ubereats.clone.register.mapper;

import com.github.joaoprg.ubereats.clone.register.entity.Dish;
import com.github.joaoprg.ubereats.clone.register.model.DishCreate;
import com.github.joaoprg.ubereats.clone.register.model.DishRead;
import com.github.joaoprg.ubereats.clone.register.model.DishUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DishMapper {

    @Mapping(target = "restaurant", ignore = true)
    Dish toDish(DishCreate dishCreate);

    @Mapping(target = "restaurantId", source = "restaurant.id")
    DishRead toDishRead(Dish dish);

    @Mapping(target = "name", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    void toDish(DishUpdate dishUpdate, @MappingTarget Dish dish);
}
