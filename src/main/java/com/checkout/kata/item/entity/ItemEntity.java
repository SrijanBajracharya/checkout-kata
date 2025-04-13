package com.checkout.kata.item.entity;

import com.checkout.kata.offer.entity.OfferEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "T_ITEM")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private OfferEntity offer;
}
