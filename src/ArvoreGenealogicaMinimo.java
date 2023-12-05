public class ArvoreGenealogicaMinimo implements Amontoavel {
    private String[] dados;
    private int ponteiroFim;

    public ArvoreGenealogicaMinimo(int tamanho) {
        dados = new String[tamanho];
        ponteiroFim = -1;
    }

    public ArvoreGenealogicaMinimo() {
        this(10);
    }

    @Override
    public void inserir(Object dado) {
        if (!estaCheia()) {
            ponteiroFim++;
            dados[ponteiroFim] = (String) dado;
            ajustarAcima(ponteiroFim);
        } else {
            System.err.println("Heap is full!");
        }
    }

    private void ajustarAcima(int indice) {
        while (indice > 0) {
            if (dados[indice].compareTo(dados[indiceGenitor(indice)]) < 0) {
                troca(indice, indiceGenitor(indice));
                indice = indiceGenitor(indice);
            } else {
                break;
            }
        }
    }

    private void ajustarAbaixo(int genitor) {
        int filhoEsquerdo = 2 * genitor + 1;
        int filhoDireito = 2 * genitor + 2;
        int menor = genitor;

        if (filhoEsquerdo <= ponteiroFim && dados[filhoEsquerdo].compareTo(dados[menor]) < 0) {
            menor = filhoEsquerdo;
        }

        if (filhoDireito <= ponteiroFim && dados[filhoDireito].compareTo(dados[menor]) < 0) {
            menor = filhoDireito;
        }

        if (menor != genitor) {
            troca(genitor, menor);
            ajustarAbaixo(menor);
        }
    }

    private void troca(int i, int j) {
        String temp = dados[i];
        dados[i] = dados[j];
        dados[j] = temp;
    }

    private int indiceGenitor(int filho) {
        return (filho - 1) / 2;
    }

    @Override
    public Object extrair() {
        String raiz = null;
        if (!estaVazia()) {
            raiz = dados[0];
            dados[0] = dados[ponteiroFim];
            ponteiroFim--;
            ajustarAbaixo(0);
        } else {
            System.err.println("Heap is Empty!");
        }
        return raiz;
    }

    @Override
    public Object obterRaiz() {
        Object raizAux = null;
        if (!estaVazia()) {
            raizAux = dados[0];
        }
        return raizAux;
    }

    public String detalharFamiliar(int indice) {
        if (indice > ponteiroFim || indice < 0) {
            return "Índice fora do alcance!";
        }
        String detalhes = "Detalhes do Familiar: " + dados[indice];
        int indicePai = indiceGenitor(indice);
        if (indicePai >= 0) {
            detalhes += "\nPai: " + dados[indicePai];
        }
        int indiceIrmao = (indice % 2 == 0) ? indice - 1 : indice + 1;
        if (indiceIrmao >= 0 && indiceIrmao <= ponteiroFim) {
            detalhes += "\nIrmão: " + dados[indiceIrmao];
        }
        return detalhes;
    }

    @Override
    public boolean buscarFamiliar(String familiar) {
        boolean encontrado = buscarRecursivamente(familiar, 0);

        if (encontrado) {
            System.out.println(familiar + " foi encontrado na árvore genealógica.");
        } else {
            System.out.println(familiar + " não foi encontrado na árvore genealógica.");
        }

        return encontrado;
    }

    private boolean buscarRecursivamente(String familiar, int indice) {
        if (indice > ponteiroFim) {
            return false;
        }

        if (dados[indice].equalsIgnoreCase(familiar)) {
            return true;
        }
        if (buscarRecursivamente(familiar, 2 * indice + 1)) {
            return true;
        }
        return buscarRecursivamente(familiar, 2 * indice + 2);
    }

    public void remover(Object dado) {
        //
    }

    @Override
    public boolean estaVazia() {
        return (ponteiroFim == -1);
    }

    @Override
    public boolean estaCheia() {
        return (ponteiroFim == dados.length - 1);
    }

    @Override
    public String imprimir() {
        if (estaVazia()) {
            return "Árvore Genealógica vazia.";
        }

        StringBuilder resultado = new StringBuilder("Árvore Genealógica:\n");

        imprimirRecursivamente(0, "", resultado);

        return resultado.toString();
    }

    private void imprimirRecursivamente(int indice, String prefixo, StringBuilder resultado) {
        if (indice <= ponteiroFim) {
            resultado.append(prefixo).append(dados[indice]).append("\n");

            imprimirRecursivamente(2 * indice + 1, prefixo + "  |-- ", resultado);
            imprimirRecursivamente(2 * indice + 2, prefixo + "  |-- ", resultado);
        }
    }
}
