import java.util.*;

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            
            for (int j = 0; j <= i; j++) {
                // First and last element of each row is 1
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    // Middle elements = sum of 2 elements above it
                    int val = result.get(i - 1).get(j - 1) + result.get(i - 1).get(j);
                    row.add(val);
                }
            }
            result.add(row);
        }
        return result;
    }
}