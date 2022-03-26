package com.study;

import org.apache.ibatis.session.SqlSession;

public class TestMybatis {
    public static void main(String[] args) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();

    }
}
