package db.bus;

import db.dao.*;

public class LoginBUS {
    LoginDAO login = new db.dao.LoginDAO();
    public boolean AddAccount(String id, String gmail, String usr, String passwd, String conf, String data) throws Exception {
        return login.AddAccount(id, gmail, usr, passwd, conf, data);
    }
    public boolean LoginCheck(String usr, String pass) throws Exception {
        return login.LoginCheck(usr, pass);
    }
    public void ResetPassword(String Gmail, String NewPass) throws Exception {
        login.ResetPassword(Gmail, NewPass);
    }
    public String GetUserID(String username, String password) throws Exception {
        return login.getUserId(username, password);
    }
}
