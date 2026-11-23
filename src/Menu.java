
import java.util.Scanner;

// Controla a interacao do jogador com o jogo.
public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private boolean emExecucao = true;

    // Guardam os objetos da partida atual.
    private Jogador jogador;
    private Cidade cidade;

    public void executar() {
        System.out.println("Bem-vindo ao Bolonha e Milano!");

        // Repete o menu ate o jogador escolher sair.
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

            String entrada = scanner.nextLine();

            switch (entrada) {
                case "1":
                    novoJogo();
                    break;

                case "2":
                    if (verificarPartida()) {
                        mostrarStatus();
                    }
                    break;

                case "3":
                case "4":
                case "5":
                case "6":
                    if (verificarPartida()) {
                        System.out.println(
                            "Funcionalidade sera implementada nas proximas etapas."
                        );
                    }
                    break;

                case "7":
                    System.out.println(
                        "Bolonha e Milano e um jogo de estrategia economica"
                    );
                    System.out.println(
                        "e politica ambientado na Italia medieval."
                    );
                    break;

                case "0":
                    emExecucao = false;
                    System.out.println("Encerrando o jogo...");
                    break;

                default:
                    System.out.println("Opcao invalida!");
            }
        }
    }

    // Cria o jogador e a cidade com seus valores iniciais.
    private void novoJogo() {
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine().trim();

        if (nome.isEmpty()) {
            nome = "Jogador 1";
        }

        System.out.print("Digite o nome da cidade: ");
        String nomeCidade = scanner.nextLine().trim();

        if (nomeCidade.isEmpty()) {
            nomeCidade = "Bolonha";
        }

        // Cria os objetos e associa a cidade ao jogador.
        jogador = new Jogador(nome);
        cidade = new Cidade(nomeCidade, jogador);

        System.out.println("\nJogo iniciado com sucesso!");
        mostrarStatus();
    }

    // Impede acessar funcionalidades antes de iniciar o jogo.
    private boolean verificarPartida() {
        if (jogador == null || cidade == null) {
            System.out.println("Inicie um novo jogo primeiro!");
            return false;
        }
        return true;
    }

    // Consulta os recursos usando os getters da Cidade.
    private void mostrarStatus() {
        System.out.println("\n===== STATUS DA CIDADE =====");
        System.out.println("Jogador: " + jogador.getNome());
        System.out.println("Titulo: " + jogador.getTitulo());
        System.out.println("Cidade: " + cidade.getNome());
        System.out.println("Ano: " + cidade.getAnoAtual());
        System.out.println("Florins: " + cidade.getFlorins());
        System.out.println("Terras: " + cidade.getTerras());
        System.out.println("Graos: " + cidade.getGraos());
        System.out.println("Servos: " + cidade.getServos());
        System.out.println("Imposto: " + cidade.getTaxaImposto() + "%");
        System.out.println("Justica: " + cidade.getNivelJustica());
        System.out.println("Soldados: " + cidade.getSoldados());
        System.out.println("Construcoes: " + cidade.getConstrucoes());
    }
}
