



import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class TaskManager {

    List<String> taskList = new ArrayList<>();

    JFrame frame = new JFrame("GTD");
    JPanel panel = new JPanel();
    JLabel taskHeader = new JLabel("1. Uppgifter");
    JLabel tasks = new JLabel("2. Uppgifter");
    JPanel taskListPanel = new JPanel();
    
    JPanel doneTasks  = new JPanel();



    public TaskManager() {

        JTextField inputInsertTask = new JTextField();
        JButton insertBtn = new JButton("Lägg till uppgift");
        insertBtn.addActionListener(e -> {
            newTask(inputInsertTask.getText());
        });

        JTextField inputRemoveTask = new JTextField();
        JButton removeBtn = new JButton("Ta bort uppgift");
        removeBtn.addActionListener(e -> {
            removeTask(inputRemoveTask.getText());
        });

        panel.setLayout(new GridLayout(4, 1));
        panel.add(taskHeader);
        panel.add(tasks);

        panel.add(inputInsertTask);
        panel.add(insertBtn);

        panel.add(inputRemoveTask);
        panel.add(removeBtn);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void newTask(String insertTask) {
        taskList.add(insertTask);
        printTasks();
    }

    public void removeTask(String removeTask) {
        taskList.remove(0);

    }

    public void printTasks () {
        taskListPanel.setLayout(new GridLayout(taskList.size(), 1));

        taskListPanel.removeAll();

        for (String item : taskList) {
            JLabel addTaskList = new JLabel(item);
            taskListPanel.add(addTaskList);
        }

        panel.add(taskListPanel);
        frame.pack();
    }
    



    public static void main(String[] args) {
        new TaskManager();
    }

}
