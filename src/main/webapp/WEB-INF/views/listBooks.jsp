<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>Book List</h2>
<a href="add">Add New Book</a>
<table border="1">
<tr><th>ID</th><th>Title</th><th>Author</th><th>Action</th></tr>
<c:forEach var="book" items="${books}">
  <tr>
    <td>${book.id}</td>
    <td>${book.title}</td>
    <td>${book.author}</td>
    <td><a href="delete?id=${book.id}">Delete</a></td>
  </tr>
</c:forEach>
</table>
