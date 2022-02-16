package crud;

/*
ORM(object relational mapping)编程思想：
数据库中的一个表 与 java中的一个类对应


 */


import java.sql.Date;

public class Customer {
    private int id;
    private String name;
    private String email;
    private Date birth;
//    private Blob photo;


    public Customer() {
    }

    public Customer(int id, String name, String email, Date birth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                '}';
    }
}
