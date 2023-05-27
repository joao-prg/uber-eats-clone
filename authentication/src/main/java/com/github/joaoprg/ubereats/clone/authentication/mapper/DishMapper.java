package com.github.joaoprg.ubereats.clone.authentication.mapper;

import com.github.joaoprg.ubereats.clone.authentication.entity.Dish;
import com.github.joaoprg.ubereats.clone.authentication.model.DishCreate;
import com.github.joaoprg.ubereats.clone.authentication.model.DishRead;
import com.github.joaoprg.ubereats.clone.authentication.model.DishUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DishMapper {

    @Mapping(target = "restaurant", ignore = true)
    Dish toDish(DishCreate dishCreate);

    DishRead toDishRead(Dish dish);

    @Mapping(target = "name", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    void toDish(DishUpdate dishUpdate, @MappingTarget Dish dish);
}
