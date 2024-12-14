package DataStructures

class AVLNode(var value: Int) {
    var height: Int = 1
    var left: AVLNode? = null
    var right: AVLNode? = null
}

class AVLTree {
    private fun height(node: AVLNode?): Int {
        return node?.height ?: 0
    }

    private fun updateHeight(node: AVLNode) {
        node.height = 1 + maxOf(height(node.left), height(node.right))
    }

    private fun balanceFactor(node: AVLNode?): Int {
        return height(node?.left) - height(node?.right)
    }

    private fun leftRotate(x: AVLNode): AVLNode {
        val y = x.right!!
        val T2 = y.left
        y.left = x
        x.right = T2
        updateHeight(x)
        updateHeight(y)
        return y
    }

    private fun rightRotate(y: AVLNode): AVLNode {
        val x = y.left!!
        val T2 = x.right
        x.right = y
        y.left = T2
        updateHeight(y)
        updateHeight(x)
        return x
    }

    fun insert(root: AVLNode?, value: Int): AVLNode {
        if (root == null) return AVLNode(value)
        if (value < root.value) root.left = insert(root.left, value)
        else if (value > root.value) root.right = insert(root.right, value)
        else return root
        updateHeight(root)
        val balance = balanceFactor(root)
        if (balance > 1) {
            if (value < root.left!!.value) {
                return rightRotate(root)
            } else {
                root.left = leftRotate(root.left!!)
                return rightRotate(root)
            }
        }
        if (balance < -1) {
            if (value > root.right!!.value) {
                return leftRotate(root)
            } else {
                root.right = rightRotate(root.right!!)
                return leftRotate(root)
            }
        }
        return root
    }

    fun delete(root: AVLNode?, value: Int): AVLNode? {
        var r: AVLNode? = root
        if (r == null) return r
        if (value < r.value) {
            r.left = delete(r.left, value)
        } else if (value > r.value) {
            r.right = delete(r.right, value)
        } else {
            if (r.left == null || r.right == null) {
                var temp: AVLNode? = null
                temp = r.left ?: r.right
                if (temp == null) {
                    temp = r
                    r = null
                } else {
                    r = temp
                }
            } else {
                val temp = minValueNode(r.right!!)
                r.value = temp.value
                r.right = delete(r.right, temp.value)
            }
        }
        if (r == null) return r
        updateHeight(r)
        val balance = balanceFactor(r)
        if (balance > 1) {
            if (balanceFactor(r.left) >= 0) {
                return rightRotate(r)
            } else {
                r.left = leftRotate(r.left!!)
                return rightRotate(r)
            }
        }
        if (balance < -1) {
            if (balanceFactor(r.right) <= 0) {
                return leftRotate(r)
            } else {
                r.right = rightRotate(r.right!!)
                return leftRotate(r)
            }
        }
        return r
    }

    private fun minValueNode(node: AVLNode): AVLNode {
        var current = node
        while (current.left != null) current = current.left!!
        return current
    }

    fun preOrder(root: AVLNode?) {
        if (root != null) {
            println(root.value)
            preOrder(root.left)
            preOrder(root.right)
        }
    }
}

// client code to test AVL Tree Implementation in Kotlin
fun main() {
    var tree: AVLNode? = null
    val avlTree = AVLTree()
    tree = avlTree.insert(tree, 10)
    tree = avlTree.insert(tree, 20)
    tree = avlTree.insert(tree, 30)
    tree = avlTree.insert(tree, 40)
    tree = avlTree.insert(tree, 50)
    tree = avlTree.insert(tree, 25)
    println("Preorder traversal of constructed AVL tree:")
    avlTree.preOrder(tree)
    tree = avlTree.delete(tree, 10)
    println("\nPreorder traversal after deleting 10:")
    if (tree == null) println("WEEERR")
    avlTree.preOrder(tree)
}

