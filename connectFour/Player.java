package connectFour;

public class Player {
    private final String name;
    private final DiscColor color;

    public Player(String name, DiscColor color)
    {
        if(color == null) throw new IllegalArgumentException("Player color must not be null");
        this.name = name;
        this.color = color;
    }

    public String getName()
    {
        return name;
    }

    public DiscColor getColor()
    {
        return color;
    }
}
