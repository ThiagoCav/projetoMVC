package br.edu.ifsp.tela;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

import br.edu.ifsp.dao.PessoaDAO;
import br.edu.ifsp.modelo.Pessoa;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Panel;
import javax.swing.ImageIcon;

public class FramePrincipal extends JFrame {
	
	private JTextField tfNomeCompleto;
	private JTextField tfIdade;
	private JButton btnSalvarPessoa;
	private JButton btnLimpar;
	private JTable tabelaPessoa;
	private JTextField textId;
	
	/**
	 * Create the frame.
	 */
	public FramePrincipal() {
		setAutoRequestFocus(false);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 353, 489);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome Completo*:");
		lblNewLabel.setBounds(10, 41, 128, 14);
		getContentPane().add(lblNewLabel);
		
		tfNomeCompleto = new JTextField();
		tfNomeCompleto.setBounds(148, 38, 187, 20);
		getContentPane().add(tfNomeCompleto);
		tfNomeCompleto.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Idade*:");
		lblNewLabel_1.setBounds(10, 66, 63, 14);
		getContentPane().add(lblNewLabel_1);
		
		tfIdade = new JTextField();
		tfIdade.setBounds(148, 63, 38, 20);
		getContentPane().add(tfIdade);
		tfIdade.setColumns(10);
		
		btnSalvarPessoa = new JButton("Salvar");
		btnSalvarPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tfNomeCompleto.getText().equals("") || tfIdade.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Para salvar, é necessario informar um nome e uma idade !");
					
				}else{
					SalvarPessoa();
					ListarValores();
					LimparCampos();
				}
			}
		});
		btnSalvarPessoa.setBounds(10, 94, 89, 23);
		getContentPane().add(btnSalvarPessoa);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LimparCampos();
			}
		});
		btnLimpar.setBounds(10, 128, 89, 23);
		getContentPane().add(btnLimpar);
		
		/*JButton btnPesquisar = new JButton("Pesquisar Salvos");
		btnPesquisar.setEnabled(false);
		btnPesquisar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				ListarValores();
			}
		});
		btnPesquisar.setBounds(194, 426, 128, 23);
		getContentPane().add(btnPesquisar);*/
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 325, 223);
		getContentPane().add(scrollPane);
		
		tabelaPessoa = new JTable();
		tabelaPessoa.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nome", "Idade"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			
		});
		scrollPane.setViewportView(tabelaPessoa);
		
		ListarValores();
		
		JLabel lblNewLabel_2 = new JLabel("Codigo:");
		lblNewLabel_2.setBounds(10, 11, 63, 14);
		getContentPane().add(lblNewLabel_2);
		
		textId = new JTextField();
		textId.setEnabled(false);
		textId.setBounds(147, 7, 38, 20);
		getContentPane().add(textId);
		textId.setColumns(10);
		
		JButton btnCarregarRegistro = new JButton("Carregar");
		btnCarregarRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	
					CarregarRegistros();
				}
				
		
		});
		btnCarregarRegistro.setBounds(10, 405, 117, 30);
		getContentPane().add(btnCarregarRegistro);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(textId.getText().equals("") || tfNomeCompleto.getText().equals("") || tfIdade.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Para alterar, é necessario selecionar um registro salvo na Lista abaixo, e clicar em Carregar. Após isso realize a modificação e clique em Alterar novamente!");
					
				}else{
					AlterarPessoa();
					ListarValores();
					LimparCampos();
				}
			}
		});
		btnAlterar.setBounds(127, 94, 89, 23);
		getContentPane().add(btnAlterar);
		
		JButton btnExluir = new JButton("Excluir");
		btnExluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textId.getText().equals("") || tfNomeCompleto.getText().equals("") || tfIdade.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Para excluir, é necessario selecionar um registro salvo na Lista abaixo, e clicar em Carregar. Após isso clique em Excluir novamente !");
					
				}else{
					DeletarPessoa();
					ListarValores();
					LimparCampos();
				}
			}	
		});
		btnExluir.setBounds(246, 94, 89, 23);
		getContentPane().add(btnExluir);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Thiago\\Desktop\\crud_novo.png"));
		lblNewLabel_3.setBounds(219, 405, 94, 44);
		getContentPane().add(lblNewLabel_3);
		
	}

	public JTextField getTfNomeCompleto() {
		return tfNomeCompleto;
	}

	public void setTfNomeCompleto(JTextField tfNomeCompleto) {
		this.tfNomeCompleto = tfNomeCompleto;
	}

	public JTextField getTfIdade() {
		return tfIdade;
	}

	public void setTfIdade(JTextField tfIdade) {
		this.tfIdade = tfIdade;
	}

	public JButton getBtnSalvarPessoa() {
		return btnSalvarPessoa;
	}

	public void setBtnSalvarPessoa(JButton btnSalvarPessoa) {
		this.btnSalvarPessoa = btnSalvarPessoa;
	}

	public JButton getBtnLimpar() {
		return btnLimpar;
	}

	public void setBtnLimpar(JButton btnLimpar) {
		this.btnLimpar = btnLimpar;
	}
	
	private void ListarValores() {

		try {
			
			PessoaDAO pessoadao = new PessoaDAO();
			
			DefaultTableModel model = (DefaultTableModel) tabelaPessoa.getModel();
			model.setNumRows(0);
			
			ArrayList<Pessoa> lista = pessoadao.PesquisarPessoa();
			
			for(int num = 0; num < lista.size(); num ++ ) {
				model.addRow(new Object[] {
						lista.get(num).getId(),
						lista.get(num).getNome(),
						lista.get(num).getIdade()
				});
						
			}
			
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Listar valores" + erro);
		}
	}
	
	private void CarregarRegistros() {
		
	
		int setar = tabelaPessoa.getSelectedRow();
		
		if(setar < 0) {
			JOptionPane.showMessageDialog(null, "Um registro salvo deve ser selecionado para poder Carrega-lo e manipula-lo! ");
			
		}else {
		
			textId.setText(tabelaPessoa.getModel().getValueAt(setar, 0).toString());
			tfNomeCompleto.setText(tabelaPessoa.getModel().getValueAt(setar, 1).toString());
			tfIdade.setText(tabelaPessoa.getModel().getValueAt(setar, 2).toString());
		}
		
	}
	
	private void LimparCampos() {
		textId.setText("");
		tfNomeCompleto.setText("");
		tfIdade.setText("");
		tfNomeCompleto.requestFocus();
	}
	
	private void AlterarPessoa() {
		int id;
		String nomecompleto;
		int idade;
		
		id = Integer.parseInt(textId.getText());
		nomecompleto = tfNomeCompleto.getText();
		idade = Integer.parseInt(tfIdade.getText());
		
		Pessoa objpessoa = new Pessoa();
		objpessoa.setId(id);
		objpessoa.setNome(nomecompleto);
		objpessoa.setIdade(idade);
		
		PessoaDAO pessoaDao = new PessoaDAO();
		pessoaDao.AlterarPessoa(objpessoa);
		
	}
	
	private void DeletarPessoa() {
		int id;
	
		id = Integer.parseInt(textId.getText());
		
		Pessoa objpessoa = new Pessoa();
		objpessoa.setId(id);
		
		PessoaDAO pessoaDao = new PessoaDAO();
		pessoaDao.ExcluirPessoa(objpessoa);
		
	}
	
	private void SalvarPessoa() {
		
		
		String nomecompleto;
		int idade;
		
		//id = Integer.parseInt(textId.getText());
		nomecompleto = tfNomeCompleto.getText();
		idade = Integer.parseInt(tfIdade.getText());
		
		Pessoa objpessoa = new Pessoa();
		//objpessoa.setId(id);
		objpessoa.setNome(nomecompleto);
		objpessoa.setIdade(idade);
		
		PessoaDAO pessoaDao = new PessoaDAO();
		pessoaDao.salvarPessoa(objpessoa);
	}
}
