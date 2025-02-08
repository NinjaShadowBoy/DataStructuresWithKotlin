package dataStructures
import kotlin.collections.HashMap

class Graph {
    private val adjacencyList: HashMap<Int, MutableList<Int>> = HashMap()

    // Adds a vertex to the graph
    fun addVertex(vertex: Int) {
        if (!adjacencyList.containsKey(vertex)) adjacencyList[vertex] = mutableListOf()
    }


    // Adds an edge between two vertices
    fun addEdge(v1: Int, v2: Int) {
        addVertex(v1)
        addVertex(v2)
        adjacencyList[v1]?.add(v2)
        adjacencyList[v2]?.add(v2)
    }

    // Removes an edge between two
    fun removeEdge(v1: Int, v2: Int) {
        adjacencyList[v1]?.remove(v2)
        adjacencyList[v2]?.remove(v1)
    }

    // Removes a vertex and its associated edges
    fun removeVertex(vertex: Int) {
        while (adjacencyList[vertex]?.isNotEmpty() == true) {
            val adjacentVertex = adjacencyList[vertex]?.first()
            removeEdge(vertex, adjacentVertex!!)
        }
        adjacencyList.remove(vertex)
    }

    // Displays the graph
    fun display(){
        for((vertex, edges) in adjacencyList){
            print("$vertex -> $edges")
        }
    }
}