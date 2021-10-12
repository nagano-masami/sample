package com.sample.domain.user;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

public class UserList {
    private final List<User> users;

    public UserList() {
        this.users = new ArrayList<User>();
    }

    public UserList(List<User> users) {
        this.users = users;
    }

    public void add(User user) {
        this.users.add(user);
    }

    public Optional<User> findByUserId(UserId userId) {
       return this.users.stream().filter(user -> user.isSameId(userId)).findFirst();
    }
    public UserList getNearestUserByAge(Long age) {
        return new UserList(
                this.users.stream()
                        .sorted(Comparator.comparing(user -> abs(user.getAge() - age)))
                        .collect(Collectors.toList())
        );
    }

    public UserList limit(Long limit) {
        return new UserList(this.users.stream().limit(limit).collect(Collectors.toList()));
    }

    public List<User> getUsers() {
        return users;
    }

    public UserList exclude(User user) {
        return new UserList(
                this.users.stream().filter(userInList -> !userInList.equals(user)).collect(Collectors.toList())
        );
    }

    public int size() {
        return this.users.size();
    }
}