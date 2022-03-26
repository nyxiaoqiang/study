import com.study.MybatisUtil;
import com.study.User;
import com.study.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMybatid {
    @Test
    public void testSelect(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.getUserList();
        System.out.println(userList.size());
        sqlSession.close();
    }


    @Test
    public void addUser(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUserName("李雪强-an-if");
        user.setPassword("123456-an");
        mapper.addUser(user);
        sqlSession.commit();
        System.out.println(user.getId());
        sqlSession.close();
    }

    @Test
    public void updateUser(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setPassword("123456-an-new");
        user.setUserName("小强");
        user.setId(13);
        mapper.updateUser(user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleteUser(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.deleteUser(14);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void getUserListMap(){
        //1.获取SqlSession对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        //2.执行SQL
        // 方式一：getMapper
        UserMapper userDao = sqlSession.getMapper(UserMapper.class);
        List<Map<String, Object>> userListMap = userDao.getUserListMap();
        for (Map<String,Object> map: userListMap) {
            System.out.println(map.get("name"));
        }
        //关闭sqlSession
        sqlSession.close();
    }

    @Test
    public void byName(){
        //1.获取SqlSession对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        //2.执行SQL
        // 方式一：getMapper
        UserMapper userDao = sqlSession.getMapper(UserMapper.class);
        List<User> userListMap = userDao.selectByName("强");
        for (User map: userListMap) {
            System.out.println(map.getUserName());
        }
        //关闭sqlSession
        sqlSession.close();
    }
}
