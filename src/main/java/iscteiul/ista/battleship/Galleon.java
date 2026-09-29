package iscteiul.ista.battleship;

/**
 * Representa um Galeão ("Galleon") no jogo da Batalha Naval[cite: 1].
 * O galeão é um navio de grande dimensão que ocupa 5 posições na grelha de jogo[cite: 1].
 *
 * @author Nome do Aluno
 * @version 1.0
 */
public class Galleon extends Ship {

    /** Tamanho do galeão (5 unidades)[cite: 1]. */
    private static final Integer SIZE = 5;

    /** Nome predefinido do navio em português[cite: 1]. */
    private static final String NAME = "Galeao";

    /**
     * Constrói um novo Galeão com a orientação e posição de referência especificadas[cite: 1].
     * Preenche a lista de posições ocupadas pelo navio com base na orientação fornecida.
     *
     * @param bearing orientação cardeal do navio (NORTH, SOUTH, EAST, WEST)[cite: 1]
     * @param pos     posição inicial de referência na grelha[cite: 1]
     * @throws NullPointerException se a orientação (bearing) for nula
     * @throws IllegalArgumentException se a orientação fornecida for inválida
     */
    public Galleon(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Galleon.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the galleon");

        switch (bearing) {
            case NORTH:
                fillNorth(pos);
                break;
            case EAST:
                fillEast(pos);
                break;
            case SOUTH:
                fillSouth(pos);
                break;
            case WEST:
                fillWest(pos);
                break;

            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the galleon");
        }
    }

    /**
     * Obtém o tamanho do galeão.
     *
     * @return o tamanho do navio como Integer (5)[cite: 1]
     */
    @Override
    public Integer getSize() {
        return Galleon.SIZE;
    }

    /**
     * Preenche a lista de posições do galeão quando orientado a Norte.
     *
     * @param pos posição inicial de referência
     */
    private void fillNorth(IPosition pos) {
        for (int i = 0; i < 3; i++) {
            getPositions().add(new Position(pos.getRow(), pos.getColumn() + i));
        }
        getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + 1));
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + 1));
    }

    /**
     * Preenche a lista de posições do galeão quando orientado a Sul.
     *
     * @param pos posição inicial de referência
     */
    private void fillSouth(IPosition pos) {
        for (int i = 0; i < 2; i++) {
            getPositions().add(new Position(pos.getRow() + i, pos.getColumn()));
        }
        for (int j = 2; j < 5; j++) {
            getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + j - 3));
        }
    }

    /**
     * Preenche a lista de posições do galeão quando orientado a Este.
     *
     * @param pos posição inicial de referência
     */
    private void fillEast(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 3));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

    /**
     * Preenche a lista de posições do galeão quando orientado a Oeste.
     *
     * @param pos posição inicial de referência
     */
    private void fillWest(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 1));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

}
