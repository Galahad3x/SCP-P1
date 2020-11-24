import java.io.*;

public class Manfutc {

    public static int id_equips = 0;
    public static int n_threads;

    public static JugadorsEquip no_agafar = new JugadorsEquip();
    public static Equip no_agafar_equip = new Equip(0, 0, 0, 0, no_agafar);
    public static JugadorsEquip agafar = new JugadorsEquip();
    public static Equip agafar_equip = new Equip(0, 0, 0, 0, agafar);

    public static void main(String[] argvs) {
        if (argvs.length != 3) {
            throw new IllegalArgumentException("Error while introduce the arguments: <pressupost>, <nom_mercat>, <n_threads>.");
        }
        int pressupost = Integer.parseInt(argvs[0]);
        n_threads = Integer.parseInt(argvs[2]);

        Mercat mercat = new Mercat();
        JugadorsEquip jugadorsEquip = new JugadorsEquip();
        Equip equip = new Equip(id_equips, 0, 0, pressupost, jugadorsEquip);
        Equip equipOptim;

        //Reads the file (mercatXj.csv)
        try {
            System.out.println("----------\nLlegint el fitxer: " + argvs[1]);
            System.out.println("----------\nLlista de jugadors del mercat:");
            mercat = LlegirFitxerJugadors(argvs[1]);
        } catch (Exception e) {
            System.out.println("ERROR: Error reading the file.");
        }
        // Calculates the best team
        System.out.println("----------\nCalculant l'equip òptim...");
        equipOptim = calcularEquipOptim(mercat, equip, (mercat.NJugadors - 1));
        System.out.println("---------- MILLOR EQUIP OBTINGUT ----------");
        equipOptim.printTeam();
    }

    //Reads the file
    public static Mercat LlegirFitxerJugadors(String fitxer) {
        Mercat mercat = new Mercat();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(fitxer);

        } catch (FileNotFoundException e) {
            System.err.println("ERROR: File not found.");
        }

        try {
            assert fis != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            br.readLine();

            mercat.NJugadors = 0;

            String line;
            while ((line = br.readLine()) != null) {
                String[] field = line.split(";");

                // Creating player
                Jugador jugador = new Jugador();

                // Setting player's id
                jugador.setId(Integer.parseInt(field[0]));

                // Setting player's name
                jugador.setNom(field[1]);

                // Setting player's position
                switch (field[2]) {
                    case "Portero" -> jugador.setPosicio(TJugador.Porter);
                    case "Defensa" -> jugador.setPosicio(TJugador.Defensa);
                    case "Medio" -> jugador.setPosicio(TJugador.Migcampista);
                    case "Delantero" -> jugador.setPosicio(TJugador.Davanter);
                    default -> System.err.println("ERROR: Invalid player type.");
                }
                // Setting player's price
                jugador.setPreu(Integer.parseInt(field[3]));

                // Setting player's team
                jugador.setEquip(field[4]);

                // Setting player's value
                jugador.setValor(Integer.parseInt(field[5]));

                mercat.jugadors[mercat.NJugadors] = jugador;
                mercat.NJugadors++;
                System.out.println(jugador.printPlayer());
            }
            br.close();
        } catch (IOException e) {
            System.err.println("ERROR: Error doing I/O.");
        }
        return mercat;
    }

    // Calculates the best team
    public static Equip calcularEquipOptim(Mercat mercat, Equip equip, int index) {
        if (index == 0) {
            if (equip.playerFits(mercat.getJugador(index)) && equip.isRepeated(mercat.getJugador(index))) {
                equip.jugadorsEquip.addPlayer(mercat.getJugador(index));
                equip.id = id_equips;
                id_equips++;
            }
        } else {
            // In the case we don't pick the player
            no_agafar_equip.id = equip.id;
            no_agafar_equip.valor = equip.valor;
            no_agafar_equip.cost = equip.cost;
            no_agafar_equip.pressupost = equip.pressupost;
            no_agafar_equip.jugadorsEquip = equip.jugadorsEquip;
            calcularEquipOptim(mercat, no_agafar_equip, index - 1);

            // In the case we pick the player
            if (equip.playerFits(mercat.getJugador(index)) && equip.isRepeated(mercat.getJugador(index))) {
                agafar_equip.id = equip.id;
                agafar_equip.valor = equip.valor + (mercat.getJugador(index).valor);
                agafar_equip.cost = equip.cost + (mercat.getJugador(index).preu);
                agafar_equip.pressupost = equip.pressupost - (mercat.getJugador(index).preu);
                agafar_equip.jugadorsEquip = equip.jugadorsEquip;
                agafar_equip.jugadorsEquip.addPlayer(mercat.getJugador(index));
                calcularEquipOptim(mercat, agafar_equip, index - 1);
            }

            // We check which equip fits better
            if (agafar_equip.valor == 0 || no_agafar_equip.valor > agafar_equip.valor) {
                equip.id = no_agafar_equip.id;
                equip.valor = no_agafar_equip.valor;
                equip.cost = no_agafar_equip.cost;
                equip.pressupost = no_agafar_equip.pressupost;
                equip.jugadorsEquip = no_agafar_equip.jugadorsEquip;
            } else {
                equip.id = agafar_equip.id;
                equip.valor = agafar_equip.valor;
                equip.cost = agafar_equip.cost;
                equip.pressupost = agafar_equip.pressupost;
                equip.jugadorsEquip = agafar_equip.jugadorsEquip;
            }
        }
        return equip;
    }

    public static class ManfutcThreads extends Thread{
        public int index;

        public ManfutcThreads(int index) {
            this.index = index;
        }

        @Override
        public void run() {

        }
    }
}