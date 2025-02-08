package dataStructures

class BinarySearchTree {
    var root: TreeNode? = null

    // Insert data into the BST
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

    // Search for a value in the tree
    fun search(value: Int): Boolean {
        return searchRecursive(root, value)
    }

    private fun searchRecursive(current: TreeNode?, value: Int): Boolean {
        if (current == null) {
            return false
        }
        if (current.value == value) {
            return true
        }
        return if (value < current.value) {
            searchRecursive(current.left, value)
        } else {
            searchRecursive(current.right, value)
        }
    }

    // Delete a value from the tree
    fun delete(value: Int) {
        root = deleteRecursive(root, value)
    }

    private fun deleteRecursive(current: TreeNode?, value: Int): TreeNode? {
        if (current == null) {
            return null
        }
        if (value == current.value) {
            if (current.left == null && current.right == null) {
                return null
            }
            if (current.left == null) {
                return current.right
            }
            if (current.right == null) {
                return current.left
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

    fun findMin(): Int? {
        if (root == null) {
            return null
        }
        return findSmallestValue(root!!)
    }

    private fun findMaxRecursive(node: TreeNode?): Int? {
        if (node == null) {
            return null
        }
        return if (node.right == null) {
            node.value
        } else {
            findMaxRecursive(node.right)
        }
    }

    fun findMax(): Int? {
        return findMaxRecursive(root)
    }

    // Tree traversal methods
    fun inOrderTraversal(node: TreeNode?) {
        if (node != null) {
            inOrderTraversal(node.left)
            println(node.value)
            inOrderTraversal(node.right)
        }
    }

    fun preOrderTraversal(node: TreeNode?) {
        if (node != null) {
            println(node.value)
            preOrderTraversal(node.left)
            preOrderTraversal(node.right)
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