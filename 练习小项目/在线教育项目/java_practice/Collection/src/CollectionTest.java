import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/*
    collection常用的方法

 */
public class CollectionTest {
    public static void main(String[] args) {
        Collection coll = new ArrayList();
        coll.add(1);
        coll.add(3);
        coll.add(5);
        coll.add(7);
        coll.add(9);
        coll.add(2);
        coll.add(4);
        coll.add(6);
        coll.add(8);
        coll.add(10);
        coll.add(10);
        coll.add(6);
        coll.add(6);
        coll.add(6);
        Student s1 = new Student("tom",26);
        Student s2 = new Student("tom",26);
        coll.add(s1);
        coll.add(s2);
        coll.remove(s1);

        System.out.println(coll.contains(10));
        coll.remove(6);

        Iterator it = coll.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }


    }
}

class Student{
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (age != student.age) return false;
        return name != null ? name.equals(student.name) : student.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
