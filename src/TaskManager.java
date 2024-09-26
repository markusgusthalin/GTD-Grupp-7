
import com.sun.source.util.TaskListener;


import java.util.ArrayList;

public class TaskManager {

    List<String> taskList = new ArrayList<>();

    public TaskManager() {
        
    }

    public static void newTask(String insertTask) {
        taskList.add(insertTask);
    }

    public static void removeTask() {

    }

    public static void printTasks () {

    }



    public static void main(String[] args) {
        new TaskManager();
    }

}