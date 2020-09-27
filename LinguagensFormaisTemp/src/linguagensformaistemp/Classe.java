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
    private Character raiz;                        // "Raiz" é equivalente ao "Símbolo terminal", também enviado pelo usuário, o simbolo terminal é a "raiz" da gramática, podendo destrinchar em todos os terminais
    private String matriz[][];                // Local que será utilizado para salvar as regras, basicamente uma formatação das regras recebidas pela lista de string em um formato utilizável

    public Classe(ArrayList<Character> variaveis, ArrayList<Character> terminais, ArrayList<String> regras, char raiz) throws Exception {
        this.variaveis = variaveis;     //seta variaveis
        this.terminais = terminais;     // seta terminais
        this.regras = regras;           // seta regras
        this.raiz = raiz;               // seta raiz
        boolean check = true;           // variável auxiliar de teste
        for (Character temp : terminais) {   //Vai checar se os terminais não estão contidos nas variáveis
            if (!variaveis.contains(temp)) {
                check = false;
            }
        }
        for (int x = variaveis.size() - 1; x > 0; x--) { //Remove todos os terminais repetidos de variaveis
            if (terminais.contains(variaveis.get(x))) {
                variaveis.remove(x);
            }
        }
        if (!check || !variaveis.contains(raiz) || terminais.contains(raiz) || !padraoRegras() || !raizCheck()) { //Caso, 'check' seja falso,"variáveis" não contiverem o "simbolo terminal",
            throw new IllegalArgumentException("Entrada inválida");                               //terminais contiverem o simbolo inicial ou as regras colocadas não façam sentido para os terminais e variáveis fornecidas, então a entrada é recusada
        }else{
            throw new Exception("Passou de boas");
        }
    }

    //Feito por Thiago e Kelvin - 24/09 | 02:00 - 04:00
    //{S -> AA, S -> BA, A -> AB|BA|SA}
    private boolean padraoRegras() {       // Padrão esperado para as regras inseridas
        boolean test = true;
        ArrayList<String> lista = new ArrayList();
        if (!checkRegrasSimb()) {
            return false;
        }
        while (test) {
            test = false;
            for (String regra : regras) {
                if (regra.contains("|")) {
                    lista.add(regra.trim().substring(0, regra.indexOf("|")));
                    regras.set(regras.indexOf(regra), regra.replace(regra.trim().substring(5, regra.indexOf("|") + 1), ""));
                    test = true;
                }
            }
        }
        lista.addAll(regras);
        lista.sort(String.CASE_INSENSITIVE_ORDER);
        matriz = new String[lista.size()][2];
        regras = lista;
        matrizAdd();
        return true;
    }

    // Refatorado por Kelvin 24/09 | 06:00 - 06:20
    // Lógica por Thiago
    private boolean checkRegrasSimb() { //Checa se as regras digitadas fazem sentido para os terminais/simbolos inseridos
        for (String regra : regras) {
            for (int x = 0; x < regra.length(); x++) {
                regra = regra.replaceAll(" ", "").replaceAll("->", "").replaceAll("|", "");
                if (!variaveis.contains(regra.charAt(x))) {
                    return false;
                }
            }
        }
        return true;
    }

    // Refatorado por Kelvin 24/09 | 06:00 - 06:20
    // Lógica por Thiago
    private void matrizAdd() { //Só adiciona à matriz regras que não façam referência a si mesma, como por exemplo C->C
        int contaRegras = 0;
        for (String regra : regras) {
            if (regra.trim().substring(0, 1) != regra.trim().substring(5)) {
                matriz[contaRegras][0] = regra.trim().substring(0, 1);
                matriz[contaRegras][1] = regra.trim().substring(5);
                contaRegras++;
            }
        }
    }

    // Feito por Thiago e Kelvin 26/09 | 21:30 - 22:40
    // Função que checa se o simbolo inicial digitado está correto para as regras inseridas
    private boolean raizCheck() {
        ArrayList<Integer> confirma = new ArrayList();
        int x = 0, y = 0;
        for (Character nope : variaveis) {
            if (nope.equals(raiz)) {
                confirma.add(1);
            } else {
                confirma.add(0);
            }
        }
        while (x < matriz.length) {
            y = 0;
            while (y < matriz.length) {
                if (confirma.get(y) == 1) {
                    for (String cada : matriz[x][1].split("")) {
                        if (variaveis.contains(cada)) {
                            confirma.set(variaveis.indexOf(cada), 1);
                        }
                    }
                }
                y++;
            }
            x++;
        }
        if (confirma.contains(0)) {
            return false;
        } else {
            return true;
        }
    }
}
