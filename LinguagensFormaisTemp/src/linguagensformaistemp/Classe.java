/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linguagensformaistemp;

import java.util.List;

/**
 *
 * @author thiag
 */
public class Classe {
    
    private List<String> variaveis;
    private List<String> terminais;
    private List<String> regras;
    private char raiz;

    public Classe(List<String> variaveis, List<String> terminais, List<String> regras, char raiz) {
        this.variaveis = variaveis;
        this.terminais = terminais;
        this.regras = regras;
        this.raiz = raiz;
        boolean check = true;
        for(String temp: terminais){
            if(!variaveis.contains(temp)){
                check = false;
            }
        }
        if(!check || !variaveis.contains(raiz) || !padraoRegras()){
            throw new IllegalArgumentException("Entrada inválida");
        }
    }
    
    private boolean padraoRegras(){
        return true;
    }
}
