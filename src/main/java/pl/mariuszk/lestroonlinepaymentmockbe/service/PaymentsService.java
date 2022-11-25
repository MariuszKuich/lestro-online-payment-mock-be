package pl.mariuszk.lestroonlinepaymentmockbe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mariuszk.lestroonlinepaymentmockbe.entity.PaymentEntity;
import pl.mariuszk.lestroonlinepaymentmockbe.model.PaymentDataDto;
import pl.mariuszk.lestroonlinepaymentmockbe.repository.PaymentsRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentsService {

    private final PaymentsRepository paymentsRepository;

    public PaymentEntity saveNewPayment(PaymentDataDto paymentDataDto) {
        PaymentEntity paymentEntity = PaymentEntity.builder()
                .paymentUUID(UUID.randomUUID().toString())
                .orderNumber(paymentDataDto.getOrderNumber())
                .amount(paymentDataDto.getOrderValue())
                .paid(false)
                .build();

        return paymentsRepository.save(paymentEntity);
    }

    public Optional<PaymentDataDto> getPaymentInfo(String paymentUUID) {
        return paymentsRepository.findByPaymentUUIDAndPaidIsFalse(paymentUUID)
                .map(paymentEntity -> PaymentDataDto.builder()
                        .orderNumber(paymentEntity.getOrderNumber())
                        .orderValue(paymentEntity.getAmount())
                        .build());
    }
}
