
public class Process {
    private int pid;
    private int arr_time;
    private int burst_time;
    private int start_time;
    private int prio;
    private int completion_time;
    private int turnaround_time;
    private int waiting_time;

    public Process(int pid, int arr_time, int burst_time) {
        this.pid = pid;
        this.arr_time = arr_time;
        this.burst_time = burst_time;
    }

    public Process(int pid, int arr_time, int burst_time, int prio) {
        this.pid = pid;
        this.arr_time = arr_time;
        this.burst_time = burst_time;
        this.prio = prio;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getArr_time() {
        return arr_time;
    }

    public void setArr_time(int arr_time) {
        this.arr_time = arr_time;
    }

    public int getBurst_time() {
        return burst_time;
    }

    public void setBurst_time(int burst_time) {
        this.burst_time = burst_time;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getPrio() {
        return prio;
    }

    public void setPrio(int prio) {
        this.prio = prio;
    }

    public int getCompletion_time() {
        return completion_time;
    }

    public void setCompletion_time(int completion_time) {
        this.completion_time = completion_time;
    }

    public int getTurnaround_time() {
        return turnaround_time;
    }

    public void setTurnaround_time(int turnaround_time) {
        this.turnaround_time = turnaround_time;
    }

    public int getWaiting_time() {
        return waiting_time;
    }

    public void setWaiting_time(int waiting_time) {
        this.waiting_time = waiting_time;
    }
}