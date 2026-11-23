public class Cidade {

    // Atributos privados: protegem os dados da cidade.
    private String nome;
    private int anoAtual;
    private Jogador dono;

    // Recursos iniciais definidos pelo professor.
    private int florins;
    private int terras;
    private int graos;

    // Construtor: executado quando uma cidade é criada.
    public Cidade(String nome, Jogador dono) {
        this.nome = nome;
        this.dono = dono;
        this.anoAtual = 1400;

        this.florins = 1000;
        this.terras = 10000;
        this.graos = 500;
    }

    // Getters permitem consultar os atributos privados.
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

    // Avança um ano no calendário do jogo.
    public void avancarAno() {
        anoAtual++;
    }

    // Adiciona florins ao tesouro.
    public void adicionarFlorins(int quantidade) {
        if (quantidade > 0 &&
            quantidade <= Integer.MAX_VALUE - florins) {
            florins += quantidade;
        }
    }

    // Só remove florins quando existe saldo suficiente.
    // boolean informa se a operação funcionou.
    public boolean removerFlorins(int quantidade) {
        if (quantidade <= 0 || quantidade > florins) {
            return false;
        }

        florins -= quantidade;
        return true;
    }

    // Acrescenta hectares ao território.
    public void adicionarTerras(int quantidade) {
        if (quantidade > 0 &&
            quantidade <= Integer.MAX_VALUE - terras) {
            terras += quantidade;
        }
    }

    // Impede vender mais terras do que a cidade possui.
    public boolean removerTerras(int quantidade) {
        if (quantidade <= 0 || quantidade > terras) {
            return false;
        }

        terras -= quantidade;
        return true;
    }

    // Acrescenta grãos ao estoque.
    public void adicionarGraos(int quantidade) {
        if (quantidade > 0 &&
            quantidade <= Integer.MAX_VALUE - graos) {
            graos += quantidade;
        }
    }

    // Impede que o estoque fique negativo.
    public boolean removerGraos(int quantidade) {
        if (quantidade <= 0 || quantidade > graos) {
            return false;
        }

        graos -= quantidade;
        return true;
    }

    // Exibe as informações econômicas da cidade.
    public void exibirStatus() {
        System.out.println("\n===== STATUS DA CIDADE =====");
        System.out.println("Cidade: " + nome);
        System.out.println("Governante: " + dono.getNome());
        System.out.println("Título: " + dono.getTitulo());
        System.out.println("Ano: " + anoAtual);
        System.out.println("Florins: " + florins);
        System.out.println("Terras: " + terras + " hectares");
        System.out.println("Grãos: " + graos + " unidades");
        System.out.println("============================");
    }
}
