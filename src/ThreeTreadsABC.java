public class ThreeTreadsABC {
    private volatile char currentChar = 'A';

    public static void main(String[] args) {
        ThreeTreadsABC test = new ThreeTreadsABC();
        new Thread(test::printA).start();
        new Thread(test::printB).start();
        new Thread(test::printC).start();

    }
    public void printA(){
        synchronized (this){
            for (int i = 0; i <5 ; i++) {
                if(currentChar != 'A') {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("A");
                currentChar = 'B';
                notify();
            }
        }
    }

    public void printB(){
        synchronized (this){
            for (int i = 0; i <5 ; i++) {
                if(currentChar != 'B') {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("B");
                currentChar = 'C';
                notify();
            }
        }
    }

    public void printC(){
        synchronized (this){
            for (int i = 0; i <5 ; i++) {
                if(currentChar != 'C') {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("C");
                currentChar = 'A';
                notify();
            }
        }
    }


}