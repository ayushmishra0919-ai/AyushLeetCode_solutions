class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size(), n = grid.get(0).size();
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        
        int[][] maxHealth = new int[m][n];
        for (int[] row : maxHealth) Arrays.fill(row, -1);
        
        Deque<int[]> dq = new ArrayDeque<>();
        
        int startHealth = health - grid.get(0).get(0);
        if (startHealth <= 0) return false;
        
        maxHealth[0][0] = startHealth;
        dq.offerFirst(new int[]{0, 0, startHealth});
        
        while (!dq.isEmpty()) {
            int[] cur = dq.pollFirst();
            int r = cur[0], c = cur[1], h = cur[2];
            
            if (r == m - 1 && c == n - 1) return true;
            
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int nh = h - grid.get(nr).get(nc);
                    
                    // FIX: health positive rehna chahiye
                    if (nh > 0 && nh > maxHealth[nr][nc]) {
                        maxHealth[nr][nc] = nh;
                        if (grid.get(nr).get(nc) == 0) {
                            dq.offerFirst(new int[]{nr, nc, nh});
                        } else {
                            dq.offerLast(new int[]{nr, nc, nh});
                        }
                    }
                }
            }
        }
        return false;
    }
}