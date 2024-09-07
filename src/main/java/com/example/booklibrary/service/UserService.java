package com.example.booklibrary.service;

import com.example.booklibrary.model.Book;
import com.example.booklibrary.model.User;
import com.example.booklibrary.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookService bookService;

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User findById(Long id) throws NoSuchElementException {
        return userRepository.findById(id).get();
    }

    public void addNewUser(User user) {
        userRepository.save(user);
    }

    public User updateById(Long id, User user) throws NoSuchElementException{
        userRepository.findById(id).get();
        user.setId(id);
        return userRepository.save(user);
    }

    public User deleteById(Long id) throws NoSuchElementException{
        User user=userRepository.findById(id).get();
        List<Book> books = user.getBooks();
        for(Book book:books)
            book.setUser(null);
        userRepository.deleteById(id);
        return user;
    }
}
