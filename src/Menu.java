import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Menu {

    private Scanner scanner = new Scanner(System.in);

    private boolean emExecucao = true;

    // ETAPA 7: controla a partida
    private Partida partida;

    // Cidade do jogador atual
    private Cidade cidade;

    // Classes de servico
    private Mercado mercado = new Mercado();
    private Agricultura agricultura = new Agricultura();
    private Governo governo = new Governo();
    private Construcoes construcoes = new Construcoes();
    private Nobreza nobreza = new Nobreza();

    // Executa o menu principal
    public void executar() {

        while (emExecucao) {

            exibirOpcoes();

            int opcao = lerOpcao();

            processarOpcao(opcao);
        }

        scanner.close();
    }

    // Exibe o menu principal
    private void exibirOpcoes() {

        System.out.println("\n===== BOLONHA E MILANO =====");

        // Exibe quem esta jogando
        if (partida != null && cidade != null) {

            System.out.println("Ano: " + partida.getAnoAtual());

            System.out.println("Jogador atual: "
                    + cidade.getDono().getNome());

            System.out.println("Cidade atual: "
                    + cidade.getNome());

            System.out.println("Titulo: "
                    + cidade.getDono().getTitulo());

            System.out.println("----------------------------");
        }

        System.out.println("1 - Iniciar novo jogo");
        System.out.println("2 - Ver status da cidade");
        System.out.println("3 - Acessar mercado");
        System.out.println("4 - Acessar agricultura");
        System.out.println("5 - Acessar governo");
        System.out.println("6 - Construcoes e soldados");
        System.out.println("7 - Encerrar turno");
        System.out.println("8 - Ver classificacao");
        System.out.println("9 - Sobre o jogo");
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

            case 6:
                abrirConstrucoes();
                break;

            case 7:
                encerrarTurno();
                break;

            case 8:
                exibirClassificacao();
                break;

            case 9:
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

    // Verifica se existe uma partida em andamento
    private boolean verificarPartida() {

        if (partida == null || cidade == null) {

            System.out.println("Nenhum jogo em andamento.");

            return false;
        }

        return true;
    }

    // =========================
    // NOVA PARTIDA - ETAPA 7
    // =========================

    private void iniciarNovoJogo() {

        System.out.print("Quantidade de jogadores (1 a 4): ");

        int quantidade = lerOpcao();

        // Utiliza dois jogadores caso o valor seja invalido
        if (quantidade < 1 ||
            quantidade > Partida.MAXIMO_JOGADORES) {

            quantidade = 2;

            System.out.println(
                    "Quantidade invalida. Serao dois jogadores.");
        }

        // Cidades disponiveis
        String[] nomesCidades = {
            "Bolonha",
            "Milano",
            "Florença",
            "Veneza"
        };

        // Cria uma nova partida
        partida = new Partida();

        // Cria os jogadores e suas respectivas cidades
        for (int i = 0; i < quantidade; i++) {

            System.out.print("Nome do jogador " + (i + 1) + ": ");

            String nome = scanner.nextLine().trim();

            if (nome.isEmpty()) {
                nome = "Jogador " + (i + 1);
            }

            Jogador jogador = new Jogador(nome);

            Cidade novaCidade =
                    new Cidade(nomesCidades[i], jogador);

            partida.adicionarCidade(novaCidade);

            System.out.println(nome + " governa "
                    + novaCidade.getNome());
        }

        // Define a primeira cidade a jogar
        cidade = partida.getCidadeAtual();

        System.out.println("\nNova partida iniciada!");
        System.out.println("Ano inicial: " + partida.getAnoAtual());
        System.out.println("Ano final: " + partida.getAnoFinal());
    }

    // Exibe o status da cidade atual
    private void exibirStatus() {

        if (!verificarPartida()) {
            return;
        }

        cidade.exibirStatus();

        System.out.println("Prestigio: "
                + nobreza.calcularPrestigio(cidade));
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

            System.out.println("Colheita realizada: "
                    + (cidade.isColheitaFeita() ? "Sim" : "Nao"));

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

    // ETAPA 7: verifica o resultado da colheita
    private void colher() {

        int resultado = agricultura.processarColheita(cidade);

        if (resultado == -1) {

            System.out.println("A colheita deste ano ja foi realizada.");

        } else if (resultado == -2) {

            System.out.println("Limite de armazenamento excedido.");
        }
    }

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

            System.out.println("Impostos arrecadados: "
                    + (cidade.isImpostosArrecadados() ? "Sim" : "Nao"));

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

    // ETAPA 7: verifica o resultado da arrecadacao
    private void arrecadarImpostos() {

        int arrecadado = governo.arrecadarImpostos(cidade);

        if (arrecadado == -1) {

            System.out.println("Os impostos deste ano ja foram arrecadados.");

        } else if (arrecadado == -2) {

            System.out.println("Limite de armazenamento excedido.");

        } else {

            System.out.println("Impostos arrecadados: "
                    + arrecadado + " florins");

            System.out.println("Saldo atual: "
                    + cidade.getFlorins());
        }
    }

    private void definirTaxaImposto() {

        System.out.print("Nova taxa de imposto (0 a 50): ");

        int taxa = lerOpcao();

        if (governo.definirTaxaImposto(cidade, taxa)) {
            System.out.println("Taxa de imposto atualizada!");
        } else {
            System.out.println("Taxa invalida! Informe de 0 a 50.");
        }
    }

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

    private void abrirConstrucoes() {

        if (!verificarPartida()) {
            return;
        }

        boolean nasConstrucoes = true;

        while (nasConstrucoes) {

            System.out.println("\n===== CONSTRUCOES E SOLDADOS =====");
            System.out.println("Florins: " + cidade.getFlorins());
            System.out.println("Servos: " + cidade.getServos());
            System.out.println("Soldados: " + cidade.getSoldados());
            System.out.println("Construcoes: " + cidade.getConstrucoes());

            System.out.println("Preco por soldado: "
                    + construcoes.getPrecoSoldado()
                    + " florins + 1 servo");

            System.out.println("Preco por construcao: "
                    + construcoes.getPrecoConstrucao()
                    + " florins");

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

    private void recrutarSoldados() {

        System.out.print("Quantidade de soldados: ");

        int quantidade = lerOpcao();

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

    private void construir() {

        System.out.print("Quantidade de construcoes: ");

        int quantidade = lerOpcao();

        if (construcoes.construir(cidade, quantidade)) {

            System.out.println("Construcoes realizadas!");
            System.out.println("Novas construcoes: " + quantidade);
            System.out.println("Florins restantes: " + cidade.getFlorins());

        } else {

            System.out.println("Construcao invalida ou saldo insuficiente.");
        }
    }

    // =========================
    // TURNOS - ETAPA 7
    // =========================

    private void encerrarTurno() {

        if (!verificarPartida()) {
            return;
        }

        System.out.println("\nTurno encerrado: "
                + cidade.getDono().getNome());

        // Verifica se todos os jogadores terminaram
        boolean anoTerminou = partida.passarTurno();

        if (anoTerminou) {

            System.out.println("\n===== FIM DO ANO =====");
            System.out.println("Ano encerrado: "
                    + partida.getAnoAtual());

            // Avanca o ano e verifica as promocoes
            List<Cidade> promovidas =
                    partida.encerrarAno(nobreza);

            // Exibe os governantes promovidos
            for (Cidade promovida : promovidas) {

                System.out.println(
                        promovida.getDono().getNome()
                        + " foi promovido para "
                        + promovida.getDono().getTitulo()
                        + "!");
            }

            // Exibe a classificacao atualizada
            exibirClassificacao();

            // Verifica se existe um vencedor
            if (partida.terminou()) {

                encerrarPartida();

                return;
            }

            System.out.println("\nNovo ano: "
                    + partida.getAnoAtual());
        }

        // Atualiza a cidade do jogador atual
        cidade = partida.getCidadeAtual();

        System.out.println("\nProximo jogador: "
                + cidade.getDono().getNome());

        System.out.println("Cidade: " + cidade.getNome());
    }

    // =========================
    // CLASSIFICACAO - ETAPA 7
    // =========================

    private void exibirClassificacao() {

        if (!verificarPartida()) {
            return;
        }

        System.out.println("\n===== CLASSIFICACAO =====");

        // Cria uma copia para ordenar sem alterar os turnos
        List<Cidade> classificacao =
                new ArrayList<>(partida.getCidades());

        // Ordena pelo titulo e depois pelo prestigio
        classificacao.sort((a, b) -> {

            int comparacao = Integer.compare(
                    b.getDono().getNivelTitulo(),
                    a.getDono().getNivelTitulo());

            if (comparacao != 0) {
                return comparacao;
            }

            return Integer.compare(
                    nobreza.calcularPrestigio(b),
                    nobreza.calcularPrestigio(a));
        });

        int posicao = 1;

        // Exibe os participantes
        for (Cidade participante : classificacao) {

            Jogador governante = participante.getDono();

            int prestigio =
                    nobreza.calcularPrestigio(participante);

            int necessario =
                    nobreza.prestigioParaProximoTitulo(governante);

            System.out.println("\nPosicao: " + posicao);
            System.out.println("Cidade: " + participante.getNome());
            System.out.println("Governante: " + governante.getNome());
            System.out.println("Titulo: " + governante.getTitulo());
            System.out.println("Prestigio: " + prestigio);

            if (necessario == -1) {

                System.out.println("Titulo maximo atingido!");

            } else {

                int faltam = Math.max(0, necessario - prestigio);

                System.out.println("Pontos para o proximo titulo: "
                        + faltam);
            }

            posicao++;
        }

        System.out.println("=========================");
    }

    // =========================
    // FIM DA PARTIDA - ETAPA 7
    // =========================

    private void encerrarPartida() {

        Cidade vencedora =
                partida.determinarVencedor(nobreza);

        System.out.println("\n===== FIM DA PARTIDA =====");

        if (vencedora != null) {

            System.out.println("Vencedor: "
                    + vencedora.getDono().getNome());

            System.out.println("Cidade vencedora: "
                    + vencedora.getNome());

            System.out.println("Titulo final: "
                    + vencedora.getDono().getTitulo());

            System.out.println("Prestigio final: "
                    + nobreza.calcularPrestigio(vencedora));
        }

        System.out.println("==========================");

        // Limpa a partida e retorna ao menu principal
        partida = null;
        cidade = null;
    }

    // Informacoes sobre o jogo
    private void exibirSobre() {

        System.out.println("\n===== SOBRE O JOGO =====");
        System.out.println("Bolonha e Milano");
        System.out.println("Jogo de administracao de cidades medievais.");
        System.out.println("Partidas de 1 a 4 jogadores.");
        System.out.println("Gerencie recursos e aumente seu prestigio.");
        System.out.println("Evolua seu titulo ate Rei/Rainha.");
        System.out.println("Periodo da partida: 1400 a 1410.");
        System.out.println("Projeto de Programacao Orientada a Objetos.");
    }
}
