package com.costumizer.view;

import com.costumizer.models.Elemento;
import com.costumizer.models.ElementoComposto;
import com.costumizer.models.ElementoFactory;
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
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Elemento elemento : elementos){

                    if(elemento.getX() >= getWidth() - 30 || elemento.getX() <= 0){
                        elemento.setdX(elemento.getdX() * -1);
                    }else if(elemento.getY() >= getHeight() - 30 || elemento.getY() <= 0){
                        elemento.setdY(elemento.getdY() * -1);
                    }
                    //Limitando a velocidade em 7 unidades
                    if(Math.abs(elemento.getdX()) > 7 || Math.abs(elemento.getdY()) > 7)
                    {
                        //incrementando de unidade/2 com o sinal inverto (para desacerelar)
                        elemento.setdX(elemento.getdX() - (int) Math.signum(elemento.getdX())/2);
                        elemento.setdY(elemento.getdY() - (int) Math.signum(elemento.getdY())/2);
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
               return -4;
           default:
               return 4;
       }
    }
    public void transformar(Elemento elementoI, Elemento elementoJ) throws NullPointerException {
        String formula = gerarFormula(elementoI, elementoJ);
        Elemento novoElemento = null;
        System.out.println(formula);

        //Caso seja transformação composto - simplesPrimario
        if ((elementoI instanceof ElementoComposto && elementoJ.getClass() == Elemento.class)) {
            novoElemento = ((ElementoComposto) elementoI).reagirComElemento(elementoJ);
        } else if (elementoJ instanceof ElementoComposto && elementoI.getClass() == Elemento.class) {
            novoElemento = ((ElementoComposto) elementoJ).reagirComElemento(elementoI);
        }
        //Caso seja transformação compostoSimples(não primario)
        if(novoElemento == null){
            //Busca se tem alguma formula para essa interação
                novoElemento = criarElementoComposto(formula);
        }

        //O que ocorre pois colisao
        if(novoElemento != null) {
            reconfigurarElementos(elementoI, elementoJ, novoElemento);
        }else{
            //inverte movimento
            colisaoSemReacao(elementoI, elementoJ);
        }
    }

    public Elemento criarElementoComposto(String formula){
        Elemento novoElemento = null;
        if (formula.equals(Compostos.VAPOR.getFormula())) {
            novoElemento = ElementoFactory.criarElemento("VAPOR");
        } else if (formula.equals(Compostos.MAGMA.getFormula())) {
            novoElemento = ElementoFactory.criarElemento("MAGMA");
        } else if (formula.equals(Compostos.LAMA.getFormula())) {
            novoElemento = ElementoFactory.criarElemento("LAMA");
        } else if (formula.equals(Compostos.NUVEM.getFormula())) {
            novoElemento = ElementoFactory.criarElemento("NUVEM");
        }else if(formula.equals(Compostos.OBSIDIAN.getFormula())){
            novoElemento = ElementoFactory.criarElemento("OBSIDIAN");
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
        int contador = 0;
        while(recElementoI.intersects(rectElementoJ) && contador != 10){
            mover(elementoI);
            mover(elementoJ);
            //Atualizando a informação dos bounds para o loop verificar
            recElementoI = elementoI.getBounds();
            rectElementoJ = elementoJ.getBounds();
            contador +=1;
        }
    }
    public void variarVelocidade(Elemento elemento){
            if(elemento.getdX() > -10 && elemento.getdY() > -10){
                elemento.setdX(elemento.getdY() - 2);
                elemento.setdY(elemento.getdY() + -2);
            }
    }
    public void mover(Elemento elemento){
        elemento.setLocation(elemento.getX() + elemento.getdX(), elemento.getY() + elemento.getdY());
    }
    public void reconfigurarElementos(Elemento elementoI, Elemento elementoJ, Elemento novoElemento){
        int novaPosX = (elementoI.getX() + elementoJ.getX()) / 2;
        int novaPosY = (elementoI.getY() + elementoJ.getY()) / 2;
        int novaVelocidadeX = elementoI.getdX();
        int novaVelocidadeY = elementoI.getdY();
        novoElemento.setBounds(novaPosX, novaPosY, 30, 30);
        if(novoElemento.getClass() == ElementoComposto.class){
            novaVelocidadeX *= 2;
            novaVelocidadeY *= 2;
        }
        novoElemento.setdX(novaVelocidadeX); novoElemento.setdY(novaVelocidadeY);
        remove(elementoI); remove(elementoJ);
        elementos.remove(elementoI); elementos.remove(elementoJ); elementos.add(novoElemento);
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
