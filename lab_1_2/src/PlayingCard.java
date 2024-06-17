public class PlayingCard {
    public static final int MIN_RANK=1;
    public static final int MAX_RANK=13;
    public enum SUIT{Hearts,Diamonts,Clubs,Spades};
    private SUIT suit;
    private int rank;
    public PlayingCard(SUIT suit,int rank)
    {
        this.suit=suit;
        this.rank=rank;
    }
    public String toString()
    {
        StringBuilder sb=new StringBuilder();
        sb.append(this.rank);
        if(this.suit==SUIT.Hearts)
        {
            sb.append("H");
        }
        if(this.suit==SUIT.Diamonts)
        {
            sb.append("D");
        }
        if(this.suit==SUIT.Clubs)
        {
            sb.append("C");
        }
        if(this.suit==SUIT.Spades)
        {
            sb.append("S");
        }
        return sb.toString();
    }
}


