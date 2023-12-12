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
import com.Proyecto.poyect1.bussines.entity.Director;
import com.Proyecto.poyect1.bussines.service.DirectorRepository;
import com.Proyecto.poyect1.mapper.DirectorMapper;
import com.Proyecto.poyect1.persistence.DAO.DirectorDAO;
import com.Proyecto.poyect1.persistence.model.DirectorEntity;

@Repository
public class DirectorRepositoryImpl implements DirectorRepository {

    @Autowired
    DirectorDAO directorDAO;

    public Optional<Director> findDirector(int id) {
        try (Connection connection = DBUtil.open(true)) {
            Optional<DirectorEntity>directorEntity=directorDAO.findDirector(id);
            if (directorEntity.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(DirectorMapper.mapper.toDirector(directorEntity.get()));            
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public Optional<Director> findDirectorByMovieId(int id) {
        try (Connection connection = DBUtil.open(true)) {
            Optional<DirectorEntity>directorEntity=directorDAO.findDirectorByMovieId(id);
            if (directorEntity.isEmpty()) {
                return Optional.empty();
            }
        return Optional.of(DirectorMapper.mapper.toDirector(directorEntity.get()));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    public int insert(Director director) {
        DirectorEntity directorEntity = DirectorMapper.mapper.toDirectorEntity(director);
        Connection connection = DBUtil.open(true);
        int id = directorDAO.insert(connection, directorEntity);
        return id;
    }

    @Override  
        public void update(Director director) {
            try (Connection connection = DBUtil.open(true)){
                directorDAO.update(DirectorMapper.mapper.toDirectorEntity(director));
                DBUtil.close(connection);
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }

    public void delete(int id) {
        //final String SQL = "DELETE FROM directors WHERE id = ?";
        try (Connection connection = DBUtil.open(true)){
            //DBUtil.delete(connection, SQL, List.of(id));
            directorDAO.delete(id);
            DBUtil.close(connection);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    }


