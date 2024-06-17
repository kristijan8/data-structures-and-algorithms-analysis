import java.util.Random;

public class Deck {
    public static final int SIZE=52;
    private PlayingCard[] cards;
    private boolean [] isUsed;
    private int totalUsed;
    public Deck()
    {
        cards=new PlayingCard[52];
        int n=0;
        for(PlayingCard.SUIT suits : PlayingCard.SUIT.values())
        {
            for(int rank =PlayingCard.MIN_RANK;rank<=PlayingCard.MAX_RANK;rank++)
            {
                cards[n++]=new PlayingCard(suits,rank);
            }
        }
        isUsed=new boolean[SIZE];
        for(int i=0;i<SIZE;i++)
        {
            isUsed[i]=false;
        }
        totalUsed=0;
    }
    public PlayingCard deal()
    {
        if(totalUsed<SIZE) {
            Random random = new Random();
            int cardIndex=random.nextInt(SIZE);
            if(!isUsed[cardIndex])
            {
                isUsed[cardIndex]=true;
                totalUsed++;
                return cards[cardIndex];
            }
            else {
                return deal();
            }
        }
        return null;
    }
    /*public int checkUsed()
    {

    }*/
    public void shuffle()
    {
        Random random=new Random();
        int shuffle_count=random.nextInt(50);
        int card1,card2;
        PlayingCard cardHealp;
        boolean cardHelpUsed;
        for(int i=0;i<shuffle_count;i++)
        {
            card1=random.nextInt(SIZE);
            card2=random.nextInt(SIZE);
            if(card1!=card2)
            {
                cardHealp=cards[card1];
                cards[card1]=cards[card2];
                cards[card2]=cardHealp;
                cardHelpUsed=isUsed[card1];
                isUsed[card1]=isUsed[card2];
                isUsed[card2]=cardHelpUsed;
            }
        }
    }


}
