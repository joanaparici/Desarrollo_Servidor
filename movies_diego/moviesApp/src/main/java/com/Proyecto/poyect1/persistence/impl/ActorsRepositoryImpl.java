package com.Proyecto.poyect1.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Proyecto.poyect1.DBUtil.DBUtil;
import com.Proyecto.poyect1.bussines.entity.Actor;
import com.Proyecto.poyect1.bussines.entity.Director;
import com.Proyecto.poyect1.bussines.service.ActorsRepository;
import com.Proyecto.poyect1.mapper.ActorMapper;
import com.Proyecto.poyect1.mapper.DirectorMapper;
import com.Proyecto.poyect1.persistence.DAO.ActorDAO;
import com.Proyecto.poyect1.persistence.model.ActorEntity;

@Repository
public class ActorsRepositoryImpl implements ActorsRepository {

    @Autowired
    ActorDAO actorDAO;
    @Override
    public Optional<Actor> findActor(int id) {
        try (Connection connection = DBUtil.open(true)) {
            Optional<ActorEntity>actorEntity=actorDAO.findActor(id); 
            if (actorEntity.isEmpty()) {
                return Optional.empty();
            } 
            return Optional.of(ActorMapper.mapper.toActor(actorEntity.get()));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Actor> findActorByMovieId(int id) {
        try(Connection connection=DBUtil.open(true)) {
            List<ActorEntity>actorEntities=actorDAO.findActorByMovieId(id);

            List<Actor>actorList= actorEntities.stream()
                    .map(actorEntity->ActorMapper.mapper.toActor(actorEntity))
                    .toList();

            return actorList;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void update(Actor actor) {
        try (Connection connection = DBUtil.open(true)) {
            actorDAO.update(ActorMapper.mapper.toActorEntity(actor));
            DBUtil.close(connection);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    public int insertActor(Actor actor) {
        try (Connection connection = DBUtil.open(true)) {
            int id=actorDAO.insertActor(ActorMapper.mapper.toActorEntity(actor));
            DBUtil.close(connection);
            return  id;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
