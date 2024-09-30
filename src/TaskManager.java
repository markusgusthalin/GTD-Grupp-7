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
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;

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
    //JLabel removeHeader = new JLabel("Skriv in numret på uppgiften som är utförd, klicka sedan på \"Ta bort\""); 
    JLabel removeHeader = new JLabel("Klicka i rutan för att markera en uppgift som klar");
    JLabel toDoHeader = new JLabel("Uppgifter att göra");
    JLabel doneHeader = new JLabel("Utförda uppgifter");



    public TaskManager() {

        JTextField inputInsertTask = new JTextField();
        //inputInsertTask.setPreferredSize(new Dimension(150, 20));
        JButton insertBtn = new JButton("Lägg till uppgift");
        //insertBtn.setPreferredSize(new Dimension(150, 20));
        insertBtn.addActionListener(e -> {
            newTask(inputInsertTask.getText());
            inputInsertTask.setText("");
        });

        // JTextField inputRemoveTask = new JTextField();
        // inputRemoveTask.setPreferredSize(new Dimension(150, 20));
        // JButton removeBtn = new JButton("Ta bort uppgift");
        // removeBtn.setPreferredSize(new Dimension(150,20));
        // removeBtn.addActionListener(e -> {
        //     removeTask(inputRemoveTask.getText());
        //     inputRemoveTask.setText("");

        // });

        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        
        final int BOTTOM = 10;
        final int LEFT = 30;
        final int RIGHT = 30;
        final int TOP = 10;
        EmptyBorder border1 = new EmptyBorder(TOP, LEFT, BOTTOM, RIGHT);
        
        // final int BOTTOM2 = 0;
        // final int LEFT2 = 30;
        // final int RIGHT2 = 30;
        // final int TOP2 = 0;
        // EmptyBorder border2 = new EmptyBorder(TOP2, LEFT2, BOTTOM2, RIGHT2);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy = 0;
        panel.add(taskHeader, gbc);
        taskHeader.setBorder(border1);

        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbc.fill = 1;
        gbc.gridy = 1;
        panel.add(inputInsertTask, gbc);
        //inputInsertTask.setBorder(border2);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = 1;
        gbc.gridy = 1;
        panel.add(insertBtn, gbc);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy = 2;
        panel.add(removeHeader, gbc);
        removeHeader.setBorder(border1);

        // gbc.gridwidth = GridBagConstraints.RELATIVE;
        // gbc.gridy = 3;
        // panel.add(inputRemoveTask, gbc);
        

        // gbc.gridwidth = GridBagConstraints.REMAINDER;
        // panel.add(removeBtn, gbc);

        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbc.gridy = 4;
        panel.add(toDoHeader, gbc);
        toDoHeader.setBorder(border1);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy = 4;
        panel.add(doneHeader, gbc);
        doneHeader.setBorder(border1);

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
    public void removeTask(int removeTask) {
        int index = removeTask;
        doneList.add(taskList.get(index-1));
        taskList.remove(index - 1);
        printTasks();
    }

    public void printTasks () {
        taskListPanel.setLayout(new GridLayout(taskList.size(), 1));

        taskListPanel.removeAll();
        JCheckBox checkBox;

        int i = 1;
        for (String item : taskList) {
            JLabel addTaskList = new JLabel(i++ + " " +item);
            //taskListPanel.add(addTaskList);
            checkBox = new JCheckBox(i - 1 + " " + item);
            int j = i - 1;
            
            checkBox.setName(item);
            taskListPanel.add(checkBox);
            
            checkBox.addActionListener(e ->{
                removeTask(j);
            });
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
