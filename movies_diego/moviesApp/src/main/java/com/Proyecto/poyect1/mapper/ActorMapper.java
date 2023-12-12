package com.Proyecto.poyect1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.Proyecto.poyect1.bussines.entity.Actor;
import com.Proyecto.poyect1.bussines.entity.Director;
import com.Proyecto.poyect1.controller.model.actor.ActorListWeb;
import com.Proyecto.poyect1.controller.model.director.DirectorCreateWeb;
import com.Proyecto.poyect1.controller.model.director.DirectorListWeb;
import com.Proyecto.poyect1.persistence.model.ActorEntity;
import com.Proyecto.poyect1.persistence.model.DirectorEntity;

@Mapper(componentModel = "spring")
public interface ActorMapper {
    ActorMapper mapper=Mappers.getMapper(ActorMapper.class);

    
    ActorListWeb toActorListWeb(Actor actor);

    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "name", expression = "java(resultSet.getString(\"name\"))")
    @Mapping(target = "birthYear", expression = "java(resultSet.getInt(\"birthYear\"))")
    @Mapping(target = "deathYear", expression = "java(resultSet.getInt(\"deathYear\"))")
    ActorEntity toActorEntity(ResultSet resultSet) throws SQLException;
    ActorEntity toActorEntity(Actor actor);
    Actor toActor(ActorEntity actorEntity);
} 

