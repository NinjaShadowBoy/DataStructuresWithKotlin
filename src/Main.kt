import DataStructures.LinkedList


fun main(){
    val myList = LinkedList<String>()
    myList += "Alex"
    myList += "Brian"
    myList += "Abena"
    println(myList)
    myList -= "Brian"
    println(myList)
}