import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class JobScheduling {

    // Class to represent a job with its deadline and processing time
    static class Job {
        int deadline;         // The deadline by which the job needs to be completed
        int processingTime;   // The time it takes to process or complete the job
        int jobId;            // Unique identifier for the job

        public Job(int deadline, int processingTime, int jobId) {
            this.deadline = deadline;
            this.processingTime = processingTime;
            this.jobId = jobId;
        }
    }

    // Function to schedule jobs to maximize completed tasks before deadlines
    public static void scheduleJobs(Job[] jobs) {
        // Sort jobs by deadlines in descending order
        Arrays.sort(jobs, Comparator.comparingInt((Job j) -> j.deadline).reversed());

        // Array to store the result schedule
        int[] result = new int[jobs.length];
        // Array to track used time slots
        boolean[] slot = new boolean[jobs.length];

        // Iterate through each job and schedule it
        for (int i = 0; i < jobs.length; i++) {
            // Find a suitable time slot for the current job
            for (int j = Math.min(jobs[i].deadline - 1, jobs.length - 1); j >= 0; j--) {
                if (!slot[j]) {
                    result[j] = jobs[i].jobId;
                    slot[j] = true;
                    break;
                }
            }
        }

        // Print the schedule
        System.out.println("Schedule to maximize completed tasks before deadlines:");
        for (int i = 0; i < jobs.length; i++) {
            if (slot[i]) {
                System.out.println("Job " + result[i] + " at time slot " + (i + 1));
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of jobs
        System.out.print("Enter the number of jobs: ");
        int n = scanner.nextInt();

        // Input details for each job
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter deadline and processing time for Job " + (i + 1) + ": ");
            int deadline = scanner.nextInt();
            int processingTime = scanner.nextInt();
            jobs[i] = new Job(deadline, processingTime, i + 1);
        }

        // Call the function to schedule jobs
        scheduleJobs(jobs);

        
    }
}
