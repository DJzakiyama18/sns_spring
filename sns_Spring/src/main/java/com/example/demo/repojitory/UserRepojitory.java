package com.example.demo.repojitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserEntity;

@Repository
public interface UserRepojitory extends JpaRepository<UserEntity, Integer> {


	@Query("SELECT u From UserEntity u WHERE u.user_name=:user_name and u.password = :password")
    UserEntity loginSelect(@Param("user_name") String user_name,@Param("password") String password) ;

	@Query("select u From UserEntity u where u.user_name=:user_name")
	UserEntity userName(@Param("user_name") String user_name);
}
