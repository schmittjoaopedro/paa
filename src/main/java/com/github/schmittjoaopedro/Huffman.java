package com.github.schmittjoaopedro;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Huffman {

    public static void main(String[] args) throws Exception {
        //String message = "Embora eu acredite que a estrategia seja mais importante do que o ato de comprar acoes, os dois pontos sao essenciais. Por isso criei esse guia de 4 passos praticos para comecar agora mesmo.";
        String message = FileUtils.readFileToString(new File("math/text-to-compress.txt"), "UTF-8");
        System.out.println("Size = " + message.length());
        //System.out.println(message);
        Huffman huffman = new Huffman();

        // Original
        Map<Integer, Data> charData = huffman.getCharFrequencies(message);
        String originalBinary = huffman.getBinaryChain(charData, message, false);
        System.out.println("originalBinary Size = " + originalBinary.length());
        //System.out.println(originalBinary);

        // Compress
        List<Data> data = new ArrayList<Data>();
        data.addAll(charData.values());
        Data decompressor = huffman.huffman(data).get(0);
        String compressedBinary = huffman.getBinaryChain(charData, message, true);
        System.out.println("compressedBinary Size = " + compressedBinary.length());
        //System.out.println(compressedBinary);

        // Decompress
        String originalMessage = huffman.decompress(decompressor, compressedBinary);
        System.out.println("originalMessage Size = " + originalMessage.length());
        //System.out.println(originalMessage);

        System.out.println("Compressed successfully = " + (message.equals(originalMessage)));
    }

    public String decompress(Data root, String messageCompressed) {
        StringBuilder newMessage = new StringBuilder();
        Data currentNode = root;
        for(int i = 0; i < messageCompressed.length(); i++) {
            char cur = messageCompressed.charAt(i);
            if(cur == '0') {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
            if(currentNode.left == null && currentNode.right == null) {
                char c = (char) Integer.parseInt(currentNode.originalCode, 2);
                newMessage.append(c);
                currentNode = root;
            }
        }
        return newMessage.toString();
    }

    public String getBinaryChain(Map<Integer, Data> charData, String message, boolean compress) {
        StringBuilder newMessage = new StringBuilder();
        for(int i = 0; i < message.length(); i++) {
            Integer asciiIndex = (int) message.charAt(i);
            if (compress) {
                newMessage.append(charData.get(asciiIndex).compressedCode);
            } else {
                newMessage.append(charData.get(asciiIndex).originalCode);
            }
        }
        return newMessage.toString();
    }

    public Map<Integer, Data> getCharFrequencies(String message) {
        Map<Integer, Data> frequencies = new HashMap<Integer, Data>();
        for(int i = 0; i < message.length(); i++) {
            Integer asciiIndex = (int) message.charAt(i);
            Data data = frequencies.get(asciiIndex);
            if(data == null) {
                data = new Data();
                data.character = message.charAt(i);
                data.originalCode = Integer.toBinaryString(asciiIndex);
                frequencies.put(asciiIndex, data);
            }
            data.frequency++;
        }
        return frequencies;
    }

    public List<Data> huffman(List<Data> C) {
        int n = C.size();
        buildHeap(C);
        for(int i = 0; i < n-1;i++) {
            Data z = new Data();
            z.left = extractMin(C);
            z.right = extractMin(C);
            z.frequency = z.left.frequency + z.right.frequency;
            insert(C, z);
        }
        dfsSetCodes(C.get(0), "");
        return C;
    }

    public void dfsSetCodes(Data root, String parent) {
        root.compressedCode = parent;
        if(root.left != null) {
            dfsSetCodes(root.left, root.compressedCode + "0");
        }
        if (root.right != null) {
            dfsSetCodes(root.right, root.compressedCode + "1");
        }
    }

    public void buildHeap(List<Data> heap) {
        for(int i = heap.size() / 2; i >= 0; i--) {
            heapify(heap, i);
        }
    }

    public void heapify(List<Data> heap, int i) {
        int left = (2*i) + 1;
        int right = (2*i) + 2;
        int smallest = i;
        if(left < heap.size() && heap.get(left).frequency < heap.get(i).frequency) {
            smallest = left;
        }
        if(right < heap.size() && heap.get(right).frequency < heap.get(smallest).frequency) {
            smallest = right;
        }
        if(smallest != i) {
            Data aux = heap.get(i);
            heap.set(i, heap.get(smallest));
            heap.set(smallest, aux);
            heapify(heap, smallest);
        }
    }

    public Data extractMin(List<Data> heap) {
        Data data = heap.get(0);
        if(heap.size() == 1) {
            heap.clear();
        } else {
            heap.set(0, heap.remove(heap.size() - 1));
        }
        heapify(heap, 0);
        return data;
    }

    public void insert(List<Data> heap, Data item) {
        heap.add(item);
        int i = heap.size() - 1;
        while(i > 0 && heap.get(parent(i)).frequency > item.frequency) {
            heap.set(i, heap.get(parent(i)));
            i = parent(i);
        }
        heap.set(i, item);
    }

    public int parent(int i) {
        return i/2;
    }

    private class Data {

        private Data left;

        private Data right;

        public char character;

        public int frequency;

        public String originalCode;

        public String compressedCode;

    }
}
