package EST.book.controller;

import EST.book.DTO.AddBookRequest;
import EST.book.DTO.BookDTO;
import EST.book.DTO.BookViewResponse;
import EST.book.domain.Book;
import EST.book.service.ExternalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import EST.book.service.BookService;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class BookController {
    private final BookService bookService;
    private final ExternalService externalService;



    @GetMapping("/books")
    public String home(Model model){
        List<Book> memberList=bookService.getBookAll();
        model.addAttribute("bookList",memberList);
        return "bookManager";
    }

//    @GetMapping("/books/{id}")
//    public String view(@PathVariable Long id, Model model){
//        Book book=bookService.getBookById(id);
//        model.addAttribute("book",book);
//        return "bookDetail";
//    }
    @GetMapping("/books/{id}")
        public String findBookById(@PathVariable("id") Long id, Model model){
        BookViewResponse book= new BookViewResponse(bookService.getBookById(id));
        model.addAttribute("book",book);
        return "bookDetail";
    }
    @GetMapping("/api/external")
    public ResponseEntity<String> externalApiSave() {
        externalService.save();
        return ResponseEntity.ok("외부 API 데이터 저장 완료");
    }

    //객체 단위 바인딩
    @PostMapping("/books")  //객체 단위로 바인딩?
    public String addBook(@ModelAttribute("book")AddBookRequest request){
        bookService.saveBook(request);
        return "redirect:/books";
    }
}
