package com.rados.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.rados.dao.FilmoviDAO;
import com.rados.model.Film;
import com.sun.org.apache.xalan.internal.xsltc.dom.Filter;

@WebServlet("/FilmoviControllerServlet")
public class FilmoviControllerServlet extends HttpServlet {
	FilmoviDAO db;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		db = new FilmoviDAO();
		super.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String command = request.getParameter("command");
			if (command == null) {
				response.sendRedirect("unos.jsp");
			}

			switch (command) {
			case "ADD":
				dodaj(response, request);
				listaFilmova(response, request);
				break;
			case "FILTER":
				filterLista(request, response);
				break;
			case "DELETE":
				deleteMovie(request, response);
				break;
			case "LOAD":
				loadDataMovie(request, response);
				break;
			case "UPDATE":
				updateMovie(request, response);
				break;
			case "TRAILER":
				movieTrailer(request, response);
				break;
			case "ADDTRAILER":
				addTrailer(request, response);
				break;
			case "OPIS":
				opisFilma(request, response);
				break;
			case "DODAJOPIS":
				dodajOpisFilma(request,response);
				break;
			default:
				listaFilmova(response, request);
				break;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			FileItemFactory factory= new DiskFileItemFactory();
			ServletFileUpload sf= new ServletFileUpload(factory);
			List<FileItem> multiFileItems= sf.parseRequest((RequestContext) req);
			for (FileItem fileItem : multiFileItems) {
				fileItem.write(new File("C:\\Users\\Rados\\eclipse-workspace\\FilmoviWebApp"+fileItem));
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("file uploaded");
	}

	private void dodajOpisFilma(HttpServletRequest request, HttpServletResponse response) {
		db.loadDriver();
		db.openConnection();
		String idFilma=request.getParameter("idFilma");
		
		
	}

	private void opisFilma(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		db.loadDriver();
		db.openConnection();
		String idfilma = request.getParameter("idFilma");
		Film film = db.opisFilma(idfilma);
		if (film.getOpis() == null) {
			request.setAttribute("opis", film);
			RequestDispatcher rq= request.getRequestDispatcher("opis.jsp");
			rq.forward(request, response);
		}
		request.setAttribute("opis1", film);
		RequestDispatcher rq= request.getRequestDispatcher("detaljanOpis.jsp");
		rq.forward(request, response);

	}

	private void addTrailer(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		db.loadDriver();
		db.openConnection();
		int id = Integer.parseInt(request.getParameter("idFilma"));
		String trailer = request.getParameter("linkT");
		db.addTrailer(id, trailer);
		listaFilmova(response, request);

	}

	private void movieTrailer(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		db.loadDriver();
		db.openConnection();
		String idFilma = request.getParameter("idFilma");
		Film film = db.getMovieTralier(idFilma);
		if (film.getTrailer() == null) {
			request.setAttribute("fil", film);
			RequestDispatcher rq = request.getRequestDispatcher("trailer.jsp");
			rq.forward(request, response);
		}
		request.setAttribute("fil", film);
		RequestDispatcher rq = request.getRequestDispatcher("video.jsp");
		rq.forward(request, response);

	}

	private void loadDataMovie(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		db.loadDriver();
		db.openConnection();
		String idFilma = request.getParameter("idFilma");
		Film film = db.loadData(idFilma);
		request.setAttribute("F", film);
		RequestDispatcher rq = request.getRequestDispatcher("update.jsp");
		rq.forward(request, response);

	}

	private void updateMovie(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		db.loadDriver();
		db.openConnection();

		int id = Integer.parseInt(request.getParameter("idFilma"));
		String naslov = request.getParameter("naslov");
		int trajanje = Integer.parseInt(request.getParameter("trajanje"));
		int godina = Integer.parseInt(request.getParameter("godina"));
		String zanr = request.getParameter("zanr");
		String aka = request.getParameter("aka");

		Film film = new Film(id, naslov, trajanje, godina, zanr, aka);

		db.update(film);
		listaFilmova(response, request);

	}

	private void deleteMovie(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		db.loadDriver();
		db.openConnection();
		String idFilma = request.getParameter("idFilma");
		db.deleteMovie(idFilma);
		listaFilmova(response, request);

	}

	private void filterLista(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		db.loadDriver();
		db.openConnection();
		ArrayList<Film> lista = db.getFilteredList();
		request.setAttribute("FILTER", lista);
		RequestDispatcher rq = request.getRequestDispatcher("filter.jsp");
		rq.forward(request, response);

	}

	private void listaFilmova(HttpServletResponse response, HttpServletRequest request)
			throws SQLException, ServletException, IOException {
		db.loadDriver();
		db.openConnection();
		ArrayList<Film> lista = db.getAllMovies();

		request.setAttribute("LISTA", lista);
		RequestDispatcher rq = request.getRequestDispatcher("glavna.jsp");
		rq.forward(request, response);

	}

	private void dodaj(HttpServletResponse response, HttpServletRequest request)
			throws IOException, ServletException, SQLException {

		db.loadDriver();
		db.openConnection();
		String naslov = request.getParameter("naslov");
		int trajanje = Integer.parseInt(request.getParameter("trajanje"));
		int godina = Integer.parseInt(request.getParameter("godina"));
		String zanr = request.getParameter("zanr");
		String aka = request.getParameter("aka");

		Film film = new Film(naslov, trajanje, godina, zanr, aka);

		db.dodaj(film);

	}

}
