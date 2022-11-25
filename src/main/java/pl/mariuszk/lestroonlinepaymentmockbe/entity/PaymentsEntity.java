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
public class PaymentsEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "order_number", nullable = false)
    private String orderNumber;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
}
