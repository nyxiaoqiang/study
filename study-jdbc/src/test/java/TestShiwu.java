import com.study.jdbc.JDBCUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestShiwu {
    @Test
    public void test() throws SQLException {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
           conn = JDBCUtil.getConn();
           conn.getAutoCommit();
           conn.setAutoCommit(false);
           statement = conn.createStatement();
           String sql = "update blog set title = 'swarm-new' where id = 'num00008'";
           int i = statement.executeUpdate(sql);
           resultSet = statement.executeQuery("select * from blog where id = 'num00008'");
           resultSet.next();
           System.out.println("id:"+resultSet.getString("id")+"-- title:"+resultSet.getString("title"));
           sql = "delete from blog where id = '00000'";
           int i1 = statement.executeUpdate(sql);
           System.out.println("受影响"+i1);
           int t = 1/0;
           conn.commit();
       }catch (Exception e){
            System.out.println("========error==========");
           conn.rollback();
            resultSet = statement.executeQuery("select * from blog where id = 'num00008'");
            resultSet.next();
            System.out.println("id:"+resultSet.getString("id")+"-- title:"+resultSet.getString("title"));
        }finally {
            resultSet.close();
           statement.close();
           conn.close();
       }
    }

    @Test
    public void test02() throws SQLException {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            conn = JDBCUtil.getConn();
            conn.getAutoCommit();
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            statement = conn.createStatement();
            resultSet = statement.executeQuery("select * from blog where id = 'num00008'");
            resultSet.next();
            System.out.println("id:"+resultSet.getString("id")+"-- title:"+resultSet.getString("title")+"-->"+System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(30);
            resultSet = statement.executeQuery("select * from blog where id = 'num00008'");
            resultSet.next();
            System.out.println("id:"+resultSet.getString("id")+"-- title:"+resultSet.getString("title")+"-->"+System.currentTimeMillis());
            conn.commit();
        }catch (Exception e){
            System.out.println("========error==========");
            conn.rollback();
        }finally {
            resultSet.close();
            statement.close();
            conn.close();
        }
    }

    @Test
    public void test03() throws SQLException {
        Connection conn = null;
        Statement statement = null;
        try{
            conn = JDBCUtil.getConn();
            conn.getAutoCommit();
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            statement = conn.createStatement();
            String sql = "update blog set title = 'swarm-new' where id = 'num00008'";
            int i = statement.executeUpdate(sql);
            System.out.println("受影响"+i+"-->" + System.currentTimeMillis());
            conn.commit();
        }catch (Exception e){
            System.out.println("========error==========");
            conn.rollback();
        }finally {
            statement.close();
            conn.close();
        }
    }
}
