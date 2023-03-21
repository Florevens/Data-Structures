
package app;


import java.util.Iterator;

public class RecursiveList<T> implements ListInterface<T> {

  private int size;
  private Node<T> head = null;

  public RecursiveList() {
    this.head = null;
    this.size = 0;
  }

  public RecursiveList(Node<T> first) {
    this.head = first;
    this.size = 1;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void insertAtBeginning(T elem) {
      //TODO: Implement this method.
      if(elem == null){
        throw new NullPointerException("Must input an element");
      }
      Node<T> newnode = new Node<T>(elem,head);
      head = newnode;
      size++;
      }

  @Override
  public void insertAtEnd(T elem) {
      //TODO: Implement this method.
      if(elem == null){
        throw new NullPointerException("Must input an element");
      }

      if(size == 0){
        insertAtBeginning(elem);
      }
      else{
        insertHelper(size-1,0,elem,head);
      size++;
      }
    }


  @Override
  public void insertAt(int index, T elem) {
      //TODO: Implement this method.
      if(elem == null){
        throw new NullPointerException("Must input an element");
      }
      if(index < 0 || index > size){
        throw new IndexOutOfBoundsException("Index in not inbound of list");
      }
      if(index == 0){
        insertAtBeginning(elem);
      }
      else{
       insertHelper(index-1, 0, elem, head);
        size++;

      }
    }
    private void insertHelper(int i, int count, T elem, Node<T> node ){
      if(size == 0){

      }
      if(i == count && node.getNext()==null){
        Node<T> node2 = new Node<T>(elem,null);
        node.setNext(node2);
      }
      if(i==count && node.getNext() != null){
        Node<T> node2 = new Node<T>(elem,node.getNext());
        node.setNext(node2);
      }
      else{
        insertHelper(i, count+1, elem, node.getNext());
      }
    }


  @Override
  public T removeFirst() {
    T removedItem = null;
    if(size == 0){
      throw new IllegalStateException("The list is empty");
    }
    removedItem = head.getData();
    head = head.getNext();
      //TODO: Implement this method.
      size--;

    return removedItem;
  }

  @Override
  public T removeLast() {
    T removedItem = null;
    if(size == 0){
      throw new IllegalStateException("The list is empty");
    }
    if(size == 1){
      removedItem = removeFirst();
    }
    else{
    removedItem = removeHelper(size-2,0,head);
    size--;
    }
      //TODO: Implement this method.


    return removedItem;
  }

  @Override
  public T removeAt(int i) {
    T removedItem = null;
    if(i < 0 || i >= size){
      throw new IndexOutOfBoundsException("Index in not inbound of list");
    }
    if(i==0){
      removedItem = removeFirst();
    }
    else{
    removedItem = removeHelper(i-1, 0, head);
    size--;
    }

    return removedItem;
  }
  private T removeHelper(int i,int count, Node<T> node){
    if(node == null){
      return null;
    }
    T elem = null;
    //delete last element
    if(i==count){
     if(node.getNext().getNext() == null){
      elem = node.getNext().getData();
      node.setNext(null);
    }
    if(node.getNext()!=null){
      elem = node.getNext().getData();
      node.setData(node.getData());
      node.setNext(node.getNext().getNext());
    }
  }
      removeHelper(i, count+1,node.getNext());
      return elem;
    }



  @Override
  public T getFirst() {
    T elem  = null;
    if(size == 0){
      throw new IllegalStateException("The list is empty");
    }
    elem = head.getData();


    return elem;
  }

  @Override
  public T getLast() {
    T elem = null;
    if(size == 0){
      throw new IllegalStateException("The list is empty");
    }
    if(size == 1){
      return getFirst();
    }

    elem = getHelper(size-2, 0, head);
      //TODO: Implement this method.

    return elem;
  }

  @Override
  public T getAt(int i){
    T elem = null;
  if(i < 0 || i >= size){
    throw new IndexOutOfBoundsException("Index in not inbound of list");
  }
  if(i == 0){
    return getFirst();
  }
  else{
  elem = getHelper(i-1,0,head);
  }
  return elem;
}
  private T getHelper(int index, int count, Node<T> node){
    if(node == null){
      return null;
    }
    T elem = null;
    if(index == 0){
      return head.getNext().getData();
    }
    //Getting last element:
    if(index==count){
      if(node.getNext().getNext() == null){
       elem = node.getNext().getData();
     }

      if(node.getNext() == null){
       elem = node.getData();
    }
    if(node.getNext() != null){
      elem = node.getNext().getData();
    }

    }

    else{
    elem = getHelper(index, count+1,node.getNext());
    }

    return elem;
  }



  @Override
  public void removeElement(T elem) {
    if(elem == null){
      throw new NullPointerException("Must input the corresponding element");
    }
    if(head == null){
      throw new ItemNotFoundException("This List is empty");
    }

    if(head.getData().equals(elem)){
      removeFirst();
    }
    else{
      removeRecurr(elem, head);
      size--;
  }
}
  private T removeRecurr(T elem, Node<T> node){
    if(node.getNext() == null){
      throw new ItemNotFoundException("This item is not int the list");
    }
    if(node.getNext().equals(elem)){
      elem = node.getNext().getData();
      node.setNext(node.getNext().getNext());
    }
     else{
      removeRecurr(elem, node.getNext());
    }
    return elem;

  }
     //TODO: Implement this method.

  @Override
  public int indexOf(T elem) {
    if(elem == null){
      throw new NullPointerException("Must input the corresponding element");
    }
    else{
      return indexOfHelper(elem, 0, head);
      //TODO: Implement this method.
    }
  }
  private int indexOfHelper(T elem, int index, Node<T> node){
    if(node == null){
      return -1;
    }
    else if(node.getData().equals(elem)){
        return index;
      }
    else{
     return indexOfHelper(elem, index+1,node.getNext());
    }
  }



  @Override
  public boolean isEmpty(){
    return size == 0;
  }

  public Iterator<T> iterator() {
    Iterator<T> iter = null;
    iter = new LinkedNodeIterator<T>(head);
		return iter;
  }
}