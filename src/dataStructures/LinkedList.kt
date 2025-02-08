package dataStructures

class LinkedList<T> {
    private var head: Node<T>? = null

    fun appendNode(value: T) {
        if (head == null) {
            head = Node(value)
        }else{
            var currentNode = head
            while (currentNode!!.next != null) {
                currentNode = currentNode.next
            }
            currentNode.next = Node(value)
        }
    }
    fun delete(value: T) {
        if (head != null){
            if (head?.value == value){
                head = head?.next
                return
            }
            var currentNode = head
            while (currentNode?.next != null && currentNode.next?.value != value) {
                currentNode = currentNode.next
            }
            currentNode?.next = currentNode?.next?.next
        }
    }
    operator fun plusAssign(value: T) {
        appendNode(value)
    }
    operator fun minusAssign(value: T) {
        delete(value)
    }
    fun prependNode(value: T) {
        head = Node(value, head)
    }
    override fun toString(): String {
        var result = "["
        if (head != null) {
            var current = head
            while (current != null) {
                result += current.value.toString() + ", "
                current = current.next
            }
        }
        result += "]"
        return result
    }
}
