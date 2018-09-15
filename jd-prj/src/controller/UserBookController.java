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
		return MyDao.queryMapList("select books.id,books.name, date_format(lend.ldDat,'%Y-%m-%d') as lddat, date_format(lend.endDat,'%Y-%m-%d') as enddat from books,lend where "
				+ "lend.user_id = (select id from users where user_account = ?) and lend.endDat < ?",session.getAttribute("loginUserId"),df.format(new Date()));
	}
}
