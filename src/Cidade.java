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

    // ETAPA 5: getters e setters do governo

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

    // Remove florins se houver saldo suficiente
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

    // Remove terras
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

    // Remove graos
    public boolean removerGraos(int quantidade) {
        if (quantidade <= 0 || quantidade > graos) {
            return false;
        }

        graos -= quantidade;
        return true;
    }

    // Aumenta a populacao
    public void adicionarServos(int quantidade) {
        if (quantidade > 0 &&
            quantidade <= Integer.MAX_VALUE - servos) {
            servos += quantidade;
        }
    }

    // Reduz a populacao
    public boolean removerServos(int quantidade) {
        if (quantidade <= 0 || quantidade > servos) {
            return false;
        }

        servos -= quantidade;
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

        // Novas informacoes da Etapa 5
        System.out.println("Taxa de imposto: " + taxaImposto + "%");
        System.out.println("Nivel de justica: " + nivelJustica);

        System.out.println("============================");
    }
}
