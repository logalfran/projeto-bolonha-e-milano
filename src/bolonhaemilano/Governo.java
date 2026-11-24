package bolonhaemilano;

public class Governo {

    private static final int TAXA_MAXIMA = 50;

    private static final double MULTIPLICADOR_JUSTA = 0.8;
    private static final double MULTIPLICADOR_MODERADA = 1.0;
    private static final double MULTIPLICADOR_SEVERA = 1.3;

    // Arrecada os impostos anuais
    public int arrecadarImpostos(Cidade cidade) {

        // ETAPA 7: impede uma segunda arrecadacao
        if (cidade.isImpostosArrecadados()) {
            return -1;
        }

        // Calcula a base do imposto
        int base = (int) ((long) cidade.getServos()
                * cidade.getTaxaImposto() / 100);

        // Identifica o multiplicador
        double multiplicador =
                multiplicadorPara(cidade.getNivelJustica());

        // Calcula o valor arrecadado
        int arrecadado = (int) (base * multiplicador);

        // Verifica o limite de armazenamento
        if (arrecadado > Integer.MAX_VALUE - cidade.getFlorins()) {
            return -2;
        }

        // Adiciona os impostos aos florins
        cidade.adicionarFlorins(arrecadado);

        // ETAPA 7: registra a arrecadacao
        cidade.marcarImpostosArrecadados();

        return arrecadado;
    }

    // Define uma nova taxa de imposto
    public boolean definirTaxaImposto(
            Cidade cidade, int taxa) {

        if (taxa < 0 || taxa > TAXA_MAXIMA) {
            return false;
        }

        cidade.setTaxaImposto(taxa);

        return true;
    }

    // Define o nivel de justica
    public boolean definirJustica(
            Cidade cidade, String nivel) {

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

    // Retorna o multiplicador correspondente
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
