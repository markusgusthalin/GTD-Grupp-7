



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
    
    JPanel doneTasks  = new JPanel();



    public TaskManager() {

        JTextField inputInsertTask = new JTextField();
        JButton insertBtn = new JButton("Lägg till uppgift");
        //insertBtn.addActionListener(e -> {
            //inputInsertTask(inputInsertMoney.getText());
        ;

        JTextField removeTask = new JTextField();
        JButton removeBtn = new JButton("Ta bort uppgift");
        //withdrawBtn.addActionListener(e -> {
            //withdrawMoney(inputWithdrawMoney.getText());
        ;

        panel.setLayout(new GridLayout(4, 1));
        panel.add(taskHeader);
        panel.add(tasks);

        panel.add(inputInsertTask);
        panel.add(insertBtn);

        panel.add(removeTask);
        panel.add(removeBtn);
        // panel.add(inputInsertTask);
        // panel.add(insertBtn);
        // panel.add(inputRemoveTask);
        // panel.add(removeBtn);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
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
