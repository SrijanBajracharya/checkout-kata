package com.checkout.kata.integration.checkout;

import com.checkout.kata.checkout.domain.dto.request.CheckoutRequest;
import com.checkout.kata.checkout.domain.dto.request.ScannedItemRequest;
import com.checkout.kata.checkout.domain.dto.response.CheckoutItemResponse;
import com.checkout.kata.checkout.domain.dto.response.CheckoutResponse;
import com.checkout.kata.integration.IntegrationBaseTest;
import com.checkout.kata.item.entity.ItemEntity;
import com.checkout.kata.item.persistence.ItemEntityRepository;
import com.checkout.kata.offer.entity.OfferEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
public class CheckoutControllerIT extends IntegrationBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ItemEntityRepository itemEntityRepository;

    @Test
    void checkoutItem_shouldCalculateTotal_whenRequestIsValid() throws Exception {
        //given
        String item1 = "Apple";
        String item2 = "Banana";
        String item3 = "Peach";
        String item4 = "Kiwi";
        ScannedItemRequest scanned1 = buildScannedItemRequest(item1, 1);
        ScannedItemRequest scanned2 = buildScannedItemRequest(item2, 1);
        ScannedItemRequest scanned3 = buildScannedItemRequest(item3, 1);
        ScannedItemRequest scanned4 = buildScannedItemRequest(item4, 1);
        ScannedItemRequest scanned5 = buildScannedItemRequest(item1, 4);

        List<ScannedItemRequest> scannedItems = new ArrayList<>();
        scannedItems.add(scanned1);
        scannedItems.add(scanned2);
        scannedItems.add(scanned3);
        scannedItems.add(scanned4);
        scannedItems.add(scanned5);

        CheckoutRequest request = new CheckoutRequest(scannedItems);

        //when
        List<ItemEntity> entities = itemEntityRepository.findAll();
        assertTrue(entities.isEmpty());

        itemEntityRepository.saveAll(List.of(
                buildItem(1L, item1, 30, 2, 45.0),
                buildItem(2L, item2, 50, 3, 130.0),
                buildItem(3L, item3, 60, null, null),
                buildItem(4L, item4, 20, null, null)
        ));

        entities = itemEntityRepository.findAll();
        assertThat(entities.size()).isEqualTo(4);

        //then
        mockMvc.perform(
                        post("/api/v1/checkout")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))

                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(BigDecimal.valueOf(250).doubleValue()))
                .andExpect(result -> {
                    String json = result.getResponse().getContentAsString();
                    CheckoutResponse response = objectMapper.readValue(json, CheckoutResponse.class);

                    if (!response.getItems().isEmpty()) {
                        for(CheckoutItemResponse item : response.getItems()){
                            if(item.getName().equals(item1)){
                                assertThat(item.getTotal().doubleValue()).isEqualTo(BigDecimal.valueOf(120).doubleValue());
                            } else if(item.getName().equals(item2)){
                                assertThat(item.getTotal().doubleValue()).isEqualTo(BigDecimal.valueOf(50).doubleValue());
                            } else if(item.getName().equals(item3)){
                                assertThat(item.getTotal().doubleValue()).isEqualTo(BigDecimal.valueOf(60).doubleValue());
                            } else if(item.getName().equals(item4)){
                                assertThat(item.getTotal().doubleValue()).isEqualTo(BigDecimal.valueOf(20).doubleValue());
                            }
                        }
                    }
                });
    }

    private ScannedItemRequest buildScannedItemRequest(String itemName, int quantity){
        return ScannedItemRequest.builder()
                .itemName(itemName)
                .quantity(quantity)
                .build();
    }

    private ItemEntity buildItem(Long id, String name, double unitPrice, Integer quantity, Double discountPrice){
        ItemEntity item = new ItemEntity();
        item.setId(id);
        item.setName(name);
        item.setUnitPrice(BigDecimal.valueOf(unitPrice));
        item.setOffer(buildOffer(item, quantity, discountPrice));
        return item;
    }

    private OfferEntity buildOffer(ItemEntity item, Integer quantity, Double discountPrice){
        if(quantity == null || discountPrice == null){
            return null;
        }
        OfferEntity offer = new OfferEntity();
        offer.setItem(item);
        offer.setQuantity(quantity);
        offer.setDiscountPrice(BigDecimal.valueOf(discountPrice));
        return offer;
    }

}
