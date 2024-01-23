package com.midterm.model.dao;

import java.io.IOException;
import java.sql.SQLException;

import com.midterm.model.bean.RecreationFarm;

public interface IRecreationFarmDao {
	public void loadData() throws IOException, SQLException;
	public void add(RecreationFarm rf) throws SQLException;
	public void update(String phone,String serveItem, int id) throws SQLException;
	public void deleteById(int id) throws SQLException;
	public void findAll() throws SQLException;
	public boolean findById(int id) throws SQLException;
	public void storeData(int id) throws SQLException, IOException;
	public boolean dataExists(int id) throws SQLException;
	public boolean likeDataExists(int id) throws SQLException;
	public void storeImage() throws SQLException, IOException;
	public void buildImage(int id) throws SQLException, IOException;
}
