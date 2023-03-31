package src;
import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.beans.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.io.*;
import java.sql.Connection;



public class UpdatePerson extends JFrame 
{	
	private JTextField  txtName;
    private JButton     cmdUpdate;
    private JButton     cmdClose;
    private JTextField  txtNameUpdate; 

    private JPanel      pnlCommand;
    private JPanel      pnlDisplay;
    
    public UpdatePerson uEntry1;
    private PersonListing plisting;
	private PersonListing thisForm;
    private ArrayList<Person> personListing;


    public UpdatePerson(PersonListing plisting)
    {
        setTitle("Updating a Reservation");
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();

        
        pnlDisplay.add(new JLabel("Name to be Updated: "));
        txtNameUpdate = new JTextField(20);
        pnlDisplay.add(txtNameUpdate);

       
        
        cmdUpdate      = new JButton("Update");
        cmdClose   = new JButton("Close");

        cmdUpdate.addActionListener(new peUpdateButtonListener());
        cmdClose.addActionListener(new peCloseButtonListener());

        pnlCommand.add(cmdUpdate);
        pnlCommand.add(cmdClose);
        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);

        this.plisting= plisting;
        uEntry1=this;
    }

	private class peCloseButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

            uEntry1.setVisible(false);
            
        }

    }

    private class peUpdateButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            personListing = plisting.loadPersons("person.dat");
            if (personListing.size()>=0)
            {

                for (int i=0; i<personListing.size(); i++)
                {


                    if (personListing.get(i).getName().compareTo(txtNameUpdate.getText()) == 0)
                    {
                        UpdateEntry uEntry = new UpdateEntry(thisForm, txtNameUpdate);
                        break;
                    }

                }

            }

            //UpdateEntry uEntry = new UpdateEntry(thisForm, txtNameUpdate, personListing.get(i).getTypeReservation());

            plisting.showTable(personListing);
            //PersonListing.createAndShowGUI();
            uEntry1.setVisible(false);

        }
    
    }


    
}
