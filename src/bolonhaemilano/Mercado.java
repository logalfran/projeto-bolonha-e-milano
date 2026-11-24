package bolonhaemilano;

public class Mercado {

    // Preços fixos provisórios para esta implementação.
    private static final int PRECO_GRAO = 2;
    private static final int PRECO_TERRA = 10;

    // Métodos para consultar os preços.
    public int getPrecoGrao() {
        return PRECO_GRAO;
    }

    public int getPrecoTerra() {
        return PRECO_TERRA;
    }

    // Compra grãos utilizando os florins da cidade.
    public boolean comprarGraos(Cidade cidade, int quantidade) {

        // Impede quantidades inválidas e estouro numérico.
        if (cidade == null || quantidade <= 0 ||
            quantidade > Integer.MAX_VALUE / PRECO_GRAO ||
            quantidade > Integer.MAX_VALUE - cidade.getGraos()) {
            return false;
        }

        int custo = quantidade * PRECO_GRAO;

        // Se não houver dinheiro, a compra é cancelada.
        if (!cidade.removerFlorins(custo)) {
            return false;
        }

        cidade.adicionarGraos(quantidade);
        return true;
    }

    // Vende grãos e acrescenta florins ao tesouro.
    public boolean venderGraos(Cidade cidade, int quantidade) {

        if (cidade == null || quantidade <= 0 ||
            quantidade > Integer.MAX_VALUE / PRECO_GRAO ||
            quantidade > cidade.getGraos()) {
            return false;
        }

        int receita = quantidade * PRECO_GRAO;

        if (receita > Integer.MAX_VALUE - cidade.getFlorins()) {
            return false;
        }

        if (!cidade.removerGraos(quantidade)) {
            return false;
        }

        cidade.adicionarFlorins(receita);
        return true;
    }

    // Compra terras para expandir o território.
    public boolean comprarTerras(Cidade cidade, int quantidade) {

        if (cidade == null || quantidade <= 0 ||
            quantidade > Integer.MAX_VALUE / PRECO_TERRA ||
            quantidade > Integer.MAX_VALUE - cidade.getTerras()) {
            return false;
        }

        int custo = quantidade * PRECO_TERRA;

        if (!cidade.removerFlorins(custo)) {
            return false;
        }

        cidade.adicionarTerras(quantidade);
        return true;
    }

    // Vende terras para obter mais florins.
    public boolean venderTerras(Cidade cidade, int quantidade) {

        if (cidade == null || quantidade <= 0 ||
            quantidade > Integer.MAX_VALUE / PRECO_TERRA ||
            quantidade > cidade.getTerras()) {
            return false;
        }

        int receita = quantidade * PRECO_TERRA;

        if (receita > Integer.MAX_VALUE - cidade.getFlorins()) {
            return false;
        }

        if (!cidade.removerTerras(quantidade)) {
            return false;
        }

        cidade.adicionarFlorins(receita);
        return true;
    }
}
