package src;
import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class UpdateEntry extends JFrame
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
  
    public UpdateEntry(PersonListing plisting, JTextField txtName, String timeFrame)
    {
        setTitle("Updating "+txtName.getText());
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();
        //pnlDisplay.add(new JLabel("Name:")); 
        //txtName = new JTextField(txtName);
        //pnlDisplay.add(txtName);
        

        Panel buttonPanel = new Panel();
        pnlDisplay.add(new JLabel("Time Frame"));
        /*if (timeFrame.compareTo("Brunch") == 0)
        {
            txtBrunch = new JButton("Brunch");
            buttonPanel.add(txtBrunch);
        }
        else if (timeFrame.compareTo("Dinner") == 0)
        {
            txtDinner = new JButton("Dinner");
            buttonPanel.add(txtDinner);
        }*/
        
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
        //pEntry1 = this;

        
    }

    
    public UpdateEntry(PersonListing thisForm, JTextField txtNameUpdate) {
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
        private ArrayList<Person> plist;

        public void actionPerformed(ActionEvent e)
        {
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
                        pEntry1.setVisible(false); 
                    } 

                }
                else if (dinner==false)
                {
                    if ((name.length > 1) && (date.length>1))
                    {
                        plisting.addPerson(new Person(txtName.getText(), txtDate.getText(), DinnerEntry.getTime(), "Dinner", txtReservationType, Integer.parseInt(txtNumPerson.getText())));
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

