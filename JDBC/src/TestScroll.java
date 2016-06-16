import java.sql.*;

/**
 * 测试结果集游标的滚动，支持向上滚动
 * @author zengli
 * @date 2016/6/3
 */
public class TestScroll {
	public static void main(String args[]) {

		try {
			//另一种写法，等价于一般的Class.forName("new oracle.jdbc.driver.OracleDriver()");
		    //new oracle.jdbc.driver.OracleDriver();
			
			Class.forName("new oracle.jdbc.driver.OracleDriver()");
			String url = "jdbc:oracle:thin:@192.168.0.1:1521:SXT";
			Connection conn = DriverManager
					.getConnection(url, "scott", "tiger");
			Statement stmt = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt
					.executeQuery("select * from emp order by sal");
			rs.next();
			System.out.println(rs.getInt(1));
			rs.last();
			System.out.println(rs.getString(1));
			
			//是最后一行吗
			System.out.println(rs.isLast());
			
			//是最后一行的下一行吗
			System.out.println(rs.isAfterLast());
			
			//获得当前行的行号
			System.out.println(rs.getRow());
			
			//游标还可以往上滚动
			rs.previous();
			System.out.println(rs.getString(1));
			
			//游标定位到指定的行号处，是绝对行号
			rs.absolute(6);
			System.out.println(rs.getString(1));
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
