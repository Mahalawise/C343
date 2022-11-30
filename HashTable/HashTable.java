import java.util.ArrayList;
class Node<K,V>{
    K key;
    V value;
    Node<K,V> preNode;
    Node<K,V> nextNode;

     Node(K key ,V value){
        this.key=key;
        this.value=value;
        this.preNode=null;
        this.nextNode=null;

    }
}

public class HashTable<K,V>{
    int length;
    ArrayList<Node<K,V>> hasharray= new ArrayList<Node<K,V>>();
    public HashTable(int size){
        this.length=size;
        for(int i=0; i<size; i++){
            hasharray.add(null);
        }
    }


    public V get(K key) throws Exception {
        if (this.containsKey(key)) {
            System.out.println("hi");
            int prehash = key.hashCode();
            int index = Math.abs(prehash) % length;
            System.out.println(index);
            Node<K, V> traversal = hasharray.get(index);
            if (traversal != null) {
                if(traversal.key.equals(key)){
                    return traversal.value;
                }
            }
            while(traversal!=null){
                if(traversal.key.equals(key)){
                    return traversal.value;
                }else{
                    traversal=traversal.nextNode;
                }
            }
        }else{
            throw new Exception();
        }
        return null;
    }

    public void put(K key, V value) {
        System.out.println("puting" + key);
        int prehash= key.hashCode();
        int index = Math.abs(prehash)%length;
        Node<K,V> head = hasharray.get(index);
        if (head==null) {
            Node<K,V> node = new Node<K,V>(key, value);
            hasharray.set(index, node);
        } else {
            Node<K, V> traversal = hasharray.get(index);
            while (traversal.nextNode!=null) {
                if(traversal.key.equals(key)){
                    traversal.value=value;
                    return;
                }else {
                    traversal = traversal.nextNode;
                }
            }
            if(traversal.key.equals(key)){
                traversal.value=value;
                return;
            }
            Node<K, V> node = new Node<K, V>(key, value);
            traversal.nextNode = node;
            node.preNode = traversal;
        }
        System.out.println(this);
    }

    public void remove(K key) {
        System.out.println("TRemoving" + key);
        if (this.containsKey(key)) {
            int prehash = key.hashCode();
            int index = Math.abs(prehash)%length;
            Node<K, V> traversal = hasharray.get(index);
                while (traversal!=null) {
                    if(traversal.key.equals(key)){
                        break;
                    }else{
                        traversal = traversal.nextNode;
                    }
                }
                if(traversal!=null) {

                    if (traversal.nextNode == null && traversal.preNode == null) {
                        this.hasharray.set(index,null);
                        return;
                    }
                    if(traversal.preNode != null){
                        traversal.preNode.nextNode=traversal.nextNode;
                    } else{
                        this.hasharray.set(index,traversal.nextNode);
                        this.hasharray.get(index).preNode = null;
                        System.out.println(this);
                    }
                    if(traversal.nextNode!=null){
                        traversal.nextNode.preNode=traversal.preNode;
                    }

                }

            }
    }

    public boolean containsKey(K key) {
        System.out.println("check " + key);
        int prehash = key.hashCode();
        int index = Math.abs(prehash)%length;
        Node<K, V> traversal = this.hasharray.get(index);
        if (traversal == null) {
            return false;
        } else {
            while(traversal!=null){
                if(traversal.key.equals(key)){
                    return true;
                }else{
                    traversal=traversal.nextNode;
                }
            }
            return false;
        }
    }

    public String toString() {
        for (Node<K,V> node : this.hasharray) {
            System.out.print("(");
            while (node != null) {
                System.out.print("[" + node.key + "," + node.value + "]");
                node = node.nextNode;
            }
            System.out.print(")");
        }
        return "";
    }
}

