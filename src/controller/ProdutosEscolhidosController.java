package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import model.Produto;

@Named
@SessionScoped
public class ProdutosEscolhidosController implements Serializable{
	private List<Produto> listaProdutosEscolhidos;

	public List<Produto> getListaProdutosEscolhidos() {
		return listaProdutosEscolhidos;
	}

	public void setListaProdutosEscolhidos(List<Produto> listaProdutosEscolhidos) {
		this.listaProdutosEscolhidos = listaProdutosEscolhidos;
	}
	
	public void adicionarEscolhido(Produto produto) {
		if(!this.listaProdutosEscolhidos.contains(produto)) {
			this.listaProdutosEscolhidos.add(produto);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto adicionado ao carrinho!"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Este produto já está no seu carrinho!"));
		}
	}
	
	public void remover(Produto produto) {
		this.listaProdutosEscolhidos.remove(produto);
	}
	
	@PostConstruct
	public void init() {
		this.listaProdutosEscolhidos = new ArrayList<Produto>();
	}
	
}
