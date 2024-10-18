package com.costumizer.view;

import com.costumizer.controllers.ControlladorBotoes;
import com.costumizer.models.Elemento;
import com.costumizer.utilitarios.Elementos;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JButton botaoLibertar;
    private PainelCampo painelDoCampo;
    private PainelElementos painelDeElementos;
    Elemento agua = new Elemento(Elementos.AGUA, Color.CYAN);
    Elemento ar = new Elemento(Elementos.AR, Color.WHITE);
    Elemento fogo = new Elemento(Elementos.FOGO, Color.ORANGE);
    public MainFrame(){
        //Configurações da janela
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(600, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        ControlladorBotoes ouvinte = new ControlladorBotoes(this);
        Elemento[] elementos = {agua, ar, fogo};
        painelDeElementos = new PainelElementos(elementos, ouvinte);
        JPanel bottonsPanel = new JPanel(new GridLayout(1, 3));
        painelDoCampo = new PainelCampo();
        painelDeElementos.setLayout(new GridLayout(0, 3));
        painelDeElementos.setBounds(40, 470, 500, 30);
        bottonsPanel.setBounds(40, 500, 500, 60);
        painelDoCampo.setBounds(40, 40, 500, 400);
        painelDoCampo.setOpaque(true);
        painelDoCampo.setBackground(Color.LIGHT_GRAY);
        botaoLibertar = new JButton("libertar!");
        botaoLibertar.addActionListener(ouvinte);
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
}
