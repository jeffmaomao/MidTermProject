package com.midterm.model.bean;

public class RecreationFarm {
	private int id;
	private String farmName;
	private String phone;
	private String fax;
	private String pCode;
	private String county;
	private String township;
	private String addressCh;
	private String addressEn;
	private String webUrl;
	private String longitude;
	private String latitude;
	private String serveItem;
	private String facebook;

	public int getId() {
		return id;
	}

	public RecreationFarm() {

	}

	public RecreationFarm(int id, String farmName, String phone, String fax, String pCode, String county,
			String township, String addressCh, String addressEn, String webUrl, String longitude, String latitude,
			String serveItem, String facebook) {
		this.id = id;
		this.farmName = farmName;
		this.phone = phone;
		this.fax = fax;
		this.pCode = pCode;
		this.county = county;
		this.township = township;
		this.addressCh = addressCh;
		this.addressEn = addressEn;
		this.webUrl = webUrl;
		this.longitude = longitude;
		this.latitude = latitude;
		this.serveItem = serveItem;
		this.facebook = facebook;
	}

	public RecreationFarm(String farmName, String phone, String fax, String pCode, String county, String township,
			String addressCh, String addressEn, String webUrl, String longitude, String latitude, String serveItem,
			String facebook) {
		this.farmName = farmName;
		this.phone = phone;
		this.fax = fax;
		this.pCode = pCode;
		this.county = county;
		this.township = township;
		this.addressCh = addressCh;
		this.addressEn = addressEn;
		this.webUrl = webUrl;
		this.longitude = longitude;
		this.latitude = latitude;
		this.serveItem = serveItem;
		this.facebook = facebook;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFarmName() {
		return farmName;
	}

	public void setFarmName(String farmName) {
		this.farmName = farmName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPCode() {
		return pCode;
	}

	public void setPCode(String pCode) {
		this.pCode = pCode;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getTownship() {
		return township;
	}

	public void setTownship(String township) {
		this.township = township;
	}

	public String getAddressCh() {
		return addressCh;
	}

	public void setAddressCh(String address_Ch) {
		this.addressCh = address_Ch;
	}

	public String getAddressEn() {
		return addressEn;
	}

	public void setAddressEn(String address_En) {
		this.addressEn = address_En;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getServeItem() {
		return serveItem;
	}

	public void setServeItem(String serveItem) {
		this.serveItem = serveItem;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

}
