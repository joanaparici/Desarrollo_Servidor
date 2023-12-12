package com.Proyecto.poyect1.persistence.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.Proyecto.poyect1.DBUtil.DBUtil;
import com.Proyecto.poyect1.bussines.entity.Director;
import com.Proyecto.poyect1.mapper.DirectorMapper;
import com.Proyecto.poyect1.persistence.model.DirectorEntity;

@Component
public class DirectorDAO {
    
    public int insert(Connection connection, DirectorEntity directorEntity) {
        try {   
            final String SQL = "INSERT INTO directors (name, birthYear, deathYear) VALUES (?, ?, ?)";
            List<Object> params = new ArrayList<>();
            params.add(directorEntity.getName());
            params.add(directorEntity.getBirthYear());
            params.add(directorEntity.getDeathYear());
            int id = DBUtil.insert(connection, SQL, params);
            DBUtil.close(connection);
            return id;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public Optional<DirectorEntity> findDirector(int id) {
        final String SQL = "SELECT * FROM directors WHERE id = ?";
        try (Connection connection = DBUtil.open(true)) {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            return Optional.ofNullable(resultSet.next()? DirectorMapper.mapper.toDirectorEntity(resultSet):null);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public Optional<DirectorEntity> findDirectorByMovieId(int id) {
            final String SQL = "SELECT directors.* from directors join movies on directors.id=movies.director_id where movies.id=?";
            try (Connection connection = DBUtil.open(true)) {
                ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
                return Optional.ofNullable(resultSet.next()?DirectorMapper.mapper.toDirectorEntity(resultSet):null);
                
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }

    public void update(DirectorEntity directorEntity) {
            final String SQL = "UPDATE directors SET name = ?, birthYear = ?, deathYear = ? WHERE id = ?";
            try (Connection connection = DBUtil.open(true)){
                List<Object> params = new ArrayList<>();

                params.add(directorEntity.getName());
                params.add(directorEntity.getBirthYear());
                params.add(directorEntity.getDeathYear());
                params.add(directorEntity.getId());

                DBUtil.update(connection, SQL, params);
                DBUtil.close(connection);
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
        public void delete( int id) {
            final String SQL = "DELETE FROM directors WHERE id = ?";
            try (Connection connection = DBUtil.open(true)){
                DBUtil.delete(connection, SQL, List.of(id));
                DBUtil.close(connection);
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
      
}
