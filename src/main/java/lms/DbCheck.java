package lms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DbCheck {

	private static final String DB_URL = "jdbc:postgresql://localhost:5434/lms";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "postgres";

	static Connection conn;
	static PreparedStatement pstmt;
	static ResultSet rs;

	// staticイニシャライザでドライバ登録（必要な場合）
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	//DBへの接続
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	}

	public static void users() throws SQLException {

		String sql1 = "SELECT * FROM users";

		conn = getConnection();
		pstmt = conn.prepareStatement(sql1);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			int a = rs.getInt(1);
			String b = rs.getString(2);
			String c = rs.getString(3);
			String d = rs.getString(4);
			int e = rs.getInt(5);
			boolean f = rs.getBoolean(6);

			System.out.println("ID:" + a);
			System.out.println("name:" + b);
			System.out.println("email:" + c);
			System.out.println("password:" + d);
			System.out.println("role:" + e);
			System.out.println("active:" + f);
			System.out.println();
		}

	}

	public static void trainings() throws SQLException {
		String sql2 = "SELECT * FROM trainings";

		conn = getConnection();
		pstmt = conn.prepareStatement(sql2);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			int a = rs.getInt(1);
			String b = rs.getString(2);
			String c = rs.getString(3);
			LocalDate d = rs.getObject(4, LocalDate.class);
			LocalDate e = rs.getObject(5, LocalDate.class);
			boolean f = rs.getBoolean(6);
			boolean g = rs.getBoolean(7);

			System.out.println("ID:" + a);
			System.out.println("name:" + b);
			System.out.println("description:" + c);
			System.out.println("start_date:" + d);
			System.out.println("end_date:" + e);
			System.out.println("active:" + f);
			System.out.println("is_finalized:" + g);
			System.out.println();
		}
	}
	
	public static void training_instructors() throws SQLException {
		String sql3 = "SELECT * FROM training_instructors";

		conn = getConnection();
		pstmt = conn.prepareStatement(sql3);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			int a = rs.getInt(1);
			int b = rs.getInt(2);

			System.out.println("training_id:" + a);
			System.out.println("instructor_id:" + b);
			System.out.println();
		}
	}
	
	public static void training_students() throws SQLException {
		String sql4 = "SELECT * FROM training_students";

		conn = getConnection();
		pstmt = conn.prepareStatement(sql4);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			int a = rs.getInt(1);
			int b = rs.getInt(2);

			System.out.println("training_id:" + a);
			System.out.println("instructor_id:" + b);
			System.out.println();
		}
	}
	
	public static void assignments() throws SQLException {
		String sql5 = "SELECT * FROM assignments";

		conn = getConnection();
		pstmt = conn.prepareStatement(sql5);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			int a = rs.getInt(1);
			String b = rs.getString(2);
			String c = rs.getString(3);
			LocalDateTime d = rs.getObject(4, LocalDateTime.class);
			int e = rs.getInt(5);
			
			boolean f = rs.getBoolean(6);
			boolean g = rs.getBoolean(7);

			System.out.println("ID:" + a);
			System.out.println("title:" + b);
			System.out.println("description:" + c);
			System.out.println("due_date:" + d);
			
			System.out.println("training_id:" + e);
			System.out.println("active:" + f);
			System.out.println("allow_late_submission:" + g);
			System.out.println();
		}
	}
	
	public static void submissions() throws SQLException {
		String sql6 = "SELECT * FROM submissions";

		conn = getConnection();
		pstmt = conn.prepareStatement(sql6);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			int a = rs.getInt(1);
			int b = rs.getInt(2);
			int c = rs.getInt(3);
			String d = rs.getString(4);
			LocalDateTime e = rs.getObject(5, LocalDateTime.class);
			String f = rs.getString(6);
			String g = rs.getString(7);

			System.out.println("ID:" + a);
			System.out.println("student_id:" + b);
			System.out.println("assignment_id:" + c);
			System.out.println("file_path:" + d);
			
			System.out.println("submitted_at:" + e);
			System.out.println("grade:" + f);
			System.out.println("feedback:" + g);
			System.out.println();
		}
	}
	
	public static void attendance() throws SQLException {
		String sql6 = "SELECT * FROM attendance";

		conn = getConnection();
		pstmt = conn.prepareStatement(sql6);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			int a = rs.getInt(1);
			int b = rs.getInt(2);
			int c = rs.getInt(3);
			LocalDate d = rs.getObject(4, LocalDate.class);
			int e = rs.getInt(5);

			System.out.println("ID:" + a);
			System.out.println("student_id:" + b);
			System.out.println("training_id:" + c);
			System.out.println("attendance_date:" + d);
			System.out.println("status:" + e);
			System.out.println();
		}
	}

	public static void main(String[] args) throws SQLException {
		users();
		trainings();
		training_instructors();
		training_students();
		assignments();
		submissions();
		attendance();
	}

}
