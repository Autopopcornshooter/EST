package EST.book.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostContent {
    private int userId;
    private Long id;
    private String title;
    private String body;

    @Override
    public String toString() {
        return id + ", " + "title: " + title + "\n";
    }
}
