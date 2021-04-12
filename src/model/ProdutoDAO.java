package model;
import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
	
	private Connection connection;
	
	public ProdutoDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void inserir(Produto produto) {
		String consultaSQL = "INSERT INTO produto (NOME, PRECO, QUANTIDADE) VALUES (?, ?, ?)";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(consultaSQL);
			
			prstate.setString(1, produto.getNome());
			prstate.setDouble(2, produto.getPreco());
			prstate.setInt(3, produto.getQuantidade());
			
			prstate.execute();
			
			prstate.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			}
		}
	}
	
	public void excluir(Long id) {
		String consultaSQL = "DELETE FROM produto WHERE ID=?";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(consultaSQL);
			
			prstate.setLong(1, id);
			
			prstate.execute();
			
			prstate.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			}
		}
		
	}
	
	public void alterar(Produto produto) {
		String consultaSQL = "UPDATE produto SET NOME=?, PRECO=?, QUANTIDADE=? WHERE ID=?";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(consultaSQL);
			
			prstate.setString(1, produto.getNome());
			prstate.setDouble(2, produto.getPreco());
			prstate.setInt(3, produto.getQuantidade());
			prstate.setLong(4, produto.getId());
			
			prstate.execute();
			
			prstate.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			}
		}
			
	}
	
	public List<Produto> buscarPorNome(String nome){
		String consultaSQL = "SELECT * FROM produto WHERE NOME LIKE UPPER(?)";
		
		List<Produto> produtos = new ArrayList<Produto>();
		
		try {
			PreparedStatement prstate = connection.prepareStatement(consultaSQL);
			
			prstate.setString(1, new String("%"+nome+"%").toUpperCase());
			
			ResultSet resultadoConsulta = prstate.executeQuery();
			
			while(resultadoConsulta.next()) {
				Produto produto = new Produto();
				produto.setId(resultadoConsulta.getLong("ID"));
				produto.setNome(resultadoConsulta.getString("NOME"));
				produto.setPreco(resultadoConsulta.getDouble("PRECO"));
				produto.setQuantidade(resultadoConsulta.getInt("QUANTIDADE"));
				
				produtos.add(produto);
			}
			resultadoConsulta.close();
			prstate.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			}
		}
		
		return produtos;
		
	}
	
	public Produto buscaPorId(Long id) {
		String consultaSQL = "SELECT * FROM produto WHERE ID = ?";
		
		Produto produto = new Produto();
		
		try {
			PreparedStatement prstate = connection.prepareStatement(consultaSQL);
			
			prstate.setLong(1, id);
			
			ResultSet resultadoConsulta = prstate.executeQuery();
			
			resultadoConsulta.next();
			
			produto.setId(resultadoConsulta.getLong("ID"));
			produto.setNome(resultadoConsulta.getString("NOME"));
			produto.setPreco(resultadoConsulta.getDouble("PRECO"));
			produto.setQuantidade(resultadoConsulta.getInt("QUANTIDADE"));
			
			resultadoConsulta.close();
			prstate.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			}
		}
		
		return produto;
	}

}


