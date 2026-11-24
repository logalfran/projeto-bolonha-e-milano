public class Governo {

    // Limite maximo da taxa de imposto
    private static final int TAXA_MAXIMA = 50;

    // Multiplicadores de cada nivel de justica
    private static final double MULTIPLICADOR_JUSTA = 0.8;
    private static final double MULTIPLICADOR_MODERADA = 1.0;
    private static final double MULTIPLICADOR_SEVERA = 1.3;

    // Calcula e arrecada os impostos da cidade
    public int arrecadarImpostos(Cidade cidade) {

        // Calcula a base do imposto pela populacao
        int base = (int) ((long) cidade.getServos()
                * cidade.getTaxaImposto() / 100);

        // Identifica o multiplicador da justica
        double multiplicador =
                multiplicadorPara(cidade.getNivelJustica());

        // Calcula o valor final arrecadado
        int arrecadado = (int) (base * multiplicador);

        // Verifica se o saldo pode receber o valor
        if (arrecadado > Integer.MAX_VALUE - cidade.getFlorins()) {
            return 0;
        }

        // Adiciona o dinheiro aos florins da cidade
        cidade.adicionarFlorins(arrecadado);

        return arrecadado;
    }

    // Define uma nova taxa de imposto
    public boolean definirTaxaImposto(Cidade cidade, int taxa) {

        // A taxa precisa estar entre 0 e 50
        if (taxa < 0 || taxa > TAXA_MAXIMA) {
            return false;
        }

        cidade.setTaxaImposto(taxa);
        return true;
    }

    // Define o nivel de justica da cidade
    public boolean definirJustica(Cidade cidade, String nivel) {

        // Valida os tres niveis permitidos
        if ("Justa".equalsIgnoreCase(nivel)) {
            cidade.setNivelJustica("Justa");
        } else if ("Moderada".equalsIgnoreCase(nivel)) {
            cidade.setNivelJustica("Moderada");
        } else if ("Severa".equalsIgnoreCase(nivel)) {
            cidade.setNivelJustica("Severa");
        } else {
            return false;
        }

        return true;
    }

    // Retorna o multiplicador correspondente a justica
    private double multiplicadorPara(String nivel) {

        switch (nivel) {
            case "Justa":
                return MULTIPLICADOR_JUSTA;

            case "Severa":
                return MULTIPLICADOR_SEVERA;

            default:
                return MULTIPLICADOR_MODERADA;
        }
    }
}
