package com.loopers.domain.like;

import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 단위 테스트용 인메모리 LikeRepository. (userId, productId) 쌍을 키로 사용한다.
 */
public class FakeLikeRepository implements LikeRepository {

    private final Map<String, Like> store = new LinkedHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    private String key(Long userId, Long productId) {
        return userId + ":" + productId;
    }

    @Override
    public Like save(Like like) {
        if (like.getId() == null || like.getId() == 0L) {
            ReflectionTestUtils.setField(like, "id", sequence.incrementAndGet());
        }
        store.put(key(like.getUserId(), like.getProductId()), like);
        return like;
    }

    @Override
    public boolean existsBy(Long userId, Long productId) {
        return store.containsKey(key(userId, productId));
    }

    @Override
    public void deleteBy(Long userId, Long productId) {
        store.remove(key(userId, productId));
    }

    @Override
    public long countByProductId(Long productId) {
        return store.values().stream()
            .filter(like -> like.getProductId().equals(productId))
            .count();
    }

    @Override
    public List<Long> findProductIdsByUserId(Long userId) {
        List<Long> result = new ArrayList<>();
        for (Like like : store.values()) {
            if (like.getUserId().equals(userId)) {
                result.add(like.getProductId());
            }
        }
        return result;
    }
}
