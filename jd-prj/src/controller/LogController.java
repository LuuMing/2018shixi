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
 * ����һ���������࣬����������ͻ��˷������ݣ�json��
 * @author Administrator
 *
 */
//@RestController //�����ע��󣬸���Ϳ�������ͻ��˷���json��ʽ����
//public class ClsController {
//	
//	
//	@RequestMapping("/usrList")
//	public Object getAllCls() {
//		
//		//��ѯ��������
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
//		return ResultDto.successResult("�ɹ�");
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
			return ResultDto.successResult("��ѯ�ɹ�");
		}
		else 
		{
			return ResultDto.failResult("��ѯʧ��");
		}
	}
	@RequestMapping("/logOut")
	public Object logout(HttpSession session) 
	{
		session.removeAttribute("loginUserId");
		return ResultDto.successResult("ע���ɹ�");
	}
}

