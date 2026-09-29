/**
* Representa a frota de navios de um jogador no jogo da Batalha Naval.
*
* A frota mantém uma lista de navios e permite adicionar, consultar
* e obter informação sobre os navios que a constituem.  
*/
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

public class Fleet implements IFleet {
    /**
     * Imprime todos os navios existentes na lista indicada. 
     *
     * @param ships lista de navios a imprimir
     */
    static void printShips(List<IShip> ships) {
        for (IShip ship : ships)
            System.out.println(ship);
    }    
    
    private List<IShip> ships;/** Lista de navios pertencentes à frota. */

    public Fleet() {/** Cria uma frota vazia. */
        ships = new ArrayList<>();
    }

    @Override
    public List<IShip> getShips() {/** Devolve a lista de navios pertencentes à frota.  @return lista de navios da frota */
        return ships;
    }

    /**Adiciona um navio à frota caso este possa ser colocado no tabuleiro não entre em colisão ou fique demasiado próximo de outro navio e a capacidade da frota não seja excedida.
    *
    * @param s navio a adicionar à frota
    * @return {@code true} se o navio foi adicionado; {@code false} caso
    * contrário 
    */
    @Override
    public boolean addShip(IShip s) {
        boolean result = false;
        if ((ships.size() <= FLEET_SIZE) && (isInsideBoard(s)) && (!colisionRisk(s))) {
            ships.add(s);
            result = true;
        }
        return result;
    }

    /**
    * Devolve os navios pertencentes a uma determinada categoria.
    *
    * @param category categoria dos navios a procurar
    * @return lista dos navios pertencentes à categoria indicada
    */
    @Override
    public List<IShip> getShipsLike(String category) {
        List<IShip> shipsLike = new ArrayList<>();
        for (IShip s : ships)
            if (s.getCategory().equals(category))
                shipsLike.add(s);

        return shipsLike;
    }

   /**
   * Devolve os navios da frota que ainda estão a flutuar.
   *
   * @return lista dos navios que ainda não foram afundados
   */
    @Override
    public List<IShip> getFloatingShips() {
        List<IShip> floatingShips = new ArrayList<>();
        for (IShip s : ships)
            if (s.stillFloating())
                floatingShips.add(s);

        return floatingShips;
    }

    /**
    * Procura o navio que ocupa uma determinada posição do tabuleiro.
    *
    * @param pos posição do tabuleiro a consultar
    * @return o navio que ocupa a posição indicada, ou {@code null} caso nenhum navio a ocupe
    */
    @Override
    public IShip shipAt(IPosition pos) {
        for (int i = 0; i < ships.size(); i++)
            if (ships.get(i).occupies(pos))
                return ships.get(i);
        return null;
    }

    /**
    * Verifica se um navio se encontra completamente dentro dos limites * do tabuleiro.
    *
    * @param s navio a verificar
    * @return {@code true} se o navio estiver dentro do tabuleiro; {@code false} caso contrário
    */
    private boolean isInsideBoard(IShip s) {
        return (s.getLeftMostPos() >= 0 && s.getRightMostPos() <= BOARD_SIZE - 1 && s.getTopMostPos() >= 0
                && s.getBottomMostPos() <= BOARD_SIZE - 1);
    }

    /**
    * Verifica se a colocação de um navio cria um risco de colisão ou proximidade excessiva com algum dos navios existentes na frota.
    *
    * @param s navio a verificar 
    * @return {@code true} se existir risco de colisão ou proximidade; {@code false} caso contrário
    */
    private boolean colisionRisk(IShip s) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).tooCloseTo(s))
                return true;
        }
        return false;
    }


    /**
    * Apresenta o estado atual da frota, mostrando todos os navios, os navios que ainda estão a flutuar e os navios agrupados por categoria.
    */
    public void printStatus() {
        printAllShips();
        printFloatingShips();
        printShipsByCategory("Galeao");
        printShipsByCategory("Fragata");
        printShipsByCategory("Nau");
        printShipsByCategory("Caravela");
        printShipsByCategory("Barca");
    }

    /**
    * Imprime todos os navios da frota pertencentes a uma determinada categoria. 
    * 
    * @param category categoria dos navios a imprimir
    */
    public void printShipsByCategory(String category) {
        assert category != null;

        printShips(getShipsLike(category));
    }

    /**
    * Imprime todos os navios da frota que ainda estão a flutuar.
    */
    public void printFloatingShips() {
        printShips(getFloatingShips());
    }

    /**
    * Imprime todos os navios pertencentes à frota.
    */
    void printAllShips() {
        printShips(ships);
    }

}
