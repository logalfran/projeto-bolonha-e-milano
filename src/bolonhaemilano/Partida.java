package bolonhaemilano;


import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Partida {

    // Limites da partida
    public static final int MAXIMO_JOGADORES = 4;
    private static final int ANO_FINAL = 1410;

    // Lista das cidades participantes
    private List<Cidade> cidades = new ArrayList<>();

    // Indice do jogador que esta jogando
    private int indiceAtual = 0;

    // Adiciona uma cidade a partida
    public boolean adicionarCidade(Cidade cidade) {

        if (cidade == null ||
            cidades.size() >= MAXIMO_JOGADORES ||
            cidades.contains(cidade)) {
            return false;
        }

        cidades.add(cidade);

        return true;
    }

    // Retorna a lista de cidades
    public List<Cidade> getCidades() {
        return Collections.unmodifiableList(cidades);
    }

    // Retorna a cidade do jogador atual
    public Cidade getCidadeAtual() {

        if (cidades.isEmpty()) {
            return null;
        }

        return cidades.get(indiceAtual);
    }

    // Retorna o ano atual da partida
    public int getAnoAtual() {

        if (cidades.isEmpty()) {
            return 1400;
        }

        return cidades.get(0).getAnoAtual();
    }

    // Retorna o ultimo ano permitido
    public int getAnoFinal() {
        return ANO_FINAL;
    }

    // Passa a vez para o proximo jogador
    public boolean passarTurno() {

        if (cidades.isEmpty()) {
            return false;
        }

        indiceAtual++;

        // Verifica se todos os jogadores ja jogaram
        if (indiceAtual >= cidades.size()) {

            indiceAtual = 0;

            // Indica que o ano terminou
            return true;
        }

        return false;
    }

    // Encerra o ano e verifica as promocoes
    public List<Cidade> encerrarAno(Nobreza nobreza) {

        List<Cidade> promovidas = new ArrayList<>();

        // Percorre todas as cidades participantes
        for (Cidade cidade : cidades) {

            // Avanca o ano e libera colheita e impostos
            cidade.avancarAno();

            // Verifica se o governante pode subir de titulo
            if (nobreza.verificarPromocao(cidade)) {
                promovidas.add(cidade);
            }
        }

        return promovidas;
    }

    // Verifica se a partida terminou
    public boolean terminou() {

        // Verifica se algum jogador virou monarca
        for (Cidade cidade : cidades) {

            if (cidade.getDono().ehMonarca()) {
                return true;
            }
        }

        // Verifica se o ultimo ano foi encerrado
        return getAnoAtual() > ANO_FINAL;
    }

    // Determina a cidade vencedora
    public Cidade determinarVencedor(Nobreza nobreza) {

        if (cidades.isEmpty()) {
            return null;
        }

        Cidade vencedora = cidades.get(0);

        for (Cidade cidade : cidades) {

            int nivelAtual =
                    cidade.getDono().getNivelTitulo();

            int nivelVencedora =
                    vencedora.getDono().getNivelTitulo();

            // Prioriza o maior titulo de nobreza
            if (nivelAtual > nivelVencedora) {

                vencedora = cidade;

            } else if (nivelAtual == nivelVencedora) {

                // Em caso de empate, compara o prestigio
                if (nobreza.calcularPrestigio(cidade) >
                    nobreza.calcularPrestigio(vencedora)) {

                    vencedora = cidade;
                }
            }
        }

        return vencedora;
    }
}
