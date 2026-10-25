# Data Structures with Kotlin

Classic data structures implemented from scratch in Kotlin, for learning and
practice. Each structure is a small, self-contained class with no external
libraries.

## What is implemented

All in `src/dataStructures/` unless noted.

| Category | Structures |
|---|---|
| Lists | `SinglyLinkedList`, `DoublyLinkedList`, `CircularLinkedList`, `LinkedList` (generic, with `+=` / `-=` operators) |
| Stacks and queues | `Stack`, `Queue`, `Deque`, `PriorityQueue` (with `PQElement`), plus `StackUnderflowException` and `QueueUnderflowException` |
| Trees | `BinaryTree`, `BinarySearchTree`, `AVLTree`, `RedBlackTree`, and a generic `BST` in `src/genericDataStructures/` |
| Heaps | `Heap` |
| Hashing | `HashTable`, `HashMap` |
| Graphs | `Graph` |

## Run it

The project is a plain IntelliJ IDEA Kotlin module (no Gradle or Maven).

```bash
git clone https://github.com/NinjaShadowBoy/DataStructuresWithKotlin.git
```

1. Open the folder in IntelliJ IDEA (it includes the Kotlin plugin).
2. Set a JDK as the project SDK if prompted.
3. Run `src/Main.kt`, which builds a `LinkedList` and adds and removes items.
   `AVLTree.kt`, `RedBlackTree.kt` and `genericDataStructures/BST.kt` also have
   their own `main()` demos: click the run icon next to them.

With the command-line Kotlin compiler instead:

```bash
kotlinc src -include-runtime -d ds.jar
java -cp ds.jar MainKt                        # the LinkedList demo
java -cp ds.jar dataStructures.AVLTreeKt      # the AVL tree demo
```
