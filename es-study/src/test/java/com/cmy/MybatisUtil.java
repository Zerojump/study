package com.cmy;

import com.alibaba.druid.pool.DruidDataSource;
import com.cmy.mapper.ItemMapper;
import com.cmy.model.Item;
import com.cmy.model.ItemExample;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.After;
import org.junit.Test;

import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Lankidd on 2017/3/11.
 */
public class MybatisUtil {

    public static SqlSession session;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("mysql");
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(bundle.getString("mysql.driver"));
        dataSource.setUrl(bundle.getString("mysql.url"));
        dataSource.setUsername(bundle.getString("mysql.username"));
        dataSource.setPassword(bundle.getString("mysql.password"));

        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(ItemMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        session = sqlSessionFactory.openSession();
    }

    @Test
    public void test1() throws Exception {
        ItemMapper mapper = session.getMapper(ItemMapper.class);
        List<Item> itemList = mapper.selectByExample(new ItemExample());

        Item item = mapper.selectByPrimaryKey(536563L);
        System.out.println("item = " + item);
    }

    @After
    public void tearDown() throws Exception {
        if (session != null) {
            session.commit();
            session.close();
        }

    }
}
