package com.midterm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.midterm.conn.ConnectionFactory;
import com.midterm.model.bean.RecreationFarm;
import com.midterm.model.dao.IRecreationFarmDao;
import com.midterm.model.dao.RecreationFarmDaoJdbcImpl;

public class Action {
	public static void main(String[] args) throws Exception, SQLException {
		Scanner scn = new Scanner(System.in);
		Connection conn = ConnectionFactory.createConnection();
		IRecreationFarmDao rfDao = new RecreationFarmDaoJdbcImpl(conn);
		while (true) {
			String function = "";
			System.out.println(
					"\n功能清單\n(0)退出選單\n(1)下載休閒農場資料\n(2)讀取農場列表\n(3)讀取資料並匯出檔案\n(4)更新電話、服務項目資料\n(5)刪除資料\n(6)新增資料\n(7)存取圖片\n(8)讀取圖片並建立檔案\n請選擇功能");
			boolean correctInput = false;
			while (!correctInput) {
				correctInput = true;
				function = scn.nextLine();
				switch (function.trim()) {
				case "0":
					System.out.println("謝謝使用");
					break;
				case "1", "一":
					rfDao.loadData();
					System.out.println("下載完成");
					break;
				case "2", "二":
					rfDao.findAll();
					break;
				case "3", "三":
					System.out.println("請輸入想要讀取資料的ID:");
					try {
						int readId = scn.nextInt();
						scn.nextLine();
						boolean hasData = rfDao.findById(readId);
						if (hasData) {
							while(true) {
								System.out.println("\n是否要將資料匯出成txt檔:(Y/N)");
								String input = scn.nextLine();
								input = input.toLowerCase();
								if (input.substring(0, 1).equals("y") || input.substring(0, 1).equals("是")) {
									rfDao.storeData(readId);
									break;
								} else if (input.substring(0, 1).equals("n") || input.substring(0, 1).equals("否"))
									break;
								else
									System.out.println("請輸入Y或N回答");
							}
						}
					} catch (InputMismatchException e) {
						System.out.println("錯誤的輸入，請輸入數字ID");
						correctInput = false;
					}
					break;
				case "4", "四":
					System.out.println("請輸入想要更新電話及服務項目資料的ID:");
					System.out.println("欲進行更改資料的ID");
					try {
						int storeId = scn.nextInt();
						if (rfDao.dataExists(storeId)) {
							scn.nextLine();
							System.out.println("輸入新電話:");
							String newPhone = scn.nextLine();
							System.out.println("輸入新服務項目:");
							String newServeItem = scn.nextLine();
							rfDao.update(newPhone, newServeItem, storeId);
						} else {
							System.out.println("資料不存在");
							scn.nextLine();
						}
					} catch (InputMismatchException e) {
						System.out.println("錯誤的輸入，請輸入數字ID");
						correctInput = false;
					}
					break;
				case "5", "五":
					System.out.println("請輸入想要刪除ID包含那些數字的資料:");
					try {
						int deleteId = scn.nextInt();
						scn.nextLine();
						if (rfDao.likeDataExists(deleteId))
							rfDao.deleteById(deleteId);
						else 
							System.out.println("資料不存在");
					} catch (InputMismatchException e) {
						System.out.println("錯誤的輸入，請輸入數字ID");
						correctInput = false;
					}
					break;
				case "6", "六":
					System.out.println("請輸入要新增的資料\n農場名稱:");
					String farmName = scn.nextLine();
					System.out.println("電話");
					String phone = scn.nextLine();
					System.out.println("傳真");
					String fax = scn.nextLine();
					System.out.println("郵遞區號");
					String pCode = scn.nextLine();
					System.out.println("縣市");
					String county = scn.nextLine();
					System.out.println("鄉鎮區");
					String township = scn.nextLine();
					System.out.println("中文地址");
					String addressCh = scn.nextLine();
					System.out.println("英文地址");
					String addressEn = scn.nextLine();
					System.out.println("網址");
					String webUrl = scn.nextLine();
					System.out.println("經度");
					String longitude = scn.nextLine();
					System.out.println("緯度");
					String latitude = scn.nextLine();
					System.out.println("服務項目");
					String serveItem = scn.nextLine();
					System.out.println("Facebook");
					String facebook = scn.nextLine();
					System.out.println("輸入完成");
					RecreationFarm rf = new RecreationFarm(farmName,phone,fax,pCode,county,township,addressCh,
							addressEn,webUrl,longitude,latitude,serveItem,facebook);
					rfDao.add(rf);
					break;
				case "7", "七":
					rfDao.storeImage();
					break;
				case "8", "八":
					System.out.println("請輸入想要存入硬碟圖片的ID:");
					try {
						int imageId = scn.nextInt();
						scn.nextLine();
						rfDao.buildImage(imageId);
					} catch (InputMismatchException e) {
						System.out.println("錯誤的輸入，請輸入數字ID");
						correctInput = false;
					}
					break;
				default:
					System.out.println("輸入錯誤，請輸入0-8重新選擇功能");
				}
			}
			if(function.trim().equals("0"))
				break;
		}
		ConnectionFactory.closeConnection(conn);
		scn.close();
	}
}

