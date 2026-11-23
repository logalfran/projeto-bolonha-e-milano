
public class Cidade {

    // CONSTANTES: valores fixos usados ao criar uma cidade.
    // static final significa que esses valores nao mudam.
    public static final int ANO_INICIAL = 1400;
    public static final int FLORINS_INICIAIS = 1000;
    public static final int TERRAS_INICIAIS = 10000;
    public static final int GRAOS_INICIAIS = 500;
    public static final int SERVOS_INICIAIS = 200;
    public static final int TAXA_IMPOSTO_INICIAL = 10;
    public static final String NIVEL_JUSTICA_INICIAL = "Moderada";
    public static final int SOLDADOS_INICIAIS = 0;
    public static final int CONSTRUCOES_INICIAIS = 0;

    // ATRIBUTOS: guardam os dados de cada cidade.
    // private protege os dados contra alteracoes diretas.
    private final String nome;
    private final Jogador dono;
    private int anoAtual;
    private int florins;
    private int terras;
    private int graos;
    private int servos;
    private int taxaImposto;
    private String nivelJustica;
    private int soldados;
    private int construcoes;

    // CONSTRUTOR: executado quando uma cidade e criada.
    // Recebe o nome da cidade e o jogador responsavel.
    public Cidade(String nome, Jogador dono) {
        this.nome = nome;
        this.dono = dono;
        this.anoAtual = ANO_INICIAL;
        this.florins = FLORINS_INICIAIS;
        this.terras = TERRAS_INICIAIS;
        this.graos = GRAOS_INICIAIS;
        this.servos = SERVOS_INICIAIS;
        this.taxaImposto = TAXA_IMPOSTO_INICIAL;
        this.nivelJustica = NIVEL_JUSTICA_INICIAL;
        this.soldados = SOLDADOS_INICIAIS;
        this.construcoes = CONSTRUCOES_INICIAIS;
    }

    // GETTERS: permitem consultar os atributos privados.
    public String getNome() { return nome; }
    public Jogador getDono() { return dono; }
    public int getAnoAtual() { return anoAtual; }
    public int getFlorins() { return florins; }
    public int getTerras() { return terras; }
    public int getGraos() { return graos; }
    public int getServos() { return servos; }
    public int getTaxaImposto() { return taxaImposto; }
    public String getNivelJustica() { return nivelJustica; }
    public int getSoldados() { return soldados; }
    public int getConstrucoes() { return construcoes; }

    // Avanca um ano. Sera utilizado na etapa de rodadas.
    public void avancarAno() {
        anoAtual++;
    }

    // METODOS DE RECURSOS
    // Adicionar aumenta o recurso.
    // Remover retorna false se a quantidade for invalida
    // ou se a cidade nao tiver recursos suficientes.

    public void adicionarFlorins(int quantidade) {
        if (quantidade > 0) florins += quantidade;
    }

    public boolean removerFlorins(int quantidade) {
        if (quantidade <= 0 || quantidade > florins) return false;
        florins -= quantidade;
        return true;
    }

    public void adicionarTerras(int quantidade) {
        if (quantidade > 0) terras += quantidade;
    }

    public boolean removerTerras(int quantidade) {
        if (quantidade <= 0 || quantidade > terras) return false;
        terras -= quantidade;
        return true;
    }

    public void adicionarGraos(int quantidade) {
        if (quantidade > 0) graos += quantidade;
    }

    public boolean removerGraos(int quantidade) {
        if (quantidade <= 0 || quantidade > graos) return false;
        graos -= quantidade;
        return true;
    }

    public void adicionarServos(int quantidade) {
        if (quantidade > 0) servos += quantidade;
    }

    public boolean removerServos(int quantidade) {
        if (quantidade <= 0 || quantidade > servos) return false;
        servos -= quantidade;
        return true;
    }

    public void adicionarSoldados(int quantidade) {
        if (quantidade > 0) soldados += quantidade;
    }

    public boolean removerSoldados(int quantidade) {
        if (quantidade <= 0 || quantidade > soldados) return false;
        soldados -= quantidade;
        return true;
    }

    public void adicionarConstrucoes(int quantidade) {
        if (quantidade > 0) construcoes += quantidade;
    }

    public boolean removerConstrucoes(int quantidade) {
        if (quantidade <= 0 || quantidade > construcoes) return false;
        construcoes -= quantidade;
        return true;
    }

    // SETTERS: alteram configuracoes do governo.
    public void setTaxaImposto(int taxaImposto) {
        if (taxaImposto >= 0 && taxaImposto <= 100) {
            this.taxaImposto = taxaImposto;
        }
    }

    public void setNivelJustica(String nivelJustica) {
        if (nivelJustica != null &&
            (nivelJustica.equals("Justa") ||
             nivelJustica.equals("Moderada") ||
             nivelJustica.equals("Severa"))) {
            this.nivelJustica = nivelJustica;
        }
    }
}
