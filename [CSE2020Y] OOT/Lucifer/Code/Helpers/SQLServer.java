package Helpers;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Vidush H. Namah on 08 Mar 2016.
 **/

public final class SQLServer {

    private static final String URL = "DATABASE CONNECTION STRING";

    private static Connection Connect = null;

    public static boolean dbConnect() {
        boolean flag;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connect = DriverManager.getConnection(URL);

            flag=true;
        } catch(Exception E) {
            System.out.println(E);

            flag=false;
        }

        return flag;
    }

    public static Connection getConnect() {
        return Connect;
    }
}
