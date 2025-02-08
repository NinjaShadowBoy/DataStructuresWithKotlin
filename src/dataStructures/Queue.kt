package dataStructures

class Queue<E> {
    private val minCapacityIncrement = 12
    private var elements: Array<Any?>
    private var size = 0

    constructor() {
        elements = arrayOf()
    }

    constructor(initialCapacity: Int) {
        elements = arrayOfNulls(initialCapacity)
    }

    constructor(elements: Array<E>) {
        this.elements = elements as Array<Any?>
        size += elements.size
    }

    fun enqueue(element: E) {
        // Increase size by size/2 (ie size shr 1) or by minCapacityIncrement
        if (size == elements.size) {
            val newArray = arrayOfNulls<Any>(size + if (size < minCapacityIncrement/2)
                minCapacityIncrement
            else size shr 1)
            System.arraycopy(elements, 0, newArray, 0, size)
            elements = newArray
        }
        elements[size++] = element
    }

    fun enqueueAll(newElements: Array<E>) {
        val newSize = size + newElements.size
        // If the resulting size is more than the current array size
        if(elements.size < newSize) {
            val newArray = arrayOfNulls<Any>(newSize + minCapacityIncrement)
            // Copy all elements in the new larger array
            System.arraycopy(elements, 0, newArray, 0, size)
            elements = newArray
        }
        // Append the elements at the end of the array and upwards
        System.arraycopy(newElements, 0, elements, size, newElements.size)
        size = newSize
    }

    fun dequeue(): E {
        if(size == 0) throw QueueUnderflowException()
        val oldValue = elements[0]
        elements[0] = null
        // Shift elements
        System.arraycopy(elements, 1, elements, 0, --size)
        return oldValue as E
    }

    fun dequeueAll(count: Int) {
        if(size == 0 || size < count) throw QueueUnderflowException()
        // Shift elements
        System.arraycopy(elements, 1, elements, 0, size - count)
        size -= count
        // Set all liberated spaces to null
        for(i in 0 until count){
            elements[size + i] = null
        }
    }

    fun front() = try {
        elements[0] as E
    }catch (e : IndexOutOfBoundsException){
        throw QueueUnderflowException()
    }

    fun rear() = try {
        elements[size - 1] as E
    }catch (e : IndexOutOfBoundsException){
        throw QueueUnderflowException()
    }

    fun isEmpty() = size == 0

    fun isFull() = size == elements.size // The number of elements equals the array capacity

    override fun toString(): String {
        if (size == 0) return "[]"
        val length = size - 1
        val sb = StringBuilder(size*16)
        sb.append('[')
        for (i in 0 until length) {
            sb.append(elements[i])
            sb.append(", ")
        }
        sb.append(elements[length])
        sb.append("]")
        return sb.toString()
    }
}

inline fun <reified T> queueOf(vararg elements: T) = Queue<T>(elements as Array<T>)