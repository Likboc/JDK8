package concurrent.lock;

/**
 * a simple lock demo
 */
public class Lock {
    private int access = 1;

    public boolean getLock() {
        if (access == 1) {
            access = 0;
            return true;
        }else {
            while(true) {
                getLock();
            }
        }
    }

    public void unLock() {
        access = 1;
    }
}
