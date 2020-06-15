package dpp.wordbreaktest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import dpp.wordbreaktest.base.BaseResponseBody;
import dpp.wordbreaktest.service.WordBreakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wordBreak")
public class WordBreakController {
    @Autowired
    private WordBreakService wordBreakService;

    @RequestMapping("/translateStr")
    public BaseResponseBody translateStr(String str, boolean useBothDictionary) {
        try {
            List<String> list = wordBreakService.translateStr(str, useBothDictionary);
            return BaseResponseBody.success(list);
        } catch (JsonProcessingException e) {
            return BaseResponseBody.error("字符串解析失败！",e);
        }
    }
}
