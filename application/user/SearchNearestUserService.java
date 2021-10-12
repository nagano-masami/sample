package com.sample.application.user;

import com.sample.domain.user.IUserRepository;
import com.sample.domain.user.User;
import com.sample.domain.user.UserId;
import com.sample.domain.user.UserList;

import java.util.Optional;

public class SearchNearestUserService {
    private final IUserRepository userRepository;
    
    public SearchNearestUserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserList findNearestUser(UserId userId, Long limit) {
        Optional<User> currentUser = this.userRepository.find(userId);
        return currentUser
                .map(user -> this.userRepository
                        .findNearestUserByAge(user.getAge(), limit+1)
                        .exclude(user).limit(limit))
                .orElseGet(UserList::new);
    }
}