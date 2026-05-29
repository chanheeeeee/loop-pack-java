package com.loopers.domain.order;

/**
 * 주문 상태. 생성 시 CREATED 로 시작하며, 결제 결과에 따라 PAID / FAILED 로 전이된다.
 */
public enum OrderStatus {
    CREATED,
    PAID,
    FAILED
}
