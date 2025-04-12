package com.checkout.kata.integration.item;

import com.checkout.kata.integration.IntegrationBaseTest;
import com.checkout.kata.item.domain.dto.request.CreateItemRequest;
import com.checkout.kata.item.entity.ItemEntity;
import com.checkout.kata.item.persistence.ItemEntityRepository;
import com.checkout.kata.offer.domain.dto.request.OfferRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
public class ItemControllerIT extends IntegrationBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ItemEntityRepository itemEntityRepository;

    @BeforeEach
    void setup(){
        List<ItemEntity> entities = itemEntityRepository.findAll();
        assertTrue(entities.isEmpty());
    }

    @Test
    void upsertItem_shouldCreateItemWithoutOffer_whenRequestIsValid() throws Exception {
        CreateItemRequest request = buildItemRequest("Apple", BigDecimal.valueOf(10), null);

        mockMvc.perform(
                        post("/api/v1/item")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))

                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Apple"))
                .andExpect(jsonPath("$.unitPrice").value(BigDecimal.valueOf(10)))
                .andExpect(jsonPath("$.offer").isEmpty());
    }

    @Test
    void upsertItem_shouldCreateItemWithOffer_whenRequestIsValid() throws Exception {
        CreateItemRequest request = buildItemRequest("Banana", BigDecimal.valueOf(10),
                OfferRequest.builder().quantity(2L).discountPrice(BigDecimal.valueOf(8)).build());

        mockMvc.perform(
                        post("/api/v1/item")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))

                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Banana"))
                .andExpect(jsonPath("$.unitPrice").value(BigDecimal.valueOf(10)))
                .andExpect(jsonPath("$.offer").isNotEmpty())
                .andExpect(jsonPath("$.offer.quantity").value(2L))
                .andExpect(jsonPath("$.offer.discountPrice").value(BigDecimal.valueOf(8)));

    }

    @Test
    void upsertItem_shouldUpdateItem_whenItemAlreadyExist() throws Exception {

        itemEntityRepository.save(buildItem());
        List<ItemEntity> items = itemEntityRepository.findAll();
        assertThat(items.size()).isEqualTo(1);
        assertThat(items.getFirst().getName()).isEqualTo("Banana");
        assertThat(items.getFirst().getUnitPrice().doubleValue()).isEqualTo(BigDecimal.valueOf(5).doubleValue());
        assertNull(items.getFirst().getOffer());

        CreateItemRequest request = buildItemRequest("Banana", BigDecimal.valueOf(10),
                OfferRequest.builder().quantity(2L).discountPrice(BigDecimal.valueOf(8)).build());

        mockMvc.perform(
                        post("/api/v1/item")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))

                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Banana"))
                .andExpect(jsonPath("$.unitPrice").value(BigDecimal.valueOf(10)))
                .andExpect(jsonPath("$.offer").isNotEmpty())
                .andExpect(jsonPath("$.offer.quantity").value(2L))
                .andExpect(jsonPath("$.offer.discountPrice").value(BigDecimal.valueOf(8)));

    }

    private CreateItemRequest buildItemRequest(String name, BigDecimal unitPrice, OfferRequest offer){
        return CreateItemRequest.builder()
                .name(name)
                .unitPrice(unitPrice)
                .offer(offer)
                .build();
    }

    private ItemEntity buildItem(){
        ItemEntity item = new ItemEntity();
        item.setName("Banana");
        item.setUnitPrice(BigDecimal.valueOf(5.00));
        return item;
    }

}
