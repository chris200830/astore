package com.rx.powerstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.rx.powerstore.entity.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long>{

}
