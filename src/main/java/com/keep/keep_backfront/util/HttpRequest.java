package com.keep.keep_backfront.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
        mv = {1, 1, 16},
        bv = {1, 0, 3},
        k = 1,
        d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004J\u0016\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004¨\u0006\b"},
        d2 = {"Lcom/keep/keep_backfront/util/HttpRequest;", "", "()V", "sendGet", "", "url", "param", "sendPost", "keep_backfront"}
)
public final class HttpRequest {
    public static final HttpRequest INSTANCE;

    @NotNull
    public static final String sendGet(@NotNull String url, @NotNull String param) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(param, "param");
        String result = "";
        BufferedReader in = (BufferedReader)null;
        boolean var18 = false;

        label130: {
            try {
                var18 = true;
                String urlNameString = url + '?' + param;
                URL realUrl = new URL(urlNameString);
                URLConnection connection = realUrl.openConnection();
                connection.setRequestProperty("accept", "*/*");
                connection.setRequestProperty("connection", "Keep-Alive");
                connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                connection.connect();
                Intrinsics.checkExpressionValueIsNotNull(connection, "connection");
                Map map = connection.getHeaderFields();
                Iterator var10 = map.keySet().iterator();

                String line;
                while(var10.hasNext()) {
                    line = (String)var10.next();
                    String var11 = line + "--->" + (List)map.get(line);
                    boolean var12 = false;
                    System.out.println(var11);
                }

                in = new BufferedReader((Reader)(new InputStreamReader(connection.getInputStream())));

                while(true) {
                    String var10000 = in.readLine();
                    if (var10000 == null) {
                        var18 = false;
                        break label130;
                    }

                    line = var10000;
                    result = result + line;
                }
            } catch (Exception var22) {
                String var6 = "发送GET请求出现异常！" + var22;
                boolean var7 = false;
                System.out.println(var6);
                var22.printStackTrace();
                var18 = false;
            } finally {
                if (var18) {
                    try {
                        if (in != null) {
                            in.close();
                        }
                    } catch (Exception var19) {
                        var19.printStackTrace();
                    }

                }
            }

            try {
                if (in != null) {
                    in.close();
                }

                return result;
            } catch (Exception var20) {
                var20.printStackTrace();
                return result;
            }
        }

        try {
            in.close();
        } catch (Exception var21) {
            var21.printStackTrace();
        }

        return result;
    }

    @NotNull
    public final String sendPost(@NotNull String url, @NotNull String param) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(param, "param");
        PrintWriter out = (PrintWriter)null;
        BufferedReader in = (BufferedReader)null;
        String result = "";
        boolean var14 = false;

        label132: {
            try {
                var14 = true;
                URL realUrl = new URL(url);
                URLConnection conn = realUrl.openConnection();
                conn.setRequestProperty("accept", "*/*");
                conn.setRequestProperty("connection", "Keep-Alive");
                conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                Intrinsics.checkExpressionValueIsNotNull(conn, "conn");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                out = new PrintWriter(conn.getOutputStream());
                out.print(param);
                out.flush();
                in = new BufferedReader((Reader)(new InputStreamReader(conn.getInputStream())));

                while(true) {
                    String var10000 = in.readLine();
                    if (var10000 == null) {
                        var14 = false;
                        break label132;
                    }

                    String line = var10000;
                    result = result + line;
                }
            } catch (Exception var18) {
                String var7 = "发送 POST 请求出现异常！" + var18;
                boolean var8 = false;
                System.out.println(var7);
                var18.printStackTrace();
                var14 = false;
            } finally {
                if (var14) {
                    try {
                        if (out != null) {
                            out.close();
                        }

                        if (in != null) {
                            in.close();
                        }
                    } catch (IOException var15) {
                        var15.printStackTrace();
                    }

                }
            }

            try {
                if (out != null) {
                    out.close();
                }

                if (in != null) {
                    in.close();
                }

                return result;
            } catch (IOException var16) {
                var16.printStackTrace();
                return result;
            }
        }

        try {
            out.close();
            in.close();
        } catch (IOException var17) {
            var17.printStackTrace();
        }

        return result;
    }

    private HttpRequest() {
    }

    static {
        HttpRequest var0 = new HttpRequest();
        INSTANCE = var0;
    }
}
