package com.example.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * author: zf
 * Date: 2016/10/27  9:18
 * Description:
 */
@Entity
@Table(name = "student")
public class Student implements Serializable {

    private static final long serialVersionUID = 7944514266528757334L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Name")
    private String name;
    @Column(name = "ScoreSum")
    private String scoreSum;
    @Column(name = "ScoreAvg")
    private String scoreAvg;
    @Column(name = "age")
    private int age;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", scoreSum='" + scoreSum + '\'' +
                ", scoreAvg='" + scoreAvg + '\'' +
                ", age=" + age +
                '}';
    }

    public Student() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScoreSum() {
        return scoreSum;
    }

    public void setScoreSum(String scoreSum) {
        this.scoreSum = scoreSum;
    }

    public String getScoreAvg() {
        return scoreAvg;
    }

    public void setScoreAvg(String scoreAvg) {
        this.scoreAvg = scoreAvg;
    }
}
