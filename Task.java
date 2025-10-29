import java.util.Date;
import java.util.Set;
class Task{
    private int TaskId;
    private String TaskName;
    private String TaskDescription;
    private Date date;
    private Priority taskPriority;
    private Set<String> categoriesSet;

    public  Task(int TaskId, String TaskName, String TaskDescription, Date date, Priority taskPriority, Set<String> categoriesSet){
        this.TaskId = TaskId;
        this.TaskName = TaskName;
        this.TaskDescription = TaskDescription;
        this.date = date;
        this.taskPriority = taskPriority;
        this.categoriesSet = categoriesSet;
    }

    public int getTaskId(){return TaskId;}
    public String getTaskName(){
        return TaskName;
    }
    public String getTaskDescription(){
        return TaskDescription;
    }

    public Date getDueDate()
    {
        return date;
    }

    public Priority getTaskPriority(){
        return taskPriority;
    }

    public Set<String> getCategoriesSet(){
        return categoriesSet;
    }


}