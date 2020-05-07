package bbsemaphore;


import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Restaurant {

    // declare variables
    public final int MAX_CAPACITY = 15;

    private int vacancy = MAX_CAPACITY;

    // declare Semaphore variables
    private Semaphore cust;

    private Semaphore serv;

    private Semaphore cashDr;

    private Semaphore waitAreaAcc;

    private Semaphore custQueueAcc;

    private Semaphore atCounter;

    private PriorityQueue<Customer> custQueue;

    private final Comparator<Customer> orderComp = (Customer cust1, Customer cust2) -> (cust1.odrCount - cust2.odrCount);

    // class constructor
    Restaurant() {

        this.cust = new Semaphore(0);

        this.serv = new Semaphore(0);

        this.cashDr = new Semaphore(1, true);

        this.waitAreaAcc = new Semaphore(1, true);

        this.custQueueAcc = new Semaphore(1, true);

        this.atCounter = new Semaphore(0, true);

        this.custQueue = new PriorityQueue<>(MAX_CAPACITY, orderComp);

    }

    // method to have customer come into burrito bros
    public void ingress (Customer customer) {

        this.vacancy -= 1;

        waitAreaIngress(customer);

    }

    // method to have customer leave burrio bros
    public void egress () {

        waitAreaLock();

        this.vacancy += 1;

        waitAreaUL();

    }

    // method to state whether burrito bros has room
    // for new customers
    public boolean hasVacancy() {

        return this.vacancy > 0;

    }

    // waiting area - where customers wait to be serviced
    public void waitAreaIngress(Customer customer) {

        boolean confirmed = this.custQueue.add(customer);

    }

    // method to get next customer
    public Customer nextCust() {

        accLockCust();

        Customer customer = this.custQueue.poll();

        accULCust();

        return customer;

    }

    // places lock on waiting area
    // semaphore acquired
    public void waitAreaLock() {

        try {

            this.waitAreaAcc.acquire();

        } catch (InterruptedException iEx) {

            Logger.getLogger(BurritoBros.class.getName()).log(Level.SEVERE, null, iEx);

        }

    }

    // removes lock from waiting area
    // semaphore released
    public void waitAreaUL() {

        this.waitAreaAcc.release();

    }

    // places lock on cash drawer
    // semaphore acquired
    public void cashDrLock() {

        try {

            this.atCounter.acquire();

        } catch (InterruptedException iEx) {

            Logger.getLogger(BurritoBros.class.getName()).log(Level.SEVERE, null, iEx);

        }

    }

    // removes lock from cash drawer
    // semaphore released
    public void cashDrUL() {

        this.cashDr.release();

    }

    // method to move customer to counter
    // acquire semaphore
    public void toCounter() {

        try {

            this.atCounter.acquire();

        } catch (InterruptedException iEx) {

            Logger.getLogger(BurritoBros.class.getName()).log(Level.SEVERE, null, iEx);

        }

    }

    // method to release
    // release semaphore
    public void leaveCounter() {

        this.atCounter.release();

    }

    // method to lock accum
    // semaphore released
    public void accLockCust() {

        try {

            this.custQueueAcc.acquire();

        } catch (InterruptedException iEx) {

            Logger.getLogger(BurritoBros.class.getName()).log(Level.SEVERE, null, iEx);

        }

    }

    // method to unlock accum
    // semaphore released
    public void accULCust() {

        this.custQueueAcc.release();

    }

    // method of lock server
    // semaphore acqired
    public void busyServ() {

        try {

            this.serv.acquire();

        } catch (InterruptedException iEx) {

            Logger.getLogger(BurritoBros.class.getName()).log(Level.SEVERE, null, iEx);

        }

    }

    // method to unlock server
    // semaphore released
    public void freeServ() {

        this.cust.release();

    }

    // method to show an available customer
    // semaphore released
    public void custRdy() {

        this.serv.release();

    }

    // method to get availble customer needing service
    // semaphore acquired
    public void custNeedServ() {

        try {

            this.cust.acquire();

        } catch (InterruptedException iEx) {

            Logger.getLogger(BurritoBros.class.getName()).log(Level.SEVERE, null, iEx);

        }

    }

}
