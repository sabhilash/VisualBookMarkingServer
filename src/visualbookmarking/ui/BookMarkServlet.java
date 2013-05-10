package visualbookmarking.ui;

import java.io.FileOutputStream;
import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import visualbookmarking.backend.DBHandler;
import visualbookmarking.bean.BookMark;

public class BookMarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBHandler dbHandler = new DBHandler();   
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookMarkServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String option = request.getParameter("option");
			if (option != null && option.equalsIgnoreCase("retrieve")) {
				String id = request.getParameter("id");
				BookMark bookmark = new BookMark();
				if (id != null && !(id.trim().equals(""))) {
					bookmark = dbHandler.retrieveBookMark(Integer.parseInt(id));
				}

				// Process bookmark and display image
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String option = request.getParameter("option");
			if (option != null && option.equalsIgnoreCase("add")) {
				String id = request.getParameter("id");
				BookMark bookmark = new BookMark();

				if (id != null && !(id.trim().equals(""))) {
					bookmark.setId(Integer.parseInt(id));
					List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
					for (FileItem item : items) {
						if (item.getFieldName().equals("imageFile")) {
							byte[] image = item.get();
							FileOutputStream out = new FileOutputStream(item.getName());  
							try {	
								out.write(image);  
							} finally {  
								out.close();  
							} 
							bookmark.setImage(image);
						}
						if (item.getFieldName().equals("location")) {
							bookmark.setLocation(item.getString());
						}
						if (item.getFieldName().equals("captureDate")) {
							bookmark.setCaptureDate(item.getString());
						}
						if (item.getFieldName().equals("additionalInfo")) {
							bookmark.setAdditionalInfo(item.getString());
						}
					}
					boolean status = dbHandler.addBookMark(bookmark);
					
					if (status) {
						response.setStatus(0);
					} else {
						response.setStatus(1);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
