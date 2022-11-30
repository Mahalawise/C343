import java.util.ArrayList;

public class StudentTest {
    public static void main(String[] args){
        Coord one_start= new Coord(0,1);
        Coord one_end= new Coord(1,0);
        Coord two_start= new Coord(1,2);
        Coord two_end= new Coord(2,0);
        Coord three_start= new Coord(0,2);
        Coord three_end= new Coord(3,5);
        Coord four_start= new Coord(2,3);
        Coord four_end= new Coord(3,4);
        Coord five_start= new Coord(0,4);
        Coord five_end= new Coord(1,5);
        ArrayList<Endpoints> goals = new ArrayList<>();
        goals.add(new Endpoints(1,one_start,one_end));
        goals.add(new Endpoints(2,two_start,two_end));
        goals.add(new Endpoints(3,three_start,three_end));
        goals.add(new Endpoints(4,four_start,four_end));
        goals.add(new Endpoints(5,five_start,five_end));
        ArrayList<Integer[]> obstacles= new ArrayList<>();
        Board b= new Board(4,6,goals,obstacles);
        ArrayList<Wire> path=Routing.findPaths(b,goals);
        for (Wire w : path){
            b.placeWire(w);
        }
        b.show();
    }
}
