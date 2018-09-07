package controller;

import java.util.Calendar;
import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bean.User;
import dq.base.dto.ResultDto;
import my.dao.MyDao;

@RestController
public class RegController
{
	@RequestMapping("/toReg")
	public Object reg(User usr) 
	{
		if(MyDao.queryOne("select * from users where user_account = ?", usr.getUser_account()) != null)
			return ResultDto.failResult("注册失败");
		else
		{
			Date current =  new java.sql.Date(new java.util.Date().getTime());
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(current);
			switch(usr.getUser_type()) 
			{
				case "学生" : 	calendar.add(Calendar.YEAR, 1);					break;
				case "教师":	calendar.add(Calendar.YEAR, 2);				   break;
				default:			calendar.add(Calendar.YEAR, 1);
			}
			MyDao.update("insert into users(user_account,user_name,sex,pwd,usr_type,begDat,endDat) values(?,?,?,?,?,?,?)", 
					usr.getUser_account(),usr.getUser_name(),usr.getSex(),usr.getPwd(),usr.getUser_type(),current,calendar.getTime());
			return ResultDto.successResult("注册成功");
		}
	}
}