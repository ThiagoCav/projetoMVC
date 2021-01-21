package br.edu.ifsp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.edu.ifsp.conexao.Conexao;
import br.edu.ifsp.modelo.Pessoa;

public class PessoaDAO {
	
	public Conexao con = null;
	ResultSet rs;
	ArrayList<Pessoa> lista = new ArrayList<>();
	
	public void salvarPessoa(Pessoa pessoa) {
		
		try {
		
			this.con = Conexao.getInstance();
		
			String sql = "insert into pessoa (nomecompleto, idade) values (?, ?)";
			PreparedStatement pstm = con.getConexao().prepareStatement(sql);
			pstm.setInt(2, pessoa.getIdade());
			pstm.setString(1, pessoa.getNome());
			pstm.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Registro salvo com sucesso !");
			
		} catch(SQLException e) {
			
			System.out.println("Problema ao inserir uma pessoa");
			e.printStackTrace();
		}	
	}
	
	public ArrayList<Pessoa> PesquisarPessoa(){
		
		try {
			
			this.con = Conexao.getInstance();
			String sql = "select * from pessoa order by id asc";
			PreparedStatement pstm = con.getConexao().prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setId(rs.getInt("id"));
				pessoa.setNome(rs.getString("nomecompleto"));
				pessoa.setIdade(rs.getInt("idade"));
				
				lista.add(pessoa);
				
		}
			
			
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Funcionario Pesquisar:" + e);
		}
		
		return lista;
	}
	
	public void AlterarPessoa(Pessoa objpessoa) {
		
			try {
			
			this.con = Conexao.getInstance();
			String sql = "update pessoa set nomecompleto = ?, idade = ? where id = ? ";
			PreparedStatement pstm = con.getConexao().prepareStatement(sql);
			pstm.setString(1, objpessoa.getNome());
			pstm.setInt(2, objpessoa.getIdade());
			pstm.setInt(3, objpessoa.getId());
			pstm.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Registro alterado com sucesso!");
			
		
			}catch(SQLException erro) {
				JOptionPane.showMessageDialog(null, "Funcionario ALTERAR" + erro);
		}
	}
	
	public void ExcluirPessoa(Pessoa objpessoa) {
		
		try {
			
			this.con = Conexao.getInstance();
			String sql = "delete from pessoa where id = ?";
			PreparedStatement pstm = con.getConexao().prepareStatement(sql);
			pstm.setInt(1, objpessoa.getId());
			pstm.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Registro excluido  com sucesso!");
			
			
		}catch(SQLException erro) {
			JOptionPane.showMessageDialog(null, "Funcionario EXCLUIR" + erro);
		}
		
	}
}
	

