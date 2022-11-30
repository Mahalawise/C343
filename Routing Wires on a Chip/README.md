1. overview of the algorithm:
   1. The first thing I do is I create a bfs helper and change the args so that it can take the board and goals. 
      I then decided to change it, so it doesn't place a wire at obstacles and if it is occupied. I realized that there was 
test cases that required some paths to go a certain way to get the path to work out. This is why I used the manhattan distance
   to calculate how far apart they were from each other and reverse the wire. I then also have a sort by Value method with help from nicky
   that is able to sort the values. Finally, after making the adjustments and sorting by id, distance and points, the maps in the 
   autograder tests are then complete. 
2. one or more examples of applying your algorithm to interesting boards
<p>One example of applying my algorithm to interesting boards would be:</p>
   <p>|  0   2   3   4   0 |</p>
     <p> |  1   2   3   4   1 |</p>
     <p>  |  1   2   3   4   1 |</p>
    <p>   |  1   1   1   1   1 |</p>
  <p> This is an interesting algorithm because if the first wire was the priority then wire 2,3,and 4 would not be able to 
finish. This is a perfect example as how my code works to get the correct path.</p>
3. evaluation of your algorithm with respect to finding and minimizing wire layouts
<p>My overall program is okay. It runs pretty fast however in certain boards it would break for example
in: </p>
<p>|  1   1   3   0   5   5 |</p>
<p>|  1   2   2   0   0   5 |</p>
<p>|  2   2   0   4   4   0 |</p>
<p>|  0   0   0   0   4   3 |</p>
<p> The wire 3 cannot connect because my algorithm makes the 4th wire connect in the reverse order thus making connect up instead of down.
A better way to make it work would be doing a backtracking algorithm however in order to back track the algorithm would run exponentially faster
thus I sacrificed connecting certain wires for the overall speed.</p>
4. evaluation of the time complexity and wall-clock time of your algorithm.
<p>The time complexity of this program would be O(nlogn) and for the search and O(n+m) for BFS with m being the number of vertices.Thus the overall time complexity would be O(nlogn)+O(n+m) which would be overall O(nlog).
In runtime, especially in the autograder: For testGenChip_1_1 the time is 14 with the total length of all the wires being 105. 
The testGenChip_1_2  has a time of 19 with a total length of all the wires as 208. for testWire5 the time is 9 with the total length of 
all the wires being 27. In testWire8 has a time of 8 with the total length of all the wire being 18.
testWire9 has a total time of 8 with a total length of all the wires being 21.The time for the testgenChip_10_10 is 126 with the total length of all the wired being 10400. </p>