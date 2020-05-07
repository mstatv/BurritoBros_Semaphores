package bbsemaphore;

/**
 * Server class of threads
 */
public class Server extends Thread {

    private Thread thread;

    public String n;

    public Restaurant restaurant;

    Server(String n, Restaurant restaurant) {

        this.n = n;

        this.restaurant = restaurant;

    }

    public void makeBurrito() {

    }

    public String name() {

        return this.n;

    }

    public void busy() {

        this.restaurant.busyServ();

    }

    public void free() {

        this.restaurant.freeServ();

    }

    @Override
    public void run() {

        while(true) {

            busy();

            Customer customer = this.restaurant.nextCust();

            System.out.println("Server: " + name() + " is waiting on Customer " + customer.custID);

            free();

            for (int i = 0; i < 3; i++) {

                customer.takeOrder();

                makeBurrito();

                if (customer.orderISCompleted()) {

                    break;

                }

            }

            System.out.println("Customer #" + customer.custID() + " leaves counter..." + customer.odrCount());

            customer.leaveCounter();

        }

    }

    @Override
    public void start() {

        System.out.println(this.n + " has arrived and is ready to work.");

        if (this.thread == null) {

            this.thread = new Thread(this, this.n);

            this.thread.start();

        }

    }

}
