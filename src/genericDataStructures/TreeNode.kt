package genericDataStructures

class TreeNode<T : Comparable<T>>(var value: T): Comparable<TreeNode<T>> {
    var leftChild: TreeNode<T>? = null
        set(child) {
            field = child
            if (field != null) {
                field!!.parent = this
            }
        }
    var rightChild: TreeNode<T>? = null
        set(child) {
            field = child
            if (field != null){
                field!!.parent = this
            }
        }

    var count = 1

    internal lateinit var parent: TreeNode<T>

    override fun compareTo(other: TreeNode<T>): Int {
        return value.compareTo(other.value)
    }
}