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
	private static final String KEY_FILE_NAME = "file_name";
	private static final String KEY_LOCATION = "location";
	private static final String KEY_CAPTURE_DATE = "capture_date";
	private static final String KEY_ADDITIONAL_INFO = "additional_info";
	private static Connection conn;

	public DBHandler(String basePath) {
		try {
			Class.forName("org.sqlite.JDBC");
			conn =  DriverManager.getConnection("jdbc:sqlite:"+ basePath + BOOKMARK_DB);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) throws Exception {
		new DBHandler("WebContent/");
		createDatabase();
	}

	public static void createDatabase() {
		try {
			Statement stat = conn.createStatement();
			stat.executeUpdate("DROP TABLE IF EXISTS " + BOOKMARK_TABLE + ";");
			String CREATE_TABLE = "CREATE TABLE " + BOOKMARK_TABLE + "("
			+ KEY_ID + " INTEGER, " 
			+ KEY_IMAGE + " BLOB," 
			+ KEY_FILE_NAME + " TEXT," 
			+ KEY_LOCATION + " TEXT," 
			+ KEY_CAPTURE_DATE + " TEXT," 
			+ KEY_ADDITIONAL_INFO + " TEXT);";
			stat.executeUpdate(CREATE_TABLE);
			conn.close();
			System.out.println("Database created: "+BOOKMARK_DB);
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean addBookMark(BookMark bookMark) {
		try {
			PreparedStatement prep = conn.prepareStatement(
					"INSERT INTO " + BOOKMARK_TABLE 
					+ " ( " + KEY_FILE_NAME + "," + KEY_IMAGE + "," + KEY_LOCATION + "," 
					+ KEY_CAPTURE_DATE + "," + KEY_ADDITIONAL_INFO + " ) "
					+ " VALUES (?,?,?,?,?);");

			prep.setString(1, bookMark.getFileName());
			prep.setBytes(2, bookMark.getImage());
			prep.setString(3, bookMark.getLocation());
			prep.setString(4, bookMark.getCaptureDate());
			prep.setString(5, bookMark.getAdditionalInfo());
			prep.addBatch();
			conn.setAutoCommit(false);
			prep.executeBatch();
			conn.setAutoCommit(true);
			conn.close();
			return true;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public BookMark retrieveBookMark(String fileName) {
		BookMark bookMark = new BookMark();
		try {
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(
					"SELECT * FROM " + BOOKMARK_TABLE 
					+ " WHERE " + KEY_FILE_NAME + " = " + fileName + ";");
			while (rs.next()) {
				bookMark.setFileName(rs.getString(KEY_FILE_NAME));
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