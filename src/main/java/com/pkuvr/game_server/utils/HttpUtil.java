package com.pkuvr.game_server.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;

public class HttpUtil {
    private static final int HTTP_TIMEOUT = 1000;
    private static Logger log = Logger.getLogger(HttpUtil.class);

    public static String postRequest(String requestURL, String[][] postParams) {
        HttpClient client = new HttpClient();
        client.getParams().setParameter("http.connection.timeout", HTTP_TIMEOUT);
        client.getParams().setParameter("http.protocol.content-charset", "UTF-8");
        PostMethod post = new PostMethod(requestURL);
        NameValuePair[] data = new NameValuePair[postParams.length];
        try {
            for (int i = 0; i < postParams.length; i++) {
                data[i] = new NameValuePair(postParams[i][0], postParams[i][1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        post.setRequestBody(data);
        try {
            client.executeMethod(post);
            return post.getResponseBodyAsString();
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            post.releaseConnection();
        }
        return null;
    }

    public static Object[] postRequestEntityReturnStatusCode(String requestURL, String postParams) {
        HttpClient client = new HttpClient();
        client.getParams().setParameter("http.connection.timeout", HTTP_TIMEOUT);
        client.getParams().setParameter("http.protocol.content-charset", "UTF-8");
        PostMethod post = new PostMethod(requestURL);

        try {

            RequestEntity entity = new ByteArrayRequestEntity(postParams.getBytes("UTF-8"));
            post.setRequestEntity(entity);

            client.executeMethod(post);
            int statusCode = post.getStatusCode();
            String responseData = post.getResponseBodyAsString();
            Object[] result = new Object[2];
            result[0] = Integer.valueOf(statusCode);
            result[1] = responseData;
            return result;
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            post.releaseConnection();
        }
        return null;
    }

    public static Object[] postRequestEntityReturnStatusCode(String requestURL, byte[] postDatas) {
        HttpClient client = new HttpClient();
        client.getParams().setParameter("http.connection.timeout", HTTP_TIMEOUT);
        client.getParams().setParameter("http.protocol.content-charset", "UTF-8");
        PostMethod post = new PostMethod(requestURL);

        try {

            RequestEntity entity = new ByteArrayRequestEntity(postDatas);
            post.setRequestEntity(entity);

            client.executeMethod(post);
            int statusCode = post.getStatusCode();
            String responseData = post.getResponseBodyAsString();
            Object[] result = new Object[2];
            result[0] = Integer.valueOf(statusCode);
            result[1] = responseData;
            return result;
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            post.releaseConnection();
        }
        return null;
    }

    public static String getRequest(String requestURL) {
        HttpClient client = new HttpClient();
        client.getParams().setParameter("http.connection.timeout", HTTP_TIMEOUT);
        client.getParams().setParameter("http.protocol.content-charset", "UTF-8");
        GetMethod get = new GetMethod(requestURL);
        try {
            client.executeMethod(get);
            return get.getResponseBodyAsString();
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            get.releaseConnection();
        }
        return null;
    }

    public static Object[] postRequestEntity(String requestURL, String postParams, String[][] headerParams) {
        HttpClient client = new HttpClient();
        client.getParams().setParameter("http.connection.timeout", HTTP_TIMEOUT);
        client.getParams().setParameter("http.protocol.content-charset", "UTF-8");
        PostMethod post = new PostMethod(requestURL);

        try {
            for (int i = 0; i < headerParams.length; i++) {
                post.setRequestHeader(headerParams[i][0], headerParams[i][1]);
            }
            RequestEntity entity = new ByteArrayRequestEntity(postParams.getBytes("UTF-8"));
            post.setRequestEntity(entity);

            client.executeMethod(post);
            int statusCode = post.getStatusCode();
            String responseData = post.getResponseBodyAsString();
            Object[] result = new Object[2];
            result[0] = Integer.valueOf(statusCode);
            result[1] = responseData;
            return result;
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            post.releaseConnection();
        }
        return null;
    }

    public static String postRequestEntity(String requestURL, String entity) {
        HttpClient client = new HttpClient();
        client.getParams().setParameter("http.connection.timeout", HTTP_TIMEOUT);
        client.getParams().setParameter("http.protocol.content-charset", "UTF-8");
        PostMethod post = new PostMethod(requestURL);
        try {
            post.setRequestHeader("Content-Type", "binary/octet-stream");
            RequestEntity requestEntity = new ByteArrayRequestEntity(entity.getBytes("UTF-8"));
            post.setRequestEntity(requestEntity);
            client.executeMethod(post);
            return post.getResponseBodyAsString();
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            post.releaseConnection();
        }
        return null;
    }

    public static String postRequest(String requestURL, String[][] uploadParams, File uploadFile) {
        URL url = null;
        HttpURLConnection urlconn = null;
        BufferedReader rd = null;

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        String beginData = "--";
        // 分隔符
        String BOUNDARY = "-------------------------" + getUniqueID(12).toLowerCase();

        StringBuffer sb = new StringBuffer();
        if (uploadParams != null && uploadParams.length > 0) {
            for (int i = 0; i < uploadParams.length; i++) {
                sb.append(beginData);
                sb.append(BOUNDARY).append("\r\n");
                sb.append("Content-Disposition: form-data; name=\"");
                sb.append(uploadParams[i][0]).append("\"").append("\r\n\r\n");
                sb.append(uploadParams[i][1]);
                sb.append("\r\n");
            }
        }
        sb.append(beginData);
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data; name=\"uploadData\"; filename=\"");
        sb.append(uploadFile.getName());
        sb.append("\"\r\n");
        sb.append("Content-Type: application/octet-stream\r\n\r\n");

        byte[] data = sb.toString().getBytes();
        byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();

        try {
            url = new URL(requestURL);
            urlconn = (HttpURLConnection) url.openConnection();
            // 设置表单类型和分隔符
            urlconn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            // 设置内容长度
            urlconn.setRequestProperty("Content-Length", String.valueOf(data.length + uploadFile.length() + endData.length));
            urlconn.setRequestMethod("POST");
            urlconn.setDoOutput(true);
            urlconn.setDoInput(true);
            urlconn.setUseCaches(false);
            urlconn.setRequestProperty("connection", "Keep-Alive");
            urlconn.setRequestProperty("cache-control", "no-cache");

            urlconn.connect();

            OutputStream out = urlconn.getOutputStream();
            bos = new BufferedOutputStream(out);
            bos.write(data);

            InputStream ist = new FileInputStream(uploadFile);
            bis = new BufferedInputStream(ist);

            byte[] buffer = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = bis.read(buffer, 0, 1024)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.write(endData);
            bos.flush();

            rd = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));
            sb = new StringBuffer();
            while (rd.ready()) {
                sb.append(rd.readLine());
            }

            return sb.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.flush();
                    bos.close();
                }
                if (bis != null) {
                    bis.close();
                }
                if (urlconn != null) {
                    urlconn.disconnect();
                }
                if (rd != null) {
                    rd.close();
                }
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        return null;
    }

    public static Object[] postRequestNotFile(String requestURL, String[][] uploadParams) {
        Object[] result = new Object[2];
        URL url = null;
        HttpURLConnection httpConn = null;

        BufferedReader rd = null;

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        String beginData = "--";
        // 分隔符
        String BOUNDARY = "-------------------------" + getUniqueID(12).toLowerCase();

        StringBuffer sb = new StringBuffer();
        if (uploadParams != null && uploadParams.length > 0) {
            for (int i = 0; i < uploadParams.length; i++) {
                sb.append(beginData);
                sb.append(BOUNDARY).append("\r\n");
                sb.append("Content-Disposition: form-data; name=\"");
                sb.append(uploadParams[i][0]).append("\"").append("\r\n");
                sb.append("Content-Type: text/plain; charset=utf-8");
                sb.append("Content-Transfer-Encoding: 8bit");
                sb.append("\r\n\r\n");
                sb.append(uploadParams[i][1]);
                sb.append("\r\n");
            }
        }

        byte[] data = sb.toString().getBytes();
        byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();

        try {
            url = new URL(requestURL);
            httpConn = (HttpURLConnection) url.openConnection();
            // 设置表单类型和分隔符
            httpConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            // 设置内容长度
            httpConn.setRequestProperty("Content-Length", String.valueOf(data.length + endData.length));
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            httpConn.setUseCaches(false);
            httpConn.setRequestProperty("connection", "Keep-Alive");
            httpConn.setRequestProperty("cache-control", "no-cache");

            httpConn.connect();

            OutputStream out = httpConn.getOutputStream();
            bos = new BufferedOutputStream(out);

            bos.write(data);

            bos.write(endData);
            bos.flush();

            rd = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            sb = new StringBuffer();
            while (rd.ready()) {
                sb.append(rd.readLine());
            }
            int reponseCode = httpConn.getResponseCode();
            String responseContent = sb.toString();
            result[0] = reponseCode;
            result[1] = responseContent;
            return result;
        } catch (IOException ex) {
            ex.printStackTrace();
            log.error("postRequestNotFile:", ex);
        } finally {
            try {
                if (bos != null) {
                    bos.flush();
                    bos.close();
                }
                if (bis != null) {
                    bis.close();
                }
                if (httpConn != null) {
                    httpConn.disconnect();
                }
                if (rd != null) {
                    rd.close();
                }
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        return null;

    }

    private static String getUniqueID(int len) {
        if (len < 1)
            return null;
        SecureRandom sr = new SecureRandom();
        final char[] alphabet = ("12345abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ67890").toCharArray();
        byte[] b = new byte[len];
        sr.nextBytes(b);
        char[] out = new char[len];
        for (int i = 0; i < len; i++) {
            int index = b[i] % alphabet.length;
            if (index < 0)
                index += alphabet.length;
            out[i] = alphabet[index];
        }
        return new String(out);
    }
}
