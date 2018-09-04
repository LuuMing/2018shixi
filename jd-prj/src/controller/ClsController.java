package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bean.Cls;
import dq.base.dto.ResultDto;
import my.dao.MyDao;

/**
 * 这是一个控制器类，其作用是向客户端发送数据（json）
 * @author Administrator
 *
 */
@RestController //引入该注解后，该类就可以向向客户端发送json格式数据
public class ClsController {
	
	
	@RequestMapping("/clsList")
	public Object getAllCls() {
		
		//查询所有数据
		return MyDao.queryMapList("select * from cls");
	}
	
	@RequestMapping("/clsAddDo")
	public Object addCls(Cls cls) {
		
		MyDao.update(
				"insert into cls(cls_name,cls_remark) values(?,?)", 
				cls.getCls_name(),cls.getCls_remark());
		
		
		return ResultDto.successResult("新增商品分类成功！");
		
	}

}












