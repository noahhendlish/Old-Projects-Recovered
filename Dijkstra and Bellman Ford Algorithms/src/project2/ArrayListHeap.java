package project2;

import java.util.ArrayList;


public class ArrayListHeap{

	private ArrayList<Vertex> arraylist;

	public ArrayListHeap(){
		arraylist = new ArrayList<Vertex>();
	}

	public ArrayListHeap(int size){
		arraylist = new ArrayList<Vertex>(size);
	}

	public void insert(Vertex v) {
		// * Always add the node so as to fill a level.
		// * Once the node has been added, traverse up the
		//   tree (using heapifyUp) and swap with parent as
		//   needed to preserve the heap property.
		// Runtime without table doubling of ArrayList: O(log n)  -> n = size()
		// Runtime with table doubling of ArrayList: O(n)

		arraylist.add(v);   // worst case O(n) because of table doubling
		v.indexInHeap=arraylist.size()-1;
		heapifyUp(arraylist.size()-1);
	}

	private void heapifyUp(int node) {
		// Traverse up the tree and swap with parent as
		// needed to preserve the heap property.
		// Runtime is O(height) = O(log n)  because tree is almost complete

		int current = node;
		int parent;
		
		while(current!=0  // not at root
				&& arraylist.get(current).compareTo(
						arraylist.get(parentIndex(current)))<0){  // current element violates
																  // heap order property
			// Swap current element with parent
			parent=parentIndex(current);
			Vertex currentVertex = arraylist.get(current);
			Vertex parentVertex = arraylist.get(parent);
			arraylist.set(current,parentVertex);
			arraylist.set(parent,currentVertex);
			parentVertex.indexInHeap=current;
			currentVertex.indexInHeap=parent;

			//Update current
			current=parent;
		}

	} //method heapifyUp

	public Vertex extractMin() {
		// * Root is the minimum element.
		// * Replace the root with the rightmost leaf (the last value in the array or ArrayList).
		// * Call a routine heapifyDown(0) to reheapify the heap.
		// O(log n) time

		if (isEmpty()) {
			throw new RuntimeException("Attempting to remove minimum from empty heap.");
		}

		Vertex result;
		if (size()>1) {
			// has 2 or more nodes

			result = arraylist.get(0);
			Vertex last = arraylist.remove(size()-1);
			arraylist.set(0,last); // put last element in
			last.indexInHeap=0;
			
			// first position
			heapifyDown(0);
		} else {
			// size()==1
			result = arraylist.remove(0);
		}
		result.indexInHeap=-1; // not in heap
		return result;

	}

	public void decreaseKey(int index, int newValue) {
		// * Decrease dValue of vertex at position "index" in the heap to have the value "newValue"
		// * Call a routine heapifyUp(index) to reheapify the heap.
		// O(log n) time
		Vertex v = arraylist.get(index);
		if(v.dValue < newValue)
			throw new RuntimeException("Attempting to call decreaseKey with larger value. current dvalue="+v.dValue+", newValue="+newValue);
		
		v.dValue = newValue;
		heapifyUp(index);
	}

	private int smallerChild(int node) {
		// Return index of the child with the smaller element,
		// returns -1 if the node has no child
		// O(1) time
		int result;
		int left = leftChildIndex(node);
		int right = rightChildIndex(node);

		if (size()<2 || 2*node >= size()-1) {
			// has no child
			return -1;
		}

		if (2*node+2 < size()) {
			// have both children
			if ( ( arraylist.get(left)).compareTo(
					arraylist.get(right)) < 0) {
				// left is smaller
				result = left;
			} else {
				// right is smaller
				result = right;
			}
		} else  {
			// has only the left child
			result = left;
		}

		return result;
	}

	private void heapifyDown(int i) {
		/* Traverses down the tree.

     The heapifyDown(i) method assumes that the left and right subtrees of position
     i are heaps and that only i might spoil the heap:

		 * If i has no children, return.
		 * Let smallest be the index of the smaller of the left and right children.
		 * If the value at i is less than or equal to the value at smallest, it's a heap so return.
		 * Exchange the value at i with the value at smallest.
		 * Now only the subtree rooted at smallest can be the problem, so call heapifyDown(smallest)
		 */

		int current = i;
		int smallest = smallerChild(i);

		while(smallest != -1 && arraylist.get(smallest).compareTo(arraylist.get(current)) <0){
			// swap smallest with current
			Vertex currentVertex = arraylist.get(current);
			Vertex smallestVertex = arraylist.get(smallest);
			arraylist.set(smallest,currentVertex);
			arraylist.set(current, smallestVertex);
			currentVertex.indexInHeap=smallest;
			smallestVertex.indexInHeap=current;

			current = smallest;
			smallest = smallerChild(current);
		}
	}

	public Vertex findMin() {
		// Runtime O(1)
		if(isEmpty()){
			throw new RuntimeException("Attempting to access minimum in empty heap.");
		}

		return arraylist.get(0); // Minimum in root
	}

	private int parentIndex(int current) {
		// O(1) runtime
		return (current-1)/2;  // integer division rounds down
	}

	private int leftChildIndex(int current) {

		return 2*current+1;
	}

	private int rightChildIndex(int current) {

		return 2*current+2;
	}

	public String toString() {
		String temp = "arraylist.size= " + size() + ", heap:{";
		for (int i = 0; i < size()-1; i++) {
			temp = temp + arraylist.get(i) + ", ";
		}
		if (size() > 0) {
			temp = temp + arraylist.get(size()- 1) + "";
		}
		temp = temp + "}";
		return temp;
	}

	public int size(){
		// O(1) time
		return arraylist.size();
	}

	public boolean isEmpty(){
		// O(1) time
		return arraylist.isEmpty();
	}

}
