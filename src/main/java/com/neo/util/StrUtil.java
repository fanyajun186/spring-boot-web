package com.neo.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class StrUtil  extends StringUtils{


    private static final DateFormat DF_DATE_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final DateFormat DF_DATE = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * <p>处理从Map中取出的Null值,默认返回空字符串</p>
     * <pre>
     * Map: {name=sinba,age=11}
     * StrUtil.fixNull( map.get("noSuchKey") )  =  ""
     * StrUtil.fixNull( map.get("name") )       =  "sinba"
     * StrUtil.fixNull( map.get("age") )        =  "11"
     * @param o
     * @return
     * </pre>
     */
    public static String fixNull(Object o) {
        return o == null ? "" : o.toString().trim();
    }

    /**
     * 处理空值
     * @param o
     * @param defaulStr
     * @return
     */
    public static String fixNull(Object o, String defaulStr) {
        return o == null ? defaulStr : o.toString().trim();
    }

    /**
     * 处理BigDecimal Null值
     * @param value
     * @return
     */
    public static BigDecimal fixBigDecimal(BigDecimal value) {
        if (value == null) {
            return BigDecimal.ZERO;
        }
        return value;
    }

    public static BigDecimal toBigDecimal(String str) {
        BigDecimal value = null;
        try {
            value = new BigDecimal(str);
        } catch (Exception e) {
            //ignored exception
        }
        return value;
    }

    public static BigDecimal toBigDecimal(String str, BigDecimal defaultValue) {
        BigDecimal value = null;
        try {
            value = new BigDecimal(str);
        } catch (Exception e) {
            //ignored exception
            value = defaultValue;
        }
        return value;
    }

    /**
     * 将字符串转化为Long类型值，转型错误则设置为null
     * @param longValue
     * @return
     */
    public static Long toLong(String longValue) {
        Long value = null;
        try {
            value = Long.parseLong(longValue);
        } catch (Exception e) {
            //ignored exception
        }
        return value;
    }

    /**
     * 将字符串转化为Long类型，转型错误则使用默认值
     * @param longValue
     * @param defalutValue
     * @return
     */
    public static Long toLong(String longValue, Long defalutValue) {
        Long value = null;
        try {
            value = Long.parseLong(longValue);
        } catch (Exception e) {
            //ignored exception
            value = defalutValue;
        }
        return value;
    }

    public static Integer toInt(String intVal) {
        Integer value = null;
        try {
            value = Integer.parseInt(intVal);
        } catch (Exception e) {
            //ignored exception
        }
        return value;
    }

    public static Integer toInt(String intVal, Integer defalutValue) {
        Integer value = null;
        try {
            value = Integer.parseInt(intVal);
        } catch (Exception e) {
            //ignored exception
            value = defalutValue;
        }
        return value;
    }

    /**
     * 去掉字符串前面的空格
     * @param original
     * @return
     */
    public static String beforeTrim(String original) {
        if (original == null || original.trim().length() == 0)
            return original;
        int len = original.length();
        int st = 0;
        int off = 0; /* avoid getfield opcode */
        char[] originalValue = original.toCharArray();
        char[] val = Arrays.copyOfRange(originalValue, off, off + len);
        ; /* avoid getfield opcode */

        while ((st < len) && (val[off + st] <= ' ')) {
            st++;
        }
        return ((st > 0) || (st < len)) ? original.substring(st, len) : "";
    }

    /**
     * 去掉字符串后面的空格
     * @param original
     * @return
     */
    public static String afterTrim(String original) {
        if (original == null || original.trim().length() == 0)
            return original;
        int len = original.length();
        int st = 0;
        int off = 0; /* avoid getfield opcode */
        char[] originalValue = original.toCharArray();
        char[] val = Arrays.copyOfRange(originalValue, off, off + len); /* avoid getfield opcode */

        while ((st < len) && (val[off + len - 1] <= ' ')) {
            len--;
        }
        return ((st > 0) || (st < len)) ? original.substring(st, len) : "";
    }

    /**
     * 字符串转list
     * @param str
     * @param splitBy
     * @return
     */
    public static List<String> splitToList(String str, String splitBy) {
        if (StringUtils.isBlank(str))
            return null;
        String[] arrays = str.split(splitBy);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < arrays.length; i++) {
            list.add(arrays[i].trim());
        }
        return list;
    }

    /**
     * 
     * @param str
     * @param splitBy
     * @return
     */
    public static List<Long> splitToLongList(String str, String splitBy) {
        if (StringUtils.isBlank(str))
            return null;
        String[] arrays = str.split(splitBy);
        List<Long> list = new ArrayList<Long>();
        for (int i = 0; i < arrays.length; i++) {
            list.add(Long.parseLong(arrays[i].trim()));
        }
        return list;
    }

    /**
     * 将数据库表字段转化为实体类属性
     * e.g. user_info --> userInfo
     * @param columnName
     * @return
     */
    public static String toCamelStyle(String columnName) {
        StringBuffer sb = new StringBuffer();
        boolean match = false;
        for (int i = 0; i < columnName.length(); i++) {
            char ch = columnName.charAt(i);
            if (match && ch >= 97 && ch <= 122)
                ch -= 32;
            if (ch != '_') {
                match = false;
                sb.append(ch);
            } else {
                match = true;
            }
        }
        return sb.toString();
    }

    /**
     * 将驼峰命名法转化为下划线的形式
     * e.g.  UserInfo --> user_info     loginId --> login_id
     * @param name
     * @return
     */
    public static String addUnderscores(String name) {
        StringBuffer buf = new StringBuffer(name.replace('.', '_'));
        for (int i = 1; i < buf.length() - 1; i++) {
            if (Character.isLowerCase(buf.charAt(i - 1)) && Character.isUpperCase(buf.charAt(i)) && Character.isLowerCase(buf.charAt(i + 1))) {
                buf.insert(i++, '_');
            }
        }
        return buf.toString().toLowerCase();
    }

    /**
     * 格式化日期
     * @param date
     * @return if date == null then "" else yyyy-MM-dd HH:mm:ss
     */
    public static String fmtDT(Date date) {
        String dateStr = "";
        if (date != null) {
            dateStr = DF_DATE_TIME.format(date);
        }
        return dateStr;
    }

    /**
     * 格式化日期
     * @param date
     * @return if date == null then "" else yyyy-MM-dd
     */
    public static String fmtD(Date date) {
        String dateStr = "";
        if (date != null) {
            dateStr = DF_DATE.format(date);
        }
        return dateStr;
    }

    /**
     * 从身份证号码中截取生日的年月日
     * @param idCard
     * @return
     */
    public static String getBirthdayFromIdCard(String idCard) {
        if (idCard == null || idCard.length() < 18) {
            return null;
        }
        return idCard.substring(6, 14);
    }

    /**
     * 从身份证号码中截取性别信息 0:女 1:男
     * @param idCard
     * @return
     */
    public static String getGenderFromIdCard(String idCard) {
        if (idCard == null || idCard.length() < 18) {
            return null;
        }
        String id17 = idCard.substring(16, 17);
        return Integer.parseInt(id17) % 2 + "";
    }

    /**
     * 是否包含中文字符
     * @param s
     * @return
     */
    public static boolean isContainChines(String s) {
        for (int i = 0; i < s.length();) {
            int codepoint = s.codePointAt(i);
            i += Character.charCount(codepoint);
            if (Character.UnicodeScript.of(codepoint) == Character.UnicodeScript.HAN) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * 不严格的手机号格式校验
     * 1开头+10位数字
     */
    public static boolean isMobile(String mobile) {
        String regex = "^1\\d{10}$";
        return Pattern.matches(regex, mobile);
    }

    /**
     * 简单的判定手机号码的格式：1 + 10位数字
     * @param mobile
     * @return
     */
    public static boolean isMobileSimple(String mobile) {
        String reg = "^1\\d{10}$";
        return Pattern.matches(reg, mobile);
    }

    /**
     * 区号+座机号码
     * @param fixedPhone
     * @return
     */
    public static boolean isFixedPhone(String fixedPhone) {
        String reg = "[0]{1}[0-9]{2,3}-[0-9]{7,8}";
        return Pattern.matches(reg, fixedPhone);
    }

    /**
     * 按照力蕴电话号码规则校验
     * @param
     * @return
     */
    public static boolean isLyPhone(String phoneNumber) {
        String reg = "^((\\d{3,4})-?)(\\d{7,8})$";
        return Pattern.matches(reg, phoneNumber);
    }

    /**
     * 匹配中国邮政编码 6位数字
     * @param postCode 邮政编码
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean isPostCode(String postCode) {
        String reg = "\\d{6}";
        return Pattern.matches(reg, postCode);
    }

    /**
     * 匹配邮箱
     * @param email 邮箱
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean isEmail(String email) {
        String reg = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        return Pattern.matches(reg, email);
    }

    /**
     * 是否是数字
     * @param number 待校验字符串
     * @return
     */
    public static boolean isNumber(String number) {
        String reg = "-?[0-9]|-?[0-9]+.?[0-9]+";
        return Pattern.matches(reg, number);
    }


    
}
