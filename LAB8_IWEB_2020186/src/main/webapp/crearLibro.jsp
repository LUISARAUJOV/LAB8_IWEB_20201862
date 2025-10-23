<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab8_iweb_2020186.Beans.editorial" %>
<%@ page import="com.example.lab8_iweb_2020186.Beans.genero" %>
<!DOCTYPE html>
<html>
<head>
    <title>Crear Libro</title>
</head>
<body>
    <h1>Crear Nuevo Libro</h1>

    <% if (request.getAttribute("error") != null) { %>
        <div>
            Error: <%= request.getAttribute("error") %>
        </div>
    <% } %>

    <div>
        <form method="post" action="LibroServlet">
            <input type="hidden" name="action" value="crear">

            <div>
                <label for="titulo">Título:</label>
                <input type="text" id="titulo" name="titulo" required>
            </div>

            <div>
                <label for="autor">Autor:</label>
                <input type="text" id="autor" name="autor" required>
            </div>

            <div>
                <label for="paginas">Páginas:</label>
                <input type="number" id="paginas" name="paginas" min="1" required>
            </div>

            <div>
                <label for="premios">Premios:</label>
                <input type="number" id="premios" name="premios" min="0" required>
            </div>

            <div>
                <label for="genero">Género:</label>
                <select id="genero" name="genero" required>
                    <option value="">Seleccione un género</option>
                    <%
                        ArrayList<genero> listaGeneros = (ArrayList<genero>) request.getAttribute("listaGeneros");
                        if (listaGeneros != null) {
                            for (genero gen : listaGeneros) {
                    %>
                    <option value="<%= gen.getId() %>"><%= gen.getNombre() %></option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>

            <div>
                <label for="editorial">Editorial:</label>
                <select id="editorial" name="editorial" required>
                    <option value="">Seleccione una editorial</option>
                    <%
                        ArrayList<editorial> listaEditoriales = (ArrayList<editorial>) request.getAttribute("listaEditoriales");
                        if (listaEditoriales != null) {
                            for (editorial ed : listaEditoriales) {
                    %>
                    <option value="<%= ed.getId() %>"><%= ed.getNombre() %></option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>

            <div>
                <button type="submit">Guardar Libro</button>
                <a href="LibroServlet?action=listar">Cancelar</a>
            </div>
        </form>
    </div>
</body>
</html>
