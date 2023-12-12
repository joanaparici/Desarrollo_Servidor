package com.Proyecto.poyect1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.Proyecto.poyect1.bussines.entity.Director;
import com.Proyecto.poyect1.controller.model.director.DirectorCreateWeb;
import com.Proyecto.poyect1.controller.model.director.DirectorDetailWeb;
import com.Proyecto.poyect1.controller.model.director.DirectorListWeb;
import com.Proyecto.poyect1.controller.model.director.DirectorUpdateWeb;
import com.Proyecto.poyect1.persistence.model.DirectorEntity;

@Mapper(componentModel = "spring")
public interface DirectorMapper {

    DirectorMapper mapper = Mappers.getMapper(DirectorMapper.class);
    
    Director toDirector(DirectorCreateWeb directorCreateWeb);//EN EL INSERT
    Director toDirector(DirectorUpdateWeb directorUpdateWeb);//EN EL UPDATE
    DirectorDetailWeb toDirectorDetailWeb(Director director);//CUANDO BUSCAMOS UN DIRECTOR
    
    DirectorListWeb toDirectorListWeb(Director director);

    DirectorEntity toDirectorEntity(Director director);

    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "name", expression = "java(resultSet.getString(\"name\"))")
    @Mapping(target = "birthYear", expression = "java(resultSet.getInt(\"birthYear\"))")
    @Mapping(target = "deathYear", expression = "java(resultSet.getInt(\"deathYear\"))")
    DirectorEntity toDirectorEntity(ResultSet resultSet) throws SQLException;
    

    Director toDirector(DirectorEntity DirectorEntity);//PARA QUE SIRVE ESTE??
}
