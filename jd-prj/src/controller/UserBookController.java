package controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.dao.MyDao;

@RestController
public class UserBookController 
{
	@RequestMapping("/expireBook")
	public Object getExpireBook(HttpSession session)
	{	
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return MyDao.queryMapList("select * from lend where user_id = (select id from users where user_account = ?) and endDat < ?",session.getAttribute("loginUserId"),df.format(new Date()));
	}
}
