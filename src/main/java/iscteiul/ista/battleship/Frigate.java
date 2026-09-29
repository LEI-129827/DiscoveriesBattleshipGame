package iscteiul.ista.battleship;

/**
 * Representa uma Fragata ("Frigate") no jogo da Batalha Naval[cite: 1].
 * Uma fragata ocupa 4 posições consecutivas na grelha de jogo[cite: 1].
 *
 * @author Nome do Aluno
 * @version 1.0
 */
public class Frigate extends Ship {

    /** Tamanho da fragata (4 unidades)[cite: 1]. */
    private static final Integer SIZE = 4;

    /** Nome predefinido do navio em português[cite: 1]. */
    private static final String NAME = "Fragata";

    /**
     * Constrói uma nova Fragata com a orientação e posição inicial especificadas[cite: 1].
     * Calcula e adiciona todas as posições ocupadas pelo navio com base na sua orientação.
     *
     * @param bearing orientação cardeal do navio (NORTH, SOUTH, EAST, WEST)[cite: 1]
     * @param pos     posição inicial (canto superior esquerdo) do navio na grelha[cite: 1]
     * @throws IllegalArgumentException se a orientação fornecida for inválida
     */
    public Frigate(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Frigate.NAME, bearing, pos);
        switch (bearing) {
            case NORTH:
            case SOUTH:
                for (int r = 0; r < SIZE; r++)
                    getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
                break;
            case EAST:
            case WEST:
                for (int c = 0; c < SIZE; c++)
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() + c));
                break;
            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for thr frigate");
        }
    }

    /**
     * Obtém o tamanho da fragata.
     *
     * @return o tamanho do navio como Integer (4)[cite: 1]
     */
    @Override
    public Integer getSize() {
        return Frigate.SIZE;
    }

} 
