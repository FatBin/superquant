package DAO.DAOimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import DAO.connection.DBconnection;
import DAO.dao.TradeRecordAccurateDao;
import DAO.pojo.TradeRecordAccurate;

public class TradeRecordAccurateDaoImpl implements TradeRecordAccurateDao{

	@Override
	public List getTradeRecordAccurate(String stockId) {
		String hql="from TradeRecordAccurate t where t.id.stockId=:stockId order by t.id.date asc";
		try {
			Session session=DBconnection.getSession();
			try {
				List list=session.createQuery(hql).list();
				session.close();
				return list;
			} catch (Exception e) {
				session.close();
				e.printStackTrace();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void clean() {
		try {
			Session session=DBconnection.getSession();
			try {
			String hql="delete from TradeRecordAccurate where 1=1";
			Transaction transaction=session.beginTransaction();
			session.createQuery(hql).executeUpdate();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean persist(ArrayList<TradeRecordAccurate> arrayList) {
        String url="jdbc:mysql://115.159.122.196:3306/superquant";  
        String userName="root";  
        String password="141250089";  
        Connection conn=null;  
        try {        
              Class.forName("com.mysql.jdbc.Driver");        
              conn =  DriverManager.getConnection(url+"?useSSL=false&useServerPrepStmts=false&rewriteBatchedStatements=true", userName, password);        
              conn.setAutoCommit(false);        
              String sql = "insert into trade_record_accurate(stockId,date,price) values(?,?,?)";        
              PreparedStatement prest = conn.prepareStatement(sql);        

              for(TradeRecordAccurate po:arrayList){        
                 prest.setString(1, po.getId().getStockId());
         		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        		String string=simpleDateFormat.format(po.getId().getDate());
                 prest.setTimestamp(2,Timestamp.valueOf(string));
                 prest.setDouble(3, po.getPrice());
                 prest.addBatch();
              }        

              prest.executeBatch();
              prest.clearBatch();
              conn.commit();
              return true;
        } catch (Exception ex) {  
            ex.printStackTrace();  
            return false;
        }finally{  
            try {  
                if(conn!=null)conn.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }     
        }  
	}

}
