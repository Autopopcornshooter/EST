package EST.expense_tracker.controller;

import EST.expense_tracker.domain.Budget;
import EST.expense_tracker.dto.AddBudgetRequest;
import EST.expense_tracker.dto.UpdateBudgetRequest;
import EST.expense_tracker.service.ExpenseTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class ExpenseTrackerController {
    private final ExpenseTrackerService expenseTrackerService;

    @Autowired
    public ExpenseTrackerController(ExpenseTrackerService expenseTrackerService){
        this.expenseTrackerService=expenseTrackerService;
    }

    @PostMapping
    public ResponseEntity<Budget> addBudget (@RequestBody AddBudgetRequest request){
        Budget response=expenseTrackerService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public List<Budget> getBudgets(){
        return expenseTrackerService.getAllBudgets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Budget> getBudgetById(@PathVariable long id){
        Budget budget=expenseTrackerService.getBudgetById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(budget);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Budget> deleteById(@PathVariable long id){
        expenseTrackerService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Budget> deleteAll(){
        expenseTrackerService.deleteAll();
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Budget> update(@PathVariable long id, @RequestBody UpdateBudgetRequest request){
        Budget budget=expenseTrackerService.updateById(id,request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(budget);

    }
}
