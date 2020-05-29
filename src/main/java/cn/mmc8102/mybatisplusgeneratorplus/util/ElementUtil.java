package cn.mmc8102.mybatisplusgeneratorplus.util;

import org.dom4j.Attribute;
import org.dom4j.Element;


/**
 * @author wangli
 */
public class ElementUtil {


    /**
     * 根据元素属性获取元素的值
     *
     * @param element       要获取的元素对象
     * @param attributeName 要获取的属性的名
     * @return 属性值
     */
    public static String getAttributeFromElement(Element element, String attributeName) {
        Attribute attribute = element.attribute(attributeName);
        if (attribute != null) {
            return attribute.getText().trim();
        }
        return "";
    }
}
