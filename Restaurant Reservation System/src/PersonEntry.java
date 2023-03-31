package src;
import java.awt.*;
import javax.swing.*;
import java.io.*;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PersonEntry extends JFrame
{
    private JTextField  txtName;       //name
    private JTextField  txtDate;        //date
    private JTextField  txtNumPerson;
    private JCheckBox   txtReservationType1;
    private JCheckBox   txtReservationType2;
    private JCheckBox   txtReservationType3;
    private JCheckBox   txtReservationType4;
    private JCheckBox   txtReservationType5;
    private JCheckBox   txtReservationType6;
    private JButton     txtBrunch;
    private JButton     txtDinner;
    private String      txtTime;
    private String txtReservationType;
    private JButton     cmdSave;
    private JButton     cmdClose;

    private JPanel      pnlCommand;
    private JPanel      pnlDisplay;
    private Boolean     brunch=true;
    private Boolean     dinner=true;

    private PersonListing plisting;
    public PersonEntry pEntry1;
  
    public PersonEntry(PersonListing plisting)
    {
        setTitle("Entering new person");
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();
        pnlDisplay.add(new JLabel("Name:")); 
        txtName = new JTextField(20);
        pnlDisplay.add(txtName);
        
        pnlDisplay.add(new JLabel("Number of persons:"));
        txtNumPerson = new JTextField(3);
        pnlDisplay.add(txtNumPerson);

        Panel checkBoxPanel= new Panel(); //place the checkboxes in a row
        pnlDisplay.add(new JLabel("Type of Reservation:"));
        txtReservationType1 = new JCheckBox("Date");
        checkBoxPanel.add(txtReservationType1);
        txtReservationType2 = new JCheckBox("Family");
        checkBoxPanel.add(txtReservationType2);
        txtReservationType3 = new JCheckBox("Party");
        checkBoxPanel.add(txtReservationType3);
        txtReservationType4 = new JCheckBox("Proposal");
        checkBoxPanel.add(txtReservationType4);
        txtReservationType5 = new JCheckBox("Reception");
        checkBoxPanel.add(txtReservationType5);
        txtReservationType6 = new JCheckBox("Single");
        checkBoxPanel.add(txtReservationType6);
        pnlDisplay.add(checkBoxPanel);
        

        pnlDisplay.add(new JLabel("Date:"));
        txtDate = new JTextField(3);
        pnlDisplay.add(txtDate);

        Panel buttonPanel = new Panel();
        pnlDisplay.add(new JLabel("Time Frame"));
        txtBrunch = new JButton("Brunch");
        buttonPanel.add(txtBrunch);
        txtDinner = new JButton("Dinner");
        buttonPanel.add(txtDinner);
       
        pnlDisplay.add(buttonPanel);
       
        pnlDisplay.setLayout(new GridLayout(6,5));
       
        cmdSave      = new JButton("Save");
        cmdClose   = new JButton("Close");

        cmdSave.addActionListener(new peSaveButtonListener());
        cmdClose.addActionListener(new peCloseButtonListener());
        txtBrunch.addActionListener(new brunchCheckBoxListener());
        txtDinner.addActionListener(new dinnerCheckBoxListener());


        pnlCommand.add(cmdSave);
        pnlCommand.add(cmdClose);
        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);

        this.plisting = plisting;
        pEntry1 = this;

        
    }

    
    /** 
     * @param time
     */
    public void Time(String time){
        txtTime= time;
    }

    
    private class peCloseButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

           pEntry1.setVisible(false);


        }

    }

    private class peSaveButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {

			//ArrayList<Person> plist = new ArrayList<Person>();
			String[] name = txtName.getText().split(" ");
			String[] date = txtDate.getText().split("/");
			
			
			if (txtReservationType1.isSelected())
			{
				txtReservationType = txtReservationType1.getText();
			}
			else if (txtReservationType2.isSelected())
			{
				txtReservationType = txtReservationType2.getText();
			}
			else if (txtReservationType3.isSelected())
			{
				txtReservationType = txtReservationType3.getText();
			}
			else if (txtReservationType4.isSelected())
			{
				txtReservationType = txtReservationType4.getText();
			}
			else if (txtReservationType5.isSelected())
			{
				txtReservationType = txtReservationType5.getText();
			}
			else if (txtReservationType6.isSelected())
			{
				txtReservationType = txtReservationType6.getText();
			}

			
			if(brunch==false)
			{
				if ((name.length > 1) && (date.length>1))
				{
					//plist= plisting.loadPersons("person.dat");
					//plist.add(new Person(txtName.getText(), txtDate.getText(), BrunchEntry.getTime(), "Brunch", txtReservationType, Integer.parseInt(txtNumPerson.getText())));
					plisting.addPerson(new Person(txtName.getText(), txtDate.getText(), BrunchEntry.getTime(), "Brunch", txtReservationType, Integer.parseInt(txtNumPerson.getText())));
					try{
						FileWriter file = new FileWriter(new File("person.dat"), true);
						//File test = new File("new.dat");
						
						String person = name[0]+" "+name[1]+" "+txtDate.getText()+" "+"Brunch"+" " +BrunchEntry.getTime()+" "+txtReservationType+" "+Integer.parseInt(txtNumPerson.getText())+"\n";
            file.write(person);
						file.close();
			  
					}catch(Exception ex){}
					pEntry1.setVisible(false); 
				} 

			}
			else if (dinner==false)
			{
				if ((name.length > 1) && (date.length>1))
				{
					plisting.addPerson(new Person(txtName.getText(), txtDate.getText(), DinnerEntry.getTime(), "Dinner", txtReservationType, Integer.parseInt(txtNumPerson.getText())));
				  try{
						FileWriter file = new FileWriter(new File("person.dat"), true);
						//File test = new File("new.dat");
						
						String person = name[0]+" "+name[1]+" "+txtDate.getText()+" "+"Dinner"+" "+DinnerEntry.getTime()+" "+txtReservationType+" "+Integer.parseInt(txtNumPerson.getText())+"\n";
            file.write(person);
						file.close();
			  
				  }catch(Exception ex){}
					pEntry1.setVisible(false);
				}

			}


        }
   
    }

    private class brunchCheckBoxListener implements ActionListener
    {
       
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            brunch = false;
            BrunchEntry bEntry = new BrunchEntry();
            
        }

    }

    private class dinnerCheckBoxListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            dinner = false;
            DinnerEntry dEntry = new DinnerEntry();
        }

    }


}