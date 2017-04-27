class Creature {

    {
        System.out.println("Creature的非静态初始化块");
    }

    static {
        System.out.println("我是最先运行的");
    }

    public Creature() {
        System.out.println("Creature无参数的构造器");
    }

    public Creature(String name) {
        //this();
        System.out.println("Creature带有name参数的构造器，name参数：" + name);
    }
}


class Animal extends Creature {
    String name;

    public void printName() {
        System.out.println("Animal.name = " + name);
    }

    {
        System.out.println("Animal的非静态初始化块");
    }

    static {
        System.out.println("我是第二个运行的");
    }

    public Animal() {
        System.out.println("Animal的无参构造器");
    }

    public Animal(String name) {
        //super(name);
        this.name = name;
        printName();
        System.out.println("Animal带一个参数的构造器，name参数：" + this.name);
    }

    public Animal(String name, int age) {
        //this(name);
        System.out.println("Animal带两个参数的构造器，其age：" + age);
    }
}

class Wolf extends Animal {
    //String name;

    {
        System.out.println("Wolf的非静态初始化块");
    }

    static {
        System.out.println("我是第三个运行的");
    }

    public void printName() {
        System.out.println("Wolf.name = " + name);
    }

    public Wolf(String name) {
        this.name = name;
        //super(name);
        printName();
    }

    public Wolf() {
        //super("灰太狼",3);
        System.out.println("Wolf的无参数的构造器");
    }

    public Wolf(double weight) {
        //this();
        System.out.println("Wolf的带weight参数的构造器，weight参数：" + weight);
    }
}


public class ConstructorTest {
    public static void main(String[] args) {
        //new Wolf(5.6);
        new Wolf("who");
    }
} 