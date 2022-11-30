public class QuickSort {

    /**
     * TODO
     * @param begin The position of the first element in the sequence to be sorted.
     * @param end   The position that is one-past the last element in the sequence to be sorted.
     * @param <E>   The element type for the sequence.
     */
    public static <E extends Comparable<? super E>>
    void quicksort(Iterator<E> begin, Iterator<E> end) {
        if (!(begin.equals( end))){
            Iterator<E> pivot = partition( begin, end);
            quicksort(begin, pivot);
            pivot.advance();
            quicksort(pivot, end);
        }
    }
    public static <E extends Comparable<? super E>>
    Iterator<E> partition(Iterator<E> begin, Iterator<E> end) {
        /// set pivot
        E pivot = Algorithms.last(begin,end).get();
        Iterator<E> last = Algorithms.last(begin,end);
        Iterator<E> lessthan = begin.clone();
        Iterator<E> greaterthan = begin.clone();
        while(!(greaterthan.equals(last))){
            if (greaterthan.get().compareTo(pivot) <= 0) {
                Algorithms.iter_swap(lessthan, greaterthan);
                lessthan.advance();
            }
            greaterthan.advance();
        }

        Algorithms.iter_swap(lessthan,last);
        return lessthan;
    }
    public <E extends Comparable<? super E>> String toString(Iterator<E> begin, Iterator<E> end){
        String returns = null;
        while(!(begin.equals(end))){
            returns=""+begin.get();
            begin.advance();
        }
        return returns;
    }
}
