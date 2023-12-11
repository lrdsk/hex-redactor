package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CustomFileReader {
    private final Path path;
    private static final String HEXES = "0123456789ABCDEF";
    private int rowCount;
    private int maxColumnCount;

    public CustomFileReader(Path path) {
        this.path = path;
    }

    public List<byte[]> readBytesFromFile(){
        if(Files.exists(path) && Files.isRegularFile(path)){
            try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toFile()))) {
                String line;
                List<byte[]> listBytes = new ArrayList<>();
                while ((line = bufferedReader.readLine()) != null){
                    rowCount++;
                    if(maxColumnCount < line.length()){
                        maxColumnCount = line.length();
                    }
                    byte[] bytes = line.getBytes(StandardCharsets.UTF_8);
                    listBytes.add(bytes);
                }
                return listBytes;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return new ArrayList<>();
    }

    public List<String[]> getBytesHexFormat(List<byte[]> raws){
        final StringBuilder hex = new StringBuilder(2 * raws.size());
        final List<String[]> hexRaws = new ArrayList<>();
        for (byte[] lineBytes : raws) {
            for(byte b : lineBytes){
                hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F))).append("\t");
            }
            while(hex.toString().split("\t").length < maxColumnCount){
                hex.append(0).append("\t");
            }

            hexRaws.add(hex.toString().split("\t"));
            hex.delete(0,hex.length());
        }
        return hexRaws;
    }

    public int getMaxColumnCount() {
        return maxColumnCount;
    }
}
