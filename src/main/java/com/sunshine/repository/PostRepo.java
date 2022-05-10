package com.sunshine.repository;

import com.sunshine.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface PostRepo extends JpaRepository<Post, Integer> {
    @Query("select p from Post  p where  p.title  like concat('%',:title,'%') or p.description like concat('%',:title,'%') ")
 List<Post> findAllByTitleOrDescriptionContaining(@Param("title") String title);



}
