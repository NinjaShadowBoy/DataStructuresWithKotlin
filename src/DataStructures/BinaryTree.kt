package DataStructures

class BinaryTree {
    var root: TreeNode? = null

    // Recursive Insertion of data
    fun insert(value: Int) {
        root = insertRecursive(root, value)
    }

    private fun insertRecursive(current: TreeNode?, value: Int): TreeNode {
        if (current == null) {
            return TreeNode(value)
        }
        if (value < current.value) {
            current.left = insertRecursive(current.left, value)
        } else if (value > current.value) {
            current.right = insertRecursive(current.right, value)
        }
        return current
    }

    // Search a value in tree
    fun search(value: Int): Boolean {
        return searchRecursive(root, value)
    }

    private fun searchRecursive(current: TreeNode?, value: Int): Boolean {
        if (current == null) {
            return false
        }
        if (value == current.value) {
            return true
        }
        return if (value < current.value) {
            searchRecursive(current.left, value)
        } else {
            searchRecursive(current.right, value)
        }
    }

    fun delete(value: Int) {
        root = deleteRecursive(root, value)
    }

    private fun deleteRecursive(current: TreeNode?, value: Int): TreeNode? {
        if (current == null) {
            return null
        }
        if (value == current.value) {
            // Check the children
            if (current.left == null && current.right == null) {
                return null
            }
            if (current.right == null) {
                return current.left
            }
            if (current.left == null) {
                return current.right
            }
            val smallestValue = findSmallestValue(current.right!!)
            current.value = smallestValue
            current.right = deleteRecursive(current.right, smallestValue)
            return current
        }

        if (value < current.value) {
            current.left = deleteRecursive(current.left, value)
            return current
        }
        current.right = deleteRecursive(current.right, value)
        return current
    }

    private fun findSmallestValue(root: TreeNode): Int {
        return if (root.left == null) {
            root.value
        } else {
            findSmallestValue(root.left!!)
        }
    }

    // Different tree traversal methods
    fun preOrderTraversal(node: TreeNode?) {
        if (node != null) {
            println(node.value)
            preOrderTraversal(node.left)
            preOrderTraversal(node.right)
        }
    }

    fun inOrderTraversal(node: TreeNode?) {
        if (node != null) {
            inOrderTraversal(node.left)
            println(node.value)
            inOrderTraversal(node.right)
        }
    }

    fun postOrderTraversal(node: TreeNode?) {
        if (node != null) {
            postOrderTraversal(node.left)
            postOrderTraversal(node.right)
            println(node.value)
        }
    }

}