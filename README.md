# Java LRU Cache System

## Description

This project implements a **Least Recently Used (LRU) Cache System** in Java. The cache is designed to store key-value pairs and supports efficient `get`, `put`, and `remove` operations with constant time complexity. The cache uses a **HashMap** to store key-value mappings and a **LinkedHashMap** to maintain access order, enabling a straightforward LRU eviction policy. The implementation is thread-safe, making it suitable for concurrent access in multi-threaded environments.

## Features

- **LRU Eviction Policy**: Automatically evicts the least recently used items when the cache reaches its capacity.
- **Constant Time Operations**: Both `get` and `put` operations have an average time complexity of O(1).
- **Thread-Safe**: The cache is designed to handle concurrent access safely using Java synchronization mechanisms.
- **Customizable Capacity**: The maximum cache size can be set when initializing the cache.

## Technologies

- **Java**: The cache system is built using core Java (`java.util.HashMap`, `java.util.LinkedHashMap`) for performance and ease of use.
- **Synchronization**: Thread safety is achieved using the `synchronized` keyword.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [API](#api)
- [Contributing](#contributing)
- [License](#license)

## Installation

1. **Clone the repository**:
    ```bash
    git clone https://gitlab.com/your-username/java-lru-cache-system.git
    ```

2. **Navigate to the project directory**:
    ```bash
    cd java-lru-cache-system
    ```

3. **Compile the code**:
    You can use any Java IDE (such as IntelliJ IDEA or Eclipse), or use the command line with the following:
    ```bash
    javac LRUCache.java
    ```

4. **Run the tests**:
    Once compiled, you can run the main method to test the LRU Cache:
    ```bash
    java LRUCache
    ```

## Usage

Here is an example of how to use the LRU cache:

```java
public class Main {
    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);

        // Add some values to the cache
        cache.put(1, "Booking.com");
        cache.put(2, "Amazon");
        cache.put(3, "Google");

        // Retrieve a value from the cache
        System.out.println(cache.get(1)); // Output: Booking.com

        // Insert more values and trigger eviction of the least recently used item
        cache.put(4, "Netflix");

        // After eviction, key 2 should no longer exist
        System.out.println(cache.get(2)); // Output: null
    }
}
