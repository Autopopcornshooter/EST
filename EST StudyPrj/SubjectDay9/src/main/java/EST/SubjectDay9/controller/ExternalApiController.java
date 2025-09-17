package EST.SubjectDay9.controller;

import EST.SubjectDay9.service.AlbumExternalService;
import EST.SubjectDay9.service.PhotoExternalService;
import EST.SubjectDay9.service.UserExternalService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExternalApiController {
    private final AlbumExternalService albumExternalService;
    private final UserExternalService userExternalService;
    private final PhotoExternalService photoExternalService;

    public ExternalApiController(AlbumExternalService albumExternalService,
                                 PhotoExternalService photoExternalService,
                                 UserExternalService userExternalService){
        this.photoExternalService = photoExternalService;
        this.albumExternalService = albumExternalService;
        this.userExternalService = userExternalService;
    }

    @GetMapping("/api/external/test")
    public ResponseEntity<Void> callExternals(){
        userExternalService.call();
        albumExternalService.call();
        photoExternalService.call();

        return ResponseEntity.ok().build();
    }
    @GetMapping("/api/external")
    public ResponseEntity<String> externalApiSave(){

        albumExternalService.save();
        return ResponseEntity.ok("외부 API 데이터 저장 완료");
    }

}
