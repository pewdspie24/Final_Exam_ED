package controller.bookDAO;

import java.util.List;

import model.book.Comics;
import model.book.LightNovel;
import model.book.TextBook;

public interface BookDAO {

	public void addBookLN(LightNovel book);

	public void addBookCM(Comics book);

	public void addBookTX(TextBook book);
	
	public boolean delBook(int id);
	
	public List<Comics> findAllComics();
	
	public List<LightNovel> findAllLightNovel();
	
	public List<TextBook> findAllTextBook();
	
	public void editLNBook(LightNovel book);
	
	public void editCMBook(Comics book);
	
	public void editTXBook(TextBook book);

}