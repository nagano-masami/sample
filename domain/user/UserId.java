package com.sample.domain.user;

import java.util.Objects;

public class UserId {
    public final String value;

    public UserId(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId = (UserId) o;
        return value.equals(userId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}