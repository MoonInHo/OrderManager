package com.mooninho.ordermanager.ownerapp.order.application.service;

import com.mooninho.ordermanager.ownerapp.delivery.application.dto.request.CreateDeliveryRequestDto;
import com.mooninho.ordermanager.ownerapp.delivery.domain.enums.DeliveryStatus;
import com.mooninho.ordermanager.ownerapp.delivery.domain.repository.DeliveryRepository;
import com.mooninho.ordermanager.ownerapp.delivery.infrastructure.dto.response.GetInProgressDeliveryOrdersResponseDto;
import com.mooninho.ordermanager.ownerapp.exception.exception.delivery.DeliveryCompanyNotFoundException;
import com.mooninho.ordermanager.ownerapp.exception.exception.delivery.DeliveryOrderNotFoundException;
import com.mooninho.ordermanager.ownerapp.exception.exception.global.InvalidRequestException;
import com.mooninho.ordermanager.ownerapp.exception.exception.global.UnauthorizedException;
import com.mooninho.ordermanager.ownerapp.member.domain.repository.MemberRepository;
import com.mooninho.ordermanager.ownerapp.order.application.strategy.BaeminOneOrderCancelationStrategy;
import com.mooninho.ordermanager.ownerapp.order.application.strategy.BaeminOrderCancellationStrategy;
import com.mooninho.ordermanager.ownerapp.order.application.strategy.OrderCancelStrategyFactory;
import com.mooninho.ordermanager.ownerapp.order.domain.enums.OrderStatus;
import com.mooninho.ordermanager.ownerapp.order.domain.enums.OrderType;
import com.mooninho.ordermanager.ownerapp.order.domain.enums.PaymentType;
import com.mooninho.ordermanager.ownerapp.order.domain.repository.OrderRepository;
import com.mooninho.ordermanager.ownerapp.order.infrastructure.dto.response.*;
import com.mooninho.ordermanager.ownerapp.orderhistory.application.dto.request.CreateOrderCancelHistoryRequestDto;
import com.mooninho.ordermanager.ownerapp.store.domain.repository.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("[유닛 테스트] - 주문 서비스")
class OrderServiceTest {

    private CreateOrderCancelHistoryRequestDto createOrderCancelHistoryRequestDto;

    private CreateDeliveryRequestDto createDeliveryRequestDto;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Mock
    private OrderCancelStrategyFactory orderCancelStrategyFactory;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setCreateOrderCancelHistoryRequestDto() {
        createOrderCancelHistoryRequestDto = new CreateOrderCancelHistoryRequestDto("재고 소진으로 인한 주문 취소");
    }

    @BeforeEach
    void setCreateDeliveryRequestDto() {
        createDeliveryRequestDto = new CreateDeliveryRequestDto(1L, 3000);
    }

    @Test
    @DisplayName("대기중 주문 조회 - 가게의 주인이 아닌 사용자가 대기중 주문 목록 조회시 예외 발생")
    void notOwnerOfStore_getWaitingOrders_throwException() {
        //given
        given(storeRepository.isOwner(any(), any())).willReturn(false);

        //when
        Throwable throwable = catchThrowable(() -> orderService.getWaitingOrders(1L, "test123"));

        //then
        assertThat(throwable).isInstanceOf(UnauthorizedException.class);
        assertThat(throwable).hasMessage("접근 권한이 없습니다.");
    }

    @Test
    @DisplayName("대기중 주문 조회 - 가게의 주인이 대기중 주문 목록 조회시 대기중 주문 목록 반환")
    void ownerOfStore_getWaitingOrders_returnWaitingOrderList() {
        //given
        List<String> menuNames = new ArrayList<>();
        menuNames.add("알리오올리오");
        menuNames.add("로제 파스타");

        GetWaitingOrderResponseDto getWaitingOrderResponseDto = new GetWaitingOrderResponseDto(
                1L,
                "23.05.26",
                "15,000",
                menuNames,
                "임시 주소",
                "010-1234-5678",
                "수저포크x",
                OrderType.DELIVERY,
                PaymentType.CARD
        );

        given(storeRepository.isOwner(any(), any())).willReturn(true);
        given(orderRepository.getWaitingOrders(1L)).willReturn(List.of(getWaitingOrderResponseDto));

        //when
        List<GetWaitingOrderResponseDto> orderList = orderService.getWaitingOrders(1L, "test123");

        //then
        assertThat(orderList).isNotEmpty();
        assertThat(orderList.get(0).getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("준비중 주문 조회 - 가게의 주인이 아닌 사용자가 준비중 주문 목록 조회시 예외 발생")
    void notOwnerOfStore_getPreparingOrders_throwException() {
        //given
        given(storeRepository.isOwner(any(), any())).willReturn(false);

        //when
        Throwable throwable = catchThrowable(() -> orderService.getPreparingOrders(1L, "test123"));

        //then
        assertThat(throwable).isInstanceOf(UnauthorizedException.class);
        assertThat(throwable).hasMessage("접근 권한이 없습니다.");
    }

    @Test
    @DisplayName("준비중 주문 조회 - 가게의 주인이 준비중 주문 목록 조회시 준비중 주문 목록 반환")
    void ownerOfStore_getPreparingOrders_returnPreparingOrderList() {
        //given
        List<String> menuNames = new ArrayList<>();
        menuNames.add("알리오올리오");
        menuNames.add("로제 파스타");

        GetPreparingOrderResponseDto getPreparingOrderResponseDto = new GetPreparingOrderResponseDto(
                1L,
                "23.05.26",
                "15,000",
                menuNames,
                "임시 주소",
                "임시 상세 주소",
                "010-1234-5678",
                "수저포크x",
                OrderType.DELIVERY,
                PaymentType.CARD
        );

        given(storeRepository.isOwner(any(), any())).willReturn(true);
        given(orderRepository.getPreparingOrders(1L)).willReturn(List.of(getPreparingOrderResponseDto));

        //when
        List<GetPreparingOrderResponseDto> orderList = orderService.getPreparingOrders(1L, "test123");

        //then
        assertThat(orderList).isNotEmpty();
        assertThat(orderList.get(0).getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("완료 주문 조회 - 가게의 주인이 아닌 사용자가 완료 주문 목록 조회시 예외 발생")
    void notOwnerOfStore_getCompletedOrders_throwException() {
        //given
        given(storeRepository.isOwner(any(), any())).willReturn(false);

        //when
        Throwable throwable = catchThrowable(() -> orderService.getCompletedOrders(1L, "test123"));

        //then
        assertThat(throwable).isInstanceOf(UnauthorizedException.class);
        assertThat(throwable).hasMessage("접근 권한이 없습니다.");
    }

    @Test
    @DisplayName("완료 주문 조회 - 가게의 주인이 완료 주문 목록 조회시 완료 주문 목록 반환")
    void ownerOfStore_getCompletedOrders_returnCompletedOrderList() {
        //given
        List<String> menuNames = new ArrayList<>();
        menuNames.add("알리오올리오");
        menuNames.add("로제 파스타");

        GetCompletedOrderResponseDto getCompletedOrderResponseDto = new GetCompletedOrderResponseDto(
                1L,
                "23.05.26",
                "15,000",
                menuNames,
                "임시 주소",
                OrderType.DELIVERY,
                PaymentType.CARD
        );

        given(storeRepository.isOwner(any(), any())).willReturn(true);
        given(orderRepository.getCompleteOrders(1L)).willReturn(List.of(getCompletedOrderResponseDto));

        //when
        List<GetCompletedOrderResponseDto> orderList = orderService.getCompletedOrders(1L, "test123");

        //then
        assertThat(orderList).isNotEmpty();
        assertThat(orderList.get(0).getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("주문 상세 조회 - 가게의 주인이 아닌 사용자가 주문 상세 조회시 예외 발생")
    void notOwnerOfStore_getOrderDetail_throwException() {
        //given
        given(storeRepository.isOwner(any(), any())).willReturn(false);

        //when
        Throwable throwable = catchThrowable(() -> orderService.getOrderDetail(1L, 1L, "test123"));

        //then
        assertThat(throwable).isInstanceOf(UnauthorizedException.class);
        assertThat(throwable).hasMessage("접근 권한이 없습니다.");
    }

    @Test
    @DisplayName("주문 상세 조회 - 가게의 주인이 주문 상세 조회시 주문 상세 반환")
    void ownerOfStore_getOrderDetail_returnOrderDetail() {
        //given
        GetOrderDetailResponseDto getOrderDetailResponseDto = new GetOrderDetailResponseDto(
                1L,
                "23.05.25",
                "15,000",
                "테스트 주소",
                "테스트 상세 주소",
                "010-1234-5678",
                "수저포크x",
                OrderType.DELIVERY,
                PaymentType.CARD,
                OrderStatus.PREPARING
        );

        given(storeRepository.isOwner(any(), any())).willReturn(true);
        given(orderRepository.getOrderDetail(1L)).willReturn(Optional.of(getOrderDetailResponseDto));

        //when
        GetOrderDetailResponseDto orderDetail = orderService.getOrderDetail(1L, 1L, "test123");

        //then
        assertThat(orderDetail).isInstanceOf(GetOrderDetailResponseDto.class);
        assertThat(orderDetail.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("주문 수락 - 가게의 주인이 아닌 사용자가 주문 수락시 예외 발생")
    void notOwnerOfStore_acceptOrder_throwException() {
        //given
        given(storeRepository.isOwner(any(), any())).willReturn(false);

        // when
        Throwable throwable = catchThrowable(
                () -> orderService.changeOrderToPreparing(1L, 1L, "user123")
        );

        //then
        assertThat(throwable).isInstanceOf(UnauthorizedException.class);
        assertThat(throwable).hasMessage("접근 권한이 없습니다.");
    }

    @ParameterizedTest
    @EnumSource(value = OrderStatus.class, names = {"PREPARING", "READY", "COMPLETE", "CANCEL"})
    @DisplayName("주문 수락 - 주문이 대기중이 아닐때 주문 수락시 예외 발생")
    void orderStatusIsNotWaiting_acceptOrder_throwException(OrderStatus orderStatus) {
        //given
        given(storeRepository.isOwner(any(), any())).willReturn(true);
        given(orderRepository.getOrderStatus(1L)).willReturn(Optional.of(orderStatus));

        //when
        Throwable throwable = catchThrowable(
                () -> orderService.changeOrderToPreparing(1L, 1L, "user123")
        );

        //then
        assertThat(throwable).isInstanceOf(InvalidRequestException.class);
        assertThat(throwable).hasMessage("잘못된 요청입니다.");
    }

    @Test
    @DisplayName("주문 수락 - 가게의 주인이 주문 수락시 주문 상태 준비중으로 변경")
    void ownerOfStore_acceptOrder_updateOrderStatusToPreparing() {
        //given
        given(storeRepository.isOwner(any(), any())).willReturn(true);
        given(orderRepository.getOrderStatus(1L)).willReturn(Optional.of(OrderStatus.WAITING));

        //when
        orderService.changeOrderToPreparing(1L, 1L, "user123");

        //then
        verify(orderRepository, times(1)).changeOrderToPreparing(1L);
    }

    @Test
    @DisplayName("주문 준비 완료 - 가게의 주인이 아닌 사용자가 주문 준비 완료시 예외 발생")
    void notOwnerOfStore_readyOrder_throwException() {
        //given
        given(storeRepository.isOwner(any(), any())).willReturn(false);

        // when
        Throwable throwable = catchThrowable(
                () -> orderService.changeOrderToReady(1L, 1L, "user123")
        );

        //then
        assertThat(throwable).isInstanceOf(UnauthorizedException.class);
        assertThat(throwable).hasMessage("접근 권한이 없습니다.");
    }

    @ParameterizedTest
    @EnumSource(value = OrderStatus.class, names = {"WAITING", "READY", "COMPLETE", "CANCEL"})
    @DisplayName("주문 준비 완료 - 주문이 준비중이 아닐때 주문 수락시 예외 발생")
    void orderStatusIsNotPreparing_readyOrder_throwException(OrderStatus orderStatus) {
        //given
        given(storeRepository.isOwner(any(), any())).willReturn(true);
        given(orderRepository.getOrderStatus(1L)).willReturn(Optional.of(orderStatus));

        //when
        Throwable throwable = catchThrowable(
                () -> orderService.changeOrderToReady(1L, 1L, "user123")
        );

        //then
        assertThat(throwable).isInstanceOf(InvalidRequestException.class);
        assertThat(throwable).hasMessage("잘못된 요청입니다.");
    }

    @Test
    @DisplayName("주문 준비 완료 - 가게의 주인이 주문 수락시 주문 상태 준비완료로 변경")
    void ownerOfStore_readyOrder_updateOrderStatusToReady() {
        //given
        given(storeRepository.isOwner(any(), any())).willReturn(true);
        given(orderRepository.getOrderStatus(1L)).willReturn(Optional.of(OrderStatus.PREPARING));

        //when
        orderService.changeOrderToReady(1L, 1L, "user123");

        //then
        verify(orderRepository, times(1)).changeOrderToReady(1L);
    }

    @Test
    @DisplayName("주문 취소 - 가게의 주인이 아닌 사용자가 주문 취소시 예외 발생")
    void notOwnerOfStore_cancelOrder_throwException() {
        //given
        given(storeRepository.isOwner(any(), any())).willReturn(false);

        // when
        Throwable throwable = catchThrowable(
                () -> orderService.cancelOrder(1L, 1L, createOrderCancelHistoryRequestDto, "user123")
        );

        //then
        assertThat(throwable).isInstanceOf(UnauthorizedException.class);
        assertThat(throwable).hasMessage("접근 권한이 없습니다.");
    }

    @Test
    @DisplayName("주문 취소 - 주문 취소 상태일 때 주문 취소 시 예외 발생")
    void orderStatusIsCanceled_cancelOrder_throwException() {
        //given
        given(storeRepository.isOwner(any(), any())).willReturn(true);
        given(orderRepository.getOrderStatus(1L)).willReturn(Optional.of(OrderStatus.CANCEL));

        //when
        Throwable throwable = catchThrowable(
                () -> orderService.cancelOrder(1L, 1L, createOrderCancelHistoryRequestDto, "user123")
        );

        //then
        assertThat(throwable).isInstanceOf(InvalidRequestException.class);
        assertThat(throwable).hasMessage("잘못된 요청입니다.");
    }

    @ParameterizedTest
    @EnumSource(value = OrderStatus.class, names = {"WAITING", "PREPARING", "READY", "COMPLETE"})
    @DisplayName("주문 취소 - 배민 기사 배정 상태일 때 주문 취소 시 예외 발생")
    void DeliveryDriverAssignedToBaemin_cancelOrder_throwException(OrderStatus orderStatus) {
        //given
        given(storeRepository.isOwner(any(), any())).willReturn(true);
        given(orderRepository.getOrderStatus(1L)).willReturn(Optional.of(orderStatus));
        given(deliveryRepository.getDeliveryId(1L)).willReturn(1L);
        given(orderCancelStrategyFactory.getStrategy(any())).willReturn(new BaeminOrderCancellationStrategy());

        //when
        Throwable throwable = catchThrowable(
                () -> orderService.cancelOrder(1L, 1L, createOrderCancelHistoryRequestDto, "user123")
        );

        //then
        assertThat(throwable).isInstanceOf(InvalidRequestException.class);
        assertThat(throwable).hasMessage("잘못된 요청입니다.");
    }

    @ParameterizedTest
    @EnumSource(value = OrderStatus.class, names = {"WAITING", "PREPARING", "READY", "COMPLETE"})
    @DisplayName("주문 취소 - 배민원 배달일 경우 주문 취소 시 예외 발생")
    void DeliveryTypeIfBaeminOne_cancelOrder_throwException(OrderStatus orderStatus) {
        //given
        given(storeRepository.isOwner(any(), any())).willReturn(true);
        given(orderRepository.getOrderStatus(1L)).willReturn(Optional.of(orderStatus));
        given(orderCancelStrategyFactory.getStrategy(any())).willReturn(new BaeminOneOrderCancelationStrategy());

        //when
        Throwable throwable = catchThrowable(
                () -> orderService.cancelOrder(1L, 1L, createOrderCancelHistoryRequestDto, "user123")
        );

        //then
        assertThat(throwable).isInstanceOf(InvalidRequestException.class);
        assertThat(throwable).hasMessage("잘못된 요청입니다.");
    }

    @ParameterizedTest
    @EnumSource(value = OrderStatus.class, names = {"WAITING", "PREPARING", "READY", "COMPLETE"})
    @DisplayName("주문 취소 - 기사 배정 전 배민 주문 취소 시 주문 상태 취소로 변경")
    void isNotDeliveryDriverAssignedToBaemin_cancelOrder_updateOrderStatusToCancel(OrderStatus orderStatus) {
        //given
        given(storeRepository.isOwner(any(), any())).willReturn(true);
        given(orderRepository.getOrderStatus(1L)).willReturn(Optional.of(orderStatus));
        given(deliveryRepository.getDeliveryId(1L)).willReturn(null);
        given(orderCancelStrategyFactory.getStrategy(any())).willReturn(new BaeminOrderCancellationStrategy());

        //when
        orderService.cancelOrder(1L, 1L, createOrderCancelHistoryRequestDto, "user123");

        //then
        verify(orderRepository, times(1)).changeOrderToCancel(1L);
        //TODO 비동기 이벤트 발행 여부 테스트 추가
    }

    @Test
    @DisplayName("포장 주문 완료 - 가게의 주인이 아닌 사용자가 포장 주문 완료 처리 시 예외 발생")
    void notOwnerOfStore_completeTogoOrder_throwException() {
        //given
        given(storeRepository.isOwner(any(), any())).willReturn(false);

        // when
        Throwable throwable = catchThrowable(
                () -> orderService.changeTogoOrderToComplete(1L, 1L, "user123")
        );

        //then
        assertThat(throwable).isInstanceOf(UnauthorizedException.class);
        assertThat(throwable).hasMessage("접근 권한이 없습니다.");
    }

    @ParameterizedTest
    @EnumSource(value = OrderStatus.class, names = {"WAITING", "PREPARING", "COMPLETE", "CANCEL"})
    @DisplayName("포장 주문 완료 - 주문이 준비중이 아닐때 주문 완료 처리 시 예외 발생")
    void orderStatusIsNotPreparing_readyTogoOrder_throwException(OrderStatus orderStatus) {
        //given
        given(storeRepository.isOwner(any(), any())).willReturn(true);
        given(orderRepository.getOrderStatus(1L)).willReturn(Optional.of(orderStatus));

        //when
        Throwable throwable = catchThrowable(
                () -> orderService.changeTogoOrderToComplete(1L, 1L, "user123")
        );

        //then
        assertThat(throwable).isInstanceOf(InvalidRequestException.class);
        assertThat(throwable).hasMessage("잘못된 요청입니다.");
    }

    @Test
    @DisplayName("포장 주문 완료 - 가게의 주인이 포장 주문 완료 처리시 주문 상태 완료로 변경")
    void ownerOfStore_readyOrders_updateOrderStatusToReady() {
        //given
        given(storeRepository.isOwner(any(), any())).willReturn(true);
        given(orderRepository.getOrderStatus(1L)).willReturn(Optional.of(OrderStatus.READY));

        //when
        orderService.changeTogoOrderToComplete(1L, 1L, "user123");

        //then
        verify(orderRepository, times(1)).changeOrderToComplete(1L);
    }

    @Test
    @DisplayName("배달 기사 요청 - 가게의 주인이 아닌 사용자가 배달 기사 요청시 시 예외 발생")
    void notOwnerOfStore_requestDeliveryDriver_throwException() {
        //given
        given(storeRepository.isOwner(any(), any())).willReturn(false);

        // when
        Throwable throwable = catchThrowable(
                () -> orderService.createDeliveryRequest(1L, 1L, createDeliveryRequestDto, "test123")
        );

        //then
        assertThat(throwable).isInstanceOf(UnauthorizedException.class);
        assertThat(throwable).hasMessage("접근 권한이 없습니다.");
    }

    @Test
    @DisplayName("배달 기사 요청 - 존재하지 않는 배달 업체로 배달 기사 요청시 예외 발생")
    void nonexistentDeliveryCompany_requestDeliveryDriver_throwException() {
        //given
        given(storeRepository.isOwner(any(), any())).willReturn(true);
        given(deliveryRepository.isCompanyExist(1L)).willReturn(false);

        // when
        Throwable throwable = catchThrowable(
                () -> orderService.createDeliveryRequest(1L, 1L, createDeliveryRequestDto, "test123")
        );

        //then
        assertThat(throwable).isInstanceOf(DeliveryCompanyNotFoundException.class);
        assertThat(throwable).hasMessage("배달 업체를 찾을 수 없습니다.");
    }

    @ParameterizedTest
    @EnumSource(value = OrderStatus.class, names = {"WAITING", "PREPARING", "COMPLETE", "CANCEL"})
    @DisplayName("배달 기사 요청 - 존재하지 않는 배달 업체로 배달 기사 요청시 예외 발생")
    void orderStatusIsNotReady_requestDeliveryDriver_throwException(OrderStatus orderStatus) {
        //given
        given(storeRepository.isOwner(any(), any())).willReturn(true);
        given(deliveryRepository.isCompanyExist(1L)).willReturn(true);
        given(orderRepository.getOrderStatus(1L)).willReturn(Optional.of(orderStatus));

        // when
        Throwable throwable = catchThrowable(
                () -> orderService.createDeliveryRequest(1L, 1L, createDeliveryRequestDto, "test123")
        );

        //then
        assertThat(throwable).isInstanceOf(InvalidRequestException.class);
        assertThat(throwable).hasMessage("잘못된 요청입니다.");
    }

    @Test
    @DisplayName("배달 기사 요청 - 준비 완료된 주문 배달 기사 요청시 배달 정보 생성")
    void orderStatusIsReady_requestDeliveryDriver_createDeliveryInfo() {
        //given
        given(storeRepository.isOwner(any(), any())).willReturn(true);
        given(deliveryRepository.isCompanyExist(1L)).willReturn(true);
        given(orderRepository.getOrderStatus(1L)).willReturn(Optional.of(OrderStatus.READY));

        // when
        orderService.createDeliveryRequest(1L, 1L, createDeliveryRequestDto, "test123");

        //then
        verify(deliveryRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("진행중 배달 목록 조회 - 가게의 주인이 아닌 사용자가 진행중 배달 주문 목록 조회 시 예외 발생")
    void notOwnerOfStore_getInProgressDeliveryOrders_throwException() {
        //given
        given(storeRepository.isOwner(any(), any())).willReturn(false);

        // when
        Throwable throwable = catchThrowable(
                () -> orderService.getInProgressDeliveryOrders(1L, "test123")
        );

        //then
        assertThat(throwable).isInstanceOf(UnauthorizedException.class);
        assertThat(throwable).hasMessage("접근 권한이 없습니다.");
    }

    @Test
    @DisplayName("진행중 배달 목록 조회 - 가게의 주인이 진행중 배달 주문 목록 조회 시 진행중 배달 목록 반환")
    void ownerOfStore_getInProgressDeliveryOrders_returnProgressDeliveryOrderList() {
        //given
        List<GetInProgressDeliveryOrdersResponseDto> progressOrders = new ArrayList<>();

        GetInProgressDeliveryOrdersResponseDto progressDeliveryOrdersResponseDto =
                new GetInProgressDeliveryOrdersResponseDto(
                        1L,
                        "23.05.26",
                        OrderType.DELIVERY,
                        "배달의민족",
                        PaymentType.CARD,
                        "15,000",
                        DeliveryStatus.DISPATCH
        );
        progressOrders.add(progressDeliveryOrdersResponseDto);

        given(storeRepository.isOwner(any(), any())).willReturn(true);
        given(orderRepository.getInProgressDeliveryOrders(1L)).willReturn(progressOrders);

        // when
        List<GetInProgressDeliveryOrdersResponseDto> progressDeliveryOrders =
                orderService.getInProgressDeliveryOrders(1L, "test123");

        //then
        assertThat(progressDeliveryOrders).isNotEmpty();
        assertThat(progressDeliveryOrders.get(0).getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("배달 완료 처리 - 가게의 주인이 아닌 사용자가 배달 완료 처리 시 예외 발생")
    void notOwnerOfStore_handleDeliveryOrderCompletion_throwException() {
        //given
        given(storeRepository.isOwner(any(), any())).willReturn(false);

        // when
        Throwable throwable = catchThrowable(
                () -> orderService.changeDeliveryOrderToComplete(1L, 1L, "test123")
        );

        //then
        assertThat(throwable).isInstanceOf(UnauthorizedException.class);
        assertThat(throwable).hasMessage("접근 권한이 없습니다.");
    }

    @Test
    @DisplayName("배달 주문 완료 처리 - 가게의 주인이 아닌 사용자가 배달 주문 완료 처리 시 예외 발생")
    void deliveryNotCompleted_handleDeliveryOrderCompletion_throwException() {
        //given
        given(storeRepository.isOwner(any(), any())).willReturn(true);
        given(deliveryRepository.isDeliveryCompleted(1L)).willReturn(false);

        // when
        Throwable throwable = catchThrowable(
                () -> orderService.changeDeliveryOrderToComplete(1L, 1L, "test123")
        );

        //then
        assertThat(throwable).isInstanceOf(InvalidRequestException.class);
        assertThat(throwable).hasMessage("잘못된 요청입니다.");
    }

    @Test
    @DisplayName("배달 주문 완료 처리 - 가게의 주인이 배달 주문 완료 처리 주문 완료 상태로 변경")
    void ownerOfStore_handleDeliveryOrderCompletion_updateOrderStatusToCompleted() {
        //given
        given(storeRepository.isOwner(any(), any())).willReturn(true);
        given(deliveryRepository.isDeliveryCompleted(1L)).willReturn(true);

        // when
        orderService.changeDeliveryOrderToComplete(1L, 1L, "test123");

        //then
        verify(orderRepository, times(1)).changeOrderToComplete(1L);
    }

    @Test
    @DisplayName("배달 주문 조회 - 가게의 주인이 아닌 사용자가 배달 주문 조회시 예외 발생")
    void ownerOfStore_getDeliveryOrder_throwException() {
        //given
        given(storeRepository.isOwner(any(), any())).willReturn(false);

        // when
        Throwable throwable = catchThrowable(
                () -> orderService.getDeliveryOrder(1L, 1L, "test123")
        );

        //then
        assertThat(throwable).isInstanceOf(UnauthorizedException.class);
        assertThat(throwable).hasMessage("접근 권한이 없습니다.");
    }

    @Test
    @DisplayName("배달 주문 조회 - 존재하지 않는 배달 주문 조회시 예외 발생")
    void nonexistenceDeliveryOrder_getDeliveryOrder_throwException() {
        //given
        given(storeRepository.isOwner(any(), any())).willReturn(true);
        given(orderRepository.getDeliveryOrder(1L, 1L)).willReturn(Optional.empty());

        // when
        Throwable throwable = catchThrowable(
                () -> orderService.getDeliveryOrder(1L, 1L, "test123")
        );

        //then
        assertThat(throwable).isInstanceOf(DeliveryOrderNotFoundException.class);
        assertThat(throwable).hasMessage("이미 처리중이거나 존재하지 않는 배달 요청입니다.");
    }

    @Test
    @DisplayName("배달 주문 조회 - 가게의 주인이 배달 주문 조회시 배달 주문 반환")
    void ownerOfStore_getDeliveryOrder_returnDeliveryOrder() {
        //given
        GetDeliveryOrderResponseDto getDeliveryOrderResponseDto = new GetDeliveryOrderResponseDto(
                1L,
                DeliveryStatus.DELIVERING,
                "배달의민족",
                "3,000",
                "김배달",
                "010-1234-5678",
                "010-5678-1234",
                "테스트 주소",
                "테스트 상세 주소",
                "23.05.26",
                OrderType.DELIVERY
        );

        given(storeRepository.isOwner(any(), any())).willReturn(true);
        given(orderRepository.getDeliveryOrder(1L, 1L)).willReturn(Optional.of(getDeliveryOrderResponseDto));

        // when
        GetDeliveryOrderResponseDto deliveryOrder = orderService.getDeliveryOrder(1L, 1L, "test123");

        //then
        assertThat(deliveryOrder).isNotNull();
        assertThat(deliveryOrder.getId()).isEqualTo(1L);
    }
}