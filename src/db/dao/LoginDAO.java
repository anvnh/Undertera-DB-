package db.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO{
    ResultSet result;
    int check;
    public String userID = "";
    public Boolean AddAccount(String id, String gmail, String usr, String passwd, String conf, String data) throws Exception {
        Connection conn = new Connect().getConn();
        String Query = "INSERT INTO player (ID, gmail, username, password, config, data) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(Query);
        ps.setString(1, id);
        ps.setString(2, gmail);
        ps.setString(3, usr);
        ps.setString(4, passwd);
        ps.setString(5, conf);
        ps.setString(6, data);
        check = ps.executeUpdate();
        conn.close();
        return check > 0;
    }
    public Boolean DeleteAccount(String ID) throws Exception {
        Connection conn = new Connect().getConn();
        String Query = "DELETE FROM player WHERE ID = ?";
        PreparedStatement ps = conn.prepareStatement(Query);
        ps.setString(1, ID);
        check = ps.executeUpdate();
        conn.close();
        return check > 0;
    }
    public Boolean LoginCheck(String gml, String pass) throws Exception {
        Connection conn = new Connect().getConn();
        String Query = "SELECT * FROM player WHERE gmail = ? AND password = ?";
        PreparedStatement ps = conn.prepareStatement(Query);
        ps.setString(1, gml);
        ps.setString(2, pass);
        result = ps.executeQuery();
        while(result.next())
        {
            if(result.getString("gmail").equals(gml) && result.getString("password").equals(pass))
            {
                return true;
            }
        }
        result.close();
        conn.close();
        return false;
    }
    public String getUserId(String gmail, String password) throws SQLException {
        String query = "SELECT ID FROM player WHERE gmail = ? AND password = ?"; // Use hashed password here
        try (Connection conn = new Connect().getConn();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, gmail);
            ps.setString(2, password); // Use hashed password here

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("ID");
                } else {
                    return null; // Or throw an exception if you prefer
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void ResetPassword(String Gmail, String NewPass) throws Exception {
        Connection conn = new Connect().getConn();
        String Query = "UPDATE player SET password = ? WHERE gmail = ?";
        PreparedStatement ps = conn.prepareStatement(Query);
        ps.setString(1, NewPass);
        ps.setString(2, Gmail);
        check = ps.executeUpdate();
        conn.close();
    }
}