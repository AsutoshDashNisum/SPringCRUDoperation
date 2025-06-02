package org.AsutoSH.controller;


import org.AsutoSH.dao.BookDAO;
import org.AsutoSH.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HelloController {
    private BookDAO bookDAO;
    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
    @RequestMapping("/hello")
    public ModelAndView sayHello() {
        String mss = "hello world";
        return new ModelAndView("hello","message", mss);
    }
    @RequestMapping("/books")
    public ModelAndView listBooks() {
        List<Book> books = bookDAO.getAllBooks();
        return new ModelAndView("listBooks", "books", books);
    }
    @RequestMapping("/delete")
    public String deleteBook(@RequestParam("id") int id) {
        bookDAO.deleteBook(id);
        return "redirect:/books";  // refresh the list after deletion
    }
    @GetMapping("/add")
    public ModelAndView showAddForm() {
        return new ModelAndView("addBook", "book", new Book());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book) {
        bookDAO.addBook(book);
        return "redirect:/books";
    }
}