import java.util.Date;

public class Person {
    private Integer id;
    private String name;
    private Date birth;

    // 无参数的构造器
    public Person() {
    }

    // 初始化全部成员变量的构造器
    public Person(Integer id, String name, Date birth) {
        this.id = id;
        this.name = name;
        this.birth = birth;
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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
