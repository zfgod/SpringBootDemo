package com.example.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * author: zf
 * Date: 2016/11/1  19:22
 * Description:
 */
@Entity
@Table(name = "user")
public class Users implements Serializable{

    private static final long serialVersionUID = 6598176455152763160L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Name")
    private String name;
    @Column(name = "qq")
    private String qq;
    @Column(name = "phone")
    private String phone;
    @Column(name = "age")
    private int age;


    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", qq='" + qq + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                '}';
    }

    public Users() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
