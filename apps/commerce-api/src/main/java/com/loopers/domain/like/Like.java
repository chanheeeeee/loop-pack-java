package com.loopers.domain.like;

import com.loopers.domain.BaseEntity;
import com.loopers.support.error.CoreException;
import com.loopers.support.error.ErrorType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 좋아요 엔티티. (유저, 상품) 한 쌍의 관계를 독립된 도메인으로 표현한다.
 * 같은 (유저, 상품) 쌍은 1건만 존재할 수 있다.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
    name = "product_like",
    uniqueConstraints = @UniqueConstraint(name = "uk_like_user_product", columnNames = {"user_id", "product_id"})
)
public class Like extends BaseEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Builder(access = AccessLevel.PRIVATE)
    private Like(Long userId, Long productId) {
        if (userId == null) {
            throw new CoreException(ErrorType.BAD_REQUEST, "좋아요에는 유저 정보가 필요합니다.");
        }
        if (productId == null) {
            throw new CoreException(ErrorType.BAD_REQUEST, "좋아요에는 상품 정보가 필요합니다.");
        }
        this.userId = userId;
        this.productId = productId;
    }

    public static Like of(Long userId, Long productId) {
        return Like.builder()
            .userId(userId)
            .productId(productId)
            .build();
    }
}
