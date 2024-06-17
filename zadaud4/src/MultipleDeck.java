public class MultipleDeck {
    private Deck [] decks;
    private int size;
    public MultipleDeck(int size)
    {
        decks=new Deck[size];
        this.size=size;
        for(int i=0;i<size;i++)
        {
            decks[i]=new Deck();
        }
    }
    public Deck getDeck(int i)
    {
        return decks[i];
    }
}
