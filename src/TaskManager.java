import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TaskManager {

    //Listor.
    List<String> taskList = new ArrayList<>();
    List<String> doneList = new ArrayList<>();

    //JFrame-objekt.
    JFrame frame = new JFrame("GTD");

    //JPanel-objekt.
    JPanel panel = new JPanel();
    JPanel taskListPanel = new JPanel();
    JPanel doneListPanel = new JPanel();
    
    //JLabel-objekt.
    JLabel taskHeader = new JLabel("Skriv in ny uppgift nedan, klicka sedan på \"Lägg till uppgift\""); //
    JLabel removeHeader = new JLabel("Skriv in numret på uppgiften som är utförd, klicka sedan på \"Ta bort"); //
    JLabel toDoHeader = new JLabel("Uppgifter att göra");
    JLabel doneHeader = new JLabel("Utförda uppgifter");



    public TaskManager() {

        JTextField inputInsertTask = new JTextField();
        inputInsertTask.setPreferredSize(new Dimension(150, 20));
        JButton insertBtn = new JButton("Lägg till uppgift"); 
        insertBtn.addActionListener(e -> {
            newTask(inputInsertTask.getText());
            inputInsertTask.setText("");
        });

        JTextField inputRemoveTask = new JTextField();
        inputRemoveTask.setPreferredSize(new Dimension(150, 20));
        JButton removeBtn = new JButton("Ta bort uppgift"); 
        removeBtn.addActionListener(e -> {
            removeTask(inputRemoveTask.getText());
            inputRemoveTask.setText("");

        });

        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(taskHeader, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(inputInsertTask, gbc);

        gbc.gridx = 1;
        panel.add(insertBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(removeHeader, gbc);

        gbc.gridy = 3;
        panel.add(inputRemoveTask, gbc);

        gbc.gridx = 1;
        panel.add(removeBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(toDoHeader, gbc);

        gbc.gridx = 1;
        panel.add(doneHeader, gbc);

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
        int index = Integer.parseInt(removeTask);
        doneList.add(taskList.get(index-1));
        taskList.remove(index - 1);
        printTasks();
    }

    public void printTasks () {
        taskListPanel.setLayout(new GridLayout(taskList.size(), 1));

        taskListPanel.removeAll();

        int i = 1;
        for (String item : taskList) {
            JLabel addTaskList = new JLabel(i++ + " " +item);//
            taskListPanel.add(addTaskList);
        }

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(taskListPanel, gbc);

        doneListPanel.setLayout(new GridLayout(doneList.size(), 1));

        doneListPanel.removeAll();

        int j = 1;
        for (String item : doneList) {
            JLabel doneTaskList = new JLabel(j++ + " " +item);
            doneListPanel.add(doneTaskList);
        }

        gbc.gridx = 1;
        panel.add(doneListPanel, gbc);


        frame.pack();
    }

    public static void main(String[] args) {
        new TaskManager();
    }

}
