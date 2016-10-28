package com.example.service;

import com.example.mapper.StudentMapper;
import com.example.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * author: zf
 * Date: 2016/10/27  9:23
 * Description:
 */
@Service

public class StudentService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private StudentMapper studentMapper;

    /**
     *  jdbc 连接数据库
     * @return
     */
    public List<Student> getList() {
        String sql = "select * from student";
        return  jdbcTemplate.query(sql, new RowMapper<Student>(){
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student stu = new Student();
                stu.setId(rs.getInt("ID"));
                stu.setAge(rs.getInt("AGE"));
                stu.setName(rs.getString("NAME"));
                stu.setScoreSum(rs.getString("ScoreSum"));
                stu.setScoreAvg(rs.getString("ScoreAvg"));
                return stu;
            }
        });
    }

    /**
     *  mybatis
     * @param id
     * @return
     */
    public Student findOne(Integer id) {

        return studentMapper.findOne(id);
    }

    public List<Student> queryStu() {

        return studentMapper.queryStu();
    }

    @Transactional
    public int updateStu(Student student){
        int i1 = studentMapper.updateByPrimaryKeySelective(student);
//        测试事务回滚
        int i = 1/0;
        return i1;
    }
}
