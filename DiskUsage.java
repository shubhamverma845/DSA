import java.io.File;

public class DiskUsage {

    public static void main(String[] args) {
        System.out.println(diskUsage(new File("D:\\Softwares")));
    }

    public static long diskUsage(File root) {
        long total = root.length();
        if(root.isDirectory()) {
            for(String child : root.list()) {
                total += diskUsage(new File(root, child));
            }
        }

        System.out.println(total + "\t" + root);

        return total;
    }
}
