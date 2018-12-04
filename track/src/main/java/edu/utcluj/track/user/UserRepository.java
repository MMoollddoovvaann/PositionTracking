package edu.utcluj.track.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Maria on 11.11.2018.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
