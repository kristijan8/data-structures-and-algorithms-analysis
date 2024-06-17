public class Main {
    public static void main(String[] args) {
        MultipleDeck md=new MultipleDeck(5);
        for(int i=0;i<5;i++)
        {
            md.getDeck(i).shuffle();
        }
        for(int i=0;i<5;i++)
        {
            md.getDeck(i).deal();
        }



    }


}