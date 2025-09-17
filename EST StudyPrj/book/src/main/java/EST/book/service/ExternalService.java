package EST.book.service;

import EST.book.DTO.AddBookRequest;
import EST.book.DTO.PostContent;
import EST.book.domain.Book;
import EST.book.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j  //로그를 찍기 위한 어노테이션
@Service
public class ExternalService {
    private final BookRepository bookRepository;

    public ExternalService(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    public void call(){
        String url="https://jsonplaceholder.typicode.com/posts";

        RestTemplate restTemplate =new RestTemplate();
        ResponseEntity<List<PostContent>> response =
                restTemplate.exchange(url, HttpMethod.GET, null
                , new ParameterizedTypeReference<>() {});

        log.info("code: {}",response.getStatusCode());
        List<PostContent> postContent=response.getBody();
        log.info("postContent: {}",postContent);
    }

    public void save(){
        String url = "https://jsonplaceholder.typicode.com/posts";

        RestTemplate restTemplate = new RestTemplate(); // 스프링에서 관리해서 의존성 주입으로 사용해도 됨.
        ResponseEntity<List<PostContent>> response = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {}
        );
        List<PostContent> books = response.getBody();
        // log.info("데이터 개수: {}", books.size());

        // Entity로 변환해서 저장
        for (PostContent dto : books) {
            log.info("title: {}, content: {}", dto.getTitle(), dto.getBody());

            AddBookRequest request = new AddBookRequest(
                    dto.getId(),
                    dto.getTitle(),
                    String.valueOf(dto.getUserId())
            );

            Book book = request.toEntity();

            bookRepository.save(book);
        }
    }
}
