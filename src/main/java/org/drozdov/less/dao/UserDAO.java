package org.drozdov.less.dao;


import org.drozdov.less.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
 List<User> getAll() throws SQLException;
 User getOne(String email);
 void add(User user) throws SQLException;
}