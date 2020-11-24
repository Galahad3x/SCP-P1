public class Equip {
    public int id;
    public int valor;
    public int cost;
    public int pressupost;
    public JugadorsEquip jugadorsEquip;

    public Equip (int id, int valor, int cost, int pressupost, JugadorsEquip jugadorsEquip) {
        this.id = id;
        this.valor = valor;
        this.cost = cost;
        this.pressupost = pressupost;
        this.jugadorsEquip = jugadorsEquip;
    }

    // Checks if a player can fit in a team
    public boolean playerFits(Jugador jugador) {
        if (jugador.preu > pressupost) {
            return false;
        } else {
            switch (jugador.posicio) {
                case Porter -> {
                    return jugadorsEquip.numPorters < jugadorsEquip.MAX_PORTERS;
                }
                case Defensa -> {
                    return jugadorsEquip.numDefenses < jugadorsEquip.MAX_DEFENSES;
                }
                case Migcampista -> {
                    return jugadorsEquip.numMigcampistes < jugadorsEquip.MAX_MIGCAMPISTES;
                }
                case Davanter -> {
                    return jugadorsEquip.numDavanters < jugadorsEquip.MAX_DAVANTERS;
                }
                default -> {
                    return false;
                }
            }
        }
    }

    // Checks if there's a repeated player in a team
    public boolean isRepeated(Jugador jugador) {
        if (jugador != null) {
            for (int i = 0; i < jugadorsEquip.MAX_JUGADORS; i++) {
                if (jugador == jugadorsEquip.getPlayer(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Prints the players of the team
    public void printTeam() {
        System.out.println("ID de l'equip: " + id);
        System.out.println("Valor de l'equip: " + valor);
        System.out.println("Cost de l'equip: " + cost);
        System.out.println("Jugadors de l'equip òpim: ");
        System.out.println("Porters: ");
        for (Jugador porter : jugadorsEquip.porters) {
                System.out.println("   - " + porter.nom);
        }
        System.out.println("Defenses: ");
        for (Jugador defensa : jugadorsEquip.defenses) {
                System.out.println("   - " + defensa.nom);
        }
        System.out.println("Migcampistes: ");
        for (Jugador migcampista : jugadorsEquip.migcampistes) {
                System.out.println("   - " + migcampista.nom);
        }
        System.out.println("Davanters: ");
        for (Jugador davanter : jugadorsEquip.davanters) {
                System.out.println("   - " + davanter.nom);
        }
    }
}