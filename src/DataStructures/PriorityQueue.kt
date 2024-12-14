package DataStructures

class PriorityQueue<T> {
    private val elements: MutableList<PQElement<T>> = mutableListOf()

    fun enqueue(item: T, priority: Int) {
        val pqElement = PQElement(item, priority)
        elements.add(pqElement)
        elements.sortByDescending { it.priority }
    }

    fun dequeue(): T?{
        if(isEmpty()){
            return null
        }
        return elements.removeAt(0).data
    }

    fun peek(): T? {
        return elements.firstOrNull()?.data
    }

    fun isEmpty() = elements.isEmpty()
}