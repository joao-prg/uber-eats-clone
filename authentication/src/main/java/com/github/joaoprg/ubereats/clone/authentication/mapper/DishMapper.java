package com.github.joaoprg.ubereats.clone.authentication.mapper;

import com.github.joaoprg.ubereats.clone.authentication.entity.Dish;
import com.github.joaoprg.ubereats.clone.authentication.model.DishCreate;
import com.github.joaoprg.ubereats.clone.authentication.model.DishRead;
import com.github.joaoprg.ubereats.clone.authentication.model.DishUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface DishMapper {

    DishRead toDishRead(Dish dish);

    Dish toDish(DishCreate dishCreate);

    void toDish(DishUpdate dishUpdate, @MappingTarget Dish dish);

}
