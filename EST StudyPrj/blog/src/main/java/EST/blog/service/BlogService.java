package EST.blog.service;

import EST.blog.domain.Article;
import EST.blog.dto.AddArticleRequest;
import EST.blog.dto.UpdateArticleRequest;
import EST.blog.repository.BlogRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }


    public Article getArticleById(long id) {
        Optional<Article> article=blogRepository.findById(id);
        return article.orElseGet(Article::new);
        //orElseThrow=> null이 아니라면 Article반환 => 오류체크는 무조건 해줘야함
        //orElseGet => null이라면 해당 메서드 반환
    }

    public void deleteById(long id) {
        blogRepository.deleteById(id);
    }

    public void deleteAll() {
        blogRepository.deleteAll();
    }

    @Transactional          //작업을 하나의 트랜잭션으로 묶어 모든 작업이 동시에 성공해야 완료된다.
    public Article update(Long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 데이터가 존재하지 않습니다. :" + id));

        article.update(request.getTitle(), request.getContent());
        return article;
    }

}
