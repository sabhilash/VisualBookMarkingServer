package visualbookmarking.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import visualbookmarking.bean.BookMark;

public class DBHandler {
	private static final String BOOKMARK_DB = "BOOKMARK_DB.db";
	private static final String BOOKMARK_TABLE = "BOOKMARK_TABLE";

	private static final String KEY_ID = "id";
	private static final String KEY_IMAGE = "image";
	private static final String KEY_URI = "uri";
	private static final String KEY_LOCATION = "location";
	private static final String KEY_CAPTURE_DATE = "capture_date";
	private static final String KEY_ADDITIONAL_INFO = "additional_info";
	private static Connection conn;

	public DBHandler() {
		try {
			Class.forName("org.sqlite.JDBC");
			conn =  DriverManager.getConnection("jdbc:sqlite:" + BOOKMARK_DB);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) throws Exception {
		new DBHandler();
		createDatabase();
	}

	public static void createDatabase() {
		try {
			Statement stat = conn.createStatement();
			stat.executeUpdate("DROP TABLE IF EXISTS " + BOOKMARK_TABLE + ";");
			String CREATE_TABLE = "CREATE TABLE " + BOOKMARK_TABLE + "("
			+ KEY_ID + " INTEGER, " 
			+ KEY_IMAGE + " BLOB," 
			+ KEY_URI + " TEXT," 
			+ KEY_LOCATION + " TEXT," 
			+ KEY_CAPTURE_DATE + " TEXT," 
			+ KEY_ADDITIONAL_INFO + " TEXT);";
			stat.executeUpdate(CREATE_TABLE);
			conn.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String addBookMark(BookMark bookMark) {
		try {
			PreparedStatement prep = conn.prepareStatement(
					"INSERT INTO " + BOOKMARK_TABLE 
					+ " ( " + KEY_ID + "," + KEY_IMAGE + "," + KEY_LOCATION + "," 
					+ KEY_CAPTURE_DATE + "," + KEY_ADDITIONAL_INFO + " ) "
					+ " VALUES (?, ?, ?,?,?);");

			prep.setInt(1, bookMark.getId());
			prep.setBytes(2, bookMark.getImage());
			prep.setString(3, bookMark.getLocation());
			prep.setString(4, bookMark.getCaptureDate());
			prep.setString(5, bookMark.getAdditionalInfo());
			prep.addBatch();
			conn.setAutoCommit(false);
			prep.executeBatch();
			conn.setAutoCommit(true);
			conn.close();
			String imageURL = "http://localhost:8080/VisualBookMarkingServer/BookMarkServlet?option=retrieve&id="+bookMark.getId();
			return imageURL;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return "";
		}
	}

	public BookMark retrieveBookMark(int id) {
		BookMark bookMark = new BookMark();
		try {
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(
					"SELECT * FROM " + BOOKMARK_TABLE 
					+ " WHERE " + KEY_ID + " = " + id + ";");
			while (rs.next()) {
				bookMark.setId(rs.getInt(KEY_ID));
				bookMark.setImage(rs.getBytes(KEY_IMAGE));
				bookMark.setLocation(rs.getString(KEY_LOCATION));
				bookMark.setCaptureDate(rs.getString(KEY_CAPTURE_DATE));
				bookMark.setAdditionalInfo(rs.getString(KEY_ADDITIONAL_INFO));
			}
			rs.close();
			conn.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return bookMark;
	}

}