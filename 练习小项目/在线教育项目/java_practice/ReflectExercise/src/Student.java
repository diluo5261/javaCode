public class Student {
    public String name;
    int age;
    protected double score;
    private boolean sex;
    private static int country;

    public Student() {
    }

    public Student(String name, int age, double score, boolean sex) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.sex = sex;
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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }


    public boolean login(String name , String password){
        return ("admin".equals(name) && "123".equals(password));
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                ", sex=" + sex +
                '}';
    }
}
