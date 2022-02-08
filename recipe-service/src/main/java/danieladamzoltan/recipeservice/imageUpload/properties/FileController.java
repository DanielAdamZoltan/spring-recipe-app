package danieladamzoltan.recipeservice.imageUpload.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//@RestController
//@RequestMapping("/file")
//public class FileController {
//
//    private FileStorageService fileStorageService;
//
//    @Autowired
//    public FileController(FileStorageService fileStorageService) {
//        this.fileStorageService = fileStorageService;
//    }
//
//    @PostMapping("/uploadFile")
//    public UploadFileResponse uploadFile(@RequestParam("file")MultipartFile file){
//        String fileName = fileStorageService.storeFile(file);
//
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("C:\\Users\\kinge\\Desktop\\images-save-folder")
//                .path(fileName)
//                .toUriString();
//
//        return new UploadFileResponse(fileName, fileDownloadUri,file.getContentType(), file.getSize());
//    }



//}
