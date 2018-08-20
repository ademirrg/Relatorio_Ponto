package br.com.koin;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.net.URL;
import net.sourceforge.htmlunit.corejs.javascript.tools.debugger.Main;

public class Tela extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	Botao botoes = new Botao();
	Page page = new Page();
	JTextField campoUser = new JTextField("ademir.garcia@koin.com.br");
	JTextField campoSenha = new JPasswordField();
		
	public void criaTela() {
		
		//Imagens
		URL iconKoin = Main.class.getResource("/icon.jpg");
		
		// Tela
		setTitle("GERADOR DE CPF/CNPJ");
		setSize(315, 225);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ImageIcon icone = new ImageIcon(iconKoin);
		setIconImage(icone.getImage());
		
		JLabel lbTextoPaginaInicial = new JLabel("INFORME O USUÁRIO E SENHA PARA CONSULTA");
		lbTextoPaginaInicial.setBounds(20,10,350,30);
		lbTextoPaginaInicial.setForeground(Color.DARK_GRAY);
		
		JLabel lbUsuario = new JLabel("USUÁRIO:");
		lbUsuario.setBounds(20,35,350,30);
		lbUsuario.setForeground(Color.DARK_GRAY);
		
		JLabel lbSenha = new JLabel("SENHA:");
		lbSenha.setBounds(20,90,350,30);
		lbSenha.setForeground(Color.DARK_GRAY);
		
		JLabel imgFundo = new JLabel();
		imgFundo.setIcon(new ImageIcon(iconKoin));
		imgFundo.setBounds(195,70,100,80);
		
		campoUser.setBounds(20,60,160,30);
		campoSenha.setBounds(20,115,160,30);
		
		campoUser.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					botoes.bt_ok.doClick();
				}
				else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					botoes.bt_sair.doClick();
				}
			}
		});
		
		campoSenha.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					botoes.bt_ok.doClick();
				}
				else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					botoes.bt_sair.doClick();
				}
			}
		});

		getContentPane().add(lbTextoPaginaInicial);
		getContentPane().add(lbUsuario);
		getContentPane().add(lbSenha);
		getContentPane().add(imgFundo).setVisible(true);
		getContentPane().add(campoUser);
		getContentPane().add(campoSenha);
	}

	public void criaBotoes() {
		botoes.definirBotoesTelaPrincipal(this, this);
	}

	public void validaCampos() {
		String user = campoUser.getText();
		String pass = campoSenha.getText();
		user = user.trim();
		pass = pass.trim();
		
		if(user.length() == 0) {
			JOptionPane.showMessageDialog(null, "O campo USUÁRIO não pode ser vazio!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
		}
		else if(pass.length() == 0) {
			JOptionPane.showMessageDialog(null, "O campo SENHA não pode ser vazio!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
		}
		else {
			PageVO.setUser(user);
			PageVO.setPass(pass);
			page.abreBrowser();
			page.consultaRelatorio();
			page.fechaBrowser();
			page.apresentaSaldoBH();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		
		case "command_sair":
			System.exit(0);
			break;
			
		case "command_ok":
			validaCampos();
			break;
		}
	}
}
