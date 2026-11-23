import java.util.Scanner;

public class Menu {

    // Lê os comandos digitados pelo jogador.
    private Scanner scanner = new Scanner(System.in);

    // Mantém o jogo funcionando até escolher sair.
    private boolean emExecucao = true;

    // Objetos que representam a partida.
    private Jogador jogador;
    private Cidade cidade;

    // Cada classe cuida de uma parte das regras.
    private Mercado mercado = new Mercado();
    private Agricultura agricultura = new Agricultura();

    // Inicia o menu principal.
    public void executar() {
        while (emExecucao) {
            exibirOpcoes();
            int opcao = lerOpcao();
            processarOpcao(opcao);
        }

        scanner.close();
    }

    private void exibirOpcoes() {
        System.out.println("\n===== BOLONHA E MILANO =====");
        System.out.println("1 - Novo jogo");
        System.out.println("2 - Ver status da cidade");
        System.out.println("3 - Mercado");
        System.out.println("4 - Agricultura");
        System.out.println("5 - Governo (proxima etapa)");
        System.out.println("6 - Construcoes (futura etapa)");
        System.out.println("7 - Sobre o jogo");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opcao: ");
    }

    // Evita erros caso o usuário digite letras.
    private int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Direciona o jogador à funcionalidade escolhida.
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
            case 4:
                abrirAgricultura();
                break;
            case 5:
            case 6:
                if (verificarPartida()) {
                    System.out.println("Disponivel em outra etapa.");
                }
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

    // Confere se existe uma partida em andamento.
    private boolean verificarPartida() {
        if (cidade == null) {
            System.out.println("Inicie um novo jogo primeiro!");
            return false;
        }
        return true;
    }

    // Cria um jogador e uma cidade.
    private void iniciarNovoJogo() {
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

        jogador = new Jogador(nome);
        cidade = new Cidade(nomeCidade, jogador);

        System.out.println("\nJogo iniciado com sucesso!");
        cidade.exibirStatus();
    }

    private void exibirStatus() {
        if (verificarPartida()) {
            cidade.exibirStatus();
        }
    }

    // ===== MERCADO - ETAPA 3 =====

    private void abrirMercado() {
        if (!verificarPartida()) {
            return;
        }

        boolean noMercado = true;

        while (noMercado) {
            System.out.println("\n===== MERCADO =====");
            System.out.println("Florins: " + cidade.getFlorins());
            System.out.println("Graos: " + cidade.getGraos());
            System.out.println("Terras: " + cidade.getTerras());
            System.out.println("Preco do grao: "
                    + mercado.getPrecoGrao());
            System.out.println("Preco da terra: "
                    + mercado.getPrecoTerra());

            System.out.println("1 - Comprar graos");
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

    // Lê uma quantidade positiva.
    private int lerQuantidade() {
        System.out.print("Digite a quantidade: ");
        int quantidade = lerOpcao();

        if (quantidade <= 0) {
            System.out.println("Quantidade invalida!");
            return -1;
        }

        return quantidade;
    }

    // Delegamos as negociações à classe Mercado.
    private void comprarGraos() {
        int qtd = lerQuantidade();

        if (mercado.comprarGraos(cidade, qtd)) {
            System.out.println("Compra realizada!");
        } else {
            System.out.println("Compra nao realizada.");
        }
    }

    private void venderGraos() {
        int qtd = lerQuantidade();

        if (mercado.venderGraos(cidade, qtd)) {
            System.out.println("Venda realizada!");
        } else {
            System.out.println("Venda nao realizada.");
        }
    }

    private void comprarTerras() {
        int qtd = lerQuantidade();

        if (mercado.comprarTerras(cidade, qtd)) {
            System.out.println("Compra realizada!");
        } else {
            System.out.println("Compra nao realizada.");
        }
    }

    private void venderTerras() {
        int qtd = lerQuantidade();

        if (mercado.venderTerras(cidade, qtd)) {
            System.out.println("Venda realizada!");
        } else {
            System.out.println("Venda nao realizada.");
        }
    }

    // ===== AGRICULTURA - ETAPA 4 =====

    // Abre o submenu e mantém o jogador nele
    // até escolher a opção 0.
    private void abrirAgricultura() {
        if (!verificarPartida()) {
            return;
        }

        boolean naAgricultura = true;

        while (naAgricultura) {
            System.out.println("\n===== AGRICULTURA =====");
            System.out.println("Terras: " + cidade.getTerras());
            System.out.println("Servos: " + cidade.getServos());
            System.out.println("Graos: " + cidade.getGraos());

            System.out.println("\n1 - Processar colheita");
            System.out.println("2 - Distribuir alimentos");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    colher();
                    break;
                case 2:
                    distribuirAlimentos();
                    break;
                case 0:
                    naAgricultura = false;
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
    }

    // Chama o método responsável pela colheita.
    private void colher() {
        agricultura.processarColheita(cidade);
    }

    // Solicita o percentual e chama Agricultura.
    private void distribuirAlimentos() {
        System.out.print(
                "Percentual de graos para distribuir (20 a 80): "
        );

        int percentual = lerOpcao();

        boolean sucesso = agricultura.distribuirAlimentos(
                cidade, percentual
        );

        if (!sucesso) {
            System.out.println(
                    "Percentual invalido! Use de 20 a 80."
            );
        }
    }

    private void exibirSobre() {
        System.out.println("\nBolonha e Milano");
        System.out.println(
                "Jogo de estrategia economica medieval."
        );
        System.out.println(
                "Etapa 4: agricultura e populacao."
        );
    }
}
