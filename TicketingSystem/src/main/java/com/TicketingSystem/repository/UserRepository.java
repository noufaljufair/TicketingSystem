package com.TicketingSystem.repository;

import com.TicketingSystem.model.User;
import com.TicketingSystem.model.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {


    //User findByid(Long id);
    List<User> findByType(UserType type);

 /*
    @Modifying
    @Query(value = "update users set password =:password  where id =:userId", nativeQuery = true)
    @Transactional
    void changePassword(@Param("userId") Long userId, @Param("password") String password);

    @Modifying
    @Query(value = "update users set password =:password, active =:active  where id =:userId", nativeQuery = true)
    @Transactional
    void editUser(@Param("userId") Long userId, @Param("password") String password,@Param("active") boolean active);
*/



}
