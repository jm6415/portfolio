package vo;

public class CustVO {
	private int customerId;
	private String cName;
	private String post,limit,custid;
	private String firstname,lastname,city,address2;
	private String address,state,phone,phone2,email;
	
	

	public CustVO(int customerId, String cName) {
		this.customerId = customerId;this.cName = cName;
	}
	public int getCustomerId() {return customerId;}
	public void setCustomerId(int customerId) {this.customerId = customerId;}
	public String getcName() {return cName;}
	public void setcName(String cName) {this.cName = cName;}
	public CustVO(String custid,String firstname, String lastname, String address,String city,String state,String post, String phone, String limit) {
		this.firstname = firstname;this.custid =custid;
		this.state = state;this.post=post;this.limit=limit;this.city=city;
		this.phone=phone;this.lastname = lastname;this.address = address;
	}


	public CustVO(String custid,String firstname,String lastname, String address,String address2, 
			 String city, String state,String post,String phone,String phone2,String limit,String email) {
	
		this.custid = custid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.post = post;
		this.phone = phone;
		this.phone2 = phone2;
		this.limit = limit;
		this.email = email;
	}


	@Override
	public String toString() {
		return firstname + lastname;
	}
	public String getcustid() {
		return custid;
	}
	public void setcustid(String custid) {
		this.custid = custid;
	}
	public String getfirstname() {
		return firstname;
	}
	public void setfirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getlastname() {
		return lastname;
	}
	public void setlastname(String lastname) {
		this.lastname = lastname;
	}
	public String getaddress() {
		return address;
	}
	public void setaddress(String address) {
		this.address = address;
	}
	public String getaddress2() {
		return address2;
	}
	public void setaddress2(String address2) {
		this.address2 = address2;
	}
	public String getcity() {
		return city;
	}
	public void setcity(String city) {
		this.city = city;
	}
	public String getstate() {
		return state;
	}
	public void state(String state) {
		this.state = state;
	}
	public String getpost() {
		return post;
	}
	public void setpost(String post) {
		this.post = post;
	}
	public String getphone() {
		return phone;
	}
	public void setphone(String phone) {
		this.phone = phone;
	}
	public String phone2() {
		return phone;
	}
	public void setphone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getlimit() {
		return limit;
	}
	public void setlimit(String limit) {
		this.limit = limit;
	}
	public String getemail() {
		return email;
	}
	public void setemail(String email) {
		this.email = email;
	}
}