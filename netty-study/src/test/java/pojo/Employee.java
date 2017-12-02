package pojo;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2017/11/1
 */
public class Employee {
    private int age;
    private boolean male;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "age=" + age +
                ", male=" + male +
                ", name='" + name + '\'' +
                '}';
    }
}
