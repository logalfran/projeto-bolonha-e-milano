package bolonhaemilano;

public class Testes {

    // Contadores dos resultados
    private static int aprovados = 0;
    private static int reprovados = 0;

    // Verifica se o resultado corresponde ao esperado
    private static void verificar(
            String descricao, boolean condicao) {

        if (condicao) {
            aprovados++;
            System.out.println("[OK] " + descricao);
        } else {
            reprovados++;
            System.out.println("[FALHA] " + descricao);
        }
    }

    // Cria uma cidade nova para os testes
    private static Cidade novaCidade() {
        return new Cidade("Bolonha", new Jogador("Teste"));
    }

    // =========================
    // TESTES DA CLASSE CIDADE
    // =========================

    private static void testarCidade() {

        System.out.println("\n===== TESTES: CIDADE =====");

        Cidade cidade = novaCidade();

        verificar("Ano inicial igual a 1400",
                cidade.getAnoAtual() == 1400);

        verificar("Florins iniciais iguais a 1000",
                cidade.getFlorins() == 1000);

        verificar("Terras iniciais iguais a 10000",
                cidade.getTerras() == 10000);

        verificar("Graos iniciais iguais a 500",
                cidade.getGraos() == 500);

        verificar("Servos iniciais iguais a 200",
                cidade.getServos() == 200);

        verificar("Soldados e construcoes iniciam em zero",
                cidade.getSoldados() == 0 &&
                cidade.getConstrucoes() == 0);

        // Testa uma operacao invalida
        verificar("Recusa retirada acima do saldo",
                !cidade.removerFlorins(2000));

        verificar("Saldo permanece inalterado",
                cidade.getFlorins() == 1000);

        // Testa o controle anual
        cidade.marcarColheitaFeita();
        cidade.marcarImpostosArrecadados();
        cidade.avancarAno();

        verificar("Ano avancou para 1401",
                cidade.getAnoAtual() == 1401);

        verificar("Operacoes anuais foram liberadas",
                !cidade.isColheitaFeita() &&
                !cidade.isImpostosArrecadados());
    }

    // =========================
    // TESTES DO MERCADO
    // =========================

    private static void testarMercado() {

        System.out.println("\n===== TESTES: MERCADO =====");

        Cidade cidade = novaCidade();
        Mercado mercado = new Mercado();

        verificar("Compra de 100 graos",
                mercado.comprarGraos(cidade, 100));

        verificar("Compra descontou 200 florins",
                cidade.getFlorins() == 800);

        verificar("Estoque aumentou para 600 graos",
                cidade.getGraos() == 600);

        verificar("Recusa quantidade negativa",
                !mercado.comprarGraos(cidade, -10));

        verificar("Recusa compra sem saldo",
                !mercado.comprarGraos(cidade, 1000));

        verificar("Compras invalidas nao alteram recursos",
                cidade.getFlorins() == 800 &&
                cidade.getGraos() == 600);

        verificar("Venda de 100 graos",
                mercado.venderGraos(cidade, 100));

        verificar("Recursos restaurados apos venda",
                cidade.getFlorins() == 1000 &&
                cidade.getGraos() == 500);

        verificar("Compra de 100 hectares",
                mercado.comprarTerras(cidade, 100));

        verificar("Venda de 100 hectares",
                mercado.venderTerras(cidade, 100));
    }

    // =========================
    // TESTES DA AGRICULTURA
    // =========================

    private static void testarAgricultura() {

        System.out.println("\n===== TESTES: AGRICULTURA =====");

        Cidade cidade = novaCidade();
        Agricultura agricultura = new Agricultura();

        verificar("Primeira colheita cultiva 1000 hectares",
                agricultura.processarColheita(cidade) == 1000);

        verificar("Estoque final de 2000 graos",
                cidade.getGraos() == 2000);

        verificar("Segunda colheita no ano recusada",
                agricultura.processarColheita(cidade) == -1);

        verificar("Colheita recusada nao altera estoque",
                cidade.getGraos() == 2000);

        verificar("Distribuicao de alimentos valida",
                agricultura.distribuirAlimentos(cidade, 20));

        verificar("Populacao aumentou para 210 servos",
                cidade.getServos() == 210);

        verificar("Percentual invalido recusado",
                !agricultura.distribuirAlimentos(cidade, 90));

        cidade.avancarAno();

        verificar("Nova colheita permitida no ano seguinte",
                agricultura.processarColheita(cidade) >= 0);
    }

    // =========================
    // TESTES DO GOVERNO
    // =========================

    private static void testarGoverno() {

        System.out.println("\n===== TESTES: GOVERNO =====");

        Cidade cidade = novaCidade();
        Governo governo = new Governo();

        verificar("Justica severa aceita",
                governo.definirJustica(cidade, "Severa"));

        verificar("Imposto severo arrecada 26 florins",
                governo.arrecadarImpostos(cidade) == 26);

        verificar("Saldo atualizado para 1026",
                cidade.getFlorins() == 1026);

        verificar("Segunda arrecadacao recusada",
                governo.arrecadarImpostos(cidade) == -1);

        verificar("Taxa de 60 por cento recusada",
                !governo.definirTaxaImposto(cidade, 60));

        verificar("Taxa permanece em 10 por cento",
                cidade.getTaxaImposto() == 10);

        verificar("Nivel de justica invalido recusado",
                !governo.definirJustica(cidade, "Invalida"));

        cidade.avancarAno();

        verificar("Impostos liberados no novo ano",
                governo.arrecadarImpostos(cidade) == 26);
    }

    // =========================
    // TESTES DAS CONSTRUCOES
    // =========================

    private static void testarConstrucoes() {

        System.out.println("\n===== TESTES: CONSTRUCOES =====");

        Cidade cidade = novaCidade();
        Construcoes construcoes = new Construcoes();

        verificar("Realizacao de 10 construcoes",
                construcoes.construir(cidade, 10));

        verificar("Construcoes descontaram 200 florins",
                cidade.getFlorins() == 800);

        verificar("Cidade possui 10 construcoes",
                cidade.getConstrucoes() == 10);

        verificar("Quantidade negativa recusada",
                !construcoes.construir(cidade, -5));

        verificar("Construcao sem saldo recusada",
                !construcoes.construir(cidade, 100));

        // Testa a validacao de recursos
        Cidade outraCidade = novaCidade();
        outraCidade.removerServos(150);

        verificar("Recrutamento sem servos recusado",
                !construcoes.recrutarSoldados(outraCidade, 100));

        verificar("Operacao recusada preserva os florins",
                outraCidade.getFlorins() == 1000);

        verificar("Operacao recusada preserva os servos",
                outraCidade.getServos() == 50);
    }

    // =========================
    // TESTES DO JOGADOR E NOBREZA
    // =========================

    private static void testarJogadorENobreza() {

        System.out.println("\n===== TESTES: NOBREZA =====");

        Cidade cidade = novaCidade();
        Nobreza nobreza = new Nobreza();
        Jogador jogador = cidade.getDono();

        verificar("Titulo inicial Cavaleiro",
                jogador.getNivelTitulo() == 0);

        verificar("Prestigio inicial igual a 300",
                nobreza.calcularPrestigio(cidade) == 300);

        verificar("Prestigio insuficiente nao promove",
                !nobreza.verificarPromocao(cidade));

        cidade.adicionarConstrucoes(50);

        verificar("Promocao com prestigio suficiente",
                nobreza.verificarPromocao(cidade));

        verificar("Promocao de apenas um nivel",
                jogador.getNivelTitulo() == 1);

        // Avanca ate o titulo maximo
        while (!jogador.ehMonarca()) {
            jogador.promover();
        }

        verificar("Titulo maximo atingido",
                jogador.ehMonarca());

        verificar("Promocao alem de monarca recusada",
                !jogador.promover());
    }

    // =========================
    // TESTES DA PARTIDA
    // =========================

    private static void testarPartida() {

        System.out.println("\n===== TESTES: PARTIDA =====");

        Partida partida = new Partida();
        Nobreza nobreza = new Nobreza();

        Cidade cidade1 = novaCidade();

        Cidade cidade2 =
                new Cidade("Milano", new Jogador("Teste 2"));

        partida.adicionarCidade(cidade1);
        partida.adicionarCidade(cidade2);

        verificar("Primeira cidade joga primeiro",
                partida.getCidadeAtual() == cidade1);

        verificar("Primeiro turno nao encerra o ano",
                !partida.passarTurno());

        verificar("Segunda cidade recebe o turno",
                partida.getCidadeAtual() == cidade2);

        verificar("Segundo turno encerra o ano",
                partida.passarTurno());

        partida.encerrarAno(nobreza);

        verificar("Todas as cidades avancaram para 1401",
                cidade1.getAnoAtual() == 1401 &&
                cidade2.getAnoAtual() == 1401);

        verificar("Turno retorna para primeira cidade",
                partida.getCidadeAtual() == cidade1);

        partida.adicionarCidade(
                new Cidade("Florenca", new Jogador("Teste 3")));

        partida.adicionarCidade(
                new Cidade("Veneza", new Jogador("Teste 4")));

        verificar("Quinta cidade recusada",
                !partida.adicionarCidade(novaCidade()));

        cidade1.adicionarConstrucoes(10);

        verificar("Vencedor definido pelo maior prestigio",
                partida.determinarVencedor(nobreza) == cidade1);

        // Testa a condicao de vitoria
        while (!cidade1.getDono().ehMonarca()) {
            cidade1.getDono().promover();
        }

        verificar("Partida termina quando existe um monarca",
                partida.terminou());

        verificar("Monarca determinado como vencedor",
                partida.determinarVencedor(nobreza) == cidade1);
    }

    // =========================
    // EXECUCAO DOS TESTES
    // =========================

    public static void main(String[] args) {

        System.out.println("===== TESTES AUTOMATIZADOS =====");

        testarCidade();
        testarMercado();
        testarAgricultura();
        testarGoverno();
        testarConstrucoes();
        testarJogadorENobreza();
        testarPartida();

        System.out.println("\n===== RESULTADO FINAL =====");

        System.out.println("Aprovados: " + aprovados
                + " | Reprovados: " + reprovados);

        // Retorna erro caso algum teste falhe
        if (reprovados > 0) {
            System.exit(1);
        }
    }
}
