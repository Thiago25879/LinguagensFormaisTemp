/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linguagensformaistemp;

import java.util.ArrayList;

/**
 *
 * @author thiag
 */
public class LinguagensFormaisTemp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        ArrayList<Character> variaveis = new ArrayList();
        ArrayList<Character> terminais = new ArrayList();
        ArrayList<String> regras = new ArrayList();
        char raiz = 'E';
        variaveis.add('E');
        variaveis.add('I');
        variaveis.add('a');
        variaveis.add('b');
        variaveis.add('+');
        variaveis.add('*');
        variaveis.add('(');
        variaveis.add(')');
        
        terminais.add('a');
        terminais.add('b');
        terminais.add('+');
        terminais.add('*');
        terminais.add('(');
        terminais.add(')');
        
        regras.add("E -> I");
        regras.add("E -> E*E");
        regras.add("E -> E+E");
        regras.add("E -> (E)");
        regras.add("I -> a");
        regras.add("I -> b");
        regras.add("I -> Ia");
        regras.add("I -> Ib");
        
        /*char raiz = 'S';
        variaveis.add('S');
        variaveis.add('A');
        variaveis.add('a');
        variaveis.add('b');
        terminais.add('a');
        terminais.add('b');
        regras.add("S -> AA");
        regras.add("A -> AAA");
        regras.add("A -> bA");
        regras.add("A -> Ab");
        regras.add("A -> a");*/
        Classe teste = new Classe(variaveis,terminais,regras,raiz);
        teste.derivar("aa*(a+ba)");
        
        
    }

}
