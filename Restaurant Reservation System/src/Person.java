package src;
import java.util.Comparator;

import javax.swing.JTextField;

public class Person extends BasePerson
{



    public Person(String name,String date,String time, String rFrame, String typeR, int numPerson )
    {
        super(name, date, time, rFrame, typeR,numPerson);
    }

    
    /** 
     * @return String
     */
    public String getName()
    {
        return name;
    }

    
    /** 
     * @return String
     */
    public static String getPHeader()
    {
        String returnval = "ID\tName\tAge\tWillPublish";
        returnval+="\n---------------------------------";
        return returnval;

    }

    
    /** 
     * @return String
     */
    public String toString()
    {
        return(getName()+"\t"+getDate()+"\t"+getTime()+"\t"+getReservationFrame()+"\t"+getTypeReservation()+"\t"+getNumPerson());
    }


    public void Time(JTextField newTime) {
    }


}
    
    class SortOnDate implements Comparator<Person>{

        public int compare(Person p1, Person p2)
        {
            //if (p1.getDate()>p2.getDate()) return 1;
            //if (p1.getDate()<p2.getDate()) return -1;
            return p1.getDate().compareTo(p2.getDate());
        }

    }

    class SortOnTime implements Comparator<Person>{

        public int compare(Person p1, Person p2)
        {
            // String[] name1= p1.getName().split(" ");
            // String[] name2= p1.getName().split(" ");
            // return name1[0].compareTo(name2[0]);   
            return p1.getTime().compareTo(p2.getTime());   
        }

    }
    
    
    
    

