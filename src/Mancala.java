public class Mancala
{
    private int[] board;

    public Mancala()
    {
        board = new int[14];
        for (int i = 0; i < 13; i++)
            if(i != 6)
                board[i] = 4;
    }
    public Mancala(int[] board)
    {
        this.board = board;
    }

    /**
     * Returns the number of seeds on the specified players side of the board
     * excluding their Mancala
     *
     * Precondition player == 1 || player == 2
     *
     * @param player
     * @return
     */
    public int getSeeds(int player)
    {
        int sum = 0;

        int beg = 0;
        int end = board.length/2 - 1;

        if(player == 2){
            beg = board.length/2;
            end = board.length - 1;
        }
        for (int i = beg; i < end; i++)
            sum += board[i];
        return sum;
    }

    public int[] getBoard()
    {
        return board;
    }

    public int move(int pit)
    {
        int activePlayer = 1;
        int activeMancala = board.length / 2 - 1;
        int enemyMancala = board.length - 1;
        if(pit > board.length/2 )
        {
            activePlayer = 2;
            activeMancala = board.length - 1;
            enemyMancala = board.length / 2 - 1;
        }

        int seeds = board[pit];
        board[pit]  = 0;

        while(seeds > 0)
        {
            pit = (pit + 1) % board.length;
            if(pit != enemyMancala)
            {
                board[pit]++;
                seeds--;
            }
        }

        int oppositePit = board.length - 2 - pit;
        if(board[pit] == 1 && isPlayerPit(activePlayer, pit) && board[oppositePit] > 0)
        {
            board[activeMancala] += board[pit] + board[oppositePit];
            board[pit] = 0;
            board[oppositePit] = 0;
        }

        if(pit == activeMancala)
            return activePlayer;

        return 3 - activePlayer;
    }

    private boolean isPlayerPit(int player, int pit)
    {
        if(player == 1 && pit < board.length / 2 - 1)
            return true;
        else if(player == 2 && pit > board.length / 2 && pit < board.length - 1)
            return true;

        return false;
    }

    public boolean gameOver()
    {
        return getSeeds(1) == 0 || getSeeds(2) == 0;
    }

    public int getWinner()
    {
        if (!gameOver())
            return -1;

        int sum1 = getSeeds(1) + board[board.length / 2 - 1];
        int sum2 = getSeeds(2) + board[board.length - 1];

        if (sum1 > sum2)
            return 1;
        else if (sum1 < sum2)
            return 2;

        return 0;
    }

    public String toString()
    {
        String rtn = "";
        for (int i = board.length - 1; i >= board.length/2  ; i--)
            rtn += board[i];
        rtn += "\n ";
        for (int i = 0; i < board.length/2; i++)
            rtn += board[i];
        return rtn + "\n ";
    }

}
