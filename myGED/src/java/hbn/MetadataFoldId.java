package hbn;
// Generated 7 oct. 2013 11:37:48 by Hibernate Tools 3.2.1.GA



/**
 * MetadataFoldId generated by hbm2java
 */
public class MetadataFoldId  implements java.io.Serializable {


     private int idFolder;
     private int idMetadata;

    public MetadataFoldId() {
    }

    public MetadataFoldId(int idFolder, int idMetadata) {
       this.idFolder = idFolder;
       this.idMetadata = idMetadata;
    }
   
    public int getIdFolder() {
        return this.idFolder;
    }
    
    public void setIdFolder(int idFolder) {
        this.idFolder = idFolder;
    }
    public int getIdMetadata() {
        return this.idMetadata;
    }
    
    public void setIdMetadata(int idMetadata) {
        this.idMetadata = idMetadata;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof MetadataFoldId) ) return false;
		 MetadataFoldId castOther = ( MetadataFoldId ) other; 
         
		 return (this.getIdFolder()==castOther.getIdFolder())
 && (this.getIdMetadata()==castOther.getIdMetadata());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdFolder();
         result = 37 * result + this.getIdMetadata();
         return result;
   }   


}


