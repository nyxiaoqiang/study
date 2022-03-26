import com.study.Blog;
import com.study.BlogMapper;
import com.study.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMybatis {
    @Test
    public void addBlog(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = new Blog();
        blog.setId("num00009");
        blog.setTitle("mysql");
        blog.setAuthorName("高级开发");
        blog.setViews(0);
        mapper.addBlog(blog);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void selectByIf(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("authorName","高级开发小强");
        List<Blog> blogs = mapper.selectByIf(params);
        blogs.stream().forEach(blog -> System.out.println(blog));
        sqlSession.close();
    }

    @Test
    public void selectByParams(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("authorName","高级开发小强");
        //params.put("title","linux");
        params.put("views","20");
        List<Blog> blogs = mapper.selectByParams(params);
        blogs.stream().forEach(blog -> System.out.println(blog));
        sqlSession.close();
    }
    @Test
    public void testSql(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("authorName","高级开发小强");
        params.put("title","linux");
        params.put("views","20");
        List<Blog> blogs = mapper.testSql(params);
        blogs.stream().forEach(blog -> System.out.println(blog));
        sqlSession.close();
    }
    @Test
    public void queeryByIds(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Map<String,List<String>> params = new HashMap<>();
        List<String> ids = new ArrayList<>();
        ids.add("num00001");
        ids.add("num00005");
        ids.add("num00009");
        params.put("ids", ids);
        List<Blog> blogs = mapper.queeryByIds(params);
        blogs.stream().forEach(blog -> System.out.println(blog));
        System.out.println("------------------------");
        List<Blog> blogs1 = mapper.queeryByIds(params);
        blogs1.stream().forEach(blog -> System.out.println(blog));
        sqlSession.close();
    }

    @Test
    public void testCache(){
        SqlSession sqlSession1 = MybatisUtil.getSqlSession();
        SqlSession sqlSession2 = MybatisUtil.getSqlSession();
        BlogMapper mapper1 = sqlSession1.getMapper(BlogMapper.class);
        BlogMapper mapper2 = sqlSession2.getMapper(BlogMapper.class);
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("authorName","高级开发小强");
        List<Blog> blogs = mapper1.selectByIf(params);
        sqlSession1.close();
        List<Blog> blogs1 = mapper2.selectByIf(params);
        sqlSession2.close();
    }
}

