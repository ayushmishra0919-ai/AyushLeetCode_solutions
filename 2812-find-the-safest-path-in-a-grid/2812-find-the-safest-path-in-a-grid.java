class Solution {
    private int n;
    private int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};
    
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        n = grid.size();
        int[][] dist = new int[n][n];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        
        Queue<int[]> q = new LinkedList<>();
        
        // Step 1: Multi-source BFS from all thieves
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    q.offer(new int[]{i, j});
                    dist[i][j] = 0;
                }
            }
        }
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int[] d : dirs) {
                int x = cur[0] + d[0], y = cur[1] + d[1];
                if (x >= 0 && y >= 0 && x < n && y < n && dist[x][y] == Integer.MAX_VALUE) {
                    dist[x][y] = dist[cur[0]][cur[1]] + 1;
                    q.offer(new int[]{x, y});
                }
            }
        }
        
        // Step 2: Binary search on safeness factor
        int left = 0, right = 2 * n, ans = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canReach(dist, mid)) {
                ans = mid;
                left = mid + 1; // try for bigger safeness
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }
    
    // Check if we can go from (0,0) to (n-1,n-1) using cells with dist >= limit
    private boolean canReach(int[][] dist, int limit) {
        if (dist[0][0] < limit || dist[n-1][n-1] < limit) return false;
        
        Queue<int[]> q = new LinkedList<>();
        boolean[][] vis = new boolean[n][n];
        q.offer(new int[]{0, 0});
        vis[0][0] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] == n - 1 && cur[1] == n - 1) return true;
            
            for (int[] d : dirs) {
                int x = cur[0] + d[0], y = cur[1] + d[1];
                if (x >= 0 && y >= 0 && x < n && y < n &&!vis[x][y] && dist[x][y] >= limit) {
                    vis[x][y] = true;
                    q.offer(new int[]{x, y});
                }
            }
        }
        return false;
    }
}