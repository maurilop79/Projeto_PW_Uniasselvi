package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report" })
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
		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);
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

	// Listar jogo
	protected void listarJogo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// recebimento do id do jogo que será editado
		// Setar a variável JavaBeans
		jogo.setIdjogo(request.getParameter("idjogo"));
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
	// Editar jogo
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
	// Gerar relatório em PDF
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document documento = new Document();
		try {
			// Tipo de conteúdo
			response.setContentType("apllication/pdf");
			// Nome do documento
			response.addHeader("Content-Disposition", "inline; filename=" + "jogos.pdf");
			// Criar o documento
			PdfWriter.getInstance(documento, response.getOutputStream());
			// Abrir o documento
			documento.open();
			documento.add(new Paragraph("Lista de jogos: "));
			documento.add(new Paragraph(" "));
			// Criar uma tabela
			PdfPTable tabela = new PdfPTable(3);
			// Cabeçalho
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Plataforma"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Desenvolvedor"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			// Incrementar a tabela com os jogos
			ArrayList<JavaBeans> lista = dao.listarJogos();
			for (int i = 0; i < lista.size(); i++ ) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getPlataforma());
				tabela.addCell(lista.get(i).getDesenvolvedor());
			}
			documento.add(tabela);
			documento.close();
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}
}
