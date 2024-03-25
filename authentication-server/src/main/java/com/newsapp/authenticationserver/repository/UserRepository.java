package com.newsapp.authenticationserver.repository;

import com.newsapp.authenticationserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    @Query(
            value = "select * from users where user_name=?1 and password=?2",
            nativeQuery = true
    )
    public User validateUser(String username, String password);

}
