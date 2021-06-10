package com.example.TicketingSystem.Repository;

import com.example.TicketingSystem.model.User;
import com.example.TicketingSystem.model.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User ,Long> {


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
