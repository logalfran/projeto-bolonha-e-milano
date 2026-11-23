public class Agricultura {

    // Constantes: regras fixas da agricultura.
    private static final int HECTARES_POR_SERVO = 5;
    private static final int RENDIMENTO_POR_HECTARE = 2;
    private static final int SERVOS_POR_GRAO = 5;
    private static final int CRESCIMENTO_PERCENTUAL = 5;
    private static final int PERCENTUAL_MINIMO = 20;
    private static final int PERCENTUAL_MAXIMO = 80;

    // Processa a colheita de acordo com terra,
    // trabalhadores e sementes disponíveis.
    public void processarColheita(Cidade cidade) {

        // Cada servo consegue cultivar até 5 hectares.
        // Não podemos cultivar mais terras do que possuímos.
        long capacidade = (long) cidade.getServos()
                * HECTARES_POR_SERVO;

        long hectares = Math.min(
                cidade.getTerras(), capacidade
        );

        // Cada 2 hectares precisam de 1 grão como semente.
        long sementes = hectares / 2;

        // Se faltarem sementes, reduzimos a área cultivada.
        if (sementes > cidade.getGraos()) {
            hectares = (long) cidade.getGraos() * 2;
            sementes = hectares / 2;
        }

        // Calcula quantos grãos serão produzidos.
        long producao = hectares * RENDIMENTO_POR_HECTARE;

        // Evita ultrapassar o limite de armazenamento int.
        long estoqueFinal = (long) cidade.getGraos()
                - sementes + producao;

        if (estoqueFinal > Integer.MAX_VALUE) {
            System.out.println("Estoque excederia o limite.");
            return;
        }

        // Remove as sementes utilizadas.
        if (sementes > 0) {
            cidade.removerGraos((int) sementes);
        }

        // Adiciona os grãos produzidos na colheita.
        if (producao > 0) {
            cidade.adicionarGraos((int) producao);
        }

        System.out.println("\n===== RESULTADO DA COLHEITA =====");
        System.out.println("Hectares cultivados: " + hectares);
        System.out.println("Sementes utilizadas: " + sementes);
        System.out.println("Graos produzidos: " + producao);
        System.out.println("Estoque atual: " + cidade.getGraos());
    }

    // Distribui uma porcentagem dos grãos à população.
    // Retorna false quando o percentual é inválido.
    public boolean distribuirAlimentos(
            Cidade cidade, int percentual) {

        // O professor exige valores entre 20 e 80.
        if (percentual < PERCENTUAL_MINIMO ||
            percentual > PERCENTUAL_MAXIMO) {
            return false;
        }

        // Calcula a quantidade de grãos distribuídos.
        int alimentoDistribuido = (int)
                ((long) cidade.getGraos() * percentual / 100);

        // Retira os alimentos do estoque.
        if (alimentoDistribuido > 0) {
            cidade.removerGraos(alimentoDistribuido);
        }

        // Cada 5 servos precisam de 1 grão.
        int necessidade = cidade.getServos()
                / SERVOS_POR_GRAO;

        System.out.println("\n===== DISTRIBUICAO =====");
        System.out.println("Alimentos: " + alimentoDistribuido);
        System.out.println("Necessidade: " + necessidade);

        if (alimentoDistribuido >= necessidade) {

            // Com comida suficiente, a população cresce 5%.
            int crescimento = (int)
                    ((long) cidade.getServos()
                    * CRESCIMENTO_PERCENTUAL / 100);

            cidade.adicionarServos(crescimento);

            System.out.println("Populacao alimentada!");
            System.out.println("Novos servos: " + crescimento);

        } else {

            // Com falta de alimentos, perde-se população.
            int deficit = necessidade - alimentoDistribuido;

            // Math.min impede remover mais servos
            // do que a cidade realmente possui.
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
