package com.example;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class Boot04DataDruidApplicationTests {
	@Autowired
	DataSource dataSource;

	@Test
	public void testDruid() throws SQLException {
		Connection connection = dataSource.getConnection();
		System.out.println(dataSource.getClass());
		System.out.println(connection);
	}

	@Test
	public void testMyDruid(){
		DruidDataSource dataSource1 = (DruidDataSource) this.dataSource;
		System.out.println(dataSource1.getMaxActive());
		System.out.println(dataSource1.getInitialSize());
	}
}
