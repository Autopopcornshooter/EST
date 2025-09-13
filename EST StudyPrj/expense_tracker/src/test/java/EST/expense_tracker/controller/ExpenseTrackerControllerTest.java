package EST.expense_tracker.controller;

import EST.expense_tracker.domain.Budget;
import EST.expense_tracker.dto.AddBudgetRequest;
import EST.expense_tracker.dto.UpdateBudgetRequest;
import EST.expense_tracker.repository.ExpenseTrackerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest                     //@SpringBootApplication 어노테이션이 붙은 클래스를 찾아서 포함되어있는 빈을 찾아 테스트용 애플리케이션 컨텍스트를 생성한다
@AutoConfigureMockMvc               //MockMvc 생성용 어노테이션, 없으면 사용불가
class ExpenseTrackerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ExpenseTrackerRepository expenseTrackerRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        expenseTrackerRepository.deleteAll();
    }

    @Test
    @DisplayName("가계부 추가 성공")
    public void testAddBudget() throws Exception {
        //given
        String url = "/api/budgets";
        final String title = "title1";
        final float amount = 45000;
        final LocalDate date = LocalDate.now();
        AddBudgetRequest request = new AddBudgetRequest(title, amount, date);
        String requestBody = objectMapper.writeValueAsString(request);
        //when
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));
        result.andExpect(status().isCreated());

        List<Budget> list = expenseTrackerRepository.findAll();
        //then
        assertThat(list.size()).isEqualTo(1);
        assertThat(list.get(0).getTitle()).isEqualTo(request.getTitle());
        assertThat(list.get(0).getAmount()).isEqualTo(request.getAmount());
        assertThat(list.get(0).getDate()).isEqualTo(request.getDate());
    }

    @Test
    @DisplayName("가계부 전체 조회 성공")
    public void testGetAllBudget() throws Exception {
        //given
        final String url = "/api/budgets";
        final String title = "title1";
        final float amount = 45000;
        final LocalDate date = LocalDate.now();
        Budget budget = expenseTrackerRepository.save(new Budget(title, amount, date));


        //when
        ResultActions result=mockMvc.perform(get(url));
        //then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(budget.getTitle()))
                .andExpect(jsonPath("$[0].amount").value(budget.getAmount()))
                .andExpect(jsonPath("$[0].date").value(budget.getDate().toString()));
        //Json의 date는 String으로 반환되어 LocalDate와 비교하려면 String형으로 바꿔줘야 한다.
    }


    @Test
    @DisplayName("가계부 단 건 조회 성공")
    public void testGetBudgetById() throws Exception {
        //given
        final String url = "/api/budgets/{id}";
        final String title = "title1";
        final float amount = 45000;
        final LocalDate date = LocalDate.now();
        Budget budget=expenseTrackerRepository.save(new Budget(title,amount,date));

        ResultActions result=mockMvc.perform(get(url,budget.getId()));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(budget.getTitle()))
                .andExpect(jsonPath("$.amount").value(budget.getAmount()))
                .andExpect(jsonPath("$.date").value(budget.getDate().toString()));

    }

    @Test
    @DisplayName("가계부 단 건 삭제 성공")
    public void testDeleteBudgetById() throws Exception {
        //given
        final String url = "/api/budgets/{id}";
        final String title = "title1";
        final float amount = 45000;
        final LocalDate date = LocalDate.now();
        Budget budget=expenseTrackerRepository.save(new Budget(title,amount,date));
        //then
        mockMvc.perform(delete(url,budget.getId())).andExpect(status().isOk());

        //then
        List<Budget> list=expenseTrackerRepository.findAll();
        assertThat(list).isEmpty();
    }

    @Test
    @DisplayName("가계부 전체 삭제 성공")
    public void testDeleteAllBudget() throws Exception {
        //given
        final String url = "/api/budgets";
        final String title = "title1";
        final float amount = 45000;
        final LocalDate date = LocalDate.now();
        Budget budget=expenseTrackerRepository.save(new Budget(title,amount,date));

        mockMvc.perform(delete(url)).andExpect(status().isOk());

        List<Budget> list=expenseTrackerRepository.findAll();
        assertThat(list).isEmpty();
    }

    @Test
    @DisplayName("가계부 수정 성공")
    public void testModifyBudget() throws Exception {
        //given
        final String url = "/api/budgets/{id}";
        final String title = "title1";
        final float amount = 45000;
        final LocalDate date = LocalDate.now();
        Budget budget=expenseTrackerRepository.save(new Budget(title,amount,date));
        final String modifiedUrl = "/api/budgets/{id}";
        final String modifiedTitle = "title1";
        final float modifiedAmount = 45000;
        final LocalDate modifiedDate = LocalDate.now();
        UpdateBudgetRequest request=new UpdateBudgetRequest(modifiedTitle,modifiedAmount,modifiedDate);
        ResultActions result=mockMvc.perform(put(url,budget.getId())
                .content(objectMapper.writeValueAsString(request))      //컨텐츠 : 수정된 객체(업데이트용)
                .contentType(MediaType.APPLICATION_JSON_VALUE));        //컨텐츠타입 : json
        result.andExpect(status().isOk());

        Budget afterModified=expenseTrackerRepository.findById(budget.getId())
                .orElseThrow(IllegalArgumentException::new);

        assertThat(afterModified.getTitle()).isEqualTo(request.getTitle());
        assertThat(afterModified.getAmount()).isEqualTo(request.getAmount());
        assertThat(afterModified.getDate()).isEqualTo(request.getDate());


    }


}