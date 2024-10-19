package com.costumizer.utilitarios;

public enum Compostos {
    VAPOR(Elementos.AGUA.toString()+  "-" +Elementos.FOGO.toString()),
    LAMA(Elementos.AGUA.toString()+  "-" +Elementos.TERRA.toString()),
    MAGMA(Elementos.FOGO.toString()+  "-" +Elementos.TERRA.toString()),
    NUVEM(Elementos.AGUA.toString()+ "-" +Elementos.AR.toString());

    final String formula;
    private Compostos(String formula){
        this.formula = formula;
    }

    public String getFormula() {
        return formula;
    }
}
