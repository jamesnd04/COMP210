package assn05;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MaxBinHeapER  <V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    private List<Prioritized<V,P>> _heap;
    /**
     * Constructor that creates an empty heap of hospital.Prioritized objects.
     */
    public MaxBinHeapER() {
        _heap = new ArrayList<>();
    }

    /**
     * Constructor that builds a heap given an initial array of hospital.Prioritized objects.
     */
    // TODO: overloaded constructor
    public MaxBinHeapER(Prioritized<V, P>[] initialEntries ) {
            _heap = new ArrayList<>();
            for (int i = 0; i < initialEntries.length; i++){
                enqueue(initialEntries[i].getValue(),initialEntries[i].getPriority());
            }
    }

    @Override
    public int size() {
        return _heap.size();
    }

    // TODO: enqueue
    @Override
    public void enqueue(V value, P priority) {
        Patient element = new Patient(value, priority);
            _heap.add(element);
            bubbleUp(_heap.size() - 1);
    }

    // TODO: enqueue
    public void enqueue(V value) {
        Patient element = new Patient(value);
        _heap.add(_heap.size(), element);

    }

    // TODO: dequeue
    @Override
    public V dequeue() {
        if(_heap.size() == 0){
            return null;}
        V value = _heap.get(0).getValue();
        if (_heap.size() == 1) {
                _heap.remove(_heap.get(0));
        }
        else{
                _heap.set(0,_heap.get(_heap.size()-1));
                _heap.remove(size()-1);
                bubbleDown(0);

        }

        return value;
    }

    // TODO: getMax
    @Override
    public V getMax() {
       if(_heap.size() == 0){return null;}
        V curr = _heap.get(0).getValue();
        V temp = null;
        return _heap.get(0).getValue();
       /*for(int i = 0; i < size() - 1; i++){
           if ((int)_heap.get(i).getValue() <= (int)_heap.get(i + 1).getValue()){
               temp = _heap.get(i + 1).getValue();
           }
        }
       curr = temp;
       return curr; */
    }

    @Override
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V,P>[] result = (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }

    public void bubbleUp(int index){

        if (index != 0) {
            Prioritized child = _heap.get(index);
            Prioritized parent = _heap.get((index - 1) / 2);

            if (child.getPriority().compareTo(parent.getPriority()) >= 0) {
                _heap.set(((index - 1) / 2), child);
                _heap.set(index, parent);
                bubbleUp((index - 1) / 2);
            }
        }

    }

    public void bubbleDown(int index){
        Prioritized top = _heap.get(index);
        if(!hasLeftChild(index) && !hasRightChild(index)){
            return;
        }
        else if(!hasRightChild(index)) {
            Prioritized leftChild = _heap.get(leftChildInd(index));
            if (leftChild.getPriority().compareTo(top.getPriority()) > 0) {
                _heap.set(index, leftChild);
                _heap.set(leftChildInd(index), top);
                bubbleDown(leftChildInd(index));
            } else {
                return;
            }
        }
        else{
            Prioritized left = _heap.get(leftChildInd(index));
            Prioritized right = _heap.get(rightChildInd(index));
            if(left.getPriority().compareTo(top.getPriority())> 0 || right.getPriority().compareTo(top.getPriority())> 0){
                if(left.getPriority().compareTo(right.getPriority())> 0){
                    _heap.set(index, left);
                    _heap.set(leftChildInd(index), top);
                    bubbleDown(leftChildInd(index));
                }
                else{
                    _heap.set(index, right);
                    _heap.set(rightChildInd(index), top);
                    bubbleDown(rightChildInd(index));
                }
            }
            else{return;}
        }
            }




    boolean validIndex(int index){
        if((index >= 0) && (index <= size() - 1)){return true;}
        else{return false;}
    }
    static int rightChildInd(int index){
        return (2*index + 2);
    }
    static int leftChildInd(int index){
        return (2*index + 1);
    }
    static int parentInd(int index){
        return ((index - 1)/2);
    }

    boolean hasRightChild(int index){
        return(validIndex(rightChildInd(index)));
    }
    boolean hasLeftChild(int index){
        return(validIndex(leftChildInd(index)));
    }



}
