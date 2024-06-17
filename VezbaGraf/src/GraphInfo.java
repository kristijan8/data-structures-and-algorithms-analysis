public class GraphInfo <E extends Comparable<E>>{
    public E info;
    GraphInfo(E info)
    {
        this.info=info;
    }


    @Override
    public String toString() {
        return "Key value pair: "+info.toString();
    }
}
