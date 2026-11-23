package connectFour;

enum GameState
{
    IN_PROGRESS,
    WON,
    DRAW
}

public class Game
{
    private final Board board;
    private final Player player1;
    private final Player player2;
    private Player currPlayer;
    private GameState state;
    private Player winner;

    Game(Player player1, Player player2)
    {
        if(player1 == null || player2 == null)
            throw new IllegalArgumentException("Both players are required");
        if(player1.getColor() == null || player2.getColor() == null)
            throw new IllegalArgumentException("Player colors must not be null");
        if(player1.getColor() == player2.getColor())
            throw new IllegalArgumentException("Players must have different colors");

        this.board = new Board();
        this.player1 = player1;
        this.player2 = player2;
        this.currPlayer = player1;
        this.state = GameState.IN_PROGRESS;
        this.winner = null;
    }

    public boolean makeMove(Player player, int colm)
    {
        if(state != GameState.IN_PROGRESS) return false;

        if(player != currPlayer)    return false;

        int row = board.placeDisc(colm, player.getColor());
        if(row == -1)   return false;

        if(board.checkWin(row, colm, player.getColor()))
        {
            state = GameState.WON;
            winner = player;
        }

        else if(board.isFull())
        {
            state = GameState.DRAW;
        }

        else
        {
            currPlayer = (currPlayer == player1)? player2 : player1;
        }

        return true;
    }

    public Player getCurrentPlayer() {
        return currPlayer;
    }

    public GameState getGameState() {
        return state;
    }

    public Player getWinner() {
        return winner;
    }

    public Board getBoard() {
        return board;
    }
}