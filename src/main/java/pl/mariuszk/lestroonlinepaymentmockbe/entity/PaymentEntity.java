package pl.mariuszk.lestroonlinepaymentmockbe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "payments")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "payment_uuid", nullable = false, unique = true)
    private String paymentUUID;

    @Column(name = "order_number", nullable = false, unique = true)
    private Long orderNumber;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
}
