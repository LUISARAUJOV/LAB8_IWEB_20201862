package com.example.lab8_iweb_2020186.Servlets;

import com.example.lab8_iweb_2020186.Beans.libro;
import com.example.lab8_iweb_2020186.Beans.editorial;
import com.example.lab8_iweb_2020186.Beans.genero;
import com.example.lab8_iweb_2020186.Daos.LibroDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "LibroServlet", urlPatterns = {"/LibroServlet"})
public class LibroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "listar" : request.getParameter("action");
        LibroDao libroDao = new LibroDao();

        switch (action) {
            case "listar":
                ArrayList<libro> listaLibros = libroDao.listar();
                request.setAttribute("listaLibros", listaLibros);
                request.getRequestDispatcher("listaLibros.jsp").forward(request, response);
                break;

            case "nuevo":
                ArrayList<editorial> listaEditorialesCrear = libroDao.listarEditoriales();
                ArrayList<genero> listaGenerosCrear = libroDao.listarGeneros();
                request.setAttribute("listaEditoriales", listaEditorialesCrear);
                request.setAttribute("listaGeneros", listaGenerosCrear);
                request.getRequestDispatcher("crearLibro.jsp").forward(request, response);
                break;

            case "borrar":
                int id = Integer.parseInt(request.getParameter("id"));
                try {
                    libroDao.borrar(id);
                    response.sendRedirect("LibroServlet?action=listar");
                } catch (SQLException e) {
                    request.setAttribute("error", e.getMessage());
                    ArrayList<libro> lista = libroDao.listar();
                    request.setAttribute("listaLibros", lista);
                    request.getRequestDispatcher("listaLibros.jsp").forward(request, response);
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        LibroDao libroDao = new LibroDao();

        if ("crear".equals(action)) {
            libro lib = new libro();
            lib.setTitulo(request.getParameter("titulo"));
            lib.setAutor(request.getParameter("autor"));
            lib.setPaginas(Integer.parseInt(request.getParameter("paginas")));
            lib.setPremios(Integer.parseInt(request.getParameter("premios")));

            editorial ed = new editorial();
            ed.setId(Integer.parseInt(request.getParameter("editorial")));
            lib.setEditorial(ed);

            genero gen = new genero();
            gen.setId(Integer.parseInt(request.getParameter("genero")));
            lib.setGenero(gen);

            try {
                libroDao.crear(lib);
                response.sendRedirect("LibroServlet?action=listar");
            } catch (SQLException e) {
                request.setAttribute("error", e.getMessage());
                ArrayList<editorial> listaEditoriales = libroDao.listarEditoriales();
                ArrayList<genero> listaGeneros = libroDao.listarGeneros();
                request.setAttribute("listaEditoriales", listaEditoriales);
                request.setAttribute("listaGeneros", listaGeneros);
                request.getRequestDispatcher("crearLibro.jsp").forward(request, response);
            }
        }
    }
}
