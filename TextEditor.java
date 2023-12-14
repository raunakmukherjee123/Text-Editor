import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener
{
    JFrame frame;
    JMenuBar menubar;
    JMenu file,edit;
    JMenuItem newfile,openfile,savefile,cut,copy,paste,selectall,close;
    JTextArea text;
    TextEditor()
    {
        frame=new JFrame();
        menubar=new JMenuBar();
        text=new JTextArea();
        file=new JMenu("Files");
        edit=new JMenu("Edit");
        newfile=new JMenuItem("New File");
        openfile=new JMenuItem("Open file");
        savefile=new JMenuItem("Save");
        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectall=new JMenuItem("Select All");
        close=new JMenuItem("Close");
        newfile.addActionListener(this); //in order to create action event on menu items,action listener is used
        // then only its respective operation will be performed
        openfile.addActionListener(this);
        savefile.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        close.addActionListener(this);
        file.add(newfile);
        file.add(openfile);
        file.add(close);
        file.add(savefile);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);
        menubar.add(file);
        menubar.add(edit);
        frame.setJMenuBar(menubar);
        //create content pane
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //create scroll pane
        JScrollPane scrollPane=new JScrollPane(text,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);
        frame.add(panel);
        //frame.add(text); we don't need this as out here only text is added, but before this line text then scrollers are added
        frame.setTitle("Text Editor");
        frame.setBounds(100,150,500,500);
        frame.setVisible(true);
        frame.setLayout(null);
    }
   // @Override
    public void actionPerformed(ActionEvent ac)
    {
      if(ac.getSource()==cut)
      {
          text.cut(); // belongs to TextArea class. In here, operation is created
      }
        if(ac.getSource()==copy)
        {
            text.copy();
        }
        if(ac.getSource()==paste)
        {
            text.paste();
        }
        if(ac.getSource()==selectall)
        {
            text.selectAll();
        }
        if(ac.getSource()==close)
        {
           System.exit(0);
        }
        if(ac.getSource()==openfile)
        {
            JFileChooser fileChooser=new JFileChooser("C:");// by default C drive
            int chooseoption=fileChooser.showOpenDialog(null);// option to choose open or cancel
            if(chooseoption==JFileChooser.APPROVE_OPTION)// if we clicked on open button
            {
                File file=fileChooser.getSelectedFile();// getting selected file
                String filePath=file.getPath();// getting path of selected file
                try
                {
                    //initialize file reader to read file
                    FileReader filereader=new FileReader(filePath);
                    // now read the content from the file
                    BufferedReader bf=new BufferedReader(filereader);
                    String intermediate="",output="";
                    //intermediate will read content line by line and store it in output which will
                    // be the actual text to be transferred to texteditor
                    while((intermediate=bf.readLine())!=null) //may throw ioexception
                    {
                        output+=intermediate+"\n";
                    }
                    // then transfer output to texteditor
                    text.setText(output);
                }
                catch (FileNotFoundException fe)
                {
                    fe.printStackTrace();
                }
                catch (IOException io)
                {
                    io.printStackTrace();
                }
            }
        }
        if(ac.getSource()==savefile) // during saving, get texts from text editor and save it to a file
        {
            //initialize file picker
           JFileChooser fileChooser=new JFileChooser("C");
           int chooseOption=fileChooser.showSaveDialog(null);
           if(chooseOption==JFileChooser.APPROVE_OPTION)// if we clicked on save button
           {
               // while saving, texts from test editor are transferred to a new file
               // so create new file with choosen directory path and file name
               File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
               // filename + path + ".txt
               try
               {
                   // while saving we need to write, not read the file. So initialize file writer
                   FileWriter fileWriter=new FileWriter(file);
                   // initialize bufferedwriter
                   BufferedWriter bw=new BufferedWriter(fileWriter);
                   // now write the contents of text area in that new file
                   text.write(bw);
               }
               catch(IOException io)
               {
                   io.printStackTrace();
               }
           }
        }
        if(ac.getSource()==newfile)
        {
            TextEditor text=new TextEditor();
        }
    }
    public static void main(String[] args)
    {
        TextEditor t=new TextEditor();
    }
}
