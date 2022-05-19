package api.Pojo;

import java.util.Objects;

public class User implements Comparable<User>{
    private Integer id;
    private String firstName;
    private String secondName;
    private Integer age;
    private Double money;
    private String sex;
    public User() {
    }

    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getSecondName() {
        return secondName;
    }

    public User setSecondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public User setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Double getMoney() {
        return money;
    }

    public User setMoney(Double money) {
        this.money = money;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public User setSex(String sex) {
        this.sex = sex;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) && Objects.equals(secondName, user.secondName) && Objects.equals(age, user.age) && Objects.equals(money, user.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName, age, money);
    }

    @Override
    public int compareTo(User o) {
        return this.getId() - o.getId();
    }
}

