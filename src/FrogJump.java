import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FrogJump {

    public boolean canCross(int[] stones) {
        
        if (stones == null || stones.length < 2 || stones.length > 2000) {
            return false;
        }
        
        Arrays.sort(stones);
        
        if (stones[0] != 0) {
            return false;
        }

        if (stones[1] != 1) {
            return false;
        }

        Set<Integer> stonePositions = new HashSet<>();
        for (int stone : stones) {
            stonePositions.add(stone);
        }

        int targetPosition = stones[stones.length - 1];
        
        Set<String> failedStates = new HashSet<>();

        return solve(1, 0, 1, stonePositions, targetPosition, failedStates);
    }

    private boolean solve(
            int currentPosition,
            int previousPosition,
            int k,
            Set<Integer> stonePositions,
            int targetPosition,
            Set<String> failedStates) {

        if (currentPosition <= previousPosition) {
            return false;
        }

        if (!stonePositions.contains(currentPosition)) {
            return false;
        }

        if (currentPosition == targetPosition) {
            return true;
        }

        String stateKey = currentPosition + "," + k;
        if (failedStates.contains(stateKey)) {
            return false;
        }

        for (int offset = -1; offset < 2; offset++) {
            int nextPosition = currentPosition + k + offset;
            int nextK = nextPosition - currentPosition;

            if (solve(nextPosition, currentPosition, nextK, stonePositions, targetPosition, failedStates)) {
                return true;
            }
        }

        failedStates.add(stateKey);
        return false;
    }
}