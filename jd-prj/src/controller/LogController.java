package controller;

import java.io.Console;

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
	@RequestMapping("/toLog")
	public Object getPassword(User usr) 
	{
		if(MyDao.queryOne("select * from users where account = ? and pwd = ?", usr.getAccount(),usr.getPwd()) != null)
			return ResultDto.successResult("��ѯʧ��");
		return ResultDto.failResult("��ѯʧ��");
	}
}










