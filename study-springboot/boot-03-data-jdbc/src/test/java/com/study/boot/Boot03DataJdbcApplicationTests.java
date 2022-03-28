package com.study.boot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@SpringBootTest
class Boot03DataJdbcApplicationTests {

	@Autowired
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	public void testJdbc() throws SQLException {
		Connection connection = dataSource.getConnection();
		System.out.println(connection);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from tbl_middle_student");
		while (resultSet.next()){
			System.out.println(resultSet.getInt("id"));
		}
		resultSet.close();
		statement.close();
		connection.close();
	}

	@Test
	public void testTemplate(){
		String sql = "select * from tbl_middle_student";
		List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
		System.out.println(maps.get(1));
	}
}
