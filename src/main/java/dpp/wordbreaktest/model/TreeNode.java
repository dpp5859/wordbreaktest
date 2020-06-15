package dpp.wordbreaktest.model;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private String currentStr = "root";
    private List<TreeNode> nextStrList = new ArrayList<>();
    private boolean isExist = true;

    public boolean getIsExist() {
        return isExist;
    }

    public TreeNode setExist(boolean exist) {
        isExist = exist;
        return this;
    }

    public String getCurrentStr() {
        return currentStr;
    }

    public TreeNode setCurrentStr(String currentStr) {
        this.currentStr = currentStr;
        return this;
    }

    public List<TreeNode> getNextStrList() {
        return nextStrList;
    }

    public void setNextStrList(List<TreeNode> nextStrList) {
        this.nextStrList = nextStrList;
    }

    public TreeNode add(TreeNode node) {
        nextStrList.add(node);
        return this;
    }
}
