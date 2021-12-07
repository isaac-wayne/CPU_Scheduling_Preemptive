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
            remaining_bt[i] = p[i].getBurst_time();
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
                if(p[i].getArr_time() <= curr_time && isCompleted[i] == 0) {
                    // if process has the lowest burst time, set as valid process
                    if(remaining_bt[i] < lowest_bt) {
                        lowest_bt = remaining_bt[i];
                        valid_process = i;
                    }
                }
            }
            //if process is valid, decrement BT by 1 , increment current time by 1
            if (valid_process != -1) {
                // if process is handled by CPU for first time, set process start time as current time
                if (remaining_bt[valid_process] == p[valid_process].getBurst_time()) {
                    p[valid_process].setStart_time(curr_time);

                }
                remaining_bt[valid_process] -= 1;
                curr_time += 1;
                prev = curr_time;
                //if process is done, calculate TAT and WAT
                if (remaining_bt[valid_process] == 0) {
                    p[valid_process].setCompletion_time(curr_time);
                    p[valid_process].setTurnaround_time(p[valid_process].getCompletion_time() - p[valid_process].getArr_time());
                    p[valid_process].setWaiting_time(p[valid_process].getTurnaround_time() -  p[valid_process].getBurst_time());
                    
                    total_tat += p[valid_process].getTurnaround_time();
                    total_wat += p[valid_process].getWaiting_time();

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
    
    public void PreemptivePrio(Process[] p) {
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
            //variable to check maximum priority
            int max_prio = -1;
            for (int i = 0; i < n ; i++) {
                //check if process is in ready queue, and is not completed
                if(p[i].getArr_time() <= curr_time && isCompleted[i] == 0) {
                    //check if process has highest priority
                    if(p[i].getPrio() > max_prio) {
                        max_prio = p[i].getPrio();
                        valid_process = i;
                    }
                    if(p[i].getPrio() == max_prio) {
                        if(p[i].getArr_time() < p[valid_process].getArr_time()) {
                            max_prio = p[i].getPrio();
                            valid_process = i;
                        }
                    }
                }
            }
            //determine if process is valid
            if (valid_process != -1) {
                if (remaining_bt[valid_process] == p[valid_process].getBurst_time()) {
                    p[valid_process].setStart_time(curr_time);

                }
                remaining_bt[valid_process] -= 1;
                curr_time += 1;
                prev = curr_time;

                if (remaining_bt[valid_process] == 0) {
                    p[valid_process].setCompletion_time(curr_time);
                    p[valid_process].setTurnaround_time(p[valid_process].getCompletion_time() - p[valid_process].getArr_time());
                    p[valid_process].setWaiting_time(p[valid_process].getTurnaround_time() -  p[valid_process].getBurst_time());
                    
                    total_tat += p[valid_process].getTurnaround_time();
                    total_wat += p[valid_process].getWaiting_time();

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
}
