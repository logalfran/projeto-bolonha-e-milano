
public class Jogador {

    // Atributos privados: protegem os dados do jogador.
    private String nome;
    private String titulo;

    // Construtor: executado quando criamos um jogador.
    public Jogador(String nome) {
        this.nome = nome;

        // Todo jogador começa como Cavaleiro.
        this.titulo = "Cavaleiro";
    }

    // Getter: permite consultar o nome.
    public String getNome() {
        return nome;
    }

    // Getter: permite consultar o título.
    public String getTitulo() {
        return titulo;
    }

    // Permite atualizar o título durante o jogo.
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
