// Imports for the parameters of flood

import java.lang.reflect.Array;
import java.util.*;

public class Flood {

    public static void flood1(WaterColor color, LinkedList<Coord> flooded_list, Tile[][] tiles, Integer board_size) {
        //Color that was selected
        //flooded_list -Linked list of all of the tiles in the current flooded region
        // tiles- array of tiles can get the color with getColor method
        //board-size-number of rows of tiles in the board.
        // Coord class contains functions up,down,left,right,onBoard- tells you if the coordinate on the board,
        // neighbors returns a list of neighboring coordinates
        // TODO add new flooded tiles to the flood_list

         //get the neighboring coordinated from the flooded list
        //use the tiles and getcolor method to see if the color is the same as color selected
        //have to check if the other neighbors have the same color.
        //add the tile coordinate to the flooded list if same color, if not then leave it alone
        //make sure it is in the region of the board size.

        for(int i=0; i!=flooded_list.size();i++){
            for(Coord c: flooded_list.get(i).neighbors(board_size)){
                if (!(flooded_list.contains(c))){
                    if(tiles[c.getY()][c.getX()].getColor()==color){
                        flooded_list.add(new Coord(c));
                    }
                }
            }
        }




    }
    public static void flood2(WaterColor color, LinkedList<Coord> flooded_list, Tile[][] tiles, Integer board_size) {
        //Arraylist
        ArrayList a = new ArrayList(flooded_list);
        for(int i=0; i!=flooded_list.size();i++){
            for(Coord c: flooded_list.get(i).neighbors(board_size)){
                if (!(a.contains(c))){
                    if(tiles[c.getY()][c.getX()].getColor()==color){
                        flooded_list.add(new Coord(c));
                        a.add(c);
                    }
                }
            }
        }
    }
    public static void flood(WaterColor color, LinkedList<Coord> flooded_list, Tile[][] tiles, Integer board_size) {
        //Hashset
        HashSet h = new HashSet(flooded_list);
        for(int i=0; i!=flooded_list.size();i++){
            for(Coord c: flooded_list.get(i).neighbors(board_size)){
                if (!(h.contains(c))){
                    if(tiles[c.getY()][c.getX()].getColor()==color){
                        flooded_list.add(new Coord(c));
                        h.add(c);
                    }
                }
            }
        }

    }
}
