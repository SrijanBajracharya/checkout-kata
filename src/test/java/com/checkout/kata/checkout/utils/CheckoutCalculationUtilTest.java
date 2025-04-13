package com.checkout.kata.checkout.utils;

import com.checkout.kata.checkout.domain.dto.request.ScannedItemRequest;
import com.checkout.kata.checkout.domain.dto.response.CheckoutItemResponse;
import com.checkout.kata.checkout.domain.dto.response.CheckoutResponse;
import com.checkout.kata.item.domain.Item;
import com.checkout.kata.offer.domain.Offer;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class CheckoutCalculationUtilTest {

    @Test
    void handleCheckout_shouldCalculateCheckout_whenScannedItemProvided(){
        //given
        String name1 = "Apple";
        int quantity1 = 1;
        String name2 = "Banana";
        int quantity2 = 1;
        String name3 = "kiwi";
        int quantity3 = 4;
        String name4 = "Apple";
        int quantity4 = 1;

        ScannedItemRequest scannedItem1 = buildScannedItemRequest(name1, quantity1);
        ScannedItemRequest scannedItem2 = buildScannedItemRequest(name2, quantity2);
        ScannedItemRequest scannedItem3 = buildScannedItemRequest(name3, quantity3);
        ScannedItemRequest scannedItem4 = buildScannedItemRequest(name4, quantity4);

        List<ScannedItemRequest> scannedItems = new ArrayList<>();
        scannedItems.add(scannedItem1);
        scannedItems.add(scannedItem2);
        scannedItems.add(scannedItem3);
        scannedItems.add(scannedItem4);

        //when
        Item item1 = createItem(name1, BigDecimal.valueOf(10), Offer.builder().quantity(2).discountPrice(BigDecimal.valueOf(15)).build());
        Item item2 = createItem(name2, BigDecimal.valueOf(20), null);
        Item item3 = createItem(name3, BigDecimal.valueOf(10), Offer.builder().quantity(3).discountPrice(BigDecimal.valueOf(25)).build());
        Map<String, Item> itemsMap = new HashMap<>();
        itemsMap.put(name1, item1);
        itemsMap.put(name2, item2);
        itemsMap.put(name3, item3);

        CheckoutResponse response = CheckoutCalculationUtil.handleCheckout(scannedItems, itemsMap);
        assertThat(response.getTotal()).isEqualTo(BigDecimal.valueOf(70));
        for(CheckoutItemResponse item: response.getItems()){
            if(item.getName().equals(name1)){
                assertThat(item.getTotal()).isEqualTo(BigDecimal.valueOf(15));
            }else if(item.getName().equals(name2)){
                assertThat(item.getTotal()).isEqualTo(BigDecimal.valueOf(20));
            }else if(item.getName().equals(name3)){
                assertThat(item.getTotal()).isEqualTo(BigDecimal.valueOf(35));
            }
        }
    }

    @Test
    void handleCheckout_shouldCalculateCheckout_whenScannedItemProvided_test2(){
        //given
        String name1 = "Apple";
        int quantity1 = 1;
        String name2 = "Banana";
        int quantity2 = 3;
        String name3 = "kiwi";
        int quantity3 = 5;
        String name4 = "Apple";
        int quantity4 = 2;

        ScannedItemRequest scannedItem1 = buildScannedItemRequest(name1, quantity1);
        ScannedItemRequest scannedItem2 = buildScannedItemRequest(name2, quantity2);
        ScannedItemRequest scannedItem3 = buildScannedItemRequest(name3, quantity3);
        ScannedItemRequest scannedItem4 = buildScannedItemRequest(name4, quantity4);

        List<ScannedItemRequest> scannedItems = new ArrayList<>();
        scannedItems.add(scannedItem1);
        scannedItems.add(scannedItem2);
        scannedItems.add(scannedItem3);
        scannedItems.add(scannedItem4);

        //when
        Item item1 = createItem(name1, BigDecimal.valueOf(10), Offer.builder().quantity(2).discountPrice(BigDecimal.valueOf(15)).build());
        Item item2 = createItem(name2, BigDecimal.valueOf(20), null);
        Item item3 = createItem(name3, BigDecimal.valueOf(10), Offer.builder().quantity(3).discountPrice(BigDecimal.valueOf(25)).build());
        Map<String, Item> itemsMap = new HashMap<>();
        itemsMap.put(name1, item1);
        itemsMap.put(name2, item2);
        itemsMap.put(name3, item3);

        CheckoutResponse response = CheckoutCalculationUtil.handleCheckout(scannedItems, itemsMap);
        assertThat(response.getTotal()).isEqualTo(BigDecimal.valueOf(130));
        for(CheckoutItemResponse item: response.getItems()){
            if(item.getName().equals(name1)){
                assertThat(item.getTotal()).isEqualTo(BigDecimal.valueOf(25));
            }else if(item.getName().equals(name2)){
                assertThat(item.getTotal()).isEqualTo(BigDecimal.valueOf(60));
            }else if(item.getName().equals(name3)){
                assertThat(item.getTotal()).isEqualTo(BigDecimal.valueOf(45));
            }
        }
    }

    @Test
    void handleCheckout_shouldCalculateCheckout_whenScannedItemProvided_test3(){
        //given
        String name1 = "Apple";
        int quantity1 = 4;
        String name2 = "Banana";
        int quantity2 = 18;
        String name3 = "kiwi";
        int quantity3 = 2;
        String name4 = "kiwi";
        int quantity4 = 7;

        ScannedItemRequest scannedItem1 = buildScannedItemRequest(name1, quantity1);
        ScannedItemRequest scannedItem2 = buildScannedItemRequest(name2, quantity2);
        ScannedItemRequest scannedItem3 = buildScannedItemRequest(name3, quantity3);
        ScannedItemRequest scannedItem4 = buildScannedItemRequest(name4, quantity4);

        List<ScannedItemRequest> scannedItems = new ArrayList<>();
        scannedItems.add(scannedItem1);
        scannedItems.add(scannedItem2);
        scannedItems.add(scannedItem3);
        scannedItems.add(scannedItem4);

        //when
        Item item1 = createItem(name1, BigDecimal.valueOf(4.5), Offer.builder().quantity(8).discountPrice(BigDecimal.valueOf(33.6)).build());
        Item item2 = createItem(name2, BigDecimal.valueOf(0.5), Offer.builder().quantity(12).discountPrice(BigDecimal.valueOf(4.8)).build());
        Item item3 = createItem(name3, BigDecimal.valueOf(3.5), Offer.builder().quantity(4).discountPrice(BigDecimal.valueOf(13.2)).build());
        Map<String, Item> itemsMap = new HashMap<>();
        itemsMap.put(name1, item1);
        itemsMap.put(name2, item2);
        itemsMap.put(name3, item3);

        CheckoutResponse response = CheckoutCalculationUtil.handleCheckout(scannedItems, itemsMap);
        assertThat(response.getTotal()).isEqualTo(BigDecimal.valueOf(55.7));
        for(CheckoutItemResponse item: response.getItems()){
            if(item.getName().equals(name1)){
                assertThat(item.getTotal().doubleValue()).isEqualTo(BigDecimal.valueOf(18).doubleValue());
            }else if(item.getName().equals(name2)){
                assertThat(item.getTotal()).isEqualTo(BigDecimal.valueOf(7.8));
            }else if(item.getName().equals(name3)){
                assertThat(item.getTotal()).isEqualTo(BigDecimal.valueOf(29.9));
            }
        }
    }
    @Test
    void handleCheckout_shouldCalculateCheckout_whenScannedItemProvided_test4(){
        //given
        String name1 = "kiwi";
        int quantity1 = 4;
        String name2 = "Banana";
        int quantity2 = 18;
        String name3 = "kiwi";
        int quantity3 = 2;
        String name4 = "kiwi";
        int quantity4 = 7;

        ScannedItemRequest scannedItem1 = buildScannedItemRequest(name1, quantity1);
        ScannedItemRequest scannedItem2 = buildScannedItemRequest(name2, quantity2);
        ScannedItemRequest scannedItem3 = buildScannedItemRequest(name3, quantity3);
        ScannedItemRequest scannedItem4 = buildScannedItemRequest(name4, quantity4);

        List<ScannedItemRequest> scannedItems = new ArrayList<>();
        scannedItems.add(scannedItem1);
        scannedItems.add(scannedItem2);
        scannedItems.add(scannedItem3);
        scannedItems.add(scannedItem4);

        //when
        Item item1 = createItem(name1, BigDecimal.valueOf(4.5), Offer.builder().quantity(8).discountPrice(BigDecimal.valueOf(33.6)).build());
        Item item2 = createItem(name2, BigDecimal.valueOf(0.5), Offer.builder().quantity(12).discountPrice(BigDecimal.valueOf(4.8)).build());
        Map<String, Item> itemsMap = new HashMap<>();
        itemsMap.put(name1, item1);
        itemsMap.put(name2, item2);

        CheckoutResponse response = CheckoutCalculationUtil.handleCheckout(scannedItems, itemsMap);
        assertThat(response.getTotal()).isEqualTo(BigDecimal.valueOf(63.9));
        for(CheckoutItemResponse item: response.getItems()){
            if(item.getName().equals(name1)){
                assertThat(item.getTotal()).isEqualTo(BigDecimal.valueOf(56.1));
            }else if(item.getName().equals(name2)){
                assertThat(item.getTotal()).isEqualTo(BigDecimal.valueOf(7.8));
            }
        }
    }

    @Test
    void handleCheckout_shouldCalculateCheckout_whenScannedItemProvided_test5(){
        //given
        String name1 = "Apple";
        int quantity1 = 4;
        String name2 = "Banana";
        int quantity2 = 3;
        String name3 = "kiwi";
        int quantity3 = 5;
        String name4 = "Apple";
        int quantity4 = 2;

        ScannedItemRequest scannedItem1 = buildScannedItemRequest(name1, quantity1);
        ScannedItemRequest scannedItem2 = buildScannedItemRequest(name2, quantity2);
        ScannedItemRequest scannedItem3 = buildScannedItemRequest(name3, quantity3);
        ScannedItemRequest scannedItem4 = buildScannedItemRequest(name4, quantity4);

        List<ScannedItemRequest> scannedItems = new ArrayList<>();
        scannedItems.add(scannedItem1);
        scannedItems.add(scannedItem2);
        scannedItems.add(scannedItem3);
        scannedItems.add(scannedItem4);

        //when
        Item item1 = createItem(name1, BigDecimal.valueOf(10), Offer.builder().quantity(3).discountPrice(BigDecimal.valueOf(25)).build());
        Item item2 = createItem(name2, BigDecimal.valueOf(20), null);
        Item item3 = createItem(name3, BigDecimal.valueOf(10), Offer.builder().quantity(3).discountPrice(BigDecimal.valueOf(25)).build());
        Map<String, Item> itemsMap = new HashMap<>();
        itemsMap.put(name1, item1);
        itemsMap.put(name2, item2);
        itemsMap.put(name3, item3);

        CheckoutResponse response = CheckoutCalculationUtil.handleCheckout(scannedItems, itemsMap);
        assertThat(response.getTotal()).isEqualTo(BigDecimal.valueOf(155));
        for(CheckoutItemResponse item: response.getItems()){
            if(item.getName().equals(name1)){
                assertThat(item.getTotal()).isEqualTo(BigDecimal.valueOf(50));
            }else if(item.getName().equals(name2)){
                assertThat(item.getTotal()).isEqualTo(BigDecimal.valueOf(60));
            }else if(item.getName().equals(name3)){
                assertThat(item.getTotal()).isEqualTo(BigDecimal.valueOf(45));
            }
        }
    }

    private ScannedItemRequest buildScannedItemRequest(String itemName, Integer quantity){
        return ScannedItemRequest.builder()
                .itemName(itemName)
                .quantity(quantity)
                .build();
    }

    private Item createItem(String name, BigDecimal unitPrice, Offer offer){
        return Item.builder()
                .name(name)
                .unitPrice(unitPrice)
                .offer(offer)
                .build();
    }

}