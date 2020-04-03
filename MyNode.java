package sample;

/**
 * Node class for the MyList linked list
 * Public attributes are used for next and prev pointers.
 * This is OK as nodes are only accessed inside MyList.
 * @author William T Krieger
 */
public class MyNode{
    /** value attribute is immutable */
    private String value;
    /** next node in the list */
    public MyNode next;
    /** previous node in the list */
    public MyNode prev;

    /**
     * ctor requires a value, which is immutable
     * @param value The node's value
     */
    public MyNode( String value) {
        this.value = value;
    }

    /**
     * Getter for the value attribute.
     * @return Returns the node's value
     */
    public String getValue() {
        return value;
    }
}
