public class Main {
    public static void main(String[] args) {
        FrogJump frogJump = new FrogJump();

        int[] stones1 = {0, 1, 3, 5, 6, 8, 12, 17};
        System.out.println("Example 1: " + frogJump.canCross(stones1)); 

        int[] stones2 = {0, 1, 2, 3, 4, 8, 9, 11};
        System.out.println("Example 2: " + frogJump.canCross(stones2)); 

        int[] stones3 = {0, 1, 3, 4, 6, 9, 100};
        System.out.println("Example 3 (Memoization dead end): " + frogJump.canCross(stones3)); 

        int[] stonesUnsorted = {17, 3, 0, 8, 1, 5, 12, 6};
        System.out.println("Unsorted: " + frogJump.canCross(stonesUnsorted)); 

        int[] stonesInvalidStart = {0, 2, 3};
        System.out.println("Invalid start: " + frogJump.canCross(stonesInvalidStart)); 
    }
}