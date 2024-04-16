import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;


public class TextEditor implements ActionListener {
    // declaring the properties of TextEditor

    JFrame frame;

    JMenuBar menuBar;

    JMenu file, edit;

    JMenuItem newFile, openFile, saveFile;

    JMenuItem cut, copy, paste, selectAll, close;

    JTextArea textArea;

    TextEditor(){
        // Initialize the frame
        frame = new JFrame();

        //Initialize menubar
        menuBar = new JMenuBar();

        //Initialize textArea
        textArea = new JTextArea();

        // Initialize menu
        file = new JMenu("File");
        edit = new JMenu("Edit");

        // Initialize file menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Sava File");
        //Add action listener to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        // add menu item in file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Initialize edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");
        //Add action listener to file menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        //add menu item in edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        menuBar.add(file);
        menuBar.add(edit);

        // Add menus to menubar
        frame.setJMenuBar(menuBar);
//        // Add text area to frame
//        frame.add(new JScrollPane(textArea)); // Use a JScrollPane to allow scrolling

        //Create content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        // Add text area to panel
        panel.add(textArea, BorderLayout.CENTER);
        //Create Scroll Pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //Add scroll pane to panel
        panel.add(scrollPane);
        // Add panel to frame
        frame.add(panel);


        // Set Dimension of frame
        frame.setBounds(100,100,500,500);
        frame.setTitle("Text Editor Developed by Shraddha Gaur");
        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()== cut){
            //perform cut operation
            textArea.cut();
        }
        if(actionEvent.getSource()== copy){
            //perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource()== paste){
            //perform paste operation
            textArea.paste();
        }
        if(actionEvent.getSource()== selectAll){
            //perform select all operation
            textArea.selectAll();
        }
        if(actionEvent.getSource()== close){
            //perform close editor operation
            System.exit(0);
        }
        if(actionEvent.getSource()== openFile){
            //open the file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            //if we have clicked on Open button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //Getting selected file
                File file = fileChooser.getSelectedFile();
                //Get the path of selected file
                String filePath = file.getPath();
                try{
                    // Initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    //Initialize Buffered Reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output ="";
                    //Read contents of file line by line
                    while((intermediate= bufferedReader.readLine())!= null) {
                        output +=intermediate+"\n";
                    }
                    //Set the output string to text area
                    textArea.setText(output);
                }
                catch (IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }

        }

        if(actionEvent.getSource()== saveFile){
            //Initialize file picker
            JFileChooser fileChooser = new JFileChooser("C:");
            // Get choosen option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //check if we have clicked on save button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //Create a new file with chosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    // Initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    //Initialize Buffered writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //Write contents of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }

        }
        if(actionEvent.getSource()==newFile){
            TextEditor newTextEditor = new TextEditor();
        }
    }

    public static void main(String[] args) {
        TextEditor texteditor = new TextEditor();

    }
}