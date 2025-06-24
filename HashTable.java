public class HashTable {
    private String[] table;
    private int size;
    private int itemCount;
    private int collisions;
    private static final double LOAD_FACTOR_THRESHOLD = 0.5;
    
    // Prime numbers for better hash distribution
    private static final int[] PRIME_SIZES = {
        31, 61, 127, 251, 509, 1021, 2039, 4093, 8191, 16381, 32749, 65521
    };
    private int sizeIndex;

    public HashTable() {
        this.sizeIndex = 0;
        this.size = PRIME_SIZES[sizeIndex];
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

        insertHelper(word, true);
    }
    
    private void insertHelper(String word, boolean countCollisions) {
        if (itemCount >= size) {
            throw new IllegalStateException("Hash table is full");
        }

        int originalIndex = hash(word);
        int index = originalIndex;
        int i = 1;

        while (table[index] != null) {
            if (countCollisions) {
                collisions++;
            }
            index = (originalIndex + i * i) % size;
            i++;
            
            // Prevent infinite loop - for quadratic probing, we need at most size probes
            if (i > size) {
                throw new IllegalStateException("Unable to find empty slot");
            }
        }

        table[index] = word;
        itemCount++;
    }

    private void resize() {
        String[] oldTable = table;
        
        // Move to next prime size
        if (sizeIndex < PRIME_SIZES.length - 1) {
            sizeIndex++;
            size = PRIME_SIZES[sizeIndex];
        } else {
            // If we run out of predefined primes, double and find next odd number
            size = size * 2 + 1;
        }
        
        table = new String[size];
        int oldItemCount = itemCount;
        itemCount = 0;
        // Don't reset collisions - we want to preserve user collision count

        // Rehash all existing items without counting collisions
        for (String word : oldTable) {
            if (word != null) {
                insertHelper(word, false); // false = don't count collisions during rehash
            }
        }
    }

    public boolean search(String word) {
        if (word == null) {
            return false;
        }
        
        int originalIndex = hash(word);
        int index = originalIndex;
        int i = 1;
        
        while (table[index] != null) {
            if (table[index].equals(word)) {
                return true;
            }
            index = (originalIndex + i * i) % size;
            i++;
            
            if (i > size) {
                break;
            }
        }
        return false;
    }

    public void displayTable() {
        System.out.println("\nHash Table Contents:");
        for (int i = 0; i < size; i++) {
            if (table[i] != null) {
                System.out.printf("Index %2d: %s%n", i, table[i]);
            }
        }
    }

    public double getLoadFactor() {
        return (double) itemCount / size;
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

        System.out.println("=== Hash Table Insertion Demo ===");
        System.out.printf("%-12s %-6s %-6s %-11s %-12s%n", 
                         "Word", "Size", "Items", "Collisions", "Load Factor");
        System.out.println("-------------------------------------------------------");

        for (String word : words) {
            hashTable.insert(word);
            System.out.printf("%-12s %-6d %-6d %-11d %-12.3f%n", 
                            word, hashTable.getSize(), hashTable.getItemCount(), 
                            hashTable.getCollisions(), hashTable.getLoadFactor());
        }

        System.out.println("\n=== Final Statistics ===");
        System.out.println("Final table size: " + hashTable.getSize());
        System.out.println("Total items: " + hashTable.getItemCount());
        System.out.println("Total collisions: " + hashTable.getCollisions());
        System.out.printf("Final load factor: %.3f%n", hashTable.getLoadFactor());
        
        // Display the hash table contents
        hashTable.displayTable();
        
        // Demonstrate search functionality
        System.out.println("\n=== Search Demo ===");
        String[] searchWords = {"dog", "cat", "elephant", "tiger"};
        for (String word : searchWords) {
            boolean found = hashTable.search(word);
            System.out.printf("Searching for '%s': %s%n", word, found ? "Found" : "Not found");
        }
    }
}
