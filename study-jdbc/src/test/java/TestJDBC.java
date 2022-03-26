import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.cj.jdbc.Driver;
import org.junit.Test;

import java.sql.*;

public class TestJDBC {
    @Test
    public void testSelect() throws SQLException {
        //1、注册驱动
        DriverManager.registerDriver(new Driver());
        //2、获取连接
        String url = "jdbc:mysql://192.168.253.111:3306/study";
        String user = "root";
        String pwd = "root";
        Connection connection = DriverManager.getConnection(url, user, pwd);
        //3、执行sql
        String sql = "select * from blog";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        //4、查看结果
        while (resultSet.next()){
            String id = resultSet.getString("id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            Date createTime = resultSet.getDate("create_time");
            System.out.println("id:"+id+"--title:"+title+"--author:"+author+"--createTime:"+createTime);
        }
        //5、关闭连接(由内及外)
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void testInsert() throws SQLException {
        DriverManager.registerDriver(new Driver());
        String url = "jdbc:mysql://192.168.253.111:3306/study";
        String user = "root";
        String pwd = "root";
        Connection connection = DriverManager.getConnection(url, user, pwd);
        String sql = "insert into blog values('num0001','jdbc 开发指南','初级开发',now(),'20');";
        Statement statement = connection.createStatement();
        int i = statement.executeUpdate(sql);
        System.out.println(i);
        statement.close();
        connection.close();
    }

    @Test
    public void testUpdate() throws SQLException {
        DriverManager.registerDriver(new Driver());
        String url = "jdbc:mysql://192.168.253.111:3306/study";
        String user = "root";
        String pwd = "root";
        Connection connection = DriverManager.getConnection(url, user, pwd);
        String sql = "update blog set id ='num00001', title = 'jdbc入门开发指南' where id = 'num0001'";
        Statement statement = connection.createStatement();
        int i = statement.executeUpdate(sql);
        System.out.println(i);
        statement.close();
        connection.close();
    }

    @Test
    public void testDelete() throws SQLException {
        DriverManager.registerDriver(new Driver());
        String url = "jdbc:mysql://192.168.253.111:3306/study";
        String user = "root";
        String pwd = "root";
        Connection connection = DriverManager.getConnection(url, user, pwd);
        String sql = "delete from  blog where id = 'num00001'";
        Statement statement = connection.createStatement();
        int i = statement.executeUpdate(sql);
        System.out.println(i);
        statement.close();
        connection.close();
    }

    @Test
    public void testSelect2() throws SQLException {
        Connection connect = JDBCUtile.getConn();
        String sql = "select * from blog";
        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        //4、查看结果
        while (resultSet.next()){
            String id = resultSet.getString("id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            Date createTime = resultSet.getDate("create_time");
            System.out.println("id:"+id+"--title:"+title+"--author:"+author+"--createTime:"+createTime);
        }
        JDBCUtile.free(resultSet, statement, connect);
    }

    @Test
    public void sqlInject() throws SQLException {
        String sql = "select * from blog where title =";
        String name = "'' or 1=1";
        System.out.println(sql + name);
        Connection conn = JDBCUtile.getConn();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql +name);
        //4、查看结果
        while (resultSet.next()){
            String id = resultSet.getString("id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            Date createTime = resultSet.getDate("create_time");
            System.out.println("id:"+id+"--title:"+title+"--author:"+author+"--createTime:"+createTime);
        }
        JDBCUtile.free(resultSet, statement, conn);
    }

    @Test
    public void sqlInjectDeal() throws SQLException {
        String sql = "select * from blog where title = ? and id = ?";
        Connection conn = JDBCUtile.getConn();
        PreparedStatement statement = conn.prepareStatement(sql);
        //statement.setString(1,"'' or 1=1");
        statement.setString(1, "swarm");
        statement.setString(2, "num00008");
        ResultSet resultSet = statement.executeQuery();
        //4、查看结果
        while (resultSet.next()){
            String id = resultSet.getString("id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            Date createTime = resultSet.getDate("create_time");
            System.out.println("id:"+id+"--title:"+title+"--author:"+author+"--createTime:"+createTime);
        }
        JDBCUtile.free(resultSet, statement, conn);
    }

    @Test
    public void testC3p0(){
        ComboPooledDataSource cds = new ComboPooledDataSource();
        cds.setUser("root");
        cds.setPassword("root");
        cds.setJdbcUrl("jdbc:mysql://192.168.253.111:3306/study");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = cds.getConnection();
            String sql = "select * from blog";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                String id = resultSet.getString("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                Date createTime = resultSet.getDate("create_time");
                System.out.println("id:"+id+"--title:"+title+"--author:"+author+"--createTime:"+createTime);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            cds.close();
        }

    }
}

