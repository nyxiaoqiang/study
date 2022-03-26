import com.study.MybatisUtil;
import com.study.Student;
import com.study.StudentMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TestMybatis {
    @Test
    public void getStudents(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> student = mapper.getStudent();
        for (int i = 0; i < student.size(); i++) {
            System.out.println(student.get(i));
        }
    }

    @Test
    public void getStudents2(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> student = mapper.getStudent2();
        for (int i = 0; i < student.size(); i++) {
            System.out.println(student.get(i));
        }
    }
}
