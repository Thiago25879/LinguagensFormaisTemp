/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linguagensformaistemp;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiag
 */
public class Classe {

    private ArrayList<Character> variaveis;   // "Variáveis" contêm terminais e variáveis | todas as variáveis e terminais que o usuário digitou
    private ArrayList<Character> terminais;   // "Terminais" contêm todos os simbolos terminais descritos pelo usuário | Todos os simbolos terminais digitados pelo usuário
    private ArrayList<String> regras;         // "Regras" Contêm uma lista de todas as regras do usuário, dividindo elas por posição da lista
    private char raiz;                        // "Raiz" é equivalente ao "Símbolo terminal", também enviado pelo usuário, o simbolo terminal é a "raiz" da gramática, podendo destrinchar em todos os terminais
    private String matriz[][];                // 

    public Classe(ArrayList<Character> variaveis, ArrayList<Character> terminais, ArrayList<String> regras, char raiz) {
        this.variaveis = variaveis;
        this.terminais = terminais;
        this.regras = regras;
        this.raiz = raiz;
        boolean check = true;
        for (Character temp : terminais) {
            if (!variaveis.contains(temp)) {
                check = false;
            }
        }
        if (!check || !variaveis.contains(raiz) || terminais.contains(raiz) || !padraoRegras()) {
            throw new IllegalArgumentException("Entrada inválida");
        }
    }

    //Feito por Thiago e Kelvin - 24/09 | 02:00 - 04:00
    //{S -> AA, S -> BA, A -> AB|BA|SA}
    private boolean padraoRegras() {
        for (String regra : regras) {
            for (int x = 0; x < regra.length(); x++) {
                regra = regra.replaceAll(" ", "").replaceAll("->", "").replaceAll("|", "");
                if (!variaveis.contains(regra.charAt(x))) {
                    return false;
                }
            }
        }
        int maior = 0, contador = 0;
        boolean test = true;
        ArrayList<String> lista = new ArrayList();
        while (test) {
            test = false;
            for (String regra : regras) {
                if (regra.contains("|")) {
                    lista.add(regra.trim().substring(0, regra.indexOf("|")));
                    regras.set(regras.indexOf(regra),regra.replace(regra.trim().substring(5, regra.indexOf("|") + 1), ""));
                    
                    test = true;
                }
            }
        }
        lista.addAll(regras);
        lista.sort(String.CASE_INSENSITIVE_ORDER);
        matriz = new String[lista.size()][2];
        regras = lista;
        int x = 0;
        for (String regra : regras) {
            if (regra.trim().substring(0, 1) != regra.trim().substring(5)) {
                matriz[x][0] = regra.trim().substring(0, 1);
                matriz[x][1] = regra.trim().substring(5);
                x++;
            }
        }
        return true;
    }

    private boolean raizCheck() {

        return true;
    }
}
