package bolonhaemilano;

public class Jogador {

    private String nome;

    // Nivel atual do titulo de nobreza
    private int nivelTitulo;

    // Titulos em ordem de evolucao
    private static final String[] TITULOS = {
        "Cavaleiro",
        "Barão",
        "Conde",
        "Marquês",
        "Duque",
        "Rei/Rainha"
    };

    // Construtor
    public Jogador(String nome) {
        this.nome = nome;
        this.nivelTitulo = 0;
    }

    public String getNome() {
        return nome;
    }

    // Retorna o titulo correspondente ao nivel
    public String getTitulo() {
        return TITULOS[nivelTitulo];
    }

    public int getNivelTitulo() {
        return nivelTitulo;
    }

    // Promove o jogador em um nivel
    public boolean promover() {

        // Impede ultrapassar o ultimo titulo
        if (ehMonarca()) {
            return false;
        }

        nivelTitulo++;
        return true;
    }

    // Verifica se o jogador chegou ao titulo maximo
    public boolean ehMonarca() {
        return nivelTitulo == TITULOS.length - 1;
    }
}
