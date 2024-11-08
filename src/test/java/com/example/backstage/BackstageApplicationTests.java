package com.example.backstage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class BackstageApplicationTests {

	@Autowired
	private DataSource dataSource;

	@Test
	public void testDataSourceClassTest() throws SQLException {
		System.out.println("dataSource的类型为：" + dataSource.getClass());
	}

	@Test
	public void testConnection() throws SQLException {
		Connection connection = dataSource.getConnection();
		System.out.println("获取连接：" + connection.getClass());
        // 判断连接对象是否为空
        System.out.println(connection != null);
        connection.close();
	}

}
