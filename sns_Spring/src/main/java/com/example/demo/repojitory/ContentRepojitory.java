package com.example.demo.repojitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ContentEntity;
@Repository
public interface ContentRepojitory extends JpaRepository<ContentEntity, Integer>{
@Query("select c from ContentEntity  c where c.user_name = :user_name")
List<ContentEntity> userContentList(@Param("user_name") String user_name);

@Query("select c from ContentEntity c order by created_at desc")
List<ContentEntity> userContentListOrder();
}
