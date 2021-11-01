package by.itacademy.javaenterprise.dao;

import java.sql.SQLException;

public interface DAOFactory<T> {

    T create(T t) throws SQLException;

    void update(T t) throws SQLException;

    int delete(T id) throws SQLException;

    T get(T id) throws SQLException;
}
