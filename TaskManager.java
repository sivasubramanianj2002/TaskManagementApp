import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

class TaskManager{
    Map<Integer,Task> map = new HashMap<>();
    Scanner sc = new Scanner(System.in);
    int idCounter = 0;

    public void addTask()
    {
        System.out.println("Enter task name");
        String taskName = sc.nextLine();
        System.out.println("Enter task description");
        String taskDescription = sc.nextLine();
        System.out.println("Enter task priority");
        Priority taskPriority = getPriority();
        Date dueDate = null;
        while(dueDate == null)
        {
             dueDate = getDate();
        }
        Set<String> categoriesSet = getCategories();
        map.put(++idCounter, new Task(idCounter, taskName,taskDescription,dueDate,taskPriority,categoriesSet));
        System.out.println("Task Added Successfully");
    }

    public void viewTask()
    {
        for(Map.Entry<Integer, Task> entry : map.entrySet())
        {
           System.out.printf("-%2d. Name-%8s\n || Description -%10s\n || Due Date -%10s\n || Priority -%7s\n || Categories - %s\n",
                   entry.getKey(),
                   entry.getValue().getTaskName(),
                   entry.getValue().getTaskDescription(),
                   getFormattedDate(entry.getValue().getDueDate()),
                   entry.getValue().getTaskPriority(),
                   setToString(entry.getValue().getCategoriesSet()));
        }
    }

    public Date getDate()
    {
        System.out.println("Enter Date(dd-mm-yyyy");
        String date = sc.nextLine().trim();
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date formattedDate = dt.parse(date);
                if(formattedDate.before(new Date()))
                {
                    System.out.println("Please enter present or future date");
                    return null;
                }
                return formattedDate;
            } catch (ParseException e) {
                System.out.println("Invalid Date Try again");
            }
            return null;
    }

    public String getFormattedDate(Date d)
    {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(d);
    }

    public Priority getPriority(){
        System.out.println("Enter Priority (HIGH/MEDIUM/LOW)");
        String priority = sc.next().trim().toUpperCase();
        try{
            return Priority.valueOf(priority);
        }catch (IllegalArgumentException
                e)
        {
            System.out.println("Invalid Priority, Defaults to Medium");
            return Priority.MEDIUM;
        }
    }
    public void viewTaskByPriority(){
        Map<Priority,List<Task>> taskPriority = new HashMap<>();
        for(Priority priority : Priority.values())
        {
            taskPriority.put(priority, new ArrayList<>());
        }

        for(Task task : map.values())
        {
            taskPriority.get(task.getTaskPriority()).add(task);
        }
        viewTask("HIGH", taskPriority.get(Priority.HIGH));
        viewTask("MEDIUM", taskPriority.get(Priority.MEDIUM));
        viewTask("LOW", taskPriority.get(Priority.LOW));
    }
    public void viewTask(String priority, List<Task> tasks) {
        if (!tasks.isEmpty()) {
            System.out.println("\n----- " + priority + " priority tasks------\n");
            for (Task task : tasks) {
                System.out.printf("-%2d. Name-%8s\n || Description -%10s\n || Due Date -%10s\n || Priority -%7s\n",
                        task.getTaskId(),
                        task.getTaskName(),
                        task.getTaskDescription(),
                        getFormattedDate(task.getDueDate()),
                        task.getTaskPriority());
            }
        }
    }

    private Set<String> getCategories(){
        System.out.println("Enter categories(comma seperated)");
        String categories = sc.nextLine();
        Set<String> categoriesSet = new HashSet<>();
        String [] categoriesArr = categories.split(",");
        for(String c : categoriesArr)
        {
            String trimmed = c.trim();
            if(!trimmed.isEmpty())
            {
                categoriesSet.add(trimmed);
            }
        }
        return categoriesSet;
    }

    public static String setToString (Set<String> categoriesSet)
    {
        return String.join(", ", categoriesSet);
    }

    public void viewTaskByCategory(){
        Map<String, List<Task>> categoriesMap = new HashMap<>();
        for(Task task : map.values())
        {
            for(String categories: task.getCategoriesSet())
            {
                if(!categoriesMap.containsKey(categories))
                {
                    categoriesMap.put(categories, new ArrayList<>());
                }
                categoriesMap.get(categories).add(task);
            }
        }

        for(Map.Entry<String, List<Task>> entry : categoriesMap.entrySet())
        {
            System.out.println("-------Tasks under Category: " + entry.getKey() +"----------");
            for(Task task : entry.getValue())
            {
                System.out.printf("-%2d. Name-%8s\n || Description -%10s\n || Due Date -%10s\n || Priority -%7s\n || Categories - %s\n",
                        task.getTaskId(),
                        task.getTaskName(),
                        task.getTaskDescription(),
                        getFormattedDate(task.getDueDate()),
                        task.getTaskPriority(),
                        setToString(task.getCategoriesSet()));
            }
        }
    }

}