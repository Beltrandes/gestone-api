package com.gestone.gestone_api.domain.quotation;

import jakarta.servlet.http.HttpServletRequest;

public interface IQuotationService {

    Quotation save(QuotationDTO quotationDTO, HttpServletRequest request);
}
