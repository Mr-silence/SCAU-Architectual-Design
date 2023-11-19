package model;

import java.sql.*;

public class t_sys_user {
    public t_sys_user()
    {

    }

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
}
