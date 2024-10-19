package com.costumizer.view;
import com.costumizer.models.Elemento;
import com.costumizer.utilitarios.Elementos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class PainelElementos extends JPanel {
    private ArrayList<JButton> botoes = new ArrayList<>();

    Elemento agua = new Elemento(Elementos.AGUA, Color.CYAN);
    Elemento terra = new Elemento(Elementos.TERRA, new Color(82, 40, 2));
    Elemento fogo = new Elemento(Elementos.FOGO, Color.ORANGE);
    Elemento ar = new Elemento(Elementos.AR, Color.white);

    public PainelElementos(ActionListener ouvinte){
        Elemento[] elementos = {ar, agua, terra, fogo};
        criarBotoes(elementos, ouvinte);
    }

    public void criarBotoes(Elemento[] listaDeElementos, ActionListener ouvinte){
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
