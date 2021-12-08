import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.lang.Math;

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
        
        List<Gantt> gantt = new LinkedList<>();

        
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
                //save time
                Gantt g = new Gantt(curr_time, p[valid_process].getPid());
                gantt.add(g);   
                // if process is handled by CPU for first time, set process start time as current time
                if (remaining_bt[valid_process] == p[valid_process].getBurst_time()) {
                    p[valid_process].setStart_time(curr_time);
                }
                remaining_bt[valid_process] -= 1;
                curr_time += 1;
                prev = curr_time;
                //save time
                Gantt a = new Gantt(curr_time, p[valid_process].getPid());
                gantt.add(a);
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
        
        //remove duplicates in gantt chart
        for (int i = 0; i < gantt.size(); i++) {
            for (int j = i+1 ; j < gantt.size(); j++) {
                if (gantt.get(i).getTime() == gantt.get(j).getTime() && gantt.get(i).getPid() == gantt.get(j).getPid()) {
                    gantt.remove(i);
                }
            }
        }

        //remove in-betweens
        int i;
        int b;
        for ( i=0; i < gantt.size(); i++) {
            b = 0;
            System.out.print("|--P" + gantt.get(i).getPid() + "--(" + gantt.get(i).getTime() + "-");
            for (int j = i+1; j < gantt.size(); j++) {
                if (gantt.get(i).getPid()!=gantt.get(j).getPid()) {
                    System.out.print(gantt.get(j).getTime() + ")--");
                    i = j-1;
                    b = 1;
                    break;
                } 
                if (j==gantt.size()-1 && b!=1) {
                    System.out.print(gantt.get(j).getTime() + ")--");
                    i = j;
                }
                
            }
        }
        System.out.print("|");
        System.out.println("");
        System.out.println("");

        System.out.print("Process" + "\t\t" + "Arrival Time" + "\t" + "Burst Time" + "\t" + "Completion Time" + "\t\t" + "Waiting Time" + "\t" + "Turnaround Time");
        for ( i = 0; i < n; i++) {
            System.out.print("\nP" + (i+1) + "\t\t" + p[i].getArr_time() + "\t\t" + p[i].getBurst_time() + "\t\t" + p[i].getCompletion_time() + "\t\t\t" + p[i].getWaiting_time() + "\t\t" + p[i].getTurnaround_time());
        }

        System.out.println("\nAverage" + "\t\t\t\t\t\t\t\t\t" + avg_wat + "\t\t" + avg_tat);
        System.out.println("");
    }
    
    public void PreemptivePrio(Process[] p) {
        int n = p.length;

        //int array to check remaining BT of each process
        int[] remaining_bt = new int[n];
        //transfer to remaining BT
        for (int i = 0 ; i < n; i++) {
            remaining_bt[i] = p[i].burst_time;
        }
        
        List<Gantt> gantt = new LinkedList<>();
        
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
                    //if prio of process and max is similar
                    if(p[i].getPrio() == max_prio) {
                        //find process with lower arrival time and set as valid process
                        if(p[i].getArr_time() < p[valid_process].getArr_time()) {
                            max_prio = p[i].getPrio();
                            valid_process = i;
                        }
                    }
                }
            }
            //determine if process is valid
            if (valid_process != -1) {
                //save time
                Gantt g = new Gantt(curr_time, p[valid_process].getPid());
                gantt.add(g);
                if (remaining_bt[valid_process] == p[valid_process].getBurst_time()) {
                    p[valid_process].setStart_time(curr_time);

                }
                remaining_bt[valid_process] -= 1;
                curr_time += 1;
                prev = curr_time;
                //save time
                Gantt a = new Gantt(curr_time, p[valid_process].getPid());
                gantt.add(a);
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


        //remove duplicates in gantt chart
        for (int i = 0; i < gantt.size(); i++) {
            for (int j = i+1 ; j < gantt.size(); j++) {
                if (gantt.get(i).getTime() == gantt.get(j).getTime() && gantt.get(i).getPid() == gantt.get(j).getPid()) {
                    gantt.remove(i);
                }
            }
        }

        //remove in-betweens
        int i;
        int b;
        for ( i=0; i < gantt.size(); i++) {
            b = 0;
            System.out.print("|--P" + gantt.get(i).getPid() + "--(" + gantt.get(i).getTime() + "-");
            for (int j = i+1; j < gantt.size(); j++) {
                if (gantt.get(i).getPid()!=gantt.get(j).getPid()) {
                    System.out.print(gantt.get(j).getTime() + ")--");
                    i = j-1;
                    b = 1;
                    break;
                } 
                if (j==gantt.size()-1 && b!=1) {
                    System.out.print(gantt.get(j).getTime() + ")--");
                    i = j;
                }
                
            }
        }
        System.out.print("|");
        System.out.println("");
        System.out.println("");

        System.out.print("Process" + "\t\t" + "Arrival Time" + "\t" + "Burst Time" + "\t" + "Priority" + "\t" + "Completion Time" + "\t\t" + "Waiting Time" + "\t" + "Turnaround Time");
        for ( i = 0; i < n; i++) {
            System.out.print("\nP" + (i+1) + "\t\t" + p[i].getArr_time() + "\t\t" + p[i].getBurst_time() + "\t\t" + p[i].getPrio() + "\t\t" + p[i].getCompletion_time() + "\t\t\t" + p[i].getWaiting_time() + "\t\t" + p[i].getTurnaround_time());
        }

        System.out.println("\nAverage" + "\t\t\t\t\t\t\t\t\t\t\t" + avg_wat + "\t\t" + avg_tat);
        System.out.println("");
    }

    public void RoundRobin(Process[] p, int time_quantum) {
        int n = p.length;
        int completed = 0;
        int curr_time = 0;
        //int array to check remaining BT of each process
        int[] remaining_bt = new int[n];
        //transfer to remaining BT
        for (int i = 0 ; i < n; i++) {
            remaining_bt[i] = p[i].getBurst_time();
        }
        int[] mark = new int[100];
        mark[0] = 1;
        int valid_process;


        Process temp;
        for (int i = 0; i < n; i++) {
            for (int j = i+1 ; j < n; j++) {
                if (p[i].getArr_time() > p[j].getArr_time()) {
                    temp = p[i];
                    p[i] = p[j];
                    p[j] = temp;
                }
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        
        while(completed != n) {
            valid_process = q.remove();

            if (remaining_bt[valid_process] == p[valid_process].getBurst_time()) {
                p[valid_process].setStart_time(Math.max(curr_time, p[valid_process].getArr_time()));
                curr_time = p[valid_process].start_time;
            }

            if (remaining_bt[valid_process] - time_quantum > 0) {
                remaining_bt[valid_process] -= time_quantum;
                curr_time += time_quantum;
            }
            else {
                curr_time += remaining_bt[valid_process];
                remaining_bt[valid_process] = 0;
                completed +=1;

                p[valid_process].setCompletion_time(curr_time);
                p[valid_process].setTurnaround_time(p[valid_process].getCompletion_time() - p[valid_process].getArr_time());
                p[valid_process].setWaiting_time(p[valid_process].getTurnaround_time() -  p[valid_process].getBurst_time());
                    
                total_tat += p[valid_process].getTurnaround_time();
                total_wat += p[valid_process].getWaiting_time();
            }

            for(int i = 1; i < n; i++) {
                if(remaining_bt[i] > 0 && p[i].getArr_time() <= curr_time && mark[i] == 0) {
                    q.add(i);
                    mark[i] = 1;
                }
            }
        }
    }
}
