package com.neo.util;

import com.neo.annotation.FreeAttribute;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.util.StringUtils;

public class ReflectUtil {
	public static Field getFieldByFieldName(Object obj, String fieldName) {
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}

	public static Object getValueByFieldName(Object obj, String fieldName) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field field = getFieldByFieldName(obj, fieldName);
		Object value = null;
		if (field != null) {
			if (field.isAccessible()) {
				value = field.get(obj);
			} else {
				field.setAccessible(true);
				value = field.get(obj);
				field.setAccessible(false);
			}
		}
		return value;
	}

	public static void setValueByFieldName(Object obj, String fieldName, Object value) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field field = obj.getClass().getDeclaredField(fieldName);
		if (field.isAccessible()) {
			field.set(obj, value);
		} else {
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(false);
		}
	}

	/**
	 * 检查bean中是否有值为null的属性
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean checkBeanIsNull(Object obj) {
		Class<? extends Object> cls = obj.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (Field f : fields) {
			try {
				FreeAttribute free = f.getAnnotation(FreeAttribute.class);
				f.setAccessible(true);
				if (f.get(obj) == null && free == null) {
					return false;
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	/**
	 * 检查对象是否有值
	 * @param obj
	 * @return
	 */
	public static boolean isBeanHasValue(Object obj) {
		Class<? extends Object> cls = obj.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (Field f : fields) {
			try {
				f.setAccessible(true);
				if (f.get(obj) != null) {
					return true;
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * set属性的值到Bean
	 * 
	 * @param bean
	 * @param valMap
	 */
	public static void setFieldValue(Object bean, Map<String, Object> vMap) {
		Class<?> cls = bean.getClass();
		Map<String, Object> valMap = changeMapKey(vMap); // 转换map的key
		// 取出bean里的所有方法
		Method[] methods = cls.getDeclaredMethods();
		Field[] fields = cls.getDeclaredFields();

		for (Field field : fields) {
			try {
				String fieldSetName = parSetName(field.getName());
				if (!checkSetMet(methods, fieldSetName)) {
					continue;
				}
				Method fieldSetMet = cls.getMethod(fieldSetName, field.getType());
				Object value = valMap.get(field.getName());
				if (null != value && !"".equals(value)) {
					String fieldType = field.getType().getSimpleName();
					if ("String".equals(fieldType)) {
						fieldSetMet.invoke(bean, String.valueOf(value));
					} else if ("Date".equals(fieldType)) {
						// Date temp = parseDate((String) value);
						Date temp = (Date) value;
						fieldSetMet.invoke(bean, temp);
					} else if ("Integer".equals(fieldType) || "int".equals(fieldType)) {
						Integer intval = Integer.parseInt(String.valueOf(value));
						fieldSetMet.invoke(bean, intval);
					} else if ("Long".equalsIgnoreCase(fieldType)) {
						Long temp = null;
						if (String.valueOf(value).indexOf(":") > 0) {
							temp = parseDateToLong(String.valueOf(value));
						} else {
							temp = Long.parseLong(String.valueOf(value));
						}
						fieldSetMet.invoke(bean, temp);
					} else if ("Double".equalsIgnoreCase(fieldType)) {
						Double temp = Double.parseDouble(String.valueOf(value));
						fieldSetMet.invoke(bean, temp);
					} else if ("Boolean".equalsIgnoreCase(fieldType)) {
						Boolean temp = Boolean.parseBoolean(String.valueOf(value));
						fieldSetMet.invoke(bean, temp);
					} else {
						System.out.println("not supper type" + fieldType);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
	}

	/**
	 * 拼接在某属性的 set方法
	 * 
	 * @param fieldName
	 * @return String
	 */
	private static String parSetName(String fieldName) {
		if (null == fieldName || "".equals(fieldName)) {
			return null;
		}
		if (Character.isUpperCase(fieldName.charAt(1))) {
			return "set" + fieldName;
		} else {
			return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		}
	}

	/**
	 * 判断是否存在某属性的 set方法
	 * 
	 * @param methods
	 * @param fieldSetMet
	 * @return boolean
	 */
	private static boolean checkSetMet(Method[] methods, String fieldSetMet) {
		for (Method met : methods) {
			if (fieldSetMet.equals(met.getName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param datestr
	 * @return
	 */
	private static Long parseDateToLong(String datestr) {
		if (null == datestr || "".equals(datestr)) {
			return null;
		}
		try {
			String fmtstr = null;
			if (datestr.indexOf(':') > 0) {
				fmtstr = "yyyy-MM-dd HH:mm:ss";
			} else {
				fmtstr = "yyyy-MM-dd";
			}
			SimpleDateFormat sdf = new SimpleDateFormat(fmtstr, Locale.UK);
			Date date = sdf.parse(datestr);
			return date.getTime();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将下划线的key 转换为峰陀式的key
	 * 
	 * @param valMap
	 * @return
	 */
	private static Map<String, Object> changeMapKey(Map<String, Object> valMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (valMap != null) {
			for (String key : valMap.keySet()) {
				map.put(toCamelStyle(key), valMap.get(key));
			}
		}
		return map;
	}

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
	 * 判断object里面是否有空值
	 * 如果是String类型,用StrUtil里面的isBlank方法去判断
	 * @param object
	 * @return
	 */
	public static boolean hasNullValue(Object object) {
		if (object == null) {
			return true;
		}
		Class<? extends Object> cls = object.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (Field f : fields) {
			try {
				f.setAccessible(true);
				Object field = f.get(object);
				if (field == null) {
					return true;
				} else {
					if (field instanceof String) {
						if (StringUtils.isEmpty(field)) {
							return true;
						}
					}
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
