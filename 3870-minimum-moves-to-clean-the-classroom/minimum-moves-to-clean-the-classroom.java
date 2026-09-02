import java.util.*;

class Solution {

    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

    
        int[][] litterId = new int[m][n];

        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        int startX = 0;
        int startY = 0;
        int litterCount = 0;

        
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startX = i;
                    startY = j;
                }

                else if (ch == 'L') {
                    litterId[i][j] = litterCount;
                    litterCount++;
                }
            }
        }

        
        if (litterCount == 0) {
            return 0;
        }

        
        boolean[][][][] visited =
                new boolean[m][n][energy + 1][1 << litterCount];

        
        Queue<int[]> queue = new LinkedList<>();

        
        int initialMask = (1 << litterCount) - 1;

        queue.offer(new int[] {
                startX,
                startY,
                energy,
                initialMask
        });

        visited[startX][startY][energy][initialMask] = true;

        
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        int moves = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            
            while (size-- > 0) {

                int[] current = queue.poll();

                int r = current[0];
                int c = current[1];
                int currEnergy = current[2];
                int mask = current[3];

                
                if (mask == 0) {
                    return moves;
                }

                
                if (currEnergy == 0) {
                    continue;
                }

            
                for (int d = 0; d < 4; d++) {

                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    
                    if (nr < 0 || nr >= m ||
                        nc < 0 || nc >= n) {
                        continue;
                    }

                    
                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    char nextCell = classroom[nr].charAt(nc);

                    
                    int newEnergy;

                    if (nextCell == 'R') {
                        newEnergy = energy;
                    } else {
                        newEnergy = currEnergy - 1;
                    }

                    
                    int newMask = mask;

                    
                    if (nextCell == 'L') {

                        int id = litterId[nr][nc];

                        
                        newMask = newMask & ~(1 << id);
                    }

                    
                    if (!visited[nr][nc][newEnergy][newMask]) {

                        visited[nr][nc][newEnergy][newMask] = true;

                        queue.offer(new int[] {
                                nr,
                                nc,
                                newEnergy,
                                newMask
                        });
                    }
                }
            }

        
            moves++;
        }

        return -1;
    }
}