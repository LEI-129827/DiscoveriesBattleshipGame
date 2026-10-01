
/**
 * Representa um navio da batalha naval.
 */
package iscteiul.ista.battleship;

import java.util.List;

/**
 * Interface que define as operações e características de um navio.
 */
public interface IShip {

    /**
     * Obtém a categoria do navio.
     *
     * @return categoria do navio
     */
    String getCategory();

    /**
     * Obtém o tamanho do navio.
     *
     * @return tamanho do navio
     */
    Integer getSize();

    /**
     * Obtém todas as posições ocupadas pelo navio.
     *
     * @return lista de posições do navio
     */
    List<IPosition> getPositions();

    /**
     * Obtém a posição principal do navio.
     *
     * @return posição do navio
     */
    IPosition getPosition();

    /**
     * Obtém a direção em que o navio está orientado.
     *
     * @return direção do navio
     */
    Compass getBearing();

    /**
     * Verifica se o navio ainda está a flutuar.
     *
     * @return true se o navio ainda não estiver afundado
     */
    boolean stillFloating();

    /**
     * Obtém a posição mais acima ocupada pelo navio.
     *
     * @return posição mais acima
     */
    int getTopMostPos();

    /**
     * Obtém a posição mais abaixo ocupada pelo navio.
     *
     * @return posição mais abaixo
     */
    int getBottomMostPos();

    /**
     * Obtém a posição mais à esquerda ocupada pelo navio.
     *
     * @return posição mais à esquerda
     */
    int getLeftMostPos();

    /**
     * Obtém a posição mais à direita ocupada pelo navio.
     *
     * @return posição mais à direita
     */
    int getRightMostPos();

    /**
     * Verifica se o navio ocupa uma determinada posição.
     *
     * @param pos posição a verificar
     * @return true se o navio ocupar essa posição
     */
    boolean occupies(IPosition pos);

    /**
     * Verifica se este navio está demasiado perto de outro navio.
     *
     * @param other outro navio
     * @return true se os navios estiverem demasiado próximos
     */
    boolean tooCloseTo(IShip other);

    /**
     * Verifica se o navio está demasiado perto de uma determinada posição.
     *
     * @param pos posição a verificar
     * @return true se estiver demasiado perto
     */
    boolean tooCloseTo(IPosition pos);

    /**
     * Regista um tiro numa posição do navio.
     *
     * @param pos posição onde o tiro foi efetuado
     */
    void shoot(IPosition pos);
}
