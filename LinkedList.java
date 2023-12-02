
public class LinkedList<T> {
    private Node<T> Head;
    private Node<T> current;
    private int size;

    public LinkedList() {
        Head=current=null;
        size = 0;
    }

    public boolean isEmpty(){
        return Head==null;
    }

    public boolean isFull(){
        return false;
    }

    public boolean last(){
        if (current == null)
            return true; 
    
    return current.next == null;    
    }

    public void FindFirst(){
    if (Head != null)
        current = Head;
    else 
        return;
    }

    public void FindNext() {
    if (current != null && current.next != null)
        current = current.next;
    else
        return;
    }

    public void update(T d) {
    if (current != null)
        current.data = d;
    }

    public T retrieve(){
    if (current != null)
        return current.data; 
    else
        return null;    
    }

    public void insert(T d){
        Node<T> p=new Node<T>(d);
        if(Head==null){
            Head=p;
            current=p;
        }
        else{
            p.next=current.next;
            current.next=p;
            current=p;
        }
    }

    public void remove(){
        if(current==Head){
            Head = Head.next;
            current = current.next;
        }

        else{
            Node<T>p=Head;
            while(p.next!=current){
                p=p.next;
            }

            p.next = current.next;
            if(current.next!=null)
                current=current.next;
            else
                current=Head;

        }
    }

    public boolean search(T d) {
    Node<T> temp = Head;
    while (temp != null) {
        if (temp.data.equals(d))
            return true;
        
        temp = temp.next;
    }
    return false;
    }

    public void dispaly(){
        Node cur=Head;
        while (cur!=null){
            System.out.println(cur.data + "");
            cur=cur.next;
        }
    }

    public void AddSorted(T d) {
    Node<T> newNode = new Node<T>(d);

    if (Head == null) {
        Head = newNode;
        current = newNode;
    } 
    else {
        if (((Contact)d).compareTo((Contact)Head.data) < 0) {
            newNode.next = Head;
            Head = newNode;
        } else {
            Node<T> previous = null;
            Node<T> current = Head;

            while (current.next != null && ((Contact) d).compareTo((Contact) current.data) >= 0) {
                previous = current;
                current = current.next;
            }

            newNode.next = current;
            previous.next = newNode;
        }
    }

    size++;

    }
    
 public void AddSortedEvent(T d) {
    Node<T> newNode = new Node<T>(d);

    if (Head == null) {
        Head = newNode;
        current = newNode;
    } 
    else {
        if (((Event)d).compareTo((Event)Head.data) < 0) {
            newNode.next = Head;
            Head = newNode;
        } else {
            Node<T> previous = null;
            Node<T> current = Head;

            while (current.next != null &&
                    ((Event) d).compareTo((Event) 
                            current.data) >= 0) {
                previous = current;
                current = current.next;
            }

            newNode.next = current;
            if(previous != null)
                previous.next = newNode;
        }
    }

    size++;

    }
    
}