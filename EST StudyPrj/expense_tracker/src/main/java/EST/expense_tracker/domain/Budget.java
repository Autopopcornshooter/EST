package EST.expense_tracker.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Getter
@NoArgsConstructor
public class Budget {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", updatable = false)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="amount")
    private float amount;

    @Column(name="date")
    private LocalDate date;

    @Builder
    public Budget(String title, float amount, LocalDate date){
        this.title=title;
        this.amount=amount;
        this.date=date;
    }
    public void update(String title, float amount, LocalDate date){
        this.title=title;
        this.amount=amount;
        this.date=date;
    }
}
