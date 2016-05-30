package DAO.test;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.SQLException;  

public class JDBCtest {

	public static void main(String[] args) {
        String url="jdbc:mysql://115.159.122.196:3306/superquant";  
        String userName="root";  
        String password="141250089";  
        Connection conn=null;  
        try {        
              Class.forName("com.mysql.jdbc.Driver");        
              conn =  DriverManager.getConnection(url+"?useSSL=false&useServerPrepStmts=false&rewriteBatchedStatements=true", userName, password);        
              conn.setAutoCommit(false);        
              String sql = "insert into user_stock(userName,stockID) values(?,?)";        
              PreparedStatement prest = conn.prepareStatement(sql);        
              long a=System.currentTimeMillis();  
              for(int x = 0; x < 100000; x++){        
                 prest.setString(1, x+"");;        
                 prest.setString(2, "张三");     
//                 prest.execute();  
                 prest.addBatch();
                 System.out.println(x);
              }        
              prest.executeBatch();
              prest.clearBatch();
              conn.commit();
//              conn.commit();        
              long b=System.currentTimeMillis();  
              System.out.println("MySql非批量插入10万条记录用时"+ (b-a)+" ms");  
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }finally{  
            try {  
                if(conn!=null)conn.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }     
        }  
	}

}
