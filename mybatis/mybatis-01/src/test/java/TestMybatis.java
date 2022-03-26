import com.study.MybatisUtil;
import com.study.User;
import com.study.UserDao;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMybatis {
    @Test
    public void test(){
        //1.获取SqlSession对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        //2.执行SQL
        // 方式一：getMapper
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> userList = userDao.getUserList();
        for (User user : userList) {
            System.out.println(user);
        }
        //关闭sqlSession
        sqlSession.close();
    }

    @Test
    public void findBy(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User userById = mapper.getUserById(1);
        System.out.println(userById);
        sqlSession.close();
    }

    @Test
    public void addUser(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setName("李雪强");
        user.setPwd("123456");
        mapper.addUser(user);
        sqlSession.commit();
        System.out.println(user.getId());
        sqlSession.close();
    }

    @Test
    public void updateUser(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setPwd("123456-new");
        user.setName("小强");
        user.setId(8);
        mapper.updateUser(user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleteUser(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        mapper.deleteUser(7);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void getUserListMap(){
        //1.获取SqlSession对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        //2.执行SQL
        // 方式一：getMapper
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<Map<String, Object>> userListMap = userDao.getUserListMap();
        for (Map<String,Object> map: userListMap) {
            System.out.println(map.get("name"));
        }
        //关闭sqlSession
        sqlSession.close();
    }

    @Test
    public void addUserMap(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        Map<String,Object> user = new HashMap<String, Object>();
        user.put("pwd","123456-new");
        user.put("name","小强map");
        mapper.addUserMap(user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void byName(){
        //1.获取SqlSession对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        //2.执行SQL
        // 方式一：getMapper
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<Map<String, Object>> userListMap = userDao.selectByName("强");
        for (Map<String,Object> map: userListMap) {
            System.out.println(map.get("name"));
        }
        //关闭sqlSession
        sqlSession.close();
    }
}

