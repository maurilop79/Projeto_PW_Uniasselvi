package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	/** Módulo de Conexão **/
	// Parâmetros de Conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbgames?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "1205";

	// Método de Conexão
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// ** CRUD CREATE **/
	public void inserirJogo(JavaBeans jogo) {
		String create = "insert into jogos (nome,plataforma,desenvolvedor) values (?,?,?)";
		try {
			// Abrir a conexão
			Connection con = conectar();
			// Preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			// Substituir os parâmetros (?) pelo conteúdo das variáveis JavaBeans
			pst.setString(1, jogo.getNome());
			pst.setString(2, jogo.getPlataforma());
			pst.setString(3, jogo.getDesenvolvedor());
			// Executar a query
			pst.executeUpdate();
			// Encerrar a conexão com o banco
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/** CRUD READ **/
	public ArrayList<JavaBeans> listarJogos() {
		// Criando um objeto para acessar a classe JavaBeams
		ArrayList<JavaBeans> jogos = new ArrayList<>();
		String read = "select * from jogos order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// O laço abaixo será executado enquanto houver contatos
			while (rs.next()) {
				// Variaveis de apoio que recebem os dados do banco
				String idjogo = rs.getString(1);
				String nome = rs.getString(2);
				String plataforma = rs.getString(3);
				String desenvolvedor = rs.getString(4);
				// Populando o ArrayList
				jogos.add(new JavaBeans(idjogo, nome, plataforma, desenvolvedor));
			}
			con.close();
			return jogos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/** CRUD UPDATE **/
	// Selecionar o jogo
	public void selecionarJogo(JavaBeans jogo) {
		String read2 = "select * from jogos where idjogo = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, jogo.getIdjogo());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// Setar as variáveis JavaBeans
				jogo.setIdjogo(rs.getString(1));
				jogo.setNome(rs.getString(2));
				jogo.setPlataforma(rs.getString(3));
				jogo.setDesenvolvedor(rs.getString(4));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	//Editar o jogo
	public void alterarJogo(JavaBeans jogo) {
		String create = "update jogos set nome=?,plataforma=?,desenvolvedor=? where idjogo=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, jogo.getNome());
			pst.setString(2, jogo.getPlataforma());
			pst.setString(3, jogo.getDesenvolvedor());
			pst.setString(4, jogo.getIdjogo());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/** CRUD DELETE**/
	public void deletarJogo(JavaBeans jogo) {
		String delete = "delete from jogos where idjogo=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, jogo.getIdjogo());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
