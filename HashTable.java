public class HashTable {
    private String[] table;
    private int size;
    private int itemCount;
    private int collisions;
    private static final double LOAD_FACTOR_THRESHOLD = 0.5;

    public HashTable() {
        this.size = 31;
        this.table = new String[size];
        this.itemCount = 0;
        this.collisions = 0;
    }

    private int hash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = 31 * hash + key.charAt(i);
        }
        return Math.abs(hash) % size;
    }

    public void insert(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Cannot insert null value");
        }

        if ((double) (itemCount + 1) / size > LOAD_FACTOR_THRESHOLD) {
            resize();
        }

        if (itemCount >= size) {
            throw new IllegalStateException("Hash table is full");
        }

        int originalIndex = hash(word);
        int index = originalIndex;
        int i = 1;

        while (table[index] != null) {
            collisions++;
            index = (originalIndex + i * i) % size;
            i++;
            
            // Prevent infinite loop if no empty slot found
            if (i > size) {
                throw new IllegalStateException("Unable to find empty slot");
            }
        }

        table[index] = word;
        itemCount++;
    }

    private void resize() {
        String[] oldTable = table;
        int oldCollisions = collisions;
        size = size * 2;
        table = new String[size];
        itemCount = 0;
        collisions = 0;

        for (String word : oldTable) {
            if (word != null) {
                insert(word);
            }
        }
        
        collisions += oldCollisions;
    }

    public int getCollisions() {
        return collisions;
    }

    public int getSize() {
        return size;
    }

    public int getItemCount() {
        return itemCount;
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable();
        String[] words = {
            "dog", "cat", "rat", "bird", "fish",
            "lion", "bear", "wolf", "deer", "frog",
            "snake", "tiger", "horse", "sheep", "goat",
            "rabbit", "mouse", "eagle", "hawk", "duck"
        };

        for (String word : words) {
            hashTable.insert(word);
            System.out.printf("Inserted: %-6s Size: %-4d Items: %-3d Collisions: %d%n", 
                            word, hashTable.getSize(), hashTable.getItemCount(), 
                            hashTable.getCollisions());
        }

        System.out.println("\nFinal table size: " + hashTable.getSize());
        System.out.println("Total items: " + hashTable.getItemCount());
        System.out.println("Total collisions: " + hashTable.getCollisions());
    }
}
