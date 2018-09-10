package bean;

import org.springframework.web.multipart.MultipartFile;

public class Books {
	
	private Integer id;
	private String name;
	private String author;

	
	
	private MultipartFile books_images;



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getAuthor() {
		return author;
	}



	public void setAuthor(String author) {
		this.author = author;
	}



	public MultipartFile getBooks_images() {
		return books_images;
	}



	public void setBooks_images(MultipartFile books_images) {
		this.books_images = books_images;
	}
	
	
	
	
	

}
