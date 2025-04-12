public class Student {
    private String id;
    private String name;
    private int age;
    private String course;

    public Student(String id, String name, int age, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCourse() { return course; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setCourse(String course) { this.course = course; }

    @Override
    public String toString() {
        return id + "," + name + "," + age + "," + course;
    }

    public static Student fromString(String data) {
        String[] parts = data.split(",");
        return new Student(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3]);
    }
}
