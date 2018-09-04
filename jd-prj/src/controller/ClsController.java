package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bean.Cls;
import dq.base.dto.ResultDto;
import my.dao.MyDao;

/**
 * ����һ���������࣬����������ͻ��˷������ݣ�json��
 * @author Administrator
 *
 */
@RestController //�����ע��󣬸���Ϳ�������ͻ��˷���json��ʽ����
public class ClsController {
	
	
	@RequestMapping("/usrList")
	public Object getAllCls() {
		
		//��ѯ��������
		return MyDao.queryMapList("select * from ims_shuihu108");
	}
	
	@RequestMapping("/clsAddDo")
	public Object addCls(Cls cls) {
		
		MyDao.update(
				"insert into cls(cls_name,cls_remark) values(?,?)", 
				cls.getCls_name(),cls.getCls_remark());
		
		
		return ResultDto.successResult("������Ʒ����ɹ���");
		
	}

}












