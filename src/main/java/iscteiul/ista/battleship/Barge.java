/**
 * Representa uma barca no jogo da Batalha Naval. 
 * Uma barca é um navio de tamanho 1, ocupando apenas uma posição * na grelha de jogo. 
 */
package iscteiul.ista.battleship;

public class Barge extends Ship {
    
    private static final Integer SIZE = 1;/** Tamanho da barca */
    private static final String NAME = "Barca"; /** Nome da Barca*/

    /**
     * Cria uma nova barca com a orientação e posição inicial indicadas.
     * @param bearing - barge bearing
     * @param pos     - upper left position of the barge
     */
    public Barge(Compass bearing, IPosition pos) {
        super(Barge.NAME, bearing, pos);
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }

    /** * Devolve o tamanho da barca. 
    *
    * @return tamanho da barca, que é 1 
    */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}
