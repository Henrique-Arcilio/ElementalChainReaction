package com.costumizer.view;

import com.costumizer.controllers.ControlladorBotoes;
import com.costumizer.models.Elemento;
import com.costumizer.utilitarios.Elementos;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JButton botaoLibertar;
    private JButton botaoLimpar;
    private PainelCampo painelDoCampo;
    private PainelElementos painelDeElementos;

    public MainFrame(){
        //Configurações da janela
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(600, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        ControlladorBotoes ouvinte = new ControlladorBotoes(this);
        JPanel bottonsPanel = new JPanel(new GridLayout(1, 3, 20,0));
        painelDoCampo = new PainelCampo();
        painelDeElementos = new PainelElementos(ouvinte);
        painelDeElementos.setLayout(new GridLayout(0, 3));
        painelDeElementos.setBounds(40, 450, 500, 30);
        bottonsPanel.setBounds(140, 500, 300, 50);
        painelDoCampo.setBounds(40, 40, 500, 400);
        painelDoCampo.setOpaque(true);
        painelDoCampo.setBackground(Color.LIGHT_GRAY);
        botaoLibertar = new JButton("libertar!");
        botaoLimpar = new JButton("Limpar!");
        botaoLibertar.addActionListener(ouvinte);
        botaoLimpar.addActionListener(ouvinte);
        bottonsPanel.add(botaoLimpar);
        bottonsPanel.add(botaoLibertar);
        add(bottonsPanel);
        add(painelDeElementos);
        add(painelDoCampo);
        setVisible(true);
    }

    public JButton getBotaoLibertar() {
        return botaoLibertar;
    }

    public void setBotaoLibertar(JButton botaoLibertar) {
        this.botaoLibertar = botaoLibertar;
    }

    public PainelCampo getPainelDoCampo() {
        return painelDoCampo;
    }

    public void setPainelDoCampo(PainelCampo painelDoCampo) {
        this.painelDoCampo = painelDoCampo;
    }

    public PainelElementos getPainelDeElementos() {
        return painelDeElementos;
    }

    public void setPainelDeElementos(PainelElementos painelDeElementos) {
        this.painelDeElementos = painelDeElementos;
    }

    public JButton getBotaoLimpar() {
        return botaoLimpar;
    }

    public void setBotaoLimpar(JButton botaoLimpar) {
        this.botaoLimpar = botaoLimpar;
    }
}
