package db.dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class DataDAO {
    ResultSet result;
    int check;
    public void UpdateUserDataAndConfig(String ID, InputStream configStream, InputStream dataStream) throws Exception
    {
        Connection conn = new Connect().getConn();
        String updateQuery = "UPDATE player SET config = ?, data = ? WHERE ID = ?";

        try (PreparedStatement updatePs = conn.prepareStatement(updateQuery)) {

            updatePs.setBinaryStream(1, configStream);
            updatePs.setBinaryStream(2, dataStream);
            updatePs.setString(3, ID);

            int rowsAffected = updatePs.executeUpdate();
            conn.close(); // Close connection after the update
        }
    }
    public void GetUserDataAndConfig(String ID) throws Exception {
        Connection conn = new Connect().getConn();
        String selectQuery = "SELECT UNCOMPRESS(config) AS config, UNCOMPRESS(data) AS data FROM player WHERE ID = ?";

        try (PreparedStatement selectPs = conn.prepareStatement(selectQuery)) {
            selectPs.setString(1, ID);

            try (ResultSet rs = selectPs.executeQuery()) {
                if (rs.next()) {
                    String configFilePath = "config";
                    String dataFilePath = "save.dat";

                    Blob configBlob = rs.getBlob("config");
                    if (configBlob != null) {
                        try (InputStream configInputStream = configBlob.getBinaryStream();
                             FileOutputStream configOutputStream = new FileOutputStream(configFilePath)) {
                            configInputStream.transferTo(configOutputStream);
                        }
                    }

                    Blob dataBlob = rs.getBlob("data");
                    if (dataBlob != null) {
                        try (InputStream dataInputStream = dataBlob.getBinaryStream();
                             FileOutputStream dataOutputStream = new FileOutputStream(dataFilePath)) {
                            dataInputStream.transferTo(dataOutputStream);
                        }
                    }
                } else {
                    System.out.println("ERR");
                }
            }
        } catch (SQLException | IOException e) {
            System.err.println("Error occurred while reading/writing files" + e.getMessage());
            throw e;
        } finally {
            conn.close();
        }
    }
}
