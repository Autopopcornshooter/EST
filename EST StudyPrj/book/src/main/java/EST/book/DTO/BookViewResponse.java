package EST.book.DTO;

import EST.book.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookViewResponse {
    private Long id;
    private String name;
    private String author;

    public BookViewResponse(Book book){
        this.id=book.getId();
        this.name=book.getName();
        this.author=book.getAuthor();
    }
}
