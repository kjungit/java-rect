package algorithm;

import java.util.*;

class LinkData implements Comparable<LinkData>{
    int v;

    public LinkData(int v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "" + v;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LinkData linkData = (LinkData) o;
        return v == linkData.v;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(v);
    }

    @Override
    public int compareTo(LinkData o) {
        return v - o.v;
    }
}

public class LinkTest {
    public static void main(String[] args) {
    List<LinkData> list = new LinkedList<>();
        Random r = new Random();
        for (int i = 1; i <= 40; i++) {
            list.add(new LinkData(i));
        }


        System.out.println(list);
        int index = Collections.binarySearch(list, new LinkData(32));
        System.out.println(list.get(index));
        System.out.println(index);
    }
}
