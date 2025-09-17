package EST.book.DTO;

import EST.book.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddBookRequest {
    private Long id;
    private String name;
    private String author;

    public Book toEntity(){
        return new Book(id, name, author);
    }
}
