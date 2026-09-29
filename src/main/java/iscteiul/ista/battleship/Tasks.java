/**
 * Contém as diferentes tarefas utilizadas para testar o jogo Batalha Naval.
 *
 * <p>As tarefas permitem criar navios e frotas, consultar o seu estado,
 * visualizar o mapa da frota e disparar rajadas de tiros.</p>
 */
package iscteiul.ista.battleship;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Fornece as diferentes tarefas utilizadas para testar o jogo Batalha Naval.
 *
 * <p>Cada tarefa introduz novas funcionalidades do jogo, começando pela
 * criação de navios e frotas e terminando com o disparo de tiros contra
 * uma frota.</p>
 */
public class Tasks {

    /**
     * Registo utilizado para apresentar informação durante a execução
     * das tarefas.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Número de tiros efetuados numa rajada.
     */
    private static final int NUMBER_SHOTS = 3;

    /**
     * Mensagem apresentada quando o utilizador abandona o jogo.
     */
    private static final String GOODBYE_MESSAGE = "Bons ventos!";

    /**
     * Comando utilizado para criar uma nova frota.
     */
    private static final String NOVAFROTA = "nova";

    /**
     * Comando utilizado para abandonar o jogo.
     */
    private static final String DESISTIR = "desisto";

    /**
     * Comando utilizado para efetuar uma rajada de três tiros.
     */
    private static final String RAJADA = "rajada";

    /**
     * Comando utilizado para apresentar os tiros válidos.
     */
    private static final String VERTIROS = "ver";

    /**
     * Comando utilizado para apresentar o mapa da frota.
     */
    private static final String BATOTA = "mapa";

    /**
     * Comando utilizado para apresentar o estado da frota.
     */
    private static final String STATUS = "estado";

    /**
     * Testa a criação de navios.
     *
     * <p>Para cada navio lido da entrada, este método lê três posições
     * e indica se o navio ocupa ou não cada uma dessas posições.</p>
     */
    public static void taskA() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            Ship s = readShip(in);
            if (s != null)
                for (int i = 0; i < NUMBER_SHOTS; i++) {
                    Position p = readPosition(in);
                    LOGGER.info("{} {}", p, s.occupies(p));
                }
        }
    }

    /**
     * Testa a criação e o estado das frotas.
     *
     * <p>O utilizador pode criar uma nova frota e consultar o seu estado.
     * A tarefa termina quando o utilizador introduz o comando
     * {@code desisto}.</p>
     */
    public static void taskB() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        String command = in.next();

        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete lá ...");
            }

            command = in.next();
        }

        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Testa a criação de frotas e a possibilidade de fazer batota,
     * permitindo visualizar o mapa completo da frota.
     *
     * <p>O utilizador pode criar uma frota, consultar o seu estado e
     * visualizar o mapa da frota através do comando {@code mapa}.</p>
     */
    public static void taskC() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        String command = in.next();

        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                case BATOTA:
                    LOGGER.info(fleet);
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete lá ...");
            }

            command = in.next();
        }

        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Testa a parte de combate do jogo.
     *
     * <p>O utilizador pode criar uma frota, consultar o seu estado,
     * visualizar o mapa, efetuar rajadas de três tiros e visualizar
     * os tiros válidos.</p>
     */
    public static void taskD() {

        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        IGame game = null;
        String command = in.next();

        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    game = new Game(fleet);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                case BATOTA:
                    if (fleet != null)
                        game.printFleet();
                    break;
                case RAJADA:
                    if (game != null) {
                        firingRound(in, game);

                        LOGGER.info("Hits: {} Inv: {} Rep: {} Restam {} navios.",
                                game.getHits(),
                                game.getInvalidShots(),
                                game.getRepeatedShots(),
                                game.getRemainingShips());

                        if (game.getRemainingShips() == 0)
                            LOGGER.info(
                                "Maldito sejas, Java Sparrow, eu voltarei, glub glub glub...");
                    }
                    break;
                case VERTIROS:
                    if (game != null)
                        game.printValidShots();
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete ...");
            }

            command = in.next();
        }

        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Constrói uma frota utilizando os dados fornecidos pelo utilizador.
     *
     * <p>O método continua a ler navios até que o número necessário de
     * navios seja adicionado com sucesso à frota.</p>
     *
     * @param in scanner utilizado para ler os dados da frota
     * @return a frota que contém os navios criados com sucesso
     */
    static Fleet buildFleet(Scanner in) {
        assert in != null;

        Fleet fleet = new Fleet();
        int i = 0;

        while (i <= Fleet.FLEET_SIZE) {
            IShip s = readShip(in);

            if (s != null) {
                boolean success = fleet.addShip(s);

                if (success)
                    i++;
                else
                    LOGGER.info(
                        "Falha na criacao de {} {} {}",
                        s.getCategory(),
                        s.getBearing(),
                        s.getPosition()
                    );
            } else {
                LOGGER.info("Navio desconhecido!");
            }
        }

        LOGGER.info("{} navios adicionados com sucesso!", i);
        return fleet;
    }

    /**
     * Lê os dados necessários para criar um navio.
     *
     * <p>O método lê o tipo de navio, a sua posição e a sua orientação,
     * criando de seguida o navio correspondente.</p>
     *
     * @param in scanner utilizado para ler os dados do navio
     * @return o navio criado, ou {@code null} caso o tipo de navio seja desconhecido
     */
    static Ship readShip(Scanner in) {
        String shipKind = in.next();
        Position pos = readPosition(in);
        char c = in.next().charAt(0);
        Compass bearing = Compass.charToCompass(c);

        return Ship.buildShip(shipKind, bearing, pos);
    }

    /**
     * Lê uma posição a partir da entrada.
     *
     * <p>Uma posição é definida por uma linha e uma coluna.</p>
     *
     * @param in scanner utilizado para ler a posição
     * @return a posição lida a partir da entrada
     */
    static Position readPosition(Scanner in) {
        int row = in.nextInt();
        int column = in.nextInt();

        return new Position(row, column);
    }

    /**
     * Efetua uma rajada de três tiros sobre uma frota.
     *
     * <p>Para cada tiro, a posição é lida da entrada e o jogo determina
     * se algum navio foi atingido.</p>
     *
     * @param in scanner utilizado para ler as posições dos tiros
     * @param game jogo no qual os tiros são efetuados
     */
    static void firingRound(Scanner in, IGame game) {
        for (int i = 0; i < NUMBER_SHOTS; i++) {
            IPosition pos = readPosition(in);
            IShip sh = game.fire(pos);

            if (sh != null)
                LOGGER.info(
                    "Mas... mas... {}s nao sao a prova de bala? :-(",
                    sh.getCategory()
                );
        }
    }
}
