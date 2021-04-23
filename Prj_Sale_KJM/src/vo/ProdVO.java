package vo;

import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ProdVO {
	private int pId;
	private String pName;
	private String cate;
	private int price;
	
	public ProdVO(int pId,String pName, String cate, int price) {
		super();
		this.pId = pId;
		this.pName = pName;
		this.cate = cate;
		this.price = price;
	}

	public ProdVO(String pName, String cate, int price) {
		super();
		this.pName = pName;
		this.cate = cate;
		this.price = price;
	}

	public int getpId() {return pId;}
	public void setpId(int pId) {this.pId = pId;}
	public String getpName() {return pName;}
	public void setpName(String pName) {this.pName = pName;}
	public String getCate() {return cate;}
	public void setCate(String cate) {this.cate = cate;}
	public int getPrice() {return price;}
	public void setPrice(int price) {this.price = price;}
}
