```java
/**
 * Representa o jogo de batalha naval.
 */
package iscteiul.ista.battleship;

import java.util.List;

/**
 * Interface que define as operações principais do jogo.
 */
public interface IGame {

    /**
     * Efetua um tiro numa determinada posição.
     *
     * @param pos posição onde o tiro é efetuado
     * @return navio atingido, caso exista
     */
    IShip fire(IPosition pos);

    /**
     * Obtém a lista de posições onde já foram efetuados tiros.
     *
     * @return lista de tiros realizados
     */
    List<IPosition> getShots();

    /**
     * Obtém o número de tiros repetidos.
     *
     * @return número de tiros repetidos
     */
    int getRepeatedShots();

    /**
     * Obtém o número de tiros inválidos.
     *
     * @return número de tiros inválidos
     */
    int getInvalidShots();

    /**
     * Obtém o número de navios atingidos.
     *
     * @return número de acertos
     */
    int getHits();

    /**
     * Obtém o número de navios afundados.
     *
     * @return número de navios afundados
     */
    int getSunkShips();

    /**
     * Obtém o número de navios que ainda não foram afundados.
     *
     * @return número de navios restantes
     */
    int getRemainingShips();

    /**
     * Mostra as posições onde é possível efetuar tiros.
     */
    void printValidShots();

    /**
     * Mostra a frota do jogo.
     */
    void printFleet();
}
```
