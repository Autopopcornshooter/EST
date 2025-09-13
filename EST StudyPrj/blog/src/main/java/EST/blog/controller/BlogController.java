package EST.blog.controller;

import EST.blog.domain.Article;
import EST.blog.dto.AddArticleRequest;
import EST.blog.dto.ArticleResponse;
import EST.blog.dto.UpdateArticleRequest;
import EST.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class BlogController {
    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService){
        this.blogService=blogService;
    }

    // '/api' => 프론트엔드와 통신하는 API 용
    // HTTP 요청이 'POST/api/articles' 경로일 때 해당 메소드 매핑
    @PostMapping
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        Article savedArticle=blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> list=blogService.findAll()
                .stream().map(ArticleResponse::new)
                .toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> findArticleById(@PathVariable long id){
        ArticleResponse response=new ArticleResponse(blogService.getArticleById(id));
        //문제 생겼던 부분 : ArticleResponse엔 id값이 포함되어있지 않은데,Test에서는 id에 접근하려는 시도가 있어 오류 출력
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ArticleResponse> deleteById(@PathVariable long id){
        blogService.deleteById(id);
        return ResponseEntity.ok().build();     //body는 없지만 상태코드만 반환해야 할 때 사용!

    }
    @DeleteMapping
    public ResponseEntity<ArticleResponse> deleteAll(){
        blogService.deleteAll();
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateById(@PathVariable long id, @RequestBody UpdateArticleRequest request){
        Article article=blogService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(article);
    }



}
