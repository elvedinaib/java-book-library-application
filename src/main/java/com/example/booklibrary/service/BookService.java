package com.example.booklibrary.service;

import com.example.booklibrary.model.Book;
import com.example.booklibrary.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book getById(Long id) throws NoSuchElementException {
        return bookRepository.findById(id).get();
    }

    public List<Book> getByTitleOrAuthor(String string){
        List<Book> allBooks=bookRepository.findAll();
        List<Book> booksToReturn=new ArrayList<>();
        for(Book book:allBooks)
            if(book.getTitle().toLowerCase().contains(string.toLowerCase()) || book.getAuthor().toLowerCase().contains(string.toLowerCase()))
                booksToReturn.add(book);
        return booksToReturn;
    }

    public void addNewBook(Book book) {
        bookRepository.save(book);
    }

    public Book updateById(Long id, Book book) throws NoSuchElementException {
        bookRepository.findById(id).get();
        book.setId(id);
        return bookRepository.save(book);
    }

    public Book deleteById(Long id) throws NoSuchElementException{
        Book book=bookRepository.findById(id).get();
        bookRepository.deleteById(id);
        return book;
    }
}
