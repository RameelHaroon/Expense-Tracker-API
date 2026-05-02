package com.example.Expense_Tracker_API.ExpenseTrackerService.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(ExpenseTrackerController.BASE_URL)
public class ExpenseTrackerController {
    public static final String BASE_URL = "api/expenseTrackerService";
    public static final String V1_TASK = "/v1/expense";
    public static final String V1_EXPENSE_ID = V1_TASK + "/{expenseId}";

    @GetMapping(V1_TASK)
    public String getTasks(Authentication authentication) {
        return "Hello! This is the expense tracker app";
    }
}
