package controller;

import java.io.Console;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bean.User;
import dq.base.dto.ResultDto;
import my.dao.MyDao;
import my.dao.Rec;

@RestController
public class LogController
{
	@RequestMapping("/logIn")
	public Object loginin(User usr,HttpSession session) 
	{
		if(MyDao.queryOne("select * from users where user_account = ? and pwd = ?", usr.getUser_account(),usr.getPwd()) != null)
		{
			//Session--start      loginUserId
			session.setAttribute("loginUserId", usr.getUser_account());
			//Session--end
			return ResultDto.successResult("查询成功");
		}
		else 
		{
			return ResultDto.failResult("查询失败");
		}
	}
	@RequestMapping("/logOut")
	public Object logout(HttpSession session) 
	{
		session.removeAttribute("loginUserId");
		return ResultDto.successResult("注销成功");
	}
}

