package br.com.coffemanager.data;

import java.sql.SQLException;
import java.util.List;

// Generic DAO Interface
public interface GenericDAO<T, TID> {
    void save(T t) throws SQLException;
    T findById(TID id) throws SQLException;
    List<T> findAll() throws SQLException;
    void update(T t) throws SQLException;
    void delete(TID id) throws SQLException;
}