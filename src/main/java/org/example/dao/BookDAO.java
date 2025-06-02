package org.example.dao;

import org.example.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookDAO {

    private JdbcTemplate jdbcTemplate;

    // Setter injection
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAllBooks() {
        String sql = "SELECT * FROM books";
        return jdbcTemplate.query(sql, new BookMapper());
    }

    public void addBook(Book book) {
        String sql = "INSERT INTO books (title, author) VALUES (?, ?)";
        jdbcTemplate.update(sql, book.getTitle(), book.getAuthor());
    }

    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // Mapper for converting ResultSet to Book object
    private static final class BookMapper implements RowMapper<Book> {
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            return book;
        }
    }
}
