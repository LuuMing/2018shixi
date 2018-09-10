package controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bean.User;
import dq.base.dto.ResultDto;
import my.dao.MyDao;

@RestController
public class PwdUpdController 
{
	@RequestMapping("/pwdUpd")
	public Object pwdupd(String newPwd,HttpSession session) 
	{
			MyDao.update("update users set pwd=? where user_account= ?", newPwd,session.getAttribute("loginUserId"));
			return ResultDto.successResult("success");
	} 
}
