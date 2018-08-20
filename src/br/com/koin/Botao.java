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
	
	int comptoBt = 75;//Comprimento do botão
	int posH = 20;//Posição horizontal do botão
	int altBt = 30;//Altura do botão
	int posV = 155;//Posição vertical do botão
	Color corBt = Color.LIGHT_GRAY;//Cor do botão

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

