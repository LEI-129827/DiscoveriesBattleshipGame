/**
* Representa uma nau no jogo da Batalha Naval. 
* 
* A nau é um navio de tamanho 3, ocupando três posições consecutivas na grelha, na horizontal ou na vertical, de acordo com a sua orientação.
*/
package iscteiul.ista.battleship;

public class Carrack extends Ship {
    private static final Integer SIZE = 3; /** Tamanho da nau. */
    private static final String NAME = "Nau"; /** Nome da Nau*/

    /** 
    * Cria uma nova nau com a orientação e posição inicial indicadas. 
    * 
    * @param bearing orientação da nau 
    * @param pos posição inicial da nau 
    * @throws IllegalArgumentException se a orientação for inválida 
    */
    public Carrack(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Carrack.NAME, bearing, pos);
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
                throw new IllegalArgumentException("ERROR! invalid bearing for the carrack");
        }
    }

    /**
    * Devolve o tamanho da nau.
    *
    * @return tamanho da nau, que é 3 
    */
    @Override
    public Integer getSize() {
        return Carrack.SIZE;
    }

}
