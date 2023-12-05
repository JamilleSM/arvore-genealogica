import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        String remove;
        ArvoreGenealogicaMinimo arvore = new ArvoreGenealogicaMinimo(10);
        do {
            exibirMenu();
            System.out.print("Escolha uma opcao (0-6): ");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 0:
                    System.out.println("Saindo da Árvore. Até mais!");
                    break;

                case 1:
                    System.out.print("Digite o nome: ");
                    String nome = scanner.next();
                    arvore.inserir(nome);
                    break;
                case 2:
                    System.out.println(formataSaida(arvore.imprimir()));
                    break;
                case 3:
                    System.out.print("Digite um nome para buscar: ");
                    String busca = scanner.next();
                    arvore.buscarFamiliar(busca);
                    break;
                case 4:
                    arvore.informacoesFamiliar();
                    break;
                case 5:
                    System.out.print("Digite o nome do membro da família a ser removido: ");
                    remove = scanner.next();
                    arvore.remover(remove);
                    break;
                case 6:
                    System.out.print("Digite um nome para obter detalhes: ");
                    String detalhe = scanner.next();
                    relacionamentoFamiliar(arvore, detalhe);
                    break;
                default:
                    System.out.println("Opcão invalida. Tente novamente.");
            }
        } while (opcao != 0);
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("=== Árvore Binaria de Pesquisa ===");
        System.out.println("0. SAIR");
        System.out.println("1. Inserir Membro");
        System.out.println("2. Vizualizar Árvore");
        System.out.println("3. Buscar por Familiar");
        System.out.println("4. Detalhar Familiar");
        System.out.println("5. Remover Familiar");
        System.out.println("6. Verificação de Relacionamento familiar");
    }

    private static void relacionamentoFamiliar(ArvoreGenealogicaMinimo arvore, String nome) {
        int indice = arvore.encontrarIndicePorNome(nome);
        if (indice != -1) {
            System.out.println(arvore.detalharFamiliar(indice));
        } else {
            System.out.println(nome + " não foi encontrado na árvore genealógica.");
        }
    }

    private static String formataSaida(String msg) {
        String resultado;
        do {
            resultado = msg;
            msg = msg.replace("  ", " "); // remove excesso de espaços
        } while (!msg.equals(resultado));
        msg = msg.trim(); // remove espaços em branco do inicio e fim, se existir
        msg = msg.replace(" ", ""); // troca espaço por vírgula
        return msg;
    }
}

/*
 * Desenvolva um sistema de árvore genealógica, onde o usuário poderá criar e
 * visualizar relações familiares. Cada nó da árvore representa um membro da
 * família e a estrutura reflete
 * o parentesco entre membros familiares.
 * 1. Inserção de Familiar;
 * 2. Visualização da Árvore destacando a relação entre os membros da família;
 * 3. Busca por Familiares;
 * 4. Detalhes do Familiar;
 * 5. Remoção de Familiar
 * 
 * que gerará uma remoção de todos os familiares hierarquicamente abaixo dele;
 * 6. Verificação de Relacionamento familiar.
 * 
 */
