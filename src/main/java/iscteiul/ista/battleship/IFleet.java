package iscteiul.ista.battleship;

import java.util.List;

/**
 * Contrato que define o comportamento de uma frota no jogo da Batalha Naval
 * (versão dos Descobrimentos).
 * <p>
 * Uma frota agrupa os navios ({@link IShip}) de um jogador e permite adicionar
 * novos navios, consultar o seu estado e localizar navios no tabuleiro.
 * </p>
 *
 * @author NOME APELIDO (LEI-XXXXX)
 * @version 1.0
 * @see IShip
 * @see IPosition
 */
public interface IFleet {

    /**
     * Dimensão (número de linhas e de colunas) do tabuleiro quadrado de jogo.
     */
    Integer BOARD_SIZE = 10;

    /**
     * Número total de navios que compõem uma frota completa.
     */
    Integer FLEET_SIZE = 10;

    /**
     * Devolve todos os navios que pertencem à frota.
     *
     * @return lista com todos os navios da frota (afundados ou não)
     */
    List<IShip> getShips();

    /**
     * Tenta adicionar um navio à frota.
     * <p>
     * A adição pode falhar, por exemplo, se o navio ficar fora dos limites do
     * tabuleiro, se colidir ou tocar noutro navio já existente, ou se a frota
     * já estiver completa.
     * </p>
     *
     * @param s o navio a adicionar
     * @return {@code true} se o navio foi adicionado com sucesso;
     *         {@code false} caso contrário
     */
    boolean addShip(IShip s);

    /**
     * Devolve os navios da frota que pertencem a uma determinada categoria.
     *
     * @param category nome da categoria pretendida (por exemplo, "Galeao",
     *                 "Fragata", "Nau", "Caravela" ou "Barca")
     * @return lista de navios dessa categoria; lista vazia se não existir nenhum
     */
    List<IShip> getShipsLike(String category);

    /**
     * Devolve os navios da frota que ainda não foram afundados.
     *
     * @return lista de navios ainda a flutuar; lista vazia se todos estiverem
     *         afundados
     */
    List<IShip> getFloatingShips();

    /**
     * Procura o navio que ocupa uma determinada posição do tabuleiro.
     *
     * @param pos a posição (linha, coluna) a consultar
     * @return o navio que ocupa essa posição, ou {@code null} se a posição
     *         estiver livre
     */
    IShip shipAt(IPosition pos);

    /**
     * Imprime na consola o estado atual da frota, incluindo os navios
     * existentes e a situação de cada um.
     */
    void printStatus();
}
