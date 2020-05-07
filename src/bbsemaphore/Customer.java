package bbsemaphore;

/**
 * Customer class threads
 * will use main classes described
 * in BurritoBros.java
 */
public class Customer extends Thread {

    private Thread thread;

    public int custID;

    public int odrCount;

    private Restaurant restaurant;

    Customer(int custID, int odrCount, Restaurant restaurant) {

        this.custID = custID;

        this.odrCount = odrCount;

        this.restaurant = restaurant;

    }

    public void ingressRest() {

        this.restaurant.ingress(this);

    }

    public void egressRest() {

        this.restaurant.egress();

    }

    public void takeOrder() {

        this.odrCount -= 1;

    }

    public boolean orderISCompleted() {

        return !(this.odrCount > 0);

    }

    public void pay() {

        System.out.println("Please pay for food...");

    }

    public void toCounter() {

        this.restaurant.toCounter();

    }

    public void leaveCounter() {

        this.restaurant.leaveCounter();

    }

    public void custRdy() {

        this.restaurant.custRdy();

    }

    public void custNeedServ() {

        this.restaurant.custNeedServ();

    }

    public String custID() {

        return Integer.toString(this.custID);

    }

    public String odrCount() {

        return Integer.toString(this.odrCount);

    }

    @Override
    public void run() {

        System.out.println("Customer #" + custID() + " has arrived, and is hungry...");

        this.restaurant.waitAreaLock();

        if (this.restaurant.hasVacancy()) {

            System.out.println("Customer #" + custID() + " would like to order " + odrCount() + " burritos!");

            ingressRest();

            System.out.println("Customer #" + custID() + " is waiting...");

            this.restaurant.waitAreaUL();

            while (true) {

                custRdy();

                custNeedServ();

                toCounter();

                if (orderISCompleted()) {

                    break;

                } else {

                    this.restaurant.waitAreaIngress(this);

                    System.out.println("Customer #" + custID() + " walks somberly back to the waiting area.");

                }

            }

            System.out.println("Customer #" + custID() + "'s order has been completed!!");

            this.restaurant.cashDrLock();

            System.out.println("Payment Required from Customer #" + custID());

            pay();

            this.restaurant.cashDrUL();

            System.out.println("Thank you for Stopping by \'Burrito Bros \' Customer #" + custID() + ". Please Come Again Soon!");

            egressRest();

        } else {

            this.restaurant.waitAreaUL();

            System.out.println("Sorry, we currently do not have availability to serve you at the moment... Please try again in a short while, Customer #" + custID());

        }

    }

    @Override
    public void start() {

        if (this.thread == null) {

            this.thread = new Thread(this, custID());

            this.thread.start();

        }

    }


}