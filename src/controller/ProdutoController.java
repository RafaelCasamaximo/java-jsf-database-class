package controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import model.Produto;
import model.ProdutoDAO;

@Named
@ViewScoped
public class ProdutoController implements Serializable{
	
	private Produto produto;
	private List<Produto> listaProdutos;
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}
	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public void buscarPorNome() {
		ProdutoDAO dao = new ProdutoDAO();
		this.listaProdutos = dao.buscarPorNome(this.produto.getNome());
	}
	
	public void remover(Produto produto) {
		long idProduto = produto.getId();
		ProdutoDAO dao = new ProdutoDAO();
		dao.excluir(idProduto);
		this.listaProdutos.remove(produto);
	}
	
	public void alterar(Produto produto) {
		ProdutoDAO dao = new ProdutoDAO();
		dao.alterar(produto);
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto alterado com sucesso"));
	}
	
	public void adicionar() {
		ProdutoDAO dao = new ProdutoDAO();
		dao.inserir(this.produto);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto inserido com sucesso"));
	}
	
	@PostConstruct
	public void init() {
		this.produto = new Produto();
	}
	
}
