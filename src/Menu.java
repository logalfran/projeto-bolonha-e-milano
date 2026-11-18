
import java.util.Scanner;

// Esta classe controla o menu principal do jogo.
// Ela recebe as escolhas do usuário e chama as funcionalidades.
public class Menu {

    // Scanner permite ler o que o jogador digita no terminal.
    private Scanner scanner = new Scanner(System.in);

    // Controla se o jogo continua funcionando.
    // true = continua; false = encerra.
    private boolean emExecucao = true;

    // Guarda o jogador da partida atual.
    // Começa como null porque ainda não existe jogador.
    private Jogador jogador;

    // Método responsável por iniciar o menu.
    public void executar() {

        System.out.println("Bem-vindo ao Bolonha e Milano!");

        // O while mantém o menu funcionando
        // enquanto emExecucao for verdadeiro.
        while (emExecucao) {

            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Novo jogo");
            System.out.println("2 - Ver status da cidade");
            System.out.println("3 - Mercado");
            System.out.println("4 - Agricultura");
            System.out.println("5 - Governo");
            System.out.println("6 - Construcoes");
            System.out.println("7 - Sobre o jogo");
            System.out.println("0 - Sair");

            System.out.print("Escolha uma opcao: ");

            // Lemos a entrada como String para evitar
            // que letras causem erros no programa.
            String entrada = scanner.nextLine();

            // O switch identifica a opção escolhida.
            switch (entrada) {

                case "1":
                    // Solicita o nome do jogador.
                    System.out.print("Digite seu nome: ");
                    String nome = scanner.nextLine().trim();

                    // Se não digitar um nome,
                    // utilizamos um valor padrão.
                    if (nome.isEmpty()) {
                        nome = "Jogador 1";
                    }

                    // Cria um objeto da classe Jogador.
                    // O construtor define o nome e o título inicial.
                    jogador = new Jogador(nome);

                    System.out.println("\nJogo iniciado!");
                    System.out.println("Jogador: " + jogador.getNome());
                    System.out.println("Titulo: " + jogador.getTitulo());
                    break;

                case "2":
                    // Verifica se existe uma partida.
                    if (jogador == null) {
                        System.out.println("Inicie um novo jogo primeiro!");
                    } else {
                        System.out.println("\n===== STATUS =====");
                        System.out.println("Jogador: " + jogador.getNome());
                        System.out.println("Titulo: " + jogador.getTitulo());
                        System.out.println("Os recursos da cidade serao adicionados depois.");
                    }
                    break;

                case "3":
                    if (jogador == null) {
                        System.out.println("Inicie um novo jogo primeiro!");
                    } else {
                        System.out.println("Mercado em desenvolvimento.");
                    }
                    break;

                case "4":
                    if (jogador == null) {
                        System.out.println("Inicie um novo jogo primeiro!");
                    } else {
                        System.out.println("Agricultura em desenvolvimento.");
                    }
                    break;

                case "5":
                    if (jogador == null) {
                        System.out.println("Inicie um novo jogo primeiro!");
                    } else {
                        System.out.println("Governo em desenvolvimento.");
                    }
                    break;

                case "6":
                    if (jogador == null) {
                        System.out.println("Inicie um novo jogo primeiro!");
                    } else {
                        System.out.println("Construcoes em desenvolvimento.");
                    }
                    break;

                case "7":
                    System.out.println("\n===== SOBRE O JOGO =====");
                    System.out.println("Bolonha e Milano e um jogo de estrategia");
                    System.out.println("economica e politica ambientado na Italia medieval.");
                    System.out.println("O jogador governa uma cidade e administra");
                    System.out.println("recursos para desenvolver seu territorio.");
                    break;

                case "0":
                    // Altera a variável para false,
                    // encerrando o laço while.
                    System.out.println("Encerrando o jogo...");
                    emExecucao = false;
                    break;

                default:
                    // Trata opções que não existem no menu.
                    System.out.println("Opcao invalida! Tente novamente.");
                    break;
            }
        }
    }
}
