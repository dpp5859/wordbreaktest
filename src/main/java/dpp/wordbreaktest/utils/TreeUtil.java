package dpp.wordbreaktest.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dpp.wordbreaktest.model.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TreeUtil {
    private static Set<String> dictionary;

    public static void buildTree(TreeNode treeNode, String sourceStr,Set<String> stringSet) {
        dictionary = stringSet;
        buildTree(treeNode,sourceStr,0);
    }

    /**
     *   根据字符串构建树结构
     * @param treeNode
     * @param sourceStr
     * @param index
     */
    private static void buildTree(TreeNode treeNode, String sourceStr, int index) {
        if (index > sourceStr.length() - 1) {
            return;
        }
        //获取指定索引处的数据
        Character charAt = sourceStr.charAt(index);
        List<String> stringList = getData(sourceStr, charAt.toString(), index);
        if (null == stringList || stringList.isEmpty()) {
            TreeNode treeNode1 = new TreeNode();
            //找不到匹配对象
            treeNode1.setCurrentStr(charAt.toString())
                    .setExist(false);
            treeNode.add(treeNode1);
            index++;
            buildTree(treeNode1, sourceStr, index);
        } else {
            //找到匹配对象
            for (String x : stringList) {
                TreeNode treeNode1 = new TreeNode();
                treeNode1.setCurrentStr(x);
                treeNode.add(treeNode1);
                buildTree(treeNode1, sourceStr, index + x.length());
            }
        }
    }


    private static List<String> getData(String sourceStr, String chartChar, int index) {

        return dictionary.parallelStream()
                .filter(s -> {
                    if (s.startsWith(chartChar)) {
                        if (sourceStr.length() < s.length() + index) {
                            //7 3 3(4)
                            //判断是否越界
                            return false;
                        } else {
                            return sourceStr.substring(index, s.length() + index).equals(s);
                        }
                    }
                    return false;
                }).collect(Collectors.toList());
    }


    /**
     * 根据树结构构建字符串
     * @param treeNode      当前节点
     * @param stringBuilder 缓存字符串
     * @param useExist      当多个字符不存在是否拼接在一起
     * @param split         分割符
     */
    public static void buildStr(TreeNode treeNode, StringBuilder stringBuilder, boolean useExist, String split, List<StringBuilder> list) throws JsonProcessingException {
        List<TreeNode> nextStrList = treeNode.getNextStrList();
        if (nextStrList.isEmpty()) {
            //buildData(treeNode, stringBuilder, useExist, split, treeNode);
            list.add(stringBuilder);
            return;
        }
        String s = stringBuilder.toString();
        for (int i = 0; i < nextStrList.size(); i++) {
            TreeNode treeNode1 = nextStrList.get(i);
            if (i == 0) {
                buildData(treeNode, stringBuilder, useExist, split, treeNode1);
                buildStr(treeNode1, stringBuilder, useExist, split, list);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(s);
                buildData(treeNode, sb, useExist, split, treeNode1);
                buildStr(treeNode1, sb, useExist, split, list);
            }
        }

    }

    private static void buildData(TreeNode treeNode, StringBuilder stringBuilder, boolean useExist, String split, TreeNode treeNode1) {
        if (useExist) {
            //使用当前字符串是否在字典存在判断\
            //当前节点存在 并且下个节点也存在--> 2个节点间添加分割符 否则不添加
            if (!treeNode1.getIsExist() && !treeNode.getIsExist()) {
                //2个节点都不存在 不添加分割符
            } else {
                stringBuilder.append(split);
            }
        } else {
            stringBuilder.append(split);
        }
        if(stringBuilder.toString().equals(split)){
            stringBuilder.setLength(0);
        }
        stringBuilder.append(treeNode1.getCurrentStr());
    }

    public static void main(String[] args) throws JsonProcessingException {
        //String text = "iicecreamillllllllllllllllllllllikesamsungmooooobilemangomangomango";
        String text = "ilikeicecreamsamsungmobile";
        TreeNode treeNode = new TreeNode();
        buildTree(treeNode, text, 0);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(treeNode);
        System.out.println(s);
        List<StringBuilder> list = new ArrayList<>();
        buildStr(treeNode, new StringBuilder(), false, " ", list);
        String s1 = objectMapper.writeValueAsString(list);
        System.out.println(s1);


       /* StringBuilder sb = new StringBuilder("sb");
        StringBuilder sb1 = new StringBuilder("sb1");
        sb.append(sb1);
        System.out.println(sb);
        sb1.append("-----");
        System.out.println(sb1);
        sb.append("aaaa");
        System.out.println(sb);*/
    }
}
