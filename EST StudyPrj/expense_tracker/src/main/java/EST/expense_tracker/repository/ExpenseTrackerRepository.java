package EST.expense_tracker.repository;

import EST.expense_tracker.domain.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseTrackerRepository extends JpaRepository<Budget,Long> {
}
