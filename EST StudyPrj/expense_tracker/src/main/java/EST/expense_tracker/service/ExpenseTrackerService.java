package EST.expense_tracker.service;

import EST.expense_tracker.domain.Budget;
import EST.expense_tracker.dto.AddBudgetRequest;
import EST.expense_tracker.dto.UpdateBudgetRequest;
import EST.expense_tracker.repository.ExpenseTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseTrackerService {
    private final ExpenseTrackerRepository expenseTrackerRepository;

    @Autowired
    public ExpenseTrackerService(ExpenseTrackerRepository expenseTrackerRepository) {
        this.expenseTrackerRepository = expenseTrackerRepository;
    }

    public Budget save(AddBudgetRequest request) {
        return expenseTrackerRepository.save(request.toEntity());
    }

    public List<Budget> getAllBudgets() {
        return expenseTrackerRepository.findAll();
    }

    public Budget getBudgetById(long id) {
        return expenseTrackerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 데이터가 존재하지 않습니다. : " + id));
    }

    public void deleteAll(){
        expenseTrackerRepository.deleteAll();
    }
    public void deleteById(long id) {
        expenseTrackerRepository.deleteById(id);
    }

    public Budget updateById(long id, UpdateBudgetRequest request){
        Budget budget=expenseTrackerRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 id의 데이터가 존재하지 않습니다. : " + id));
         budget.update(request.getTitle(), request.getAmount(), request.getDate());
         expenseTrackerRepository.save(budget);
         return budget;

    }

}
