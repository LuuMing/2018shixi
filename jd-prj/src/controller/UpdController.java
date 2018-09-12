package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bean.User;
import dq.base.dto.ResultDto;
import my.dao.MyDao;

@RestController
public class UpdController 
{
	@RequestMapping("/pwdUpd")
	public Object pwdupd(String newPwd,HttpSession session) 
	{
		MyDao.update("update users set pwd=? where user_account= ?", newPwd,session.getAttribute("loginUserId"));
		 return ResultDto.successResult("success");
	} 
	
	@RequestMapping("/infoUpd")
	public Object infoupd(User newinfo,HttpSession session) 
	{
		System.out.println(newinfo.getUser_name()+newinfo.getSex());
		MyDao.update("update users set user_name= ?, sex=? where user_account  = ?",newinfo.getUser_name(),newinfo.getSex(),session.getAttribute("loginUserId"));
		 return ResultDto.successResult("success");
	} 
}
