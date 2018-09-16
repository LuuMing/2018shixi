package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		//锟斤拷询锟斤拷锟斤拷锟斤拷锟斤拷
		return MyDao.queryMapList("select * from books");
	}
	
	@RequestMapping("/loadReserveBookList")
	public Object getReverseCls() {
		
		//锟斤拷询锟斤拷锟斤拷锟斤拷锟斤拷
		return MyDao.queryMapList("select * from books where Lend = false");
	}
	
	@RequestMapping("/loadLendBookList")
	public Object getLendCls(HttpSession session) {
		
		Integer user_id = MyDao.queryInteger("select id from users where user_account = ?", session.getAttribute("loginUserId"));
		//锟斤拷询锟斤拷锟斤拷锟斤拷锟斤拷
		return MyDao.queryMapList("select * from books where id in (select book_id from lend where user_id = ?)",user_id);
	}
	
	@RequestMapping("/queryBooks")
	public Object getGoodsQueryList(Books gd) {
		
		String sql="select g.*,c.type_name "
				+ "from books g left join book_type c on g.books_type=c.id "
				+ "where 1=1";
		
		
		String where="";
		ArrayList list = new ArrayList();
		
		if(gd!=null) {
			if(gd.getId()!=null) {
				sql+=" and g.id like ?";
				list.add("%"+gd.getId()+"%");
			}
			if(gd.getName()!=null) {
				sql+=" and g.name like ?";
				list.add("%"+gd.getName()+"%");
			}
			if(gd.getAuthor()!=null) {
				sql+=" and g.author like ?";
				list.add("%"+gd.getAuthor()+"%");
			}
			
			
		}
		
		return MyDao.queryMapList(sql,list.toArray());
	}
	@RequestMapping("/queryBooksBorrow")
	public Object getGoodsBorrowQueryList(Books gd) {
		String sql="select g.*,c.type_name "
				+ "from books g left join book_type c on g.books_type=c.id "
				+ "where g.Lend=false ";
		String where="";
		ArrayList list = new ArrayList();
		
		if(gd!=null) {
			if(gd.getId()!=null) {
				sql+=" and g.id like ?";
				list.add("%"+gd.getId()+"%");
			}
			if(gd.getName()!=null) {
				sql+=" and g.name like ?";
				list.add("%"+gd.getName()+"%");
			}
			if(gd.getAuthor()!=null) {
				sql+=" and g.author like ?";
				list.add("%"+gd.getAuthor()+"%");
			}		
		}	
		return MyDao.queryMapList(sql,list.toArray());
	}
	
	@RequestMapping("/booksAddDo")
	public Object addGoods(Books goods) {
		
		try {
			//锟斤拷取锟侥硷拷锟斤拷原始锟斤拷锟斤拷
			String fileName = goods.getBooks_images().getOriginalFilename();
			System.out.println("filename:"+fileName);
			//锟斤拷目锟斤拷锟侥硷拷锟侥达拷诺锟铰凤拷锟�
			String path = "/resources/images/" + fileName;
			//锟斤拷取锟侥硷拷锟斤拷实路锟斤拷
			String realpath = servletContext.getRealPath(path);
			//锟斤拷取锟侥硷拷锟斤拷锟捷ｏ拷锟街斤拷锟斤拷锟介）
			byte[] bytes = goods.getBooks_images().getBytes();
			//锟斤拷锟斤拷募锟�
			MyUtils.save(realpath, bytes);
			
			MyDao.update(
					"insert into " + "books (name,author,books_images,Lend) "
							+ "values(?       ,?    ,?    ,false)",
					goods.getName(), goods.getAuthor(), fileName);
			
			return ResultDto.successResult("锟斤拷锟斤拷锟斤拷品锟斤拷息锟缴癸拷锟斤拷");
		} catch (Exception e) {
			e.printStackTrace();
			return ResultDto.failResult("锟斤拷锟斤拷锟斤拷品锟斤拷息失锟杰ｏ拷");
		}
		
	}
	//http://localhost:8080/jd-prj/images/books?filename=%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1).png
	@RequestMapping("/images/books")
	public void image(String filename,HttpServletResponse response) throws Exception {
		
		//filename=new String(filename.getBytes("iso-8859-1"),"UTF-8");
		
		OutputStream out =response.getOutputStream();
		
		InputStream in = new FileInputStream("E:/imgs/books/"+filename); //linux "/home/luming/imgs/books/"
		
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
				//锟斤拷取锟侥硷拷锟斤拷原始锟斤拷锟斤拷
				String fileName = book.getBooks_images().getOriginalFilename();
				fileName = getNewNum()+fileName.substring(fileName.lastIndexOf("."));
				System.out.println("filename:"+fileName);
				//锟斤拷目锟斤拷锟侥硷拷锟侥达拷诺锟铰凤拷锟�
				//String path = "/resources/images/books/" + fileName;
				//锟斤拷取锟侥硷拷锟斤拷实路锟斤拷
				//String realpath = servletContext.getRealPath(path);
				String realpath = "E:/imgs/books/"+fileName;   //linux "/home/luming/imgs/books/"
				//锟斤拷取锟侥硷拷锟斤拷锟捷ｏ拷锟街斤拷锟斤拷锟介）
				byte[] bytes = book.getBooks_images().getBytes();
				//锟斤拷锟斤拷募锟�
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
			
			
			return ResultDto.successResult("锟睫革拷锟斤拷品锟斤拷息锟缴癸拷锟斤拷");
		} catch (Exception e) {
			e.printStackTrace();
			return ResultDto.failResult("锟睫革拷锟斤拷品锟斤拷息失锟杰ｏ拷");
		}		
		
}		
		
		
		
	
	
	@RequestMapping("/booksDelDo")
	public Object delGoods(Integer id) {
		
		MyDao.update(
				"delete from books where id = ?", 
				id);
		
		
		return ResultDto.successResult("删锟斤拷锟斤拷品锟斤拷息锟缴癸拷锟斤拷");
		
	}
	
	@RequestMapping("/booksDelMultiDo")
	public Object delGoodsMulti(@RequestParam(value="ids[]",required=false) Integer[] g_ids) {
		
		
		if(g_ids==null || g_ids.length==0) {
			return ResultDto.failResult("锟斤拷没选锟斤拷锟轿猴拷锟斤拷锟捷ｏ拷");
		}
		
		int i;
		for(i=0;i<g_ids.length;i++) {
			MyDao.update(
					"delete from books where id = ?", 
					g_ids[i]);
		}
		
		
		
		return ResultDto.successResult("删锟斤拷锟斤拷品锟斤拷息锟缴癸拷锟斤拷");		
	}
	@RequestMapping("/booksBorrowDo")
	public Object borrowBooks(Integer id, HttpSession session) {
		String user_account = (String) session.getAttribute("loginUserId");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Integer user_day = MyDao.queryInteger("select maxDay from users where user_account = ?", user_account);
		Integer user_id = MyDao.queryInteger("select id from users where user_account = ?", user_account);
		Calendar calendar = Calendar.getInstance();
		Date current = new Date();
        calendar.setTime(current);
        calendar.add(Calendar.DATE, user_day);
		MyDao.update(
				"insert  into lend(user_id,book_id,ldDat,endDat) values(?, ?,?,?)",user_id,id,df.format(current) ,calendar.getTime());
		MyDao.update("update books set Lend = true where id = ?",id);
		return ResultDto.successResult("OK!");
		
	}
}

