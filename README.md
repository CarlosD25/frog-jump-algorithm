# Frog Jump Algorithm

An efficient Java solution for the **Frog Jump** problem using Depth-First Search (DFS), Backtracking, and Memoization.

---

## Problem Statement

A frog is attempting to cross a river. The river is divided into units, and at each unit, there may or may not exist a stone. The frog can jump on a stone, but it must never jump into the water.

Given an array of stone positions in units, determine if the frog can cross the river by landing on the last stone.

### Rules & Constraints
1. **Initial Position & First Jump:** The frog starts at `stones[0] == 0`. Its first jump must strictly be **$1$ unit forward**, landing on the stone at coordinate `1`.
2. **Jump Range:** If the frog's previous jump was $k$ units, the next jump must be either $k - 1$, $k$, or $k + 1$ units.
3. **Direction:** The frog can only jump in the forward direction ($jump > 0$).
4. **Input Constraints:**
   - $2 \le \text{stones.length} \le 2000$
   - $0 \le \text{stones}[i] \le 2^{31} - 1$
   - Stones are strictly increasing positive integers.

---

## Solution Architecture & Key Decisions

The algorithm implements a recursive backtracking strategy enhanced by state memoization and constant-time lookups:

### 1. Robust Input Sanitization
- **Sorting Fallback:** Uses `Arrays.sort(stones)` to guarantee strictly ascending order, gracefully handling unsorted input data.
- **Early Boundary Validations:** Fails immediately if the array length violates constraints, if `stones[0] != 0`, or if `stones[1] != 1` (impossible first jump).

### 2. $O(1)$ River Representation
Instead of querying linear collections via $O(N)$ searches, all stone coordinates are loaded into a `HashSet<Integer>`. This enables instant verification of whether a target landing coordinate is a solid stone or water.

### 3. State Memoization (Pruning Dead Ends)
A state is uniquely identified by the tuple `(currentPosition, k)`:
- Multiple paths can land on the same stone with the exact same momentum $k$.
- Without memoization, exploring all branches yields an exponential time complexity of $O(3^N)$, resulting in **Time Limit Exceeded (TLE)** on arrays of up to $2000$ elements.
- When all three jump offsets ($\{-1, 0, 1\}$) from a given state fail, the key `"position,k"` is recorded in a `HashSet<String> failedStates`. Subsequent paths reaching this state are pruned immediately in $O(1)$ time.

---

## Complexity Analysis

| Metric | Complexity | Explanation |
| :--- | :--- | :--- |
| **Time Complexity** | $O(N^2)$ | In the worst-case scenario, at most $N$ stones can be paired with at most $N$ distinct jump lengths $k$, bounding total state explorations. |
| **Space Complexity** | $O(N^2)$ | Required to store the memoized states in `failedStates` and manage the call stack during deep recursions. |

---

## Project Structure

```text
frog-jump/
│
├── src/
│   ├── FrogJump.java     # Core algorithm implementation
│   └── Main.java         # Test suite covering edge cases
│
├── .gitignore
└── README.md
```

---

## Getting Started

### Prerequisites
- **Java Development Kit (JDK):** Version 11 or higher installed on your machine.
- **Git:** Version control CLI.

---

## How to Run

### 1. Clone the Repository
```bash
git clone https://github.com/CarlosD25/frog-jump-algorithm.git
cd frog-jump-algorithm
```

### 2. Compile the Source Code
Compile the Java files inside the `src` directory and output the bytecode into the `bin` directory:
```bash
javac -d bin src/*.java
```

### 3. Run the Test Suite
Execute the `Main` class using the compiled classpath:
```bash
java -cp bin Main
```

---

## Expected Output
```text
Example 1: true
Example 2: false
Example 3 (Memoization dead end): false
Unsorted: true
Invalid start: false
```