import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;

public class Event implements Comparable<String>{
    private String eventTitle ,location;
    private LocalDate eventDate;
    private LocalTime startTime ,endTime;
    private String contactName;
    private Contact contactInvolved ;
    private boolean isEvent;
    LinkedList<Event> allEvents = new LinkedList<Event>();
    LinkedList<Contact> contactInEvent = new LinkedList<Contact>();//it's only required to be bst in the phonebook class the rest is left for us
    
    public Event() {
        this.eventTitle = null;
        this.location = null;
        this.eventDate = null;
        this.startTime = null;
        this.endTime = null;
        this.contactName = null;
        this.isEvent = true;
    }

    

    public Event(String eventTitle, String location, LocalDate eventDate, LocalTime startTime, LocalTime endTime, String contactName , boolean isEvent) {
        this.eventTitle = eventTitle;
        this.location = location;
        this.eventDate = eventDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.contactName = contactName;
        this.isEvent = isEvent;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public boolean isIsEvent() {
        return isEvent;
    }

    public void setIsEvent(boolean isEvent) {
        this.isEvent = isEvent;
    }
    
    
    public LinkedList<Contact> getContactInEvent() {
        return contactInEvent;
    }
    
    

    @Override
    public String toString() {//add if this appointment
        String type ="Appointment"; 
        if(isEvent){
            type = "Event";
        }
        
        return  "\nevent/appointment Title:" + eventTitle
                +"\nType:"+ type
                + "\nlocation:" + location
                + "\neventDate:" + eventDate
                + "\nstartTime:" + startTime
                + "\nendTime:" + endTime;
                
               
    }

     void setContactInvolved(Contact contactInvolved) {
        this.contactInvolved = contactInvolved;
    }

   @Override
    public int compareTo(String comparedName) {
        //in the requierment spcify that it's unique by phone number and name
        return eventTitle.compareTo(comparedName);
        }
    
    public int compareTo(Event comparedEvent) {
      
        return eventTitle.compareTo(comparedEvent.getEventTitle());

        }

    
}