package com.suntendy.core.util.lang;


import java.io.*;
import java.util.*;
import java.text.*;


/**
 * Created by IntelliJ IDEA.
 * User:chenYuan (mail:cayurain@21cn.com)
 * Date: 2004-3-31
 * Time: 11:21:23
 * com.urp.core.util.lang.StringUtil
 */
public class StringUtil {
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final StringUtil stringUtil = new StringUtil();
    private static final char[] BR_TAG = "<BR>".toCharArray();
    private static final char SINGLE_QUOTE_TAG = '\'';
    private static final char DOUBLE_QUOTE_TAG = '\"';

    public static final String EMPTY = "";
    private static final char[] HexChars = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };
    private static final char[] IncertitudeChars = {
            '*', '\\', '/', '\r', '\n', '|', '$', '&',
            '@', '(', ')', '&', '#', ' '
    };

    private StringUtil() {

    }

    // SubStringAfter/SubStringBefore
    //-----------------------------------------------------------------------
    /**
     * <p>Gets the substring before the first occurrence of a separator.
     * The separator is not returned.</p>
     * <p/>
     * <p>A <code>null</code> string input will return <code>null</code>.
     * An empty ("") string input will return the empty string.
     * A <code>null</code> separator will return the input string.</p>
     * <p/>
     * <pre>
     * StringUtils.substringBefore(null, *)      = null
     * StringUtils.substringBefore("", *)        = ""
     * StringUtils.substringBefore("abc", "a")   = ""
     * StringUtils.substringBefore("abcba", "b") = "a"
     * StringUtils.substringBefore("abc", "c")   = "ab"
     * StringUtils.substringBefore("abc", "d")   = "abc"
     * StringUtils.substringBefore("abc", "")    = ""
     * StringUtils.substringBefore("abc", null)  = "abc"
     * </pre>
     *
     * @param str       the String to get a substring from, may be null
     * @param separator the String to search for, may be null
     * @return the substring before the first occurrence of the separator,
     *         <code>null</code> if null String input
     * @since 2.0
     */
    public static String substringBefore(String str, String separator) {
        if (isNULL(str) || separator == null) {
            return str;
        }
        if (separator.length() == 0) {
            return EMPTY;
        }
        int pos = str.indexOf(separator);
        if (pos == -1) {
            return str;
        }
        return str.substring(0, pos);
    }

    /**
     * <p>Gets the substring after the first occurrence of a separator.
     * The separator is not returned.</p>
     * <p/>
     * <p>A <code>null</code> string input will return <code>null</code>.
     * An empty ("") string input will return the empty string.
     * A <code>null</code> separator will return the empty string if the
     * input string is not <code>null</code>.</p>
     * <p/>
     * <pre>
     * StringUtils.substringAfter(null, *)      = null
     * StringUtils.substringAfter("", *)        = ""
     * StringUtils.substringAfter(*, null)      = ""
     * StringUtils.substringAfter("abc", "a")   = "bc"
     * StringUtils.substringAfter("abcba", "b") = "cba"
     * StringUtils.substringAfter("abc", "c")   = ""
     * StringUtils.substringAfter("abc", "d")   = ""
     * StringUtils.substringAfter("abc", "")    = "abc"
     * </pre>
     *
     * @param str       the String to get a substring from, may be null
     * @param separator the String to search for, may be null
     * @return the substring after the first occurrence of the separator,
     *         <code>null</code> if null String input
     * @since 2.0
     */
    public static String substringAfter(String str, String separator) {
        if (isNULL(str)) {
            return str;
        }
        if (separator == null) {
            return EMPTY;
        }
        int pos = str.indexOf(separator);
        if (pos == -1) {
            return EMPTY;
        }
        return str.substring(pos + separator.length());
    }

    /**
     * <p>Gets the substring before the last occurrence of a separator.
     * The separator is not returned.</p>
     * <p/>
     * <p>A <code>null</code> string input will return <code>null</code>.
     * An empty ("") string input will return the empty string.
     * An empty or <code>null</code> separator will return the input string.</p>
     * <p/>
     * <pre>
     * StringUtils.substringBeforeLast(null, *)      = null
     * StringUtils.substringBeforeLast("", *)        = ""
     * StringUtils.substringBeforeLast("abcba", "b") = "abc"
     * StringUtils.substringBeforeLast("abc", "c")   = "ab"
     * StringUtils.substringBeforeLast("a", "a")     = ""
     * StringUtils.substringBeforeLast("a", "z")     = "a"
     * StringUtils.substringBeforeLast("a", null)    = "a"
     * StringUtils.substringBeforeLast("a", "")      = "a"
     * </pre>
     *
     * @param str       the String to get a substring from, may be null
     * @param separator the String to search for, may be null
     * @return the substring before the last occurrence of the separator,
     *         <code>null</code> if null String input
     * @since 2.0
     */
    public static String substringBeforeLast(String str, String separator) {
        if (isNULL(str) || isNULL(separator)) {
            return str;
        }
        int pos = str.lastIndexOf(separator);
        if (pos == -1) {
            return str;
        }
        return str.substring(0, pos);
    }

    /**
     * <p>Gets the substring after the last occurrence of a separator.
     * The separator is not returned.</p>
     * <p/>
     * <p>A <code>null</code> string input will return <code>null</code>.
     * An empty ("") string input will return the empty string.
     * An empty or <code>null</code> separator will return the empty string if
     * the input string is not <code>null</code>.</p>
     * <p/>
     * <pre>
     * StringUtils.substringAfterLast(null, *)      = null
     * StringUtils.substringAfterLast("", *)        = ""
     * StringUtils.substringAfterLast(*, "")        = ""
     * StringUtils.substringAfterLast(*, null)      = ""
     * StringUtils.substringAfterLast("abc", "a")   = "bc"
     * StringUtils.substringAfterLast("abcba", "b") = "a"
     * StringUtils.substringAfterLast("abc", "c")   = ""
     * StringUtils.substringAfterLast("a", "a")     = ""
     * StringUtils.substringAfterLast("a", "z")     = ""
     * </pre>
     *
     * @param str       the String to get a substring from, may be null
     * @param separator the String to search for, may be null
     * @return the substring after the last occurrence of the separator,
     *         <code>null</code> if null String input
     * @since 2.0
     */
    public static String substringAfterLast(String str, String separator) {
        if (isNULL(str)) {
            return str;
        }
        if (isNULL(separator)) {
            return EMPTY;
        }
        int pos = str.lastIndexOf(separator);
        if (pos == -1 || pos == (str.length() - separator.length())) {
            return EMPTY;
        }
        return str.substring(pos + separator.length());
    }

    // Substring between
    //-----------------------------------------------------------------------
    /**
     * <p>Gets the String that is nested in between two instances of the
     * same String.</p>
     * <p/>
     * <p>A <code>null</code> input String returns <code>null</code>.
     * A <code>null</code> tag returns <code>null</code>.</p>
     * <p/>
     * <pre>
     * StringUtils.substringBetween(null, *)            = null
     * StringUtils.substringBetween("", "")             = ""
     * StringUtils.substringBetween("", "tag")          = null
     * StringUtils.substringBetween("tagabctag", null)  = null
     * StringUtils.substringBetween("tagabctag", "")    = ""
     * StringUtils.substringBetween("tagabctag", "tag") = "abc"
     * </pre>
     *
     * @param str the String containing the substring, may be null
     * @param tag the String before and after the substring, may be null
     * @return the substring, <code>null</code> if no match
     * @since 2.0
     */
    public static String substringBetween(String str, String tag) {
        return substringBetween(str, tag, tag);
    }

    /**
     * <p>Gets the String that is nested in between two Strings.
     * Only the first match is returned.</p>
     * <p/>
     * <p>A <code>null</code> input String returns <code>null</code>.
     * A <code>null</code> open/close returns <code>null</code> (no match).
     * An empty ("") open and close returns an empty string.</p>
     * <p/>
     * <pre>
     * StringUtils.substringBetween("wx[b]yz", "[", "]") = "b"
     * StringUtils.substringBetween(null, *, *)          = null
     * StringUtils.substringBetween(*, null, *)          = null
     * StringUtils.substringBetween(*, *, null)          = null
     * StringUtils.substringBetween("", "", "")          = ""
     * StringUtils.substringBetween("", "", "]")         = null
     * StringUtils.substringBetween("", "[", "]")        = null
     * StringUtils.substringBetween("yabcz", "", "")     = ""
     * StringUtils.substringBetween("yabcz", "y", "z")   = "abc"
     * StringUtils.substringBetween("yabczyabcz", "y", "z")   = "abc"
     * </pre>
     *
     * @param str   the String containing the substring, may be null
     * @param open  the String before the substring, may be null
     * @param close the String after the substring, may be null
     * @return the substring, <code>null</code> if no match
     * @since 2.0
     */
    public static String substringBetween(String str, String open, String close) {
        if (str == null || open == null || close == null) {
            return null;
        }
        int start = str.indexOf(open);
        if (start != -1) {
            int end = str.indexOf(close, start + open.length());
            if (end != -1) {
                return str.substring(start + open.length(), end);
            }
        }
        return null;
    }

    /**
     * <p>Searches a String for substrings delimited by a start and end tag,
     * returning all matching substrings in an array.</p>
     * <p/>
     * <p>A <code>null</code> input String returns <code>null</code>.
     * A <code>null</code> open/close returns <code>null</code> (no match).
     * An empty ("") open/close returns <code>null</code> (no match).</p>
     * <p/>
     * <pre>
     * StringUtils.substringsBetween("[a][b][c]", "[", "]") = ["a","b","c"]
     * StringUtils.substringsBetween(null, *, *)            = null
     * StringUtils.substringsBetween(*, null, *)            = null
     * StringUtils.substringsBetween(*, *, null)            = null
     * StringUtils.substringsBetween("", "[", "]")          = []
     * </pre>
     *
     * @param str   the String containing the substrings, null returns null, empty returns empty
     * @param open  the String identifying the start of the substring, empty returns null
     * @param close the String identifying the end of the substring, empty returns null
     * @return a String Array of substrings, or <code>null</code> if no match
     * @since 2.3
     */
    public static String[] substringsBetween(String str, String open, String close) {
        if (str == null || isNULL(open) || isNULL(close)) {
            return null;
        }
        int strLen = str.length();
        if (strLen == 0) {
            return new String[0];
        }
        int closeLen = close.length();
        int openLen = open.length();
        List list = new ArrayList();
        int pos = 0;
        while (pos < (strLen - closeLen)) {
            int start = str.indexOf(open, pos);
            if (start < 0) {
                break;
            }
            start += openLen;
            int end = str.indexOf(close, start);
            if (end < 0) {
                break;
            }
            list.add(str.substring(start, end));
            pos = end + closeLen;
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    /**
     * <p>Replaces a String with another String inside a larger String, once.</p>
     * <p/>
     * <p>A <code>null</code> reference passed to this method is a no-op.</p>
     * <p/>
     * <pre>
     * StringUtils.replaceOnce(null, *, *)        = null
     * StringUtils.replaceOnce("", *, *)          = ""
     * StringUtils.replaceOnce("any", null, *)    = "any"
     * StringUtils.replaceOnce("any", *, null)    = "any"
     * StringUtils.replaceOnce("any", "", *)      = "any"
     * StringUtils.replaceOnce("aba", "a", null)  = "aba"
     * StringUtils.replaceOnce("aba", "a", "")    = "ba"
     * StringUtils.replaceOnce("aba", "a", "z")   = "zba"
     * </pre>
     *
     * @param text text to search and replace in, may be null
     * @param repl the String to search for, may be null
     * @param with the String to replace with, may be null
     * @return the text with any replacements processed,
     *         <code>null</code> if null String input
     */
    public static String replaceOnce(String text, String repl, String with) {
        return replace(text, repl, with, 1);
    }

    /**
     * <p>Replaces all occurrences of a String within another String.</p>
     * <p/>
     * <p>A <code>null</code> reference passed to this method is a no-op.</p>
     * <p/>
     * <pre>
     * StringUtils.replace(null, *, *)        = null
     * StringUtils.replace("", *, *)          = ""
     * StringUtils.replace("any", null, *)    = "any"
     * StringUtils.replace("any", *, null)    = "any"
     * StringUtils.replace("any", "", *)      = "any"
     * StringUtils.replace("aba", "a", null)  = "aba"
     * StringUtils.replace("aba", "a", "")    = "b"
     * StringUtils.replace("aba", "a", "z")   = "zbz"
     * </pre>
     *
     * @param text text to search and replace in, may be null
     * @param repl the String to search for, may be null
     * @param with the String to replace with, may be null
     * @return the text with any replacements processed,
     *         <code>null</code> if null String input
     */
    public static String replace(String text, String repl, String with) {
        return replace(text, repl, with, -1);
    }

    /**
     * <p>Replaces a String with another String inside a larger String,
     * for the first <code>max</code> values of the search String.</p>
     * <p/>
     * <p>A <code>null</code> reference passed to this method is a no-op.</p>
     * <p/>
     * <pre>
     * StringUtils.replace(null, *, *, *)         = null
     * StringUtils.replace("", *, *, *)           = ""
     * StringUtils.replace("any", null, *, *)     = "any"
     * StringUtils.replace("any", *, null, *)     = "any"
     * StringUtils.replace("any", "", *, *)       = "any"
     * StringUtils.replace("any", *, *, 0)        = "any"
     * StringUtils.replace("abaa", "a", null, -1) = "abaa"
     * StringUtils.replace("abaa", "a", "", -1)   = "b"
     * StringUtils.replace("abaa", "a", "z", 0)   = "abaa"
     * StringUtils.replace("abaa", "a", "z", 1)   = "zbaa"
     * StringUtils.replace("abaa", "a", "z", 2)   = "zbza"
     * StringUtils.replace("abaa", "a", "z", -1)  = "zbzz"
     * </pre>
     *
     * @param text text to search and replace in, may be null
     * @param repl the String to search for, may be null
     * @param with the String to replace with, may be null
     * @param max  maximum number of values to replace, or <code>-1</code> if no maximum
     * @return the text with any replacements processed,
     *         <code>null</code> if null String input
     */
    public static String replace(String text, String repl, String with, int max) {
        if (isNULL(text) || isNULL(repl) || with == null || max == 0) {
            return text;
        }
        int start = 0;
        int end = text.indexOf(repl, start);
        if (end == -1) {
            return text;
        }
        int replLength = repl.length();
        int increase = with.length() - replLength;
        increase = (increase < 0 ? 0 : increase);
        increase *= (max < 0 ? 16 : (max > 64 ? 64 : max));
        StringBuffer buf = new StringBuffer(text.length() + increase);
        while (end != -1) {
            buf.append(text.substring(start, end)).append(with);
            start = end + replLength;
            if (--max == 0) {
                break;
            }
            end = text.indexOf(repl, start);
        }
        buf.append(text.substring(start));
        return buf.toString();
    }

    /**
     * @param str 字符串
     * @return 首字母小写
     */
    public static String uncapitalize(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        return new StringBuffer(strLen)
                .append(Character.toLowerCase(str.charAt(0)))
                .append(str.substring(1))
                .toString();
    }

    /**
     * @param str 字符串
     * @return 交换大小写
     */
    public static String swapCase(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        StringBuffer buffer = new StringBuffer(strLen);
        for (int i = 0; i < strLen; i++) {
            char ch = str.charAt(i);
            if (Character.isUpperCase(ch)) {
                ch = Character.toLowerCase(ch);
            } else if (Character.isTitleCase(ch)) {
                ch = Character.toLowerCase(ch);
            } else if (Character.isLowerCase(ch)) {
                ch = Character.toUpperCase(ch);
            }
            buffer.append(ch);
        }
        return buffer.toString();
    }

    /**
     * @param str 字符串
     * @return 首字母大写
     */
    public static String capitalize(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        return new StringBuffer(strLen)
                .append(Character.toTitleCase(str.charAt(0)))
                .append(str.substring(1))
                .toString();
    }

    /**
     * @param str    字符串
     * @param before 原来的字符串
     * @param after  替换后的字符串
     * @return 不区分大小写替换
     */
    static public String replaceIgnoreCase(String str, String before, String after) {
        if (isNULL(str)) return "";
        int beginIndex = 0;
        int endIndex;
        StringBuffer sbRet = new StringBuffer();
        for (endIndex = indexIgnoreCaseOf(str, before); endIndex != -1; endIndex = indexIgnoreCaseOf(str, before, beginIndex)) {
            sbRet.append(str.substring(beginIndex, endIndex));
            sbRet.append(after);
            beginIndex = endIndex + before.length();
        }
        if (beginIndex >= 0) {
            sbRet.append(str.substring(beginIndex));
        }
        return sbRet.toString();
    }

    /**
     * Checks if a String has length.
     * <p><pre>
     * StringUtils.hasLength(null) = false
     * StringUtils.hasLength("") = false
     * StringUtils.hasLength(" ") = true
     * StringUtils.hasLength("Hello") = true
     * </pre>
     *
     * @param str the String to impl, may be null
     * @return <code>true</code> if the String is has length and is not null
     */
    public static boolean hasLength(String str) {
        return (str != null && str.length() > 0);
    }


    // \r\n 2 <br>
    public static String convertNewlines(String input) {
        char[] chars = input.toCharArray();
        int cur = 0;
        int len = chars.length;
        StringBuffer buf = new StringBuffer(len);
        // Loop through each zhex lookin for newlines.
        for (int i = 0; i < len; i++) {
            // If we've found a Unix newline, add BR tag.
            if (chars[i] == '\n') {
                buf.append(chars, cur, i - cur).append(BR_TAG);
                cur = i + 1;
            }
            // If we've found a Windows newline, add BR tag.
            else if (chars[i] == '\r' && i < len - 1 && chars[i + 1] == '\n') {
                buf.append(chars, cur, i - cur).append(BR_TAG);
                i++;
                cur = i + 1;
            }
        }
        // Add whatever chars are left to buffer.
        buf.append(chars, cur, len - cur);
        return buf.toString();
    }

    public static String insertString(String input, int length, String fen) {
        if (isNULL(input)) return "";
        if (length <= 0) return input;
        StringBuffer buf = new StringBuffer(input);
        int len = buf.length() / length;
        if (len <= 1) return input;
        for (int i = len; i >= 1; i--) {
            buf.insert(length * i, fen);
        }
        return buf.toString();
    }

    /**
     * Tests whether a given zhex is alphabetic, numeric or
     *
     * @param c The zhex to be tested
     * @return whether the given zhex is alphameric or not
     */
    public static boolean isAlphaNumeric(char c) {
        return c == '_' ||
                (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z') ||
                (c >= '0' && c <= '9');
    }

    /*
    *  判断一字段值是否数字都是字符
    */
    public static boolean isNumber(String numStr) {
        if (isNULL(numStr)) return false;
        String s = numStr.replaceAll("[0-9;]+", "");
        return s.trim().equals("");
    }

    /**
     * @param numStr 字符串
     * @return 字符串中提出数字
     */
    public static String getNumber(String numStr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < numStr.length(); i++) {
            char c = numStr.charAt(i);
            if (c != ' ' && isNumber(c + "")) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * <p>是否为一个标准的数学数字Checks whether the String a valid Java number.</p>
     * <p/>
     * <p>Valid numbers include hexadecimal marked with the <code>0x</code>
     * qualifier, scientific notation and numbers marked with a type
     * qualifier (e.g. 123L).</p>
     * <p/>
     * <p><code>Null</code> and empty String will return
     * <code>false</code>.</p>
     *
     * @param str the <code>String</code> to check
     * @return <code>true</code> if the string is a correctly formatted number
     */
    public static boolean isStandardNumber(String str) {
        if (isNULL(str)) {
            return false;
        }
        char[] chars = str.toCharArray();
        int sz = chars.length;
        boolean hasExp = false;
        boolean hasDecPoint = false;
        boolean allowSigns = false;
        boolean foundDigit = false;
        // deal with any possible sign up front
        int start = (chars[0] == '-') ? 1 : 0;
        if (sz > start + 1) {
            if (chars[start] == '0' && chars[start + 1] == 'x') {
                int i = start + 2;
                if (i == sz) {
                    return false; // str == "0x"
                }
                // checking hex (it can't be anything else)
                for (; i < chars.length; i++) {
                    if ((chars[i] < '0' || chars[i] > '9')
                            && (chars[i] < 'a' || chars[i] > 'f')
                            && (chars[i] < 'A' || chars[i] > 'F')) {
                        return false;
                    }
                }
                return true;
            }
        }
        sz--; // don't want to loop to the last char, check it afterwords
        // for type qualifiers
        int i = start;
        // loop to the next to last char or to the last char if we need another digit to
        // make a valid number (e.g. chars[0..5] = "1234E")
        while (i < sz || (i < sz + 1 && allowSigns && !foundDigit)) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                foundDigit = true;
                allowSigns = false;

            } else if (chars[i] == '.') {
                if (hasDecPoint || hasExp) {
                    // two decimal points or dec in exponent
                    return false;
                }
                hasDecPoint = true;
            } else if (chars[i] == 'e' || chars[i] == 'E') {
                // we've already taken care of hex.
                if (hasExp) {
                    // two E's
                    return false;
                }
                if (!foundDigit) {
                    return false;
                }
                hasExp = true;
                allowSigns = true;
            } else if (chars[i] == '+' || chars[i] == '-') {
                if (!allowSigns) {
                    return false;
                }
                allowSigns = false;
                foundDigit = false; // we need a digit after the E
            } else {
                return false;
            }
            i++;
        }
        if (i < chars.length) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                // no type qualifier, OK
                return true;
            }
            if (chars[i] == 'e' || chars[i] == 'E') {
                // can't have an E at the last byte
                return false;
            }
            if (!allowSigns
                    && (
                    chars[i] == 'd'
                            || chars[i] == 'D'
                            || chars[i] == 'f'
                            || chars[i] == 'F')) {
                return foundDigit;
            }
            if (chars[i] == 'l'
                    || chars[i] == 'L') {
                // not allowing L with an exponent
                return foundDigit && !hasExp;
            }
            // last zhex is illegal
            return false;
        }
        // allowSigns is true iff the val ends in 'E'
        // found digit it to make sure weird stuff like '.' and '1E-' doesn't pass
        return !allowSigns && foundDigit;
    }


    /**
     * UTF编码转换为真实的字符串
     *
     * @param str UTF 编码列表 \\u格式,支持中英文格式
     * @return UTF编码专字符串
     */
    public static String UTFToString(String str) {
        StringBuffer sb = new StringBuffer();
        try {
            while (str.length() > 0) {
                //4位长度
                if (str.startsWith("\\u") && str.length() >= 6 && str.substring(2, 6).indexOf("\\") == -1) {
                    sb.append((char) Integer.parseInt(str.substring(2, 6), 16));
                    str = str.substring(6);
                    continue;
                }
                //2位长度
                if (str.startsWith("\\u") && str.length() >= 4 && str.substring(2, 4).indexOf("\\") == -1) {
                    sb.append((char) Integer.parseInt(str.substring(2, 4), 16));
                    str = str.substring(4);
                    continue;
                }
                sb.append(str.charAt(0));
                str = str.substring(1);
            }
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return sb.toString();
    }

    /**
     * 切分字符串
     *
     * @param source 切分字符串
     * @param delim  切分表示
     * @return String[]  返回切分结果
     */
    public static String[] split
            (String
                    source, String
                    delim) {
        if (isNULL(delim)) delim = ";";
        if (source == null) return new String[0];
        return source.split(delim);
    }

    /**
     * 此方法将给出的字符串source使用delim划分为单词数组。
     *
     * @param source 需要进行划分的原字符串
     * @param delim  单词的分隔字符
     * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组。
     * @since 0.2
     */
    public static String[] split(String source, char delim) {
        return split(source, String.valueOf(delim));
    }

    /**
     * 此方法将给出的字符串source使用逗号划分为单词数组。
     *
     * @param source 需要进行划分的原字符串
     * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组。
     * @since 0.1
     */

    public static String[] split
            (String
                    source) {
        return split(source, ";");
    }

    /**
     * @param source URL
     * @return String 删除url文件名
     */
    public static String deleteURLFileName(String source) {
        if (isNULL(source)) return "";
        if (source.indexOf("?") != -1) source = substringAfter(source, "?");
        String[] ls = split(source, "/");
        if (ls == null) return "";
        return source.substring(0, source.length() - ls[ls.length - 1].length());
    }


    /**
     * 循环打印字符串数组。
     * 字符串数组的各元素间以指定字符分隔，如果字符串中已经包含指定字符则在字符串的两端加上双引号。
     *
     * @param strings 字符串数组
     * @param delim   分隔符
     * @param out     打印到的输出流
     * @since 0.4
     */

    public static void printStrings
            (String[] strings, String
                    delim, OutputStream
                    out) {
        try {
            if (strings != null) {
                int length = strings.length - 1;
                for (int i = 0; i < length; i++) {
                    if (strings[i] != null) {
                        if (strings[i].indexOf(delim) > -1) {
                            out.write(("\"" + strings[i] + "\"" + delim).getBytes());
                        } else {
                            out.write((strings[i] + delim).getBytes());
                        }
                    } else {
                        out.write("null".getBytes());
                    }
                }
                if (strings[length] != null) {
                    if (strings[length].indexOf(delim) > -1) {
                        out.write(("\"" + strings[length] + "\"").getBytes());
                    } else {
                        out.write(strings[length].getBytes());
                    }
                } else {
                    out.write("".getBytes());
                }
            } else {
                out.write("".getBytes());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 循环打印字符串数组到标准输出。
     * 字符串数组的各元素间以指定字符分隔，如果字符串中已经包含指定字符则在字符串的两端加上双引号。
     *
     * @param strings 字符串数组
     * @param delim   分隔符
     * @since 0.4
     */
    public static void printStrings
            (String[] strings, String
                    delim) {
        printStrings(strings, delim, System.out);
    }

    /**
     * 循环打印字符串数组。
     * 字符串数组的各元素间以逗号分隔，如果字符串中已经包含逗号则在字符串的两端加上双引号。
     *
     * @param strings 字符串数组
     * @param out     打印到的输出流
     * @since 0.2
     */

    public static void printStrings
            (String[] strings, OutputStream
                    out) {
        printStrings(strings, ",", out);
    }

    /**
     * 循环打印字符串数组到系统标准输出流System.out。
     * 字符串数组的各元素间以逗号分隔，如果字符串中已经包含逗号则在字符串的两端加上双引号。
     *
     * @param strings 字符串数组
     * @since 0.2
     */
    public static void printStrings
            (String[] strings) {
        printStrings(strings, ",", System.out);
    }

    /**
     * 将字符串中的变量使用values数组中的内容进行替换。
     * 替换的过程是不进行嵌套的，即如果替换的内容中包含变量表达式时不会替换。
     *
     * @param prefix 变量前缀字符串
     * @param source 带参数的原字符串
     * @param values 替换用的字符串数组
     * @return 替换后的字符串。
     *         如果前缀为null则使用“%”作为前缀；
     *         如果source或者values为null或者values的长度为0则返回source；
     *         如果values的长度大于参数的个数，多余的值将被忽略；
     *         如果values的长度小于参数的个数，则后面的所有参数都使用最后一个值进行替换。
     * @since 0.2
     */
    public static String getReplaceString
            (String
                    prefix, String
                    source,
             String[] values) {
        String result = source;
        if (source == null || values == null || values.length < 1) {
            return source;
        }
        if (prefix == null) {
            prefix = "%";
        }

        for (int i = 0; i < values.length; i++) {
            String argument = prefix + Integer.toString(i + 1);
            int index = result.indexOf(argument);
            if (index != -1) {
                String temp = result.substring(0, index);
                if (i < values.length) {
                    temp += values[i];
                } else {
                    temp += values[values.length - 1];
                }
                temp += result.substring(index + 2);
                result = temp;
            }
        }
        return result;
    }

    /**
     * 将字符串中的变量（以“%”为前导后接数字）使用values数组中的内容进行替换。
     * 替换的过程是不进行嵌套的，即如果替换的内容中包含变量表达式时不会替换。
     *
     * @param source 带参数的原字符串
     * @param values 替换用的字符串数组
     * @return 替换后的字符串
     * @since 0.1
     */
    public static String getReplaceString(String source, String[] values) {
        return getReplaceString("%", source, values);
    }

    /**
     * 字符串数组中是否包含指定的字符串。
     *
     * @param strings       字符串数组
     * @param string        字符串
     * @param caseSensitive 是否大小写敏感
     * @return 包含时返回true，否则返回false
     * @since 0.4
     */
    public static boolean contains(String[] strings, String string, boolean caseSensitive) {
    	for(int i=0;i<strings.length;i++){
    		String string1 = strings[i];
            if (caseSensitive) {
                if (string1.equals(string)) {
                    return true;
                }
            } else {
                if (string1.equalsIgnoreCase(string)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 字符串数组中是否包含指定的字符串。大小写敏感。
     *
     * @param strings 字符串数组
     * @param string  字符串
     * @return 包含时返回true，否则返回false
     * @since 0.4
     */
    public static boolean contains
            (String[] strings, String
                    string) {
        return contains(strings, string, true);
    }

    /**
     * 不区分大小写判定字符串数组中是否包含指定的字符串。
     *
     * @param strings 字符串数组
     * @param string  字符串
     * @return 包含时返回true，否则返回false
     * @since 0.4
     */
    public static boolean containsIgnoreCase
            (String[] strings, String
                    string) {
        return contains(strings, string, false);
    }

    /**
     * 将字符串数组使用指定的分隔符合并成一个字符串。
     *
     * @param array 字符串数组
     * @param delim 分隔符，为null的时候使用""作为分隔符（即没有分隔符）
     * @return 合并后的字符串
     * @since 0.4
     */
    public static String combineStringArray
            (String[] array, String
                    delim) {
        if (array == null) return "";
        if (delim == null) delim = "";
        StringBuffer result = new StringBuffer();
        for(int i=0;i<array.length;i++){
        	String aArray = array[i];
        	result.append(aArray).append(delim);
        }
        return result.toString();
    }

    /**
     * 以指定的字符和长度生成一个该字符的指定长度的字符串。
     *
     * @param c      指定的字符
     * @param length 指定的长度
     * @return 最终生成的字符串
     * @since 0.6
     */
    public static String fillString(char c, int length) {
        String ret = "";
        for (int i = 0; i < length; i++) {
            ret += c;
        }
        return ret;
    }

    /**
     * 包括\t tab 建一起清除 
     * @param value 字符串
     * @return 清除空格 
     */
    public static String trim(String value) {
        if (isNULL(value)) {
            return "";
        } else {
            return replace(value.trim(),"\t","");
        }
    }

    /**
     * 去除左边多余的空格。
     *
     * @param value 待去左边空格的字符串
     * @return 去掉左边空格后的字符串
     * @since 0.6
     */
    public static String trimLeft(String value) {
        String result = value;
        if (result == null) return result;
        char ch[] = result.toCharArray();
        int index = -1;
        for (int i = 0; i < ch.length; i++) {
            if (Character.isWhitespace(ch[i])) {
                index = i;
            } else {
                break;
            }
        }
        if (index != -1) {
            result = result.substring(index + 1);
        }
        return result;
    }

    /**
     * 去除右边多余的空格。
     *
     * @param value 待去右边空格的字符串
     * @return 去掉右边空格后的字符串
     * @since 0.6
     */
    public static String trimRight(String value) {
        String result = value;
        if (result == null) return result;
        char ch[] = result.toCharArray();
        int endIndex = -1;
        for (int i = ch.length - 1; i > -1; i--) {
            if (Character.isWhitespace(ch[i])) {
                endIndex = i;
            } else {
                break;
            }
        }
        if (endIndex != -1) {
            result = result.substring(0, endIndex);
        }
        return result;
    }

    /**
     * 根据转义列表对字符串进行转义。
     *
     * @param source        待转义的字符串
     * @param escapeCharMap 转义列表
     * @return 转义后的字符串
     * @since 0.6
     */
    public static String escapeCharacter(String source, Map escapeCharMap) {
        if (source == null || source.length() == 0) return source;
        if (escapeCharMap.size() == 0) return source;
        StringBuffer sb = new StringBuffer();
        StringCharacterIterator sci = new StringCharacterIterator(source);
        for (char c = sci.first(); c != StringCharacterIterator.DONE; c = sci.next()) {
            String character = String.valueOf(c);
            if (escapeCharMap.containsKey(character)) {
                character = (String) escapeCharMap.get(character);
            }
            sb.append(character);
        }
        return sb.toString();
    }

    /**
     * 得到字符串的字节长度
     *
     * @param source 字符串
     * @return 字符串的字节长度
     * @since 0.6
     */
    public static int getByteLength(String source) {
        int len = 0;
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            int highByte = c >>> 8;
            len += highByte == 0 ? 1 : 2;
        }
        return len;
    }

    /*
    得到字符的 16 位编码
    注意 encodeHex (ss.getBytes("GBK"))
    转时候 getBytes（参数）不同， 得到对应的 GBK 16 位编码
    */
    public static String encodeHex(byte[] bytes, String fen) {
        StringBuffer sb = new StringBuffer();
        int i;
        for (i = 0; i < bytes.length; i++) {
            sb.append(HexChars[(bytes[i] >> 4) & 0xf]);
            sb.append(HexChars[bytes[i] & 0xf]);
            if (fen != null) sb.append(fen);
        }
        return sb.toString();
    }

    /*
    得到字符的 编码
    注意 encodeBytes (ss.getBytes("GBK"))
    转时候 getBytes（参数）不同， 得到对应的 编码 会不同
    */
    public static String encodeBytes
            (
                    byte[] bytes) {
        if (bytes == null) return "";
        StringBuffer buff = new StringBuffer();
        for(int i=0;i<bytes.length;i++){
        	byte aByte = bytes[i];
            buff.append(aByte).append(" ");
        }
        return buff.toString();
    }

    /*
    将String  转为 Unicode 编码
    */
    public static String toUTFString(String value) {
        StringBuffer result = new StringBuffer();
        StringBuffer buff = new StringBuffer(value);
        for (int i = 0; i < buff.length(); i++) {
            result.append("\\u").append(toHexString(buff.charAt(i), 4, '0'));
        }
        return result.toString();
    }

    /**
     * @param value char 基本类型 得到的int
     * @param len   返回保持的长度
     * @param pad   不够填补
     * @return 得到字符的 16 位编码
     */
    public static String toHexString(long value, int len, char pad) {
        StringBuffer sb = new StringBuffer(Long.toHexString(value));
        int npad = len - sb.length();
        while (npad-- > 0) {
            sb.insert(0, pad);
        }
        return new String(sb);
    }

    /*
    * 安装c++的方式得到字符串长度
    */
    public static int getLength(String value) {
        int result = 0;
        if (isNULL(value)) return result;
        StringBuffer buff = new StringBuffer(value);
        for (int i = 0; i < buff.length(); i++) {
            if (buff.substring(i, i + 1).matches("[\\u4E00-\\u9FA5]+")) {
                result = result + 2;
            } else {
                result++;
            }
        }
        return result;
    }

    /*
    判断是否为中文
    */
    public static boolean isChinese
            (String
                    value) {
        if (isNULL(value)) return false;
        StringBuffer buff = new StringBuffer(value);
        for (int i = 0; i < buff.length(); i++) {
            if (buff.substring(i, i + 1).matches("[\\u4E00-\\u9FA5]+")) {
                return true;
            }
        }
        return false;
    }

    /*
    判断是否为空
    */
    public static boolean isNULL(String value) {
        return value == null || value.equals("") || value.length() < 1;
    }

    public static boolean isHttp
            (String
                    http) {
        return !isNULL(http) && http.length() >= 2 && http.toLowerCase().startsWith("http");

    }

    /**
     * 修复HTTP 判断用户是否输入了 http:// 如果没有就增加 http://
     *
     * @param http url
     * @return 修复HTTP nurl
     */
    public static String mendHttp
            (String
                    http) {
        if (isNULL(http) || http.length() < 2) return "http://";
        if (http.toLowerCase().startsWith("http://")) {
            return http;
        } else {
            return "http://" + http;
        }
    }


    /**
     * 生成一个引用
     *
     * @param s 生成一个引用
     * @return String  生成一个引用
     */
    public static String Quote(String s) {
        if (s == null) s = "''";
        return "\'" + s + "\'";
    }


    /**
     * 删除xml 中多的文字
     *
     * @param str
     * @return String
     */
    static public String deleteText(String str) {
        if (str == null) return "";
        if (str.indexOf(">") == -1) return str;
        StringBuffer out = new StringBuffer(str.length());
        while (str.indexOf("<") != -1) {
            out.append(str.substring(str.indexOf("<"), str.indexOf(">") + 1));
            if (str.indexOf("<") == -1) continue;
            str = str.substring(str.indexOf(">") + 1, str.length());
        }
        out.append(str);
        return out.toString().trim();
    }

    /**
     * 中文按照两个的长处理
     *
     * @param ibein
     * @param iend
     * @return String
     */
    static public String substring(String str, int ibein, int iend) {
        StringBuffer resultString = new StringBuffer();
        StringBuffer buff = new StringBuffer(str);
        int result = 0;
        for (int i = 0; i < buff.length(); i++) {
            if (buff.substring(i, i + 1).matches("[\\u4E00-\\u9FA5]+")) {
                result = result + 2;
            } else {
                result++;
            }
            if (result > ibein && result <= iend) {
                resultString.append(buff.substring(i, i + 1));
            }
        }
        return resultString.toString();
    }


    static char toLowerCase
            (
                    char ch) {
        String str = "" + ch;
        return str.toLowerCase().toCharArray()[0];
    }

    static char toUpperCase
            (
                    char ch) {
        String str = "" + ch;
        return str.toUpperCase().toCharArray()[0];
    }

    /**
     * 不区分大小写的 indexOf
     *
     * @param source
     * @param sourceOffset
     * @param sourceCount
     * @param target
     * @param targetOffset
     * @param targetCount
     * @param fromIndex
     * @return indexOf(value,offset,count,
     *str.value,str.offset,str.count,fromIndex);
     */
    static int indexIgnoreCaseOf
            (
                    char[] source,
                    int sourceOffset,
                    int sourceCount,
                    char[] target,
                    int targetOffset,
                    int targetCount,
                    int fromIndex) {
        if (fromIndex >= sourceCount) {
            return (targetCount == 0 ? sourceCount : -1);
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (targetCount == 0) {
            return fromIndex;
        }

        char first = target[targetOffset];
        int i = sourceOffset + fromIndex;
        int max = sourceOffset + (sourceCount - targetCount);

        startSearchForFirstChar:
        while (true) {
            /* Look for first zhex. */
            while (i <= max && (toUpperCase(source[i]) != toUpperCase(first))) {
                i++;
            }
            if (i > max) {
                return -1;
            }

            /* Found first zhex, now look at the rest of v2 */
            int j = i + 1;
            int end = j + targetCount - 1;
            int k = targetOffset + 1;
            while (j < end) {
                if (toUpperCase(source[j++]) != toUpperCase(target[k++])) {
                    i++;
                    /* Look for str's first char again. */
                    continue startSearchForFirstChar;
                }
            }
            return i - sourceOffset;
            /* Found whole string. */
        }
    }

    /**
     * @param str 查找的字符串
     * @param ind 要查询的数组
     * @return 判断字符串中是否存在数组中的字符
     */
    public static boolean indexOfArray(String str, String[] ind) {
        if (str == null) return false;
        if (ind == null) return false;
        for(int i=0;i<ind.length;i++){
        	String ix = ind[i];
            if (str.indexOf(ix) != -1) return true;
        }
        return false;
    }

    /**
     * 接口简化的不区分大小写的 indexOf
     *
     * @param source
     * @param target
     * @return int
     */
    public static int indexIgnoreCaseOf
            (String
                    source, String
                    target) {
        return indexIgnoreCaseOf(source.toCharArray(), 0, source.length(),
                target.toCharArray(), 0, target.length(), 0);
    }

    public static int indexIgnoreCaseOf
            (String
                    source, String
                    target, int begin) {
        return indexIgnoreCaseOf(source.toCharArray(), 0, source.length(),
                target.toCharArray(), 0, target.length(), begin);
    }

    public static boolean toBoolean(String str) {
        return !isNULL(str) && (str.equalsIgnoreCase("TRUE") || str.equals("1") || str.equalsIgnoreCase("yes") || str.equalsIgnoreCase("y") || str.equalsIgnoreCase("T") || str.equalsIgnoreCase("是") || str.equalsIgnoreCase("ok"));
    }


    /**
     * 提供把字符串转为整数
     *
     * @param value 转换的数字
     * @return 四舍五入后的结果
     */
    public static int toInt(String value) {
        if ("true".equalsIgnoreCase(value) || "t".equalsIgnoreCase(value)) return 1;
        if ("false".equalsIgnoreCase(value) || "f".equalsIgnoreCase(value)) return 0;
        return toInt(value, 0);
    }

    /**
     * 提供把字符串转为整数
     *
     * @param value 转换的数字
     * @param nint  空备用返回
     * @return 四舍五入后的结果
     */
    public static int toInt(String value, int nint) {
        if (value == null) return nint;
        if (!StringUtil.isStandardNumber(value)) return nint;
        if (value.indexOf(".") != -1) {
            try {
                return (int) Double.parseDouble(value);
            }
            catch (NumberFormatException e) {
                return nint;
            }
        }
        try {
            return Integer.parseInt(value);
        }
        catch (NumberFormatException e) {
            return nint;
        }
    }

    /**
     * 提供把字符串转为精度数
     *
     * @param sdouble 转换的数字
     * @return 结果
     */
    public static double toDouble
            (String
                    sdouble) {
        return toDouble(sdouble, 0, 0);
    }

    /**
     * 提供把字符串转为精度数
     *
     * @param sdouble 转换的数字
     * @param nint    空备用返回
     * @param bint    出错备用返回
     * @return 结果
     */
    public static double toDouble
            (String
                    sdouble, double nint,
             double bint) {
        if (sdouble == null) return nint;
        if (sdouble.equalsIgnoreCase("null")) return nint;
        if (sdouble.equals("")) return nint;
        try {
            return Double.parseDouble(sdouble);
        }
        catch (NumberFormatException e) {
            return bint;
        }
    }

    /**
     * 转换为Float
     *
     * @param sdouble
     * @return float
     */
    public static float toFloat(String sdouble) {
        return toFloat(sdouble, 0);
    }

    /**
     * 转换为Float
     *
     * @param sdouble
     * @param nint
     * @return float
     */
    public static float toFloat(String sdouble, long nint) {
        if (sdouble == null) return nint;
        try {
            return Float.parseFloat(sdouble);
        }
        catch (NumberFormatException e) {
            return nint;
        }
    }

    /**
     * 转换为 long
     *
     * @param sdouble
     * @return long
     */
    public static long toLong
            (String
                    sdouble) {
        return toLong(sdouble, 0);
    }


    /**
     * @param sdouble 字符串数字
     * @param nint    保留小数
     * @return long 提供把字符串转为Long
     */
    public static long toLong
            (String
                    sdouble, long nint) {
        if (sdouble == null) return nint;
        try {
            return Long.parseLong(sdouble);
        }
        catch (NumberFormatException e) {
            return nint;
        }
    }

    /**
     * @param date 日期字符串
     * @return Date 字符专日期
     */
    public static Date toDate(String date) throws ParseException {
        if (StringUtil.isNULL(date)) return new Date();
        String fomat = null;
        if (date.length() > 16 && StringUtil.countMatches(date, "-") == 2 && StringUtil.countMatches(date, ":") == 2) {
            fomat = DateUtil.FULL_ST_FORMAT;
        } else if (date.length() > 12 && StringUtil.countMatches(date, "-") == 2 && StringUtil.countMatches(date, ":") == 1) {
            fomat = DateUtil.CURRENCY_ST_FORMAT;
        } else if (date.length() > 16 && StringUtil.countMatches(date, "/") == 2 && StringUtil.countMatches(date, ":") == 2) {
            fomat = DateUtil.FULL_J_FORMAT;
        } else if (date.length() > 12 && StringUtil.countMatches(date, "/") == 2 && StringUtil.countMatches(date, ":") == 1) {
            fomat = DateUtil.CURRENCY_J_FORMAT;
        } else if (date.indexOf("年") != -1 && date.indexOf("月") != -1 && date.indexOf("日") != -1 && StringUtil.countMatches(date, ":") == 2) {
            fomat = "yy年MM月dd日HH:mm:ss";
        } else if (date.indexOf("年") != -1 && date.indexOf("月") != -1 && date.indexOf("日") != -1 && StringUtil.countMatches(date, ":") == 1) {
            fomat = DateUtil.CN_FORMAT;
        } else if (date.length() > 8 && date.length() < 11 && StringUtil.countMatches(date, "-") == 2) {
           fomat = "yyyy-MM-dd";
        } else if (date.length() > 8 && date.length() < 11 && StringUtil.countMatches(date, "/") == 2) {
           fomat = "yyyy/MM/dd";
        } else if (date.length() > 5 && date.length() < 9 && StringUtil.countMatches(date, "-") == 2) {
            fomat = DateUtil.SHORT_DATE_FORMAT;
        }
        if (!StringUtil.isNULL(fomat))
            return toDate(date, fomat);
        String sdate = getNumber(date);
        int ilen = sdate.length();
        if (ilen == 4) {
            fomat = "MMdd";
        } else if (ilen == 8) {
            fomat = "yyyyMMdd";
        } else if (ilen == 10) {
            fomat = "yyyyMMddHH";
        } else if (ilen == 12) {
            fomat = "yyyyMMddHHmm";
        }
        if (ilen > 12) {
            fomat = DateUtil.DATA_FORMAT;
        }
        return toDate(date, fomat);
    }

    /**
     * @param sdate      日期字符串
     * @param dateformat 日期格式
     * @return Date 转换为日期
     * @throws java.text.ParseException 转换错误
     */
    public static Date toDate(String sdate, String dateformat) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);
        return dateFormat.parse(sdate);
    }

    /**
     * @param sdate      日期字符串
     * @param dateformat 日期格式
     * @return 判断是否为日期格式
     */
    public static boolean isDate(String sdate, String dateformat) {
        if (sdate == null || dateformat == null) return false;
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);
        try {
            dateFormat.parse(sdate);
        }
        catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * @param s ip字符串
     * @return 判断是否为IP
     */
    static public boolean isIPAddress(String s) {
        if (isNULL(s)) return false;
        String[] ips = s.split("\\.");
        if (ips == null || ips.length != 4) return false;
        for(int i=0;i<ips.length;i++){
        	String ip = ips[i];
            int xx = StringUtil.toInt(ip);
            if (0 < xx || xx > 255) return false;
        }
        return true;
    }

    /**
     * @param sip IP 专 IP 数字
     * @return String String 返回
     */
    static public long toIpNumber(String sip) {
        if (!isIPAddress(sip)) sip = "127.0.0.1";
        String[] ip = sip.split("\\.");
        long a = Integer.parseInt(ip[0]);
        long b = Integer.parseInt(ip[1]);
        long c = Integer.parseInt(ip[2]);
        long d = Integer.parseInt(ip[3]);
        return a * 256 * 256 * 256 + b * 256 * 256 + c * 256 + d;
    }

    /**
     * This method accepts name with char, number or '_' or '.'
     * <p/>
     * This method should be used to impl all LoginName input from user for security.
     */
    public static boolean checkGoodName(String str) {
        if (str == null || str.length() < 2) return false;

        for(int i=0;i<IncertitudeChars.length;i++){
        	char c = IncertitudeChars[i];
            if (str.indexOf(c) != -1) return false;
        }
        return true;
    }

    public static boolean checkMail(String str) {
        return !(str == null || str.length() < 2) && str.indexOf("@") != -1;
    }

    /**
     * 编码是否有效
     *
     * @param text
     * @return boolean
     */
    static public boolean UTF8CodeCheck
            (String
                    text) {
        String sign = "";
        if (text.startsWith("%e")) {
            for (int p = 0; p != -1;) {
                p = text.indexOf("%", p);
                if (p != -1) {
                    p++;
                }
                sign += p;
            }
        }
        return sign.equals("147-1");
    }

    /**
     * @param text url
     * @return boolean 是否Utf8Url编码
     */
    static public boolean isUtf8Url
            (String
                    text) {
        text = text.toLowerCase();
        int p = text.indexOf("%");
        if (p != -1 && text.length() - p > 9) {
            text = text.substring(p, p + 9);
        }
        return UTF8CodeCheck(text);
    }

    static public boolean charCountEquals
            (String
                    text, String
                    left, String
                    right) {
        return countMatches(text, left) == countMatches(text, right);
    }

    /**
     * <p>Counts how many times the substring appears in the larger String.</p>
     * <p/>
     * <p>A <code>null</code> or empty ("") String input returns <code>0</code>.</p>
     * <p/>
     * <pre>
     * StringUtils.countMatches(null, *)       = 0
     * StringUtils.countMatches("", *)         = 0
     * StringUtils.countMatches("abba", null)  = 0
     * StringUtils.countMatches("abba", "")    = 0
     * StringUtils.countMatches("abba", "a")   = 2
     * StringUtils.countMatches("abba", "ab")  = 1
     * StringUtils.countMatches("abba", "xxx") = 0
     * </pre>
     *
     * @param str the String to check, may be null
     * @param sub the substring to count, may be null
     * @return the number of occurrences, 0 if either String is <code>null</code>
     */
    public static int countMatches(String str, String sub) {
        if (isNULL(str) || isNULL(sub)) {
            return 0;
        }
        int count = 0;
        int idx = 0;
        while ((idx = str.indexOf(sub, idx)) != -1) {
            count++;
            idx += sub.length();
        }
        return count;
    }

    /**
     * 如果str 为空返回 defaultStr
     *
     * @param str        字符串
     * @param defaultStr 默认
     * @return 如果str 为空返回 defaultStr
     */
    public static String defaultIfEmpty
            (String
                    str, String
                    defaultStr) {
        return isNULL(str) ? defaultStr : str;
    }

    /**
     * 修复namespace
     *
     * @param namespace 命名恐惧
     * @return 修复命名空间
     */
    public static String fixedNamespace
            (String
                    namespace) {
        if (namespace == null) return null;
        if (namespace.endsWith("/")) {
            namespace = namespace.substring(0, namespace.length() - 1);
        }
        if (namespace.startsWith("/")) {
            namespace = namespace.substring(1, namespace.length());
        }

        return namespace;
    }

    /**
     * 得到Freemarker 的变量列表
     *
     * @return 得到变量列表
     */
    public static String[] getFreeMarkerVar
            (String
                    str) {
        StringBuffer sb = new StringBuffer();
        int length = str.length();
        boolean isVar = false;
        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            if (c == '$' && i < length && str.charAt(i + 1) == '{') {
                isVar = true;
            }
            if (isVar && c == '}') {
                isVar = false;
                sb.append(";");
            }
            if (isVar && str.charAt(i) != '$' && str.charAt(i) != '{') {
                sb.append(c);
            }
        }
        return split(sb.toString(), ";");
    }

    /**
     * 判断都是半角
     *
     * @param str 字符串
     * @return 判断都是半角 0:半角    1:混合 2:全部是全角
     */
    public static int getCompareHalf(String str) {
        if (isNULL(str)) return 0;
        if (str.getBytes().length == str.length()) {
            return 0;
        }
        if (str.getBytes().length == str.length() * 2) {
            return 2;
        }
        if (str.getBytes().length < str.length() * 2 && str.getBytes().length > str.length()) {
            return 1;
        }
        return 0;
    }

}
