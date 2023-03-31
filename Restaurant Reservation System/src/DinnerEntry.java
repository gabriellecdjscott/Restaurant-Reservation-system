package src;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DinnerEntry extends JFrame
{
    private JButton     cmdSave;
    private JButton     cmdClose;

    private JPanel      pnlCommand;
    private JPanel      pnlDisplay;
    private JList       b;
    private static String      time;

    public DinnerEntry dEntry1;

    public DinnerEntry()
    {
        setTitle("Selecting Dinner Time");
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();

        
        pnlDisplay.add(new JLabel("Time: "));

        String times[]= {"6:00", "6:30","7:00", "7:30", "8:00", "8:30", "9:00", "9:30"};
        b = new JList(times);
        
        pnlDisplay.add(b);
     
        
        cmdSave      = new JButton("Save");
        cmdClose   = new JButton("Close");

        cmdSave.addActionListener(new peSaveButtonListener());
        cmdClose.addActionListener(new peCloseButtonListener());

        pnlCommand.add(cmdSave);
        pnlCommand.add(cmdClose);
        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);

        dEntry1=this;
    }

    private class peCloseButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

           dEntry1.setVisible(false);
           
        }

    }

    private class peSaveButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            time = (String) b.getSelectedValue();
            dEntry1.setVisible(false);

        }
   
    }

    
    /** 
     * @return String
     */
    public static String getTime()
    {
        return time;
    }
}
