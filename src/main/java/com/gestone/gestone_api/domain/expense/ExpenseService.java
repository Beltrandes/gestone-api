package com.gestone.gestone_api.domain.expense;

import com.gestone.gestone_api.domain.bill.Bill;
import com.gestone.gestone_api.domain.bill.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExpenseService implements IExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private BillService billService;

    @Override
    public Expense save(ExpenseRequestDTO expenseRequestDTO) {
        Expense expense = new Expense();
        Bill bill = billService.findById(expenseRequestDTO.billId());
        expense.setBill(bill);
        expense.setDetails(expenseRequestDTO.details());
        expense.setPaidValue(expenseRequestDTO.paidValue());
        expense.setPaymentDate(expenseRequestDTO.paymentDate());
        expense.setPaymentType(expenseRequestDTO.paymentType());
        expense.setMarbleshop(bill.getMarbleshop());
        bill.addExpense(expense);
        bill.setTotalPaid();
        bill.updateStatus();
        bill.setPaymentDate();
        return this.expenseRepository.save(expense);
    }

    @Override
    public List<Expense> findExpensesByMarbleshopId(UUID marbleshopId) {
        return expenseRepository.findByMarbleshopId(marbleshopId);
    }
}
