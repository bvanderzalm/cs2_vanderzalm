# cs2_vanderzalm

## About the course

Computer Science 2 focuses on the design and analysis of algorithms and advanced data structures. These `Java` solutions heavily focus on Runtime Analysis and Space Complexity where the goal is efficient solutions to problems instead of "brute force". The course covers recursion, data structures such as Binary Search Trees, skip lists, hash tables, graphs, sorting and graph algorithms.

## Project Descriptions

### Attackable Knights Chess Problem
The problem presented here is you are a given a number of different Knight pieces in the game of chess and the program has to determine if all of the Knights are safe. A knight is determined "not safe" is when one knight can jump and attack another knight. A knight in chess can move in an L-shaped movement (recall a knight either moves up or down one square vertically and over two squares horizontally OR up or down two squares vertically and over one square horizontally) thus attacking another knight if there is a piece in that location.

The only catch to this is that the boardsize doesn't necessarily have to be a standard 8x8 grid where the parameters can take up to the Integer.MAX_VALUE (around 2 billion). Therefore, a smart and efficient solution is required where a 2 billion board size with a million knight pieces would take forever if you were to check every single grid.

Runtime Analysis and Space Complexity Requirements:
* O(nk) runtime where n is the number of coordinate strings (knights) and k is the length of those coordinate strings.

Solution:

The smart solution uses the data structures LinkedList and HashSet to quickly store knight coordintes where HashSet has O(1) runtime for it's add() and contains() methods.
