package com.sy.controller;


import com.sy.model.Department;
import com.sy.model.Job;
import com.sy.service.DeptService;
import com.sy.service.DeptServiceImpl;
import com.sy.service.JobService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping("/uploadExcel/*")
public class UploadExcelControl {
    @Autowired
    JobService jobService;

    //导出
    @RequestMapping("/exportexcel.do")
    public void exportexcel(HttpServletResponse response) {
        OutputStream oStream = null;
        try {
//       创建工作簿
            HSSFWorkbook wb = new HSSFWorkbook();
//       创建sheet
            HSSFSheet sheet = wb.createSheet("列表");
//       创建表头
            HSSFRow row = sheet.createRow(0);
            //创建单元格
            HSSFCell cell = row.createCell(0);
            cell.setCellValue("序号");
            HSSFCell cell1 = row.createCell(1);
            cell1.setCellValue("职位名称");
            HSSFCell cell2 = row.createCell(2);
            cell2.setCellValue("详细信息");

            List<Job> list = jobService.selectAll();
            for (int i = 0; i < list.size(); i++) {
                Job us = list.get(i);
                //创建表头
                HSSFRow lrow = sheet.createRow(i + 1);
                //创建单元格
                HSSFCell lcell = lrow.createCell(0);
                lcell.setCellValue(us.getId());
                HSSFCell lcell1 = lrow.createCell(1);
                lcell1.setCellValue(us.getName());
                HSSFCell lcell2 = lrow.createCell(2);
                lcell2.setCellValue(us.getRemark());

            }
            //根据response获取输出流
            response.setContentType("application/force-download"); // 设置下载类型
            response.setHeader("Content-Disposition", "attachment;filename=job.xls"); // 设置文件的名称
            oStream = response.getOutputStream(); // 输出流
            //把工作薄写入到输出流
            wb.write(oStream);

        } catch (Exception e) {
            // TODO: handle exception
            try {
                oStream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }


        }
    }
}