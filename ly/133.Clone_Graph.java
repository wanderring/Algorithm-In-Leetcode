/*Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph. Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.

 

Example:

1--2
|  |
3--4


Input:
{"$id":"1","neighbors":[{"$id":"2","neighbors":[{"$ref":"1"},{"$id":"3","neighbors":[{"$ref":"2"},{"$id":"4","neighbors":[{"$ref":"3"},{"$ref":"1"}],"val":4}],"val":3}],"val":2},{"$ref":"4"}],"val":1}

Explanation:
Node 1's value is 1, and it has two neighbors: Node 2 and 4.
Node 2's value is 2, and it has two neighbors: Node 1 and 3.
Node 3's value is 3, and it has two neighbors: Node 2 and 4.
Node 4's value is 4, and it has two neighbors: Node 1 and 3.
 

Note:

The number of nodes will be between 1 and 100.
The undirected graph is a simple graph, which means no repeated edges and no self-loops in the graph.
Since the graph is undirected, if node p has node q as neighbor, then node q must have node p as neighbor too.
You must return the copy of the given node as a reference to the cloned graph.

使用两个list模拟队列广度优先遍历整个图，遍历的过程中复制，并使用map存储复制后的节点，以方便
迅速找到复制后的neighbors

Runtime: 1 ms, faster than 100.00% of Java online submissions for Clone Graph.
Memory Usage: 32.7 MB, less than 98.85% of Java online submissions for Clone Graph.
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}

    public Node(int _val,List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};
*/
class Solution {
    public Node cloneGraph(Node node) {
        ArrayList<Node> temp1 = new ArrayList<>();
        ArrayList<Node> temp2 = new ArrayList<>();
        ArrayList<Node> tempCopy = new ArrayList<>();
        HashMap<Node,Node> origin = new HashMap<>();
        temp1.add(node);
        Node nodeCopy = new Node(node.val, new ArrayList<Node>());
        tempCopy.add(nodeCopy);
        origin.put(node,nodeCopy);
        while (temp1.size()>0) {
            Node tempNode = temp1.get(0);
            Node tempCopyNode = tempCopy.get(0);
            temp1.remove(0);
            tempCopy.remove(0);
            for (Node item : tempNode.neighbors) {
                if (!origin.containsKey(item)) {
                    Node itemCopy = new Node(item.val, new ArrayList<Node>());
                    origin.put(item,itemCopy);
                    tempCopy.add(itemCopy);
                    tempCopyNode.neighbors.add(itemCopy);
                    temp2.add(item);
                } else {
                    tempCopyNode.neighbors.add(origin.get(item));
                }
            }
            if (temp1.size()==0) {
                temp1 = temp2;
                temp2 = new ArrayList<>();
            }
        }
        return nodeCopy;
    }
}
