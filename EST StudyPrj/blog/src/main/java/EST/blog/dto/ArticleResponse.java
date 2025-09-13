package EST.blog.dto;

import EST.blog.domain.Article;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter

public class ArticleResponse {
    private Long id;
    private String title;
    private String content;

    @Autowired
    public ArticleResponse(Article article){
        this.id=article.getId();
        this.title=article.getTitle();
        this.content= article.getContent();
    }
}
