package com.midterm.model.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.midterm.model.bean.RecreationFarm;


public class RecreationFarmDaoJdbcImpl implements IRecreationFarmDao {

	private Connection conn;
	
	public RecreationFarmDaoJdbcImpl(Connection conn) {
		this.conn = conn;
	}

	public void loadData() throws IOException, SQLException {
		final String DB_URL = "	https://data.moa.gov.tw/Service/OpenData/ODwsv/ODwsvOutdoorEdu.aspx?FOTT=CSV&IsTransData=1&UnitId=242";
		URL url = new URL(DB_URL);
		URLConnection urlConn = url.openConnection();
		InputStream is = urlConn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String str = "";
		br.readLine(); // skip the first line
		while ((str = br.readLine()) != null) {
			if (str.length() < 1) // if data left some space at the end
				break;
			if (str.charAt(str.length() - 1) == ',') // if last column is empty, add space to match the column count
				str += " ";
			String[] arr = str.split(",");
			String sqlStr = "Insert into RecreationFarm (farmName,phone,fax,pCode,county,township,addressCh,addressEn,webUrl,"
					+ "longitude,latitude,serveItem,facebook) Values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement state = conn.prepareStatement(sqlStr);
			for (int i = 0; i < arr.length; i++)
				state.setString(i + 1, arr[i]);
			state.executeUpdate();
			state.close();
		}

		br.close();
		is.close();
	}

	public void add(RecreationFarm rf) throws SQLException {
		String sqlStr = "Insert into RecreationFarm (farmName,phone,fax,pCode,county,township,addressCh,addressEn,webUrl,"
				+ "longitude,latitude,serveItem,facebook) Values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement state = conn.prepareStatement(sqlStr);
		state.setString(1, rf.getFarmName());
		state.setString(2, rf.getPhone());
		state.setString(3, rf.getFax());
		state.setString(4, rf.getPCode());
		state.setString(5, rf.getCounty());
		state.setString(6, rf.getTownship());
		state.setString(7, rf.getAddressCh());
		state.setString(8, rf.getAddressEn());
		state.setString(9, rf.getWebUrl());
		state.setString(10, rf.getLongitude());
		state.setString(11, rf.getLatitude());
		state.setString(12, rf.getServeItem());
		state.setString(13, rf.getFacebook());
		state.executeUpdate();

		state.close();
		System.out.println("Data inserted\n資料已存入資料庫");
	}

	public void update(String phone, String serveItem, int id) throws SQLException {
		String sqlStr = "Update RecreationFarm Set phone=?,serveItem=? Where id=?";
		PreparedStatement state = conn.prepareStatement(sqlStr);
		state.setString(1, phone);
		state.setString(2, serveItem);
		state.setInt(3, id);
		state.executeUpdate();
		state.close();
		System.out.println("Data of ID:" + id + " has been updated");
		System.out.println("ID " + id + "資料更新完成");
	}

	public void deleteById(int id) throws SQLException {
		String sqlStr = "Delete from RecreationFarm Where id like ? ";
		PreparedStatement state = conn.prepareStatement(sqlStr);
		state.setString(1, "%" + id + "%");
		state.executeUpdate();
		state.close();
		System.out.println("Data whose ID contains " + id + " has been deleted");
		System.out.println("ID含有數字" + id + "的資料已刪除");
	}
	
	public void findAll() throws SQLException {
		String sqlStr = "Select id, farmName From RecreationFarm";
		PreparedStatement state = conn.prepareStatement(sqlStr);
		ResultSet rs = state.executeQuery();
		while(rs.next()) {
			System.out.println("ID: " + rs.getInt(1) + "\t" + rs.getString(2));
		}
		rs.close();
		state.close();
	}

	public boolean findById(int id) throws SQLException {
		boolean idExist = false;
		String sqlStr = "Select * From RecreationFarm Where id=?";
		PreparedStatement state = conn.prepareStatement(sqlStr);
		state.setInt(1, id);
		ResultSet rs = state.executeQuery();
		if (idExist = rs.next()) {
			System.out.println("\nID: " + rs.getInt(1));
			System.out.println("農場名稱: " + rs.getString(2));
			System.out.println("電話：　" + rs.getString(3));
			System.out.println("傳真：　" + rs.getString(4));
			System.out.println("郵遞區號：　" + rs.getString(5));
			System.out.println("縣市：　" + rs.getString(6));
			System.out.println("鄉鎮區：　" + rs.getString(7));
			System.out.println("中文地址：　" + rs.getString(8));
			System.out.println("英文地址：　" + rs.getString(9));
			System.out.println("網站: " + rs.getString(10));
			System.out.println("經度: " + rs.getString(11));
			System.out.println("緯度: " + rs.getString(12));
			System.out.println("服務項目: " + rs.getString(13));
			System.out.println("Facebook: " + rs.getString(14));
		} else
			System.out.println("Data not exist\n資料不存在");
		rs.close();
		state.close();
		return idExist;
	}

	public void storeData(int id) throws IOException, SQLException {
		String sqlStr = "Select * From RecreationFarm Where id=?";
		PreparedStatement state = conn.prepareStatement(sqlStr);
		state.setInt(1, id);
		ResultSet rs = state.executeQuery();
		rs.next();
		BufferedWriter bw = new BufferedWriter(
				new FileWriter("C:\\Users\\Student\\desktop\\ID_" + id + ".txt", true));
		bw.write("ID: " + rs.getInt(1) + "\n");
		bw.write("農場名稱: " + rs.getString(2) + "\n");
		bw.write("電話：　" + rs.getString(3) + "\n");
		bw.write("傳真：　" + rs.getString(4) + "\n");
		bw.write("郵遞區號：　" + rs.getString(5) + "\n");
		bw.write("縣市：　" + rs.getString(6) + "\n");
		bw.write("鄉鎮區：　" + rs.getString(7) + "\n");
		bw.write("中文地址：　" + rs.getString(8) + "\n");
		bw.write("英文地址：　" + rs.getString(9) + "\n");
		bw.write("網站: " + rs.getString(10) + "\n");
		bw.write("經度: " + rs.getString(11) + "\n");
		bw.write("緯度: " + rs.getString(12) + "\n");
		bw.write("服務項目: " + rs.getString(13) + "\n");
		bw.write("Facebook: " + rs.getString(14) + "\n");
		bw.close();
		rs.close();
		state.close();
		System.out.println("txt is created\n檔案建立完成");
	}

	public boolean dataExists(int id) throws SQLException {
		String sqlStr = "Select * From RecreationFarm Where id =?";
		PreparedStatement state = conn.prepareStatement(sqlStr);
		state.setInt(1, id);
		boolean status = state.executeQuery().next();
		if (!status)
			state.close();
		return status;
	}

	public boolean likeDataExists(int id) throws SQLException {
		String sqlStr = "Select * From RecreationFarm Where id like ?";
		PreparedStatement state = conn.prepareStatement(sqlStr);
		state.setString(1, "%" + id + "%");
		boolean status = state.executeQuery().next();
		if (!status)
			state.close();
		return status;
	}
	
	public void storeImage() throws SQLException, IOException {
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream("c://temp//test//mickey.jpg"));
			String sqlStr = "Insert into Images (FileName,FileContent) Values(?,?)";
			PreparedStatement state = conn.prepareStatement(sqlStr);
			state.setString(1, "Mickey");
			state.setBinaryStream(2, bis);
			state.executeUpdate();
			state.close();
			bis.close();
			System.out.println("圖片成功存取入資料庫");
		} catch (FileNotFoundException e) {
			System.out.println("File not found\n找不到指定路徑檔案");
		}

	}

	public void buildImage(int id) throws IOException, SQLException {
		String sqlStr = "Select * From images Where FileId =?";
		PreparedStatement state = conn.prepareStatement(sqlStr);
		state.setInt(1, id);
		ResultSet rs = state.executeQuery();
		if (rs.next()) {
			Blob blob = rs.getBlob(3);
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("c://temp//test//copy.jpg"));
			bos.write(blob.getBytes(1, (int) blob.length()));
			bos.flush();
			bos.close();
			rs.close();
			state.close();
			System.out.println("New image is built\n新圖片已建立");
		} else
			System.out.println("ImageID " + id + " not exist\n資料庫沒有ID " + id + "的圖片");
	}
}
