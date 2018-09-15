package bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class User {

	public Boolean valid() //判断有效数据--start
	{
		return user_account != null && user_name != null && sex != null && pwd != null && user_type != null;
	}											//判断有效数据--end
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUser_account() {
		return user_account;
	}
	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
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
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
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
	Integer id;
	String user_account;
	String user_name;
	String sex;
	String pwd;
	String user_type;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date begDat;
	String endDat;
	Integer maxCap; 
	Integer cnt;
	
}
