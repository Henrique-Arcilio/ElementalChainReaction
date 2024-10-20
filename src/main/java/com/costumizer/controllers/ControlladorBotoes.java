package com.costumizer.controllers;

import com.costumizer.models.Elemento;
import com.costumizer.models.ElementoFactory;
import com.costumizer.utilitarios.Elementos;
import com.costumizer.view.MainFrame;
import com.costumizer.view.PainelCampo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControlladorBotoes  implements ActionListener {
    private MainFrame mainFrame;

    public ControlladorBotoes(MainFrame mainFrame){
        this.mainFrame = mainFrame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        JButton botaoApertado = (JButton) e.getSource();
        JButton botaoLibertar = mainFrame.getBotaoLibertar();
        JButton botaoLimpar = mainFrame.getBotaoLimpar();
        PainelCampo painelCampo = mainFrame.getPainelDoCampo();

        if (botaoApertado.equals(botaoLibertar)) {
            painelCampo.rodarJogo();
        }else if (botaoApertado.equals(botaoLimpar)){
            painelCampo.encerrar();
        }else{
            String elementoApertado = botaoApertado.getText();
            ArrayList<Elemento> elementos = mainFrame.getPainelDoCampo().getElementos();
            if (elementos.size() < 10) {
                Elemento elemento = ElementoFactory.criarElemento(elementoApertado);;
                if(elemento != null){
                    elementos.add(elemento);
                    painelCampo.prepararElementos(elemento);
                    painelCampo.addElementosNaTela();
                    painelCampo.repaint();
                    System.out.println("1");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Máximo de elementos atingido");
            }
        }
    }
}
