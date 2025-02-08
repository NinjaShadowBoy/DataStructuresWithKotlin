package dataStructures

class Heap(private val isMinHeap: Boolean = true) {
    private var list = mutableListOf<Int>()

    // Helper functions
    private fun parent(index: Int) = (index - 1) / 2
    private fun leftChild(index: Int) = 2 * index + 1
    private fun rightChild(index: Int) = 2 * index + 2

    // Compare based on heap type
    private fun compare(a: Int, b: Int) = if (isMinHeap) list[a] < list[b] else list[a] > list[b]
    fun add(value: Int) {
        list.add(value)
        var index = list.size - 1
        while (index > 0 && compare(parent(index), index)) {
            val temp = list[index]
            list[index] = list[parent(index)]
            list[parent(index)] = temp
            index = parent(index)
        }
    }

    fun removePeak(): Int {
        if (list.isEmpty()) throw NoSuchElementException("Heap is empty")
        val peak = list[0]
        val lastValue = list.removeLast()
        if (list.isNotEmpty()) {
            // Put the last value at the top
            list[0] = lastValue
            heapify(0)
        }
        return peak
    }

    private fun heapify(index: Int) {
        val left = leftChild(index)
        val right = rightChild(index)
        var largestOrSmallest = index
        // If it does not respect the heap property at the left
        if (left < list.size && compare(left, index)) largestOrSmallest = left
        if (right < list.size && compare(right, largestOrSmallest)) largestOrSmallest = right
        if (largestOrSmallest != index) {
            val temp = list[index]
            list[index] = list[largestOrSmallest]
            list[largestOrSmallest] = temp
            heapify(largestOrSmallest)
        }
    }

    fun display() {
        println(list)
    }
}