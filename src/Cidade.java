public class Cidade {

    // Atributos privados: representam os dados da cidade.
    // Usamos private para aplicar o encapsulamento.
    private String nome;
    private int anoAtual;
    private Jogador dono;

    // Recursos econômicos e população da cidade.
    private int florins;
    private int terras;
    private int graos;
    private int servos;

    // Construtor: define os valores iniciais da partida.
    public Cidade(String nome, Jogador dono) {
        this.nome = nome;
        this.dono = dono;
        this.anoAtual = 1400;

        this.florins = 1000;
        this.terras = 10000;
        this.graos = 500;
        this.servos = 200;
    }

    // GETTERS: consultam os atributos privados.
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

    // Avança o calendário em um ano.
    public void avancarAno() {
        anoAtual++;
    }

    // Adiciona dinheiro ao tesouro da cidade.
    public void adicionarFlorins(int quantidade) {
        if (quantidade > 0 &&
            quantidade <= Integer.MAX_VALUE - florins) {
            florins += quantidade;
        }
    }

    // Retorna false se não houver dinheiro suficiente.
    public boolean removerFlorins(int quantidade) {
        if (quantidade <= 0 || quantidade > florins) {
            return false;
        }

        florins -= quantidade;
        return true;
    }

    // Acrescenta terras ao território.
    public void adicionarTerras(int quantidade) {
        if (quantidade > 0 &&
            quantidade <= Integer.MAX_VALUE - terras) {
            terras += quantidade;
        }
    }

    // Impede que a quantidade de terras fique negativa.
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

    // Retira grãos apenas se houver estoque suficiente.
    public boolean removerGraos(int quantidade) {
        if (quantidade <= 0 || quantidade > graos) {
            return false;
        }

        graos -= quantidade;
        return true;
    }

    // ETAPA 4: acrescenta servos à população.
    public void adicionarServos(int quantidade) {
        if (quantidade > 0 &&
            quantidade <= Integer.MAX_VALUE - servos) {
            servos += quantidade;
        }
    }

    // ETAPA 4: remove servos sem permitir saldo negativo.
    public boolean removerServos(int quantidade) {
        if (quantidade <= 0 || quantidade > servos) {
            return false;
        }

        servos -= quantidade;
        return true;
    }

    // Exibe todos os recursos da cidade.
    public void exibirStatus() {
        System.out.println("\n===== STATUS DA CIDADE =====");
        System.out.println("Cidade: " + nome);
        System.out.println("Governante: " + dono.getNome());
        System.out.println("Titulo: " + dono.getTitulo());
        System.out.println("Ano: " + anoAtual);
        System.out.println("Florins: " + florins);
        System.out.println("Terras: " + terras + " hectares");
        System.out.println("Graos: " + graos + " unidades");
        System.out.println("Servos: " + servos);
        System.out.println("============================");
    }
}
