package com.bazlur.meetup.extended.dao;

import com.bazlur.meetup.extended.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/31/17.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByGithub(String github);
}
