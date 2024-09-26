



import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    List<String> taskList = new ArrayList<>();
   
    public TaskManager() {
        
    }

    public void newTask(String insertTask) {
        taskList.add(insertTask);
    }

    public void removeTask() {

    }

    public void printTasks () {

    }



    public static void main(String[] args) {
        new TaskManager();
    }

}