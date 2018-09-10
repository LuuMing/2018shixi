package controller;

import java.io.Console;
import java.util.List;
import java.util.Map;

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
		
		List<Map<String,Object> > result = MyDao.queryMapList("select * from users where user_account = ? and pwd = ?", usr.getUser_account(),usr.getPwd());
		System.out.println(result);
		if(!result .isEmpty())
		{
			//Session--start      loginUserId
			session.setAttribute("loginUserId", usr.getUser_account());
			//Session--end
			if( result.get(0).get("usr_type").equals("admin") )
				return ResultDto.successResult("admin");
			else
				return ResultDto.successResult("user");
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
	@RequestMapping("/getInfo")
	public Object getinfo(HttpSession session)
	{
		return  MyDao.queryOneJson( "select * from users where user_account = ?", session.getAttribute("loginUserId"));
	}
}

