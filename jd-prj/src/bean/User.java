package bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class User {
	Integer id;
	String account;
	String name;
	String sex;
	String pwd;
	Integer usr_type;
	@DateTimeFormat(pattern="yyyyy-MM-dd")
	Date begDat;
	String endDat;
	Integer maxCap; 
	Integer cnt;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Integer getUsr_type() {
		return usr_type;
	}
	public void setUsr_type(Integer usr_type) {
		this.usr_type = usr_type;
	}
	public Date getBegDat() {
		return begDat;
	}
	public void setBegDat(Date begDat) {
		this.begDat = begDat;
	}
	public String getEndDat() {
		return endDat;
	}
	public void setEndDat(String endDat) {
		this.endDat = endDat;
	}
	public Integer getMaxCap() {
		return maxCap;
	}
	public void setMaxCap(Integer maxCap) {
		this.maxCap = maxCap;
	}
	public Integer getCnt() {
		return cnt;
	}
	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
	
	
	
	
}
