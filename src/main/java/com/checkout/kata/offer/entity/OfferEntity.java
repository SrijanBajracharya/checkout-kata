package com.checkout.kata.offer.entity;

import com.checkout.kata.item.entity.ItemEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "T_OFFER")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class OfferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "item_id")
    private ItemEntity item;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "discount_price", nullable = false)
    private BigDecimal discountPrice;


}
