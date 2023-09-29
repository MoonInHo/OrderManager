package com.mooninho.ordermanager.driverapp.delivery.application.service;

import com.mooninho.ordermanager.driverapp.delivery.domain.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

//    @Transactional
//    public void createDeliveryInfo(Long orderId, Long storeId, DeliveryRequestDto requestDeliveryDto) {

//        String inputCompanyName = requestDeliveryDto.getCompanyName();
//        Integer deliveryTips = requestDeliveryDto.getDeliveryTips();
//
//        Company companyName = findByCompanyName(inputCompanyName);
//
//        OrderTypeResponseDto orderTypes = orderQueryRepository.findOrderTypes(orderId, storeId);
//        orderTypeValidation(orderTypes);
//
//        Long deliveryCompanyId = orderQueryRepository.findDeliveryCompanyIdByCompanyName(companyName);
//        Delivery delivery = DeliveryDto.toEntity(orderId, deliveryCompanyId, deliveryTips);
//
//        deliveryRepository.save(delivery);
//    }

//    @Transactional
//    public List<DeliveryTrackingResponseDto> lookupInProgressDelivery(Long storeId) {
//
//        List<DeliveryTrackingResponseDto> fetchedInProgressDeliveryList = orderQueryRepository.findDeliveryByDeliveryState(storeId);
//        if (isEmptyInProgressDelivery(fetchedInProgressDeliveryList)) {
//            throw new EmptyDeliveryListException();
//        }
//        return fetchedInProgressDeliveryList;
//    }

//    @Transactional
//    public List<DeliveryDetailResponseDto> deliveryDetailLookup(Long storeId, Long deliveryId) {
//
//        List<DeliveryDetailResponseDto> deliveryInfo = orderQueryRepository.findDeliveryDetail(storeId, deliveryId);
//        if (isEmptyDeliveryDetail(deliveryInfo)) {
//            throw new EmptyDeliveryException();
//        }
//        return deliveryInfo;
//    }
}
