package DataStructures

class HashTable<K, V> (private val size: Int){
    private val storage: Array<MutableList<Pair<K, V>>> = Array(size){mutableListOf()}


    // hash function
    private fun hash(key: K) = key.hashCode()%size

    // Insertion of a key-value pair
    fun put(key: K, value: V) {
        val index = hash(key)
        storage[index] += Pair(key, value)
    }

    // Retrieve a value for the given key from the table
    fun get(key: K): V? {
        val index = hash(key)
        for(entry in storage[index]){
            if(entry.first == key){
                return entry.second
            }
        }
        return null
    }

    // Remove a K-V pair from the has table
    fun remove(key: K) {
        val index = hash(key)
        val iterator = storage[index].iterator()
        while(iterator.hasNext()){
            if (iterator.next() == key){
                iterator.remove()
                return
            }
        }
    }
}