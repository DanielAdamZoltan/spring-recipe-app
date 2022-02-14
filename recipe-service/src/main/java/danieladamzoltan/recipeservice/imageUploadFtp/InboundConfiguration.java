//package danieladamzoltan.recipeservice.imageUploadFtp;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.integration.dsl.IntegrationFlow;
//import org.springframework.integration.dsl.IntegrationFlows;
//import org.springframework.integration.ftp.dsl.Ftp;
//import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
//
//import java.io.File;
//import java.util.concurrent.TimeUnit;
//
//@Configuration
//@Profile("inbound")
//public class InboundConfiguration {
//    @Bean
//    DefaultFtpSessionFactory defaultFtpSessionFactory(
//            @Value("${ftp1.username}") String username,
//            @Value("${ftp1.password}") String pw,
//            @Value("${ftp1.host}") String host,
//            @Value("${ftp1.port}") int port) {
//        DefaultFtpSessionFactory defaultFtpSessionFactory = new DefaultFtpSessionFactory();
//        defaultFtpSessionFactory.setPassword(pw);
//        defaultFtpSessionFactory.setUsername(username);
//        defaultFtpSessionFactory.setHost(host);
//        defaultFtpSessionFactory.setPort(port);
//        return defaultFtpSessionFactory;
//    }
//
//    @Bean
//    IntegrationFlow inbound(DefaultFtpSessionFactory ftpSf) {
//        var localDirectory = new File(new File(System.getProperty("user.home"), "Desktop"), "local");
//        var spec = Ftp
//                .inboundAdapter(ftpSf)
//                .autoCreateLocalDirectory(true)
//                .patternFilter("*.txt")
//                .localDirectory(localDirectory);
//        return IntegrationFlows
//                .from(spec, pc -> pc.poller(pm -> pm.fixedRate(1000, TimeUnit.MILLISECONDS)))
//                .handle((file, messageHeaders) -> {
//                    System.out.println("new file: " + file + ".");
//                    messageHeaders.forEach((k, v) -> System.out.println(k + ':' + v));
//
//                    return null;
//                })
//                .get();
//    }
//}
