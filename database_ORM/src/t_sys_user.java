import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class t_sys_user {

    public t_sys_user()
    {

    }

    //model
    private int fid;
    private String fNumber;
    private String fLoginName;
    private String fName;
    private String fPassword;
    private String fCollege;
    private String fMajorClass;
    private String fSex;
    private String fPhone;
    private String fPhoneShortNum;
    private String fEmail;
    private String fQQ;
    private String fAddress;
    private String fRemark;
    private Date fLastLoginTime;

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFNumber() {
        return fNumber;
    }

    public void setFNumber(String fNumber) {
        this.fNumber = fNumber;
    }

    public String getFLoginName() {
        return fLoginName;
    }

    public void setFLoginName(String fLoginName) {
        this.fLoginName = fLoginName;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getFPassword() {
        return fPassword;
    }

    public void setFPassword(String fPassword) {
        this.fPassword = fPassword;
    }

    public String getFCollege() {
        return fCollege;
    }

    public void setFCollege(String fCollege) {
        this.fCollege = fCollege;
    }

    public String getFMajorClass() {
        return fMajorClass;
    }

    public void setFMajorClass(String fMajorClass) {
        this.fMajorClass = fMajorClass;
    }

    public String getFSex() {
        return fSex;
    }

    public void setFSex(String fSex) {
        this.fSex = fSex;
    }

    public String getFPhone() {
        return fPhone;
    }

    public void setFPhone(String fPhone) {
        this.fPhone = fPhone;
    }

    public String getFPhoneShortNum() {
        return fPhoneShortNum;
    }

    public void setFPhoneShortNum(String fPhoneShortNum) {
        this.fPhoneShortNum = fPhoneShortNum;
    }

    public String getFEmail() {
        return fEmail;
    }

    public void setFEmail(String fEmail) {
        this.fEmail = fEmail;
    }

    public String getFQQ() {
        return fQQ;
    }

    public void setFQQ(String fQQ) {
        this.fQQ = fQQ;
    }

    public String getFAddress() {
        return fAddress;
    }

    public void setFAddress(String fAddress) {
        this.fAddress = fAddress;
    }

    public String getFRemark() {
        return fRemark;
    }

    public void setFRemark(String fRemark) {
        this.fRemark = fRemark;
    }

    public Date getFLastLoginTime() {
        return fLastLoginTime;
    }

    public void setFLastLoginTime(Date fLastLoginTime) {
        this.fLastLoginTime = fLastLoginTime;
    }

    public static t_sys_user resultSetToModel(ResultSet resultSet) throws SQLException {
        t_sys_user model = new t_sys_user();

        if (resultSet != null) {
            model.setFid(resultSet.getInt("fId"));
            model.setFNumber(resultSet.getString("fNumber"));
            model.setFLoginName(resultSet.getString("fLoginName"));
            model.setFName(resultSet.getString("fName"));
            model.setFPassword(resultSet.getString("fPassword"));
            model.setFCollege(resultSet.getString("fCollege"));
            model.setFMajorClass(resultSet.getString("fMajorClass"));
            model.setFSex(resultSet.getString("fSex"));
            model.setFPhone(resultSet.getString("fPhone"));
            model.setFPhoneShortNum(resultSet.getString("fPhoneShortNum"));
            model.setFEmail(resultSet.getString("fEmail"));
            model.setFQQ(resultSet.getString("fQQ"));
            model.setFAddress(resultSet.getString("fAddress"));
            model.setFRemark(resultSet.getString("fRemark"));
            model.setFLastLoginTime(resultSet.getDate("fLastLoginTime"));
        }

        return model;
    }

    private static final String JDBC_URL = "jdbc:mysql://localhost:3300/ScauCSDB";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    public t_sys_user[] query(String strSQL) {
        List<t_sys_user> userList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT fId, fGuid, fName, fNickName, fPasswordMD5, fEnable, fRole, fGroup, fUr," +
                     " fLastLoginDateTime, fToken, fTokenDateTime, fRemark FROM t_sys_user " + strSQL)) {

            while (resultSet.next()) {
                t_sys_user user = t_sys_user.resultSetToModel(resultSet);
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList.toArray(new t_sys_user[0]);
    }

    public boolean Add(t_sys_user user) {
        String sql = "INSERT INTO t_sys_user " +
                "(fNumber, fLoginName, fName, fPassword, fCollege, fMajorClass, fSex, fPhone, fPhoneShortNum, " +
                "fEmail, fQQ, fAddress, fRemark, fLastLoginTime) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

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
            preparedStatement.setTimestamp(14, new Timestamp(user.getFLastLoginTime().getTime()));

            // 插入
            int rowsAffected = preparedStatement.executeUpdate();

            // 检测是否插入成功
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // 更新
    public boolean Modify(t_sys_user user) {
        String sql = "UPDATE t_sys_user SET " +
                "fGuid=?, fName=?, fNickName=?, fPasswordMD5=?, fEnable=?, fRole=?, fGroup=?, " +
                "fUrl=?, fLastLoginTime=?, fToken=?, fTokenDateTime=?, fRemark=? " +
                "WHERE fId=?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

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
            preparedStatement.setTimestamp(14, new Timestamp(user.getFLastLoginTime().getTime()));

            // 执行更新操作
            int rowsAffected = preparedStatement.executeUpdate();

            // 判断是否更新成功
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    //删除
    public boolean Delete(int fId) {
        String sql = "DELETE FROM t_sys_user WHERE fId=?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, fId);
            // 删除
            int rowsAffected = preparedStatement.executeUpdate();

            // 检测
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
