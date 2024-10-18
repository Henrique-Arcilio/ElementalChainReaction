package com.costumizer.view;

import com.costumizer.models.Elemento;
import com.costumizer.utilitarios.Elementos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class PainelCampo extends JPanel {
    private ArrayList<Elemento> elementos = new ArrayList<>();
    private Random random = new Random();
    public ArrayList<Elemento> getElementos() {
        return elementos;
    }

    public void setElementos(ArrayList<Elemento> elementos) {
        this.elementos = elementos;
    }
    public void rodarJogo(){
        Timer timer = new Timer(60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Elemento elemento : elementos){
                    if(elemento.getX() >= getWidth() - 20 || elemento.getX() <= 0){
                        elemento.setdX(elemento.getdX() * -1);
                    }else if(elemento.getY() >= getHeight() - 20 || elemento.getY() <= 0){
                        elemento.setdY(elemento.getdY() * -1);
                    }
                    int deslocamentoX = elemento.getX() + elemento.getdX();
                    int deslocamentoY = elemento.getY() + elemento.getdY();
                    elemento.setBounds(deslocamentoX, deslocamentoY, 20, 20);
                }
                verificarColisao();
                repaint();
            }
        });
        timer.start();
    }
    public void prepararElementos(Elemento elemento){
        int x = random.nextInt(470);
        int y = random.nextInt(370);
        elemento.setBounds(x, y, 20, 20);
        int velX, velY;
        do{
            velX = gerarMovimento(random);
            velY = gerarMovimento(random);
        }
        while(velX == 0 && velY == 0);
        elemento.setdX(velX);
        elemento.setdY(velY);
    }
    public  void addElementosNaTela(){
        for(Elemento elemento : elementos){
            add(elemento);
        }
    }
    public int gerarMovimento(Random random){
        int vel = random.nextInt(3);
       switch (vel){
           case 0:
               return 0;
           case 1:
               return -5;
           default:
               return 5;
       }
    }
    public void transformar(Elemento elementoI, Elemento elementoJ) {
        Elementos tipoI = elementoI.getTipo();
        Elementos tipoJ = elementoJ.getTipo();
        Elemento novoElemento = null;
        if ((tipoI.equals(Elementos.FOGO) && tipoJ.equals(Elementos.AGUA)) ||
                (tipoI.equals(Elementos.AGUA) && tipoJ.equals(Elementos.FOGO))) {
            novoElemento = new Elemento(Elementos.VAPOR, Color.DARK_GRAY, "VP");
        }
        else if ((tipoI.equals(Elementos.AR) && tipoJ.equals(Elementos.FOGO)) ||
                (tipoI.equals(Elementos.FOGO) && tipoJ.equals(Elementos.AR))) {
            novoElemento = new Elemento(Elementos.FOGO, Color.ORANGE, "FG");
        }
        else if ((tipoI.equals(Elementos.AGUA) && tipoJ.equals(Elementos.VAPOR)) ||
                (tipoI.equals(Elementos.VAPOR) && tipoJ.equals(Elementos.AGUA))) {
            novoElemento = new Elemento(Elementos.AGUA, Color.CYAN, "AG");
        }
        else if ((tipoI.equals(Elementos.FOGO) && tipoJ.equals(Elementos.VAPOR)) ||
                (tipoI.equals(Elementos.VAPOR) && tipoJ.equals(Elementos.FOGO))) {
            novoElemento = new Elemento(Elementos.AR, Color.WHITE, "AR");
        }
        if(novoElemento != null){
            remove(elementoI);
            remove(elementoJ);
            int novaPosX = (elementoI.getX() + elementoJ.getX())/2;
            int novaPosY = (elementoI.getY() + elementoJ.getY())/2;
            novoElemento.setBounds(novaPosX, novaPosY, 20, 20);
            elementos.remove(elementoI);
            elementos.remove(elementoJ);
            novoElemento.setdX(elementoI.getdX());
            novoElemento.setdY(elementoI.getdY());
            elementos.add(novoElemento);
            add(novoElemento);
            repaint();
        }

    }
    public void verificarColisao(){
        Rectangle elementoI;
        Rectangle elementoJ;
        for(int i = 0; i < elementos.size(); i++){
            elementoI = elementos.get(i).getBounds();
            for(int j = i+1; j < elementos.size(); j++){
                elementoJ = elementos.get(j).getBounds();
                if(elementoI.intersects(elementoJ)){
                    transformar(elementos.get(i), elementos.get(j));
                }
            }
        }
    }
}
