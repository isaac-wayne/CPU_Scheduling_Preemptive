import java.util.Scanner;

public class Runner
{
    
    int no_of_processes;
    
    float avg_tat;
    float avg_wat;
    //int array to check remaining BT of each process
    int[] remaining_bt = new int[9];
    //int array to check if process is completed
    int[] isCompleted = new int[9];
    
    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);
        String choice, choice2;

    do {
        System.out.print("Input no. of processes [2-9]: ");
        int n = Integer.parseInt(sc.next());

        int ArrayAT[], ArrayBT[];
        ArrayAT = new int[n];
        ArrayBT = new int[n];

        Process myProcess[] = new Process[n];

    if (n < 2 || n > 9) {
        System.out.println("Input no. of processes [2-9]: ");
    }
        System.out.println("\nInput individual arrival time: ");
        for (int i = 0;  i < n; i++) {
            System.out.print("AT" + (i+1) + ": ");
            int AT = Integer.parseInt(sc.next());
            ArrayAT[i] = AT;
        }
        
        System.out.println("\nInput individual burst time: ");
        for (int i = 0;  i < n; i++) {
            System.out.print("BT" + (i+1) + ": ");
            int BT = Integer.parseInt(sc.next());
            ArrayBT[i] = BT;
        }

        for (int i = 0; i < n; i++) {
            int getAT = ArrayAT[i];
            int getBT = ArrayBT[i];
            myProcess[i] = new Process (i+1, getAT, getBT);
        }
        
        System.out.println("\nCPU Scheduling Algorithm: ");
        System.out.println("[A] Shortest Remaining Time First (SRTF)");
        System.out.println("[B] Round Robin (RR)");
        System.out.println("[C] Round Robin with Overhead (RRO)");
        System.out.println("[D] Preemptive Priority (P-Prio)");
        System.out.println("[E] Multi-level Feedback Queue (MLFQ)");
        System.out.println("[F] Exit");
        sc.nextLine();
        System.out.print("\nEnter choice: ");
        
        choice = sc.next();
        
        Algorithms alg = new Algorithms();
        switch (choice) {
            case "A":
                alg.SRTF(myProcess);
                break;

            case "B":
                //alg.RR(myProcess);
                break;

            case "C":

                break;

            case "D":

                break;

            case "E":

                break;

            case "F":
                System.out.println("Goodbye!");
                System.exit(0);
                break;

            default:
                System.out.println("Invalid choice!");
                break;
        }

        System.out.print("Input again (y/n)?: ");
        sc.nextLine();
        choice2 = sc.nextLine();
    
    } while (choice2.equals("y"));

        if (choice2.equals("n")) {
            System.out.println("Goodbye!");
        }
    }
}
