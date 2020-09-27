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
        regras.add("E -> E * E");
        regras.add("E -> E + E");
        regras.add("E -> (E)");
        regras.add("I -> a");
        regras.add("I -> b");
        regras.add("I -> Ia");
        regras.add("I -> Ib");
        Classe teste = new Classe(variaveis,terminais,regras,raiz);
        
    }

}
