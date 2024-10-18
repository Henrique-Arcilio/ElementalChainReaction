package com.costumizer.models;

import com.costumizer.utilitarios.Elementos;

import javax.swing.*;
import java.awt.*;

public class Elemento extends JLabel{
    private Elementos tipo;
    private Color cor;
    private int dX;
    private int dY;
    private String sigla;
    private Elemento elemento;

    public Elemento(Elementos tipo, Color cor) {
        this.tipo = tipo;
        this.cor = cor;
        setOpaque(true);
        setBackground(cor);
        setText(tipo.toString());
    }

    public Elemento(Elementos tipo, Color cor, String sigla) {
        this.tipo = tipo;
        this.cor = cor;
        this.sigla = sigla;
        setOpaque(true);
        setBackground(cor);
        setText(tipo.toString());
    }
    public gerarSubProduto(){

    }
    public Elementos getTipo() {
        return tipo;
    }

    public void setTipo(Elementos tipo) {
        this.tipo = tipo;
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }

    public int getdY() {
        return dY;
    }

    public void setdY(int dY) {
        this.dY = dY;
    }

    public int getdX() {
        return dX;
    }

    public void setdX(int dX) {
        this.dX = dX;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
