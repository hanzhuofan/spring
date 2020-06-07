package com.bilibili.mybatis;

import com.bilibili.mybatis.entity.User;
import com.bilibili.mybatis.mapper.UserMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashSet;

/**
 * @author hanzhuofan
 * @date 2020/5/24 12:
 */
public class MybatisDome {
  public static void main(String[] args) throws IOException, SQLException {
    UserMapper mapper = getMapper(UserMapper.class);
    User user = mapper.selectUserById(1, null);
    System.out.println(user.getAge());
  }

  private static <T> T getMapper(Class<T> clazz) throws IOException, SQLException {
    String resource = "mybatis/mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//    DataSource dataSource = getDataSource();
//    TransactionFactory transactionFactory = new JdbcTransactionFactory();
//    Environment environment = new Environment("development", transactionFactory, dataSource);
//    Configuration configuration = new Configuration(environment);
//    configuration.addMapper(userMapperClass);
//    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.getMapper(clazz);
  }

  private static DataSource getDataSource() {
    PooledDataSource pooledDataSource = new PooledDataSource();
    pooledDataSource.setDriver("com.mysql.cj.jdbc.Driver");
    pooledDataSource.setUrl("jdbc:mysql://192.168.242.130:3306/test?useUnicode=true&characterEncoding=utf-8");
    pooledDataSource.setUsername("root");
    pooledDataSource.setPassword("Changeme_123");
    return pooledDataSource;
  }
}
