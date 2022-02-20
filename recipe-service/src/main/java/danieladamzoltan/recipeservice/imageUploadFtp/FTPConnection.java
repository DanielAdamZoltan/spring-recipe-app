//package danieladamzoltan.recipeservice.imageUploadFtp;
//
//import org.apache.commons.net.ftp.FTPClient;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//
//import org.apache.commons.net.PrintCommandListener;
//import org.apache.commons.net.ftp.FTP;
//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.net.ftp.FTPFile;
//import org.apache.commons.net.ftp.FTPReply;
//import org.apache.ftpserver.FtpServer;
//import org.apache.ftpserver.FtpServerFactory;
//import org.apache.ftpserver.listener.ListenerFactory;
//import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
//import org.springframework.beans.factory.annotation.Value;
//
//public class FTPConnection {
//
//    private FTPClient ftp = null;
//
//    @Value("${ftp1.host}")
//    private String host;
//
//    @Value("${ftp1.port}")
//    private int port;
//
//    @Value("${ftp1.username}")
//    private String username;
//
//    @Value("${ftp1.password}")
//    private String password;
//
//    public FTPConnection() {}
//
//    public FTPConnection(String host, int port, String username, String password) throws Exception{
//
//        ftp = new FTPClient();
//        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
//        int reply;
//        ftp.connect(host,port);
//        System.out.println("FTP URL is:"+ftp.getDefaultPort());
//        reply = ftp.getReplyCode();
//        if (!FTPReply.isPositiveCompletion(reply)) {
//            ftp.disconnect();
//            throw new Exception("Exception in connecting to FTP Server");
//        }
//        ftp.login(username, password);
//        ftp.setFileType(FTP.LOCAL_FILE_TYPE);
//        ftp.enterLocalPassiveMode();
//    }
//    // Main method to invoke the above methods
//    public static void main(String[] args) {
//        try {
//
//            FTPFunctions objFTPFunctions = new FTPFunctions();
//            objFTPFunctions.StartServer();
//            ftpobj.disconnect();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//    }
//
//    //    public void StartServer()
////    {
////        try {
////            FtpServerFactory serverFactory = new FtpServerFactory();
////            ListenerFactory factory = new ListenerFactory();
////
////            factory.setPort(21);
////            serverFactory.addListener(“default”, factory.createListener());
////                PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
////                userManagerFactory.setFile(new File(“C:\\Test\\users.properties”));
////                serverFactory.setUserManager(userManagerFactory.createUserManager());
////// start the server
////                FtpServer server = serverFactory.createServer();
////                server.start();
////        } catch (Exception e) {
////// TODO Auto-generated catch block
////            System.out.println(“Exception occured ———————”);
////            e.printStackTrace();
////        }
////    }
//
//// Constructor to connect to the FTP Server
//    /**
//     * @param host
//     * @param port
//     * @param username
//     * @param password
//     * @throws Exception
//     */
//    public FTPFunctions(String host, int port, String username, String password) throws Exception{
//
//        ftp = new FTPClient();
//        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
//        int reply;
//        ftp.connect(host,port);
//        System.out.println(“FTP URL is:”+ftp.getDefaultPort());
//        reply = ftp.getReplyCode();
//        if (!FTPReply.isPositiveCompletion(reply)) {
//            ftp.disconnect();
//            throw new Exception(“Exception in connecting to FTP Server”);
//        }
//        ftp.login(username, password);
//        ftp.setFileType(FTP.LOCAL_FILE_TYPE);
//        ftp.enterLocalPassiveMode();
//    }
//    // Main method to invoke the above methods
//    public static void main(String[] args) {
//        try {
//
//            FTPFunctions objFTPFunctions = new FTPFunctions();
//            objFTPFunctions.StartServer();
//            ftpobj.disconnect();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//}
