package src;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BrunchEntry extends JFrame
{
    private JButton     cmdSave;
    private JButton     cmdClose;

    private JPanel      pnlCommand;
    private JPanel      pnlDisplay;
    private JList       b;
    private static String      time;

    public BrunchEntry bEntry1;

    public BrunchEntry()
    {
        setTitle("Selecting Brunch Time");
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();

        
        pnlDisplay.add(new JLabel("Time: "));

        String times[]= {"11:00", "11:30","12:00", "12:30", "1:00", "1:30", "2:00"};
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

        bEntry1=this;
    }

    private class peCloseButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

           bEntry1.setVisible(false);


        }

    }

    private class peSaveButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            time = (String) b.getSelectedValue();
            
            bEntry1.setVisible(false);

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
