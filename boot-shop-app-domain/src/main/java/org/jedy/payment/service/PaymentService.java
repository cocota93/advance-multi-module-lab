package org.jedy.payment.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jedy.payment.domain.PaymentType;
import org.jedy.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public void pay(PaymentType paymentType, Long payPrice){
        //..something process
    }
}

