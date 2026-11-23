package connectFour;

enum DiscColor
{
    RED,
    YELLOW
}

public class Board {
    private static final int rows = 6;
    private static final int colms = 7;
    private final DiscColor[][] grid;

    Board()
    {
        this.grid = new DiscColor[rows][colms];
    }

    public int getRows()
    {
        return rows;
    }

    public int getColms()
    {
        return colms;
    }

    public boolean canPlace(int colm)
    {
        if(colm < 0 || colm >= colms)   return false;
        if(grid[0][colm] == null)   return true;  
        return false;
    }

    public int placeDisc(int colm, DiscColor disc)
    {
        if(disc == null || canPlace(colm) == false)  return -1;
        for(int row = rows - 1; row >= 0; row--)
        {
            if(grid[row][colm] == null)
            {
                grid[row][colm] = disc;
                return row;
            }
        }
        return -1;
    }

    public boolean isFull()
    {
        for(int i = 0; i < colms; i++)
        {
            if(grid[0][i] == null) return false;
        }
        return true;
    }

    public boolean checkWin(int row, int colm, DiscColor color)
    {
        if(color == null || inBound(row, colm) == false || grid[row][colm] != color) return false;

        int[][] directions = new int[][] {{1, 0}, {0, 1}, {1, 1}, {-1, 1}};

        for(int i = 0; i < 4; i++)
        {
            int count = 1;
            count += checkDirection(row, colm, directions[i][0], directions[i][1], color);
            count += checkDirection(row, colm, -directions[i][0], -directions[i][1], color);
            if(count >= 4)  return true;
        }
        return false;
    }

    int checkDirection(int row, int colm, int dx, int dy, DiscColor color)
    {
        int newRow = row + dx;
        int newColm = colm + dy;
        int count = 0;
        while(inBound(newRow, newColm) && grid[newRow][newColm] == color)
        {
            count++;
            newRow += dx;
            newColm += dy;
        }
        return count;
    }

    public boolean inBound(int row, int colm)
    {
        return (row >= 0 && colm >= 0 && row < rows && colm < colms);
    }
}
