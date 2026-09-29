package iscteiul.ista.battleship;

/**
 * Representa as direções cardeais (pontos de orientação) utilizadas para posicionar os navios no jogo da Batalha Naval[cite: 1].
 *
 * @author fba
 * @version 1.0
 */
public enum Compass {
    /** Direção Norte ('n'). */
    NORTH('n'),

    /** Direção Sul ('s'). */
    SOUTH('s'),

    /** Direção Este ('e'). */
    EAST('e'),

    /** Direção Oeste ('o'). */
    WEST('o'),

    /** Direção desconhecida ou inválida ('u'). */
    UNKNOWN('u');

    /** Carácter que representa a direção. */
    private final char c;

    /**
     * Construtor da enumeração Compass.
     *
     * @param c carácter correspondente à direção cardeal
     */
    Compass(char c) {
        this.c = c;
    }

    /**
     * Obtém o carácter associado à direção cardeal.
     *
     * @return o carácter da direção ('n', 's', 'e', 'o' ou 'u')
     */
    public char getDirection() {
        return c;
    }

    /**
     * Retorna a representação em texto do carácter da direção.
     *
     * @return String contendo o carácter da direção
     */
    @Override
    public String toString() {
        return "" + c;
    }

    /**
     * Converte um carácter para a respetiva constante do tipo {@link Compass}.
     *
     * @param ch o carácter a converter ('n', 's', 'e', 'o')
     * @return o objeto {@link Compass} correspondente ou {@link #UNKNOWN} se o carácter não for reconhecido
     */
    static Compass charToCompass(char ch) {
        Compass bearing;
        switch (ch) {
            case 'n':
                bearing = NORTH;
                break;
            case 's':
                bearing = SOUTH;
                break;
            case 'e':
                bearing = EAST;
                break;
            case 'o':
                bearing = WEST;
                break;
            default:
                bearing = UNKNOWN;
        }

        return bearing;
    }
}
