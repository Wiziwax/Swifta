package com.rapid.swifta.Repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CommentRepository{
//        extends JpaRepository<Comments, Integer> {
}
