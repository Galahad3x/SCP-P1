public class JugadorsEquip {
    int MAX_PORTERS = 1;
    int MAX_DEFENSES = 3;
    int MAX_MIGCAMPISTES = 2;
    int MAX_DAVANTERS = 1;
    int MAX_JUGADORS = MAX_PORTERS + MAX_DEFENSES + MAX_MIGCAMPISTES + MAX_DAVANTERS;

    int numPorters = 0;
    int numDefenses = 0;
    int numMigcampistes = 0;
    int numDavanters = 0;

    public Jugador[] porters = new Jugador[MAX_PORTERS];
    public Jugador[] defenses = new Jugador[MAX_DEFENSES];
    public Jugador[] migcampistes = new Jugador[MAX_MIGCAMPISTES];
    public Jugador[] davanters = new Jugador[MAX_DAVANTERS];
    public Jugador[] jugadors = new Jugador[MAX_JUGADORS];

    public JugadorsEquip () {}

    // Adds a player
    public void addPlayer(Jugador jugador) {
        switch (jugador.posicio) {
            case Porter ->  {
                for (int i = 0; i < porters.length; i++) {
                    if (porters[i] == null) {
                        porters[i] = jugador;
                        break;
                    }
                }
                numPorters++;
            }
            case Defensa ->  {
                for (int i = 0; i < defenses.length; i++) {
                    if (defenses[i] == null) {
                        defenses[i] = jugador;
                        break;
                    }
                }
                numDefenses++;
            }
            case Migcampista ->  {
                for (int i = 0; i < migcampistes.length; i++) {
                    if (migcampistes[i] == null) {
                        migcampistes[i] = jugador;
                        break;
                    }
                }
                numMigcampistes++;
            }
            case Davanter ->  {
                for (int i = 0; i < davanters.length; i++) {
                    if (davanters[i] == null) {
                        davanters[i] = jugador;
                        break;
                    }
                }
                numDavanters++;
            }
        }
    }

    // Copies all the diferent player's array into an array together
    public void getJugadors() {
        if (MAX_PORTERS >= 0) System.arraycopy(porters, 0, jugadors, 0, MAX_PORTERS);
        if (MAX_DEFENSES >= 0) System.arraycopy(defenses, 0, jugadors, 0, MAX_DEFENSES);
        if (MAX_MIGCAMPISTES >= 0) System.arraycopy(migcampistes, 0, jugadors, 0, MAX_MIGCAMPISTES);
        if (MAX_DAVANTERS >= 0) System.arraycopy(davanters, 0, jugadors, 0, MAX_DAVANTERS);
    }

    // Gets a player
    public Jugador getPlayer(int index) {
        getJugadors();
        return jugadors[index];
    }
}