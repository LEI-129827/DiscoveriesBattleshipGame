package iscteiul.ista.battleship;

/**
 * Represents a Galleon (Galeão) ship in the Battleship game.
 * <p>
 * A Galleon occupies a fixed size of 5 grid positions arranged in a specific T-like layout
 * depending on its cardinal orientation (bearing).
 * </p>
 */
public class Galleon extends Ship {
    
    /**
     * The fixed size (total grid positions occupied) of a Galleon ship.
     */
    private static final Integer SIZE = 5;

    /**
     * The default localized display name of the Galleon ship type.
     */
    private static final String NAME = "Galeao";

    /**
     * Constructs a new {@code Galleon} ship with a specified orientation and reference position.
     * Populates the grid positions occupied by this ship according to its bearing.
     *
     * @param bearing the cardinal direction (NORTH, EAST, SOUTH, WEST) the Galleon is facing
     * @param pos the top-left origin/reference position of the ship on the board
     * @throws NullPointerException if the specified {@code bearing} is {@code null}
     * @throws IllegalArgumentException if the provided {@code bearing} is invalid or unsupported
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
     * Returns the size of the Galleon ship.
     *
     * @return the number of grid positions occupied by the Galleon (always 5)
     * @see battleship.Ship#getSize()
     */
    @Override
    public Integer getSize() {
        return Galleon.SIZE;
    }

    /**
     * Calculates and adds the 5 grid positions occupied by the Galleon when facing North.
     *
     * @param pos the reference starting position
     */
    private void fillNorth(IPosition pos) {
        for (int i = 0; i < 3; i++) {
            getPositions().add(new Position(pos.getRow(), pos.getColumn() + i));
        }
        getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + 1));
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + 1));
    }

    /**
     * Calculates and adds the 5 grid positions occupied by the Galleon when facing South.
     *
     * @param pos the reference starting position
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
     * Calculates and adds the 5 grid positions occupied by the Galleon when facing East.
     *
     * @param pos the reference starting position
     */
    private void fillEast(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 3));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

    /**
     * Calculates and adds the 5 grid positions occupied by the Galleon when facing West.
     *
     * @param pos the reference starting position
     */
    private void fillWest(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 1));
        } 
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

}
