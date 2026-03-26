package com.gestone.gestone_api.domain.bill;

import com.gestone.gestone_api.domain.expense.Expense;
import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import com.gestone.gestone_api.domain.payment.Payment;
import com.gestone.gestone_api.infra.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
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
        int installments = billRequestDTO.installments() != null ? billRequestDTO.installments() : 1;
        UUID parentId = installments > 1 ? UUID.randomUUID() : null;
        
        Bill firstBill = null;
        
        for (int i = 1; i <= installments; i++) {
            Bill bill = new Bill();
            bill.setName(billRequestDTO.name() + (installments > 1 ? " (" + i + "/" + installments + ")" : ""));
            bill.setDescription(billRequestDTO.description());
            bill.setSupplierName(billRequestDTO.supplierName());
            bill.setCategory(billRequestDTO.category());
            bill.setDueDate(billRequestDTO.dueDate().plusMonths(i - 1));
            bill.setNotes(billRequestDTO.notes());
            bill.setIsRecurring(billRequestDTO.isRecurring());
            
            if (installments > 1) {
                bill.setInstallmentNumber(i);
                bill.setTotalInstallments(installments);
                bill.setParentBillId(parentId);
                bill.setTotalValue(billRequestDTO.totalValue().divide(java.math.BigDecimal.valueOf(installments), 2, RoundingMode.HALF_UP));
            } else {
                bill.setTotalValue(billRequestDTO.totalValue().setScale(2, RoundingMode.HALF_UP));
            }
            
            bill.setMarbleshop(marbleshop);
            
            // For now, expenses are only added to the first installment if multi-installment
            if (i == 1 && billRequestDTO.expenses() != null) {
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
            }
            
            bill.setTotalPaid();
            bill.updateStatus();
            bill.setPaymentDate();
            Bill saved = billRepository.save(bill);
            if (i == 1) firstBill = saved;
        }
        
        return firstBill;
    }

    @Override
    public List<Bill> findAll(UUID marbleshopId) {
        return billRepository.findByMarbleshopId(marbleshopId);
    }

    @Override
    public Bill update(BillRequestDTO billRequestDTO, UUID billId) {
        Bill bill = findById(billId);
        bill.setName(billRequestDTO.name());
        bill.setDescription(billRequestDTO.description());
        bill.setSupplierName(billRequestDTO.supplierName());
        bill.setCategory(billRequestDTO.category());
        bill.setDueDate(billRequestDTO.dueDate());
        bill.setNotes(billRequestDTO.notes());
        bill.setTotalValue(billRequestDTO.totalValue().setScale(2, RoundingMode.HALF_UP));
        bill.setIsRecurring(billRequestDTO.isRecurring());
        
        bill.setTotalPaid();
        bill.updateStatus();
        bill.setPaymentDate();
        return billRepository.save(bill);
    }

    @Override
    public void delete(UUID billId) {
        Bill bill = findById(billId);
        billRepository.delete(bill);
    }

    public Bill findById(UUID billId) {
        return this.billRepository.findById(billId).orElseThrow(() -> new IllegalArgumentException("Bill not found with id: " + billId));
    }
}
