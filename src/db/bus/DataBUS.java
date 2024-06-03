package db.bus;

import db.dao.DataDAO;

import java.io.InputStream;

public class DataBUS {
    DataDAO dataDAO = new db.dao.DataDAO();
    public void UpdateUserDataAndConfig(String ID, InputStream configStream, InputStream dataStream) throws Exception {
        dataDAO.UpdateUserDataAndConfig(ID, configStream, dataStream);
    }
    public void GetUserDataAndConfig(String ID) throws Exception {
        dataDAO.GetUserDataAndConfig(ID);
    }
}
