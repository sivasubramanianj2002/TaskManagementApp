    import java.util.*;
    class Main{
        public static void main(String[] args){
            System.out.println("Welcome to Task Management");
            boolean run = true;
            Scanner sc = new Scanner(System.in);
            TaskManager taskManager = new TaskManager();
            while(run){
                System.out.println("\n 1.Add Task \n 2.View Task \n 3.View Task By Priority\n 4.View Task By Categories\n 5.Exit");
                System.out.println("Enter user choice");
                int userChoice = Integer.parseInt(sc.nextLine());

                switch (userChoice){
                    case 1 :
                        taskManager.addTask();
                        break;
                    case 2:
                        taskManager.viewTask();
                        break;
                    case 3:
                        taskManager.viewTaskByPriority();
                        break;
                    case 4:
                        taskManager.viewTaskByCategory();
                        break;
                    case 5:
                        System.out.println("Exit");
                        run = false;
                        sc.close();
                }
            }
        }
    }