import java.io.Serializable;
import java.util.*;

class HuffmanNode implements Serializable {
    int data;
    char c;
    HuffmanNode left;
    HuffmanNode right;
}

class MyComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y) {
        return x.data - y.data;
    }
}

public class HuffmanTreeBuilder {
    private HuffmanNode root;
    private Map<Character, String> huffmanCodeMap = new HashMap<>();
    private Map<String, Character> reverseHuffmanCodeMap = new HashMap<>();

    public void buildHuffmanTree(String data) {
        int[] freq = new int[256];
        for (char c : data.toCharArray()) {
            freq[c]++;
        }

        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(new MyComparator());

        for (int i = 0; i < 256; i++) {
            if (freq[i] > 0) {
                HuffmanNode hn = new HuffmanNode();
                hn.c = (char) i;
                hn.data = freq[i];
                hn.left = null;
                hn.right = null;
                pq.add(hn);
            }
        }

        while (pq.size() > 1) {
            HuffmanNode x = pq.poll();
            HuffmanNode y = pq.poll();

            HuffmanNode f = new HuffmanNode();
            f.data = x.data + y.data;
            f.c = '-';
            f.left = x;
            f.right = y;
            root = f;

            pq.add(f);
        }

        generateCodes(root, "");
    }

    private void generateCodes(HuffmanNode root, String s) {
        if (root.left == null && root.right == null) {
            huffmanCodeMap.put(root.c, s);
            reverseHuffmanCodeMap.put(s, root.c);
            return;
        }
        generateCodes(root.left, s + "0");
        generateCodes(root.right, s + "1");
    }

    public void buildHuffmanTreeFromCodeMap(Map<Character, String> codeMap) {
        this.huffmanCodeMap = codeMap;
        this.reverseHuffmanCodeMap = new HashMap<>();
        for (Map.Entry<Character, String> entry : codeMap.entrySet()) {
            reverseHuffmanCodeMap.put(entry.getValue(), entry.getKey());
        }
    }

    public Map<Character, String> getHuffmanCodeMap() {
        return huffmanCodeMap;
    }

    public Map<String, Character> getReverseHuffmanCodeMap() {
        return reverseHuffmanCodeMap;
    }
}
