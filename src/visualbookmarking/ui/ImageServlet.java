package visualbookmarking.ui;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import visualbookmarking.backend.DBHandler;
import visualbookmarking.bean.BookMark;

public class ImageServlet extends HttpServlet {

	private static DBHandler dbHandler = null;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {


		try {
			setDBHandler(request);
			String id = request.getParameter("id");

			if (id != null && !(id.trim().equals(""))) {
				byte[] img = dbHandler.retrieveBookMarkImage(id);
				response.setContentType("image/png");
				response.setContentLength(img.length);

				response.setHeader("Content-Disposition", "inline; filename=\"" + id + "\"");

				BufferedInputStream input = null;
				BufferedOutputStream output = null;

				try {
					input = new BufferedInputStream(new ByteArrayInputStream(img));
					output = new BufferedOutputStream(response.getOutputStream());
					byte[] buffer = new byte[8192];
					int length;
					while ((length = input.read(buffer)) > 0) {
						output.write(buffer, 0, length);
					}
				} catch (IOException e) {
					System.out.println("There are errors in reading/writing image stream "
									+ e.getMessage());
				} finally {
					if (output != null) {
						try {
							output.close();
						} catch (IOException ignore) {
						}
					}
					if (input != null) {
						try {
							input.close();
						} catch (IOException ignore) {
						}
					}
				}
			}
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