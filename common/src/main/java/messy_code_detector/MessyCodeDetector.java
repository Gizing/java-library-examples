package messy_code_detector;

public class MessyCodeDetector
{
    /**
     * 判断字符是否是中文
     *
     * @param c 字符
     * @return true 表示是中文
     */
    public static boolean isChinese(char c)
    {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS)
        {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串里是否含有乱码
     *
     * @param str 需要判断的字符串
     * @return true 包含乱码
     */
    public static boolean containMessyCode(String str)
    {
        // 去除字符串里所有的操作符,空格、换行、TAB
        char[] array = str.replaceAll("\\p{Punct}|\\s*|\\t*|\\r*|\\n*", "").toCharArray();
        
        for (int i = 0; i < array.length; ++i)
        {
            char c = array[i];
            if (!Character.isLetterOrDigit(c))
            {
                if (!isChinese(c) && !(c >= 0 && c < 128)) // 字符不是中文也不是ASCII 0-127的字符，则认为是乱码
                {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args)
    {
        System.out.println(containMessyCode("你好Ã©Å¸Â©Ã©Â¡ÂºÃ¥Â¹Â³"));
        System.out.println(containMessyCode("int a=2 ; // 你好"));
        System.out.println(containMessyCode("int a=3"));
    }

}
