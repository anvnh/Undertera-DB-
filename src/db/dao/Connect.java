package db.dao;
import java.sql.*;

public class Connect {
    private final Connection conn;
    public Connect() throws Exception
    {
        String url = "jdbc:mysql://localhost:3306/undertera";
        String user = "root";
        String password = "";
        conn = DriverManager.getConnection(url, user, password);
        System.out.println("Connecting Successfully.");
    }
    public Connection getConn()
    {
        return conn;
    }
    /*
    public ResultSet queryDB(String query) throws Exception
    {
        Statement st = this.getConn().createStatement();
        return st.executeQuery(query);
    }
     */

}
