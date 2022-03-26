import com.study.MybatisUtil;
import com.study.Teacher;
import com.study.TeacherMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TestMybatis {
    @Test
    public void getTeacher(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        List<Teacher> teacher = mapper.getTeacher();
        System.out.println(teacher.get(0));
        sqlSession.close();
    }

    @Test
    public void getTeacher2(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        List<Teacher> teacher = mapper.getTeacher2();
        System.out.println(teacher.get(0));
        sqlSession.close();
    }
}
