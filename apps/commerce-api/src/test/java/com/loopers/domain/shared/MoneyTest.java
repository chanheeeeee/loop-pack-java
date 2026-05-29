package com.loopers.domain.shared;

import com.loopers.support.error.CoreException;
import com.loopers.support.error.ErrorType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MoneyTest {

    @DisplayName("Money 를 생성할 때, ")
    @Nested
    class Create {

        @DisplayName("금액이 음수이면, BAD_REQUEST 예외가 발생한다.")
        @Test
        void throwsBadRequest_whenAmountIsNegative() {
            // act
            CoreException result = assertThrows(CoreException.class, () -> Money.of(-1L));

            // assert
            assertThat(result.getErrorType()).isEqualTo(ErrorType.BAD_REQUEST);
        }

        @DisplayName("금액이 null 이면, BAD_REQUEST 예외가 발생한다.")
        @Test
        void throwsBadRequest_whenAmountIsNull() {
            // act
            CoreException result = assertThrows(CoreException.class, () -> Money.of((BigDecimal) null));

            // assert
            assertThat(result.getErrorType()).isEqualTo(ErrorType.BAD_REQUEST);
        }

        @DisplayName("금액이 0 이면, 정상 생성된다.")
        @Test
        void createsZeroMoney() {
            // act
            Money money = Money.of(0L);

            // assert
            assertThat(money.amount()).isEqualByComparingTo(BigDecimal.ZERO);
        }
    }

    @DisplayName("Money 연산 시, ")
    @Nested
    class Operate {

        @DisplayName("plus 는 두 금액의 합을 반환한다.")
        @Test
        void plus_returnsSum() {
            // arrange
            Money a = Money.of(1_000L);
            Money b = Money.of(2_500L);

            // act
            Money result = a.plus(b);

            // assert
            assertThat(result).isEqualTo(Money.of(3_500L));
        }

        @DisplayName("multiply 는 수량을 곱한 금액을 반환한다.")
        @Test
        void multiply_returnsProduct() {
            // arrange
            Money unitPrice = Money.of(1_500L);

            // act
            Money result = unitPrice.multiply(3);

            // assert
            assertThat(result).isEqualTo(Money.of(4_500L));
        }
    }
}
