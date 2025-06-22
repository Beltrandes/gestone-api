package com.gestone.gestone_api.domain.expense;

import com.gestone.gestone_api.domain.bill.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<Expense, UUID> {

    List<Expense> findByMarbleshopId(UUID marbleshopId);
}
