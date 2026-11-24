package bolonhaemilano;

public class Cidade {

    // Informacoes da cidade
    private String nome;
    private int anoAtual;
    private Jogador dono;

    // Recursos
    private int florins;
    private int terras;
    private int graos;
    private int servos;

    // Configuracoes do governo
    private int taxaImposto;
    private String nivelJustica;

    // Soldados e construcoes
    private int soldados;
    private int construcoes;

    // ETAPA 7: controle das operacoes anuais
    private boolean colheitaFeita;
    private boolean impostosArrecadados;

    // Construtor
    public Cidade(String nome, Jogador dono) {

        this.nome = nome;
        this.dono = dono;
        this.anoAtual = 1400;

        this.florins = 1000;
        this.terras = 10000;
        this.graos = 500;
        this.servos = 200;

        this.taxaImposto = 10;
        this.nivelJustica = "Moderada";

        this.soldados = 0;
        this.construcoes = 0;

        // Nenhuma operacao anual foi realizada
        this.colheitaFeita = false;
        this.impostosArrecadados = false;
    }

    // GETTERS

    public String getNome() {
        return nome;
    }

    public int getAnoAtual() {
        return anoAtual;
    }

    public Jogador getDono() {
        return dono;
    }

    public int getFlorins() {
        return florins;
    }

    public int getTerras() {
        return terras;
    }

    public int getGraos() {
        return graos;
    }

    public int getServos() {
        return servos;
    }

    public int getSoldados() {
        return soldados;
    }

    public int getConstrucoes() {
        return construcoes;
    }

    // Configuracoes do governo

    public int getTaxaImposto() {
        return taxaImposto;
    }

    public void setTaxaImposto(int taxa) {
        this.taxaImposto = taxa;
    }

    public String getNivelJustica() {
        return nivelJustica;
    }

    public void setNivelJustica(String nivel) {
        this.nivelJustica = nivel;
    }

    // =========================
    // CONTROLE ANUAL - ETAPA 7
    // =========================

    // Verifica se a colheita ja foi realizada
    public boolean isColheitaFeita() {
        return colheitaFeita;
    }

    // Registra a realizacao da colheita
    public void marcarColheitaFeita() {
        colheitaFeita = true;
    }

    // Verifica se os impostos ja foram arrecadados
    public boolean isImpostosArrecadados() {
        return impostosArrecadados;
    }

    // Registra a arrecadacao dos impostos
    public void marcarImpostosArrecadados() {
        impostosArrecadados = true;
    }

    // Avanca o ano e libera as operacoes anuais
    public void avancarAno() {

        anoAtual++;

        colheitaFeita = false;
        impostosArrecadados = false;
    }

    // =========================
    // FLORINS
    // =========================

    public void adicionarFlorins(int quantidade) {
        if (quantidade > 0 &&
            quantidade <= Integer.MAX_VALUE - florins) {
            florins += quantidade;
        }
    }

    public boolean removerFlorins(int quantidade) {
        if (quantidade <= 0 || quantidade > florins) {
            return false;
        }

        florins -= quantidade;
        return true;
    }

    // =========================
    // TERRAS
    // =========================

    public void adicionarTerras(int quantidade) {
        if (quantidade > 0 &&
            quantidade <= Integer.MAX_VALUE - terras) {
            terras += quantidade;
        }
    }

    public boolean removerTerras(int quantidade) {
        if (quantidade <= 0 || quantidade > terras) {
            return false;
        }

        terras -= quantidade;
        return true;
    }

    // =========================
    // GRAOS
    // =========================

    public void adicionarGraos(int quantidade) {
        if (quantidade > 0 &&
            quantidade <= Integer.MAX_VALUE - graos) {
            graos += quantidade;
        }
    }

    public boolean removerGraos(int quantidade) {
        if (quantidade <= 0 || quantidade > graos) {
            return false;
        }

        graos -= quantidade;
        return true;
    }

    // =========================
    // SERVOS
    // =========================

    public void adicionarServos(int quantidade) {
        if (quantidade > 0 &&
            quantidade <= Integer.MAX_VALUE - servos) {
            servos += quantidade;
        }
    }

    public boolean removerServos(int quantidade) {
        if (quantidade <= 0 || quantidade > servos) {
            return false;
        }

        servos -= quantidade;
        return true;
    }

    // =========================
    // SOLDADOS
    // =========================

    public void adicionarSoldados(int quantidade) {
        if (quantidade > 0 &&
            quantidade <= Integer.MAX_VALUE - soldados) {
            soldados += quantidade;
        }
    }

    public boolean removerSoldados(int quantidade) {
        if (quantidade <= 0 || quantidade > soldados) {
            return false;
        }

        soldados -= quantidade;
        return true;
    }

    // =========================
    // CONSTRUCOES
    // =========================

    public void adicionarConstrucoes(int quantidade) {
        if (quantidade > 0 &&
            quantidade <= Integer.MAX_VALUE - construcoes) {
            construcoes += quantidade;
        }
    }

    public boolean removerConstrucoes(int quantidade) {
        if (quantidade <= 0 || quantidade > construcoes) {
            return false;
        }

        construcoes -= quantidade;
        return true;
    }

    // Exibe as informacoes da cidade
    public void exibirStatus() {

        System.out.println("\n===== STATUS DA CIDADE =====");
        System.out.println("Cidade: " + nome);
        System.out.println("Governante: " + dono.getNome());
        System.out.println("Titulo: " + dono.getTitulo());
        System.out.println("Ano: " + anoAtual);
        System.out.println("Florins: " + florins);
        System.out.println("Terras: " + terras + " hectares");
        System.out.println("Graos: " + graos);
        System.out.println("Servos: " + servos);
        System.out.println("Taxa de imposto: " + taxaImposto + "%");
        System.out.println("Nivel de justica: " + nivelJustica);
        System.out.println("Soldados: " + soldados);
        System.out.println("Construcoes: " + construcoes);

        // ETAPA 7: situacao das operacoes anuais
        System.out.println("Colheita realizada: "
                + (colheitaFeita ? "Sim" : "Nao"));

        System.out.println("Impostos arrecadados: "
                + (impostosArrecadados ? "Sim" : "Nao"));

        System.out.println("============================");
    }
}
