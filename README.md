# Word Frequency Using BST and HashMap

## Project Overview
This repository implements and compares three MapSet data structures to count word frequencies in two corpora—Reddit comments (2015) and Shakespeare’s complete works—and measures build times, structural depth, and top-10 words to illustrate performance trade-offs and linguistic differences.

## Core Features

### MapSet Implementations
- **BSTMap<K,V>**  
  Node-based binary search tree with recursive `insert`, `get`, `remove`, and `maxDepth`.
- **HashMap<K,V>**  
  Separate-chaining hash table with dynamic resizing (grow/shrink) and O(1) amortized operations.
- **ArrayListMap<K,V>**  
  Simple `ArrayList` backing for linear scans (O(n) per operation).

### WordCounter
- Reads and tokenizes large text files.
- Builds frequency maps and times the insertion process.
- Reports total and unique word counts; retrieves individual frequencies.
- Exports word-count files.
- Provides `getTop10Words()` to list the ten most frequent tokens.

### Benchmarking & Analysis
1. **Top-10 word comparison** between corpora  
2. **Average `buildMap(...)` times** over multiple runs  
3. **`maxDepth()` or bucket-depth analysis** to explain performance differences  

### Extensions
- **BSTMap Balance-Factor Analysis**: Counts nodes with \|height(left) − height(right)\| > 1.  
- **Custom Hash Function**: Polynomial rolling hash vs. Java’s `hashCode()`.  
- **ArrayListMap Comparison**: Demonstrates the cost of linear-scan maps on large data.

## Experiments & Results

| Structure     | Reddit Time (ms) | Shakespeare Time (ms) | Reddit Depth | Shakespeare Depth |
|---------------|------------------|-----------------------|--------------|-------------------|
| **BSTMap**    | 7,190.8          | 147.5                 | 38           | 35                |
| **HashMap**   | 1,272.8          | 26.3                  | 6            | 5                 |
| **ArrayListMap** | 339,558       | 5,410                 | unique words            | unique words               |


### Top-10 Words
- **Reddit:** like, dont, would, get, one, people, think, really, time, good  
- **Shakespeare:** thou, thy, shall, thee, lord, king, good, sir, come, well  

## Getting Started

### Prerequisites
- Java 8 (or later)  
- Git

### Clone & Build
```bash
git clone https://github.com/Muneeb-ii/Word_Frequency_Using_BST_and_HashMap.git
cd Word_Frequency_Using_BST_and_HashMap
javac *.java # Compile in each sub dir separately
```
### Usage
Modify the main in `WordCounterExt.java` to switch datasets, run benchmarks, or export top-10 lists.
```bash
# Run the WordCounter file in the extension dir
java WordCounterExt
```
## Code Structure
- **BSTMap.java** – Generic binary-search tree implementation of MapSet
- **HashMap.java / HashMapExt.java** – Separate-chaining hash table with dynamic resizing
- **ArrayListMap.java** – Linear-scan list-based MapSet
- **WordCounter.java / WordCounterExt.java** – Tokenization, map building, timing, and reporting
- **MapSet.java** – Interface and KeyValuePair<K,V> inner class

## Acknowledgments
- Original lab specifications and starter code by CS231 instructors.
- [Hash-function inspiration from GeeksforGeeks.](https://www.geeksforgeeks.org/string-hashing-using-polynomial-rolling-hash-function/)
- Special thanks to peers for their feedback and support.


