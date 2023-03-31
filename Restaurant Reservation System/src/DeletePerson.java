package src;
import java.awt.*;
import javax.swing.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.io.*;


public class DeletePerson extends JFrame
{

    private JButton     cmdDelete;
    private JButton     cmdClose;
    private JTextField  txtNameDeleted; 

    private JPanel      pnlCommand;
    private JPanel      pnlDisplay;
   
    public DeletePerson dEntry1;
    private PersonListing plisting;
    private ArrayList<Person> personListing;

    public DeletePerson(PersonListing plisting)
    {
        setTitle("Deleting a Person");
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();

        
        pnlDisplay.add(new JLabel("Name to be deleted: "));
        txtNameDeleted = new JTextField(20);
        pnlDisplay.add(txtNameDeleted);
        
        cmdDelete      = new JButton("Delete");
        cmdClose   = new JButton("Close");

        cmdDelete.addActionListener(new peDeleteButtonListener());
        cmdClose.addActionListener(new peCloseButtonListener());

        pnlCommand.add(cmdDelete);
        pnlCommand.add(cmdClose);
        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);

        this.plisting= plisting;
       dEntry1=this;
    }

    private class peCloseButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

           dEntry1.setVisible(false);
           
        }

    }

    private class peDeleteButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            personListing = plisting.loadPersons("person.dat");
            if (personListing.size()>=0)
            {

                for (int i=0; i<personListing.size(); i++)
                {


                    if (personListing.get(i).getName().compareTo(txtNameDeleted.getText()) == 0)
                    {
                        //dEntry1.setVisible(false);
                      try{
                        int j = 0;
                        personListing.remove(i);
                        FileWriter per = new FileWriter(new File("person.dat"), false);
                        PrintWriter pwOb = new PrintWriter(per, false);
                        pwOb.flush();
                        pwOb.close();

                        File newp = new File("person.dat");
                        FileWriter rewrites= new FileWriter(newp,true);

                        while(j<personListing.size()){
                          Person rperson = personListing.get(j);
                          String [] name = rperson.getName().split(" ");
                          String date = rperson.getDate();
                          String frame = rperson.getReservationFrame();
                          String time = rperson.getTime();
                          String type = rperson.getTypeReservation();
                          int nump = rperson.getNumPerson();
                          String person = name[0]+" "+name[1]+" "+date+" "+frame+" "+time+" "+type+" "+nump+"\n";
                          rewrites.write(person);
                          j++;
                        }
                        rewrites.close();
                      }
                      catch(Exception io)
                      {}
                      break;
                    }

                }

            }



            plisting.showTable(personListing);
            //PersonListing.createAndShowGUI();
            dEntry1.setVisible(false);

        }
   
    }

}