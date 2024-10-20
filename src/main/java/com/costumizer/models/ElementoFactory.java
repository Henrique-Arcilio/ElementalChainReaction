package com.costumizer.models;

import com.costumizer.utilitarios.Compostos;
import com.costumizer.utilitarios.Elementos;

import java.awt.*;

import static com.costumizer.utilitarios.Elementos.TERRA;

public class ElementoFactory {
    public static Elemento criarElemento(String nome){
        switch (nome) {
            case "TERRA":
                return new Elemento(TERRA, new Color(82, 40, 2), "TR");
            case "AGUA":
                return new Elemento(Elementos.AGUA, Color.CYAN, "AG");
            case "FOGO":
                return new Elemento(Elementos.FOGO, new Color(253, 116, 41), "FG");
            case "AR":
                return new Elemento(Elementos.AR, Color.WHITE, "AR");
            case "ELETRICO":
                return new Elemento(Elementos.ELETRICO, new Color(126, 51, 193, 249), "EL");
            case "NUVEM":
                return new ElementoComposto(Compostos.NUVEM, new Color(71, 108, 138, 184), "NUV", criarElemento("AGUA"), criarElemento("AR"));
            case "VAPOR":
                return new ElementoComposto(Compostos.VAPOR, new Color(225, 249, 250), "VAP",
                        criarElemento("AGUA"), criarElemento("AR"));
            case "LAMA":
                return new ElementoComposto(Compostos.LAMA, new Color(92, 85, 53), "LAM", criarElemento("TERRA"), criarElemento("AGUA"));
            case "MAGMA":
                return new ElementoComposto(Compostos.MAGMA, new Color(255, 6, 6), "MA",
                        criarElemento("TERRA"), criarElemento("FOGO"));
            case "OBSIDIAN":
                return new ElementoComposto(Compostos.OBSIDIAN, Color.BLACK, "OBS",
                        criarElemento("TERRA"), criarElemento("TERRA"));
            case "MAGNETICO":
                return new ElementoComposto(Compostos.MAGNETICO, new Color(225, 194, 253), "MAG", criarElemento("ELETRICO"), criarElemento("TERRA"));
            default:
                return null;
        }
    }
}
