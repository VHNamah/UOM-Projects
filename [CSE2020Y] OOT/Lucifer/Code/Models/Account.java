package Models;

/**
 * Created by Vidush H. Namah on 11 Mar 2016.
 **/

public class Account {

    private int Permission;

    public Account(int Permission) {
        this.Permission=Permission;
    }

    public int getPermission() {
        return Permission;
    }

    public void setPermission(int permission) {
        Permission = permission;
    }
}
