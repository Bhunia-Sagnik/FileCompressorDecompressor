import java.io.*;
import java.util.Map;

public class HuffmanCompressor {

    private HuffmanTreeBuilder treeBuilder = new HuffmanTreeBuilder();

    public String encode(String data) {
        treeBuilder.buildHuffmanTree(data);
        Map<Character, String> huffmanCodeMap = treeBuilder.getHuffmanCodeMap();
        StringBuilder encodedData = new StringBuilder();
        for (char c : data.toCharArray()) {
            encodedData.append(huffmanCodeMap.get(c));
        }
        return encodedData.toString();
    }

    public void compressFile(String inputFilePath, String outputFilePath) throws IOException {
        String data = readFile(inputFilePath);
        System.out.println("Original data: " + data);
        String encodedData = encode(data);
        System.out.println("Encoded data: " + encodedData);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outputFilePath))) {
            oos.writeObject(treeBuilder.getHuffmanCodeMap());
            oos.writeObject(encodedData);
        }
    }

    private String readFile(String filePath) throws IOException {
        StringBuilder data = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.append(line).append("\n");
            }
        }
        return data.toString();
    }
}
