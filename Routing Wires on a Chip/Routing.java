import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.currentTimeMillis;

public class Routing {

    /**
     * TODO
     * <p>
     * The findPaths function takes a board and a list of goals that contain
     * endpoints that need to be connected. The function returns a list of
     * Paths that connect the points.
     */
    public static ArrayList<Wire>
    findPaths(Board board, ArrayList<Endpoints> goals) {
        long start = currentTimeMillis();
//        board.show();
        ArrayList<Wire> end_result = new ArrayList<Wire>();
        Map<Integer, Wiring> wirings = new HashMap<Integer, Wiring>();
        ArrayList<Integer> ID = new ArrayList<>();
        HashMap<Integer, distance> distances = new HashMap<Integer, distance>();
        // Find distances for every goal and sort in increasing order
        for (Endpoints e : goals) {
            // calculate distance
            distance d = new distance(manhattan_distance(e.start, e.end), e);
            distances.put(e.id, d);
        }
        HashMap<Integer, distance> sortedGoals = sortByValue(distances);
        for (distance d : sortedGoals.values()) {
            Endpoints e = d.e;
            if (!ID.contains(e.id)) {
                Map<Coord, Boolean> visited = new HashMap<Coord, Boolean>();
                Map<Coord, Coord> parent = new HashMap<Coord, Coord>();
                ArrayList<Coord> result = BFS(board, e.start, e.end, visited, parent, e.id);
                Integer len = result.get(result.size() - 1).x;
                result.remove(result.size()-1);
                wirings.put(len, new Wiring(e.id, result));
                Wire added = new Wire(e.id, result);
                board.placeWire(added);
                end_result.add(added);
            }
        }
        long stop = currentTimeMillis();
        System.out.println("Time: " + (stop - start));
//        board.show();
        return end_result;
    }

    static ArrayList<Coord> BFS(Board board, Coord start, Coord ends, Map<Coord,Boolean> visited,
                                Map<Coord,Coord> parent, int id) {
        boolean found = false;
        Map<Coord, Boolean> wired = new HashMap<Coord, Boolean>();
        // Begin at start node and BFS on the board until end is reached or not
        for (Coord c : board.adj(start))
            visited.put(c, false);
        Queue<Coord> Q = new LinkedList<Coord>();
        Q.add(start);
        parent.put(start, null);
        visited.put(start, true);
        while (!Q.isEmpty()) {
            Coord u = Q.remove();
            // If end is found, exit
            if (Coord.compare(u, ends) == 0) {
                found = true;
                break;
            }
            ArrayList<Coord> adj = board.adj(u);
            Collections.reverse(adj);
            for (Coord v : adj) {
                // If neighbor not visted, add it to the list of nodes
                if (!visited.containsKey(v)) {
                    visited.put(v, false);
                }
                // If not visited, add to queue
                if (!visited.get(v)) {
                    // If not blocked or not
                    if (!board.isObstacle(v)) {
                        // check if wired already
                        if (!board.isOccupied(v) || board.getValue(v) == id) {
                            parent.put(v, u);
                            Q.add(v);
                            visited.put(v, true);
                            wired.put(v, true);
                        }
                    }
                    else {
                        visited.put(v, true);
                    }
                }
            }
        }
        // If end is found, return the backtrack (wire) to the start
        Coord curr = ends;
        Coord par;
        ArrayList<Coord> wire = new ArrayList<Coord>();
        Integer pathLength = 0;
        if (found) {
            wire.add(new Coord(curr.x, curr.y));
            pathLength++;
            while (parent.get(curr) != null) {
                par = parent.get(curr);
                wire.add(new Coord(par.x, par.y));
                curr = par;
                pathLength++;
            }
        }
        // Return wire with additional info
        Collections.reverse(wire);
        wire.add(new Coord(pathLength, 0));
        return wire;
    }

    public static class Wiring{
        Integer id;
        ArrayList<Coord> wire;
        Wiring(Integer id, ArrayList<Coord> wire) {
            this.id = id;
            this.wire = wire;
        }
    }
    public static double manhattan_distance(Coord start, Coord end) {
        return Double.valueOf(Math.abs(end.x - start.x) + Math.abs(end.y - start.y)) ;
    }
    public static class distance{
        Double dist;
        Endpoints e;
        distance(Double dist, Endpoints e) {
            this.dist = dist;
            this.e = e;
        }
    }
    public static HashMap<Integer, distance>
    sortByValue(HashMap<Integer, distance> m) {
        HashMap<Integer, distance> temp
                = m.entrySet()
                .stream()
                .sorted((i1, i2)
                        -> i1.getValue().dist.compareTo(
                        i2.getValue().dist))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
        return temp;
    }

}