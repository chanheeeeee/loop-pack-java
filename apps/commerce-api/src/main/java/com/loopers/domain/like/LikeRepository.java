package com.loopers.domain.like;

import java.util.List;

public interface LikeRepository {
    Like save(Like like);

    boolean existsBy(Long userId, Long productId);

    void deleteBy(Long userId, Long productId);

    long countByProductId(Long productId);

    List<Long> findProductIdsByUserId(Long userId);
}
