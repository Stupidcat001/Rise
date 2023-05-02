/*     */ package util;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.DatatypeConverter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ImgurUtil
/*     */ {
/*     */   public static final String UPLOAD_API_URL = "https://api.imgur.com/3/image";
/*     */   public static final String ALBUM_API_URL = "https://api.imgur.com/3/album";
/*     */   public static final int MAX_UPLOAD_ATTEMPTS = 3;
/*     */   private static final String CLIENT_ID = "efce6070269a7f1";
/*     */   
/*     */   public static String upload(File file) {
/*  28 */     HttpURLConnection conn = getHttpConnection("https://api.imgur.com/3/image");
/*  29 */     writeToConnection(conn, "image=" + toBase64(file));
/*  30 */     return getResponse(conn);
/*     */   }
/*     */   
/*     */   public static String upload(byte[] file) {
/*  34 */     HttpURLConnection conn = getHttpConnection("https://api.imgur.com/3/image");
/*  35 */     writeToConnection(conn, "image=" + toBase64(file));
/*  36 */     return getResponse(conn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String createAlbum(List<String> imageIds) {
/*  47 */     HttpURLConnection conn = getHttpConnection("https://api.imgur.com/3/album");
/*  48 */     String ids = "";
/*  49 */     for (String id : imageIds) {
/*  50 */       if (!ids.equals("")) {
/*  51 */         ids = ids + ",";
/*     */       }
/*  53 */       ids = ids + id;
/*     */     } 
/*  55 */     writeToConnection(conn, "ids=" + ids);
/*  56 */     return getResponse(conn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String toBase64(File file) {
/*     */     try {
/*  67 */       byte[] b = new byte[(int)file.length()];
/*  68 */       FileInputStream fs = new FileInputStream(file);
/*  69 */       fs.read(b);
/*  70 */       fs.close();
/*  71 */       return URLEncoder.encode(DatatypeConverter.printBase64Binary(b), "UTF-8");
/*  72 */     } catch (IOException e) {
/*  73 */       e.printStackTrace();
/*     */ 
/*     */       
/*  76 */       return null;
/*     */     } 
/*     */   }
/*     */   private static String toBase64(byte[] file) {
/*     */     try {
/*  81 */       return URLEncoder.encode(DatatypeConverter.printBase64Binary(file), "UTF-8");
/*  82 */     } catch (IOException e) {
/*  83 */       e.printStackTrace();
/*     */ 
/*     */       
/*  86 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static HttpURLConnection getHttpConnection(String url) {
/*     */     try {
/*  98 */       HttpURLConnection conn = (HttpURLConnection)(new URL(url)).openConnection();
/*  99 */       conn.setDoInput(true);
/* 100 */       conn.setDoOutput(true);
/* 101 */       conn.setRequestMethod("POST");
/* 102 */       conn.setRequestProperty("Authorization", "Client-ID efce6070269a7f1");
/* 103 */       conn.setReadTimeout(100000);
/* 104 */       conn.connect();
/* 105 */       return conn;
/* 106 */     } catch (Exception ex) {
/* 107 */       throw new RuntimeException(ex.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void writeToConnection(HttpURLConnection conn, String message) {
/*     */     try {
/* 120 */       OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
/* 121 */       writer.write(message);
/* 122 */       writer.flush();
/* 123 */       writer.close();
/* 124 */     } catch (Exception ex) {
/* 125 */       throw new RuntimeException(ex.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getResponse(HttpURLConnection conn) {
/* 136 */     StringBuilder str = new StringBuilder();
/*     */     
/*     */     try {
/* 139 */       if (conn.getResponseCode() != 200) {
/* 140 */         return null;
/*     */       }
/* 142 */       BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
/*     */       String line;
/* 144 */       while ((line = reader.readLine()) != null) {
/* 145 */         str.append(line);
/*     */       }
/* 147 */       reader.close();
/* 148 */     } catch (IOException e) {
/* 149 */       return null;
/*     */     } 
/* 151 */     if (str.toString().equals("")) {
/* 152 */       return null;
/*     */     }
/* 154 */     return str.toString();
/*     */   }
/*     */ }


/* Location:              F:\QQ\1446679699\FileRecv\Rise.jar\\util\ImgurUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */