# Almost Shortest Path

[URI 1391](https://www.urionlinejudge.com.br/judge/en/problems/view/1391)

Finding the shortest path that goes from a starting point to a destination point given 
a set of points and route lengths connecting them is an already well known problem, 
and it’s even part of our daily lives, as shortest path programs are widely available nowadays.

Most people usually like very much these applications as they make their lives easier. 
Well, maybe not that much easier.

Now that almost everyone can have access to GPS navigation devices able to calculate the 
shortest paths, most routes that form the shortest path are getting slower because of heavy traffic. 
As most people try to follow the same path, it’s not worth it anymore to follow these directions.

With this in his mind, your boss asks you to develop a new application that only he will have 
access to, thus saving him time whenever he has a meeting or any urgent event. He asks you 
that the program must answer not the shortest path, but the almost shortest path. He defines 
the almost shortest path as the shortest path that goes from a starting point to a destination 
point such that no route between two consecutive points belongs to any shortest path from the 
starting point to the destination.

## Input

The input contains several test cases. 
The first line of a test case contains two integers **N** (2 ≤ **N** ≤ 500) and **M** (1 ≤ **M** ≤ 104), 
separated by a single space, indicating respectively the number of points in the map, and 
the number of existing one-way routes connecting two points directly. Each point is identified 
by an integer between 0 and **N**-1. The second line contains two integers **S** and **D**, separated by 
a single space, indicating respectively the starting, and the destination points (**S** ≠ **D**; 0 ≤ **S**, **D** < **N**). 
Each one of the following **M** lines contains three integers **U**, **V** and **P** (**U** ≠ **V**; 0 ≤ **U**, **V** < **N**; 1 ≤ **P** ≤ 103), 
separated by single spaces, indicating the existence of a one-way route from **U** to **V** with 
distance **P**. There is at most one route from a given point **U** to a given point **V**, but notice 
that the existence of a route from **U** to **V** does not imply there is a route from **V** to **U**, and, 
if such road exists, it can have a different length. The end of input is indicated by a line 
containing only two zeros separated by a single space.

## Output

For each test case in the input, your program must print a single line, 
containing -1 if it is not possible to match the requirements, or an integer 
representing the length of the almost shortest path found.