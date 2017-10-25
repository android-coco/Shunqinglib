package yh.org.shunqinglib;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest
{
    @Test
    public void addition_isCorrect() throws Exception
    {
        assertEquals(4, 2 + 2);
//        byte[] weeks = "1234567".getBytes();
//        System.out.print(Arrays.toString(weeks));

        String[] strs = {"1,2,5,", ",,,1,2,5,", ",,,,,,," };
        String regex = "^,*|,*$";

        // 需要处理多个字符串
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("");
        for(int i = 0; i < strs.length; i++) {
            System.out.println(matcher.reset(strs[i]).replaceAll(""));
        }

        // 只处理一个字符串
        String str = ",,,,1,2,3,,,,,";
        System.out.println(str.replaceAll(regex, ""));

        System.out.print("18".substring(1));
    }
}