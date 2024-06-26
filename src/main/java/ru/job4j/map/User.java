package ru.job4j.map;

import java.util.*;

public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>(16);
        Calendar birthdayDate = new GregorianCalendar(1987, 2, 15);
        User user1 = new User("Alex", 2, birthdayDate);
        User user2 = new User("Alex", 2, birthdayDate);
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.print(map);
    }
}
