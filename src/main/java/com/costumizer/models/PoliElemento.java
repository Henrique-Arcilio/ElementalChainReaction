package com.costumizer.models;

import com.costumizer.utilitarios.Elementos;

import java.awt.*;

public class PoliElemento extends Elemento {
    public Elemento ElementoPrimario;
    public Elemento ElementoSecundario;

    public PoliElemento(Elementos tipo, Color cor, String sigla) {
        super(tipo, cor, sigla);
    }

    public Elemento getElementoSecundario() {
        return ElementoSecundario;
    }

    public void setElementoSecundario(Elemento elementoSecundario) {
        ElementoSecundario = elementoSecundario;
    }

    public Elemento getElementoPrimario() {
        return ElementoPrimario;
    }

    public void setElementoPrimario(Elemento elementoPrimario) {
        ElementoPrimario = elementoPrimario;
    }
}
