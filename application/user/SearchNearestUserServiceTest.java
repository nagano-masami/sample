package com.sample.application.user;

import com.sample.application.user.SearchNearestUserService;
import com.sample.domain.user.IUserRepository;
import com.sample.domain.user.User;
import com.sample.domain.user.UserId;
import com.sample.domain.user.UserList;
import com.sample.repositories.inMemory.user.InMemoryUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SearchNearestUserServiceTest {
    @Test
    void findNearestUser() {
        IUserRepository userRepository = new InMemoryUserRepository();
        SearchNearestUserService service = new SearchNearestUserService(userRepository);

        UserId userId = new UserId("257cf7d5-fb26-ab9d-e348-b4c3170a4099");
        long limit = 5;

        UserList results = service.findNearestUser(userId, limit);
        Assertions.assertEquals(limit, results.size());

        User currentUser = userRepository.find(userId).orElseThrow();

        // Check if the results are ordered by age difference from current user
        long currentAgeDiff = 0;
        for (User user : results.getUsers()) {
            long ageDiff = Math.abs(user.getAge() - currentUser.getAge());
            Assertions.assertTrue(ageDiff >= currentAgeDiff);
            currentAgeDiff = ageDiff;
        }

        Assertions.assertTrue(results.findByUserId(userId).isEmpty());
    }
}