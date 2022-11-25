package pl.mariuszk.lestroonlinepaymentmockbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mariuszk.lestroonlinepaymentmockbe.entity.PaymentEntity;

import java.util.Optional;

public interface PaymentsRepository extends JpaRepository<PaymentEntity, Long> {

    Optional<PaymentEntity> findByPaymentUUID(String paymentUUID);
}
