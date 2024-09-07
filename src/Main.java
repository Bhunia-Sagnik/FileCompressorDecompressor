import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the operation (compress/decompress): ");
        String command = scanner.nextLine();

        System.out.println("Enter the input file path: ");
        String inputFilePath = scanner.nextLine();

        System.out.println("Enter the output file path: ");
        String outputFilePath = scanner.nextLine();

        try {
            if (command.equalsIgnoreCase("compress")) {
                HuffmanCompressor compressor = new HuffmanCompressor();
                compressor.compressFile(inputFilePath, outputFilePath);
                System.out.println("File compressed successfully.");
            } else if (command.equalsIgnoreCase("decompress")) {
                HuffmanDecompressor decompressor = new HuffmanDecompressor();
                decompressor.decompressFile(inputFilePath, outputFilePath);
                System.out.println("File decompressed successfully.");
            } else {
                System.out.println("Invalid command. Use 'compress' or 'decompress'.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
