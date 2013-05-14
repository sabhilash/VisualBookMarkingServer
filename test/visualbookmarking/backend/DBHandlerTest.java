package visualbookmarking.backend;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;

import visualbookmarking.bean.BookMark;

/** 
 * Unit Test Cases for DBHandler
 */
public class DBHandlerTest {

	private DBHandler dbHandler = new DBHandler(null);
	private String id = UUID.randomUUID().toString();
	private BookMark bookMark;
	/**
	 * Test method for {@link visualbookmarking.backend.DBHandler#addBookMark(visualbookmarking.bean.BookMark)}.
	 */
	@Test
	public void testAddBookMark() {
		try{
			bookMark = new BookMark(id,	
 					"Test", 
					"file://sdcard", 
					52, 
					53, 
					new java.sql.Timestamp((new Date()).getTime()), 
					"TEST", 
					"p", 
					null);
			dbHandler.addBookMark(bookMark);
		} catch (Exception e){
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link visualbookmarking.backend.DBHandler#retrieveBookMarkById(int)}.
	 */
	@Test
	public void testRetrieveBookMarkById() {
		assertNotNull(dbHandler.retrieveBookMarkById(id));
	}
}
