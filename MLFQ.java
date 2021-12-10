import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MLFQ {

    private Queue<Integer> q1 = new LinkedList<>();
    private Queue<Integer> q2 = new LinkedList<>();
    private Queue<Integer> q3 = new LinkedList<>();

    private int completed = 0;;
    private int[] isCompleted = new int[9];

    List<Gantt> gantt = new LinkedList<>();

    private int total_tat;
    private int total_wat;
    private float avg_tat;
    private float avg_wat;

    public void Run(Process[] p) {
        int n = p.length;

        //int array to check remaining BT of each process
        int[] remaining_bt = new int[n];
        //transfer to remaining BT
        for (int i = 0 ; i < n; i++) {
            remaining_bt[i] = p[i].getBurst_time();
        }
        
        while (completed != n) {

        }
    }

    private void SRTF() {

    }
}
