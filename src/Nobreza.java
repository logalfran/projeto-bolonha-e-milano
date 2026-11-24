
public class Nobreza {

    // Peso de cada recurso no prestigio
    private static final int PESO_CONSTRUCAO = 10;
    private static final int PESO_SOLDADO = 2;
    private static final int PESO_SERVO = 1;
    private static final int HECTARES_POR_PONTO = 100;

    // Prestigio necessario para cada titulo
    private static final int[] PRESTIGIO_MINIMO = {
        0, 400, 550, 700, 850, 1000
    };

    // Calcula o prestigio da cidade
    public int calcularPrestigio(Cidade cidade) {

        long prestigio =
                (long) cidade.getConstrucoes() * PESO_CONSTRUCAO
                + (long) cidade.getSoldados() * PESO_SOLDADO
                + (long) cidade.getServos() * PESO_SERVO
                + cidade.getTerras() / HECTARES_POR_PONTO;

        return (int) Math.min(prestigio, Integer.MAX_VALUE);
    }

    // Retorna o prestigio necessario para o proximo titulo
    public int prestigioParaProximoTitulo(Jogador jogador) {

        // Nao existe titulo superior ao de monarca
        if (jogador.ehMonarca()) {
            return -1;
        }

        int proximoNivel = jogador.getNivelTitulo() + 1;

        return PRESTIGIO_MINIMO[proximoNivel];
    }

    // Verifica se o governante pode ser promovido
    public boolean verificarPromocao(Cidade cidade) {

        Jogador jogador = cidade.getDono();

        int necessario =
                prestigioParaProximoTitulo(jogador);

        // Impede promover quem ja e monarca
        if (necessario == -1) {
            return false;
        }

        // Verifica se atingiu o prestigio necessario
        if (calcularPrestigio(cidade) >= necessario) {
            return jogador.promover();
        }

        return false;
    }
}
