package src;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;



public class PersonListing extends JPanel {
    private JButton     cmdAddPerson;
    private JButton     cmdClose;
    private JButton     cmdSortDate;
    private JButton     cmdSortTime;
    private JButton     cmdDelete;
    private JButton     cmdUpdate;
  
    private JPanel      pnlCommand;
    private JPanel      pnlDisplay;
    private ArrayList<Person> plist;
    //private ArrayList<Person> agelist;
    private PersonListing thisForm;
    private  JScrollPane scrollPane;
    //private PersonEntry pEntry;

    private JTable table;
    private DefaultTableModel model;

    public PersonListing() {
        super(new GridLayout(2,1));
        thisForm = this;
        

        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();

        plist= loadPersons("person.dat");
        String[] columnNames=  {"First Name",
                "Last Name",
                "Date",
                "Time",
                "Time Frame",
                "Resevation Type",
                "Number of persons"};
        model=new DefaultTableModel(columnNames,0);
        table = new JTable(model);
        table.setBackground(Color.cyan);/////////////////////////
        showTable(plist);

        table.setPreferredScrollableViewportSize(new Dimension(500, plist.size()*15 +50));
        table.setFillsViewportHeight(true);

        scrollPane = new JScrollPane(table);
       
        add(scrollPane);

       
        cmdAddPerson  = new JButton("Add Person");
        cmdSortTime = new JButton("Sort by Time");
        cmdSortDate  = new JButton("Sort by Date");
        cmdDelete = new JButton("Delete Person");
        cmdClose   = new JButton("Close");
        cmdUpdate    = new JButton("Update");


        cmdAddPerson.addActionListener(new AddPersonButtonListener());
        cmdSortDate.addActionListener(new SortDateButtonListener());
        cmdSortTime.addActionListener(new SortTimeButtonListener());
        cmdDelete.addActionListener(new peDeleteButtonListener());
        cmdClose.addActionListener(new CloseButtonListener());
        cmdUpdate.addActionListener(new UpdateButtonListener());
        
        
        pnlCommand.add(cmdAddPerson);
        pnlCommand.add(cmdDelete);
        pnlCommand.add(cmdSortDate);
        pnlCommand.add(cmdSortTime);
        pnlCommand.add(cmdClose);
        pnlCommand.add(cmdUpdate);
       
        add(pnlCommand);
    }

    
    /** 
     * @param plist
     */
    public void showTable(ArrayList<Person> plist)
    {
       if (plist.size()>0)
           model.setRowCount(0);
       /////////////////////
          for (int i=0; i<plist.size(); i++)

                addToTable(plist.get(i));

    }

    
    /** 
     * @param p
     */
    private void addToTable(Person p)
    {
        String[] name= p.getName().split(" ");
        String[] item={name[0],name[1],""+ p.getDate(),""+p.getTime(),""+p.getReservationFrame(),""+p.getTypeReservation(),""+p.getNumPerson()};

        model.addRow(item);        

    }

    public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Macau Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        PersonListing newContentPane = new PersonListing();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    createAndShowGUI();
                }
            });
    }

    
    /** 
     * @param p
     */
    public void addPerson(Person p)
    {
        plist.add(p);
        addToTable(p);

    }


    
    /** 
     * @param pfile
     * @return ArrayList<Person>
     */
    public ArrayList<Person> loadPersons(String pfile)
    {
        Scanner pscan = null;
        ArrayList<Person> plist = new ArrayList<Person>();
        try
        {
            pscan  = new Scanner(new File(pfile));
            while(pscan.hasNext())
            {
                String [] nextLine = pscan.nextLine().split(" ");
                String name = nextLine[0]+ " " + nextLine[1];
                String date = nextLine[2];
                String rFrame = nextLine[3];
                String time = nextLine[4];
                String typeR = nextLine[5];
                int numPerson = Integer.parseInt(nextLine[6]);
                Person p = new Person(name, date, time, rFrame, typeR, numPerson);
                plist.add(p);
            }

            pscan.close();
        }
        catch(IOException e)
        {}
        return plist;
    }

    private class peDeleteButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            DeletePerson dPerson = new DeletePerson(thisForm);
            
        }
    }

    private class CloseButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }

    }

    private class UpdateButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            UpdatePerson pUpdate = new UpdatePerson(thisForm); 
        }

    }

    private class AddPersonButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
 
            PersonEntry pEntry = new PersonEntry(thisForm);


        }

    }

    private class SortDateButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            model.setRowCount(0);
            Collections.sort(plist, new SortOnDate());
            showTable(plist);
            

        }

    }

    private class SortTimeButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            model.setRowCount(0);
            Collections.sort(plist, new SortOnTime());
            showTable(plist);
            

        }

    }

}