import java.awt.GridBagConstraints;     //Importerar alla färdiga klasser vi behöver.
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TaskManager {      //Klassen som utgör själva programmet.

    //Listor för att spara nya uppgifter och genomförda uppgifter.
    List<String> taskList = new ArrayList<>();
    List<String> doneList = new ArrayList<>();

    //JFrame-objekt.
    JFrame frame = new JFrame("GTD");

    //JPanel-objekt.
    JPanel panel = new JPanel();
    JPanel taskListPanel = new JPanel();
    JPanel doneListPanel = new JPanel();
    
    //JLabel-objekt.
    JLabel taskHeader = new JLabel("Skriv in ny uppgift nedan, klicka sedan på \"Lägg till uppgift\"");  
    JLabel removeHeader = new JLabel("Klicka i rutan för att markera en uppgift som klar");
    JLabel toDoHeader = new JLabel("Uppgifter att göra");
    JLabel doneHeader = new JLabel("Utförda uppgifter");

    public TaskManager() {      //Metoden Taskmanager skapar vårt fönster.

        JTextField inputInsertTask = new JTextField();      //Textfält och knapp för att lägga till ny uppgift. Lämnaas fältet tomt så händer inget.
        JButton insertBtn = new JButton("Lägg till uppgift");
        insertBtn.addActionListener(e -> {
            if (!inputInsertTask.getText().isEmpty()) {
                try {
                    newTask(inputInsertTask.getText());
                } catch (IOException ex) {
                }
                inputInsertTask.setText("");
            }

            
        });

        panel.setLayout(new GridBagLayout());       //Själva layouten.

        GridBagConstraints gbc = new GridBagConstraints();
        
        final int BOTTOM = 10;
        final int LEFT = 30;
        final int RIGHT = 30;
        final int TOP = 10;
        EmptyBorder border1 = new EmptyBorder(TOP, LEFT, BOTTOM, RIGHT);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy = 0;
        panel.add(taskHeader, gbc);
        taskHeader.setBorder(border1);

        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbc.fill = 1;
        gbc.gridy = 1;
        panel.add(inputInsertTask, gbc);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = 1;
        gbc.gridy = 1;
        panel.add(insertBtn, gbc);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy = 2;
        panel.add(removeHeader, gbc);
        removeHeader.setBorder(border1);

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
    //Metod för att addera uppgift till listan med nya uppgifter. Tar emot sträng med texten som beskriver uppgiften.
    public void newTask(String insertTask) throws IOException {

        taskList.add(insertTask);
        printTasks();       //Åberopar metod för att skriva ut listan med uppgifter.
    }
    
    public void removeTask(int removeTask) throws IOException {     //Metod för att ta bort uppgift och flytta den till listan med slutförda.
        int index = removeTask;                                     //Tar emot int som används för att ta bort uppgiften på rätt indexplats.
        doneList.add(taskList.get(index-1));
        taskList.remove(index - 1);
        printTasks();
    }

    public JLabel getImage() throws IOException {   //Metod för att visa bild när alla uppgifter är slutförda.
        BufferedImage bufferedImage = ImageIO.read(getClass().getResourceAsStream("chuck.png"));
        Image image = bufferedImage.getScaledInstance(200, 125, Image.SCALE_DEFAULT);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        return imageLabel;
    } 

    public JPanel getCheckBoxes() {     //Metod som skapar en checkbox för varje uppgift.
        JCheckBox checkBox;
        int i = 1;
        for (String item : taskList) {
            i++;
            int j = i - 1;
            checkBox = new JCheckBox(String.valueOf(j) + " " + item);
            taskListPanel.add(checkBox);
            
            checkBox.addActionListener(e ->{        //När checkboxen klickas så åberobas metoden som flyttar uppgiften till slutfört.
                try {
                    removeTask(j);
                } catch (IOException ex) {
                }
            });
            }
        return taskListPanel;
    }

    public JPanel getDoneList() {   //Metod som visar listan med slutförda uppgifter.
        int j = 1;
        for (String item : doneList) {
            JLabel doneTaskList = new JLabel(j++ + " " +item);
            doneListPanel.add(doneTaskList);
        }
        return doneListPanel;
    }

    public void printTasks () throws IOException {      //Metoden som skriver ut listan med återstående uppgifter.
        taskListPanel.setLayout(new GridLayout(taskList.size(), 1));
        taskListPanel.removeAll();

        if (taskList.isEmpty()) {           //Kontrollerar om listan är tömd, åberopar i så fall metoden för att visa bilden.
            taskListPanel.add(getImage());
        }
        else {
            taskListPanel = getCheckBoxes();    //Annars Presenteras återstående uppgifter.
        }

        GridBagConstraints gbc = new GridBagConstraints();      //Layoutförändringar när listor presenteras.

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(taskListPanel, gbc);

        doneListPanel.setLayout(new GridLayout(doneList.size(), 1));
        doneListPanel.removeAll();

        doneListPanel = getDoneList();

        gbc.gridx = 1;
        panel.add(doneListPanel, gbc);

        frame.pack();
    }
    
    public static void main(String[] args) {    //Main-metoden där Taskmanager åberopas för att skapa gränssnittet.
        new TaskManager();
    }

}