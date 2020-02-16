package finall.demo.controller;

import finall.demo.service.UplodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@RestController
public class UplodController {
    @Autowired
    UplodeService uplodeService;
    @ResponseBody
    @RequestMapping("/upload")

    public List upload(@RequestParam("file") MultipartFile file) throws Exception{
        List list=uplodeService.resolveExcel(file);
        return list;
    }
}
