package week4;
public class DiningPhilosophers {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Fork[] fork = new Fork[n];
        Philosopher[] philosopher = new Philosopher[n];

        for (int i = 0; i < n; i++) {
            fork[i] = new Fork();
        }

        for (int i = 0; i < n; i++) {
            philosopher[i] = new Philosopher("P" + i, fork[i], fork[(i + 1) % n]);
        }

        for (int i = 0; i < n; i++) {
            philosopher[i].start();
        }

        String info;
        for (int i = 0; i < 100; i++) {
            info = "|";
            for (int j = 0; j < n; j++) {
                info += (philosopher[j].getStatus() + "|");
            }
            info += "\t\t|";
            for (int j = 0; j < n; j++) {
                info += (fork[j].getHolderName() + "|");
            }
            System.out.println(info);
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }
        }

        for (int i = 0; i < n; i++) {
            philosopher[i].done();
            System.out.println("P" + i + ": eat " + philosopher[i].getNumEat() + " times");
        }
    }
}
