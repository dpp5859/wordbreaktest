package dpp.wordbreaktest.utils;

import dpp.wordbreaktest.constant.WordBreakConstant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AcAutomaton {
    /**
     * trie树的根节点
     * */
    private static TrieNode rootNode;

    /**
     * 添加词
     * */
    public static void addWord(String word) {

        if (null == rootNode) {
            rootNode = new TrieNode();
        }
        TrieNode currentNode = rootNode;
        for (char c : word.toCharArray()) {
            if (null == currentNode.getSubNodes()) {
                currentNode.setSubNodes(new HashMap<>());
            }
            if (!currentNode.getSubNodes().containsKey(c)) {
                TrieNode node = new TrieNode();
                currentNode.getSubNodes().put(c, node);
            }
            currentNode = currentNode.getSubNodes().get(c);
        }
        currentNode.setWord(word);
    }

    /**
     * 广度优先创建失败指针
     * */
    private static void bfsFailePointProcess(List<TrieNode> nodes) {
        if (nodes.isEmpty()) {
            return;
        }
        List<TrieNode> nextNodes = new ArrayList<>();
        nodes.stream().forEach(node -> {
            if (null != node.getSubNodes()) {
                node.getSubNodes().forEach((k, v) -> {
                    nextNodes.add(v);
                    if (node.getFaileNode() == null) {
                        v.setFaileNode(node);
                    } else {
                        TrieNode currentNode = node;
                        while (currentNode.getFaileNode().getSubNodes() == null || !currentNode.getFaileNode().getSubNodes().containsKey(k)) {
                            currentNode = currentNode.getFaileNode();
                            if (null == currentNode.getFaileNode()) {
                                v.setFaileNode(currentNode);
                                break;
                            }
                        }
                        if (null == v.getFaileNode()) {
                            v.setFaileNode(currentNode.getFaileNode().getSubNodes().get(k));
                        }
                    }
                });
            }
        });
        bfsFailePointProcess(nextNodes);
    }

    /**
     * 节点类
     * */
    static class TrieNode {
        // 子结点
        private HashMap<Character, TrieNode> subNodes;
        // 失败指针
        private TrieNode faileNode;
        // 词条信息，若到此结点不是词则为null
        private String word;

        public HashMap<Character, TrieNode> getSubNodes() {
            return subNodes;
        }
        public void setSubNodes(HashMap<Character, TrieNode> subNodes) {
            this.subNodes = subNodes;
        }

        public TrieNode getFaileNode() {
            return faileNode;
        }

        public void setFaileNode(TrieNode faileNode) {
            this.faileNode = faileNode;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }
    }
    /**
     * 匹配词
     * */
    public List<String> matchWord(String sentence) {
        List<String> words = new ArrayList<>();
        TrieNode currentNode = rootNode;
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            TrieNode nextNode = null;
            while (null == currentNode.getSubNodes() || null == currentNode.getSubNodes().get(c)) {
                if (currentNode.getFaileNode() == null) {
                    break;
                }
                currentNode = currentNode.getFaileNode();
                if(null != currentNode.getWord())
                    words.add(currentNode.getWord());
            }
            if (null != currentNode.getSubNodes()) {
                nextNode = currentNode.getSubNodes().get(c);
            }
            if (nextNode == null) {
                continue;
            }
            if (null != nextNode.getWord()) {
                words.add(nextNode.getWord());
            }
            currentNode = nextNode;
        }
        return words;
    }

    public static void main(String[] args){
        AcAutomaton acAutomaton = new AcAutomaton();
        Arrays.asList(WordBreakConstant.user_customized_dictionary).forEach(word -> acAutomaton.addWord(word));
        bfsFailePointProcess(Arrays.asList(rootNode));
        String sentence = "ilikesamsungmobileicecreammangofafqa";
        acAutomaton.matchWord(sentence).forEach(System.out::println);
    }
}
