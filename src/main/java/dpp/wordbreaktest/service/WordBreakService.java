package dpp.wordbreaktest.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface WordBreakService {
    /**
     *  根据字典转换对应字符串
     */
    List<String> translateStr(String inputWords,boolean useBothDictionary) throws JsonProcessingException;

}
