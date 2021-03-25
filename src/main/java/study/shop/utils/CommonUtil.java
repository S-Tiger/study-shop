package study.shop.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

public class CommonUtil {

    public static String[] fileExtension =  {"xls","xlsx","ppt","pptx","txt","hwp","xml","jpg","png","gif", "zip","pdf"};

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
            if (!"page".equals(name)) {
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
        if(clientIp == null){
            clientIp = request.getHeader("WL-Proxy-Client-IP");
            if(clientIp == null){
                clientIp = request.getHeader("X-Forwared-For");
                if(clientIp == null){
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
        if (originalfileName != null && !"".equals(originalfileName)) {
            if (Arrays.asList(fileExtension).contains(originalfileName.substring(originalfileName.indexOf(".")+1))) {
                return true;
            }else {
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
}
