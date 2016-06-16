import java.sql.*;
public class TestTransaction {


	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:SXT", "scott", "tiger");
			
			//将自动提交设置为false
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			
			//添加进批处理
			stmt.addBatch("insert into dept2 values (51, '500', 'haha')");
			stmt.addBatch("insert into dept2 values (52, '500', 'haha')");
			stmt.addBatch("insert into dept2 values (53, '500', 'haha')");
			//执行批处理
			stmt.executeBatch();
			//手动提交
			conn.commit();
			
			//恢复现场
			conn.setAutoCommit(true);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			
			e.printStackTrace();
			
			try {
				if(conn != null)
				{
					//如果SQL语句执行异常，那么执行回滚，保险起见，再讲自动提交设回true
					conn.rollback();
					conn.setAutoCommit(true);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

	}

}
