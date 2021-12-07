public class Algorithms {


    int total_tat;
    int total_wat;
    float avg_tat;
    float avg_wat;
    
    //int array to check if process is completed
    int[] isCompleted = new int[9];


    public void SRTF(Process[] p) {
        int n = p.length;

        //int array to check remaining BT of each process
        int[] remaining_bt = new int[n];
        //transfer to remaining BT
        for (int i = 0 ; i < n; i++) {
            remaining_bt[i] = p[i].burst_time;
        }
        
        
        int completed = 0;
        int curr_time = 0;
        int prev = 0;
        
        while (completed != n) {
            //variable to verify process validity to start
            int valid_process = -1;
            //variable to check lowest BT
            int lowest_bt = 100000;
            for (int i = 0; i < n ; i++) {
                //check if process is in ready queue, and is not completed
                if(p[i].arr_time <= curr_time && isCompleted[i] == 0) {
                    //check if process has the lowest burst time
                    if(remaining_bt[i] < lowest_bt) {
                        lowest_bt = remaining_bt[i];
                        valid_process = i;
                    }
                }
            }
            //determine if process is valid
            if (valid_process != -1) {
                if (remaining_bt[valid_process] == p[valid_process].burst_time) {
                    p[valid_process].start_time = curr_time;

                }
                remaining_bt[valid_process] -= 1;
                curr_time += 1;
                prev = curr_time;

                if (remaining_bt[valid_process] == 0) {
                    p[valid_process].completion_time = curr_time;
                    p[valid_process].turnaround_time = p[valid_process].completion_time - p[valid_process].arr_time;
                    p[valid_process].waiting_time = p[valid_process].turnaround_time -  p[valid_process].burst_time;
                    
                    total_tat += p[valid_process].turnaround_time;
                    total_wat += p[valid_process].waiting_time;

                    isCompleted[valid_process] = 1;
                    completed++;
                }
            }
            else {curr_time += 1;}
        }

        avg_tat = (float) total_tat / n;
        avg_wat = (float) total_wat / n;

        System.out.println("Average Turnaround Time: " + avg_tat);
        System.out.println("Average Waiting Time: " + avg_wat);
    }
    
    public void PreemptivePRIO(Process[] p) {
        int n = p.length;

        int completed = 0; 
        int curr_time = 0;
        int prev = 0;
        
        while (completed != n) {
             
            int idx = -1;
            int mx = -1;
            
            for (int i = 0; i < n; i++) {
                if (p[i].arr_time <= curr_time && isCompleted[i] == 0) {
                    if (p[i].prio > mx) {
                        mx = p[i].prio;
                        idx = i;
                    }
                    if (p[i].prio == mx) {
                        if (p[i].arr_time < p[idx].arr_time) {
                            mx = p[i].prio;
                            idx = i;
                        }
                    }
                }
            }
            //determine if process is valid
            if (idx != -1) {
                
            }
        }
    }
}
