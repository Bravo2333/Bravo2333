package finall.demo.service;

import finall.demo.dao.DataMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Configuration
@Service
public class UplodeService {
    @Autowired
    DataMapper dataMapper;
    public List resolveExcel(MultipartFile file) throws Exception {
         String SUFFIX_2003 = ".xls";
         String SUFFIX_2007 = ".xlsx";
        if (file == null) {
            throw new Exception("对象不能为空");
        }
        String originalFilename = file.getOriginalFilename();
        Workbook workbook = null;
        try {
            if (originalFilename.endsWith(SUFFIX_2003)) {
                workbook = new HSSFWorkbook(file.getInputStream());
            } else if (originalFilename.endsWith(SUFFIX_2007)) {
                workbook = new XSSFWorkbook(file.getInputStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("格式错误");
        }
        if (workbook == null) {
            throw new Exception("格式错误");
        }
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            int i;
        Map m=new HashMap();
            for(i=1;i<=lastRowNum;i++){
                Row row=sheet.getRow(i);
                Cell cell=row.getCell(1);
                String s=cell.getStringCellValue();
        m.put("NUM",s);
                cell=row.getCell(2);
                s=cell.getStringCellValue();
        m.put("name",s);
        dataMapper.insert(m);
            }
            return  dataMapper.select();
    }

    }
