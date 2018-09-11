package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bean.Books;
import dq.base.dto.ResultDto;
import dq.base.utils.MyUtils;
import my.dao.MyDao;

@RestController
public class BooksController {

	
	@Autowired
	private ServletContext servletContext;
	
	
	@RequestMapping("/loadBookList")
	public Object getAllCls() {
		
		//查询所有数据
		return MyDao.queryMapList("select * from books");
	}
	
	
/*	@RequestMapping("/goodsList")
	public Object getGoodsList(Integer cls_id) {
		
		return MyDao.queryMapList("select * from goods where g_id=?", cls_id);
	}*/
	
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
	
	@RequestMapping("/booksAddDo")
	public Object addGoods(Books goods) {
		
		try {
			//获取文件的原始名称
			String fileName = goods.getBooks_images().getOriginalFilename();
			System.out.println("filename:"+fileName);
			//项目中文件的存放的路径
			String path = "/resources/images/" + fileName;
			//获取文件真实路径
			String realpath = servletContext.getRealPath(path);
			//获取文件数据（字节数组）
			byte[] bytes = goods.getBooks_images().getBytes();
			//存放文件
			MyUtils.save(realpath, bytes);
			
			MyDao.update(
					"insert into " + "books (name,author,books_images) "
							+ "values(?       ,?    ,?    )",
					goods.getName(), goods.getAuthor(), fileName);
			
			return ResultDto.successResult("新增商品信息成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ResultDto.failResult("新增商品信息失败！");
		}
		
	}
	//http://localhost:8080/jd-prj/images/books?filename=%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1).png
	@RequestMapping("/images/books")
	public void image(String filename,HttpServletResponse response) throws Exception {
		
		//filename=new String(filename.getBytes("iso-8859-1"),"UTF-8");
		
		OutputStream out =response.getOutputStream();
		
		InputStream in = new FileInputStream("E:/imgs/books/"+filename);
		
		byte[] b=new byte[1024*1024];
		int len = -1;
		
		while((len=in.read(b))!=-1) {
			out.write(b, 0, len);
		}
		
		out.flush();
		out.close();
		in.close();
		
		
	}
	
	private static long num=System.currentTimeMillis();
	private static synchronized  long getNewNum() {
		return ++num;
	}
	
	@RequestMapping("/booksUpdDo")
    public Object updBooks(Books book) {
		
		try {
			if(book.getBooks_images()!=null && book.getBooks_images().getSize()>0) {
				//获取文件的原始名称
				String fileName = book.getBooks_images().getOriginalFilename();
				fileName = getNewNum()+fileName.substring(fileName.lastIndexOf("."));
				System.out.println("filename:"+fileName);
				//项目中文件的存放的路径
				//String path = "/resources/images/books/" + fileName;
				//获取文件真实路径
				//String realpath = servletContext.getRealPath(path);
				String realpath = "E:/imgs/books/"+fileName;
				//获取文件数据（字节数组）
				byte[] bytes = book.getBooks_images().getBytes();
				//存放文件
				MyUtils.save(realpath, bytes);
				
				MyDao.update(
						"update books set name = ? , author=? ,books_images = ? "+
						"where id = ?", 
						book.getName(),
						book.getAuthor(),
						
						fileName,
						book.getId());
			}else {
				MyDao.update(
						"update  books set name = ? , author=?  "+
						"where id = ?", 
						book.getName(),
						book.getAuthor(),
						
						/*fileName,*/
						book.getId());
			}
			
			
			return ResultDto.successResult("修改商品信息成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ResultDto.failResult("修改商品信息失败！");
		}		
		
}		
		
		
		
	
	
	@RequestMapping("/booksDelDo")
	public Object delGoods(Integer id) {
		
		MyDao.update(
				"delete from books where id = ?", 
				id);
		
		
		return ResultDto.successResult("删除商品信息成功！");
		
	}
	
	@RequestMapping("/goodsDelMultiDo")
	public Object delGoodsMulti(@RequestParam(value="g_ids[]",required=false) Integer[] g_ids) {
		
		
		if(g_ids==null || g_ids.length==0) {
			return ResultDto.failResult("您没选择任何数据！");
		}
		
		int i;
		for(i=0;i<g_ids.length;i++) {
			MyDao.update(
					"delete from books where id = ?", 
					g_ids[i]);
		}
		
		
		
		return ResultDto.successResult("删除商品信息成功！");
		
	}
}

