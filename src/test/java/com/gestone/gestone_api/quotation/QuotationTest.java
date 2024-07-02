package com.gestone.gestone_api.quotation;

import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import com.gestone.gestone_api.domain.material.MarbleshopMaterial;
import com.gestone.gestone_api.domain.material.MarbleshopMaterialType;
import com.gestone.gestone_api.domain.quotation.Quotation;
import com.gestone.gestone_api.domain.quotation.QuotationStatus;
import com.gestone.gestone_api.domain.quotation.MarbleshopItem;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuotationTest {

    @Test
    public void testCheckDueDateExpired() {
        LocalDateTime creationDate = LocalDateTime.now().minusDays(2);
        Integer daysForDue = 1;
        Quotation quotation = new Quotation("Test quotation", "...", "...", 2, daysForDue, null);
        quotation.setCreatedAt(creationDate);
        quotation.checkDueDate();
        assertEquals(QuotationStatus.EXPIRED, quotation.getQuotationStatus());
    }

    @Test
    public void testCheckDueDateNotExpired() {
        Integer daysForDue = 90;
        Quotation quotation = new Quotation("Test quotation", "...", "...", 2, daysForDue, null);
        quotation.setCreatedAt(LocalDateTime.now().minusDays(80));
        quotation.checkDueDate();
        assertEquals(QuotationStatus.PENDING, quotation.getQuotationStatus());
    }

    @Test
    public void testCheckDueDateIfQuotationApproved() {
        Integer daysForDue = 90;
        Quotation quotation = new Quotation("Test quotation", "...", "...", 2, daysForDue, null);
        quotation.setCreatedAt(LocalDateTime.now().minusDays(80));
        quotation.setQuotationStatus(QuotationStatus.APPROVED);
        quotation.checkDueDate();
        assertEquals(QuotationStatus.APPROVED, quotation.getQuotationStatus());
    }

    // @Test
    // public void testCalculateTotalValueAndArea() {
    //     List<QuoteItem> mockQuoteItems = Arrays.asList(
    //             new QuoteItem("Item 1", "Description 1", new BigDecimal("1.55"), new BigDecimal("0.60"), 2, new Material("Test material 1", "Test details 1", new BigDecimal(850), MaterialType.GRANITE, mock(Marbleshop.class))),
    //             new QuoteItem("Item 2", "Description 2", new BigDecimal("0.90"), new  BigDecimal("0.45"), 1, new Material("Test material 2", "Test details 2", new BigDecimal(1200), MaterialType.QUARTZ, mock(Marbleshop.class))),
    //             new QuoteItem("Item 3", "Description 3", new BigDecimal(0), new BigDecimal(0), 1, new Material("Test material 3", "Test details 3", new BigDecimal(230), MaterialType.OTHER, mock(Marbleshop.class)))
    //     );
    //     Quotation quotation = new Quotation();

    //     mockQuoteItems.forEach((item) -> {
    //         item.setQuotation(quotation);
    //         item.calculate();
    //     });

    //     quotation.setQuoteItems(mockQuoteItems);

    //     BigDecimal expectedTotalValue = new BigDecimal("2297.0000");
    //     BigDecimal expectedTotalArea = new BigDecimal("2.2650");

    //     quotation.calculate();

    //     assertEquals(expectedTotalValue, quotation.getTotalValue());
    //     assertEquals(expectedTotalArea, quotation.getTotalArea());
    // }



}
