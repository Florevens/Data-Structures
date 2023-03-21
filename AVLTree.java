	package structures;

	public class AVLTree <T extends Comparable<T>> implements BSTInterface<T> {
		protected BSTNode<T> root;
		private int size;

		public AVLTree() {
			root = null;
			size = 0;
		}

		public boolean isEmpty() {
			// DO NOT MODIFY
			return root == null;
		}

		public int size() {
			// DO NOT MODIFY
			return size;
		}

		public BSTNode<T> getRoot() {
			// DO NOT MODIFY
			return root;
		}

		public void printTree() {
			System.out.println("------------------------");
			if (root != null) root.printSubtree(0);
		}

		public boolean remove(T element) {
			// Do not need to implement this method.
			return false;
		}

		public T get(T element) {
			// Do not need to implement this method.
			return null;
		}

		public int height() {
			return height(this.root);
		}

		public int height(BSTNode<T> node) {
			// return the node height
			return node != null ? node.getHeight() : -1;
		}

		public void updateHeight(BSTNode<T> node) {
			int leftheight = height(node.getLeft());
			int rightheight = height(node.getRight());
			node.setHeight(Math.max(leftheight,rightheight)+1);
			}

			// TODO: update node height to 1 + the maximal height between left and right subtree




		public int balanceFactor(BSTNode<T> node) {
			if(node == null){
				return 0;
			}
			return height(node.getRight()) - height(node.getLeft());
			// TODO: compute the balance factor by substracting right subtree height by left subtree height
		}

		public BSTNode<T> rotateLeft(BSTNode<T> node) {
			// TODO: implement left rotation algorithm
			BSTNode<T> rightChild = node.getRight();
			if(node.getParent()!=null){
				if(node.getParent().getRight() == node){
					node.getParent().setRight(rightChild);
				}
				else{
					node.getParent().setLeft(rightChild);
				}
			}
			if(rightChild.getLeft()!=null){
				rightChild.getLeft().setParent(node);
			}
			rightChild.setParent(node.getParent());
			node.setRight(rightChild.getLeft());
			rightChild.setLeft(node);
			node.setParent(rightChild);

			updateHeight(node);
			updateHeight(rightChild);

			if(node == root){
				root = rightChild;
			}
			return rightChild;

		}

		public BSTNode<T> rotateRight(BSTNode<T> node) {
			// TODO: implement right rotation algorithm
			BSTNode<T> leftChild = node.getLeft();
			if(node.getParent()!=null){
				if(node.getParent().getLeft() == node){
					node.getParent().setLeft(leftChild);
				}
				else{
					node.getParent().setRight(leftChild);
				}
			}
			if(leftChild.getRight()!=null){
				leftChild.getRight().setParent(node);
			}
			leftChild.setParent(node.getParent());
			node.setLeft(leftChild.getRight());
			leftChild.setRight(node);
			node.setParent(leftChild);

			updateHeight(node);
			updateHeight(leftChild);

			if(node == root){
				root = leftChild;
			}
			return leftChild;
		}

		// When inserting a new node, updating the height of each node in the path from root to the new node.
		// Check the balance factor of each updated height and run rebalance algorithm if the balance factor
		// is less than -1 or larger than 1 with following algorithm
		// 1. if the balance factor is less than -1
		//    1a. if the balance factor of left child is less than or equal to 0, apply right rotation
	    //    1b. if the balance factor of left child is larger than 0, apply left rotation on the left child,
		//        then apply right rotation
		// 2. if the balance factor is larger than 1
		//    2a. if the balance factor of right child is larger than or equal to 0, apply left rotation
	    //    2b. if the balance factor of right child is less than 0, apply right rotation on the right child,
		//        then apply left rotation
		public void add(T t) {
			// TODO
			root = insertHelper(t, root);
			size++;
		}

		private BSTNode<T> insertHelper(T t, BSTNode<T> node){
			if (node == null) {
				node = new BSTNode<T>(t, null, null);
				node.setParent(null);
				return node;
			}
			else if (t.compareTo(node.getData()) < 0){
				BSTNode<T> lchild = insertHelper(t, node.getLeft());
				node.setLeft(lchild);
				lchild.setParent(node);

			}
			else if(t.compareTo(node.getData()) > 0){
				BSTNode<T> rchild = insertHelper(t, node.getRight());
				node.setRight(rchild);
				rchild.setParent(node);
			}
			else{
			return node;
			}

			node.setHeight(1+Math.max( height(node.getLeft()), height(node.getRight()) ));
			int balance = balanceFactor(node);
			if(balance < -1){
				if(balanceFactor(node.getLeft()) <= 0){
					return rotateRight(node);
				}
				if(balanceFactor(node.getLeft()) > 0){
					node.setLeft(rotateLeft(node.getLeft()));
					return rotateRight(node);
				}
			}
			else if(balance > 1){
				if(balanceFactor(node.getRight()) >= 0){
					return rotateLeft(node);
				}
				if(balanceFactor(node.getRight()) < 0){
					node.setRight(rotateRight(node.getRight()));
					return rotateLeft(node);
				}
			}
			return node;
		}
	}
