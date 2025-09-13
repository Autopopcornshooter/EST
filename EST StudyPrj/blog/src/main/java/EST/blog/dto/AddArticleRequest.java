package EST.blog.dto;

import EST.blog.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


//dto가 필요한 이유
//DB에 있는 데이터들 중에서 전달을 원하는 데이터들만 필터링해서 전달하고 싶을 때 사용
//직접 DB 구조에 손을 대는 것이 아닌 DTO 구조만 변경하여 원하는 데이터 전달 가능
//필요한 필드만 포함시켜 최적화 가능
//계층을 나누어 책임을 분리한다.


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {
    private String title;
    private String content;

    public Article toEntity(){
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
