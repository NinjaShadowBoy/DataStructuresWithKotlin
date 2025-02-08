package dataStructures

class CircularLinkedList<E> {
    private var head: CNode<E>? = null
    private var tail: CNode<E>? = null
    private var size = 0

    // Inner Node class representing each node in the circular linked list
    inner class CNode<E>(
        internal var prev: CNode<E>?,
        internal var value: E,
        internal var next: CNode<E>? = null
    )

    // Check if the list is empty
    fun isEmpty(): Boolean = size == 0

    // Get the size of the list
    fun getSize(): Int = size

    // Add a new element to the end of the list
    fun add(element: E) {
        val newCNode = CNode(tail, element, head) // Create a new node
        if (isEmpty()) {
            // If the list is empty, initialize head and tail to the new node
            head = newCNode
            tail = newCNode
            head?.next = head
            head?.prev = head
        } else {
            // If the list is not empty, adjust the pointers
            tail?.next = newCNode
            head?.prev = newCNode
            tail = newCNode
        }
        size++
    }

    // Remove an element from the list
    fun remove(element: E): Boolean {
        if (isEmpty()) return false

        var current = head
        do {
            if (current?.value == element) {
                if (current == head) {
                    // If the element to be removed is the head
                    head = head?.next
                    tail?.next = head
                    head?.prev = tail
                } else if (current == tail) {
                    // If the element to be removed is the tail
                    tail = tail?.prev
                    tail?.next = head
                    head?.prev = tail
                } else if (current != null) {
                    // If the element to be removed is in the middle
                    current.prev?.next = current.next
                    current.next?.prev = current.prev
                }
                size--
                return true
            }
            current = current?.next
        } while (current != head)

        return false
    }

    // Get the previous node of a given node
    fun getPrevious(node: CNode<E>): CNode<E>? {
        if (head == null || node == null) return null

        if (node == head) {
            // If the node is the head, return the tail
            return tail
        }

        var current = head
        do {
            // If the next node is the given node, return the current node
            if (current?.next == node) {
                return current
            }
            current = current?.next
        } while (current != head)

        return null
    }

    override fun toString(): String {
        var current = head
        if (current == null) return "[]"
        else {
            val sb = StringBuilder()
            sb.append('[')
            while (current != null) {
                sb.append(current.value)
                current = current.next
                if (current?.value == null) {
                    sb.append(']')
                } else {
                    sb.append(',').append(' ')
                }
            }
            return sb.toString()
        }
    }
}
