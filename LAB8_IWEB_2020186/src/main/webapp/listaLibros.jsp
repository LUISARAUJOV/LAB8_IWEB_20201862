<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab8_iweb_2020186.Beans.libro" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Libros</title>
</head>
<body>
    <h1>Listado de Libros</h1>

    <% if (request.getAttribute("error") != null) { %>
        <div>
            Error: <%= request.getAttribute("error") %>
        </div>
    <% } %>

    <div>
        <a href="LibroServlet?action=nuevo">Crear Nuevo Libro</a>
    </div>

    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Título</th>
                <th>Autor</th>
                <th>Género</th>
                <th>Editorial</th>
                <th>Dirección Editorial</th>
                <th>Precio</th>
                <th>Fecha Publicación</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <%
                ArrayList<libro> listaLibros = (ArrayList<libro>) request.getAttribute("listaLibros");
                if (listaLibros != null && !listaLibros.isEmpty()) {
                    for (libro lib : listaLibros) {
            %>
            <tr>
                <td><%= lib.getId() %></td>
                <td><%= lib.getTitulo() %></td>
                <td><%= lib.getAutor() %></td>
                <td><%= lib.getGenero().getNombre() %></td>
                <td><%= lib.getEditorial().getNombre() %></td>
                <td><%= lib.getEditorial().getDireccion() %></td>
                <td>S/. <%= String.format("%.2f", lib.getPrecio()) %></td>
                <td><%= lib.getFechaPublicacion() %></td>
                <td>
                    <a href="LibroServlet?action=editar&id=<%= lib.getId() %>">Editar</a>
                    <a href="LibroServlet?action=borrar&id=<%= lib.getId() %>" 
                       onclick="return confirm('¿Está seguro de borrar este libro?')">Borrar</a>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="9">No hay libros registrados</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
