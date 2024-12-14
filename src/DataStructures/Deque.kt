package DataStructures

class Deque<E>{
    private val minCapacityIncrement = 12
    private var elements: Array<Any?>
    private var size = 0

    constructor(){
        elements = arrayOf()
    }

    constructor(initialCapacity: Int){
        elements = arrayOfNulls(initialCapacity)
    }

    fun enqueueFront(item: E){
        if(size == elements.size) {
            val newArray = arrayOfNulls<Any>(
                size + if (size < minCapacityIncrement / 2)
                    minCapacityIncrement
                else
                    size shr 1
            )
            System.arraycopy(elements, 0, newArray, 1, size)
            elements = newArray
        } else {
            System.arraycopy(elements, 0, elements, 1, size)
        }
        elements[0] = item
        size++
    }

    fun enqueueRear(item: E){
        if (size == elements.size){
            val newArray = arrayOfNulls<Any>(
                size + if (size < minCapacityIncrement / 2)
                    minCapacityIncrement
                else
                    size shr 1
            )
            System.arraycopy(elements, 0, newArray, 1, size)
            elements = newArray
        }
        elements[size++] = item
    }

    fun dequeueFront(): E{
        if(size == 0) throw QueueUnderflowException()
        val oldVal = elements[0]
        elements[0] = null
        System.arraycopy(elements, 1, elements, 0, --size)
        return oldVal as E
    }

    fun dequeueRear(): E {
        if(size == 0) throw QueueUnderflowException()
        val oldVal = elements[--size]
        elements[size] = null
        return oldVal as E
    }

    fun front() = try {
        elements[0] as E
    } catch (e: ArrayIndexOutOfBoundsException) {
        throw QueueUnderflowException()
    }

    fun rear() = try {
        elements[size - 1] as E
    } catch (e: IndexOutOfBoundsException){
        throw QueueUnderflowException()
    }

    fun isEmpty() = size == 0

    fun isFull() = size == elements.size

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