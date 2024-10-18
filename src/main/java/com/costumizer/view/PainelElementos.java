package com.costumizer.view;
import com.costumizer.models.Elemento;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PainelElementos extends JPanel {
    private ArrayList<JButton> botoes = new ArrayList<>();
    public PainelElementos(Elemento[] listaDelementos, ActionListener ouvinte){
        criarElementos(listaDelementos, ouvinte);
    }

    public void criarElementos(Elemento[] listaDeElementos, ActionListener ouvinte){
        for(Elemento elemento: listaDeElementos){
            if(elemento != null){
                JButton botao = new JButton(String.valueOf(elemento.getTipo()));
                botao.setOpaque(true);
                botao.setBackground(elemento.getCor());
                botao.addActionListener(ouvinte);
                botoes.add(botao);
                add(botao);
            }
        }
    }

    public ArrayList<JButton> getBotoes() {
        return botoes;
    }

    public void setBotoes(ArrayList<JButton> botoes) {
        this.botoes = botoes;
    }
}
