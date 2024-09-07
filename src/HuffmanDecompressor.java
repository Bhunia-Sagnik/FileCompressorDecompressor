import java.io.*;
import java.util.Map;

public class HuffmanDecompressor {

    private HuffmanTreeBuilder treeBuilder = new HuffmanTreeBuilder();

    public String decode(String encodedData, Map<String, Character> reverseHuffmanCodeMap) {
        StringBuilder decodedData = new StringBuilder();
        String temp = "";
        for (char c : encodedData.toCharArray()) {
            temp += c;
            if (reverseHuffmanCodeMap.containsKey(temp)) {
                decodedData.append(reverseHuffmanCodeMap.get(temp));
                temp = "";
            }
        }
        return decodedData.toString();
    }

    public void decompressFile(String inputFilePath, String outputFilePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(inputFilePath))) {
            Map<Character, String> huffmanCodeMap = (Map<Character, String>) ois.readObject();
            String encodedData = (String) ois.readObject();
            System.out.println("Encoded data: " + encodedData);

            treeBuilder.buildHuffmanTreeFromCodeMap(huffmanCodeMap); // Assuming you have a method to build tree from code map
            Map<String, Character> reverseHuffmanCodeMap = treeBuilder.getReverseHuffmanCodeMap();
            String decodedData = decode(encodedData, reverseHuffmanCodeMap);
            System.out.println("Decoded data: " + decodedData);

            writeFile(outputFilePath, decodedData);
        }
    }

    private void writeFile(String filePath, String data) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(data);
        }
    }
}
