package vo;

import java.util.Date;

public class UserVO {
	private int userId;
	private String userName;
	private String password;
	private Date createdOn;
	private int quota;
	private String PRODUCTS;
	private Date expiresOn;
	private String adminUser;
	private String id;
	
	public UserVO(int userId,String userName,String id, String password) {
		this.userId = userId;
		this.userName = userName;
		this.id = id;
		this.password = password;
	}
	
	public UserVO(String userName, String id, String password, Date createdOn) {
		this.userName = userName;
		this.id = id;
		this.password = password;
		this.createdOn = createdOn;
	}
	
	public UserVO(int userId, String userName, String password, Date createdOn, int quota, String PRODUCTS,
			Date expiresOn, String adminUser, String id) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.createdOn = createdOn;
		this.quota = quota;
		this.PRODUCTS = PRODUCTS;
		this.expiresOn = expiresOn;
		this.adminUser = adminUser;
		this.id = id;
	}
	@Override
	public String toString() {
		return userId + "::" + userName + "/" + id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public int getQuota() {
		return quota;
	}
	public void setQuota(int quota) {
		this.quota = quota;
	}
	public String getPRODUCTS() {
		return PRODUCTS;
	}
	public void setPRODUCTS(String pRODUCTS) {
		PRODUCTS = pRODUCTS;
	}
	public Date getExpiresOn() {
		return expiresOn;
	}
	public void setExpiresOn(Date expiresOn) {
		this.expiresOn = expiresOn;
	}
	public String getAdminUser() {
		return adminUser;
	}
	public void setAdminUser(String adminUser) {
		this.adminUser = adminUser;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
