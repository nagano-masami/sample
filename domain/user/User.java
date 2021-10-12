package com.sample.domain.user;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
public class User {
   private final UserId id;
   private final String firstName;
    private final String surname;
    private final LocalDate birthDate;

    public User(UserId id, String firstName, String surname, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.birthDate = birthDate;
    }

    public boolean isSameId(UserId userId) {
        return userId.equals(this.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getAge() {
        return ChronoUnit.YEARS.between(this.birthDate, LocalDate.now());
    }
}
