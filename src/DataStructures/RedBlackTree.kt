package DataStructures

enum class Color {
    RED, BLACK
}

class RBNode(var value: Int, var color: Color, var parent: RBNode? = null) {
    var left: RBNode? = null
    var right: RBNode? = null
}

class RedBlackTree {
    private var root: RBNode? = null

    private fun leftRotate(x: RBNode) {
        val y = x.right!!
        x.right = y.left
        if (y.left != null) {
            y.left!!.parent = x
        }
        y.parent = x.parent
        if (x.parent == null) {
            root = y
        } else if (x == x.parent!!.left) {
            x.parent!!.left = y
        } else {
            x.parent!!.right = y
        }
        y.left = x
        x.parent = y
    }

    private fun rightRotate(y: RBNode) {
        val x = y.left!!
        y.left = x.right
        if (x.right != null) {
            x.right!!.parent = y
        }
        x.parent = y.parent
        if (y.parent == null) {
            root = x
        } else if (y == y.parent!!.right) {
            y.parent!!.right = x
        } else {
            y.parent!!.left = x
        }
        x.right = y
        y.parent = x
    }

    fun insert(value: Int) {
        var z = RBNode(value, Color.RED)
        var y: RBNode? = null
        var x = root
        while (x != null) {
            y = x
            if (z.value < x.value) {
                x = x.left
            } else {
                x = x.right
            }
        }
        z.parent = y
        if (y == null) {
            root = z
        } else if (z.value < y.value) {
            y.left = z
        } else {
            y.right = z
        }
        if (z.parent == null) {
            z.color = Color.BLACK
            return
        }
        if (z.parent!!.parent == null) {
            return
        }
        fixViolations(z)
    }

    private fun fixViolations(z: RBNode) {
        var z = z
        while (z.parent != null && z.parent!!.color == Color.RED) {
            var y: RBNode? = null
            if (z.parent == z.parent!!.parent!!.left) {
                y = z.parent!!.parent!!.right
                if (y != null && y.color == Color.RED) {
                    z.parent!!.color = Color.BLACK
                    y.color = Color.BLACK
                    z.parent!!.parent!!.color = Color.RED
                    z = z.parent!!.parent!!
                } else {
                    if (z == z.parent!!.right) {
                        z = z.parent!!
                        leftRotate(z)
                    }
                    z.parent!!.color = Color.BLACK
                    z.parent!!.parent!!.color = Color.RED
                    rightRotate(z.parent!!.parent!!)
                }
            } else {
                y = z.parent!!.parent!!.left
                if (y != null && y.color == Color.RED) {
                    z.parent!!.color = Color.BLACK
                    y.color = Color.BLACK
                    z.parent!!.parent!!.color = Color.RED
                    z = z.parent!!.parent!!
                } else {
                    if (z == z.parent!!.left) {
                        z = z.parent!!
                        rightRotate(z)
                    }
                    z.parent!!.color = Color.BLACK
                    z.parent!!.parent!!.color = Color.RED
                    leftRotate(z.parent!!.parent!!)
                }
            }
        }
        root!!.color = Color.BLACK
    }

    fun inorder() = inorderHelper(root)
    private fun inorderHelper(root: RBNode?) {
        if (root == null) return
        inorderHelper(root.left)
        println("${root.value} - ${root.color}")
        inorderHelper(root.right)
    }
}

// client code to test Red-Black Tree Implementation in Kotlin
fun main() {
    val tree = RedBlackTree()
    tree.insert(7)
    tree.insert(6)
    tree.insert(5)
    tree.insert(4)
    tree.insert(3)
    tree.insert(2)
    tree.insert(1)
    tree.inorder()
}
