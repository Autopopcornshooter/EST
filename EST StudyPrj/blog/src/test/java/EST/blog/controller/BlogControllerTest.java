package EST.blog.controller;

import EST.blog.domain.Article;
import EST.blog.dto.AddArticleRequest;
import EST.blog.dto.UpdateArticleRequest;
import EST.blog.repository.BlogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest                     //@SpringBootApplication 어노테이션이 붙은 클래스를 찾아서 포함되어있는 빈을 찾아 테스트용 애플리케이션 컨텍스트를 생성한다
@AutoConfigureMockMvc               //MockMvc 생성용 어노테이션, 없으면 사용불가
class BlogControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private BlogRepository blogRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        blogRepository.deleteAll();
    }

    @DisplayName("블로그 글 추가 성공")
    @Test
    public void addArticle() throws Exception {
        //given
        String url = "/api/articles";
        String title = "title";
        String content = "contents";
        AddArticleRequest request = new AddArticleRequest(title, content);
        //objectMapper로 request 데이터 JSON으로 직렬화
        String requestBody = objectMapper.writeValueAsString(request);


        //when
        ResultActions result = mockMvc.perform(post(url)        //Test클래스에서 post는 test.web.....로 import해야한다.
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        //then
        result.andExpect(status().isCreated());

        List<Article> articleList = blogRepository.findAll();

        assertThat(articleList.size()).isEqualTo(1);                //데이터 개수(크기) 검사
        assertThat(articleList.get(0).getTitle()).isEqualTo(title);         //assertThat(A).isEqualTo(B) => A와 B가 동일한가?
        assertThat(articleList.get(0).getContent()).isEqualTo(content);
    }

    @DisplayName("블로그 글 전체 조회 성공")
    @Test
    public void testFindAll() throws Exception{
        //given
        final String url="/api/articles";
        final String title="title1";
        final String content="content1";
        Article article=blogRepository.save(new Article(title,content));

        //when  mockMvc.perform(get(url)) => 특정 url에 get 요청을 보냄
        ResultActions result= mockMvc.perform(get(url));

        //then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(article.getTitle()))
                .andExpect(jsonPath("$[0].content").value(article.getContent()));
    }

    @DisplayName("블로그 글 단 건 조회 성공")
    @Test
    public void testFindById() throws Exception{
        //given
        final String url="/api/articles/{id}";
        final String title="title1";
        final String content="content1";
        Article article=Article.builder()
                .title("단 건 테스트 title")
                .content("단 건 테스트 Content")
                .build();
        blogRepository.save(article);

        //when  mockMvc.perform(get(url)) => 특정 url에 get 요청을 보냄
        ResultActions result= mockMvc.perform(get(url,article.getId()));
        //then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(article.getId()))
                .andExpect(jsonPath("$.title").value(article.getTitle()))
                .andExpect(jsonPath("$.content").value(article.getContent()));
    }

    @DisplayName("블로그 글 삭제 성공")
    @Test
    public void testDeleteById() throws Exception{
        //given
        final String url="/api/articles/{id}";
        final String title="title1";
        final String content="content1";
        Article article=blogRepository.save(new Article(title,content));
        Long savedId=article.getId();
        //when
        mockMvc.perform(delete(url,savedId)).andExpect(status().isOk());
        //then
        List<Article> list=blogRepository.findAll();
        assertThat(list).isEmpty();
    }

    @DisplayName("블로그 글 전체 삭제 성공")
    @Test
    public void testDeleteAll() throws Exception{
        final String url="/api/articles";
        blogRepository.save(new Article("title1","content1"));
        blogRepository.save(new Article("title2","content2"));
        blogRepository.save(new Article("title3","content3"));

        //when
        mockMvc.perform(delete(url)).andExpect(status().isOk());
        //then
        List<Article> list=blogRepository.findAll();
        assertThat(list).isEmpty();
    }
    @DisplayName("블로그 글 수정 성공")
    @Test
    public void testUpdate() throws Exception{
        final String url="/api/articles/{id}";
        final String title="title1";
        final String content="content1";
        Article article=blogRepository.save(new Article(title,content));

        String modifiedTitle="Modified Title";
        String modifiedContent="Modified Content";
        UpdateArticleRequest request=new UpdateArticleRequest(modifiedTitle,modifiedContent);

        //when
        ResultActions result=mockMvc.perform(put(url,article.getId())
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        //then
        result.andExpect(status().isOk());

        Article afterModifiedArticle=blogRepository.findById(article.getId())
                .orElseThrow(()->new IllegalIdentifierException(""));
        assertThat(afterModifiedArticle.getTitle()).isEqualTo(modifiedTitle);
        assertThat(afterModifiedArticle.getContent()).isEqualTo(modifiedContent);

    }

    @DisplayName("400 예외 확인 테스트")
    @Test
    public void updateArticleBadRequest() throws Exception {
        // given : 존재하지 않는 id 설정
        Long invalidId = 0L;
        UpdateArticleRequest request = new UpdateArticleRequest("updateTitle", "updateContent");
        String requestBody = new ObjectMapper().writeValueAsString(request);

        // when
        ResultActions resultActions = mockMvc.perform(put("/api/articles/{id}", invalidId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        // then : 400 BAD_REQUEST 응답 확인
        resultActions.andExpect(status().isBadRequest());
    }
}