package iscteiul.ista.battleship;

import java.util.Objects;

/**
 * Representa uma posição (célula) do tabuleiro da Batalha Naval, identificada
 * pela linha e pela coluna.
 * <p>
 * Cada posição guarda ainda o seu estado: se está ocupada por (parte de) um
 * navio e se já foi alvo de um tiro.
 * </p>
 *
 * @author NOME APELIDO (LEI-XXXXX)
 * @version 1.0
 * @see IPosition
 */
public class Position implements IPosition {

    /** Índice da linha desta posição no tabuleiro. */
    private int row;

    /** Índice da coluna desta posição no tabuleiro. */
    private int column;

    /** Indica se a posição está ocupada por um navio. */
    private boolean isOccupied;

    /** Indica se a posição já foi atingida por um tiro. */
    private boolean isHit;

    /**
     * Cria uma nova posição livre e ainda não atingida.
     *
     * @param row    índice da linha
     * @param column índice da coluna
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.isOccupied = false;
        this.isHit = false;
    }

    /**
     * Devolve a linha desta posição.
     *
     * @return o índice da linha
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * Devolve a coluna desta posição.
     *
     * @return o índice da coluna
     */
    @Override
    public int getColumn() {
        return column;
    }

    /**
     * Calcula o código de hash da posição, com base na linha, na coluna e no
     * estado (ocupada e atingida).
     *
     * @return o código de hash desta posição
     */
    @Override
    public int hashCode() {
        return Objects.hash(column, isHit, isOccupied, row);
    }

    /**
     * Compara esta posição com outro objeto. Duas posições são consideradas
     * iguais se tiverem a mesma linha e a mesma coluna, independentemente do
     * seu estado (ocupada ou atingida).
     *
     * @param otherPosition o objeto a comparar
     * @return {@code true} se {@code otherPosition} for uma {@link IPosition}
     *         com a mesma linha e coluna; {@code false} caso contrário
     */
    @Override
    public boolean equals(Object otherPosition) {
        if (this == otherPosition)
            return true;
        if (otherPosition instanceof IPosition) {
            IPosition other = (IPosition) otherPosition;
            return (this.getRow() == other.getRow() && this.getColumn() == other.getColumn());
        } else {
            return false;
        }
    }

    /**
     * Verifica se esta posição é adjacente a outra, incluindo as diagonais.
     * Uma posição é considerada adjacente a si própria.
     *
     * @param other a posição a comparar
     * @return {@code true} se a diferença de linhas e a diferença de colunas
     *         forem ambas no máximo 1; {@code false} caso contrário
     */
    @Override
    public boolean isAdjacentTo(IPosition other) {
        return (Math.abs(this.getRow() - other.getRow()) <= 1 && Math.abs(this.getColumn() - other.getColumn()) <= 1);
    }

    /**
     * Marca esta posição como ocupada por um navio.
     */
    @Override
    public void occupy() {
        isOccupied = true;
    }

    /**
     * Regista um tiro nesta posição, marcando-a como atingida.
     */
    @Override
    public void shoot() {
        isHit = true;
    }

    /**
     * Indica se esta posição está ocupada por um navio.
     *
     * @return {@code true} se estiver ocupada; {@code false} caso contrário
     */
    @Override
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * Indica se esta posição já foi atingida por um tiro.
     *
     * @return {@code true} se já foi atingida; {@code false} caso contrário
     */
    @Override
    public boolean isHit() {
        return isHit;
    }

    /**
     * Devolve uma representação textual da posição, no formato
     * {@code "Linha = X Coluna = Y"}.
     *
     * @return a descrição textual da posição
     */
    @Override
    public String toString() {
        return ("Linha = " + row + " Coluna = " + column);
    }
}
