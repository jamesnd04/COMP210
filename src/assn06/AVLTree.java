package assn06;


public class AVLTree<T extends Comparable<T>> implements SelfBalancingBST<T> {
    // Fields
    private T _value;
    private AVLTree<T> _left;
    private AVLTree<T> _right;
    private int _height;
    private int _size;
    
    public AVLTree() {
        _value = null;
        _left = null;
        _right = null;
        _height = -1;
        _size = 0;
    }

    /**
     *
     * Rotates the tree left and returns
     * AVLTree root for rotated result.
     */
    
     private AVLTree<T> rotateLeft() {
         AVLTree<T> currR = _right;
         AVLTree<T> currL = currR._left;

         currR._left = this;
         _right = currL;
         _height = Math.max(_left.height(), _right.height())+1;
         _size = _left._size + _right._size + 1;

         currR._height = Math.max(currR._left.height(), currR._right.height())+1;
         currR._size = currR._left._size + currR._right._size + 1;

         return currR;
     }
    
    /**
     *
     * Rotates the tree right and returns
     * AVLTree root for rotated result.
     */ 
     
     private AVLTree<T> rotateRight() {
         AVLTree<T> currL = _left;
         AVLTree<T> currR = currL._right;

         currL._right = this;
         _left = currR;
         _height = Math.max(_left.height(), _right.height())+1;
         _size = _left._size + _right._size + 1;

         currL._height = Math.max(currL._left.height(), currL._right.height())+1;
         currL._size = currL._left._size + currL._right._size + 1;

         return currL;
     }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int height() {
        return _height;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public SelfBalancingBST<T> insert(T element) {
        if(isEmpty()){
            _value = element;
            _right = new AVLTree<T>();
            _left = new AVLTree<T>();
            _size++;
            _height = Math.max(_left.height(), _right.height()) + 1;
        }
        else if(element.compareTo(_value) < 0){
            _left = (AVLTree<T>) _left.insert(element);
            _size++;
            _height = Math.max(_left.height(), _right.height()) + 1;
        }
        else{
            _right = (AVLTree<T>) _right.insert(element);
            _size++;
            _height = Math.max(_left.height(), _right.height()) + 1;
        }
        return balance();
     }



    @Override
    public SelfBalancingBST<T> remove(T element) {
         if(!contains(element)){
            return this;
        }
        else if (size() == 1 && _value.compareTo(element) == 0){
            _value = null;
            _size--;
            _left = null;
            _right = null;
            _height = -1;
            return this;
        }
        else{
             if (element.compareTo(_value) == 0){
                 if(!hasChildren()){
                     return new AVLTree<T>();
                 }
                 else{
                     if (_left.isEmpty()){
                         return _right;
                     }
                     else if(_right.isEmpty()){
                         return _left;
                     }
                     T low = _right.findMin();  // if there is a left and right, the right value must now become the element
                     _right.remove(low);
                     _value = low; // assigns element to low, the left should still be less than the element

                 }
             }
             else if (element.compareTo(_value) > 0){
                 _right = (AVLTree<T>) _right.remove(element);
             }
             else if (element.compareTo(_value) < 0){
                 _left = (AVLTree<T>) _left.remove(element);
             }
         }
        _height = Math.max(_left.height(), _right.height()) + 1;
        _size = _left._size + _right._size + 1;

        return balance();
    }

    @Override
    public T findMin() {
         if (isEmpty()) {
             throw new RuntimeException("Illegal operation on empty tree");
         }
         if (_left.isEmpty()) {
             return _value;
         } else {
             return _left.findMin();
         }
    }

    @Override
    public T findMax() {
        if (isEmpty()) {
            throw new RuntimeException("Illegal operation on empty tree");
        }
        if (_right.isEmpty()) {
            return _value;
        } else {
            return _right.findMax();
        }
    }

    @Override
    public boolean contains(T element) {
    	if(isEmpty()){
        return false;
        }
        else if(element.compareTo(_value) < 0){
            return _left.contains(element);
        }
        else if(element.compareTo(_value) > 0){
            return _right.contains(element);
        }
        else if(element.compareTo(_value) == 0){
            return true;
        }
        return false;
    }

    @Override
    public T getValue() {
        return _value;
    }

    @Override
    public SelfBalancingBST<T> getLeft() {
        if (isEmpty()) {
            return null;
        }
        return _left;
    }

    @Override
    public SelfBalancingBST<T> getRight() {
        if (isEmpty()) {
            return null;
        }

         return _right;
    }

    public boolean hasChildren(){
         if(getLeft() == null){
             if(getRight() == null){
                 return false;}
             else{
                 return true;
             }
         }
        else if(getRight() == null){
            if(getLeft() == null){
                return false;
            }
            else{
            return true;
            }
        }
        else{
        return true;
        }
    }
    public Integer bf(){
         if(isEmpty()){return 0;}
         return _left.height() - _right.height();}

    public SelfBalancingBST<T> balance(){
         _height = Math.max(_left.height(), _right.height())+1;
        int curB = bf();
        if(curB < -1) {
            if (_right.bf() > 0) {
                _right = this._right.rotateRight();
            }
            return this.rotateLeft();
        }
        if(curB > 1) {
            if (_left.bf() < 0) {
                _left = _left.rotateLeft();
            }
            return this.rotateRight();
        }
        return this;
    }

}
