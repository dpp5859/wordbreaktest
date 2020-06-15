package dpp.wordbreaktest.service.imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import dpp.wordbreaktest.constant.WordBreakConstant;
import dpp.wordbreaktest.model.TreeNode;
import dpp.wordbreaktest.service.WordBreakService;
import dpp.wordbreaktest.utils.TreeUtil;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 需求1:
 * If user provide a customized dictionary of valid English words as additional input, and the
 * program will only find in the user customized dictionary
 * E.g.: the user customized dictionary
 * { i, like, sam, sung, mobile, icecream, man go, mango}
 */
@Service
public class WordBreakServiceImp implements WordBreakService {
    /**
     * 根据字典转换对应字符串
     */
    @Override
    public List<String> translateStr(String inputWords, boolean useBothDictionary) throws JsonProcessingException {
        TreeNode treeNode = new TreeNode();
        Set<String> set = new HashSet<>(Arrays.asList(WordBreakConstant.user_customized_dictionary));
        if (useBothDictionary) {
            Set<String> pub = new HashSet<>(Arrays.asList(WordBreakConstant.default_dictionary));
            set.addAll(pub);
        }
        //遍历保存重复字段 （具体字段，没trim字段）
        Map<String, String> hashMap = new HashMap<>();
        /*set.stream().forEach(f -> {
            String trim = f.trim();
            if (set.contains(trim)) {
                hashMap.put(trim, f);
            }
        });*/
        TreeUtil.buildTree(treeNode, inputWords, set);
        List<StringBuilder> list = new ArrayList<>();
        TreeUtil.buildStr(treeNode, new StringBuilder(), true, " ", list);
        List<String> returnList = new ArrayList<>();
        list.stream().forEach(s -> {
            String string = s.toString();
            hashMap.forEach((k, v) -> {
                if (string.indexOf(k) >= 0) {
                    string.replaceAll(k, v);
                    returnList.add(v);
                }
            });
        });
        list.stream().forEach(k->returnList.add(k.toString()));
        return returnList;

    }
}
