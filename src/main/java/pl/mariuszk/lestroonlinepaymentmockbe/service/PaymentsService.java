package pl.mariuszk.lestroonlinepaymentmockbe.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.mariuszk.lestroonlinepaymentmockbe.entity.PaymentEntity;
import pl.mariuszk.lestroonlinepaymentmockbe.exceptions.PaymentNotFoundException;
import pl.mariuszk.lestroonlinepaymentmockbe.exceptions.TransferFailedException;
import pl.mariuszk.lestroonlinepaymentmockbe.model.PaymentDataDto;
import pl.mariuszk.lestroonlinepaymentmockbe.repository.PaymentsRepository;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentsService {

    private final PaymentsRepository paymentsRepository;

    @Value("${mock-payment.shopPaymentUrl}")
    private String shopPaymentUrl;

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

    public void confirmTransfer(String paymentUUID) {
        PaymentEntity paymentEntity =
                paymentsRepository.findByPaymentUUIDAndPaidIsFalse(paymentUUID).orElseThrow(PaymentNotFoundException::new);
        try {
            sendTransferConfirmationToTheShop(paymentEntity.getOrderNumber());
            updatePaymentEntityAsPaid(paymentEntity);
        } catch (Exception e) {
            log.error("Transfer confirmation failed for order {}", paymentEntity.getOrderNumber());
            throw new TransferFailedException();
        }
    }

    private void sendTransferConfirmationToTheShop(long orderNumber) {
        RestTemplate restTemplate = new RestTemplate();
        String paymentUrl = String.format("%s/%s", shopPaymentUrl, orderNumber);
        restTemplate.postForObject(URI.create(paymentUrl), null, Void.class);
    }

    private void updatePaymentEntityAsPaid(PaymentEntity paymentEntity) {
        paymentEntity.setPaid(true);
        paymentsRepository.save(paymentEntity);
    }
}
