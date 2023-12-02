public class BST<T> {

    BSTNode<T> root, current;

    /**
     * Creates a new instance of BST
     */
    public BST() {
        root = current = null;
    }

    public boolean empty() {
        return root == null;
    }

    public void clear() {
        root = current = null;
    }

    public boolean full() {
        return false;
    }

    public T retrieve() {
        return current.data;
    }

    public boolean findKey(String k) {
        BSTNode<T> p = root;
        while (p != null) {
            current = p;
            if (k.compareToIgnoreCase(p.key) == 0) {
                return true;
            } else if (k.compareToIgnoreCase(p.key) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return false;
    }

    public boolean insert(String k, T val) {

        if (root == null) {
            current = root = new BSTNode<T>(k, val);
            return true;
        }
        BSTNode<T> p = current;
        if (findKey(k)) {
            current = p;
            return false;
        }

        BSTNode<T> tmp = new BSTNode<T>(k, val);
        if (k.compareToIgnoreCase(current.key) < 0) {

            current.left = tmp;
        } else {
            current.right = tmp;
        }
        current = tmp;
        return true;

    }

    public boolean remove_key(String tkey) {
        Boolean removed = new Boolean(false);
        BSTNode<T> p;
        p = remove_aux(tkey, root, removed);
        current = root = p;
        return removed;
    }

    private BSTNode<T> remove_aux(String key, BSTNode<T> p, boolean flag) {//I change it to boolean isted of BooleanWrper beacuse it's cause an error can't find symbol
        BSTNode<T> q, child = null;
        if (p == null) {
            return null;
        }
        if (key.compareToIgnoreCase(p.key) < 0) {//less than
            p.left = remove_aux(key, p.left, flag); //go left
        } else if (key.compareToIgnoreCase(p.key) > 0) {//more than
            p.right = remove_aux(key, p.right, flag); //go right
        } else { // key is found
            flag = true;//so this is changed accordingly
            if (p.left != null && p.right != null) { //two children
                q = find_min(p.right);
                p.key = q.key;
                p.data = q.data;
                p.right = remove_aux(q.key, p.right, flag);
            } else {
                if (p.right == null) //one child
                {
                    child = p.left;
                } else if (p.left == null) //one child
                {
                    child = p.right;
                }
                return child;
            }
        }
        return p;
    }

    private BSTNode<T> find_min(BSTNode<T> p) {
        if (p == null) {
            return null;
        }
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    public boolean update(String key, T data) {
        remove_key(current.key);
        return insert(key, data);
    }
    //------------------------------in order traversal-------------------------------------//
    public void inOrder(){
        inOrder((BSTNode<Contact>) root);
    } 
    private void inOrder(BSTNode<Contact> p){
        if(p==null)
            return;
        inOrder(p.left);
        System.out.println(p.data.toString());
        inOrder(p.right);
    }
    
    public boolean isPhoneExist(String phone) {
        if (root == null) {
            return false;
        } else {
            return checkPhoneInOrder((BSTNode<Contact>) root, phone);
        }
    }

    private boolean checkPhoneInOrder(BSTNode<Contact> p, String phone) {
        if (p == null) {
            return false;
        }
        boolean ExistInLeft = checkPhoneInOrder(p.left, phone);
        if (ExistInLeft) {
            return true;
        }

        if (p.data.getPhoneNumber().equals(phone)) {
            return true;
        }
        return checkPhoneInOrder(p.right, phone);
    }

    //------------------------------search methods-------------------------------------//
    public LinkedList<Contact> SearchFname(String fname) {

 

        LinkedList<Contact> res = new LinkedList<Contact>();

        if (root == null) {

            return res;

        }

        RecSearchByFirstName(root, res, fname);

        return res;

    }



    
    private void RecSearchByFirstName(BSTNode<T> p, LinkedList<Contact> res, String fname) {

        if (p == null) {

            return;

        }

        RecSearchByFirstName(p.left, res, fname);

        String CurFname = p.key;

        String FirstName = CurFname.substring(0, CurFname.indexOf(" "));

 

        if (FirstName.equalsIgnoreCase(fname)) {

 

            res.insert((Contact) p.data);}

            RecSearchByFirstName(p.right, res, fname);

        }
    ////////

public Contact searchByPhoneNumber(String phoneNumber) {
    if (root == null) {
        return null;
    }

    return recSearchByPhoneNumber(root, phoneNumber);
}

private Contact recSearchByPhoneNumber(BSTNode<T> p, String phoneNumber) {
    if (p == null) {
         return null;
    }
    else{
  recSearchByPhoneNumber(p.left,  phoneNumber);
    Contact contact = (Contact) p.data;

  
   if (contact.getPhoneNumber().equals(phoneNumber)) {

 

            return contact;}

            recSearchByPhoneNumber(p.right,  phoneNumber);
            
    }
    return null ;
}

    ///
    public LinkedList<Contact> SearchEmail(String maill) {

        LinkedList<Contact> res = new LinkedList<Contact>();

        if (root == null) {

            return res;

        }

        RecSearchByEmail(root, res, maill);

        return res;

    }
    
    private void RecSearchByEmail(BSTNode<T> p, LinkedList<Contact> res, String Email) {

        if (p == null) {

            return;

        }

         RecSearchByEmail(p.left, res, Email);

        Contact currEmail = (Contact) p.data;

        String Emailofcurr = currEmail.getEmail();

 

        if (Emailofcurr.equalsIgnoreCase(Email)) {

 

            res.insert(currEmail);}

            RecSearchByFirstName(p.right, res, Email);

        }
    
    public LinkedList<Contact> SearchDOB(String dob) {

        LinkedList<Contact> res = new LinkedList<Contact>();

        if (root == null) {

            return res;

        }

        RecSearchByDOB(root, res, dob);

        return res;

    }
    
    private void RecSearchByDOB(BSTNode<T> p, LinkedList<Contact> res, String dob) {

        if (p == null) {

            return;

        }

         RecSearchByDOB(p.left, res, dob);

        Contact currDOB = (Contact) p.data;

        String DOB = currDOB.getBirthday();

 

        if (DOB.equals(dob)) {

 

            res.insert(currDOB);}

            RecSearchByDOB(p.right, res, dob);

        }
    
    
    
      public LinkedList<Contact> SearchAddress(String add) {

        LinkedList<Contact> res = new LinkedList<Contact>();

        if (root == null) {

            return res;

        }

        RecSearchByAddress(root, res, add);

        return res;

    }
    
    private void RecSearchByAddress(BSTNode<T> p, LinkedList<Contact> res, String add) {

        if (p == null) {

            return;

        }

         RecSearchByAddress(p.left, res, add);

        Contact curraddress = (Contact) p.data;

        String Address = curraddress.getAddress();

 

        if (Address.equalsIgnoreCase(add)) {

 

            res.insert(curraddress);}

            RecSearchByAddress(p.right, res, add);

        }
}
