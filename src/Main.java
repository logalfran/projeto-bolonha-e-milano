
public class Main {

    // O método main é o ponto de entrada do programa.
    // Isso significa que o Java começa a executar o jogo por aqui.
    public static void main(String[] args) {

        // Criamos um objeto chamado menu da classe Menu.
        // Essa classe será responsável por mostrar as opções
        // e receber as escolhas do jogador.
        Menu menu = new Menu();

        // Chamamos o método executar() da classe Menu.
        // Ele inicia o jogo e mantém o menu funcionando
        // até o jogador escolher a opção de sair.
        menu.executar();
    }
}
