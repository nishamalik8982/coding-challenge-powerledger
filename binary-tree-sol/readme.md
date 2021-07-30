Binary Tree
==================
Binary tree is consist of nodes. Nodes can have two child nodes and every child can have their children... to the infinite (more or less).

Requirement
------------------
For this project, each node in a tree stores a single digit (from 0 to 9), and each path from root to leaf encodes a non-negative integer. There should be a function that takes a tree as an input and return the sum of all the integers encoded from the path.  

<b>Graphical Example 1:</b>
<pre>
        _1_
     __|   |__
    |         |
  __0__       4
 |     | 
 3     1
</pre>

We can see 3 paths from root to leaf as mentioned below. Each path encodes a non-negative integer.
1. 1 -> 0 -> 3 = 103
2. 1 -> 0 -> 1 = 101
3. 1 -> 4      =  14

Sum : 103 + 101 + 14 = 218 (Need a function to calculate this)

Let's take another example.

<b>Graphical Example 2:</b>
<pre>
        _0_
     __|   |__
    |         |
    9       __9__
           |     |
           1     3
</pre>

We can see 3 paths from root to leaf as mentioned below. Each path encodes a non-negative integer.
1. 0 -> 9      =  9
2. 0 -> 9 -> 1 = 91
3. 0 -> 9 -> 3 = 93

Sum : 9 + 91 + 93 = 193 (Need a function to calculate this)

Prerequistes
---------------------
Java 8 or above

Maven 3.0 or above

Any suitable IDE (i.e. IntelliJ Idea, Netbeans, Eclipse) (Optional)


Implementation
---------------------
Below two classes are implemented to achieve above task.
1. Node : This class represents a node. It has 3 attributes; data, left, right to store digit, left side node and right side node accordingly. A set of node connected with each other form a binary tree.

2. Tree : This class provides a way to find the sum of all integers derived from the various paths. The findSum() is a static method that accepts root node of the tree and returns a sum. There are other static methods to support findSum() method to get the desired result.

Testing
---------------------
Test cases are developed for each of the classes mentioned in the Implementation section.

Command to execute test cases from command line:
<pre>
mvn test
</pre>

We will see below lines in the logs upon execution of above command
<ascii>
<pre>

-------------------------------------------------------
T E S T S
-------------------------------------------------------
Running com.datastructures.NodeTest
Tests run: 7, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0 sec
Running com.datastructures.TreeTest
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0 sec

Results :

Tests run: 11, Failures: 0, Errors: 0, Skipped: 0
</pre>
