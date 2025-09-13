package EST.blog.controller;

import EST.blog.domain.Article;
import EST.blog.dto.ArticleViewResponse;
import EST.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogPageController {
    private BlogService blogService;

    @Autowired
    public BlogPageController(BlogService blogService){
        this.blogService=blogService;
    }

    @GetMapping("/articles")
    public String getArticles(Model model){
        List<ArticleViewResponse> articles=blogService.findAll()
                .stream()
                .map(ArticleViewResponse::new)
                .toList();
        model.addAttribute("articles",articles); //model 에 블로그 글 리스트 저장(이름은 articles)

        return "articleList"; //=>articleList.html 파일을 찾아서 렌더링한다는 뜻
        //"articles" 를 모델로 전달해서 사용한다!
    }

    @GetMapping("/articles/{id}")
    public String showArticles(@PathVariable Long id, Model model){
        Article article=blogService.getArticleById(id);
        model.addAttribute("article",new ArticleViewResponse(article));

        return "article";
    }

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required=false) Long id, Model model){
        if(id==null){
            model.addAttribute("article",new ArticleViewResponse());
        }else{
            Article article=blogService.getArticleById(id);
            model.addAttribute("article",new ArticleViewResponse(article));
        }
        return "newArticle";
    }
}
