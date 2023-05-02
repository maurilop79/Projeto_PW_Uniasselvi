package model;

public class JavaBeans {
	private String idjogo;
	private String nome;
	private String plataforma;
	private String desenvolvedor;
	
	public JavaBeans() {
		super();
	}
	
	public JavaBeans(String idjogo, String nome, String plataforma, String desenvolvedor) {
		super();
		this.idjogo = idjogo;
		this.nome = nome;
		this.plataforma = plataforma;
		this.desenvolvedor = desenvolvedor;
	}

	public String getIdjogo() {
		return idjogo;
	}
	public void setIdjogo(String idjogo) {
		this.idjogo = idjogo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	public String getDesenvolvedor() {
		return desenvolvedor;
	}
	public void setDesenvolvedor(String desenvolvedor) {
		this.desenvolvedor = desenvolvedor;
	}
}
