package com.jaewon.jpa.repository;

import com.jaewon.jpa.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
