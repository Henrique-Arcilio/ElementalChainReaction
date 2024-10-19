package com.costumizer.view;

import com.costumizer.models.Elemento;
import com.costumizer.models.ElementoComposto;
import com.costumizer.utilitarios.Compostos;
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
    private Timer timer;

    public ArrayList<Elemento> getElementos() {
        return elementos;
    }

    public void setElementos(ArrayList<Elemento> elementos) {
        this.elementos = elementos;
    }
    public void rodarJogo(){
        timer = new Timer(60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Elemento elemento : elementos){
                    if(elemento.getX() >= getWidth() - 30 || elemento.getX() <= 0){
                        elemento.setdX(elemento.getdX() * -1);
                    }else if(elemento.getY() >= getHeight() - 30 || elemento.getY() <= 0){
                        elemento.setdY(elemento.getdY() * -1);
                    }
                    mover(elemento);
                }
                repaint();
                verificarColisao();
            }
        });
        timer.start();
    }
    public void encerrar(){
        this.removeAll();
        elementos.clear();
        repaint();
        if (timer != null) {
            timer.stop();
        }
    }

    public void prepararElementos(Elemento elemento){
        int x = random.nextInt(460);
        int y = random.nextInt(360);
        elemento.setBounds(x, y, 30, 30);
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
        String formula = gerarFormula(elementoI, elementoJ);
        Elemento novoElemento = null;
        System.out.println(formula);
        if ((elementoI instanceof ElementoComposto && elementoJ.getClass() == Elemento.class)) {
            novoElemento = ((ElementoComposto) elementoI).reagirComElemento(elementoJ);
        } else if (elementoJ instanceof ElementoComposto && elementoI.getClass() == Elemento.class) {
            novoElemento = ((ElementoComposto) elementoJ).reagirComElemento(elementoI);
        } else {
            novoElemento = criarElementoComposto(elementoI, elementoJ, formula);
        }
        //O que ocorre pois colisao
        if(novoElemento != null) {
            reconfigurarElementos(elementoI, elementoJ, novoElemento);
            //Não houve reaçao(movimentos invertem).
        }else{
            colisaoSemReacao(elementoI, elementoJ);
        }
    }

    public Elemento criarElementoComposto(Elemento elementoI,Elemento elementoJ,String formula){
        Elemento novoElemento = null;
        if (formula.equals(Compostos.VAPOR.getFormula())) {
            novoElemento = new ElementoComposto(Compostos.VAPOR, new Color(225, 249, 250), "VAP", elementoI, elementoJ);
        } else if (formula.equals(Compostos.MAGMA.getFormula())) {
            novoElemento = new ElementoComposto(Compostos.MAGMA, new Color(255, 72, 0), "MAG", elementoI, elementoJ);
        } else if (formula.equals(Compostos.LAMA.getFormula())) {
            novoElemento =  new ElementoComposto(Compostos.LAMA, new Color(92, 85, 53), "LAM", elementoI, elementoJ);
        } else if (formula.equals(Compostos.NUVEM.getFormula())) {
            novoElemento = new ElementoComposto(Compostos.NUVEM, new Color(107, 107, 107), "NUV", elementoI, elementoJ);
        }
        return novoElemento;
    }
    public void colisaoSemReacao(Elemento elementoI, Elemento elementoJ){
        Rectangle recElementoI = elementoI.getBounds();
        Rectangle rectElementoJ = elementoJ.getBounds();
        int dxElementoI = elementoI.getdX();
        int dyElementoI = elementoI.getdY();
        elementoI.setdX(elementoJ.getdX());
        elementoI.setdY(elementoJ.getdY());
        elementoJ.setdX(dxElementoI);
        elementoJ.setdY(dyElementoI);
        while(recElementoI.intersects(rectElementoJ)){
            mover(elementoI);
            mover(elementoJ);
            //Atualizando a informação dos bounds para o loop verificar
            recElementoI = elementoI.getBounds();
            rectElementoJ = elementoJ.getBounds();
        }
    }
    public void mover(Elemento elemento){
        elemento.setLocation(elemento.getX() + elemento.getdX(), elemento.getY() + elemento.getdY());
    }
    public void reconfigurarElementos(Elemento elementoI, Elemento elementoJ, Elemento novoElemento){
        int novaPosX = (elementoI.getX() + elementoJ.getX()) / 2;
        int novaPosY = (elementoI.getY() + elementoJ.getY()) / 2;
        novoElemento.setBounds(novaPosX, novaPosY, 30, 30);
        novoElemento.setdX(elementoI.getdX());
        novoElemento.setdY(elementoI.getdY());
        System.out.print(novaPosX + " " + novaPosY);
        System.out.print(novoElemento.getSigla());
        remove(elementoI);
        remove(elementoJ);
        elementos.remove(elementoI);
        elementos.remove(elementoJ);
        elementos.add(novoElemento);
        add(novoElemento);
        repaint();
    }
    public String gerarFormula(Elemento elm1, Elemento elm2){
        String nomeEl1 = elm1.toString();
        String nomeEl2 = elm2.toString();
        if(nomeEl1.compareTo(nomeEl2) < 0){
            return nomeEl1 + "-" + nomeEl2;
        }else{
            return nomeEl2 + "-" + nomeEl1;
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
