package dataStructures

class HashMap<K, V> {
    private data class Entry<K, V>(val key: K, var value: V){
        var next: Entry<K, V>? = null
    }
    private val capacity = 20
    private val table = arrayOfNulls<Entry<K, V>>(capacity)
    private var size = 0

    // Compute the index in the array
    private fun hash(key: K): Int = (key.hashCode() and Int.MAX_VALUE) % capacity

    // Inserts or updates the key-value pair
    fun put(key: K, value: V): V? {
        val index = hash(key)
        var existing = table[index]
        while(existing != null){
            if(existing.key == key){
                val oldValue = existing.value
                existing.value = value
                return oldValue
            }else{
                existing = existing.next
            }
        }
        val entry = Entry(key, value)
        entry.next = table[index]
        table[index] = entry
        size++
        return null
    }

    // Fetches the key by key
    fun get(key: K): V? {
        var current = table[hash(key)]
        while(current != null){
            if(current.key == key){
                return current.value
            }
            current = current.next
        }
        return null
    }

    // Removes the key-value pair by key
    fun remove(key: K): V? {
        val index = hash(key)
        var previous: Entry<K, V>? = null
        var current = table[index]
        while(current != null){
            if(current.key == key){
                if(previous == null){
                    table[index] = current.next
                }else{
                    previous.next = current.next
                }
                size--
                return current.value
            }
            previous = current
            current = current.next
        }
        return null
    }

    // Returns the size of the map
    fun size() = size

}