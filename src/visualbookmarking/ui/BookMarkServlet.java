package visualbookmarking.ui;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import visualbookmarking.backend.DBHandler;
import visualbookmarking.bean.BookMark;

public class BookMarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DBHandler dbHandler = null;
	DateFormat m_ISO8601Local = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookMarkServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		BookMark bookmark = null;

		try {
			setDBHandler(request);
			String id = request.getParameter("id");

			if (id != null && !(id.trim().equals(""))) {
				bookmark = dbHandler.retrieveBookMarkById(id);
			}

			request.setAttribute("bookmarkObj", bookmark);
			request.getRequestDispatcher("image.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

			setDBHandler(request);
			List<FileItem> items = new ServletFileUpload(
					new DiskFileItemFactory()).parseRequest(request);
			BookMark bookmark = new BookMark();
			for (FileItem item : items) {
				if (item != null) {

					if (item.getFieldName().equals("imageFile")) {
						byte[] image = item.get();
						bookmark.setImage(image);
					}
					if (item.getFieldName().equals("id")) {
						bookmark.setId(item.getString());
					}
					if (item.getFieldName().equals("name")) {
						bookmark.setName(item.getString());
					}
					if (item.getFieldName().equals("path")) {
						bookmark.setPath(item.getString());
					}
					if (item.getFieldName().equals("captureDate")) {
						Timestamp dateTime = new Timestamp(
								new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z")
										.parse(item.getString()).getTime());
						bookmark.setCaptureDate(dateTime);
					}
					if (item.getFieldName().equals("lat")) {
						bookmark.setLat(Float.parseFloat(item.getString()));
					}
					if (item.getFieldName().equals("long")) {
						bookmark.setLon(Float.parseFloat(item.getString()));
					}
					if (item.getFieldName().equals("sharingFlag")) {
						bookmark.setSharingFlag(item.getString());
					}
					if (item.getFieldName().equals("additionalInfo")) {
						bookmark.setAdditionalInfo(item.getString());
					}
				}

			}
			boolean status = dbHandler.addBookMark(bookmark);
			if (status)
				response.getWriter().write("Saved");
			else
				response.getWriter().write("Error saving file");

		} catch (FileUploadException e) {
			response.getWriter().write("Cannot parse multipart request.");
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
			response.getWriter().write("Error saving file");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("Error saving file");
		}
	}

	private void setDBHandler(HttpServletRequest request) {
		if (dbHandler == null) {
			synchronized (this) {
				if (dbHandler == null) {
					dbHandler = new DBHandler(getBasePath(request));
				}
			}
		}
	}

	private String getBasePath(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath("/");
	}

}
