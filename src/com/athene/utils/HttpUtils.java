package com.athene.utils;


import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import cn.trinea.android.common.util.StringUtils;

/**
 * HttpUtils, use common userAgent
 *
 * @author gxwu@lewatek.com 2012-12-13
 */
public class HttpUtils {

    /**
     * http connect time out, the default value of 0 mean we will never time out, see
     * {@link HttpURLConnection#setConnectTimeout(int)}
     */
    public static int connectTimeout = 10000;
    /**
     * http read time out, the default value of 0 disables read timeouts. see
     * {@link HttpURLConnection#setReadTimeout(int)}
     */
    public static int readTimeout = 60000;

    /**
     * url and para separator *
     */
    public static final String URL_AND_PARA_SEPARATOR = "?";
    /**
     * parameters separator *
     */
    public static final String PARAMETERS_SEPARATOR = "&";
    /**
     * paths separator *
     */
    public static final String PATHS_SEPARATOR = "/";
    /**
     * equal sign *
     */
    public static final String EQUAL_SIGN = "=";
    /**
     * http get method *
     */
    public static String HTTP_GET_METHOD = "get";
    /**
     * http post method *
     */
    public static String HTTP_POST_METHOD = "post";

    public static final SimpleDateFormat GMT_FORMAT = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z",
            Locale.ENGLISH);

    /**
     * join paras
     *
     * @param parasMap paras map, key is para name, value is para value
     * @return
     */
    public static String getParas(Map<String, String> parasMap) {
        if (parasMap == null || parasMap.size() == 0) {
            return null;
        }

        StringBuilder paras = new StringBuilder();
        Iterator<Map.Entry<String, String>> ite = parasMap.entrySet().iterator();
        while (ite.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) ite.next();
            paras.append(entry.getKey()).append("=").append(entry.getValue());
            if (ite.hasNext()) {
                paras.append("&");
            }
        }
        return paras.toString();
    }

    /**
     * join paras
     *
     * @param parasMap paras map, key is para name, value is para value
     * @return
     */
    public static String toJson(Map<String, String> parasMap) {
        if (parasMap == null || parasMap.size() == 0) {
            return null;
        }

        StringBuilder paras = new StringBuilder();
        paras.append("{");
        Iterator<Map.Entry<String, String>> ite = parasMap.entrySet().iterator();
        while (ite.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) ite.next();
            paras.append("\"").append(entry.getKey()).append("\":").append(entry.getValue());
            if (ite.hasNext()) {
                paras.append(",");
            }
        }
        paras.append("}");
        return paras.toString();
    }

    /**
     * join url and paras
     * <p/>
     * <pre>
     * getUrlWithParas(null, {(a, b)})                        =   "?a=b";
     * getUrlWithParas("baidu.com", {})                       =   "baidu.com";
     * getUrlWithParas("baidu.com", {(a, b), (i, j)})         =   "baidu.com?a=b&i=j";
     * getUrlWithParas("baidu.com", {(a, b), (i, j), (c, d)}) =   "baidu.com?a=b&i=j&c=d";
     * </pre>
     *
     * @param url      url
     * @param parasMap paras map, key is para name, value is para value
     * @return if url is null, process it as empty string
     */
    public static String getUrlWithParas(String url, Map<String, String> parasMap) {
        StringBuilder urlWithParas = new StringBuilder(isEmpty(url) ? "" : url);
        String paras = getParas(parasMap);
        if (!isEmpty(paras)) {
            urlWithParas.append("?").append(paras);
        }
        return urlWithParas.toString();
    }

    public static String getUrlWithValueEncodeParas(String url, Map<String, String> parasMap) {
        StringBuilder urlWithParas = new StringBuilder(StringUtils.isEmpty(url) ? "" : url);
        String paras = getValueEncodeParas(parasMap);
        if (!StringUtils.isEmpty(paras)) {
            if (url != null) {
                if (!url.contains(URL_AND_PARA_SEPARATOR)) {
                    urlWithParas.append(URL_AND_PARA_SEPARATOR);
                } else {
                    urlWithParas.append(PARAMETERS_SEPARATOR);
                }
            }
            urlWithParas.append(paras);
        }
        return urlWithParas.toString();
    }

    public static String getValueEncodeParas(Map<String, String> parasMap) {
        StringBuilder paras = new StringBuilder("");
        if (parasMap != null && parasMap.size() > 0) {
            Iterator<Map.Entry<String, String>> ite = parasMap.entrySet().iterator();
            while (ite.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry<String, String>) ite.next();
                paras.append(entry.getKey()).append(EQUAL_SIGN).append(utf8Encode(entry.getValue()));
                if (ite.hasNext()) {
                    paras.append(PARAMETERS_SEPARATOR);
                }
            }
        }
        return paras.toString();
    }

    public static String utf8Encode(String url) {
        if (!StringUtils.isEmpty(url)) {
            try {
                return URLEncoder.encode(url, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("UnsupportedEncodingException occurred. ", e);
            }
        }
        return url;
    }

    /**
     * parse gmt time to long
     *
     * @param gmtTime likes Thu, 11 Apr 2013 10:20:30 GMT
     * @return -1 represents exception
     */
    private static long parseGmtTime(String gmtTime) {
        try {
            return GMT_FORMAT.parse(gmtTime).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static boolean isEmpty(String str) {
        return (str == null || str.length() == 0);
    }

    public static String addParaToUrl(String url, String paraKey, String paraValue) {
        if (StringUtils.isEmpty(url)) {
            return url;
        }

        StringBuilder sb = new StringBuilder(url);
        if (!url.contains(URL_AND_PARA_SEPARATOR)) {
            sb.append(URL_AND_PARA_SEPARATOR);
        } else {
            sb.append(PARAMETERS_SEPARATOR);
        }
        return sb.append(paraKey).append(EQUAL_SIGN).append(paraValue).toString();
    }
}
