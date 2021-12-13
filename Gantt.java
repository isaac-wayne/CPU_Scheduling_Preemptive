public class Gantt {
    private int time;
    private int pid;
    
    public Gantt(int time, int pid) {
        this.time = time;
        this.pid = pid;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    
}
