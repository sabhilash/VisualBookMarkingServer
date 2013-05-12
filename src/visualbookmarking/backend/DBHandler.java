package visualbookmarking.backend;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import visualbookmarking.bean.BookMark;

// CREATE TABLE BOOKMARK_TABLE(id VARCHAR  PRIMARY KEY,name TEXT,path TEXT,
// 	capture_date DATETIME,lat REAL,long REAL,additional_info TEXT,sharing_flag CHAR,image BLOB)
public class DBHandler {
	private static final String BOOKMARK_DB = "BOOKMARK_DB.db";
	private static final String BOOKMARK_TABLE = "BOOKMARK_TABLE";

	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_PATH = "path";
	private static final String KEY_CAPTURE_DATE = "capture_date";
	private static final String KEY_LAT = "lat";
	private static final String KEY_LONG = "long";
	private static final String KEY_SHARING_FLAG = "sharing_flag";
	private static final String KEY_ADDITIONAL_INFO = "additional_info";
	private static final String KEY_IMAGE = "image";
	
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
			String CREATE_TABLE = "CREATE TABLE " + BOOKMARK_TABLE 
									+ "("
									+ KEY_ID + " VARCHAR, " 
									+ KEY_NAME + " TEXT," 
									+ KEY_PATH + " TEXT,"
									+ KEY_CAPTURE_DATE + " DATETIME,"
									+ KEY_LAT + " REAL," 
									+ KEY_LONG + " REAL," 
									+ KEY_SHARING_FLAG + " CHAR," 
									+ KEY_ADDITIONAL_INFO + " TEXT,"
									+ KEY_IMAGE + " BLOB"
									+");";
			stat.executeUpdate(CREATE_TABLE);
			System.out.println("Database created: "+BOOKMARK_DB);
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean addBookMark(BookMark bookMark) {
		try {
			String insertQuery = "INSERT INTO " + BOOKMARK_TABLE 
					+ " ( " 
					+ KEY_ID + ","
					+ KEY_NAME + "," 
					+ KEY_PATH + "," 
					+ KEY_CAPTURE_DATE + ","
					+ KEY_LAT + "," 
					+ KEY_LONG + "," 
					+ KEY_SHARING_FLAG + ","
					+ KEY_ADDITIONAL_INFO + "," 
					+ KEY_IMAGE 
					+ " ) "
					+ " VALUES (?,?,?,?,?,?,?,?,?);";
			
			PreparedStatement prep = conn.prepareStatement(insertQuery);

			prep.setString(1, bookMark.getId());
			prep.setString(2, bookMark.getName());
			prep.setString(3, bookMark.getPath());
			prep.setTimestamp(4, bookMark.getCaptureDate());
			prep.setFloat(5, bookMark.getLat());
			prep.setFloat(6, bookMark.getLon());
			prep.setString(7, bookMark.getSharingFlag());
			prep.setString(8, bookMark.getAdditionalInfo());
			prep.setBytes(9, bookMark.getImage());
			prep.addBatch();
			conn.setAutoCommit(false);
			prep.executeBatch();
			conn.setAutoCommit(true);
			return true;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public byte[] retrieveBookMarkImage(String id){
		byte[] imageBytes=null;
		try {
			Statement stat = conn.createStatement();
			String query = "SELECT image FROM " + BOOKMARK_TABLE
					+ " WHERE " + KEY_ID + " = '" + id + "';";
			ResultSet rs = stat.executeQuery(query);
			if (rs.next()) {
				imageBytes = rs.getBytes(KEY_IMAGE);
			}
			rs.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return imageBytes;
	}

	public List<BookMark> retrieveBookMarksByName(String fileName) {
		
		List<BookMark> list = new ArrayList<BookMark>();
		
		try {
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(
					"SELECT id,name,path,capture_date,lat,long,sharing_flag,additional_info FROM " + BOOKMARK_TABLE 
					+ " WHERE " + KEY_NAME + " = '" + fileName + "';");
			while (rs.next()) {
				BookMark bookMark = new BookMark();
				list.add(populateBookMarkProperties(bookMark, rs));
			}
			rs.close();
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
	
	
	
	
	public BookMark retrieveBookMarkById(String id) {
		BookMark bookMark = null;
		try {
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(
					"SELECT id,name,path,capture_date,lat,long,sharing_flag,additional_info FROM " + BOOKMARK_TABLE 
					+ " WHERE id = '" + id + "';" );
			if (rs.next()) {
				bookMark = new BookMark();
				bookMark = populateBookMarkProperties(bookMark, rs);
			}
			rs.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return bookMark;
	}
	
	private BookMark populateBookMarkProperties(BookMark bookMark, ResultSet rs)throws SQLException {
		
		bookMark.setId(rs.getString(KEY_ID));
		bookMark.setName(rs.getString(KEY_NAME));
		bookMark.setPath(rs.getString(KEY_PATH));
		bookMark.setCaptureDate(rs.getTimestamp(KEY_CAPTURE_DATE));
		bookMark.setLat(rs.getLong(KEY_LAT));
		bookMark.setLon(rs.getLong(KEY_LONG));
		bookMark.setSharingFlag(rs.getString(KEY_SHARING_FLAG));
		bookMark.setAdditionalInfo(rs.getString(KEY_ADDITIONAL_INFO));
		//skip byte[]
		return bookMark;
		
	}
	
	 public void destroy() {
		    // Clean up.
		    try {
		      if (conn != null) conn.close();
		    }
		    catch (SQLException ignored) { }
		  }

}