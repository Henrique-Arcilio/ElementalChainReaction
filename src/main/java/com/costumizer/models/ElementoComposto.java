package com.costumizer.models;
import com.costumizer.utilitarios.Compostos;
import com.costumizer.utilitarios.Elementos;
import java.awt.*;

public class ElementoComposto extends Elemento{
    private Elemento primario;
    private Elemento secundario;
    private Compostos tipo;
    public ElementoComposto(Compostos tipo, Color cor, String sigla, Elemento primario, Elemento secundario) {
        super(cor, sigla);
        this.primario = primario;
        this.secundario = secundario;
        this.tipo = tipo;
    }
    private Elemento reagirComElemento(Elemento elemento){
        if(elemento.getTipo().equals(primario.getTipo())){
            return primario;
        }else if(elemento.getTipo().equals(secundario.getTipo())) {
            return secundario;
        }
        return null;
    }

    @Override
    public String toString(){
        return getComposto().toString();
    }
    public Elemento getPrimario() {
        return primario;
    }

    public void setPrimario(Elemento primario) {
        this.primario = primario;
    }

    public Elemento getSecundario() {
        return secundario;
    }

    public void setSecundario(Elemento secundario) {
        this.secundario = secundario;
    }

    public Compostos getComposto() {
        return tipo;
    }

    public void setTipo(Compostos tipo) {
        this.tipo = tipo;
    }
}
