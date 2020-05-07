package bbsemaphore;

import java.util.Random;
import java.util.Timer;


/*****************************
 * Main Class for Command Line
 *****************************/

public class BurritoBros {

    final static String[] SERVERS = {"Langdon", "Nostradamus", "Deadpool"}; //meant to be the names of servers in BB


    public static void main(String[] args) {

        Restaurant restaurant = new Restaurant();

        for(int i=0; i < 3; i++){
            Server server = new Server(SERVERS[i], restaurant);
            server.start();
        }

        Timer timer = new Timer();

        int delay = (2 + new Random().nextInt(5)) * 1000;

        timer.schedule(new createCustomer(restaurant), delay, 1);

    }
}
