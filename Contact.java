
import java.time.LocalDate;
import java.util.Comparator;


// all the comparable data are from type string( which is name ,phoneNumber and email) so we spicify the geniric type to string
public class Contact implements Comparable<String>{ 
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private String birthday ;
    private String notes;

    public LinkedList<Event> relatedEvent = new LinkedList<Event>();
    
    
    
    Contact(){
        name = null;
        phoneNumber =null;
        email= null;
        address =null;
        birthday= null;
        notes =null;
    }
    
    // a constructor
    public Contact(String name, String phoneNumber, String email, String address, String birthday, String notes) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.notes = notes;
    }
    
    //copy constructor
    public Contact(Contact c) {
        name = c.getName();
        phoneNumber = c.getPhoneNumber() ;
        email = c.getEmail();
        address = c.getAddress();
        birthday = c.getBirthday();
        notes = c.getNotes();
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public int compareTo(String comparedName) {//ihave a question here
        //in the requierment spcify that it's unique by phone number and name
        return name.compareTo(comparedName);
        }
    
    public int compareTo(Contact comparedCOntact) {// is exeptable to do that comarition?
        //in the requierment spcify that it's unique by phone number and name
        if(name.compareTo(comparedCOntact.getName()) != 0) //zero is the state where there is equal object
            return phoneNumber.compareTo(comparedCOntact.getPhoneNumber());
        else 
            return name.compareTo(comparedCOntact.getName());
        }

    public LinkedList<Event> getRelatedEvent() {
        return relatedEvent;
    }

    public void setRelatedEvent(LinkedList<Event> relatedEvent) {
        this.relatedEvent = relatedEvent;
    }

    
    @Override
    public String toString() {
        String allInformation = "Contact information" 
                                + "name:" + name 
                                + "\nphoneNumber=" + phoneNumber 
                                + "\nemail:" + email 
                                + "\naddress:" + address 
                                + "\nbirthday:" + birthday 
                                + "\nnotes:" + notes ;
                             
        return allInformation;
       
    }
    
    
     
    
}