package structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> implements
		BSTInterface<T> {
	protected BSTNode<T> root;

	public boolean isEmpty() {
		return root == null;
	}

	public int getSize() {
		// TODO
		return size(root);
	}
	private int size(BSTNode<T> node){
		if (node == null){
			return 0;
		}
		else{
			return 1 + size(node.getLeft()) + size(node.getRight());
		}
	}

	public boolean contains(T t) {
		// TODO
		return getElement(t) != null;
	}

	public boolean removeElement(T t) {
		// TODO
		if (t == null) {
			throw new NullPointerException();
		}
		if(contains(t)){
			root = removeAlg(root, t);
		}
		return contains(t);
	}
	private BSTNode<T> removeAlg(BSTNode<T> node, T key){
		 //tree is empty
		 if (node == null){
			 return node;
		 }
		 //traverse the tree
		 if (key.compareTo(node.getData()) < 0){   //traverse left subtree
			 node.setLeft(removeAlg(node.getLeft(), key));
		 }
		 else if (key.compareTo(node.getData()) > 0){  //traverse right subtree
			 node.setRight(removeAlg(node.getRight(), key));
		 }
		 else {
			 // node contains only one child
			 if (node.getLeft() == null){
				 return node.getRight();
			 }
			 else if (node.getRight() == null){
				 return node.getLeft();
			 }

			 // node has two children;
			 //get inorder successor (min value in the right subtree)
			 node.setData(minval(node.getRight()));
			 // Delete the inorder successor
			 node.setRight(removeAlg(node.getRight(), node.getData()));
		 }
		 return root;
	 }
	 private T minval(BSTNode<T> node){
        //initially minval = root
        T minval = node.getData();
        //find minval
        while (node.getLeft() != null){
            minval = node.getLeft().getData();
            node = node.getLeft();
        }
		return minval;
	}


	public T getHighestValueFromSubtree(BSTNode<T> node) {
		// node must not be null
		if(node == null){
			throw new NullPointerException();
		}
		if (node.getRight() == null) {
			return node.getData();
		}
		else{
			return getHighestValueFromSubtree(node.getRight());
		}
	}

	public T getLowestValueFromSubtree(BSTNode<T> node) {
		// TODO
		while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return (node.getData());
	}

	private BSTNode<T> removeRightmostFromSubtree(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getLeft();
		} else {
			node.setRight(removeRightmostFromSubtree(node.getRight()));
			if (node.getRight() != null){
				node.getRight().setParent(node);
			}
			return node;
		}
	}

	public BSTNode<T> removeLeftmostFromSubtree(BSTNode<T> node) {
		// TODO
		if (node.getLeft() ==  null){
			return node.getRight();
		}
		else{
			node.setLeft(removeLeftmostFromSubtree(node.getLeft()));
			if(node.getLeft() != null){
				node.getLeft().setParent(node);
			}
			return node;
		}
	}

	public T getElement(T t) {
		// TODO
		if(t == null){
			throw new NullPointerException();
		}
		return searchAlg(t, root);
	}
	private T searchAlg(T key, BSTNode<T> firstNode){
		if(firstNode == null){
			return null;
		}
		while(firstNode != null){
			if(key.compareTo(firstNode.getData()) == 0){
				return firstNode.getData();
			}
			else if(key.compareTo(firstNode.getData()) < 0){
				firstNode = firstNode.getLeft();
			}
			else{
				firstNode = firstNode.getRight();
			}

		}
		return null;
	}

	public void addElement(T t) {
		// TODO
		if(t == null){
			throw new NullPointerException();
		}
		root = insertAlg(root, new BSTNode<T>(t, null, null));
	}

	private BSTNode<T> insertAlg(BSTNode<T> firstNode,BSTNode<T> elem){
		if(firstNode == null){
			return elem;
		}
		if (elem.getData().compareTo(firstNode.getData()) <= 0){
            firstNode.setLeft(insertAlg(firstNode.getLeft(), elem));
		}
        else if (elem.getData().compareTo(firstNode.getData()) > 0)
            firstNode.setRight(insertAlg(firstNode.getRight(), elem));
			return firstNode;
	}



	@Override
	public T getMin() {
		// TODO
		if(root == null){
			return null;
		}
		else{
			return min(root);
		}
	}

	private T min(BSTNode<T> node){
		if(node.getLeft() == null){
			return node.getData();
		}
		else{
			return min(node.getLeft());
		}

	}


	@Override
	public T getMax() {
		// TODO
		if(root == null){
			return null;
		}
		else{
			return max(root);
		}
	}

	private T max(BSTNode<T> node){
		if(node.getRight() == null){
			return node.getData();
		}
		else{
			return max(node.getRight());
		}
	}

	@Override
	public int height() {
		// TODO
		return getHeight(root);
	}
	private int getHeight(BSTNode<T> node){
		int leftHeight = 0;
		int rightHeight = 0;
		if(node == null){
			return -1;
		}
		else{
			leftHeight = getHeight(node.getLeft());
			rightHeight = getHeight(node.getRight());
			return 1 + Math.max(leftHeight, rightHeight);
		}
	}

	public Iterator<T> preorderIterator() {
		// TODO
		Queue<T> preQueue = new LinkedList<T>();
		preorder(preQueue, root);
		return preQueue.iterator();
	}
	private void preorder(Queue<T> preQueue, BSTNode<T> node) {
		if (node != null) {
			preQueue.add(node.getData());
			preorder(preQueue, node.getLeft());
			preorder(preQueue, node.getRight());
		}
	}


	public Iterator<T> inorderIterator() {
		Queue<T> inQueue = new LinkedList<T>();
		inorder(inQueue, root);
		return inQueue.iterator();
	}

	private void inorder(Queue<T> inQueue, BSTNode<T> node) {
		if (node != null) {
			inorder(inQueue, node.getLeft());
			inQueue.add(node.getData());
			inorder(inQueue, node.getRight());
		}
	}

	public Iterator<T> postorderIterator() {
		// TODO
		Queue<T> posQueue = new LinkedList<T>();
		postorder(posQueue, root);
		return posQueue.iterator();
	}


	private void postorder(Queue<T> posQueue, BSTNode<T> node) {
		if (node != null) {
			postorder(posQueue, node.getLeft());
			postorder(posQueue, node.getRight());
			posQueue.add(node.getData());
		}
	}

	@Override
	public boolean equals(BSTInterface<T> other) {
		// TODO
		return isEqual(root, other.getRoot());
	}
	private boolean isEqual(BSTNode<T> root1, BSTNode<T> root2 ){
		if(root1 == null && root2 == null){
			return true;
		}
		if(root1 == null || root2 == null){
			return false;
		}
		if (!root1.getData().equals(root2.getData())){
			return false;
		}
		return isEqual(root1.getLeft(), root2.getLeft()) && isEqual(root1.getRight(), root2.getRight());
	}

	@Override
	public boolean sameValues(BSTInterface<T> other) {
		// TODO
		Iterator<T> currTree = this.inorderIterator();
		Iterator<T> otherTree = other.inorderIterator();
		while (currTree.hasNext() && otherTree.hasNext())
			if (!currTree.next().equals(otherTree.next()))
				return false;
		return !currTree.hasNext() && !otherTree.hasNext();
	}

	@Override
	public int countRange(T min, T max) {
    	// TODO
		return count(root, min, max);
  }
  private int count(BSTNode<T> node, T min, T max){
	  if( node == null){
		return 0;
	  }
	  if(max.compareTo(min) == 0){
		  return 0;
	  }
	  if(node.getData().compareTo(min) >= 0 && node.getData().compareTo(max) <= 0){
            return 1 + count(node.getLeft(), min, max) +
			           count(node.getRight(), min, max);
	  }
	  else if(node.getData().compareTo(min) < 0){
            return count(node.getRight(), min, max);
	  }
	  else{
		  return count(node.getLeft(), min, max);
	  }
	}





	@Override
	public BSTNode<T> getRoot() {
        // DO NOT MODIFY
		return root;
	}

	public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
		// header
		int count = 0;
		String dot = "digraph G { \n";
		dot += "graph [ordering=\"out\"]; \n";
		// iterative traversal
		Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
		queue.add(root);
		BSTNode<T> cursor;
		while (!queue.isEmpty()) {
			cursor = queue.remove();
			if (cursor.getLeft() != null) {
				// add edge from cursor to left child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getLeft().getData().toString() + ";\n";
				queue.add(cursor.getLeft());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}
			if (cursor.getRight() != null) {
				// add edge from cursor to right child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getRight().getData().toString() + ";\n";
				queue.add(cursor.getRight());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}

		}
		dot += "};";
		return dot;
	}

	public static void main(String[] args) {
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			BSTInterface<String> tree = new BinarySearchTree<String>();
			for (String s : new String[] { "d", "b", "a", "c", "f", "e", "g" }) {
				tree.addElement(s);
			}
			Iterator<String> iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.preorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.postorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();

			System.out.println(tree.removeElement(r));

			iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
		}

		BSTInterface<String> tree = new BinarySearchTree<String>();
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			tree.addElement(r);
		}
		System.out.println(tree.getSize());
		System.out.println(tree.height());
		System.out.println(tree.countRange("a", "g"));
		System.out.println(tree.countRange("c", "f"));


	}
}