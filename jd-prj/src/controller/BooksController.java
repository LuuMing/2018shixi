package controller;

import java.util.ArrayList;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bean.Books;
import dq.base.dto.ResultDto;
import dq.base.utils.MyUtils;
import my.dao.MyDao;

public class BooksController {


@RestController
public class GoodsController {
	
	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping("/goodsList")
	public Object getGoodsList(Integer cls_id) {
		
		return MyDao.queryMapList("select * from goods where g_id=?", cls_id);
	}
	
	@RequestMapping("/goodsQueryList")
	public Object getGoodsQueryList(Books gd) {
		
		String sql="select *,c.cls_name "
				+ "from books g left join cls c on g.g_cls=c.cls_id "
				+ "where 1=1";
		
		
		String where="";
		ArrayList list = new ArrayList();
		
		if(gd!=null) {
			if(gd.getId()!=null) {
				sql+=" and g.g_id like ?";
				list.add("%"+gd.getId()+"%");
			}
			if(gd.getName()!=null) {
				sql+=" and g.g_name like ?";
				list.add("%"+gd.getName()+"%");
			}
			if(gd.getAuthor()!=null) {
				sql+=" and g.g_remark like ?";
				list.add("%"+gd.getAuthor()+"%");
			}
			
			
		}
		
		return MyDao.queryMapList(sql,list.toArray());
	}
	
	@RequestMapping("/goodsAddDo")
	public Object addGoods(Books goods) {
		
		try {
			//��ȡ�ļ���ԭʼ����
			String fileName = goods.getBooks_images().getOriginalFilename();
			System.out.println("filename:"+fileName);
			//��Ŀ���ļ��Ĵ�ŵ�·��
			String path = "/resources/images/" + fileName;
			//��ȡ�ļ���ʵ·��
			String realpath = servletContext.getRealPath(path);
			//��ȡ�ļ����ݣ��ֽ����飩
			byte[] bytes = goods.getBooks_images().getBytes();
			//����ļ�
			MyUtils.save(realpath, bytes);
			
			MyDao.update(
					"insert into " + "books (name,author,books_images) "
							+ "values(?       ,?    ,?    )",
					goods.getName(), goods.getAuthor(), fileName);
			
			return ResultDto.successResult("������Ʒ��Ϣ�ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			return ResultDto.failResult("������Ʒ��Ϣʧ�ܣ�");
		}
		
	}
	
	@RequestMapping("/goodsUpdDo")
	public Object updGoods(Books goods) {
		
		try {
			if(goods.getBooks_images()!=null && goods.getBooks_images().getSize()>0) {
				//��ȡ�ļ���ԭʼ����
				String fileName = goods.getBooks_images().getOriginalFilename();
				System.out.println("filename:"+fileName);
				//��Ŀ���ļ��Ĵ�ŵ�·��
				String path = "/resources/images/" + fileName;
				//��ȡ�ļ���ʵ·��
				String realpath = servletContext.getRealPath(path);
				//��ȡ�ļ����ݣ��ֽ����飩
				byte[] bytes = goods.getBooks_images().getBytes();
				//����ļ�
				MyUtils.save(realpath, bytes);
				
				MyDao.update(
						"update books set name = ? , author=? ,	books_images = ? "+
						"where id = ?", 
						goods.getName(),
						goods.getAuthor(),
				
						fileName,
					
						goods.getId());
			}else {
				MyDao.update(
						"update books set name = ? , author =?  "+
						"where id = ?", 
						goods.getName(),
						goods.getAuthor(),
				
						/*fileName,*/
					
						goods.getId());
			}
			
			
			return ResultDto.successResult("�޸���Ʒ��Ϣ�ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			return ResultDto.failResult("�޸���Ʒ��Ϣʧ�ܣ�");
		}
		
		
		
		
		
		
		
		
	}
	
	@RequestMapping("/goodsDelDo")
	public Object delGoods(Integer g_id) {
		
		MyDao.update(
				"delete from books where id = ?", 
				g_id);
		
		
		return ResultDto.successResult("ɾ����Ʒ��Ϣ�ɹ���");
		
	}
	
	@RequestMapping("/goodsDelMultiDo")
	public Object delGoodsMulti(@RequestParam(value="g_ids[]",required=false) Integer[] g_ids) {
		
		
		if(g_ids==null || g_ids.length==0) {
			return ResultDto.failResult("��ûѡ���κ����ݣ�");
		}
		
		int i;
		for(i=0;i<g_ids.length;i++) {
			MyDao.update(
					"delete from books where id = ?", 
					g_ids[i]);
		}
		
		
		
		return ResultDto.successResult("ɾ����Ʒ��Ϣ�ɹ���");
		
	}
}
}
