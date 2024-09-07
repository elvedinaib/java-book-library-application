package com.example.booklibrary.controller;

import com.example.booklibrary.model.Book;
import com.example.booklibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/book-library")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping()
    public List<Book> findAllBooks(){
        return bookService.findAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) throws NoSuchElementException{
        try{
            return new ResponseEntity<>(bookService.getById(id), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>("Book with id=" + id + " does not exist in our database", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{string}")
    public ResponseEntity<?> getByTitleOrAuthor(@PathVariable("string") String string){
            List<Book> booksToReturn=bookService.getByTitleOrAuthor(string);
            if (booksToReturn.isEmpty())
                return new ResponseEntity<>("No such books are found in our database. (check your spelling)", HttpStatus.NOT_FOUND);
            else return new ResponseEntity<>(booksToReturn, HttpStatus.OK);
    }

    @PostMapping("/add")
    public String addNewBook(@RequestBody Book book){
        book.setIsAvailable(true);
        book.setUser(null);
        bookService.addNewBook(book);
        return "New book is added successfully.";
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") Long id, @RequestBody Book book) throws NoSuchElementException{
        try {
            return new ResponseEntity<>(bookService.updateById(id, book), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>("Book with id=" + id + " does not exist in our database so it cannot be updated.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) throws NoSuchElementException{
        try {
            return new ResponseEntity<>(bookService.deleteById(id), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>("Book with id=" + id + " does not exist in our database so it cannot be deleted.", HttpStatus.NOT_FOUND);
        }
    }

}
