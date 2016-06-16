import java.sql.*;
public class TestUpdataRs {
    public static void main(String args[]){
	
	try{
		//��һ��д�����ȼ���Class.forName("oracle.jdbc.driver.OracleDriver()");
	    //new oracle.jdbc.driver.OracleDriver();
		
		Class.forName("oracle.jdbc.driver.OracleDriver()");
	    String url="jdbc:oracle:thin:@192.168.0.1:1521:SXT";
	    Connection conn=DriverManager.getConnection(url,"scott","tiger");
	    
	    //ResultSet.TYPE_SCROLL_INSENSITIVE�Խ�������α���������У�ResultSet.CONCUR_UPDATABLE������ɸ���
	    Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	    
	    ResultSet rs=stmt.executeQuery("select * from emp2");
	    
	    rs.next();
	    //����һ������
	    rs.updateString("ename","AAAA");
	    rs.updateRow();

	    //��������,����������ڽ������������������һ������
	    rs.moveToInsertRow();
	    rs.updateInt(1, 9999);
	    rs.updateString("ename","AAAA");
	    rs.updateInt("mgr", 7839);
	    rs.updateDouble("sal", 99.99);
	    rs.insertRow();
	    //������ƶ����½�����
	    rs.moveToCurrentRow();

	    //ɾ����
	    rs.absolute(5);
	    rs.deleteRow();

	    //ȡ������
	    //rs.cancelRowUpdates();

	  }catch(SQLException e){
	    e.printStackTrace();
	  } catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
    }
}
