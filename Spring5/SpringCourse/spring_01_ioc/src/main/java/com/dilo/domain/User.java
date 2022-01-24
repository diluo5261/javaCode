package com.dilo.domain;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class User {
    private String name;
    private int age;

    private School school;
    private List<String> hobby;
    private Map<String,School> schoolMap;
    private List<School> schoolList;
    private Properties properties;


    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public List<School> getSchoolList() {
        return schoolList;
    }

    public void setSchoolList(List<School> schoolList) {
        this.schoolList = schoolList;
    }

    public Map<String, School> getSchoolMap() {
        return schoolMap;
    }

    public void setSchoolMap(Map<String, School> schoolMap) {
        this.schoolMap = schoolMap;
    }

    public List<String> getHobby() {
        return hobby;
    }

    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", school=" + school +
                ", hobby=" + hobby +
                ", schoolMap=" + schoolMap +
                ", schoolList=" + schoolList +
                ", properties=" + properties +
                '}';
    }
}
