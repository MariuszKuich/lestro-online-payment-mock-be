package pl.mariuszk.lestroonlinepaymentmockbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mariuszk.lestroonlinepaymentmockbe.entity.PaymentsEntity;

public interface PaymentsRepository extends JpaRepository<PaymentsEntity, Long> {
}
