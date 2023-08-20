package user.entitiy;

public class Img {
    private String fileName;
    private String newFileName;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }

    @Override
    public String toString() {
        return "Img{" +
                "fileName='" + fileName + '\'' +
                ", newFileName='" + newFileName + '\'' +
                '}';
    }
}
