public class Construcoes {

    // Custos definidos pelas regras do jogo
    private static final int PRECO_SOLDADO = 5;
    private static final int SERVOS_POR_SOLDADO = 1;
    private static final int PRECO_CONSTRUCAO = 20;

    // Retorna o preco de um soldado
    public int getPrecoSoldado() {
        return PRECO_SOLDADO;
    }

    // Retorna o preco de uma construcao
    public int getPrecoConstrucao() {
        return PRECO_CONSTRUCAO;
    }

    // Recruta soldados para a cidade
    public boolean recrutarSoldados(Cidade cidade, int quantidade) {

        // Impede quantidades invalidas
        if (quantidade <= 0) {
            return false;
        }

        // Calcula os recursos necessarios
        long custo = (long) quantidade * PRECO_SOLDADO;

        long servosNecessarios =
                (long) quantidade * SERVOS_POR_SOLDADO;

        // Verifica os dois recursos antes de descontar
        if (cidade.getFlorins() < custo ||
            cidade.getServos() < servosNecessarios) {
            return false;
        }

        // Evita ultrapassar o limite de armazenamento
        if (quantidade > Integer.MAX_VALUE - cidade.getSoldados()) {
            return false;
        }

        // Desconta os recursos necessarios
        cidade.removerFlorins((int) custo);
        cidade.removerServos((int) servosNecessarios);

        // Adiciona os novos soldados
        cidade.adicionarSoldados(quantidade);

        return true;
    }

    // Realiza novas construcoes na cidade
    public boolean construir(Cidade cidade, int quantidade) {

        // Impede quantidades invalidas
        if (quantidade <= 0) {
            return false;
        }

        // Calcula o custo total das construcoes
        long custo = (long) quantidade * PRECO_CONSTRUCAO;

        // Verifica se existem florins suficientes
        if (custo > cidade.getFlorins()) {
            return false;
        }

        // Evita ultrapassar o limite de armazenamento
        if (quantidade > Integer.MAX_VALUE - cidade.getConstrucoes()) {
            return false;
        }

        // Desconta o dinheiro
        if (!cidade.removerFlorins((int) custo)) {
            return false;
        }

        // Adiciona as construcoes
        cidade.adicionarConstrucoes(quantidade);

        return true;
    }
}
