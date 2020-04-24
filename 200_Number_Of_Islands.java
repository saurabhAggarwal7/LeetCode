/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of
 * islands. An island is surrounded by water and is formed by connecting
 * adjacent lands horizontally or vertically. You may assume all four edges of
 * the grid are all surrounded by water.
 * 
 * Example 1:
 * 
 * Input: 11110 11010 11000 00000
 * 
 * Output: 1 Example 2:
 * 
 * Input: 11000 11000 00100 00011
 * 
 * Output: 3
 */

class number_of_islands {
    static int y; // height of grid
    static int x; // width of grid
    static char[][] g; // given grid, stored to reduce the recursion memory usage

    /**
     * Given a 2d grid map of '1's (land) and '0's (water), count the number of
     * islands.
     * 
     * This method approaches the problem as one of depth-first connected components
     * search
     * 
     * @param grid, the given grid.
     * @return the number of islands.
     */
    static int num_islands(char[][] grid) {
        // stor eth given grid/
        // prevents to make multiple copies of te grid during the recursion
        g = grid;

        // our count to return
        int count = 0;

        // dimensions of the graph:

        // height:
        y = g.length;
        if (y == 0)
            return 0;

        // width:
        x = g[0].length;

        // Iterate over the entire given grid
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (g[i][j] == '1') {
                    dfs(i, j);
                    count++;
                }
            }
        }
        return count;
    }

        /**
     * Marks the given site as visited, then checks adjacent sites.
     * 
     * Or, Marks the given site as water, if land, then checks adjacent sites.
     * 
     * Or, Given one coordinate (i,j) of an island, obliterates the island
     * from the given grid, so that it is not counted again.
     * 
     * @param i, the row index of the given grid
     * @param j, the column index of the given grid
     */
    static void dfs(int i, int j){

        //Check for invalid indices and for sites that aren't land
        if(i<0 || i>=y ||j<0 || j>=x)
            return;
        if(g[i][j] != '1')
            return;
        
        //mark the site as visited:
        g[i][j] = '0';

        //check all adjacent sites:
        dfs(i+1, j);
        dfs(i-1, j);
        dfs(i, j+1);
        dfs(i, j-1);
    }

}
