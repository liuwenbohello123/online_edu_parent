import org.junit.Test;

import java.util.ArrayList;

public class myTest {
    /**
     * 测试字符串去重
     */
    @Test
    public void testString(){

        /*String str = "sdfsdohsoghgo";
        String[] split = str.split("");
        System.out.println("split = " + split);
        TreeSet<String> strings = new TreeSet<>();

        for (String s : split) {
           // System.out.println("s = " + s);
            strings.add(s);
        }
        System.out.println(strings);
        String string = strings.toString();
        System.out.println("string = " + string);*/

        String string = "sdgasiudhipasdha";
        char[] chars = string.toCharArray();
        ArrayList<Character> list = new ArrayList<>();
        for (char aChar : chars) {
            //System.out.println("aChar = " + aChar);
            if(!list.contains(aChar)){
                list.add(aChar);
            }
        }
        System.out.println(list);

    }

}
