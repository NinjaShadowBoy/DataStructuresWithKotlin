package genericDataStructures

import kotlin.random.Random


//val red = "\u001B[31m"
//val green = "\u001B[32m"
//val yellow = "\u001B[33m"
//val blue = "\u001B[34m"
//val reset = "\u001B[0m"

class BST<T : Comparable<T>> {
    /**
     * A heap where the minimum element is at the top and does not allow duplicates
     */
    private var root: TreeNode<T>? = null
    private fun TreeNode<T>.replaceWith(otherNode: TreeNode<T>?) {
        // Replace the current node with the given node. Used in deletion.
        if (this == root) {
            // If the node has no parent
            root = otherNode
        } else if (parent.leftChild == this) {
            // If it is a left child
            parent.leftChild = otherNode
        } else if (parent.rightChild == this) {
            // If it is a right child
            parent.rightChild = otherNode
        }
    }

    private var size = 0

    fun add(value: T) {
        /**
         * Add a new node to the tree
         */

        val newNode = TreeNode(value)
        if (root == null) { // The heap is empty
            root = newNode
        } else {
            var current: TreeNode<T> = root!!
            // While the newNode has not been inserted
            while (true) {
                // If the value is greater
                if (newNode > current) {
                    if (current.rightChild != null) { // Move to the right child if there is one
                        current = current.rightChild!!
                    } else { // If there is no right child put the new node
                        current.rightChild = newNode
                        break
                    }
                } else if (newNode < current) {// If the value is less
                    if (current.leftChild != null) { // Move to the left child if there is one
                        current = current.leftChild!!
                    } else { // If there is no left child put the new node
                        current.leftChild = newNode
                        break
                    }
                } else { // If the value is equal
                    // Do not allow duplicates
                    current.count++
                    break
                }
            }
        }
        size++
    }

    private fun findSmallestDescendant(node: TreeNode<T>): TreeNode<T> {
        // Find the smallest descendant of the given node. Used in deletion.
        var current: TreeNode<T> = node
        while (current.leftChild != null) {
            current = current.leftChild!!
        }
        return current
    }

    private fun findLargestDescendant(node: TreeNode<T>): TreeNode<T> {
        // Find the largest descendant of the given node. Used in deletion.
        var current: TreeNode<T> = node
        while (current.rightChild != null) {
            current = current.rightChild!!
        }
        return current
    }

    fun delete(value: T) {
        var current: TreeNode<T>? = root
        while (current != null) {
            if (current.value == value) {
                // If the value occurs more than once
                if (current.count > 1) {
                    current.count--
                    size--
                    return
                }

                /// If the value occurs once
                deleteAll(current)
                return
            } else {
                current = if (value > current.value) {
                    println("Moving to right child ${current.rightChild?.value}")
                    current.rightChild
                } else {
                    println("Moving to left child ${current.leftChild?.value}")
                    current.leftChild
                }
            }
        }
    }

    fun deleteAll(value: T) {
        var current: TreeNode<T>? = root
        while (current != null) {
            if (current.value == value) {
                deleteAll(current)
                return
            } else {
                current = if (value > current.value) {
                    println("Moving to right child ${current.rightChild?.value}")
                    current.rightChild
                } else {
                    println("Moving to left child ${current.leftChild?.value}")
                    current.leftChild
                }
            }
        }
    }

    private fun deleteAll(current: TreeNode<T>) {
        // If the node has no children
        if (current.leftChild == null && current.rightChild == null) {
            // Remove link with parent, and it will be garbage collected.
            current.replaceWith(null)
            if (current == root) {
                root = null
            }
            size--
            return
        }
        // If the node has children
        val successor: TreeNode<T>
        if (current.leftChild == null) {
            // If the node has only a right child
            successor = current.rightChild!!
        } else if (current.rightChild == null) {
            // If the node has only a left child
            successor = current.leftChild!!
        } else {
            // If the node has both children the successor is the minimum value of the right subtree
            val smallestRightDescendant = findSmallestDescendant(current.rightChild!!)
            smallestRightDescendant.replaceWith(smallestRightDescendant.rightChild) // Delete the node at the end of the tree (avoid circular relationship)
            successor = smallestRightDescendant
            // Link accordingly
            successor.leftChild = current.leftChild
            if (successor != current.rightChild) { // Avoid circular relationship
                successor.rightChild = current.rightChild
            }
        }

        // Replace the current node with the successor in the tree
        current.replaceWith(successor)
        size --
    }

    private fun print(root: TreeNode<T>?, prefix: String, isTail: Boolean) {
        // root: The current node being printed
        // prefix: The string prefix used for indentation and tree structure
        // isTail: A boolean indicating if the current node is the last child (true) or not (false)
        println(prefix + (if (isTail) "└──── " else "├──── ") + root?.value + " (${root?.count})")
        if (root != null) {
            print(
                root.leftChild,
                prefix + if (isTail) "    " else "│   ",
                false
            )
            print(root.rightChild, prefix + if (isTail) "    " else "│   ", true)
        }
    }

    fun print() {
        print(root, "", true)
    }

    operator fun contains(value: T): Boolean {
        /**
         * Check if the value is in the tree
         */
        var current: TreeNode<T>? = root
        while (current != null) {
            if (current.value == value) {
                return true
            }
            current = if (value > current.value) {
                current.rightChild
            } else {
                current.leftChild
            }
        }
        return false
    }

    fun min() = if (root != null) findSmallestDescendant(root!!).value else null
    fun max() = if (root != null) findLargestDescendant(root!!).value else null

    fun search(value: T): Boolean = value in this
}

fun main() {

    val tree = BST<Int>()
    while (true) {
        tree.print()
        print(
            """What do you want to do?
            | 1. Add
            | 2. Delete
            | else. Quit
            |Enter your choice: 
        """.trimMargin()
        )
        val choice = readln()
        val choiceNum = choice.toIntOrNull()
        when (choiceNum) {
            1 -> {
                print("Enter a value to add: ")
                val num = readln().toIntOrNull()
                if (num != null){
                    tree.add(num)
                } else{
                    println("Invalid number")
                }
            }
            2 -> {
                print("Enter a value to delete: ")
                val num = readln().toIntOrNull()
                if (num != null){
                    tree.delete(num)
                } else{
                    println("Invalid number")
                }
            }
            else -> break
        }
    }
}
