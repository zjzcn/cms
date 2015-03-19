package com.zjzcn.util;

import java.util.Iterator;
import java.util.Random;

/**
 * 
 * Class Name: StringUtil<br>
 * Description: 类功能说明<br>
 * Sample: 该类的典型使用方法和用例<br>
 * Author: zengtao<br>
 * Date: 2011-9-30<br>
 * --------------------------------------------------<br>
 * 修改人　　　　修改日期　　　　　修改描述<br>
 * zengtao　　 2011-9-30　　　　　　创建<br>
 * --------------------------------------------------<br>
 */
public class StringUtils
{
    /* ============================================================================ */
    /*  常量和singleton。                                                           */
    /* ============================================================================ */
    
    /** 空字符串。 */
    public static final String EMPTY_STRING = "";
    
    private static final int NUM_ONE_SIX = 16;
    
    private static final int NUMBER_TWO_FIVE_SIX = 256;
    
    private static final int NUM_TWO = 2;
    
    private static final int NUM_THREE = 3;
    
    private static final int NUM_FOUR = 4;
    
    private static final int NUM_SEVEN = 7;
    
    /* ============================================================================ */
    /*  判空函数。                                                                  */
    /*                                                                              */
    /*  以下方法用来判定一个字符串是否为：                                          */
    /*  1. null                                                                     */
    /*  2. empty - ""                                                               */
    /*  3. blank - "全部是空白" - 空白由Character.isWhitespace所定义。              */
    /* ============================================================================ */
    
    /**
     * 检查字符串是否为<code>null</code>或空字符串<code>""</code>。
     * <pre>
     * StringUtil.isEmpty(null)      = true
     * StringUtil.isEmpty("")        = true
     * StringUtil.isEmpty(" ")       = false
     * StringUtil.isEmpty("bob")     = false
     * StringUtil.isEmpty("  bob  ") = false
     * </pre>
     *
     * @param str 要检查的字符串
     *
     * @return 如果为空, 则返回<code>true</code>
     */
    public static boolean isEmpty(String str)
    {
        return (str == null) || (str.length() == 0);
    }
    
    /**
     * 检查字符串是否不是<code>null</code>和空字符串<code>""</code>。
     * <pre>
     * StringUtil.isEmpty(null)      = false
     * StringUtil.isEmpty("")        = false
     * StringUtil.isEmpty(" ")       = true
     * StringUtil.isEmpty("bob")     = true
     * StringUtil.isEmpty("  bob  ") = true
     * </pre>
     *
     * @param str 要检查的字符串
     *
     * @return 如果不为空, 则返回<code>true</code>
     */
    public static boolean isNotEmpty(String str)
    {
        return (str != null) && (str.length() > 0);
    }
    
    /**
     * 检查字符串是否是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
     * <pre>
     * StringUtil.isBlank(null)      = true
     * StringUtil.isBlank("")        = true
     * StringUtil.isBlank(" ")       = true
     * StringUtil.isBlank("bob")     = false
     * StringUtil.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str 要检查的字符串
     *
     * @return 如果为空白, 则返回<code>true</code>
     */
    public static boolean isBlank(String str)
    {
        int length;
        
        if ((str == null) || ((length = str.length()) == 0))
        {
            return true;
        }
        
        for (int i = 0; i < length; i++)
        {
            if (!Character.isWhitespace(str.charAt(i)))
            {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * 检查字符串是否不是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
     * <pre>
     * StringUtil.isBlank(null)      = false
     * StringUtil.isBlank("")        = false
     * StringUtil.isBlank(" ")       = false
     * StringUtil.isBlank("bob")     = true
     * StringUtil.isBlank("  bob  ") = true
     * </pre>
     *
     * @param str 要检查的字符串
     *
     * @return 如果为空白, 则返回<code>true</code>
     */
    public static boolean isNotBlank(String str)
    {
        int length;
        
        if ((str == null) || ((length = str.length()) == 0))
        {
            return false;
        }
        
        for (int i = 0; i < length; i++)
        {
            if (!Character.isWhitespace(str.charAt(i)))
            {
                return true;
            }
        }
        
        return false;
    }
    
    /* ============================================================================ */
    /*  默认值函数。                                                                */
    /*                                                                              */
    /*  当字符串为null、empty或blank时，将字符串转换成指定的默认字符串。            */
    /* ============================================================================ */
    
    /**
     * 如果字符串是<code>null</code>，则返回空字符串<code>""</code>，否则返回字符串本身。
     * <pre>
     * StringUtil.defaultIfNull(null)  = ""
     * StringUtil.defaultIfNull("")    = ""
     * StringUtil.defaultIfNull("  ")  = "  "
     * StringUtil.defaultIfNull("bat") = "bat"
     * </pre>
     *
     * @param str 要转换的字符串
     *
     * @return 字符串本身或空字符串<code>""</code>
     */
    public static String defaultIfNull(String str)
    {
        return (str == null) ? EMPTY_STRING : str;
    }
    
    /**
     * 如果字符串是<code>null</code>，则返回指定默认字符串，否则返回字符串本身。
     * <pre>
     * StringUtil.defaultIfNull(null, "default")  = "default"
     * StringUtil.defaultIfNull("", "default")    = ""
     * StringUtil.defaultIfNull("  ", "default")  = "  "
     * StringUtil.defaultIfNull("bat", "default") = "bat"
     * </pre>
     *
     * @param str 要转换的字符串
     * @param defaultStr 默认字符串
     *
     * @return 字符串本身或指定的默认字符串
     */
    public static String defaultIfNull(String str, String defaultStr)
    {
        return (str == null) ? defaultStr : str;
    }
    
    /**
     * 如果字符串是<code>null</code>或空字符串<code>""</code>，则返回空字符串<code>""</code>，否则返回字符串本身。
     * 
     * <p>
     * 此方法实际上和<code>defaultIfNull(String)</code>等效。
     * <pre>
     * StringUtil.defaultIfEmpty(null)  = ""
     * StringUtil.defaultIfEmpty("")    = ""
     * StringUtil.defaultIfEmpty("  ")  = "  "
     * StringUtil.defaultIfEmpty("bat") = "bat"
     * </pre>
     * </p>
     *
     * @param str 要转换的字符串
     *
     * @return 字符串本身或空字符串<code>""</code>
     */
    public static String defaultIfEmpty(String str)
    {
        return (str == null) ? EMPTY_STRING : str;
    }
    
    /**
     * 如果字符串是<code>null</code>或空字符串<code>""</code>，则返回指定默认字符串，否则返回字符串本身。
     * <pre>
     * StringUtil.defaultIfEmpty(null, "default")  = "default"
     * StringUtil.defaultIfEmpty("", "default")    = "default"
     * StringUtil.defaultIfEmpty("  ", "default")  = "  "
     * StringUtil.defaultIfEmpty("bat", "default") = "bat"
     * </pre>
     *
     * @param str 要转换的字符串
     * @param defaultStr 默认字符串
     *
     * @return 字符串本身或指定的默认字符串
     */
    public static String defaultIfEmpty(String str, String defaultStr)
    {
        return ((str == null) || (str.length() == 0)) ? defaultStr : str;
    }
    
    /**
     * 如果字符串是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符，则返回空字符串<code>""</code>，否则返回字符串本身。
     * <pre>
     * StringUtil.defaultIfBlank(null)  = ""
     * StringUtil.defaultIfBlank("")    = ""
     * StringUtil.defaultIfBlank("  ")  = ""
     * StringUtil.defaultIfBlank("bat") = "bat"
     * </pre>
     *
     * @param str 要转换的字符串
     *
     * @return 字符串本身或空字符串<code>""</code>
     */
    public static String defaultIfBlank(String str)
    {
        return isBlank(str) ? EMPTY_STRING : str;
    }
    
    /**
     * 如果字符串是<code>null</code>或空字符串<code>""</code>，则返回指定默认字符串，否则返回字符串本身。
     * <pre>
     * StringUtil.defaultIfBlank(null, "default")  = "default"
     * StringUtil.defaultIfBlank("", "default")    = "default"
     * StringUtil.defaultIfBlank("  ", "default")  = "default"
     * StringUtil.defaultIfBlank("bat", "default") = "bat"
     * </pre>
     *
     * @param str 要转换的字符串
     * @param defaultStr 默认字符串
     *
     * @return 字符串本身或指定的默认字符串
     */
    public static String defaultIfBlank(String str, String defaultStr)
    {
        return isBlank(str) ? defaultStr : str;
    }
    
    /* ============================================================================ */
    /*  去空白（或指定字符）的函数。                                                */
    /*                                                                              */
    /*  以下方法用来除去一个字串中的空白或指定字符。                                */
    /* ============================================================================ */
    
    /**
     * 除去字符串头尾部的空白，如果字符串是<code>null</code>，依然返回<code>null</code>。
     * 
     * <p>
     * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>来判定空白，
     * 因而可以除去英文字符集之外的其它空白，如中文空格。
     * <pre>
     * StringUtil.trim(null)          = null
     * StringUtil.trim("")            = ""
     * StringUtil.trim("     ")       = ""
     * StringUtil.trim("abc")         = "abc"
     * StringUtil.trim("    abc    ") = "abc"
     * </pre>
     * </p>
     *
     * @param str 要处理的字符串
     *
     * @return 除去空白的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
     */
    public static String trim(String str)
    {
        return trim(str, null, 0);
    }
    
    /**
     * 除去字符串头尾部的指定字符，如果字符串是<code>null</code>，依然返回<code>null</code>。
     * <pre>
     * StringUtil.trim(null, *)          = null
     * StringUtil.trim("", *)            = ""
     * StringUtil.trim("abc", null)      = "abc"
     * StringUtil.trim("  abc", null)    = "abc"
     * StringUtil.trim("abc  ", null)    = "abc"
     * StringUtil.trim(" abc ", null)    = "abc"
     * StringUtil.trim("  abcyx", "xyz") = "  abc"
     * </pre>
     *
     * @param str 要处理的字符串
     * @param stripChars 要除去的字符，如果为<code>null</code>表示除去空白字符
     *
     * @return 除去指定字符后的的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
     */
    public static String trim(String str, String stripChars)
    {
        return trim(str, stripChars, 0);
    }
    
    /**
     * 除去字符串头部的空白，如果字符串是<code>null</code>，则返回<code>null</code>。
     * 
     * <p>
     * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>来判定空白，
     * 因而可以除去英文字符集之外的其它空白，如中文空格。
     * <pre>
     * StringUtil.trimStart(null)         = null
     * StringUtil.trimStart("")           = ""
     * StringUtil.trimStart("abc")        = "abc"
     * StringUtil.trimStart("  abc")      = "abc"
     * StringUtil.trimStart("abc  ")      = "abc  "
     * StringUtil.trimStart(" abc ")      = "abc "
     * </pre>
     * </p>
     *
     * @param str 要处理的字符串
     *
     * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回<code>null</code>
     */
    public static String trimStart(String str)
    {
        return trim(str, null, -1);
    }
    
    /**
     * 除去字符串头部的指定字符，如果字符串是<code>null</code>，依然返回<code>null</code>。
     * <pre>
     * StringUtil.trimStart(null, *)          = null
     * StringUtil.trimStart("", *)            = ""
     * StringUtil.trimStart("abc", "")        = "abc"
     * StringUtil.trimStart("abc", null)      = "abc"
     * StringUtil.trimStart("  abc", null)    = "abc"
     * StringUtil.trimStart("abc  ", null)    = "abc  "
     * StringUtil.trimStart(" abc ", null)    = "abc "
     * StringUtil.trimStart("yxabc  ", "xyz") = "abc  "
     * </pre>
     *
     * @param str 要处理的字符串
     * @param stripChars 要除去的字符，如果为<code>null</code>表示除去空白字符
     *
     * @return 除去指定字符后的的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
     */
    public static String trimStart(String str, String stripChars)
    {
        return trim(str, stripChars, -1);
    }
    
    /**
     * 除去字符串尾部的空白，如果字符串是<code>null</code>，则返回<code>null</code>。
     * 
     * <p>
     * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>来判定空白，
     * 因而可以除去英文字符集之外的其它空白，如中文空格。
     * <pre>
     * StringUtil.trimEnd(null)       = null
     * StringUtil.trimEnd("")         = ""
     * StringUtil.trimEnd("abc")      = "abc"
     * StringUtil.trimEnd("  abc")    = "  abc"
     * StringUtil.trimEnd("abc  ")    = "abc"
     * StringUtil.trimEnd(" abc ")    = " abc"
     * </pre>
     * </p>
     *
     * @param str 要处理的字符串
     *
     * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回<code>null</code>
     */
    public static String trimEnd(String str)
    {
        return trim(str, null, 1);
    }
    
    /**
     * 除去字符串尾部的指定字符，如果字符串是<code>null</code>，依然返回<code>null</code>。
     * <pre>
     * StringUtil.trimEnd(null, *)          = null
     * StringUtil.trimEnd("", *)            = ""
     * StringUtil.trimEnd("abc", "")        = "abc"
     * StringUtil.trimEnd("abc", null)      = "abc"
     * StringUtil.trimEnd("  abc", null)    = "  abc"
     * StringUtil.trimEnd("abc  ", null)    = "abc"
     * StringUtil.trimEnd(" abc ", null)    = " abc"
     * StringUtil.trimEnd("  abcyx", "xyz") = "  abc"
     * </pre>
     *
     * @param str 要处理的字符串
     * @param stripChars 要除去的字符，如果为<code>null</code>表示除去空白字符
     *
     * @return 除去指定字符后的的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
     */
    public static String trimEnd(String str, String stripChars)
    {
        return trim(str, stripChars, 1);
    }
    
    /**
     * 除去字符串头尾部的空白，如果结果字符串是空字符串<code>""</code>，则返回<code>null</code>。
     * 
     * <p>
     * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>来判定空白，
     * 因而可以除去英文字符集之外的其它空白，如中文空格。
     * <pre>
     * StringUtil.trimToNull(null)          = null
     * StringUtil.trimToNull("")            = null
     * StringUtil.trimToNull("     ")       = null
     * StringUtil.trimToNull("abc")         = "abc"
     * StringUtil.trimToNull("    abc    ") = "abc"
     * </pre>
     * </p>
     *
     * @param str 要处理的字符串
     *
     * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回<code>null</code>
     */
    public static String trimToNull(String str)
    {
        return trimToNull(str, null);
    }
    
    /**
     * 除去字符串头尾部的空白，如果结果字符串是空字符串<code>""</code>，则返回<code>null</code>。
     * 
     * <p>
     * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>来判定空白，
     * 因而可以除去英文字符集之外的其它空白，如中文空格。
     * <pre>
     * StringUtil.trim(null, *)          = null
     * StringUtil.trim("", *)            = null
     * StringUtil.trim("abc", null)      = "abc"
     * StringUtil.trim("  abc", null)    = "abc"
     * StringUtil.trim("abc  ", null)    = "abc"
     * StringUtil.trim(" abc ", null)    = "abc"
     * StringUtil.trim("  abcyx", "xyz") = "  abc"
     * </pre>
     * </p>
     *
     * @param str 要处理的字符串
     * @param stripChars 要除去的字符，如果为<code>null</code>表示除去空白字符
     *
     * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回<code>null</code>
     */
    public static String trimToNull(String str, String stripChars)
    {
        String result = trim(str, stripChars);
        
        if ((result == null) || (result.length() == 0))
        {
            return null;
        }
        
        return result;
    }
    
    /**
     * 除去字符串头尾部的空白，如果字符串是<code>null</code>，则返回空字符串<code>""</code>。
     * 
     * <p>
     * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>来判定空白，
     * 因而可以除去英文字符集之外的其它空白，如中文空格。
     * <pre>
     * StringUtil.trimToEmpty(null)          = ""
     * StringUtil.trimToEmpty("")            = ""
     * StringUtil.trimToEmpty("     ")       = ""
     * StringUtil.trimToEmpty("abc")         = "abc"
     * StringUtil.trimToEmpty("    abc    ") = "abc"
     * </pre>
     * </p>
     *
     * @param str 要处理的字符串
     *
     * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回<code>null</code>
     */
    public static String trimToEmpty(String str)
    {
        return trimToEmpty(str, null);
    }
    
    /**
     * 除去字符串头尾部的空白，如果字符串是<code>null</code>，则返回空字符串<code>""</code>。
     * 
     * <p>
     * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>来判定空白，
     * 因而可以除去英文字符集之外的其它空白，如中文空格。
     * <pre>
     * StringUtil.trim(null, *)          = ""
     * StringUtil.trim("", *)            = ""
     * StringUtil.trim("abc", null)      = "abc"
     * StringUtil.trim("  abc", null)    = "abc"
     * StringUtil.trim("abc  ", null)    = "abc"
     * StringUtil.trim(" abc ", null)    = "abc"
     * StringUtil.trim("  abcyx", "xyz") = "  abc"
     * </pre>
     * </p>
     *
     * @param str 要处理的字符串
     * @param stripChars 字符串
     *
     * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回<code>null</code>
     */
    public static String trimToEmpty(String str, String stripChars)
    {
        String result = trim(str, stripChars);
        
        if (result == null)
        {
            return EMPTY_STRING;
        }
        
        return result;
    }
    
    /**
     * 除去字符串头尾部的指定字符，如果字符串是<code>null</code>，依然返回<code>null</code>。
     * <pre>
     * StringUtil.trim(null, *)          = null
     * StringUtil.trim("", *)            = ""
     * StringUtil.trim("abc", null)      = "abc"
     * StringUtil.trim("  abc", null)    = "abc"
     * StringUtil.trim("abc  ", null)    = "abc"
     * StringUtil.trim(" abc ", null)    = "abc"
     * StringUtil.trim("  abcyx", "xyz") = "  abc"
     * </pre>
     *
     * @param str 要处理的字符串
     * @param stripChars 要除去的字符，如果为<code>null</code>表示除去空白字符
     * @param mode <code>-1</code>表示trimStart，<code>0</code>表示trim全部，<code>1</code>表示trimEnd
     *
     * @return 除去指定字符后的的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
     */
    private static String trim(String str, String stripChars, int mode)
    {
        if (str == null)
        {
            return null;
        }
        
        int length = str.length();
        int start = 0;
        int end = length;
        
        // 扫描字符串头部
        if (mode <= 0)
        {
            if (stripChars == null)
            {
                while ((start < end) && (Character.isWhitespace(str.charAt(start))))
                {
                    start++;
                }
            }
            else if (stripChars.length() == 0)
            {
                return str;
            }
            else
            {
                while ((start < end) && (stripChars.indexOf(str.charAt(start)) != -1))
                {
                    start++;
                }
            }
        }
        
        // 扫描字符串尾部
        if (mode >= 0)
        {
            if (stripChars == null)
            {
                while ((start < end) && (Character.isWhitespace(str.charAt(end - 1))))
                {
                    end--;
                }
            }
            else if (stripChars.length() == 0)
            {
                return str;
            }
            else
            {
                while ((start < end) && (stripChars.indexOf(str.charAt(end - 1)) != -1))
                {
                    end--;
                }
            }
        }
        
        if ((start > 0) || (end < length))
        {
            return str.substring(start, end);
        }
        
        return str;
    }
    
    /* ============================================================================ */
    /*  比较函数。                                                                  */
    /*                                                                              */
    /*  以下方法用来比较两个字符串是否相同。                                        */
    /* ============================================================================ */
    
    /**
     * 比较两个字符串（大小写敏感）。
     * <pre>
     * StringUtil.equals(null, null)   = true
     * StringUtil.equals(null, "abc")  = false
     * StringUtil.equals("abc", null)  = false
     * StringUtil.equals("abc", "abc") = true
     * StringUtil.equals("abc", "ABC") = false
     * </pre>
     *
     * @param str1 要比较的字符串1
     * @param str2 要比较的字符串2
     *
     * @return 如果两个字符串相同，或者都是<code>null</code>，则返回<code>true</code>
     */
    public static boolean equals(String str1, String str2)
    {
        if (str1 == null)
        {
            return str2 == null;
        }
        
        return str1.equals(str2);
    }
    
    /**
     * 比较两个字符串（大小写不敏感）。
     * <pre>
     * StringUtil.equalsIgnoreCase(null, null)   = true
     * StringUtil.equalsIgnoreCase(null, "abc")  = false
     * StringUtil.equalsIgnoreCase("abc", null)  = false
     * StringUtil.equalsIgnoreCase("abc", "abc") = true
     * StringUtil.equalsIgnoreCase("abc", "ABC") = true
     * </pre>
     *
     * @param str1 要比较的字符串1
     * @param str2 要比较的字符串2
     *
     * @return 如果两个字符串相同，或者都是<code>null</code>，则返回<code>true</code>
     */
    public static boolean equalsIgnoreCase(String str1, String str2)
    {
        if (str1 == null)
        {
            return str2 == null;
        }
        
        return str1.equalsIgnoreCase(str2);
    }
    
    /* ============================================================================ */
    /*  字符串类型判定函数。                                                        */
    /*                                                                              */
    /*  判定字符串的类型是否为：字母、数字、空白等                                  */
    /* ============================================================================ */
    
    /**
     * 判断字符串是否只包含unicode字母。
     * 
     * <p>
     * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回<code>true</code>。
     * </p>
     * <pre>
     * StringUtil.isAlpha(null)   = false
     * StringUtil.isAlpha("")     = true
     * StringUtil.isAlpha("  ")   = false
     * StringUtil.isAlpha("abc")  = true
     * StringUtil.isAlpha("ab2c") = false
     * StringUtil.isAlpha("ab-c") = false
     * </pre>
     *
     * @param str 要检查的字符串
     *
     * @return 如果字符串非<code>null</code>并且全由unicode字母组成，则返回<code>true</code>
     */
    public static boolean isAlpha(String str)
    {
        if (str == null)
        {
            return false;
        }
        
        int length = str.length();
        
        for (int i = 0; i < length; i++)
        {
            if (!Character.isLetter(str.charAt(i)))
            {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * 判断字符串是否只包含unicode字母和空格<code>' '</code>。
     * 
     * <p>
     * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回<code>true</code>。
     * </p>
     * <pre>
     * StringUtil.isAlphaSpace(null)   = false
     * StringUtil.isAlphaSpace("")     = true
     * StringUtil.isAlphaSpace("  ")   = true
     * StringUtil.isAlphaSpace("abc")  = true
     * StringUtil.isAlphaSpace("ab c") = true
     * StringUtil.isAlphaSpace("ab2c") = false
     * StringUtil.isAlphaSpace("ab-c") = false
     * </pre>
     *
     * @param str 要检查的字符串
     *
     * @return 如果字符串非<code>null</code>并且全由unicode字母和空格组成，则返回<code>true</code>
     */
    public static boolean isAlphaSpace(String str)
    {
        if (str == null)
        {
            return false;
        }
        
        int length = str.length();
        
        for (int i = 0; i < length; i++)
        {
            if (!Character.isLetter(str.charAt(i)) && (str.charAt(i) != ' '))
            {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * 判断字符串是否只包含unicode字母和数字。
     * 
     * <p>
     * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回<code>true</code>。
     * </p>
     * <pre>
     * StringUtil.isAlphanumeric(null)   = false
     * StringUtil.isAlphanumeric("")     = true
     * StringUtil.isAlphanumeric("  ")   = false
     * StringUtil.isAlphanumeric("abc")  = true
     * StringUtil.isAlphanumeric("ab c") = false
     * StringUtil.isAlphanumeric("ab2c") = true
     * StringUtil.isAlphanumeric("ab-c") = false
     * </pre>
     *
     * @param str 要检查的字符串
     *
     * @return 如果字符串非<code>null</code>并且全由unicode字母数字组成，则返回<code>true</code>
     */
    public static boolean isAlphanumeric(String str)
    {
        if (str == null)
        {
            return false;
        }
        
        int length = str.length();
        
        for (int i = 0; i < length; i++)
        {
            if (!Character.isLetterOrDigit(str.charAt(i)))
            {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * 判断字符串是否只包含unicode字母数字和空格<code>' '</code>。
     * 
     * <p>
     * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回<code>true</code>。
     * </p>
     * <pre>
     * StringUtil.isAlphanumericSpace(null)   = false
     * StringUtil.isAlphanumericSpace("")     = true
     * StringUtil.isAlphanumericSpace("  ")   = true
     * StringUtil.isAlphanumericSpace("abc")  = true
     * StringUtil.isAlphanumericSpace("ab c") = true
     * StringUtil.isAlphanumericSpace("ab2c") = true
     * StringUtil.isAlphanumericSpace("ab-c") = false
     * </pre>
     *
     * @param str 要检查的字符串
     *
     * @return 如果字符串非<code>null</code>并且全由unicode字母数字和空格组成，则返回<code>true</code>
     */
    public static boolean isAlphanumericSpace(String str)
    {
        if (str == null)
        {
            return false;
        }
        
        int length = str.length();
        
        for (int i = 0; i < length; i++)
        {
            if (!Character.isLetterOrDigit(str.charAt(i)) && (str.charAt(i) != ' '))
            {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * 判断字符串是否只包含unicode数字。
     * 
     * <p>
     * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回<code>true</code>。
     * </p>
     * <pre>
     * StringUtil.isNumeric(null)   = false
     * StringUtil.isNumeric("")     = true
     * StringUtil.isNumeric("  ")   = false
     * StringUtil.isNumeric("123")  = true
     * StringUtil.isNumeric("12 3") = false
     * StringUtil.isNumeric("ab2c") = false
     * StringUtil.isNumeric("12-3") = false
     * StringUtil.isNumeric("12.3") = false
     * </pre>
     *
     * @param str 要检查的字符串
     *
     * @return 如果字符串非<code>null</code>并且全由unicode数字组成，则返回<code>true</code>
     */
    public static boolean isNumeric(String str)
    {
        if (str == null)
        {
            return false;
        }
        
        int length = str.length();
        
        for (int i = 0; i < length; i++)
        {
            if (!Character.isDigit(str.charAt(i)))
            {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * 判断字符串是否只包含unicode数字和空格<code>' '</code>。
     * 
     * <p>
     * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回<code>true</code>。
     * </p>
     * <pre>
     * StringUtil.isNumericSpace(null)   = false
     * StringUtil.isNumericSpace("")     = true
     * StringUtil.isNumericSpace("  ")   = true
     * StringUtil.isNumericSpace("123")  = true
     * StringUtil.isNumericSpace("12 3") = true
     * StringUtil.isNumericSpace("ab2c") = false
     * StringUtil.isNumericSpace("12-3") = false
     * StringUtil.isNumericSpace("12.3") = false
     * </pre>
     *
     * @param str 要检查的字符串
     *
     * @return 如果字符串非<code>null</code>并且全由unicode数字和空格组成，则返回<code>true</code>
     */
    public static boolean isNumericSpace(String str)
    {
        if (str == null)
        {
            return false;
        }
        
        int length = str.length();
        
        for (int i = 0; i < length; i++)
        {
            if (!Character.isDigit(str.charAt(i)) && (str.charAt(i) != ' '))
            {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * 判断字符串是否只包含unicode空白。
     * 
     * <p>
     * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回<code>true</code>。
     * </p>
     * <pre>
     * StringUtil.isWhitespace(null)   = false
     * StringUtil.isWhitespace("")     = true
     * StringUtil.isWhitespace("  ")   = true
     * StringUtil.isWhitespace("abc")  = false
     * StringUtil.isWhitespace("ab2c") = false
     * StringUtil.isWhitespace("ab-c") = false
     * </pre>
     *
     * @param str 要检查的字符串
     *
     * @return 如果字符串非<code>null</code>并且全由unicode空白组成，则返回<code>true</code>
     */
    public static boolean isWhitespace(String str)
    {
        if (str == null)
        {
            return false;
        }
        
        int length = str.length();
        
        for (int i = 0; i < length; i++)
        {
            if (!Character.isWhitespace(str.charAt(i)))
            {
                return false;
            }
        }
        
        return true;
    }
    
    /* ============================================================================ */
    /*  大小写转换。                                                                */
    /* ============================================================================ */
    
    /**
     * 将字符串转换成大写。
     * 
     * <p>
     * 如果字符串是<code>null</code>则返回<code>null</code>。
     * <pre>
     * StringUtil.toUpperCase(null)  = null
     * StringUtil.toUpperCase("")    = ""
     * StringUtil.toUpperCase("aBc") = "ABC"
     * </pre>
     * </p>
     *
     * @param str 要转换的字符串
     *
     * @return 大写字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String toUpperCase(String str)
    {
        if (str == null)
        {
            return null;
        }
        
        return str.toUpperCase();
    }
    
    /**
     * 将字符串转换成小写。
     * 
     * <p>
     * 如果字符串是<code>null</code>则返回<code>null</code>。
     * <pre>
     * StringUtil.toLowerCase(null)  = null
     * StringUtil.toLowerCase("")    = ""
     * StringUtil.toLowerCase("aBc") = "abc"
     * </pre>
     * </p>
     *
     * @param str 要转换的字符串
     *
     * @return 大写字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String toLowerCase(String str)
    {
        if (str == null)
        {
            return null;
        }
        
        return str.toLowerCase();
    }
    
    /**
     * 将字符串的首字符转成大写（<code>Character.toTitleCase</code>），其它字符不变。
     * 
     * <p>
     * 如果字符串是<code>null</code>则返回<code>null</code>。
     * <pre>
     * StringUtil.capitalize(null)  = null
     * StringUtil.capitalize("")    = ""
     * StringUtil.capitalize("cat") = "Cat"
     * StringUtil.capitalize("cAt") = "CAt"
     * </pre>
     * </p>
     *
     * @param str 要转换的字符串
     *
     * @return 首字符为大写的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String capitalize(String str)
    {
        int strLen;
        
        if ((str == null) || ((strLen = str.length()) == 0))
        {
            return str;
        }
        
        return new StringBuffer(strLen).append(Character.toTitleCase(str.charAt(0)))
            .append(str.substring(1))
            .toString();
    }
    
    /**
     * 将字符串的首字符转成小写，其它字符不变。
     * 
     * <p>
     * 如果字符串是<code>null</code>则返回<code>null</code>。
     * <pre>
     * StringUtil.uncapitalize(null)  = null
     * StringUtil.uncapitalize("")    = ""
     * StringUtil.uncapitalize("Cat") = "cat"
     * StringUtil.uncapitalize("CAT") = "cAT"
     * </pre>
     * </p>
     *
     * @param str 要转换的字符串
     *
     * @return 首字符为小写的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String uncapitalize(String str)
    {
        int strLen;
        
        if ((str == null) || ((strLen = str.length()) == 0))
        {
            return str;
        }
        
        return new StringBuffer(strLen).append(Character.toLowerCase(str.charAt(0)))
            .append(str.substring(1))
            .toString();
    }
    
    /**
     * 反转字符串的大小写。
     * 
     * <p>
     * 如果字符串是<code>null</code>则返回<code>null</code>。
     * <pre>
     * StringUtil.swapCase(null)                 = null
     * StringUtil.swapCase("")                   = ""
     * StringUtil.swapCase("The dog has a BONE") = "tHE DOG HAS A bone"
     * </pre>
     * </p>
     *
     * @param str 要转换的字符串
     *
     * @return 大小写被反转的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String swapCase(String str)
    {
        int strLen;
        
        if ((str == null) || ((strLen = str.length()) == 0))
        {
            return str;
        }
        
        StringBuffer buffer = new StringBuffer(strLen);
        
        char ch = 0;
        
        for (int i = 0; i < strLen; i++)
        {
            ch = str.charAt(i);
            
            if (Character.isUpperCase(ch))
            {
                ch = Character.toLowerCase(ch);
            }
            else if (Character.isTitleCase(ch))
            {
                ch = Character.toLowerCase(ch);
            }
            else if (Character.isLowerCase(ch))
            {
                ch = Character.toUpperCase(ch);
            }
            
            buffer.append(ch);
        }
        
        return buffer.toString();
    }

    

    
    /* ============================================================================ */
    /*  字符串连接函数。                                                            */
    /*                                                                              */
    /*  将多个对象按指定分隔符连接成字符串。                                        */
    /* ============================================================================ */
    
    /**
     * 将数组中的元素连接成一个字符串。
     * <pre>
     * StringUtil.join(null)            = null
     * StringUtil.join([])              = ""
     * StringUtil.join([null])          = ""
     * StringUtil.join(["a", "b", "c"]) = "abc"
     * StringUtil.join([null, "", "a"]) = "a"
     * </pre>
     *
     * @param array 要连接的数组
     *
     * @return 连接后的字符串，如果原数组为<code>null</code>，则返回<code>null</code>
     */
    public static String join(Object[] array)
    {
        return join(array, null);
    }
    
    /**
     * 将数组中的元素连接成一个字符串。
     * <pre>
     * StringUtil.join(null, *)               = null
     * StringUtil.join([], *)                 = ""
     * StringUtil.join([null], *)             = ""
     * StringUtil.join(["a", "b", "c"], ';')  = "a;b;c"
     * StringUtil.join(["a", "b", "c"], null) = "abc"
     * StringUtil.join([null, "", "a"], ';')  = ";;a"
     * </pre>
     *
     * @param array 要连接的数组
     * @param separator 分隔符
     *
     * @return 连接后的字符串，如果原数组为<code>null</code>，则返回<code>null</code>
     */
    public static String join(Object[] array, char separator)
    {
        if (array == null)
        {
            return null;
        }
        
        int arraySize = array.length;
        int bufSize =
            (arraySize == 0) ? 0 : ((((array[0] == null) ? NUM_ONE_SIX 
                : array[0].toString().length()) + 1) * arraySize);
        StringBuffer buf = new StringBuffer(bufSize);
        
        for (int i = 0; i < arraySize; i++)
        {
            if (i > 0)
            {
                buf.append(separator);
            }
            
            if (array[i] != null)
            {
                buf.append(array[i]);
            }
        }
        
        return buf.toString();
    }
    
    /**
     * 将数组中的元素连接成一个字符串。
     * <pre>
     * StringUtil.join(null, *)                = null
     * StringUtil.join([], *)                  = ""
     * StringUtil.join([null], *)              = ""
     * StringUtil.join(["a", "b", "c"], "--")  = "a--b--c"
     * StringUtil.join(["a", "b", "c"], null)  = "abc"
     * StringUtil.join(["a", "b", "c"], "")    = "abc"
     * StringUtil.join([null, "", "a"], ',')   = ",,a"
     * </pre>
     *
     * @param array 要连接的数组
     * @param separator 分隔符
     *
     * @return 连接后的字符串，如果原数组为<code>null</code>，则返回<code>null</code>
     */
    public static String join(Object[] array, String separator)
    {
        if (array == null)
        {
            return null;
        }
        
        if (separator == null)
        {
            separator = EMPTY_STRING;
        }
        
        int arraySize = array.length;
        
        // ArraySize ==  0: Len = 0
        // ArraySize > 0:   Len = NofStrings *(len(firstString) + len(separator))
        //           (估计大约所有的字符串都一样长)
        int bufSize =
            (arraySize == 0) ? 0
                : (arraySize * (((array[0] == null) ? NUM_ONE_SIX : array[0].toString().length()) 
                    + ((separator != null) ? separator.length()
                    : 0)));
        
        StringBuffer buf = new StringBuffer(bufSize);
        
        for (int i = 0; i < arraySize; i++)
        {
            if ((separator != null) && (i > 0))
            {
                buf.append(separator);
            }
            
            if (array[i] != null)
            {
                buf.append(array[i]);
            }
        }
        
        return buf.toString();
    }
    
    /**
     * 将<code>Iterator</code>中的元素连接成一个字符串。
     * <pre>
     * StringUtil.join(null, *)                = null
     * StringUtil.join([], *)                  = ""
     * StringUtil.join([null], *)              = ""
     * StringUtil.join(["a", "b", "c"], "--")  = "a--b--c"
     * StringUtil.join(["a", "b", "c"], null)  = "abc"
     * StringUtil.join(["a", "b", "c"], "")    = "abc"
     * StringUtil.join([null, "", "a"], ',')   = ",,a"
     * </pre>
     *
     * @param iterator 要连接的<code>Iterator</code>
     * @param separator 分隔符
     *
     * @return 连接后的字符串，如果原数组为<code>null</code>，则返回<code>null</code>
     */
    public static String join(Iterator iterator, char separator)
    {
        if (iterator == null)
        {
            return null;
        }
        
        StringBuffer buf = new StringBuffer(NUMBER_TWO_FIVE_SIX); // Java默认值是16, 可能偏小
        
        while (iterator.hasNext())
        {
            Object obj = iterator.next();
            
            if (obj != null)
            {
                buf.append(obj);
            }
            
            if (iterator.hasNext())
            {
                buf.append(separator);
            }
        }
        
        return buf.toString();
    }
    
    /**
     * 将<code>Iterator</code>中的元素连接成一个字符串。
     * <pre>
     * StringUtil.join(null, *)                = null
     * StringUtil.join([], *)                  = ""
     * StringUtil.join([null], *)              = ""
     * StringUtil.join(["a", "b", "c"], "--")  = "a--b--c"
     * StringUtil.join(["a", "b", "c"], null)  = "abc"
     * StringUtil.join(["a", "b", "c"], "")    = "abc"
     * StringUtil.join([null, "", "a"], ',')   = ",,a"
     * </pre>
     *
     * @param iterator 要连接的<code>Iterator</code>
     * @param separator 分隔符
     *
     * @return 连接后的字符串，如果原数组为<code>null</code>，则返回<code>null</code>
     */
    public static String join(Iterator iterator, String separator)
    {
        if (iterator == null)
        {
            return null;
        }
        
        StringBuffer buf = new StringBuffer(NUMBER_TWO_FIVE_SIX); // Java默认值是16, 可能偏小
        
        while (iterator.hasNext())
        {
            Object obj = iterator.next();
            
            if (obj != null)
            {
                buf.append(obj);
            }
            
            if ((separator != null) && iterator.hasNext())
            {
                buf.append(separator);
            }
        }
        
        return buf.toString();
    }

    
    /**
     * 在字符串中查找指定字符集合中的字符，并返回第一个匹配的起始索引。 如果字符串为<code>null</code>，则返回<code>-1</code>。
     * 如果字符集合为<code>null</code>或空，也返回<code>-1</code>。
     * <pre>
     * StringUtil.indexOfAny(null, *)                = -1
     * StringUtil.indexOfAny("", *)                  = -1
     * StringUtil.indexOfAny(*, null)                = -1
     * StringUtil.indexOfAny(*, [])                  = -1
     * StringUtil.indexOfAny("zzabyycdxx",['z','a']) = 0
     * StringUtil.indexOfAny("zzabyycdxx",['b','y']) = 3
     * StringUtil.indexOfAny("aba", ['z'])           = -1
     * </pre>
     *
     * @param str 要扫描的字符串
     * @param searchChars 要搜索的字符集合
     *
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int indexOfAny(String str, char[] searchChars)
    {
        if ((str == null) || (str.length() == 0) || (searchChars == null) || (searchChars.length == 0))
        {
            return -1;
        }
        
        for (int i = 0; i < str.length(); i++)
        {
            char ch = str.charAt(i);
            
            for (int j = 0; j < searchChars.length; j++)
            {
                if (searchChars[j] == ch)
                {
                    return i;
                }
            }
        }
        
        return -1;
    }
    
 
    /**
     * 在字符串中查找指定字符串集合中的字符串，并返回第一个匹配的起始索引。 如果字符串为<code>null</code>，则返回<code>-1</code>。
     * 如果字符串集合为<code>null</code>或空，也返回<code>-1</code>。
     * 如果字符串集合包括<code>""</code>，并且字符串不为<code>null</code>，则返回<code>str.length()</code>
     * <pre>
     * StringUtil.indexOfAny(null, *)                     = -1
     * StringUtil.indexOfAny(*, null)                     = -1
     * StringUtil.indexOfAny(*, [])                       = -1
     * StringUtil.indexOfAny("zzabyycdxx", ["ab","cd"])   = 2
     * StringUtil.indexOfAny("zzabyycdxx", ["cd","ab"])   = 2
     * StringUtil.indexOfAny("zzabyycdxx", ["mn","op"])   = -1
     * StringUtil.indexOfAny("zzabyycdxx", ["zab","aby"]) = 1
     * StringUtil.indexOfAny("zzabyycdxx", [""])          = 0
     * StringUtil.indexOfAny("", [""])                    = 0
     * StringUtil.indexOfAny("", ["a"])                   = -1
     * </pre>
     *
     * @param str 要扫描的字符串
     * @param searchStrs 要搜索的字符串集合
     *
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int indexOfAny(String str, String[] searchStrs)
    {
        if ((str == null) || (searchStrs == null))
        {
            return -1;
        }
        
        int sz = searchStrs.length;
        
        // String's can't have a MAX_VALUEth index.
        int ret = Integer.MAX_VALUE;
        
        int tmp = 0;
        
        for (int i = 0; i < sz; i++)
        {
            String search = searchStrs[i];
            
            if (search == null)
            {
                continue;
            }
            
            tmp = str.indexOf(search);
            
            if (tmp == -1)
            {
                continue;
            }
            
            if (tmp < ret)
            {
                ret = tmp;
            }
        }
        
        return (ret == Integer.MAX_VALUE) ? (-1) : ret;
    }
    
 
    /**
     * 从字符串尾部开始查找指定字符串集合中的字符串，并返回第一个匹配的起始索引。 如果字符串为<code>null</code>，则返回<code>-1</code>。
     * 如果字符串集合为<code>null</code>或空，也返回<code>-1</code>。
     * 如果字符串集合包括<code>""</code>，并且字符串不为<code>null</code>，则返回<code>str.length()</code>
     * <pre>
     * StringUtil.lastIndexOfAny(null, *)                   = -1
     * StringUtil.lastIndexOfAny(*, null)                   = -1
     * StringUtil.lastIndexOfAny(*, [])                     = -1
     * StringUtil.lastIndexOfAny(*, [null])                 = -1
     * StringUtil.lastIndexOfAny("zzabyycdxx", ["ab","cd"]) = 6
     * StringUtil.lastIndexOfAny("zzabyycdxx", ["cd","ab"]) = 6
     * StringUtil.lastIndexOfAny("zzabyycdxx", ["mn","op"]) = -1
     * StringUtil.lastIndexOfAny("zzabyycdxx", ["mn","op"]) = -1
     * StringUtil.lastIndexOfAny("zzabyycdxx", ["mn",""])   = 10
     * </pre>
     *
     * @param str 要扫描的字符串
     * @param searchStrs 要搜索的字符串集合
     *
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int lastIndexOfAny(String str, String[] searchStrs)
    {
        if ((str == null) || (searchStrs == null))
        {
            return -1;
        }
        
        int searchStrsLength = searchStrs.length;
        int index = -1;
        int tmp = 0;
        
        for (int i = 0; i < searchStrsLength; i++)
        {
            String search = searchStrs[i];
            
            if (search == null)
            {
                continue;
            }
            
            tmp = str.lastIndexOf(search);
            
            if (tmp > index)
            {
                index = tmp;
            }
        }
        
        return index;
    }
    
    /**
     * 检查字符串中是否包含指定的字符。如果字符串为<code>null</code>，将返回<code>false</code>。
     * <pre>
     * StringUtil.contains(null, *)    = false
     * StringUtil.contains("", *)      = false
     * StringUtil.contains("abc", 'a') = true
     * StringUtil.contains("abc", 'z') = false
     * </pre>
     *
     * @param str 要扫描的字符串
     * @param searchChar 要查找的字符
     *
     * @return 如果找到，则返回<code>true</code>
     */
    public static boolean contains(String str, char searchChar)
    {
        if ((str == null) || (str.length() == 0))
        {
            return false;
        }
        
        return str.indexOf(searchChar) >= 0;
    }
    
    /**
     * 检查字符串中是否包含指定的字符串。如果字符串为<code>null</code>，将返回<code>false</code>。
     * <pre>
     * StringUtil.contains(null, *)     = false
     * StringUtil.contains(*, null)     = false
     * StringUtil.contains("", "")      = true
     * StringUtil.contains("abc", "")   = true
     * StringUtil.contains("abc", "a")  = true
     * StringUtil.contains("abc", "z")  = false
     * </pre>
     *
     * @param str 要扫描的字符串
     * @param searchStr 要查找的字符串
     *
     * @return 如果找到，则返回<code>true</code>
     */
    public static boolean contains(String str, String searchStr)
    {
        if ((str == null) || (searchStr == null))
        {
            return false;
        }
        
        return str.indexOf(searchStr) >= 0;
    }
 

    
    /**
     * 检查字符串是是否不包含指定字符集合中的字符。
     * 
     * <p>
     * 如果字符串为<code>null</code>，则返回<code>false</code>。 如果字符集合为<code>null</code>则返回<code>true</code>。
     * 但是空字符串永远返回<code>true</code>.
     * </p>
     * <pre>
     * StringUtil.containsNone(null, *)       = true
     * StringUtil.containsNone(*, null)       = true
     * StringUtil.containsNone("", *)         = true
     * StringUtil.containsNone("ab", '')      = true
     * StringUtil.containsNone("abab", 'xyz') = true
     * StringUtil.containsNone("ab1", 'xyz')  = true
     * StringUtil.containsNone("abz", 'xyz')  = false
     * </pre>
     *
     * @param str 要扫描的字符串
     * @param invalid 要查找的字符串
     *
     * @return 如果找到，则返回<code>true</code>
     */
    public static boolean containsNone(String str, char[] invalid)
    {
        if ((str == null) || (invalid == null))
        {
            return true;
        }
        
        int strSize = str.length();
        int validSize = invalid.length;
        
        for (int i = 0; i < strSize; i++)
        {
            char ch = str.charAt(i);
            
            for (int j = 0; j < validSize; j++)
            {
                if (invalid[j] == ch)
                {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * 检查字符串是是否不包含指定字符集合中的字符。
     * 
     * <p>
     * 如果字符串为<code>null</code>，则返回<code>false</code>。 如果字符集合为<code>null</code>则返回<code>true</code>。
     * 但是空字符串永远返回<code>true</code>.
     * </p>
     * <pre>
     * StringUtil.containsNone(null, *)       = true
     * StringUtil.containsNone(*, null)       = true
     * StringUtil.containsNone("", *)         = true
     * StringUtil.containsNone("ab", "")      = true
     * StringUtil.containsNone("abab", "xyz") = true
     * StringUtil.containsNone("ab1", "xyz")  = true
     * StringUtil.containsNone("abz", "xyz")  = false
     * </pre>
     *
     * @param str 要扫描的字符串
     * @param invalidChars 要查找的字符串
     *
     * @return 如果找到，则返回<code>true</code>
     */
    public static boolean containsNone(String str, String invalidChars)
    {
        if ((str == null) || (invalidChars == null))
        {
            return true;
        }
        
        return containsNone(str, invalidChars.toCharArray());
    }
    
    /**
     * 取得指定子串在字符串中出现的次数。
     * 
     * <p>
     * 如果字符串为<code>null</code>或空，则返回<code>0</code>。
     * <pre>
     * StringUtil.countMatches(null, *)       = 0
     * StringUtil.countMatches("", *)         = 0
     * StringUtil.countMatches("abba", null)  = 0
     * StringUtil.countMatches("abba", "")    = 0
     * StringUtil.countMatches("abba", "a")   = 2
     * StringUtil.countMatches("abba", "ab")  = 1
     * StringUtil.countMatches("abba", "xxx") = 0
     * </pre>
     * </p>
     *
     * @param str 要扫描的字符串
     * @param subStr 子字符串
     *
     * @return 子串在字符串中出现的次数，如果字符串为<code>null</code>或空，则返回<code>0</code>
     */
    public static int countMatches(String str, String subStr)
    {
        if ((str == null) || (str.length() == 0) || (subStr == null) || (subStr.length() == 0))
        {
            return 0;
        }
        
        int count = 0;
        int index = 0;
        
        while ((index = str.indexOf(subStr, index)) != -1)
        {
            count++;
            index += subStr.length();
        }
        
        return count;
    }
    
    /* ============================================================================ */
    /*  搜索并取子串函数。                                                          */
    /* ============================================================================ */
    
    
    /**
     * 取得两个分隔符之间的子串。
     * 
     * <p>
     * 如果字符串为<code>null</code>，则返回<code>null</code>。 如果分隔子串为<code>null</code>，则返回<code>null</code>。
     * <pre>
     * StringUtil.substringBetween(null, *, *)          = null
     * StringUtil.substringBetween("", "", "")          = ""
     * StringUtil.substringBetween("", "", "tag")       = null
     * StringUtil.substringBetween("", "tag", "tag")    = null
     * StringUtil.substringBetween("yabcz", null, null) = null
     * StringUtil.substringBetween("yabcz", "", "")     = ""
     * StringUtil.substringBetween("yabcz", "y", "z")   = "abc"
     * StringUtil.substringBetween("yabczyabcz", "y", "z")   = "abc"
     * </pre>
     * </p>
     *
     * @param str 字符串
     * @param open 要搜索的分隔子串1
     * @param close 要搜索的分隔子串2
     * @param fromIndex 从指定index处搜索
     *
     * @return 子串，如果原始串为<code>null</code>或未找到分隔子串，则返回<code>null</code>
     */
    public static String substringBetween(String str, String open, String close, int fromIndex)
    {
        if ((str == null) || (open == null) || (close == null))
        {
            return null;
        }
        
        int start = str.indexOf(open, fromIndex);
        
        if (start != -1)
        {
            int end = str.indexOf(close, start + open.length());
            
            if (end != -1)
            {
                return str.substring(start + open.length(), end);
            }
        }
        
        return null;
    }
    
    /* ============================================================================ */
    /*  删除字符。                                                                  */
    /* ============================================================================ */
    
    /**
     * 删除所有在<code>Character.isWhitespace(char)</code>中所定义的空白。
     * <pre>
     * StringUtil.deleteWhitespace(null)         = null
     * StringUtil.deleteWhitespace("")           = ""
     * StringUtil.deleteWhitespace("abc")        = "abc"
     * StringUtil.deleteWhitespace("   ab  c  ") = "abc"
     * </pre>
     *
     * @param str 要处理的字符串
     *
     * @return 去空白后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String deleteWhitespace(String str)
    {
        if (str == null)
        {
            return null;
        }
        
        int sz = str.length();
        StringBuffer buffer = new StringBuffer(sz);
        
        for (int i = 0; i < sz; i++)
        {
            if (!Character.isWhitespace(str.charAt(i)))
            {
                buffer.append(str.charAt(i));
            }
        }
        
        return buffer.toString();
    }
    
    /* ============================================================================ */
    /*  替换子串。                                                                  */
    /* ============================================================================ */
    
    /**
     * 替换指定的子串，只替换第一个出现的子串。
     * 
     * <p>
     * 如果字符串为<code>null</code>则返回<code>null</code>，如果指定子串为<code>null</code>，则返回原字符串。
     * <pre>
     * StringUtil.replaceOnce(null, *, *)        = null
     * StringUtil.replaceOnce("", *, *)          = ""
     * StringUtil.replaceOnce("aba", null, null) = "aba"
     * StringUtil.replaceOnce("aba", null, null) = "aba"
     * StringUtil.replaceOnce("aba", "a", null)  = "aba"
     * StringUtil.replaceOnce("aba", "a", "")    = "ba"
     * StringUtil.replaceOnce("aba", "a", "z")   = "zba"
     * </pre>
     * </p>
     *
     * @param text 要扫描的字符串
     * @param repl 要搜索的子串
     * @param with 替换字符串
     *
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replaceOnce(String text, String repl, String with)
    {
        return replace(text, repl, with, 1);
    }
    
    /**
     * 替换指定的子串，替换所有出现的子串。
     * 
     * <p>
     * 如果字符串为<code>null</code>则返回<code>null</code>，如果指定子串为<code>null</code>，则返回原字符串。
     * <pre>
     * StringUtil.replace(null, *, *)        = null
     * StringUtil.replace("", *, *)          = ""
     * StringUtil.replace("aba", null, null) = "aba"
     * StringUtil.replace("aba", null, null) = "aba"
     * StringUtil.replace("aba", "a", null)  = "aba"
     * StringUtil.replace("aba", "a", "")    = "b"
     * StringUtil.replace("aba", "a", "z")   = "zbz"
     * </pre>
     * </p>
     *
     * @param text 要扫描的字符串
     * @param repl 要搜索的子串
     * @param with 替换字符串
     *
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replace(String text, String repl, String with)
    {
        return replace(text, repl, with, -1);
    }
    
    /**
     * 替换指定的子串，替换指定的次数。
     * 
     * <p>
     * 如果字符串为<code>null</code>则返回<code>null</code>，如果指定子串为<code>null</code>，则返回原字符串。
     * <pre>
     * StringUtil.replace(null, *, *, *)         = null
     * StringUtil.replace("", *, *, *)           = ""
     * StringUtil.replace("abaa", null, null, 1) = "abaa"
     * StringUtil.replace("abaa", null, null, 1) = "abaa"
     * StringUtil.replace("abaa", "a", null, 1)  = "abaa"
     * StringUtil.replace("abaa", "a", "", 1)    = "baa"
     * StringUtil.replace("abaa", "a", "z", 0)   = "abaa"
     * StringUtil.replace("abaa", "a", "z", 1)   = "zbaa"
     * StringUtil.replace("abaa", "a", "z", 2)   = "zbza"
     * StringUtil.replace("abaa", "a", "z", -1)  = "zbzz"
     * </pre>
     * </p>
     *
     * @param text 要扫描的字符串
     * @param repl 要搜索的子串
     * @param with 替换字符串
     * @param max maximum number of values to replace, or <code>-1</code> if no maximum
     *
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replace(String text, String repl, String with, int max)
    {
        if ((text == null) || (repl == null) || (with == null) || (repl.length() == 0) || (max == 0))
        {
            return text;
        }
        
        StringBuffer buf = new StringBuffer(text.length());
        int start = 0;
        int end = 0;
        
        while ((end = text.indexOf(repl, start)) != -1)
        {
            buf.append(text.substring(start, end)).append(with);
            start = end + repl.length();
            
            if (--max == 0)
            {
                break;
            }
        }
        
        buf.append(text.substring(start));
        return buf.toString();
    }
    
    /**
     * 将字符串中所有指定的字符，替换成另一个。
     * 
     * <p>
     * 如果字符串为<code>null</code>则返回<code>null</code>。
     * <pre>
     * StringUtil.replaceChars(null, *, *)        = null
     * StringUtil.replaceChars("", *, *)          = ""
     * StringUtil.replaceChars("abcba", 'b', 'y') = "aycya"
     * StringUtil.replaceChars("abcba", 'z', 'y') = "abcba"
     * </pre>
     * </p>
     *
     * @param str 要扫描的字符串
     * @param searchChar 要搜索的字符
     * @param replaceChar 替换字符
     *
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replaceChars(String str, char searchChar, char replaceChar)
    {
        if (str == null)
        {
            return null;
        }
        
        return str.replace(searchChar, replaceChar);
    }
    
    /**
     * 将字符串中所有指定的字符，替换成另一个。
     * 
     * <p>
     * 如果字符串为<code>null</code>则返回<code>null</code>。如果搜索字符串为<code>null</code>或空，则返回原字符串。
     * </p>
     * 
     * <p>
     * 例如： <code>replaceChars(&quot;hello&quot;, &quot;ho&quot;, &quot;jy&quot;) = jelly</code>。
     * </p>
     * 
     * <p>
     * 通常搜索字符串和替换字符串是等长的，如果搜索字符串比替换字符串长，则多余的字符将被删除。 如果搜索字符串比替换字符串短，则缺少的字符将被忽略。
     * <pre>
     * StringUtil.replaceChars(null, *, *)           = null
     * StringUtil.replaceChars("", *, *)             = ""
     * StringUtil.replaceChars("abc", null, *)       = "abc"
     * StringUtil.replaceChars("abc", "", *)         = "abc"
     * StringUtil.replaceChars("abc", "b", null)     = "ac"
     * StringUtil.replaceChars("abc", "b", "")       = "ac"
     * StringUtil.replaceChars("abcba", "bc", "yz")  = "ayzya"
     * StringUtil.replaceChars("abcba", "bc", "y")   = "ayya"
     * StringUtil.replaceChars("abcba", "bc", "yzx") = "ayzya"
     * </pre>
     * </p>
     *
     * @param str 要扫描的字符串
     * @param searchChars 要搜索的字符串
     * @param replaceChars 替换字符串
     *
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replaceChars(String str, String searchChars, String replaceChars)
    {
        if ((str == null) || (str.length() == 0) || (searchChars == null) || (searchChars.length() == 0))
        {
            return str;
        }
        
        char[] chars = str.toCharArray();
        int len = chars.length;
        boolean modified = false;
        
        for (int i = 0, isize = searchChars.length(); i < isize; i++)
        {
            char searchChar = searchChars.charAt(i);
            
            if ((replaceChars == null) || (i >= replaceChars.length()))
            {
                // 删除
                int pos = 0;
                
                for (int j = 0; j < len; j++)
                {
                    if (chars[j] != searchChar)
                    {
                        chars[pos++] = chars[j];
                    }
                    else
                    {
                        modified = true;
                    }
                }
                
                len = pos;
            }
            else
            {
                // 替换
                for (int j = 0; j < len; j++)
                {
                    if (chars[j] == searchChar)
                    {
                        chars[j] = replaceChars.charAt(i);
                        modified = true;
                    }
                }
            }
        }
        
        if (!modified)
        {
            return str;
        }
        
        return new String(chars, 0, len);
    }
    
    /**
     * 将指定的子串用另一指定子串覆盖。
     * 
     * <p>
     * 如果字符串为<code>null</code>，则返回<code>null</code>。 负的索引值将被看作<code>0</code>，越界的索引值将被设置成字符串的长度相同的值。
     * <pre>
     * StringUtil.overlay(null, *, *, *)            = null
     * StringUtil.overlay("", "abc", 0, 0)          = "abc"
     * StringUtil.overlay("abcdef", null, 2, 4)     = "abef"
     * StringUtil.overlay("abcdef", "", 2, 4)       = "abef"
     * StringUtil.overlay("abcdef", "", 4, 2)       = "abef"
     * StringUtil.overlay("abcdef", "zzzz", 2, 4)   = "abzzzzef"
     * StringUtil.overlay("abcdef", "zzzz", 4, 2)   = "abzzzzef"
     * StringUtil.overlay("abcdef", "zzzz", -1, 4)  = "zzzzef"
     * StringUtil.overlay("abcdef", "zzzz", 2, 8)   = "abzzzz"
     * StringUtil.overlay("abcdef", "zzzz", -2, -3) = "zzzzabcdef"
     * StringUtil.overlay("abcdef", "zzzz", 8, 10)  = "abcdefzzzz"
     * </pre>
     * </p>
     *
     * @param str 要扫描的字符串
     * @param overlay 用来覆盖的字符串
     * @param start 起始索引
     * @param end 结束索引
     *
     * @return 被覆盖后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String overlay(String str, String overlay, int start, int end)
    {
        if (str == null)
        {
            return null;
        }
        
        if (overlay == null)
        {
            overlay = EMPTY_STRING;
        }
        
        int len = str.length();
        
        if (start < 0)
        {
            start = 0;
        }
        
        if (start > len)
        {
            start = len;
        }
        
        if (end < 0)
        {
            end = 0;
        }
        
        if (end > len)
        {
            end = len;
        }
        
        if (start > end)
        {
            int temp = start;
            
            start = end;
            end = temp;
        }
        
        return new StringBuffer((len + start) - end + overlay.length() + 1).append(str.substring(0, start))
            .append(overlay)
            .append(str.substring(end))
            .toString();
    }
    
    /* ============================================================================ */
    /*  Perl风格的chomp和chop函数。                                                 */
    /* ============================================================================ */
    
    /**
     * 删除字符串末尾的指定字符串。如果字符串不以该字符串结尾，则什么也不做。
     * <pre>
     * StringUtil.chomp(null, *)         = null
     * StringUtil.chomp("", *)           = ""
     * StringUtil.chomp("foobar", "bar") = "foo"
     * StringUtil.chomp("foobar", "baz") = "foobar"
     * StringUtil.chomp("foo", "foo")    = ""
     * StringUtil.chomp("foo ", "foo")   = "foo "
     * StringUtil.chomp(" foo", "foo")   = " "
     * StringUtil.chomp("foo", "foooo")  = "foo"
     * StringUtil.chomp("foo", "")       = "foo"
     * StringUtil.chomp("foo", null)     = "foo"
     * </pre>
     *
     * @param str 要处理的字符串
     * @param separator 要删除的字符串
     *
     * @return 不以指定字符串结尾的字符串，如果原始字串为<code>null</code>，则返回<code>null</code>
     */
    public static String chomp(String str, String separator)
    {
        if ((str == null) || (str.length() == 0) || (separator == null))
        {
            return str;
        }
        
        if (str.endsWith(separator))
        {
            return str.substring(0, str.length() - separator.length());
        }
        
        return str;
    }
    
    /**
     * 删除最后一个字符。
     * 
     * <p>
     * 如果字符串以<code>\r\n</code>结尾，则同时删除它们。
     * <pre>
     * StringUtil.chop(null)          = null
     * StringUtil.chop("")            = ""
     * StringUtil.chop("abc \r")      = "abc "
     * StringUtil.chop("abc\n")       = "abc"
     * StringUtil.chop("abc\r\n")     = "abc"
     * StringUtil.chop("abc")         = "ab"
     * StringUtil.chop("abc\nabc")    = "abc\nab"
     * StringUtil.chop("a")           = ""
     * StringUtil.chop("\r")          = ""
     * StringUtil.chop("\n")          = ""
     * StringUtil.chop("\r\n")        = ""
     * </pre>
     * </p>
     *
     * @param str 要处理的字符串
     *
     * @return 删除最后一个字符的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String chop(String str)
    {
        if (str == null)
        {
            return null;
        }
        
        int strLen = str.length();
        
        if (strLen < NUM_TWO)
        {
            return EMPTY_STRING;
        }
        
        int lastIdx = strLen - 1;
        String ret = str.substring(0, lastIdx);
        char last = str.charAt(lastIdx);
        
        if (last == '\n')
        {
            if (ret.charAt(lastIdx - 1) == '\r')
            {
                return ret.substring(0, lastIdx - 1);
            }
        }
        
        return ret;
    }
    
    /* ============================================================================ */
    /*  重复/对齐字符串。                                                           */
    /* ============================================================================ */
    
    /**
     * 将指定字符串重复n遍。
     * <pre>
     * StringUtil.repeat(null, 2)   = null
     * StringUtil.repeat("", 0)     = ""
     * StringUtil.repeat("", 2)     = ""
     * StringUtil.repeat("a", 3)    = "aaa"
     * StringUtil.repeat("ab", 2)   = "abab"
     * StringUtil.repeat("abcd", 2) = "abcdabcd"
     * StringUtil.repeat("a", -2)   = ""
     * </pre>
     *
     * @param str 要重复的字符串
     * @param repeat 重复次数，如果小于<code>0</code>，则看作<code>0</code>
     *
     * @return 重复n次的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String repeat(String str, int repeat)
    {
        if (str == null)
        {
            return null;
        }
        
        if (repeat <= 0)
        {
            return EMPTY_STRING;
        }
        
        int inputLength = str.length();
        
        if ((repeat == 1) || (inputLength == 0))
        {
            return str;
        }
        
        int outputLength = inputLength * repeat;
        
        switch (inputLength)
        {
            case 1:
                
                char ch = str.charAt(0);
                char[] output1 = new char[outputLength];
                
                for (int i = repeat - 1; i >= 0; i--)
                {
                    output1[i] = ch;
                }
                
                return new String(output1);
                
            case NUM_TWO:
                char ch0 = str.charAt(0);
                char ch1 = str.charAt(1);
                char[] output2 = new char[outputLength];
                
                for (int i = (repeat * NUM_TWO) - NUM_TWO; i >= 0; i--, i--)
                {
                    output2[i] = ch0;
                    output2[i + 1] = ch1;
                }
                
                return new String(output2);
                
            default:
                
                StringBuffer buf = new StringBuffer(outputLength);
                
                for (int i = 0; i < repeat; i++)
                {
                    buf.append(str);
                }
                
                return buf.toString();
        }
    }

    
    /* ============================================================================ */
    /*  反转字符串。                                                                */
    /* ============================================================================ */
    
    /**
     * 反转字符串中的字符顺序。
     * 
     * <p>
     * 如果字符串为<code>null</code>，则返回<code>null</code>。
     * </p>
     * <pre>
     * StringUtil.reverse(null)  = null
     * StringUtil.reverse("")    = ""
     * StringUtil.reverse("bat") = "tab"
     * </pre>
     *
     * @param str 要反转的字符串
     *
     * @return 反转后的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String reverse(String str)
    {
        if ((str == null) || (str.length() == 0))
        {
            return str;
        }
        
        return new StringBuffer(str).reverse().toString();
    }

    /* ============================================================================ */
    /*  取得字符串的缩略。                                                          */
    /* ============================================================================ */
    
    /**
     * 将字符串转换成指定长度的缩略，例如： 将"Now is the time for all good men"转换成"Now is the time for..."。
     * 
     * <ul>
     * <li>
     * 如果<code>str</code>比<code>maxWidth</code>短，直接返回；
     * </li>
     * <li>
     * 否则将它转换成缩略：<code>substring(str, 0, max-3) + "..."</code>；
     * </li>
     * <li>
     * 如果<code>maxWidth</code>小于<code>4</code>抛出<code>IllegalArgumentException</code>；
     * </li>
     * <li>
     * 返回的字符串不可能长于指定的<code>maxWidth</code>。
     * </li>
     * </ul>
     * 
     * <pre>
     * StringUtil.abbreviate(null, *)      = null
     * StringUtil.abbreviate("", 4)        = ""
     * StringUtil.abbreviate("abcdefg", 6) = "abc..."
     * StringUtil.abbreviate("abcdefg", 7) = "abcdefg"
     * StringUtil.abbreviate("abcdefg", 8) = "abcdefg"
     * StringUtil.abbreviate("abcdefg", 4) = "a..."
     * StringUtil.abbreviate("abcdefg", 3) = IllegalArgumentException
     * </pre>
     *
     * @param str 要检查的字符串
     * @param maxWidth 最大长度，不小于<code>4</code>，如果小于<code>4</code>，则看作<code>4</code>
     *
     * @return 字符串缩略，如果原始字符串为<code>null</code>则返回<code>null</code>
     */
    public static String abbreviate(String str, int maxWidth)
    {
        return abbreviate(str, 0, maxWidth);
    }
    
    /**
     * 将字符串转换成指定长度的缩略，例如： 将"Now is the time for all good men"转换成"...is the time for..."。
     * 
     * <p>
     * 和<code>abbreviate(String, int)</code>类似，但是增加了一个“左边界”偏移量。
     * 注意，“左边界”处的字符未必出现在结果字符串的最左边，但一定出现在结果字符串中。
     * </p>
     * 
     * <p>
     * 返回的字符串不可能长于指定的<code>maxWidth</code>。
     * <pre>
     * StringUtil.abbreviate(null, *, *)                = null
     * StringUtil.abbreviate("", 0, 4)                  = ""
     * StringUtil.abbreviate("abcdefghijklmno", -1, 10) = "abcdefg..."
     * StringUtil.abbreviate("abcdefghijklmno", 0, 10)  = "abcdefg..."
     * StringUtil.abbreviate("abcdefghijklmno", 1, 10)  = "abcdefg..."
     * StringUtil.abbreviate("abcdefghijklmno", 4, 10)  = "abcdefg..."
     * StringUtil.abbreviate("abcdefghijklmno", 5, 10)  = "...fghi..."
     * StringUtil.abbreviate("abcdefghijklmno", 6, 10)  = "...ghij..."
     * StringUtil.abbreviate("abcdefghijklmno", 8, 10)  = "...ijklmno"
     * StringUtil.abbreviate("abcdefghijklmno", 10, 10) = "...ijklmno"
     * StringUtil.abbreviate("abcdefghijklmno", 12, 10) = "...ijklmno"
     * StringUtil.abbreviate("abcdefghij", 0, 3)        = IllegalArgumentException
     * StringUtil.abbreviate("abcdefghij", 5, 6)        = IllegalArgumentException
     * </pre>
     * </p>
     *
     * @param str 要检查的字符串
     * @param offset 左边界偏移量
     * @param maxWidth 最大长度，不小于<code>4</code>，如果小于<code>4</code>，则看作<code>4</code>
     *
     * @return 字符串缩略，如果原始字符串为<code>null</code>则返回<code>null</code>
     */
    public static String abbreviate(String str, int offset, int maxWidth)
    {
        if (str == null)
        {
            return null;
        }
        
        // 调整最大宽度
        if (maxWidth < NUM_FOUR)
        {
            maxWidth = NUM_FOUR;
        }
        
        if (str.length() <= maxWidth)
        {
            return str;
        }
        
        if (offset > str.length())
        {
            offset = str.length();
        }
        
        if ((str.length() - offset) < (maxWidth - NUM_THREE))
        {
            offset = str.length() - (maxWidth - NUM_THREE);
        }
        
        if (offset <= NUM_FOUR)
        {
            return str.substring(0, maxWidth - NUM_THREE) + "...";
        }
        
        // 调整最大宽度
        if (maxWidth < NUM_SEVEN)
        {
            maxWidth = NUM_SEVEN;
        }
        
        if ((offset + (maxWidth - NUM_THREE)) < str.length())
        {
            return "..." + abbreviate(str.substring(offset), maxWidth - NUM_THREE);
        }
        
        return "..." + str.substring(str.length() - (maxWidth - NUM_THREE));
    }
    

    public static String randomString(int length) {
        if (length < 1) {
            return null;
        }
        char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
                + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[new Random().nextInt(71)];
        }
        return new String(randBuffer);
    }
    
}
