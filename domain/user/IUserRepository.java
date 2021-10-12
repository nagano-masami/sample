package com.sample.domain.user;

import java.util.Optional;

public interface IUserRepository {
    public Optional<User> find(UserId userId);

    public UserList findNearestUserByAge(Long age, Long limit);
}