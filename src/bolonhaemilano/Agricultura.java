package bolonhaemilano;

public class Agricultura {

    private static final int HECTARES_POR_SERVO = 5;
    private static final int RENDIMENTO_POR_HECTARE = 2;
    private static final int SERVOS_POR_GRAO = 5;
    private static final int CRESCIMENTO_PERCENTUAL = 5;
    private static final int PERCENTUAL_MINIMO = 20;
    private static final int PERCENTUAL_MAXIMO = 80;

    // Processa a colheita anual
    public int processarColheita(Cidade cidade) {

        // ETAPA 7: impede uma segunda colheita no ano
        if (cidade.isColheitaFeita()) {
            return -1;
        }

        // Calcula a capacidade de cultivo
        long capacidade =
                (long) cidade.getServos() * HECTARES_POR_SERVO;

        long hectares =
                Math.min(cidade.getTerras(), capacidade);

        // Calcula as sementes necessarias
        long sementes = hectares / 2;

        // Limita o cultivo ao estoque de sementes
        if (sementes > cidade.getGraos()) {
            hectares = (long) cidade.getGraos() * 2;
            sementes = hectares / 2;
        }

        // Calcula a producao
        long producao = hectares * RENDIMENTO_POR_HECTARE;

        long estoqueFinal =
                (long) cidade.getGraos() - sementes + producao;

        // Evita ultrapassar o limite de armazenamento
        if (estoqueFinal > Integer.MAX_VALUE) {
            return -2;
        }

        // Desconta as sementes
        if (sementes > 0) {
            cidade.removerGraos((int) sementes);
        }

        // Adiciona os graos produzidos
        if (producao > 0) {
            cidade.adicionarGraos((int) producao);
        }

        // ETAPA 7: registra a colheita
        cidade.marcarColheitaFeita();

        // Exibe o resultado
        System.out.println("\n===== RESULTADO DA COLHEITA =====");
        System.out.println("Hectares cultivados: " + hectares);
        System.out.println("Sementes utilizadas: " + sementes);
        System.out.println("Graos produzidos: " + producao);
        System.out.println("Estoque atual: " + cidade.getGraos());

        return (int) hectares;
    }

    // Distribui alimentos para a populacao
    public boolean distribuirAlimentos(
            Cidade cidade, int percentual) {

        // Valida o percentual informado
        if (percentual < PERCENTUAL_MINIMO ||
            percentual > PERCENTUAL_MAXIMO) {
            return false;
        }

        int alimentoDistribuido = (int)
                ((long) cidade.getGraos() * percentual / 100);

        if (alimentoDistribuido > 0) {
            cidade.removerGraos(alimentoDistribuido);
        }

        int necessidade =
                cidade.getServos() / SERVOS_POR_GRAO;

        System.out.println("\n===== DISTRIBUICAO =====");
        System.out.println("Alimentos: " + alimentoDistribuido);
        System.out.println("Necessidade: " + necessidade);

        if (alimentoDistribuido >= necessidade) {

            // Crescimento da populacao
            int crescimento = (int)
                    ((long) cidade.getServos()
                    * CRESCIMENTO_PERCENTUAL / 100);

            cidade.adicionarServos(crescimento);

            System.out.println("Populacao alimentada!");
            System.out.println("Novos servos: " + crescimento);

        } else {

            // Reducao da populacao por falta de alimentos
            int deficit = necessidade - alimentoDistribuido;

            int perda = Math.min(deficit, cidade.getServos());

            if (perda > 0) {
                cidade.removerServos(perda);
            }

            System.out.println("Faltaram alimentos!");
            System.out.println("Servos perdidos: " + perda);
        }

        System.out.println("Populacao atual: " + cidade.getServos());
        System.out.println("Graos restantes: " + cidade.getGraos());

        return true;
    }
}
