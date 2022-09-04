//唯一的摩斯密码
//给出一个单词字符串，返回我们可以获得所有词不同单词翻译的数量
//即计算有多少不同的字母

import java.util.TreeSet;
public class Solution804 {
    public int uniqueMorseRepresentations(String[]words){

        String[] codes ={".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

        TreeSet<String> treeSet =new TreeSet<>();
        for(String word:words){
            StringBuilder res= new StringBuilder();
            for(int i=0; i<word.length();i++)
                //charAt() 方法用于返回指定索引处的字符。索引范围为从 0 到 length() - 1
                //获得摩斯密码
              res.append( codes[word.charAt(i) - 'a']);
            treeSet.add(res.toString());
        }
        return treeSet.size();
    }
}
