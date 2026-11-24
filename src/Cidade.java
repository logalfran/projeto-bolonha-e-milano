public class Cidade {

    // Informacoes da cidade
    private String nome;
    private int anoAtual;
    private Jogador dono;

    // Recursos da cidade
    private int florins;
    private int terras;
    private int graos;
    private int servos;

    // ETAPA 5: configuracoes do governo
    private int taxaImposto;
    private String nivelJustica;

    // ETAPA 6: novos recursos
    private int soldados;
    private int construcoes;

    // Construtor da cidade
    public Cidade(String nome, Jogador dono) {
        this.nome = nome;
        this.dono = dono;
        this.anoAtual = 1400;
        this.florins = 1000;
        this.terras = 10000;
        this.graos = 500;
        this.servos = 200;

        // Configuracoes iniciais do governo
        this.taxaImposto = 10;
        this.nivelJustica = "Moderada";

        // Recursos iniciais da Etapa 6
        this.soldados = 0;
        this.construcoes = 0;
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

    // ETAPA 6: getters dos novos recursos

    public int getSoldados() {
        return soldados;
    }

    public int getConstrucoes() {
        return construcoes;
    }

    // Avanca o ano da cidade
    public void avancarAno() {
        anoAtual++;
    }

    // Adiciona florins
    public void adicionarFlorins(int quantidade) {
        if (quantidade > 0 &&
            quantidade <= Integer.MAX_VALUE - florins) {
            florins += quantidade;
        }
    }

    // Remove florins com seguranca
    public boolean removerFlorins(int quantidade) {
        if (quantidade <= 0 || quantidade > florins) {
            return false;
        }

        florins -= quantidade;
        return true;
    }

    // Adiciona terras
    public void adicionarTerras(int quantidade) {
        if (quantidade > 0 &&
            quantidade <= Integer.MAX_VALUE - terras) {
            terras += quantidade;
        }
    }

    // Remove terras com seguranca
    public boolean removerTerras(int quantidade) {
        if (quantidade <= 0 || quantidade > terras) {
            return false;
        }

        terras -= quantidade;
        return true;
    }

    // Adiciona graos
    public void adicionarGraos(int quantidade) {
        if (quantidade > 0 &&
            quantidade <= Integer.MAX_VALUE - graos) {
            graos += quantidade;
        }
    }

    // Remove graos com seguranca
    public boolean removerGraos(int quantidade) {
        if (quantidade <= 0 || quantidade > graos) {
            return false;
        }

        graos -= quantidade;
        return true;
    }

    // Adiciona servos
    public void adicionarServos(int quantidade) {
        if (quantidade > 0 &&
            quantidade <= Integer.MAX_VALUE - servos) {
            servos += quantidade;
        }
    }

    // Remove servos com seguranca
    public boolean removerServos(int quantidade) {
        if (quantidade <= 0 || quantidade > servos) {
            return false;
        }

        servos -= quantidade;
        return true;
    }

    // =========================
    // SOLDADOS - ETAPA 6
    // =========================

    // Aumenta a quantidade de soldados
    public void adicionarSoldados(int quantidade) {
        if (quantidade > 0 &&
            quantidade <= Integer.MAX_VALUE - soldados) {
            soldados += quantidade;
        }
    }

    // Remove soldados com seguranca
    public boolean removerSoldados(int quantidade) {
        if (quantidade <= 0 || quantidade > soldados) {
            return false;
        }

        soldados -= quantidade;
        return true;
    }

    // =========================
    // CONSTRUCOES - ETAPA 6
    // =========================

    // Aumenta a quantidade de construcoes
    public void adicionarConstrucoes(int quantidade) {
        if (quantidade > 0 &&
            quantidade <= Integer.MAX_VALUE - construcoes) {
            construcoes += quantidade;
        }
    }

    // Remove construcoes com seguranca
    public boolean removerConstrucoes(int quantidade) {
        if (quantidade <= 0 || quantidade > construcoes) {
            return false;
        }

        construcoes -= quantidade;
        return true;
    }

    // Exibe todas as informacoes da cidade
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

        // Informacoes do governo
        System.out.println("Taxa de imposto: " + taxaImposto + "%");
        System.out.println("Nivel de justica: " + nivelJustica);

        // Novas informacoes da Etapa 6
        System.out.println("Soldados: " + soldados);
        System.out.println("Construcoes: " + construcoes);

        System.out.println("============================");
    }
}
