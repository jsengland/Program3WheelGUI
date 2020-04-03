package sample;

/**
 * This class is my implementation of the List210 interface.
 * @author William T Krieger
 */
public class MyList {
    private MyNode head;
    private MyNode tail;

    /**
     * Default ctor
     */
    public MyList() {
        head = tail = null;
    }

    /**
     * Copy ctor
     * @param copyMe The List to copy
     */
    public MyList( MyList copyMe) {
        head = tail = null;
        MyNode n = copyMe.head;
        while( n != null) {
            this.addToEnd( n.getValue());
            n = n.next;
        }
    }

    /**
     * Add to end of list; see List210 for more info
     */
    public void addToEnd( String item) {
        MyNode n = new MyNode(item);	// always need a new node to add

        if( head == null) {   // case 1: empty list
            head = n;
            tail = n;
        }
        else {    // case 2: add to end of existing list
            n.prev = tail;
            tail.next = n;
            tail = n;
        }
    }

    /**
     * Add to beginning of list; see List210 for more info
     */
    public void addToBeginning( String item) {
        MyNode n = new MyNode(item);  // add this new node

        if( head == null) {   // case 1: empty list
            head = n;
            tail = n;
        }
        else {      // case 2: add to existing list
            n.next = head;
            head.prev = n;
            head = n;
        }
    }

    /**
     * Get the list item; see List210 for more info
     */
    public boolean get( String item) {
        boolean found = false;

        MyNode n = head;
        while( n!= null) {
            if( item.equals(n.getValue())) {
                found = true;
                break;
            }
            n = n.next;
        }
        return found;
    }

    /**
     * Get the i-th item; see List210 for more info
     */
    public String get( int index) {
        String item = null;	// return item; default is null, not found

        int count = 0;
        MyNode n = head;
        while( n!= null) {
            count++;
            if( count == index) {
                item = n.getValue();
                break;
            }
            n = n.next;
        }
        return item;
    }

    /**
     * Get the first item; see List210 for more info
     */
    public String getFirst() {
        String s = null;
        if( head != null) {
            s = head.getValue();
        }
        return s;
    }

    /**
     * Get last item; see List210 for more info
     */
    public String getLast() {
        String s = null;
        if( tail != null) {
            s = tail.getValue();
        }
        return s;
    }

    /**
     * Remove an item; see List210 for more info
     */
    public boolean remove( String item) {
        boolean found = false;

        MyNode n = head;
        while( n != null) {
            if( item.equals( n.getValue())) {
                removeNode( n);
                found = true;
                break;
            }
            n = n.next;
        }
        return found;
    }

    /**
     * Remove the i-th item; see List210 for more info
     */
    public boolean remove( int i) {
        boolean found = false;

        MyNode n = head;
        int count = 0;
        while( n != null) {
            count++;
            if( count == i) {
                removeNode( n);
                found = true;
                break;
            }
            n = n.next;
        }
        return found;
    }

    /**
     * Clear list of all items; see List210 for more info
     */
    public void clear() {
        head = null;
        tail = null;
    }

    /**
     * Returns the number of items; see List210 for more info
     */
    public int numItems() {
        int count = 0;
        MyNode n = head;
        while( n != null) {
            count++;
            n = n.next;
        }
        return count;
    }

    /**
     * Reverse order of items; see List210 for more info
     */
    public void reverse() {
        if( isEmpty()) { return; }	// guard

        MyNode n = head;
        MyNode tmp;
        while( n != null) {
            tmp = n.next;    // save next node
            n.next = n.prev;   // swap next and prev values
            n.prev = tmp;
            n = tmp;
        }

        // swap head and tail
        tmp = head;
        head = tail;
        tail = tmp;
    }

    /**
     * Returns string for list; items are comma-separated (CSV).
     * An empty list returns and empty string; null is never returned.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        MyNode n = head;
        while( n != null) {
            if( ! first) { sb.append( ", "); }
            sb.append( n.getValue());

            first = false;
            n = n.next;
        }
        return sb.toString();
    }

    /**
     * Returns true if list is empty; this should be added to List210.
     */
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Removes a node from the list.
     * @param n The node to rm
     */
    private void removeNode( MyNode n) {
        if( n == head) {    // case 1: remove head of list
            head = n.next;
            if( head != null) { head.prev = null; }
        }
        else if( n == tail) {   // case 2: remove tail of list
            tail = n.prev;
            if( tail != null) { tail.next = null; }
        }
        else {    // case 3: remove node in middle of list
            MyNode before = n.prev;
            before.next = n.next;
            MyNode after = n.next;
            after.prev = n.prev;
        }
    }
}