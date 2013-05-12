package visualbookmarking.ui;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import visualbookmarking.backend.DBHandler;
import visualbookmarking.bean.BookMark;

/**
 * Servlet implementation class BookMarkInputServlet
 */
public class BookMarkInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DBHandler dbHandler = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookMarkInputServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		if (id != null) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/BookMarkServlet");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request,
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
