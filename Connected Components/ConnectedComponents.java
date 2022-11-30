import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

public class ConnectedComponents {

    /*
     * TODO
     */
    public static <V> void
    connected_components(Graph<V> G, Map<V, V> representative) {

        Iterator<V> node = G.vertices().iterator();
        while (node.hasNext()) {
            V starter = node.next();
            if (visited(representative, starter) == 0) {
                Map<V, V> adding = specific_dfs(G, representative, starter);
                for (Map.Entry<V, V> entry : adding.entrySet()) {
                    representative.put(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    public static <V> Map<V,V> specific_dfs(Graph<V> G, Map<V, V> representative,V starter){
        Stack<V> temp = new Stack();
        temp.push(starter);
        while(!(temp.empty())){
            V u = temp.pop();
            for(V v: G.adjacent(u)){
                if(visited(representative,v)==0){
                    representative.put(v,starter);
                    temp.push(v);
                }
            }
            representative.put(starter,starter);
        }
        return representative;
    }
    public static <V> int visited(Map<V, V> representative,V node){
        int ret = 0;
        for(Map.Entry<V,V> entry : representative.entrySet()){
            if((int)entry.getKey() == (int) node){
                ret = 1;
            }
            if((int)entry.getValue() == (int) node){
                ret = 1;
            }
        }
        return ret;
    }

}