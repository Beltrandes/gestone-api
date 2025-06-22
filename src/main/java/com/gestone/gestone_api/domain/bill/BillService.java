package com.gestone.gestone_api.domain.bill;

import com.gestone.gestone_api.domain.expense.Expense;
import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import com.gestone.gestone_api.domain.payment.Payment;
import com.gestone.gestone_api.infra.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BillService implements IBillService{

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private TokenService tokenService;

    @Override
    public Bill save(BillRequestDTO billRequestDTO, HttpServletRequest request) {
        Marbleshop marbleshop = tokenService.getMarbleshopFromToken(request.getHeader("Authorization"));
        Bill bill = new Bill();
        bill.setName(billRequestDTO.name());
        bill.setDescription(billRequestDTO.description());
        bill.setCategory(billRequestDTO.category());
        bill.setDueDate(billRequestDTO.dueDate());
        bill.setNotes(billRequestDTO.notes());
        bill.setTotalValue(billRequestDTO.totalValue());
        bill.setMarbleshop(marbleshop);
        billRequestDTO.expenses().forEach(expenseRequestDTO -> {
            Expense expense = new Expense();
            expense.setBill(bill);
            expense.setPaidValue(expenseRequestDTO.paidValue());
            expense.setPaymentType(expenseRequestDTO.paymentType());
            expense.setDetails(expenseRequestDTO.details());
            expense.setPaymentDate(expenseRequestDTO.paymentDate());
            expense.setMarbleshop(marbleshop);
            bill.addExpense(expense);
        });
        bill.setTotalPaid();
        bill.updateStatus();
        bill.setPaymentDate();
        return billRepository.save(bill);
    }

    @Override
    public List<Bill> findAll(UUID marbleshopId) {
        return billRepository.findByMarbleshopId(marbleshopId);
    }

    @Override
    public Bill update(BillRequestDTO billRequestDTO, UUID billId) {
        return null;
    }

    @Override
    public void delete(UUID billId) {

    }

    public Bill findById(UUID billId) {
        return this.billRepository.findById(billId).orElseThrow(() -> new IllegalArgumentException("Bill not found with id: " + billId));
    }
}
