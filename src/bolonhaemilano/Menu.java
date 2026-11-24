package bolonhaemilano;

import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private boolean emExecucao = true;

    private Partida partida;
    private Cidade cidade;

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

    // Exibe as opcoes do menu principal
    private void exibirOpcoes() {

        System.out.println("\n===== BOLONHA E MILANO =====");

        if (partida != null && cidade != null) {
            System.out.println("Ano: " + partida.getAnoAtual());
            System.out.println("Cidade atual: " + cidade.getNome());
            System.out.println("Jogador: " + cidade.getDono().getNome());
        }

        System.out.println("1 - Iniciar novo jogo");
        System.out.println("2 - Ver status da cidade");
        System.out.println("3 - Mercado");
        System.out.println("4 - Agricultura");
        System.out.println("5 - Governo");
        System.out.println("6 - Construcoes e soldados");
        System.out.println("7 - Encerrar turno");
        System.out.println("8 - Ver classificacao");
        System.out.println("9 - Sobre o jogo");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    // ETAPA 8: centraliza a leitura de numeros inteiros
    // Evita repetir o mesmo try/catch em varios metodos
    private int lerInteiro() {

        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private int lerOpcao() {
        return lerInteiro();
    }

    private int lerQuantidade() {
        System.out.print("Quantidade: ");
        return lerInteiro();
    }

    // ETAPA 8: centraliza a verificacao da partida
    private boolean exigirJogoEmAndamento() {

        if (partida == null || cidade == null) {
            System.out.println("Nenhum jogo em andamento.");
            return false;
        }

        return true;
    }

    // Executa a opcao escolhida pelo jogador
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
                System.out.println("Opcao invalida!");
        }
    }

    // =========================
    // INICIAR NOVO JOGO
    // =========================

    private void iniciarNovoJogo() {

        System.out.println("\n===== NOVO JOGO =====");

        System.out.print("Quantidade de jogadores (1 a 4): ");
        int quantidade = lerInteiro();

        if (quantidade < 1 || quantidade > 4) {
            quantidade = 2;
            System.out.println("Quantidade invalida. Serao 2 jogadores.");
        }

        partida = new Partida();

        String[] nomesCidades = {
            "Bolonha",
            "Milano",
            "Florenca",
            "Veneza"
        };

        // Cria um jogador e uma cidade para cada participante
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

            System.out.println(nome + " governa " + nomesCidades[i]);
        }

        cidade = partida.getCidadeAtual();

        System.out.println("\nPartida iniciada!");
        System.out.println("Primeiro jogador: "
                + cidade.getDono().getNome());
    }

    // =========================
    // STATUS DA CIDADE
    // =========================

    private void exibirStatus() {

        if (!exigirJogoEmAndamento()) {
            return;
        }

        cidade.exibirStatus();

        System.out.println("Prestigio: "
                + nobreza.calcularPrestigio(cidade));
    }

    // =========================
    // MERCADO
    // =========================

    private void abrirMercado() {

        if (!exigirJogoEmAndamento()) {
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
                    System.out.println("Opcao invalida!");
            }
        }
    }

    private void comprarGraos() {

        int quantidade = lerQuantidade();

        if (mercado.comprarGraos(cidade, quantidade)) {
            System.out.println("Compra realizada com sucesso!");
        } else {
            System.out.println("Compra recusada. Verifique os recursos.");
        }
    }

    private void venderGraos() {

        int quantidade = lerQuantidade();

        if (mercado.venderGraos(cidade, quantidade)) {
            System.out.println("Venda realizada com sucesso!");
        } else {
            System.out.println("Venda recusada. Verifique os recursos.");
        }
    }

    private void comprarTerras() {

        int quantidade = lerQuantidade();

        if (mercado.comprarTerras(cidade, quantidade)) {
            System.out.println("Compra realizada com sucesso!");
        } else {
            System.out.println("Compra recusada. Verifique os recursos.");
        }
    }

    private void venderTerras() {

        int quantidade = lerQuantidade();

        if (mercado.venderTerras(cidade, quantidade)) {
            System.out.println("Venda realizada com sucesso!");
        } else {
            System.out.println("Venda recusada. Verifique os recursos.");
        }
    }

    // =========================
    // AGRICULTURA
    // =========================

    private void abrirAgricultura() {

        if (!exigirJogoEmAndamento()) {
            return;
        }

        boolean naAgricultura = true;

        while (naAgricultura) {

            System.out.println("\n===== AGRICULTURA =====");

            System.out.println("Terras: " + cidade.getTerras());
            System.out.println("Graos: " + cidade.getGraos());
            System.out.println("Servos: " + cidade.getServos());

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
                    System.out.println("Opcao invalida!");
            }
        }
    }

    private void colher() {

        int hectares = agricultura.processarColheita(cidade);

        if (hectares == -1) {
            System.out.println("A colheita deste ano ja foi realizada.");
        } else if (hectares < 0) {
            System.out.println("Nao foi possivel realizar a colheita.");
        } else {
            System.out.println("Colheita realizada!");
            System.out.println("Hectares cultivados: " + hectares);
        }
    }

    private void distribuirAlimentos() {

        System.out.print("Percentual de alimentos (20 a 80): ");

        int percentual = lerInteiro();

        if (!agricultura.distribuirAlimentos(cidade, percentual)) {
            System.out.println("Percentual invalido!");
        }
    }

    // =========================
    // GOVERNO
    // =========================

    private void abrirGoverno() {

        if (!exigirJogoEmAndamento()) {
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
                    System.out.println("Opcao invalida!");
            }
        }
    }

    private void arrecadarImpostos() {

        int arrecadado = governo.arrecadarImpostos(cidade);

        if (arrecadado == -1) {

            System.out.println("Os impostos deste ano ja foram arrecadados.");

        } else if (arrecadado < 0) {

            System.out.println("Nao foi possivel arrecadar impostos.");

        } else {

            System.out.println("Impostos arrecadados: "
                    + arrecadado + " florins");

            System.out.println("Saldo atual: "
                    + cidade.getFlorins());
        }
    }

    private void definirTaxaImposto() {

        System.out.print("Nova taxa de imposto (0 a 50): ");

        int taxa = lerInteiro();

        if (governo.definirTaxaImposto(cidade, taxa)) {
            System.out.println("Taxa atualizada com sucesso!");
        } else {
            System.out.println("Taxa invalida!");
        }
    }

    private void definirJustica() {

        System.out.println("\n1 - Justa");
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
                System.out.println("Opcao invalida!");
                return;
        }

        if (governo.definirJustica(cidade, nivel)) {
            System.out.println("Nivel de justica atualizado!");
        } else {
            System.out.println("Nivel de justica invalido!");
        }
    }

    // =========================
    // CONSTRUCOES E SOLDADOS
    // =========================

    private void abrirConstrucoes() {

        if (!exigirJogoEmAndamento()) {
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
                    + construcoes.getPrecoSoldado());

            System.out.println("Preco por construcao: "
                    + construcoes.getPrecoConstrucao());

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
                    System.out.println("Opcao invalida!");
            }
        }
    }

    private void recrutarSoldados() {

        int quantidade = lerQuantidade();

        if (construcoes.recrutarSoldados(cidade, quantidade)) {
            System.out.println("Recrutamento realizado com sucesso!");
        } else {
            System.out.println("Recrutamento recusado. Recursos insuficientes ou quantidade invalida.");
        }
    }

    private void construir() {

        int quantidade = lerQuantidade();

        if (construcoes.construir(cidade, quantidade)) {
            System.out.println("Construcao realizada com sucesso!");
        } else {
            System.out.println("Construcao recusada. Recursos insuficientes ou quantidade invalida.");
        }
    }

    // =========================
    // TURNOS E RODADAS
    // =========================

    private void encerrarTurno() {

        if (!exigirJogoEmAndamento()) {
            return;
        }

        System.out.println("\nTurno encerrado por "
                + cidade.getDono().getNome());

        // Retorna true quando todos os jogadores terminaram
        boolean fimDoAno = partida.passarTurno();

        if (fimDoAno) {

            System.out.println("\n===== FIM DO ANO =====");

            // Avanca o ano e verifica as promocoes
            for (Cidade promovida : partida.encerrarAno(nobreza)) {

                System.out.println(
                        promovida.getDono().getNome()
                        + " foi promovido para "
                        + promovida.getDono().getTitulo());
            }

            System.out.println("Novo ano: " + partida.getAnoAtual());

            exibirClassificacao();

            // Verifica se a partida atingiu uma condicao de vitoria
            if (partida.terminou()) {
                encerrarPartida();
                return;
            }
        }

        // Atualiza a cidade do jogador da vez
        cidade = partida.getCidadeAtual();

        System.out.println("\nAgora e a vez de "
                + cidade.getDono().getNome());

        System.out.println("Cidade: " + cidade.getNome());
        System.out.println("Ano: " + partida.getAnoAtual());
    }

    // =========================
    // CLASSIFICACAO E NOBREZA
    // =========================

    private void exibirClassificacao() {

        if (!exigirJogoEmAndamento()) {
            return;
        }

        System.out.println("\n===== CLASSIFICACAO =====");

        for (Cidade participante : partida.getCidades()) {

            Jogador jogador = participante.getDono();

            int prestigio =
                    nobreza.calcularPrestigio(participante);

            int proximoTitulo =
                    nobreza.prestigioParaProximoTitulo(jogador);

            System.out.println("\nCidade: " + participante.getNome());
            System.out.println("Governante: " + jogador.getNome());
            System.out.println("Titulo: " + jogador.getTitulo());
            System.out.println("Prestigio: " + prestigio);

            if (proximoTitulo == -1) {

                System.out.println("Titulo maximo atingido!");

            } else {

                int faltam = Math.max(0, proximoTitulo - prestigio);

                System.out.println("Prestigio necessario para promocao: "
                        + faltam);
            }

            System.out.println("----------------------------");
        }
    }

    // =========================
    // ENCERRAMENTO DA PARTIDA
    // =========================

    private void encerrarPartida() {

        Cidade vencedora =
                partida.determinarVencedor(nobreza);

        System.out.println("\n===== FIM DE JOGO =====");

        if (vencedora != null) {

            System.out.println("Vencedor: "
                    + vencedora.getDono().getNome());

            System.out.println("Cidade: "
                    + vencedora.getNome());

            System.out.println("Titulo: "
                    + vencedora.getDono().getTitulo());

            System.out.println("Prestigio: "
                    + nobreza.calcularPrestigio(vencedora));
        }

        // Limpa a partida para permitir um novo jogo
        partida = null;
        cidade = null;
    }

    // =========================
    // SOBRE O JOGO
    // =========================

    private void exibirSobre() {

        System.out.println("\n===== SOBRE O JOGO =====");

        System.out.println("Bolonha e Milano");
        System.out.println("Jogo de administracao de cidades medievais.");
        System.out.println("Gerencie recursos, agricultura e impostos.");
        System.out.println("Realize construcoes e aumente seu prestigio.");
        System.out.println("Evolua na nobreza ate se tornar Rei ou Rainha.");
        System.out.println("Projeto desenvolvido em Java.");
        System.out.println("Programacao Orientada a Objetos.");
    }
}
