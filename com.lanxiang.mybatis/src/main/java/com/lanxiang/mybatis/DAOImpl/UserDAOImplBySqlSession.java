package com.lanxiang.mybatis.DAOImpl;

import com.lanxiang.mybatis.mapper.UserMapper;
import com.lanxiang.mybatis.model.User;
import com.lanxiang.mybatis.model.UserExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.util.List;

/**
 * Created by lanxiang on 16/8/22.
 */
public class UserDAOImplBySqlSession implements UserMapper {

    private SqlSessionFactory sqlSessionFactory;

    private void initSqlSessionFactory() throws Exception {
        String resource = "configuration.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    public int countByExample(UserExample example) {
        return 0;
    }

    public int deleteByExample(UserExample example) {
        return 0;
    }

    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    public int insert(User record) {
        try {
            initSqlSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SqlSession session = sqlSessionFactory.openSession();
        try {
            int count = session.insert("com.lanxiang.mybatis.mapper.UserMapper.insert", record);
            System.out.println(count);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return 0;
    }

    public int insertSelective(User record) {
        return 0;
    }

    public List<User> selectByExample(UserExample example) {
        return null;
    }

    public User selectByPrimaryKey(Integer id) {
        return null;
    }

    public int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example) {
        return 0;
    }

    public int updateByExample(@Param("record") User record, @Param("example") UserExample example) {
        return 0;
    }

    public int updateByPrimaryKeySelective(User record) {
        return 0;
    }

    public int updateByPrimaryKey(User record) {
        return 0;
    }
}
