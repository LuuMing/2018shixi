package controller;

import java.io.Console;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bean.User;
import dq.base.dto.ResultDto;
import my.dao.MyDao;
import my.dao.Rec;

/**
 * ï¿½ï¿½ï¿½ï¿½Ò»ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½à£¬ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Í»ï¿½ï¿½Ë·ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ý£ï¿½jsonï¿½ï¿½
 * @author Administrator
 *
 */
//@RestController //ï¿½ï¿½ï¿½ï¿½ï¿½×¢ï¿½ï¿½ó£¬¸ï¿½ï¿½ï¿½Í¿ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Í»ï¿½ï¿½Ë·ï¿½ï¿½ï¿½jsonï¿½ï¿½Ê½ï¿½ï¿½ï¿½ï¿½
//public class ClsController {
//	
//	
//	@RequestMapping("/usrList")
//	public Object getAllCls() {
//		
//		//ï¿½ï¿½Ñ¯ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
//		return MyDao.queryMapList("select * from ims_shuihu108");
//	}
//	
//	@RequestMapping("/clsAddDo")
//	public Object addCls(User cls) {
//		
//		MyDao.update(
//				"insert into cls(cls_name,cls_remark) values(?,?)", 
//				cls.getCls_name(),cls.getCls_remark());
//		
//		
//		return ResultDto.successResult("³É¹¦");
//		
//	}
//
//}

@RestController
public class LogController
{
	@RequestMapping("/logIn")
	public Object loginin(User usr,HttpSession session) 
	{
		if(MyDao.queryOne("select * from users where user_account = ? and pwd = ?", usr.getUser_account(),usr.getPwd()) != null)
		{
			//Session--start
			session.setAttribute("loginUserId", usr.getId());
			//Session--end
			return ResultDto.successResult("²éÑ¯³É¹¦");
		}
		else 
		{
			return ResultDto.failResult("²éÑ¯Ê§°Ü");
		}
	}
	@RequestMapping("/logOut")
	public Object logout(HttpSession session) 
	{
		session.removeAttribute("loginUserId");
		return ResultDto.successResult("×¢Ïú³É¹¦");
	}
}

