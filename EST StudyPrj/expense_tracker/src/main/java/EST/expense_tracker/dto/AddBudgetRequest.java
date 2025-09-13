package EST.expense_tracker.dto;

import EST.expense_tracker.domain.Budget;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddBudgetRequest {
    private String title;
    private float amount;
    private LocalDate date;

    public Budget toEntity(){
        return Budget.builder()
                .title(title)
                .amount(amount)
                .date(date)
                .build();

    }
}
