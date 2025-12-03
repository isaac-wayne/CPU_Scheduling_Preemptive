# CPU_Scheduling_Preemptive

https://teams.microsoft.com/webhook/12345678-aaaa-bbbb-cccc-1234567890ab@12345678-aaaa-bbbb-cccc-1234567890ab/IncomingWebhook/abcdef1234567890abcdef1234567890/12345678-aaaa-bbbb-cccc-1234567890ab

USAGE: Run "runner.java" (Java)

INTRODUCTION

The group was assigned to create a program that involves Preemptive Scheduling with the following algorithms: Shortest Remaining Time First (SRTF), Round Robin (RR), Round Robin with Overhead (RRO), and Preemptive Priority (P-Prio). Preemptive scheduling is a CPU scheduling technique that allocates to a process for a limited time. These processes can be interrupted and this technique is considered to be flexible. For this reason, the cpu utilization is higher or better than non-preemptive scheduling.

Shortest Remaining Time First (SRTF)

In this algorithm, the process which has the least burst time gets the CPU first. A process that is running on the CPU can be removed if a new process arrives in the ready queue with lower burst time than the current process. This algorithm is also called the preemptive version of the Shortest Job First (SJF).

Round Robin (RR)

Round Robin is always preemptive in nature. There is a time quantum that is given to each process and these processes are sent to the ready queue if they have burst time remaining. The time interrupts every quantum to schedule the next process and it is done in a First In First Out (FIFO) order.

Round Robin with Overhead (RRO)

For this algorithm, there is not much of a difference with the Round Robin algorithm. There will only be a delay for every process allotted based on the time quantum and burst time result. As such, there will be an overhead time that the algorithm will follow, and this will cause a delay in between each process.

Preemptive Priority (P-Prio)

In preemptive priority scheduling, there is a priority provided wherein the process with the highest priority acquires the CPU first. However, it may vary depending on the systems. In our case, the higher priority will be first. If there is a tie in terms of priority, FCFS is used to break this tie.

SAMPLE OUTPUT

![image](https://user-images.githubusercontent.com/95679649/204960674-932e1d4f-fed7-4b28-9166-f0acb2879e9e.png)

![image](https://user-images.githubusercontent.com/95679649/204960721-c503ac6f-44ac-473e-a5e9-406dbb972db7.png)

![image](https://user-images.githubusercontent.com/95679649/204960757-fb499c52-ca6b-48eb-8627-286120281a91.png)

![image](https://user-images.githubusercontent.com/95679649/204960844-310f5d7b-8ef3-4a04-bfab-a884629523f4.png)
 

