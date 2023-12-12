package com.Proyecto.poyect1.persistence.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.Proyecto.poyect1.DBUtil.DBUtil;
import com.Proyecto.poyect1.bussines.entity.Movie;
import com.Proyecto.poyect1.mapper.MovieMapper;
import com.Proyecto.poyect1.persistence.model.MovieEntity;

@Component
public class MovieDAO {
    public void addActor(Connection connection, int movieId, int actorId) {
        final String SQL = "INSERT INTO actors_movies (actor_id, movie_id) VALUES (?, ?)";
        DBUtil.insert(connection, SQL, List.of(actorId, movieId));
    }

    public int insert(Connection connection, MovieEntity movieEntity) throws SQLException {
        try {
            final String SQL = "INSERT INTO movies (title, year, runtime, director_id) VALUES (?, ?, ?, ?)";
            List<Object> params = new ArrayList<>();
            params.add(movieEntity.getTitle());
            params.add(movieEntity.getYear());
            params.add(movieEntity.getRuntime());
            params.add(movieEntity.getDirectorId());
            int id = DBUtil.insert(connection, SQL, params);
            //insertar los actores
            movieEntity.getActorIds().stream()
                    .forEach(actorId -> addActor(connection, id, actorId));

            connection.commit();
            return id;
        } catch (Exception e) {
            connection.rollback();
            throw new RuntimeException(e);
        }
    }

    public List<MovieEntity> findMovies(Integer page, Integer LIMIT) {
        try (Connection conn = DBUtil.open(true)) {
            String sql = "SELECT * FROM movies";

            int offset = (page - 1) * LIMIT;
            sql += String.format(" LIMIT %d, %d", offset, LIMIT);

            ResultSet resultSet = DBUtil.select(conn, sql, null);
            List<MovieEntity> moviesEntities = new ArrayList<>();

            while (resultSet.next()) {
                moviesEntities.add(MovieMapper.mapper.toMovieEntity(resultSet));

            }
            return moviesEntities;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Optional<MovieEntity> findMoviesById(int id) {
        final String SQL = "SELECT * FROM movies WHERE id = ?";
        try (Connection connection = DBUtil.open(true)) {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            return Optional.ofNullable(resultSet.next() ? MovieMapper.mapper.toMovieEntity(resultSet) : null);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public int getTotalNumberOfRecords(Connection connection) {
        final String SQL = "SELECT COUNT(*) FROM movies";
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException("SQL: " + SQL);
        }
    }

    public void delete(int id) {
        try (Connection connectio = DBUtil.open(true)) {
            String sql = """

                    DELETE from movies where id=?
                    """;

            DBUtil.delete(connectio, sql, List.of(id));
        } catch (Exception e) {
            System.out.println("NO VA");
        }
    }
}
