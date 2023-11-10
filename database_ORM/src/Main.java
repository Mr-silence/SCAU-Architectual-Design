



public abstract class Main  {

    public static void main(String[] args) {

        t_sys_user dataAdapter = new t_sys_user();

        // 添加
        t_sys_user userToAdd = new t_sys_user();
        // 设置 userToAdd 对象的属性值
        boolean addSuccess = dataAdapter.Add(userToAdd);
        if (addSuccess) {
            System.out.println("成功添加一条记录！");
        } else {
            System.out.println("添加记录失败！");
        }

        // 修改
        t_sys_user userToUpdate = new t_sys_user();
        boolean updateSuccess = dataAdapter.Modify(userToUpdate);
        if (updateSuccess) {
            System.out.println("成功修改记录！");
        } else {
            System.out.println("修改记录失败！");
        }

        // 删除
        int fIdToDelete = 1;
        boolean deleteSuccess = dataAdapter.Delete(fIdToDelete);
        if (deleteSuccess) {
            System.out.println("成功删除记录！");
        } else {
            System.out.println("删除记录失败！");
        }

        // 查询
        String strSQL = "SELECT * FROM t_sys_user WHERE fCollege = 'Engineering'";
        t_sys_user[] queryResult = dataAdapter.query(strSQL);
        //结果
        for (t_sys_user user : queryResult) {
            System.out.println("ID: " + user.getFid() + ", Name: " + user.getFName() + ", College: " + user.getFCollege());
        }


    }
}
