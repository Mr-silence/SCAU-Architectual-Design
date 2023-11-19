package model;

import model.t_sys_user;

import java.sql.*;

public interface UserService {
    boolean addUser(t_sys_user user);
    boolean modifyUser(t_sys_user user);
    boolean deleteUser(int fid);
    t_sys_user[] queryUsers(String strSQL) throws SQLException;
}

