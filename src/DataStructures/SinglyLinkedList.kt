package DataStructures

class SinglyLinkedList<E> {
    private var size = 0
    private var head: Node<E>? = null
    private var tail: Node<E>? = null

    fun getFirst() = head?.value

    fun getLast() = tail?.value

    fun removeFirst() {
        if (head != null) {
            // Keep the node after head
            val next = head!!.next
            // Remove the link between the head and the other nodes
            head!!.next = null
            // That value becomes the new head
            head = next
            if (next == null) {
                tail = null
            }
            size--
        }
    }

    private fun getPrevious(node: Node<E>): Node<E>? {
        if (head != null && node == head) return null
        var current: Node<E>? = head
        while (current != null) {
            // If next is node then current is previous
            if (current.next == node) {
                return current
            }
            current = current.next
        }

        return null
    }

    fun removeLast() {
        if (tail != null) {
            val prev = getPrevious(tail!!)
            // New tail is the node preceding tail
            tail = prev
            // Remove link of tail with its previous
            prev!!.next = null
        }
    }

    fun add(element: E) {
        val t = tail
        val newNode = Node(element, null)
        tail = newNode
        if (t != null) {
            t.next = newNode
        } else {
            head = newNode
        }
        size++
    }

    fun add(index: Int, value: E) {
        validatePositionIndex(index)
        if (index == size) {
            add(value)
        } else {
            val successor = node(index)
            val previous = getPrevious(successor)
            val newNode = Node(value, successor)
            if (previous == null) {
                head = newNode
            } else {
                previous.next = newNode
            }
            size++
        }
    }

    fun addLast(element: E) = add(element)
    fun addFirst(element: E) {
        val h = head
        val newNode = Node(element, head)
        head = newNode
        if (h == null) {
            tail = newNode
        }
        size++
    }

    fun <T> addAll(index: Int, arr: Array<T>): Boolean where T : E {
        validatePositionIndex(index)

        val numNew = arr.size
        if (numNew == 0) return false

        var precedent: Node<E>?
        var following: Node<E>?
        when (index) {
            0 -> {
                following = head
                precedent = null
            }

            size -> {
                following = null
                precedent = tail
            }

            else -> {
                precedent = node(index - 1)
                following = precedent.next
            }
        }

        for (item in arr) {
            val e = item as E
            val newNode = Node<E>(e, null)
            if (precedent == null)
                head = newNode
            else
                precedent.next = newNode
            precedent = newNode
        }

        if (following == null) {
            tail = precedent
        } else {
            precedent!!.next = following
        }

        size += numNew
        return true
    }

    fun get(index: Int): E {
        validateElementIndex(index)
        return node(index).value
    }

    fun set(index: Int, value: E): E {
        validateElementIndex(index)
        val x = node(index)
        val oldVal = x.value
        x.value = value
        return oldVal
    }

    fun remove(element: E): Boolean {
        var current: Node<E>? = head
        while (current != null) {
            if (current.value == element) {
                unlink(current)
                return true
            }
            current = current.next
        }
        return false
    }

    fun clear() {
        var node: Node<E>? = head
        while (node != null) {
            val next = node.next
            node.next = null
            node = next
        }
        tail = null
        head = null
        size = 0
    }

    private fun unlink(node: Node<E>): E {
        val value = node.value
        val next = node.next
        val previous = getPrevious(node)

        if (previous == null) { // If the node is the head
            head = next
        } else {
            // Remove the link with the rest of the list
            previous.next = next
            node.next = null
        }

        if (next == null) { // If node is followed by null
            previous?.next = null
            tail = previous
        } else {
            previous?.next = next
            // Remove link with the list
            node.next = null
        }
        size--
        return value
    }

    operator fun contains(element: E) = indexOf(element) != -1

    fun indexOf(value: E): Int {
        var index = 0
        var x = head
        while (x != null) {
            if (value == x.value)
                return index
            index++
            x = x.next
        }
        return -1
    }

    private fun linkBefore(value: E, successor: Node<E>) {
        val previous = getPrevious(successor)
        val newNode = Node(value, successor)
        if (previous == null) {
            head = newNode
        } else {
            previous.next = newNode
        }
        size++
    }

    fun size() = size

    private fun node(index: Int): Node<E> {
        var node = head
        for (i in 0 until index) {
            node = node!!.next
        }
        return node!!
    }

    private fun validateElementIndex(index: Int) {
        if (index < 0 || index >= size)
            throw IndexOutOfBoundsException(outOfBoundsMsg(index))
    }

    private fun validatePositionIndex(index: Int) {
        if (index < 0 && index > size)
            throw IndexOutOfBoundsException(outOfBoundsMsg(index))
    }

    private fun outOfBoundsMsg(index: Int): String {
        return "Index: $index, Size: $size"
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