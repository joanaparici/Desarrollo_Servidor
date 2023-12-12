package com.Proyecto.poyect1.persistence.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.Proyecto.poyect1.DBUtil.DBUtil;
import com.Proyecto.poyect1.bussines.entity.Actor;
import com.Proyecto.poyect1.mapper.ActorMapper;
import com.Proyecto.poyect1.persistence.model.ActorEntity;

@Component
public class ActorDAO {
    
    public Optional<ActorEntity> findActor(int id) {
        final String SQL = "SELECT * FROM actors WHERE id = ?";
        try (Connection connection = DBUtil.open(true)) {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            return Optional.ofNullable(resultSet.next()?ActorMapper.mapper.toActorEntity(resultSet):null);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<ActorEntity> findActorByMovieId(int id) {
        final String SQL = "SELECT*from actors join actors_movies on actors.id=actors_movies.actor_id WHERE actors_movies.movie_id=?";
        List<ActorEntity> listActores=new ArrayList<>();
        try (Connection connection = DBUtil.open(true)) {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            while (resultSet.next()) {
                listActores.add(ActorMapper.mapper.toActorEntity(resultSet));
            }
            return listActores;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public int insertActor(ActorEntity actorEntity) {

        final String SQL = "INSERT INTO actors (name, birthYear, deathYear) VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(actorEntity.getName());
        params.add(actorEntity.getBirthYear());
        params.add(actorEntity.getDeathYear());
        try (Connection connection = DBUtil.open(true)) {
            int id=DBUtil.insert(connection, SQL, params);
            DBUtil.close(connection);
            return id;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
    public void update(ActorEntity actorEntity) {
        final String SQL = "UPDATE actors SET name = ?, birthYear = ?, deathYear = ? WHERE id = ?";
        try (Connection connection = DBUtil.open(true)) {
            List<Object> params = new ArrayList<>();
            params.add(actorEntity.getName());
            params.add(actorEntity.getBirthYear());
            params.add(actorEntity.getDeathYear());
            params.add(actorEntity.getId());

            DBUtil.update(connection, SQL, params);
            DBUtil.close(connection);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
