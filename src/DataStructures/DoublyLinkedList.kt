package DataStructures

class DoublyLinkedList<E> {
    private var head: Node<E>? = null
    private var tail: Node<E>? = null
    private var size = 0

    private inner class Node<E>(internal var prev: Node<E>?, internal var value: E, internal var next: Node<E>? = null)

    fun getFirst() = head?.value
    fun getLast() = tail?.value
    fun removeFirst() {
        if (head != null) {
            val next = head!!.next
            // Remove link between head and next node
            head = next
            if (next == null) { // If there was just one element
                tail = null
            } else {
                // Remove the link between next node and head(old head)
                next.prev = null
            }
            size--
        }
    }

    fun removeLast() {
        if (tail != null) {
            val previous = tail!!.prev
            tail!!.prev = null
            if (previous == null) {
                head = null
            } else {
                previous.next = null
            }
            size--
        }
    }

    fun addFirst(value: E) {
        val h = head
        val newNode = Node<E>(null, value, h)
        head = newNode
        if (h == null) { // If list was empty
            tail = newNode
        } else {
            h.prev = newNode
        }
        size++
    }

    fun addLast(value: E) {
        val t = tail
        val newNode = Node<E>(t, value, null)
        tail = newNode
        if (t == null) { // If the list was empty
            head = newNode
        } else {
            t.next = newNode
        }
        size++
    }

    fun <T> addAll(index: Int, newVals: Array<T>): Boolean where T : E {
        validatePositionIndex(index)

        val numNew = newVals.size
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
                following = node(index)
                precedent = following.prev
            }
        }

        for (new in newVals) {
            val e = new as E
            val newNode = Node(precedent, e, null)
            if (precedent == null) {
                head = newNode
            } else {
                precedent.next = newNode
            }
            precedent = newNode
        }



        if (following == null) {
            tail = precedent
        } else {
            precedent!!.next = following
            following!!.prev = precedent
        }

        size += numNew
        return true
    }

    fun remove(element: E): Boolean {
        var current = head
        while (current != null) {
            if (current.value == element) {
                unlink(current)
                return true
            }
            current = current.next
        }
        return false
    }

    fun remove(index: Int): E {
        validateElementIndex(index)
        return unlink(node(index))
    }

    fun clear() {
        var x = head
        while (x != null) {
            val next = x.next
            x.next = null
            x.prev = null
            x = next
        }
        tail = null
        head = tail
        size = 0
    }

    fun size() = size

    operator fun contains(value: E): Boolean {
        return indexOf(value) != -1
    }

    fun get(index: Int): E {
        validateElementIndex(index)
        return node(index).value
    }

    fun set(index: Int, element: E): E {
        validateElementIndex(index)
        val x = node(index)
        val oldVal = x.value
        x.value = element
        return oldVal
    }

    fun indexOf(value: E): Int {
        var index = 0
        var current = head
        while (current != null) {
            if (current.value == value) {
                return index
            }
            current = current.next
            index++
        }
        return -1
    }

    private fun node(index: Int): Node<E> {
        if (index < size shr 1) {
            var current = head
            for (i in 0 until index) {
                current = current!!.next
            }
            return current!!
        } else {
            var current = tail
            for (i in size - 1 downTo index + 1) {
                current = current!!.prev
            }
            return current!!
        }
    }

    private fun linkBefore(element: E, following: Node<E>) {
        val predecessor = following.prev
        val newNode = Node(predecessor, element, following)
        following.prev = newNode
        if (predecessor == null) {
            head = newNode
        } else {
            predecessor.next = newNode
        }
        size++
    }

    private fun unlink(current: Node<E>): E {
        val value = current.value
        val next = current.next
        val prev = current.prev

        if (prev == null) {
            head = next
        } else {
            prev.next = next
            current.prev = null
        }

        if (next == null) {
            tail = prev
        } else {
            next.prev = prev
            current.next = null
        }
        size--
        return value
    }

    // Checks if there can exist an element at that index
    private fun validateElementIndex(index: Int) {
        if (index < 0 || index >= size)
            throw IndexOutOfBoundsException(outOfBoundsMsg(index))
    }

    // Checks if an element can be inserted at that index
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