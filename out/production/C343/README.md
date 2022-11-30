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
in </p>
4. evaluation of the time complexity and wall-clock time of your algorithm.
<p></p>