public class Student {
    public String name;
    public int age;

    String classname;
    int id;

    protected String email;
    protected double score;

    private String sex;
    private boolean isHigh;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student(String name, int age, String classname, int id, String email, double score, String sex, boolean isHigh) {
        this.name = name;
        this.age = age;
        this.classname = classname;
        this.id = id;
        this.email = email;
        this.score = score;
        this.sex = sex;
        this.isHigh = isHigh;
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

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isHigh() {
        return isHigh;
    }

    public void setHigh(boolean high) {
        isHigh = high;
    }

    private boolean login(String name,String password){
        return "tom".equals(name) && "123".equals(password);
    }

    private String birth(String birthday){
        return birthday;
    }

    public String hobby(String hobby){
        return "my hobby is "+ hobby;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", classname='" + classname + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", score=" + score +
                ", sex='" + sex + '\'' +
                ", isHigh=" + isHigh +
                '}';
    }
}
