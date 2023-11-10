import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public interface Data_Adapter_Layer {
    boolean Add(t_sys_user user);

    boolean Modify(t_sys_user user);

    boolean Delete(int fid);

    t_sys_user[] Query(String strSQL);
}



