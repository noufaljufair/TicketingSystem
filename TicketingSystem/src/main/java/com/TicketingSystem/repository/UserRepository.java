package com.TicketingSystem.repository;

import com.TicketingSystem.model.User;
import com.TicketingSystem.model.enums.Gender;
import com.TicketingSystem.model.enums.TicketStatus;
import com.TicketingSystem.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update User user set user.firstName= ?1, user.lastName =?2, user.gender = ?3, user.avatar = ?4 where user.id = ?5")
    void updateUser(String firstName, String lastName, Gender gender, int avatar, long  id);

    @Modifying
    @Query(value = "update users set password =:password  where id =:userId", nativeQuery = true)
    @javax.transaction.Transactional
    void changePassword(@Param("userId") Long userId, @Param("password") String password);

}
