package view;

import model.UserService;
import model.t_sys_user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final Connection connection;

    public UserServiceImpl(Connection connection) {
        this.connection = connection;
    }

    public static Connection getConnection(String dbUser, String dbPassword) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3300/mysql";
            return DriverManager.getConnection(url, dbUser, dbPassword);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver not found", e);
        }
    }

    @Override
    public boolean addUser(t_sys_user user) {
        String sql = "INSERT INTO t_sys_user " +
                "(fNumber, fLoginName, fName, fPassword, fCollege, fMajorClass, fSex, fPhone, fPhoneShortNum, " +
                "fEmail, fQQ, fAddress, fRemark, fLastLoginTime) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getFNumber());
            preparedStatement.setString(2, user.getFLoginName());
            preparedStatement.setString(3, user.getFName());
            preparedStatement.setString(4, user.getFPassword());
            preparedStatement.setString(5, user.getFCollege());
            preparedStatement.setString(6, user.getFMajorClass());
            preparedStatement.setString(7, user.getFSex());
            preparedStatement.setString(8, user.getFPhone());
            preparedStatement.setString(9, user.getFPhoneShortNum());
            preparedStatement.setString(10, user.getFEmail());
            preparedStatement.setString(11, user.getFQQ());
            preparedStatement.setString(12, user.getFAddress());
            preparedStatement.setString(13, user.getFRemark());

            if (user.getFLastLoginTime() != null) {
                preparedStatement.setTimestamp(14, new Timestamp(user.getFLastLoginTime().getTime()));
            } else {
                preparedStatement.setNull(14, Types.TIMESTAMP);
            }

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modifyUser(t_sys_user user) {
        String sql = "UPDATE t_sys_user SET " +
                "fNumber=?, fLoginName=?, fName=?, fPassword=?, fCollege=?, fMajorClass=?, fSex=?, " +
                "fPhone=?, fPhoneShortNum=?, fEmail=?, fQQ=?, fAddress=?, fRemark=?, fLastLoginTime=? " +
                "WHERE fId=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getFNumber());
            preparedStatement.setString(2, user.getFLoginName());
            preparedStatement.setString(3, user.getFName());
            preparedStatement.setString(4, user.getFPassword());
            preparedStatement.setString(5, user.getFCollege());
            preparedStatement.setString(6, user.getFMajorClass());
            preparedStatement.setString(7, user.getFSex());
            preparedStatement.setString(8, user.getFPhone());
            preparedStatement.setString(9, user.getFPhoneShortNum());
            preparedStatement.setString(10, user.getFEmail());
            preparedStatement.setString(11, user.getFQQ());
            preparedStatement.setString(12, user.getFAddress());
            preparedStatement.setString(13, user.getFRemark());

            if (user.getFLastLoginTime() != null) {
                preparedStatement.setTimestamp(14, new Timestamp(user.getFLastLoginTime().getTime()));
            } else {
                preparedStatement.setNull(14, Types.TIMESTAMP);
            }

            preparedStatement.setInt(15, user.getFid());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUser(int fid) {
        String sql = "DELETE FROM t_sys_user WHERE fId=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, fid);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public t_sys_user[] queryUsers(String strSQL) throws SQLException {
        List<t_sys_user> userList = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT fId, fNumber, fLoginName, fName, fPassword, fCollege, fMajorClass, fSex, fPhone, fPhoneShortNum, " +
                     "fEmail, fQQ, fAddress, fRemark, fLastLoginTime FROM t_sys_user WHERE " + strSQL)) {

            while (resultSet.next()) {
                t_sys_user user = t_sys_user.resultSetToModel(resultSet);
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList.toArray(new t_sys_user[0]);
    }
}
