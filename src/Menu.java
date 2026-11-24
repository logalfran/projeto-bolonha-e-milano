import java.util.Scanner;

public class Menu {

    // Leitura dos dados digitados
    private Scanner scanner = new Scanner(System.in);

    // Controla a execucao do jogo
    private boolean emExecucao = true;

    // Objetos principais
    private Jogador jogador;
    private Cidade cidade;

    // Classes responsaveis pelas regras do jogo
    private Mercado mercado = new Mercado();
    private Agricultura agricultura = new Agricultura();
    private Governo governo = new Governo();

    // ETAPA 6: nova classe
    private Construcoes construcoes = new Construcoes();

    // Executa o menu principal
    public void executar() {

        while (emExecucao) {
            exibirOpcoes();
            int opcao = lerOpcao();
            processarOpcao(opcao);
        }

        scanner.close();
    }

    // Exibe as opcoes do menu principal
    private void exibirOpcoes() {

        System.out.println("\n===== BOLONHA E MILANO =====");
        System.out.println("1 - Iniciar novo jogo");
        System.out.println("2 - Ver status da cidade");
        System.out.println("3 - Acessar mercado");
        System.out.println("4 - Acessar agricultura");
        System.out.println("5 - Acessar governo");
        System.out.println("6 - Construcoes e soldados");
        System.out.println("7 - Sobre o jogo");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    // Le uma opcao numerica
    private int lerOpcao() {

        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Processa a opcao escolhida
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
                abrirGoverno();
                break;

            // ETAPA 6: acesso as construcoes
            case 6:
                abrirConstrucoes();
                break;

            case 7:
                exibirSobre();
                break;

            case 0:
                emExecucao = false;
                System.out.println("Jogo encerrado.");
                break;

            default:
                System.out.println("Opcao invalida.");
        }
    }

    // Verifica se existe um jogo iniciado
    private boolean verificarPartida() {

        if (cidade == null) {
            System.out.println("Nenhum jogo em andamento.");
            return false;
        }

        return true;
    }

    // Cria um jogador e sua cidade
    private void iniciarNovoJogo() {

        System.out.print("Nome do jogador: ");
        String nomeJogador = scanner.nextLine().trim();

        if (nomeJogador.isEmpty()) {
            nomeJogador = "Jogador 1";
        }

        System.out.print("Nome da cidade: ");
        String nomeCidade = scanner.nextLine().trim();

        if (nomeCidade.isEmpty()) {
            nomeCidade = "Bolonha";
        }

        jogador = new Jogador(nomeJogador);
        cidade = new Cidade(nomeCidade, jogador);

        System.out.println("Novo jogo iniciado!");
    }

    // Exibe o status da cidade
    private void exibirStatus() {

        if (!verificarPartida()) {
            return;
        }

        cidade.exibirStatus();
    }

    // =========================
    // MERCADO - ETAPA 3
    // =========================

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

            switch (lerOpcao()) {

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
                    System.out.println("Opcao invalida.");
            }
        }
    }

    // Le uma quantidade digitada
    private int lerQuantidade() {
        System.out.print("Quantidade: ");
        return lerOpcao();
    }

    private void comprarGraos() {

        int quantidade = lerQuantidade();

        if (mercado.comprarGraos(cidade, quantidade)) {
            System.out.println("Compra realizada!");
        } else {
            System.out.println("Compra invalida ou saldo insuficiente.");
        }
    }

    private void venderGraos() {

        int quantidade = lerQuantidade();

        if (mercado.venderGraos(cidade, quantidade)) {
            System.out.println("Venda realizada!");
        } else {
            System.out.println("Quantidade invalida ou insuficiente.");
        }
    }

    private void comprarTerras() {

        int quantidade = lerQuantidade();

        if (mercado.comprarTerras(cidade, quantidade)) {
            System.out.println("Compra realizada!");
        } else {
            System.out.println("Compra invalida ou saldo insuficiente.");
        }
    }

    private void venderTerras() {

        int quantidade = lerQuantidade();

        if (mercado.venderTerras(cidade, quantidade)) {
            System.out.println("Venda realizada!");
        } else {
            System.out.println("Quantidade invalida ou insuficiente.");
        }
    }

    // =========================
    // AGRICULTURA - ETAPA 4
    // =========================

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

            System.out.println("1 - Processar colheita");
            System.out.println("2 - Distribuir alimentos");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            switch (lerOpcao()) {

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
                    System.out.println("Opcao invalida.");
            }
        }
    }

    // Processa a colheita
    private void colher() {
        agricultura.processarColheita(cidade);
    }

    // Distribui alimentos para a populacao
    private void distribuirAlimentos() {

        System.out.print("Percentual de alimentos (20 a 80): ");
        int percentual = lerOpcao();

        if (!agricultura.distribuirAlimentos(cidade, percentual)) {
            System.out.println("Percentual invalido.");
        }
    }

    // =========================
    // GOVERNO - ETAPA 5
    // =========================

    private void abrirGoverno() {

        if (!verificarPartida()) {
            return;
        }

        boolean noGoverno = true;

        while (noGoverno) {

            System.out.println("\n===== GOVERNO =====");
            System.out.println("Florins: " + cidade.getFlorins());

            System.out.println("Taxa de imposto: "
                    + cidade.getTaxaImposto() + "%");

            System.out.println("Nivel de justica: "
                    + cidade.getNivelJustica());

            System.out.println("1 - Arrecadar impostos");
            System.out.println("2 - Definir taxa de imposto");
            System.out.println("3 - Definir nivel de justica");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            switch (lerOpcao()) {

                case 1:
                    arrecadarImpostos();
                    break;

                case 2:
                    definirTaxaImposto();
                    break;

                case 3:
                    definirJustica();
                    break;

                case 0:
                    noGoverno = false;
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }
        }
    }

    // Arrecada impostos
    private void arrecadarImpostos() {

        int arrecadado = governo.arrecadarImpostos(cidade);

        System.out.println("Impostos arrecadados: "
                + arrecadado + " florins");

        System.out.println("Saldo atual: "
                + cidade.getFlorins());
    }

    // Altera a taxa de imposto
    private void definirTaxaImposto() {

        System.out.print("Nova taxa de imposto (0 a 50): ");
        int taxa = lerOpcao();

        if (governo.definirTaxaImposto(cidade, taxa)) {
            System.out.println("Taxa de imposto atualizada!");
        } else {
            System.out.println("Taxa invalida! Informe de 0 a 50.");
        }
    }

    // Altera o nivel de justica
    private void definirJustica() {

        System.out.println("\n===== NIVEL DE JUSTICA =====");
        System.out.println("1 - Justa");
        System.out.println("2 - Moderada");
        System.out.println("3 - Severa");
        System.out.print("Escolha: ");

        String nivel;

        switch (lerOpcao()) {

            case 1:
                nivel = "Justa";
                break;

            case 2:
                nivel = "Moderada";
                break;

            case 3:
                nivel = "Severa";
                break;

            default:
                System.out.println("Nivel de justica invalido.");
                return;
        }

        if (governo.definirJustica(cidade, nivel)) {
            System.out.println("Nivel de justica atualizado!");
        } else {
            System.out.println("Nivel de justica invalido.");
        }
    }

    // =========================
    // CONSTRUCOES - ETAPA 6
    // =========================

    // Abre o submenu de construcoes e soldados
    private void abrirConstrucoes() {

        if (!verificarPartida()) {
            return;
        }

        boolean nasConstrucoes = true;

        while (nasConstrucoes) {

            // Exibe os recursos atuais
            System.out.println("\n===== CONSTRUCOES E SOLDADOS =====");
            System.out.println("Florins: " + cidade.getFlorins());
            System.out.println("Servos: " + cidade.getServos());
            System.out.println("Soldados: " + cidade.getSoldados());
            System.out.println("Construcoes: " + cidade.getConstrucoes());

            // Exibe os precos
            System.out.println("Preco por soldado: "
                    + construcoes.getPrecoSoldado()
                    + " florins + 1 servo");

            System.out.println("Preco por construcao: "
                    + construcoes.getPrecoConstrucao()
                    + " florins");

            // Opcoes do submenu
            System.out.println("1 - Recrutar soldados");
            System.out.println("2 - Construir");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            switch (lerOpcao()) {

                case 1:
                    recrutarSoldados();
                    break;

                case 2:
                    construir();
                    break;

                case 0:
                    nasConstrucoes = false;
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }
        }
    }

    // Solicita a quantidade de soldados
    private void recrutarSoldados() {

        System.out.print("Quantidade de soldados: ");
        int quantidade = lerOpcao();

        // Delega a operacao para a classe Construcoes
        if (construcoes.recrutarSoldados(cidade, quantidade)) {

            System.out.println("Recrutamento realizado!");
            System.out.println("Soldados recrutados: " + quantidade);
            System.out.println("Florins restantes: " + cidade.getFlorins());
            System.out.println("Servos restantes: " + cidade.getServos());

        } else {

            System.out.println("Recrutamento invalido.");
            System.out.println("Verifique a quantidade, florins e servos.");
        }
    }

    // Solicita a quantidade de construcoes
    private void construir() {

        System.out.print("Quantidade de construcoes: ");
        int quantidade = lerOpcao();

        // Delega a operacao para a classe Construcoes
        if (construcoes.construir(cidade, quantidade)) {

            System.out.println("Construcoes realizadas!");
            System.out.println("Novas construcoes: " + quantidade);
            System.out.println("Florins restantes: " + cidade.getFlorins());

        } else {

            System.out.println("Construcao invalida ou saldo insuficiente.");
        }
    }

    // Informacoes sobre o jogo
    private void exibirSobre() {

        System.out.println("\n===== SOBRE O JOGO =====");
        System.out.println("Bolonha e Milano");
        System.out.println("Jogo de administracao de cidades medievais.");
        System.out.println("Gerencie recursos, populacao e governo.");
        System.out.println("Administre soldados e construcoes.");
        System.out.println("Projeto de Programacao Orientada a Objetos.");
    }
}
