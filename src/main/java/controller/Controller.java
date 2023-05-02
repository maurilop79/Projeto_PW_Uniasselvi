package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans jogo = new JavaBeans();

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			jogos(request, response);
		} else if (action.equals("/insert")) {
			novoJogo(request, response);
		} else if (action.equals("/select")) {
			listarJogo(request, response);
		} else if (action.equals("/update")) {
			editarJogo(request, response);
		} else if (action.equals("/delete")) {
			removerJogo(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	// Listar jogos
	protected void jogos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um objeto que irá receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarJogos();
		// Encaminhar a lista ao documento jogo.jsp
		request.setAttribute("jogos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("jogo.jsp");
		rd.forward(request, response);
	}

	// Novo jogo
	protected void novoJogo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("jogo.jsp");
		// Setar as variáveis JavaBeans
		jogo.setNome(request.getParameter("nome"));
		jogo.setPlataforma(request.getParameter("plataforma"));
		jogo.setDesenvolvedor(request.getParameter("desenvolvedor"));
		// Invocar o método inserirJogo passando o objeto jogo
		dao.inserirJogo(jogo);
		// Redirecionar para o documento jogo.jsp
		response.sendRedirect("main");
	}

	// Editar jogo
	protected void listarJogo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// recebimento do id do jogo que será editado
		String idjogo = request.getParameter("idjogo");
		// Setar a variável JavaBeans
		jogo.setIdjogo(idjogo);
		// Executar o método selecionarJogo
		dao.selecionarJogo(jogo);
		// Setar os atributos do formulário com o conteúdo JavaBeans
		request.setAttribute("idjogo", jogo.getIdjogo());
		request.setAttribute("nome", jogo.getNome());
		request.setAttribute("plataforma", jogo.getPlataforma());
		request.setAttribute("desenvolvedor", jogo.getDesenvolvedor());
		// Encaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}

	protected void editarJogo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Setar as variáveis JavaBeans
		jogo.setIdjogo(request.getParameter("idjogo"));
		jogo.setNome(request.getParameter("nome"));
		jogo.setPlataforma(request.getParameter("plataforma"));
		jogo.setDesenvolvedor(request.getParameter("desenvolvedor"));
		// Executar o método alterarJogo
		dao.alterarJogo(jogo);
		// Redirecionar para o documento jogo.jsp (atualizando as alterações)
		response.sendRedirect("main");
	}

	// Remover um jogo
	protected void removerJogo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// recebimento do id do jogo a ser excluído (validador.js)
		String idjogo = request.getParameter("idjogo");
		// Setar a variavel idjogo JavaBeans
		jogo.setIdjogo(idjogo);
		// Executar o método deletarJogo (DAO) passando o objeto jogo como parâmetro
		dao.deletarJogo(jogo);
		// Redirecionar para o documento jogo.jsp (atualizando as alterações)
		response.sendRedirect("main");
	}
}
