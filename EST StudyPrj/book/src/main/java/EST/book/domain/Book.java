package EST.book.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false,nullable = false,unique = true)
    Long id;
    @Column(name="name")
    String name;
    @Column(name="author")
    String author;

    @Builder
    public Book(String name, String author){
        this.name=name;
        this.author=author;
    }
}
