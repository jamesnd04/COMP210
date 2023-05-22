package assn04;


import java.util.LinkedList;
import java.util.Queue;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {
	private T _element;
	private BST<T> _left;
	private BST<T> _right;

	public NonEmptyBST(T element) {

		_left = new EmptyBST<T>();
		_right = new EmptyBST<T>();
		_element = element;
	}

	// TODO: insert
	@Override
	public BST<T> insert(T element) {
		boolean isGreater = element.compareTo(_element) > 0;  // creates a boolean variable to check if element is greater than the one in the BST
			if(isGreater){
				_right = _right.insert(element);  // recursive algorithmn, assuming that the insert function works how it was defined
			}
			else{
			_left = _left.insert(element);
			}
		return this;
	}

	// TODO: remove
	@Override
	public BST<T> remove(T element) {
		if (element.compareTo(_element) == 0){
			if(_left.isEmpty() && _right.isEmpty()){
				return new EmptyBST<T>();
			}
			else{
				if (_left.isEmpty()){
					return _right;
				}
				else if(_right.isEmpty()){
					return _left;
				}
				T low = _right.getElement();  // if there is a left and right, the right value must now become the element
				_right = _right.remove(low);
				_element = low; // assigns element to low, the left should still be less than the element

			}
		}
		if (element.compareTo(_element) > 0){
			_right = _right.remove(element);
		}
		else{
			_left = _left.remove(element);
		}


		return this;
	}

	// TODO: printPreOrderTraversal
	@Override
	public void printPreOrderTraversal() {  // prints the top(highest) of the BST currently being recursed through
		System.out.print(getElement() + " ");
		getLeft().printPreOrderTraversal();
		getRight().printPreOrderTraversal();
	}

	// TODO: printPostOrderTraversal
	@Override
	public void printPostOrderTraversal() {  // this is kind of the opposite of PreOrder, code returns the last lowest value into the print statement then recurses
		getLeft().printPostOrderTraversal();
		getRight().printPostOrderTraversal();
		System.out.print(getElement() + " ");
	}

	// TODO: printBreadthFirstTraversal
	@Override
	public void printBreadthFirstTraversal() {
		Queue<BST<T>> queue = new LinkedList<BST<T>>();
		queue.add(this);
		while (!queue.isEmpty()) {
			BST<T> curr = queue.poll();
			System.out.print(curr.getElement() + " ");
			boolean notLeftEmpty = !curr.getLeft().isEmpty();
			if (notLeftEmpty) {
				queue.add(curr.getLeft());
			}
			boolean notRightEmpty = !curr.getRight().isEmpty();
			if (notRightEmpty) {
				queue.add(curr.getRight());
			}
		}
	}

	// GetHeight of A Tree

	@Override
	public int getHeight() {
		   return Math.max(_left.getHeight(), _right.getHeight())+1;
	}


	@Override
	public BST<T> getLeft() {
		return _left;
	}

	@Override
	public BST<T> getRight() {
		return _right;
	}

	@Override
	public T getElement() {
		return _element;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

}
