package br.com.koin;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Botao {
	JButton bt_ok;
	JButton bt_sair;
	
	int comptoBt = 75;//Comprimento do bot�o
	int posH = 20;//Posi��o horizontal do bot�o
	int altBt = 30;//Altura do bot�o
	int posV = 155;//Posi��o vertical do bot�o
	Color corBt = Color.LIGHT_GRAY;//Cor do bot�o

	public void definirBotoesTelaPrincipal(ActionListener tela,JFrame frame) {
		bt_ok = new JButton("OK");
		bt_ok.setBounds(posH, posV, comptoBt, altBt);
		bt_ok.addActionListener(tela);
		bt_ok.setActionCommand("command_ok");
		bt_ok.setBackground(corBt);
		bt_ok.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					bt_ok.doClick();
				}
				else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					bt_sair.doClick();
				}
			}
		});
		
		bt_sair = new JButton("Sair");
		bt_sair.setBounds(105, posV, comptoBt, altBt);
		bt_sair.addActionListener(tela);
		bt_sair.setActionCommand("command_sair");
		bt_sair.setBackground(corBt);
		bt_sair.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					bt_sair.doClick();
				}
			}
		});
		
		frame.add(bt_ok);
		frame.add(bt_sair);
	}
 }

