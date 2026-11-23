import java.util.Scanner;

public class Menu {

    // Scanner lê as informações digitadas pelo jogador.
    private Scanner scanner = new Scanner(System.in);

    // Controla se o programa continua funcionando.
    private boolean emExecucao = true;

    // Objetos utilizados durante a partida.
    private Jogador jogador;
    private Cidade cidade;
    private Mercado mercado = new Mercado();

    // Inicia o laço principal do jogo.
    public void executar() {
        while (emExecucao) {
            exibirOpcoes();
            int opcao = lerOpcao();
            processarOpcao(opcao);
        }

        scanner.close();
    }

    // Exibe as opções disponíveis.
    private void exibirOpcoes() {
        System.out.println("\n===== BOLONHA E MILANO =====");
        System.out.println("1 - Novo jogo");
        System.out.println("2 - Ver status da cidade");
        System.out.println("3 - Mercado");
        System.out.println("6 - Construcoes (futura etapa)");
        System.out.println("7 - Sobre o jogo");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opcao: ");
    }

    // Lê um número inteiro sem encerrar o jogo
    // caso o usuário digite letras.
    private int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Direciona cada opção para o método correspondente.
    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                iniciarNovoJogo();
                break;

            case 2:
                exibirStatus();
                break;

            case 3:
                abrirMercado();
                break;

            case 6:
                System.out.println("Disponivel em outra etapa.");
                break;

            case 7:
                exibirSobre();
                break;

            case 0:
                emExecucao = false;
                System.out.println("Obrigado por jogar!");
                break;

            default:
                System.out.println("Opcao invalida!");
        }
    }

    // Cria o jogador e a cidade.
    private void iniciarNovoJogo() {
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine().trim();

        System.out.print("Digite o nome da cidade: ");
        String nomeCidade = scanner.nextLine().trim();

        if (nome.isEmpty() || nomeCidade.isEmpty()) {
            System.out.println("Os nomes nao podem ficar vazios.");
            return;
        }

        jogador = new Jogador(nome);
        cidade = new Cidade(nomeCidade, jogador);

        System.out.println("\nJogo iniciado com sucesso!");
        cidade.exibirStatus();
    }

    // Mostra o status ou avisa se não há partida.
    private void exibirStatus() {
        if (cidade == null) {
            System.out.println("Inicie um novo jogo primeiro.");
        } else {
            cidade.exibirStatus();
        }
    }

    // Submenu do mercado: repete até escolher voltar.
    private void abrirMercado() {
        if (cidade == null) {
            System.out.println("Inicie um novo jogo primeiro.");
            return;
        }

        boolean noMercado = true;

        while (noMercado) {
            System.out.println("\n===== MERCADO =====");
            System.out.println("Florins: " + cidade.getFlorins());
            System.out.println("Graos: " + cidade.getGraos());
            System.out.println("Terras: " + cidade.getTerras());

            System.out.println("Preco do grao: "
                + mercado.getPrecoGrao() + " florins");
            System.out.println("Preco da terra: "
                + mercado.getPrecoTerra() + " florins");

            System.out.println("\n1 - Comprar graos");
            System.out.println("2 - Vender graos");
            System.out.println("3 - Comprar terras");
            System.out.println("4 - Vender terras");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    comprarGraos();
                    break;
                case 2:
                    venderGraos();
                    break;
                case 3:
                    comprarTerras();
                    break;
                case 4:
                    venderTerras();
                    break;
                case 0:
                    noMercado = false;
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
    }

    // Lê e valida a quantidade de uma negociação.
    private int lerQuantidade() {
        System.out.print("Digite a quantidade: ");
        int quantidade = lerOpcao();

        if (quantidade <= 0) {
            System.out.println("Digite um numero inteiro positivo.");
            return -1;
        }

        return quantidade;
    }

    // Os quatro métodos seguintes chamam o Mercado.
    // As regras econômicas ficam na classe Mercado,
    // e não na classe Menu.

    private void comprarGraos() {
        int quantidade = lerQuantidade();

        if (mercado.comprarGraos(cidade, quantidade)) {
            System.out.println("Graos comprados com sucesso!");
        } else {
            System.out.println("Compra nao realizada.");
        }
    }

    private void venderGraos() {
        int quantidade = lerQuantidade();

        if (mercado.venderGraos(cidade, quantidade)) {
            System.out.println("Graos vendidos com sucesso!");
        } else {
            System.out.println("Venda nao realizada.");
        }
    }

    private void comprarTerras() {
        int quantidade = lerQuantidade();

        if (mercado.comprarTerras(cidade, quantidade)) {
            System.out.println("Terras compradas com sucesso!");
        } else {
            System.out.println("Compra nao realizada.");
        }
    }

    private void venderTerras() {
        int quantidade = lerQuantidade();

        if (mercado.venderTerras(cidade, quantidade)) {
            System.out.println("Terras vendidas com sucesso!");
        } else {
            System.out.println("Venda nao realizada.");
        }
    }

    // Apresenta informações sobre o projeto.
    private void exibirSobre() {
        System.out.println("\nBolonha e Milano");
        System.out.println("Jogo de estrategia economica medieval.");
        System.out.println("Etapa 3: Economia e Mercado.");
    }
}
