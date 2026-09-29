package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe abstrata que representa um navio genérico no jogo da Batalha Naval.
 * Define os atributos e comportamentos comuns a todos os tipos de navios (posição, orientação, 
 * estado de flutuação, colisões e disparos).
 * Implementa a interface {@link IShip}.
 *
 * @author Nome do Aluno
 * @version 1.0
 */
public abstract class Ship implements IShip {

    /** Identificador de categoria para o Galeão. */
    private static final String GALEAO = "galeao";

    /** Identificador de categoria para a Fragata. */
    private static final String FRAGATA = "fragata";

    /** Identificador de categoria para a Nau. */
    private static final String NAU = "nau";

    /** Identificador de categoria para a Caravela. */
    private static final String CARAVELA = "caravela";

    /** Identificador de categoria para a Barca. */
    private static final String BARCA = "barca";

    /**
     * Método estático de fábrica (Factory Method) para instanciar tipos específicos de navios.
     *
     * @param shipKind a categoria do navio em formato de texto (ex: "galeao", "fragata")
     * @param bearing  a orientação cardeal do navio
     * @param pos      a posição inicial do navio na grelha
     * @return uma nova instância da subclasse de {@link Ship} correspondente, ou {@code null} se a categoria for desconhecida
     */
    static Ship buildShip(String shipKind, Compass bearing, Position pos) {
        Ship s;
        switch (shipKind) {
            case BARCA:
                s = new Barge(bearing, pos);
                break;
            case CARAVELA:
                s = new Caravel(bearing, pos);
                break;
            case NAU:
                s = new Carrack(bearing, pos);
                break;
            case FRAGATA:
                s = new Frigate(bearing, pos);
                break;
            case GALEAO:
                s = new Galleon(bearing, pos);
                break;
            default:
                s = null;
        }
        return s;
    }

    /** A categoria/nome do tipo de navio. */
    private String category;

    /** A orientação cardeal do navio na grelha. */
    private Compass bearing;

    /** A posição inicial de referência do navio. */
    private IPosition pos;

    /** Lista de todas as posições individuais ocupadas pelo navio. */
    protected List<IPosition> positions;

    /**
     * Constrói uma nova instância abstrata de um navio.
     *
     * @param category a categoria/nome do navio
     * @param bearing  a orientação cardeal do navio
     * @param pos      a posição inicial de referência
     */
    public Ship(String category, Compass bearing, IPosition pos) {
        assert bearing != null;
        assert pos != null;

        this.category = category;
        this.bearing = bearing;
        this.pos = pos;
        positions = new ArrayList<>();
    }

    /**
     * Obtém a categoria do navio.
     *
     * @return a categoria do navio em formato texto
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Obtém a lista de posições ocupadas pelo navio na grelha.
     *
     * @return lista de objetos {@link IPosition} pertencentes ao navio
     */
    public List<IPosition> getPositions() {
        return positions;
    }

    /**
     * Obtém a posição inicial de referência do navio.
     *
     * @return a posição inicial como {@link IPosition}
     */
    @Override
    public IPosition getPosition() {
        return pos;
    }

    /**
     * Obtém a orientação cardeal do navio.
     *
     * @return a orientação do tipo {@link Compass}
     */
    @Override
    public Compass getBearing() {
        return bearing;
    }

    /**
     * Verifica se o navio ainda se encontra a flutuar.
     * Um navio continua a flutuar se pelo menos uma das suas posições ainda não tiver sido atingida por um disparo.
     *
     * @return {@code true} se pelo menos uma posição não foi atingida, {@code false} caso contrário
     */
    @Override
    public boolean stillFloating() {
        for (int i = 0; i < getSize(); i++)
            if (!getPositions().get(i).isHit())
                return true;
        return false;
    }

    /**
     * Obtém o índice da linha da posição mais a norte (mais acima) ocupada pelo navio.
     *
     * @return o menor valor de linha entre as posições do navio
     */
    @Override
    public int getTopMostPos() {
        int top = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() < top)
                top = getPositions().get(i).getRow();
        return top;
    }

    /**
     * Obtém o índice da linha da posição mais a sul (mais abaixo) ocupada pelo navio.
     *
     * @return o maior valor de linha entre as posições do navio
     */
    @Override
    public int getBottomMostPos() {
        int bottom = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() > bottom)
                bottom = getPositions().get(i).getRow();
        return bottom;
    }

    /**
     * Obtém o índice da coluna da posição mais a oeste (mais à esquerda) ocupada pelo navio.
     *
     * @return o menor valor de coluna entre as posições do navio
     */
    @Override
    public int getLeftMostPos() {
        int left = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() < left)
                left = getPositions().get(i).getColumn();
        return left;
    }

    /**
     * Obtém o índice da coluna da posição mais a este (mais à direita) ocupada pelo navio.
     *
     * @return o maior valor de coluna entre as posições do navio
     */
    @Override
    public int getRightMostPos() {
        int right = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() > right)
                right = getPositions().get(i).getColumn();
        return right;
    }

    /**
     * Verifica se o navio ocupa uma determinada posição na grelha.
     *
     * @param pos a posição a verificar
     * @return {@code true} se o navio contiver essa posição, {@code false} caso contrário
     */
    @Override
    public boolean occupies(IPosition pos) {
        assert pos != null;

        for (int i = 0; i < getSize(); i++)
            if (getPositions().get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Verifica se este navio está demasiado próximo (adjacente) de outro navio.
     *
     * @param other o outro navio a verificar
     * @return {@code true} se alguma posição de outro navio for adjacente a este, {@code false} caso contrário
     */
    @Override
    public boolean tooCloseTo(IShip other) {
        assert other != null;

        Iterator<IPosition> otherPos = other.getPositions().iterator();
        while (otherPos.hasNext())
            if (tooCloseTo(otherPos.next()))
                return true;

        return false;
    }

    /**
     * Verifica se este navio está demasiado próximo (adjacente) de uma determinada posição.
     *
     * @param pos a posição a verificar
     * @return {@code true} se a posição for adjacente a qualquer parte deste navio, {@code false} caso contrário
     */
    @Override
    public boolean tooCloseTo(IPosition pos) {
        for (int i = 0; i < this.getSize(); i++)
            if (getPositions().get(i).isAdjacentTo(pos))
                return true;
        return false;
    }

    /**
     * Processa um disparo contra uma posição específica.
     * Se o navio ocupar essa posição, marca a posição correspondente como atingida.
     *
     * @param pos a posição do disparo na grelha
     */
    @Override
    public void shoot(IPosition pos) {
        assert pos != null;

        for (IPosition position : getPositions()) {
            if (position.equals(pos))
                position.shoot();
        }
    }

    /**
     * Retorna a representação textual do navio no formato "[categoria orientação posição]".
     *
     * @return String com os dados formatados do navio
     */
    @Override
    public String toString() {
        return "[" + category + " " + bearing + " " + pos + "]";
    }

}
