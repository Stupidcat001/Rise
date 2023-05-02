/*    */ package util.type;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Collection;
/*    */ import java.util.LinkedList;
/*    */ 
/*    */ public class EvictingList<T>
/*    */   extends LinkedList<T> implements Serializable {
/*    */   private int maxSize;
/*    */   
/*    */   public int getMaxSize() {
/* 12 */     return this.maxSize; } public void setMaxSize(int maxSize) {
/* 13 */     this.maxSize = maxSize;
/*    */   }
/*    */   
/*    */   public EvictingList(int maxSize) {
/* 17 */     this.maxSize = maxSize;
/*    */   }
/*    */   
/*    */   public EvictingList(Collection<? extends T> c, int maxSize) {
/* 21 */     super(c);
/* 22 */     this.maxSize = maxSize;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean add(T t) {
/* 27 */     if (size() >= getMaxSize()) removeFirst(); 
/* 28 */     return super.add(t);
/*    */   }
/*    */   
/*    */   public boolean isFull() {
/* 32 */     return (size() >= getMaxSize());
/*    */   }
/*    */ }


/* Location:              F:\QQ\1446679699\FileRecv\Rise.jar\\util\type\EvictingList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */