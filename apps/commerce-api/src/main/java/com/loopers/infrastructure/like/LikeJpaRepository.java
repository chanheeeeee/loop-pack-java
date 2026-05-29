package com.loopers.infrastructure.like;

import com.loopers.domain.like.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeJpaRepository extends JpaRepository<Like, Long> {
    boolean existsByUserIdAndProductId(Long userId, Long productId);

    void deleteByUserIdAndProductId(Long userId, Long productId);

    long countByProductId(Long productId);

    @Query("select l.productId from Like l where l.userId = :userId")
    List<Long> findProductIdsByUserId(@Param("userId") Long userId);
}
