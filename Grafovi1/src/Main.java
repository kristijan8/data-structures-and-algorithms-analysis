import java.security.Key;

class Map<K extends Comparable<K>,E>
{
    public K key;
    public E value;

    public Map(K key, E value) {
        this.key = key;
        this.value = value;
    }
}
class SLLNode<E>
{
    public E value;
    public SLLNode<E> next;
    public SLLNode(E value, SLLNode<E> next) {
        this.value = value;
        this.next = next;
    }
}
class SLLHT<K extends Comparable<K>,E>
{
    public SLLNode<Map<K,E>> [] htable;
    SLLHT(int m)
    {
        htable=new SLLNode[m];
        for (int i=0;i<m;i++)
            htable[i]=null;
    }
    private int hash(K key)
    {
        return (Integer)key % htable.length;
    }
    public SLLNode<Map<K,E>> find(K look)
    {
        int h=hash(look);
        for (SLLNode<Map<K,E>> node=htable[h];node!=null;node=node.next)
        {
            if(look.equals(node.value.key))
                return node;
        }
        return null;
    }
    public void insert(K key, E val)
    {
        Map<K,E> entry=new Map<>(key,val);
        int h=hash(key);
        for (SLLNode<Map<K,E>> node=htable[h];node!=null;node=node.next)
        {
            if(key.equals(node.value.key))
            {
                node.value=entry;
                return;
            }
        }
        htable[h]=new SLLNode<Map<K,E>>(entry,htable[h]);
    }
    public void delete(K key) {
        int h = hash(key);
        for (SLLNode<Map<K, E>> pred = null, node = htable[h]; node != null; pred = node, node = node.next) {
            if (key.equals(node.value.key)) {
                if (pred==null)
                    htable[h]=node.next;
                else
                    pred.next = node.next;
            }
        }
    }
}
class ChessPlayer {
    public String name, surname;
    public String country;
    public Integer rank, points;

    ChessPlayer(String name, String surname, String country, Integer rank, Integer points) {
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.rank = rank;
        this.points = points;
    }

    @Override
    public String toString() {
        return "Player " + this.name + " " + this.surname + " is from " +
                this.country + " and is ranked as No. " + this.rank + " with "
                + this.points + " points.";
    }
}
class ChessGame {
    public ChessPlayer player_1, player_2;
    public String datetime;
    public Integer gameId;

    ChessGame(ChessPlayer player_1, ChessPlayer player_2, String datetime, Integer gameId) {
        this.player_1 = player_1;
        this.player_2 = player_2;
        this.datetime = datetime;
        this.gameId = gameId;
    }

    @Override
    public String toString() {
        return "Game is played by " + player_1.toString() + " and " +
                player_2.toString();
    }

}




public class Main {
    public static void main(String[] args)
    {

    }
}