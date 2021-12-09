import java.util.Scanner;

public class Runner {

    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);
        char choice, choice2;
        int n;

    do {
        do {
        System.out.print("Input no. of processes [2-9]: ");
        n = sc.nextInt();
            if (n < 2 || n > 9) {
                System.out.println("Invalid number of processes! Try again!");
            }
        } while (n < 2 || n > 9);

        int ArrayAT[], ArrayBT[];
        ArrayAT = new int[n];
        ArrayBT = new int[n];

        Process myProcess[] = new Process[n];

        System.out.println("\nInput individual arrival time: ");
        for (int i = 0;  i < n; i++) {
            System.out.print("AT" + (i+1) + ": ");
            int AT = sc.nextInt();
            ArrayAT[i] = AT;
        }
        
        System.out.println("\nInput individual burst time: ");
        for (int i = 0;  i < n; i++) {
            System.out.print("BT" + (i+1) + ": ");
            int BT = sc.nextInt();
            ArrayBT[i] = BT;
        }

        for (int i = 0; i < n; i++) {
            int getAT = ArrayAT[i];
            int getBT = ArrayBT[i];
            myProcess[i] = new Process (i+1, getAT, getBT);
        }

        System.out.println("\nCPU Scheduling Algor4ithm: ");
        System.out.println("[A] Shortest Remaining Time First (SRTF)");
        System.out.println("[B] Round Robin (RR)");
        System.out.println("[C] Round Robin with Overhead (RRO)");
        System.out.println("[D] Preemptive Priority (P-Prio)");
        System.out.println("[E] Multi-level Feedback Queue (MLFQ)");
        System.out.println("[F] Exit");

        System.out.print("\nEnter choice: ");
        choice = sc.next().charAt(0);
        System.out.println("");

        Algorithms alg = new Algorithms();
        switch (choice) {
            case 'A':
                alg.SRTF(myProcess);
                break;

            case 'B':
                int time_quantum;
                System.out.println("\nInput time slice: ");
                time_quantum = sc.nextInt();
                System.out.println("");

                alg.RoundRobin(myProcess, time_quantum);
                break;

            case 'C':

                break;

            case 'D':
                int[] ArrayPrio = new int[n];
                System.out.println("\nInput individual priority number: ");
                for (int i = 0;  i < n; i++) {
                    System.out.print("P" + (i+1) + ": ");
                   
                    int prio;
                    do {
                    prio = sc.nextInt();
                    if (prio < 0) {
                        System.out.print("Invalid input");
                        System.out.print("\nInput priority number for P" + (i+1) + ": ");
                    }
                    } while (prio < 0);
                    ArrayPrio[i] = prio;
                }

                for (int i = 0; i < n; i++) {
                    int getPrio = ArrayPrio[i];
                    myProcess[i].setPrio(getPrio);;
                }
                System.out.println("");

                alg.PreemptivePrio(myProcess);
                break;

            case 'E':

                break;

            case 'F':
                System.out.println("Goodbye!");
                System.exit(0);
                break;

            default:
                System.out.println("Invalid choice!");
        }

        System.out.print("Input again (y/n)?: ");
        choice2 = sc.next().charAt(0);
    
    } while (choice2 == 'y');

        if (choice2 == 'n') {
            sc.close();
            System.out.println("Goodbye!");
        }
    }

    
}