class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals;
        
        // 1. Sort by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        List<int[]> merged = new ArrayList<>();
        int[] current = intervals[0];
        merged.add(current);
        
        for (int[] interval : intervals) {
            int currentEnd = current[1];
            int nextStart = interval[0];
            int nextEnd = interval[1];
            
            if (currentEnd >= nextStart) { // Overlap, merge them
                current[1] = Math.max(currentEnd, nextEnd);
            } else { // No overlap, add new interval
                current = interval;
                merged.add(current);
            }
        }
        
        return merged.toArray(new int[merged.size()][]);
    }
}