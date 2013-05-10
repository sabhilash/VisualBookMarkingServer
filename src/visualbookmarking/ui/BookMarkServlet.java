package visualbookmarking.ui;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.servlet.*;

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
		String option = request.getParameter("option");
		if (option.equalsIgnoreCase("add")) {
			BookMark bookMark = new BookMark();

			String url = dbHandler.addBookMark(bookMark);
			response.setStatus(0, url);

		} else if (option.equalsIgnoreCase("retrieve")) {

			int id = Integer.parseInt(request.getParameter("id"));

			Iterator<Part> iterator = request.getParts().iterator();
//			Part parts = null;
//			while (iterator.hasNext()) {
//				parts = (Part) iterator.next();
//				//rest of the code block removed
//			}

			BookMark bookMark  = dbHandler.retrieveBookMark(id);

		} else {

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		try {
//			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
//			for (FileItem item : items) {
//				if (item.isFormField()) {
//					// Process regular form field (input type="text|radio|checkbox|etc", select, etc).
//					String fieldname = item.getFieldName();
//					String fieldvalue = item.getString();
//					// ... (do your job here)
//				} else {
//					// Process form file field (input type="file").
//					String fieldname = item.getFieldName();
//					String filename = FilenameUtils.getName(item.getName());
//					InputStream filecontent = item.getInputStream();
//					// ... (do your job here)
//				}
//			}
//		} catch (FileUploadException e) {
//			throw new ServletException("Cannot parse multipart request.", e);
//		}

	}
}
