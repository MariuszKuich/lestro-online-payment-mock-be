package pl.mariuszk.lestroonlinepaymentmockbe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mariuszk.lestroonlinepaymentmockbe.entity.PaymentEntity;
import pl.mariuszk.lestroonlinepaymentmockbe.model.PaymentDataDto;
import pl.mariuszk.lestroonlinepaymentmockbe.service.PaymentsService;

@RestController
@RequestMapping("/payment-mock")
@RequiredArgsConstructor
public class PaymentsController {

    private final PaymentsService paymentsService;

    @Value("${mock-payment.redirectUrl}")
    private String redirectUrl;

    @PostMapping("/new")
    public ResponseEntity<String> saveNewPaymentInfo(@RequestBody PaymentDataDto paymentDataDto) {
        PaymentEntity savedPayment = paymentsService.saveNewPayment(paymentDataDto);

        return ResponseEntity.ok(String.format("%s/%s", redirectUrl, savedPayment.getPaymentUUID()));
    }

    @GetMapping("/{paymentUUID}")
    public ResponseEntity<PaymentDataDto> getPaymentInfo(@PathVariable String paymentUUID) {
        return paymentsService.getPaymentInfo(paymentUUID)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/confirm-transfer/{paymentUUID}")
    public ResponseEntity<Void> confirmTransfer(@PathVariable String paymentUUID) {
        try {
            paymentsService.confirmTransfer(paymentUUID);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
