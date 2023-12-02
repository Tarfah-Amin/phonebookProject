
import java.util.Stack;//I wii add the implementation of the stack later+we can git rid of it so choose what is more sutable:)
import java.time.LocalTime;

public class Phonebook {

    static BST<Contact> allContactsBST;// should it be static?
    static LinkedList<Event> EventList;

    public Phonebook() {
        allContactsBST = new BST<Contact>();
        EventList = new LinkedList<Event>();
    }

    /*--------------------------------------------------------
                     All add methodes
                   [sorted,no repeted ]
   --------------------------------------------------------*/
    public boolean checkBeforeAdd(Contact contact) {
        if (allContactsBST.empty()) {
            return false;
        }

        Stack<BSTNode> s = new Stack<BSTNode>();
        BSTNode<Contact> curr = allContactsBST.root;

        // Traverse the tree in order
        while (curr != null || s.size() > 0) {

            // Reach the left most Node of the
            // curr Node
            while (curr != null) {
                // Place pointer to a tree node on
                // the stack before traversing
                // the node's left subtree
                s.push(curr);
                curr = curr.left;
            }

            // Current must be NULL at this point
            curr = s.pop();

            //visit the node
            if ((allContactsBST.retrieve().getName().equals(contact.getName()))
                    || (allContactsBST.retrieve().getPhoneNumber().equals(contact.getPhoneNumber()))) {
                return true;
            }

            // Now, it's right subtree's turn
            curr = curr.right;
        }
        return false;

    }

    public void addContact(Contact contact) {
//      boolean found = checkBeforeAdd(contact);
//        if (!found) {
//            String name = contact.getName();
//            allContactsBST.insert(name,contact);
//        }
//////////////////////////////////////////////////////choose what is sutable more
        boolean insertedName = false;
        boolean phoneExist = allContactsBST.isPhoneExist(contact.getPhoneNumber());
        if (phoneExist) {
            System.out.println("the given contact has phone Exist before cannot add " + contact.getName());

        } else {
            insertedName = allContactsBST.insert(contact.getName(), contact);
            if (!insertedName) {
                System.out.println("the given contact has name Exist before cannot add" + contact.getName());
            }
        }

    }

    public void addEvent(Event e) {
        Event found = searchEventTitle(e.getEventTitle());//she will add it 
        if (found == null) {
            EventList.AddSortedEvent(e);
        }

    }

    /*--------------------------------------------------------
                   All Delete methodes 
    by name and phone since they both unique 
   --------------------------------------------------------*/
    public void deleteContact(String n) {
        if (allContactsBST.empty()) {
            System.out.println("Empty List can not delete");
            return;
        }
        LinkedList<Event> listContact = new LinkedList<>();////lمانحدد نوع ؟
        boolean found = allContactsBST.findKey(n);
        if (!found) {
            System.out.println("can not be deleted bec it is not exist");
            return;
        }
        listContact = allContactsBST.retrieve().getRelatedEvent();//See this razan---what is this method? getContact_events()
        DeleteAllEventsWithContact(n, listContact);
        boolean deleted = allContactsBST.remove_key(n);
        if (deleted) {
            System.out.println(n + " contact deleted");
        } else {
            System.out.println("not deleted");
        }
    }

    public void DeleteAllEventsWithContact(String t, LinkedList<Event> L) {

        while (!L.isEmpty()) {
            String AllEventTitel = L.retrieve().getEventTitle();
            DeletEvent(AllEventTitel, t);
            L.remove();

        }

    }

    public void DeletEvent(String Titel, String n) {
        LinkedList<Contact> contactWithEvent = GetContactInEvent(Titel); //See this razan---- you should GetContactInEvent return BST do modify this method
        contactWithEvent.FindFirst();
        while (!contactWithEvent.isEmpty() && !contactWithEvent.last()) {
            if (contactWithEvent.retrieve().getName().equals(n)) {
                contactWithEvent.remove();
                break;
            }

            contactWithEvent.FindNext();

        }
        if (!contactWithEvent.isEmpty() && contactWithEvent.retrieve().getName().equals(n)) {
            contactWithEvent.remove();
        }

        if (!contactWithEvent.isEmpty()) {
            return;
        }
        if (EventList.isEmpty()) {
            return;
        }
        EventList.FindFirst();
        while (!EventList.last()) {//// checkkkkk
            if (EventList.retrieve().getEventTitle().equals(Titel)) {
                EventList.remove();
                System.out.println(Titel + " event deleted");
                return;
            }
            EventList.FindNext();
        }
        if (EventList.retrieve().getEventTitle().equals(Titel)) {
            EventList.remove();
            System.out.println(Titel + " event deleted");
            return;

        } else {
            System.out.println("event cant delet (it doesnt exist)");
        }

    }

    /*--------------------------------------------------------
                   All search methodes dob
   --------------------------------------------------------*/
    public Contact searchName(String n) {

        if (allContactsBST.empty()) {

            return null;

        }

        boolean found = allContactsBST.findKey(n);

        if (found) {

            return allContactsBST.retrieve();
        }

        return null;

    }

    public static Event searchEventTitle(String n) {
        if (EventList.isEmpty()) {
            return null;
        }
        EventList.FindFirst();
        while (!EventList.last()) {

            if (EventList.retrieve().getEventTitle().equals(n)) {
                return EventList.retrieve();
            }
            EventList.FindNext();
        }
        if (EventList.retrieve().getEventTitle().equals(n)) {
            return EventList.retrieve();
        }
        return null;
    }

    public LinkedList<Contact> SearchFname(String n) {
        return allContactsBST.SearchFname(n);
    }

    public LinkedList<Contact> SearchEmail(String e) {
        return allContactsBST.SearchEmail(e);
    }

    public LinkedList<Contact> SearchDOB(String d) {
        return allContactsBST.SearchDOB(d);
    }

    public LinkedList<Contact> searchAddress(String address) {
        return allContactsBST.SearchAddress(address);
    }

    public Contact SearchPhone(String p) {
        return allContactsBST.searchByPhoneNumber(p);
    }

    /*--------------------------------------------------------
                   All print methodes  && get 
    
    
   --------------------------------------------------------*/
    //-------------------------------------------------------------------change print methods------------------------------//
//    public void printName(BSTNode<Contact> node) {//complixity =O(n)
//        
//        if (node == null){
//            System.out.println("empty contact ");
//            return;
//        }
//        // Traverse left subtree
//        printName(node.left);
// 
//        // Visit node
//        System.out.println(node.data.getName());
// 
//        // Traverse right subtree
//        printName(node.right);//it's a reqursive method
//        
//    }
    public void printName(LinkedList<Contact> list) {
        if (list.isEmpty()) {
            System.out.println("empty contact ");
            return;
        }
        list.FindFirst();
        while (!list.last()) {
            System.out.println(list.retrieve().getName());
            list.FindNext();

        }
        System.out.println(list.retrieve().getName());
    }

    public static void printBSTofallcontacts(BSTNode<Contact> node) {

        if (node == null) {
            System.out.println("empty tree ");
            return;
        }
        // Traverse left subtree
        printBSTofallcontacts(node.left);

        // Visit node
        System.out.println(node.data.toString());

        // Traverse right subtree
        printBSTofallcontacts(node.right);//it's a reqursive method
    }

    public static void printLinkedListofallcontacts(LinkedList<Contact> L) {

        if (L.isEmpty()) {
            System.out.println("empty List");
        } else {
            L.FindFirst();
            while (!L.last()) {
                System.out.println(L.retrieve().toString());
                L.FindNext();
            }
            System.out.println(L.retrieve().toString());
        }
    }

    public void printLinkedListOfEvents(LinkedList<Event> List) {
        if (List.isEmpty()) {
            System.out.println("empty List");
        } else {
            List.FindFirst();
            while (!List.last()) {
                System.out.println(List.retrieve());
                System.out.println("this event has the these contacts :");

                printName(List.retrieve().contactInEvent);
                System.out.println("");
                List.FindNext();
            }
            System.out.println(List.retrieve());
            System.out.println("this event has the these contacts");
            printName(List.retrieve().contactInEvent);

        }
    }

    public LinkedList<Event> GetEventsInContact(String n) {
        Contact EventGetInContact = searchName(n);

        if (EventGetInContact != null) {
            return EventGetInContact.getRelatedEvent();
        }

        return new LinkedList<Event>();
    }

    public LinkedList<Contact> GetContactInEvent(String n) {
        Event ContactGetInEvent = searchEventTitle(n);
        if (ContactGetInEvent != null) {
            return ContactGetInEvent.getContactInEvent();
        }

        return new LinkedList<Contact>();
    }

    public static void PrintEvenInfo(String title) {
        Event t = searchEventTitle(title);//she will add it
        if (t == null) {
            System.out.println("no event with title +  " + title);
            return;
        } else {
            System.out.println(t.toString());

        }
    }

    /*--------------------------------------------------------
                  Conflict && scheduleEvent
    
    
   --------------------------------------------------------*/
    public boolean isConflict(Event e, Contact c) {

        LinkedList<Event> ss = c.getRelatedEvent();//return list

        LocalTime eStart;
        LocalTime cStart;
        LocalTime eEnd;
        LocalTime cEnd;

        boolean notEnd = true;// to continue performing the loop till the last element on the list
        ss.FindFirst();
        if (ss.isEmpty()) {
            return false;
        }
        while (notEnd) {//compare all the event in contact to our event
            eStart = e.getStartTime();
            cStart = c.getRelatedEvent().retrieve().getStartTime();
            eEnd = e.getEndTime();
            cEnd = c.getRelatedEvent().retrieve().getEndTime();

            if (ss.last())//it will apply the code to the last element then stop 
            {
                notEnd = false;
            }
            if (e.getEventDate().compareTo(ss.retrieve().getEventDate()) == 0) {//the case on the same day

                if (eStart == cStart)//start at the same time(case1)
                {
                    return true;//there is conflict
                }
                if (eStart.isBefore(cStart))//if e start before c event
                {
                    if (eEnd.isAfter(cStart))//e ends durring c(case2&3)
                    {
                        return true;//there is conflict                                     
                    }
                }
                if (eStart.isAfter(cStart))//e start after c event
                {

                    if (eStart.isBefore(cEnd))//e starts during c event(case4 and5)
                    {

                        return true;////there is conflict
                    }                //the case didn't written is when both events start and ends at diffrent times== no conlict         
                }
            }//end of if in the same day
            ss.FindNext();
        }//end of while
        return false;
    }

    public void scheduleEvent(Event e, String contactName) {

        Contact this_contact = searchName(contactName);
        if (this_contact == null) {
            System.out.println("can not schedule this event bec this contact not Exist" + contactName);
            return;
        }
        boolean IsConflictCheck = isConflict(e, this_contact);

        if (!IsConflictCheck) {
            System.out.println("schedulling contact:" + this_contact.getName() + "'" + e.getEventTitle());
            this_contact.relatedEvent.AddSortedEvent(e);
            e.contactInEvent.AddSorted(this_contact);
            addEvent(e);
            e.setContactInvolved(this_contact);
            e.setContactName(contactName);
            this_contact.relatedEvent.AddSortedEvent(e);//make sure if this ok 
            this_contact.getRelatedEvent().retrieve().allEvents.AddSortedEvent(e);//make sure if this ok 
            return;

        }//if end
        else {

            System.out.println(" there is conflict event title= " + e.getEventTitle() + "\ncontact =" + contactName);

        }
    }

}
