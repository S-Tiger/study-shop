package study.shop.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

public class CommonUtil {

    public static String[] fileExtension = {"xls", "xlsx", "ppt", "pptx", "txt", "hwp", "xml", "jpg", "png", "gif", "zip", "pdf"};

    /**
     * 검색 조건 파라미터 저장 (URL) 페이지 이동시 사용
     * @param request
     * @return
     */
    public static final String getSearchUrl(HttpServletRequest request) {
        String searchUrl = "";
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = enu.nextElement();
            String value = request.getParameter(name);
            if (!"page" .equals(name)) {
                searchUrl += "&" + name + "=" + value;
            }
        }
        return searchUrl;
    }

    /**
     * IP 조회
     * @param request
     * @return
     */
    public static final String getRemoteIp(HttpServletRequest request) {
        String clientIp = request.getHeader("Proxy-Client-IP");
        if (clientIp == null) {
            clientIp = request.getHeader("WL-Proxy-Client-IP");
            if (clientIp == null) {
                clientIp = request.getHeader("X-Forwared-For");
                if (clientIp == null) {
                    clientIp = request.getRemoteAddr();
                }
            }
        }
        return clientIp;
    }

    /**
     * 파일확장자 체크
     * @param file
     * @return
     */
    public static boolean extensionFilter(MultipartFile file) {
        String originalfileName = file.getOriginalFilename();
        if (originalfileName != null && !"" .equals(originalfileName)) {
            if (Arrays.asList(fileExtension).contains(originalfileName.substring(originalfileName.indexOf(".") + 1))) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Map -> Dto 변환
     * @param map
     * @param obj
     * @return
     */
    public static Object convertMapToObject(Map<String, Object> map, Object obj) {
        String keyAttribute = null;
        String setMethodString = "set";
        String methodString = null;
        Iterator itr = map.keySet().iterator();
        while (itr.hasNext()) {
            keyAttribute = (String) itr.next();
            methodString = setMethodString + keyAttribute.substring(0, 1).toUpperCase() + keyAttribute.substring(1);
            Method[] methods = obj.getClass().getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                if (methodString.equals(methods[i].getName())) {
                    try {
                        methods[i].invoke(obj, map.get(keyAttribute));
                    } catch (Exception e) {
                        System.out.println("Exception{}");
                    }
                }
            }
        }
        return obj;
    }

    /**
     * 메세지 Alert후 Url location.href
     * @param request
     * @param response
     * @param forwardUrl
     * @param msg
     * @throws IOException
     */
    public static final void commonWriter(HttpServletRequest request, HttpServletResponse response, String forwardUrl,
                                          String msg) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write("<script>");
        writer.write("alert('" + msg + "');");
        writer.write("location.href='" + request.getContextPath().concat(forwardUrl) + "';");
        writer.write("</script>");
        writer.flush();
        writer.close();
        return;
    }

    /**
     * 전화번호 유효성체크 (true:정상 , false:비정상)
     * @param phoneNumber
     * @return
     */
    public static final boolean validatePhoneNumber(String phoneNumber) {
        if (StringUtils.isEmpty(phoneNumber)) {
            return false;
        }
        if (!phoneNumber.matches("^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$")) {
            return false;
        }
        return true;
    }


    /**
     * XSS Filter
     * @param str
     * @return
     */
    public static String xssFilter(String str) {
        if (str != null) {
            str = str.replaceAll("&", "&amp;");
            str = str.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
            str = str.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
            str = str.replaceAll("\"", "&quot;");
            str = str.replaceAll("\'", "&#x27;");
            str = str.replaceAll("/", "&#x2F;");
            str = str.replaceAll("\\(", "&#x28;").replaceAll("\\)", "&#x29;");
            str = str.replaceAll("=", "&#x3D;");
            str = str.replaceAll("'", "&#39;");
            str = str.replaceAll("eval\\((.*)\\)", "");
            str = str.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
            str = str.replaceAll("script", "");
        }
        return str;
    }
}