package EST.expense_tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class BudgetResponse {
    private String title;
    private float amount;
    private LocalDate date;
}
