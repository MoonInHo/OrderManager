package com.mooninho.ordermanager.ownerapp.orderhistory.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[유닛 테스트] - 취소 사유 도메인")
class CancelReasonTest {

    @Test
    @DisplayName("주문 취소 사유 입력 - 올바른 취소 사유 입력시 취소 사유 객체 생성")
    void properCancelReasonFormat_createCancelReasonObject() {
        //given & when
        CancelReason cancelReason = CancelReason.of("단순 변심으로 인한 주문 취소");

        //then
        assertThat(cancelReason).isInstanceOf(CancelReason.class);
        assertThat(cancelReason.getCancelReason()).isEqualTo("단순 변심으로 인한 주문 취소");
    }
}