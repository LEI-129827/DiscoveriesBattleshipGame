
/**
 * Representa uma partida do jogo de batalha naval.
 */
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Alterei este código pelo IntelliJ, e vou dar submit e pull.
 * Implementa as principais operações de uma partida de batalha naval.
 *
 * @author fba
 */
public class Game implements IGame {

    // Frota de navios utilizada no jogo.
    private IFleet fleet;

    // Lista das posições onde já foram realizados tiros.
    private List<IPosition> shots;

    // Contadores dos diferentes tipos de tiros e resultados.
    private Integer countInvalidShots;
    private Integer countRepeatedShots;
    private Integer countHits;
    private Integer countSinks;


    /**
     * Cria um novo jogo com a frota indicada.
     *
     * @param fleet frota utilizada no jogo
     */
    public Game(IFleet fleet) {
        shots = new ArrayList<>();
        countInvalidShots = 0;
        countRepeatedShots = 0;
        this.fleet = fleet;
    }

    /**
     * Efetua um tiro numa determinada posição.
     * Verifica se o tiro é válido e se já foi realizado anteriormente.
     * Se atingir um navio, regista o acerto e verifica se o navio foi afundado.
     *
     * @param pos posição onde o tiro é efetuado
     * @return navio atingido e afundado, ou null caso contrário
     */
    @Override
    public IShip fire(IPosition pos) {
        if (!validShot(pos))
            countInvalidShots++;
        else { // tiro válido
            if (repeatedShot(pos))
                countRepeatedShots++;
            else {
                shots.add(pos);
                IShip s = fleet.shipAt(pos);
                if (s != null) {
                    s.shoot(pos);
                    countHits++;
                    if (!s.stillFloating()) {
                        countSinks++;
                        return s;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Obtém a lista de posições onde já foram realizados tiros.
     *
     * @return lista de tiros realizados
     */
    @Override
    public List<IPosition> getShots() {
        return shots;
    }

    /**
     * Obtém o número de tiros repetidos.
     *
     * @return número de tiros repetidos
     */
    @Override
    public int getRepeatedShots() {
        return this.countRepeatedShots;
    }

    /**
     * Obtém o número de tiros inválidos.
     *
     * @return número de tiros inválidos
     */
    @Override
    public int getInvalidShots() {
        return this.countInvalidShots;
    }

    /**
     * Obtém o número de tiros que atingiram um navio.
     *
     * @return número de acertos
     */
    @Override
    public int getHits() {
        return this.countHits;
    }

    /**
     * Obtém o número de navios afundados.
     *
     * @return número de navios afundados
     */
    @Override
    public int getSunkShips() {
        return this.countSinks;
    }

    /**
     * Obtém o número de navios que ainda estão a flutuar.
     *
     * @return número de navios restantes
     */
    @Override
    public int getRemainingShips() {
        List<IShip> floatingShips = fleet.getFloatingShips();
        return floatingShips.size();
    }

    /**
     * Verifica se uma posição corresponde a um tiro válido dentro do tabuleiro.
     *
     * @param pos posição a verificar
     * @return true se a posição for válida
     */
    private boolean validShot(IPosition pos) {
        return (pos.getRow() >= 0 && pos.getRow() <= Fleet.BOARD_SIZE && pos.getColumn() >= 0
                && pos.getColumn() <= Fleet.BOARD_SIZE);
    }

    /**
     * Verifica se já foi realizado um tiro numa determinada posição.
     *
     * @param pos posição a verificar
     * @return true se a posição já tiver sido atingida
     */
    private boolean repeatedShot(IPosition pos) {
        for (int i = 0; i < shots.size(); i++)
            if (shots.get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Mostra no ecrã um conjunto de posições no tabuleiro.
     *
     * @param positions posições que serão mostradas
     * @param marker símbolo utilizado para representar as posições
     */
    public void printBoard(List<IPosition> positions, Character marker) {
        char[][] map = new char[Fleet.BOARD_SIZE][Fleet.BOARD_SIZE];

        // Inicializa o tabuleiro com posições vazias.
        for (int r = 0; r < Fleet.BOARD_SIZE; r++)
            for (int c = 0; c < Fleet.BOARD_SIZE; c++)
                map[r][c] = '.';

        // Marca no tabuleiro as posições indicadas.
        for (IPosition pos : positions)
            map[pos.getRow()][pos.getColumn()] = marker;

        // Mostra o tabuleiro no ecrã.
        for (int row = 0; row < Fleet.BOARD_SIZE; row++) {
            for (int col = 0; col < Fleet.BOARD_SIZE; col++)
                System.out.print(map[row][col]);
            System.out.println();
        }
    }

    /**
     * Mostra o tabuleiro com os tiros já realizados.
     */
    public void printValidShots() {
        printBoard(getShots(), 'X');
    }

    /**
     * Mostra no tabuleiro as posições ocupadas pelos navios.
     */
    public void printFleet() {
        List<IPosition> shipPositions = new ArrayList<IPosition>();

        // Obtém todas as posições ocupadas pelos navios.
        for (IShip s : fleet.getShips())
            shipPositions.addAll(s.getPositions());

        printBoard(shipPositions, '#');
    }
}
