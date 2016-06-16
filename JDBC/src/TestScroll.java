import java.sql.*;

/**
 * ���Խ�����α�Ĺ�����֧�����Ϲ���
 * @author zengli
 * @date 2016/6/3
 */
public class TestScroll {
	public static void main(String args[]) {

		try {
			//��һ��д�����ȼ���һ���Class.forName("new oracle.jdbc.driver.OracleDriver()");
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
			
			//�����һ����
			System.out.println(rs.isLast());
			
			//�����һ�е���һ����
			System.out.println(rs.isAfterLast());
			
			//��õ�ǰ�е��к�
			System.out.println(rs.getRow());
			
			//�α껹�������Ϲ���
			rs.previous();
			System.out.println(rs.getString(1));
			
			//�α궨λ��ָ�����кŴ����Ǿ����к�
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
