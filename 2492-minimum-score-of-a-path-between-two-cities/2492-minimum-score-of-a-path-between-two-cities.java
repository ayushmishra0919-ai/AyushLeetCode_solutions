class Solution {
    int minScore = Integer.MAX_VALUE;
    
    public int minScore(int n, int[][] roads) {
        List<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        
        for (int[] r : roads) {
            int a = r[0], b = r[1], dist = r[2];
            graph[a].add(new int[]{b, dist});
            graph[b].add(new int[]{a, dist});
        }
        
        boolean[] visited = new boolean[n + 1];
        dfs(1, graph, visited);
        return minScore;
    }
    
    void dfs(int node, List<int[]>[] graph, boolean[] visited) {
        visited[node] = true;
        for (int[] nei : graph[node]) {
            int next = nei[0], dist = nei[1];
            minScore = Math.min(minScore, dist);
            if (!visited[next]) {
                dfs(next, graph, visited);
            }
        }
    }
}