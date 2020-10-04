package linguagensformaistemp;

import java.util.ArrayList;

// Revisado por Thiago,Charles,Pedro, Kelvin e Ritiele - 27/09 | 20:00 - 20:45
public class Classe {

    private ArrayList<Character> variaveis;   // "Variáveis" contêm terminais e variáveis | todas as variáveis e terminais que o usuário digitou
    private ArrayList<Character> terminais;   // "Terminais" contêm todos os simbolos terminais descritos pelo usuário | Todos os simbolos terminais digitados pelo usuário
    private ArrayList<String> regras;         // "Regras" Contêm uma lista de todas as regras do usuário, dividindo elas por posição da lista
    private Character raiz;                   // "Raiz" é equivalente ao "Símbolo terminal", também enviado pelo usuário, o simbolo terminal é a "raiz" da gramática, podendo destrinchar em todos os terminais
    private String[][] regrasMatriz;          // Local que será utilizado para salvar as regras, basicamente uma formatação das regras recebidas pela lista de string em um formato utilizável


    public Classe(ArrayList<Character> variaveis, ArrayList<Character> terminais, ArrayList<String> regras, char raiz) throws Exception {
        this.variaveis = variaveis;             // seta variaveis
        this.terminais = terminais;             // seta terminais
        this.regras = regras;                   // seta regras
        this.raiz = raiz;                       // seta raiz
        boolean check = true;                   // variável auxiliar de teste
        for (Character temp : terminais) {      // Vai checar se os terminais estão contidos nas variáveis
            if (!variaveis.contains(temp)) {
                check = false;
            }
        }
        for (int x = variaveis.size() - 1; x > 0; x--) { // Remove todos os terminais repetidos de variaveis
            if (terminais.contains(variaveis.get(x))) {
                variaveis.remove(x);
            }
        }
        if (!check) {   // Caso, 'check' seja falso, terminais não estavam contidos nas variáveis.
            System.out.println("Os terminais não estão nas variáveis");                                                 // Terminais contiverem o simbolo inicial ou as regras colocadas não façam sentido para os terminais e variáveis fornecidas, então a entrada é recusada
            return;
        }

        if (!variaveis.contains(raiz)) {   // Caso, 'check' seja falso,"variáveis" não contiverem o "simbolo terminal",
            System.out.println("Raiz não está presente entre as variáveis");                                                 // Terminais contiverem o simbolo inicial ou as regras colocadas não façam sentido para os terminais e variáveis fornecidas, então a entrada é recusada
            return;
        }

        if (terminais.contains(raiz)) {   // Caso, 'check' seja falso,"variáveis" não contiverem o "simbolo terminal",
            System.out.println("Raiz usada é um dos terminais");                                                 // Terminais contiverem o simbolo inicial ou as regras colocadas não façam sentido para os terminais e variáveis fornecidas, então a entrada é recusada
            return;
        }

        if (!padraoRegras()) {   // Caso, 'check' seja falso,"variáveis" não contiverem o "simbolo terminal",
            System.out.println("Regras estão mal formatadas");                                                 // Terminais contiverem o simbolo inicial ou as regras colocadas não façam sentido para os terminais e variáveis fornecidas, então a entrada é recusada
            return;
        }

        if (!raizCheck()) {   // Caso, 'check' seja falso,"variáveis" não contiverem o "simbolo terminal",
            System.out.println("Raiz não consegue chegar a todos as variáveis");                                                 // Terminais contiverem o simbolo inicial ou as regras colocadas não façam sentido para os terminais e variáveis fornecidas, então a entrada é recusada
            return;
        }

        System.out.println("Checagem completa");

    }

    //Feito por Thiago e Kelvin - 24/09 | 02:00 - 04:00
    //{S -> AA, S -> BA, A -> ABA|BA|SA}
    private boolean padraoRegras() {       // Padrão esperado para as regras inseridas
        boolean test = true;
        ArrayList<String> lista = new ArrayList<String>();
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
        regrasMatriz = new String[lista.size()][2];
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
                if (!(variaveis.contains(regra.charAt(x)) || terminais.contains(regra.charAt(x)))) {
                    return false;
                }
            }
        }
        return true;
    }

    // Refatorado por Kelvin 24/09 | 06:00 - 06:20
    // Lógica por Thiago
    private void matrizAdd() { //Só adiciona à regrasMatriz regras que não façam referência a si mesma, como por exemplo C->C
        int contaRegras = 0;
        for (String regra : regras) {
            if (regra.trim().substring(0, 1) != regra.trim().substring(5)) {
                regrasMatriz[contaRegras][0] = regra.trim().substring(0, 1);
                regrasMatriz[contaRegras][1] = regra.trim().substring(5);
                contaRegras++;
            }
        }
    }

    // Feito por Thiago e Kelvin 26/09 | 21:30 - 22:40
    // Função que checa se o simbolo inicial digitado está correto para as regras inseridas
    private boolean raizCheck() {
        ArrayList<Integer> confirma = new ArrayList<Integer>();
        int x = 0, y = 0;
        for (Character nope : variaveis) {
            if (nope.equals(raiz)) {
                confirma.add(1);
            } else {
                confirma.add(0);
            }
        }
        while (x < regrasMatriz.length) {
            y = 0;
            while (y < regrasMatriz.length) {
                if (confirma.get(variaveis.indexOf((regrasMatriz[y][0].charAt(0)))) == 1) {
                    for (String cada : regrasMatriz[y][1].split("")) {
                        if (variaveis.contains(cada.charAt(0))) {
                            confirma.set(variaveis.indexOf(cada.charAt(0)), 1);
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

// Implementar uma função que mostre o processo de derivação até a expressão/string digitada pelo usuário, caso seja possível.
    public ArrayList derivar(String entrada) {
        ArrayList<Integer> variacoes = new ArrayList<Integer>();
        ArrayList<String> retorno = new ArrayList<String>();
        retorno.add(entrada);
        String entradaLocal = entrada;
        boolean skip = false;
        StringBuilder builder = new StringBuilder(entradaLocal);
        int nivel = 0, inicio = 0, fim = inicio + 1, regra = 0;
        for (String x : entrada.split("")) {
            if (!terminais.contains(x.charAt(0))) {
                return null;
            }
        }
        while (nivel >= 0) {
            for (; inicio < entradaLocal.length(); inicio++) {
                for (; fim <= entradaLocal.length(); fim++) {
                    if (skip) {
                        skip = false;
                    } else {
                        regra = buscaRegra(entradaLocal.substring(inicio, fim), 0);
                        if (regra != -1) {
                            variacoes.add(inicio);
                            variacoes.add(fim);
                            variacoes.add(regra);
                            nivel++;
                            builder.replace(inicio, fim, regrasMatriz[regra][0]);
                            entradaLocal = builder.toString();
                            retorno.add(entradaLocal);
                            System.out.println(entradaLocal + "- Inicio:" + inicio + " Fim: " + fim + " Nível: " + nivel);
                            inicio = 0;
                            fim = 0;
                        }
                    }
                }
                fim = inicio + 1;
            }
            if (entradaLocal.equals(raiz.toString())) {
                for (String linha : retorno) {
                    System.out.println(linha);
                }
                return retorno;
            }
            if (retorno.size() > 1) {
                if (buscaRegra(entradaLocal, variacoes.get(variacoes.size() - 1) + 1) == -1) {
                    nivel--;
                    retorno.remove(retorno.size() - 1);
                    entradaLocal = retorno.get(retorno.size() - 1);
                    variacoes.remove(variacoes.size() - 1);
                    fim = variacoes.remove(variacoes.size() - 1);
                    inicio = variacoes.remove(variacoes.size() - 1);
                    skip = true;
                    builder = new StringBuilder(entradaLocal);
                } else {
                    //Tem mais conjuntos cuja regra pode ser convertida, essa parte do código é que vai testar os outros caminhos dessa silaba
                    System.out.println("Achou outra regra");
                }
            }else{
                nivel--;
            }
            
        }
        
       return null;
    }

    private int buscaRegra(String parte, int inicioBusca) {
        for (int x = inicioBusca; x < this.regrasMatriz.length; x++) {
            if (parte.equals(regrasMatriz[x][1])) {
                return x;
            }
        }
        return -1;
    }
    
    
    // Kelvin Clovis (Setters e Getters) - 03/10  | 14:50-15:00 
       public ArrayList<Character> getVariaveis() {
        return variaveis;
    }
    public void setVariaveis(ArrayList<Character> variaveis) {
        this.variaveis = variaveis;
    }

    public ArrayList<Character> getTerminais() {
        return terminais;
    }

    public void setTerminais(ArrayList<Character> terminais) {
        this.terminais = terminais;
    }

    public ArrayList<String> getRegras() {
        return regras;
    }

    public void setRegras(ArrayList<String> regras) {
        this.regras = regras;
    }

    public Character getRaiz() {
        return raiz;
    }

    public void setRaiz(Character raiz) {
        this.raiz = raiz;
    }

    public String[][] getRegrasMatriz() {
        return regrasMatriz;
    }

    public void setRegrasMatriz(String[][] regrasMatriz) {
        this.regrasMatriz = regrasMatriz;
    }
    
    public String toString(){
        return("Terminais: " + getTerminais()+ "\n" + "Variáveis: " + getVariaveis() + "\n" + "Símbolo inicial: " + getRaiz() + "\n" + "Regras: " + getRegras());
    }

}
