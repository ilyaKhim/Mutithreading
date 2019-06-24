public class ThreeTreadsABC {

    public static void main(String[] args) {
        ThreeTreadsABC test = new ThreeTreadsABC();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.printA();
            }
        });
        t1.start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                test.printB();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.printC();
            }
        }).start();



    }


    public volatile char currentLetter = 'A';

    public synchronized void  printA(){

            try {
                for (int i = 0; i<5; i++){
                    while(currentLetter != 'A') wait();
                    System.out.println("A");
                    currentLetter = 'B';
                    notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }

    public synchronized void printB(){
        try {
            for (int i = 0; i<5; i++){
                while (currentLetter != 'B') wait();
                System.out.println("B");
                currentLetter = 'C';
                notify();
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public synchronized void printC(){
        try{
            for(int i = 0; i< 5; i++){
                while (currentLetter != 'C') wait();
                System.out.println('C');
                currentLetter = 'A';
                notifyAll();
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}