package dataStructures

class Stack<E> {
    private val minCapacityIncrement: Int = 12
    private var elements: Array<Any?>
    private var size: Int = 0

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

    fun push(element: E) {
        if (size == elements.size) {
            // Increase size by size/2 (ie size shr 1) or by minCapacityIncrement
            val newArray = arrayOfNulls<Any>(size + if (size < minCapacityIncrement/2)
                minCapacityIncrement
            else
            size shr 1)
            System.arraycopy(elements, 0, newArray, 0, size)
            elements = newArray
        }
        elements[size++] = element
    }

    fun pushAll(newElements: Array<E>) {
        val newSize = size + newElements.size
        if(elements.size < newSize) {
            // If the resulting size is more than the current array size
            val newArray = arrayOfNulls<Any>(newSize + minCapacityIncrement)
            // Copy all elements in the new larger array
            System.arraycopy(elements, 0, newArray, 0, size)
            elements = newArray
        }
        // Append the elements at the end of the array and upwards
        System.arraycopy(newElements, 0, elements, size, newElements.size)
        size = newSize
    }

    fun pop(): E{
        // remove the last element and replace with null
        if (size == 0) throw StackUnderflowException()
        val index = --size
        val last = elements[index]
        elements[index] = null
        return last as E
    }

    fun pop(count: Int){
        // replace 'count' elements with null at end of stack
        if (size == 0 || size < count) throw StackUnderflowException()
        for(i in 0 until count){
            elements[--size] = null
        }
    }

    fun peek() = try {
        elements[size - 1] as E
    }catch (e: IndexOutOfBoundsException){
        throw StackUnderflowException()
    }

    fun isEmpty() = size == 0

    fun isFull() = size == elements.size // The number of elements equals the array capacity

    override fun toString(): String {
        if (size == 0) return "[]"
        val length = size - 1
        val sb = StringBuilder(size*16)
        sb.append('[')
        for (i in 0..<length) {
            sb.append(elements[i])
            sb.append(", ")
        }
        sb.append(elements[length])
        sb.append("]")
        return sb.toString()
    }
}
inline fun <reified T> stackOf(vararg elements: T): Stack<T> = Stack<T>(elements as Array<T>)