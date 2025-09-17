package EST.book.service;

import EST.book.DTO.AddBookRequest;
import EST.book.DTO.BookDTO;
import EST.book.DTO.BookViewResponse;
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

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    public List<Book> getBookAll(){

        return bookRepository.findAll();
    }
//    public List<BookDTO> getBookAll(){
//
//        return bookRepository.findAll()
//                .stream()
//                .map(entity->new BookDTO(entity.getId(),entity.getName(),entity.getAuthor()))
//                .toList();
//    }

    public Book getBookById(Long id){
        return bookRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 id의 책이 존재하지 않습니다: "+id));

    }

//    public BookDTO getBookById(Long id) {
//        Book book = bookRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("해당 id의 책이 존재하지 않습니다: " + id));
//        return new BookDTO(book.getId(), book.getName(), book.getAuthor());
//    }

    public Book saveBook(AddBookRequest request){
        Book book=request.toEntity();
        return bookRepository.save(book);
    }

}
