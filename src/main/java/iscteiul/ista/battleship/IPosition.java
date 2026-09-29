```java
/**
 * Representa uma posição na grelha do jogo.
 */
package iscteiul.ista.battleship;

/**
 * Interface que define as operações de uma posição na grelha.
 * Uma posição pode estar ocupada e pode ser atingida por um tiro.
 *
 * @author fba
 */
public interface IPosition {

    /**
     * Obtém a linha onde se encontra a posição.
     *
     * @return número da linha
     */
    int getRow();

    /**
     * Obtém a coluna onde se encontra a posição.
     *
     * @return número da coluna
     */
    int getColumn();

    /**
     * Verifica se esta posição é igual a outra posição.
     *
     * @param other posição a comparar
     * @return true se as posições forem iguais
     */
    boolean equals(Object other);

    /**
     * Verifica se esta posição é adjacente a outra posição.
     *
     * @param other posição a comparar
     * @return true se as posições forem adjacentes
     */
    boolean isAdjacentTo(IPosition other);

    /**
     * Marca a posição como ocupada por um navio.
     */
    void occupy();

    /**
     * Regista um tiro nesta posição.
     */
    void shoot();

    /**
     * Verifica se a posição está ocupada por um navio.
     *
     * @return true se a posição estiver ocupada
     */
    boolean isOccupied();

    /**
     * Verifica se a posição foi atingida por um tiro.
     *
     * @return true se a posição tiver sido atingida
     */
    boolean isHit();
}
```
