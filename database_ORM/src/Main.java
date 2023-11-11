import java.sql.Date;

public abstract class Main  {

    public static void main(String[] args) {

        Data_Adapter_Layer dataAdapter = new t_sys_user();

// 添加
        t_sys_user userToAdd = new t_sys_user();
        userToAdd.setFNumber("1001");
        userToAdd.setFLoginName("john_doe");
        userToAdd.setFName("John");
        userToAdd.setFPassword("password123");
        userToAdd.setFCollege("Engineering");
        userToAdd.setFMajorClass("Computer Science");
        userToAdd.setFSex("Ma");
        userToAdd.setFPhone("123-456-7890");
        userToAdd.setFEmail("john.doe@example.com");
        userToAdd.setFRemark("Sample user");
        userToAdd.setFLastLoginTime(new Date(System.currentTimeMillis()));

        boolean addSuccess = dataAdapter.Add(userToAdd);
        System.out.println("Add success: " + addSuccess);

        // 修改
        t_sys_user userToUpdate = new t_sys_user();
        userToUpdate.setFid(1); // Specify the user ID to update
        userToUpdate.setFNumber("1002");
        userToUpdate.setFLoginName("updated_john_doe");
        userToUpdate.setFName("jey");
        userToUpdate.setFPassword("newpassword456");
        userToUpdate.setFCollege("Engineering");
        userToUpdate.setFMajorClass("Finance");
        userToUpdate.setFSex("Fe");
        userToUpdate.setFPhone("987-654-3210");
        userToUpdate.setFEmail("updated_john.doe@example.com");
        userToUpdate.setFRemark("Updated user");
        userToUpdate.setFLastLoginTime(new Date(System.currentTimeMillis()));

        boolean updateSuccess = dataAdapter.Modify(userToUpdate);
        System.out.println("Update success: " + updateSuccess);


        // 删除
        int fIdToDelete = 2;
        boolean deleteSuccess = dataAdapter.Delete(fIdToDelete);


        // 查询
        String strSQL = "fCollege = 'Engineering'";
        t_sys_user[] queryResult = dataAdapter.Query(strSQL);
        for (t_sys_user user : queryResult) {
            System.out.println("User ID: " + user.getFid());
            System.out.println("Number: " + user.getFNumber());
            System.out.println("Login Name: " + user.getFLoginName());
            System.out.println("Name: " + user.getFName());
            System.out.println("Password: " + user.getFPassword());
            System.out.println("College: " + user.getFCollege());
            System.out.println("Major Class: " + user.getFMajorClass());
            System.out.println("Sex: " + user.getFSex());
            System.out.println("Phone: " + user.getFPhone());
            System.out.println("Phone Short Num: " + user.getFPhoneShortNum());
            System.out.println("Email: " + user.getFEmail());
            System.out.println("QQ: " + user.getFQQ());
            System.out.println("Address: " + user.getFAddress());
            System.out.println("Remark: " + user.getFRemark());
            System.out.println("Last Login Time: " + user.getFLastLoginTime());
            System.out.println("--------------------------------------");
        }


    }
}