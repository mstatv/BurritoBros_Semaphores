package bbsemaphore;

import java.util.Random;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class createCustomer extends TimerTask {

    private int id = 0;

    private Restaurant restaurant;

    createCustomer(Restaurant restaurant) {

        this.restaurant = restaurant;

    }

    @Override
    public void run() {

        if (this.id < 99) {

            Random random = new Random();

            int OC = random.nextInt(((20 - 1) + 1) + 1);

            Customer customer = new Customer(this.id, OC, this.restaurant);

            customer.start();

            try {

                customer.join();

            } catch (InterruptedException iEx) {

                Logger.getLogger(BurritoBros.class.getName()).log(Level.SEVERE, null, iEx);

            }

            this.id++;

        }

    }

}
