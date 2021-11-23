import java.util.Scanner;

public class Bankers {

    private int need[][], allocation[][], max[][], available[], numberOfProcesses, numberOfResources;

    private void input(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of processes: ");
        numberOfProcesses = scanner.nextInt();
        System.out.println("Enter number of resources: ");
        numberOfResources = scanner.nextInt();

        need = new int[numberOfProcesses][numberOfResources]; //this is how much a process needs
        max = new int[numberOfProcesses][numberOfResources]; //this is the maximum amount they will ever need
        allocation = new int[numberOfProcesses][numberOfResources]; //this is what they are currently allocated
        available = new int[numberOfResources]; //this is the number of resources currently available

        System.out.println("Enter Max Matrix: ");
        for(int i = 0; i < numberOfProcesses; i++){
            for(int j = 0; j < numberOfResources; j++){
                System.out.println("Enter max for process " + i + " and resource " + j);
                max[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter Allocation Matrix: ");
        for(int i = 0; i < numberOfProcesses; i++){
            for(int j = 0; j < numberOfResources; j++){
                System.out.println("Enter allocation for process " + i + " and resource " + j);
                max[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter available resources: ");
        for(int i = 0; i < numberOfResources; i++){
            System.out.println("Enter available for resource " + i);
            available[i] = scanner.nextInt();
        }

        scanner.close();
    }

    private int[][] calculateNeed(){
        for(int i = 0; i < numberOfProcesses; i++){
            for(int j = 0; j < numberOfResources; j++){
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
        return need;
    }

    private boolean check(int i){
        for(int j = 0; j < numberOfResources; j++){
            if (available[j] < need[i][j]){
                return false;
            }
        }
        return true;
    }

    public void isSafe(){
        input();
        calculateNeed();
        boolean finished[] = new boolean[numberOfResources];
        int processesAllocated = 0;

        while(processesAllocated < numberOfProcesses){
            boolean allocated = false;
            for(int i = 0; i < numberOfProcesses; i++){
                if(!finished[i] && check(i)){
                    for(int j = 0; j < numberOfResources; j++){
                        available[j] = available[j] - need[i][j];
                    }
                    System.out.println("Allocated process: " + i);
                    finished[i] = true;
                    allocated = true;
                    processesAllocated++;
                }
            }
            if(!allocated){
                break;
            }
        }
        if(processesAllocated == numberOfProcesses){
            System.out.println("Safely allocated");
        }else{
            System.out.println("Not all processes can be allocated safely");
        }
    }
}
