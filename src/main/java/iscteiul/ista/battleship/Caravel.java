/** 
* Representa uma caravela no jogo da Batalha Naval. 
* A caravela é um navio de tamanho 2, ocupando duas posições consecutivas na grelha, na horizontal ou na vertical, de acordo com a sua orientação. 
*/
package iscteiul.ista.battleship;

public class Caravel extends Ship {
    private static final Integer SIZE = 2; /** Tamanho da caravela. */
    private static final String NAME = "Caravela";/** Nome da Caravela*/

    /**
    * Cria uma nova caravela com a orientação e posição inicial indicadas.  
    * @param bearing orientação da caravela 
    * @param pos posição inicial da caravela 
    * @throws NullPointerException se a orientação for nula 
    * @throws IllegalArgumentException se a orientação for inválida
    */
    public Caravel(Compass bearing, IPosition pos) throws NullPointerException, IllegalArgumentException {
        super(Caravel.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the caravel");

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
                throw new IllegalArgumentException("ERROR! invalid bearing for the caravel");
        }

    }

    /**
    * Devolve o tamanho da caravela. 
    *
    * @return tamanho da caravela, que é 2 
    */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}
