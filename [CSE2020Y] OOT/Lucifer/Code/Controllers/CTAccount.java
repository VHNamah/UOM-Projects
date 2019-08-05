package Controllers;

import Helpers.SQLServer;
import Models.Account;
import Models.Employee;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Vidush H. Namah on 11 Mar 2016.
 **/

public class CTAccount {

    private static Connection C = SQLServer.getConnect();

    public static int validateLogin(String ID, String Password) throws SQLException {
        String SQL = "EXEC SP_LOGIN '"+ID+"', '"+Password+"'";

        Statement S = C.createStatement();
        ResultSet R = S.executeQuery(SQL);

        if (R.next()) {
            int T=R.getInt("Permission");

            R.close();
            S.close();

            return T;
        }


        R.close();
        S.close();

        return -1;
    }

    public static void fetchPermissions() throws SQLException {
        for (Employee E : CTEmployee.LISTEmployee) {
            String SQL = "SELECT Permission FROM TBLACCOUNT WHERE Permission>0 AND EmployeeID='" + E.getID() + "'";
            Statement S = C.createStatement();
            ResultSet R = S.executeQuery(SQL);
            if (R.next())
                E.setEAccount(new Account(R.getInt("Permission")));

            R.close();
            S.close();
        }
    }

    public static void insertAccount(String ID, String Password, int Permission) throws SQLException {
        String SQL="INSERT INTO TBLACCOUNT VALUES(";
        SQL+="'"+ID+"', ";
        SQL+="HASHBYTES('SHA2_512', '"+Password+"'), ";
        SQL+=Permission+");";

        Statement S = C.createStatement();
        S.execute(SQL);
    }

    public static void updatePassword(String ID, String Password) throws SQLException {
        String SQL="UPDATE TBLACCOUNT SET passwordHash=HASHBYTES('SHA2_512', ?) WHERE employeeId=?";
        PreparedStatement S = C.prepareStatement(SQL);

        S.setString(1, Password);
        S.setString(2, ID);

        S.execute();
    }

    public static void updatePermission(String ID, int Permission) throws SQLException {
        String SQL="UPDATE TBLACCOUNT SET ";
        SQL+="permission="+Permission+" ";
        SQL+="WHERE employeeId='"+ID+"'";

        Statement S = C.createStatement();
        S.execute(SQL);
    }

    public static void retireEmployee(String ID) throws SQLException {
        String SQL="DELETE FROM TBLACCOUNT WHERE EmployeeId='"+ID+"';";
        Statement S = C.createStatement();
        S.execute(SQL);
    }
}
