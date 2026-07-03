class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        List<int[]>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        int hi = 0;
        for (int[] e : edges) {
            g[e[0]].add(new int[]{e[1], e[2]});
            hi = Math.max(hi, e[2]);
        }

        int lo = 0, ans = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (dfs(0, g, online, k, mid, n, new Long[n]) >= 0) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }

    // returns min cost to reach n-1 from u, or -1 if impossible
    private long dfs(int u, List<int[]>[] g, boolean[] online, long k, int minE, int n, Long[] memo) {
        if (u == n - 1) return 0;
        if (memo[u]!= null) return memo[u];
        
        long res = Long.MAX_VALUE;
        for (int[] e : g[u]) {
            int v = e[0], w = e[1];
            if (w < minE) continue;
            if (v!= n - 1 &&!online[v]) continue;
            long sub = dfs(v, g, online, k, minE, n, memo);
            if (sub!= -1 && sub + w <= k) {
                res = Math.min(res, sub + w);
            }
        }
        return memo[u] = res == Long.MAX_VALUE? -1 : res;
    }
}