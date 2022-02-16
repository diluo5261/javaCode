public class Student {
    private String name;
    private int age;
    public int id;
    boolean sex;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
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

    public int doubleNum(int num){
        return num*2;
    }

    boolean isExists(int num){
        return true;
    }

    private boolean isMale(int num){
        return false;
    }



    protected int tenYear(int age){
        return age + 10;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
