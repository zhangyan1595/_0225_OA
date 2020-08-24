package com.sy.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.sy.model.Department;
import com.sy.model.Job;
import com.sy.model.base.Result;

import com.sy.service.JobService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.File;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/pdf")
public class PDFController {

    @Autowired
    JobService deptService;


    @RequestMapping("/pdf.do")
    public ResponseEntity<byte[]> PDF(int[] ids, Result result) throws Exception {
        System.out.println(ids.length+"===============");

        String filename = "C:\\Users\\Administrator.USER-20190508YF\\Desktop\\SHC\\_0225_OA\\src\\main\\webapp\\pdf\\" + UUID.randomUUID() + ".pdf";
        System.out.println(filename+"filname");
        ArrayList<Job> list = new ArrayList<>();
        for (int i :ids) {

            Job select = deptService.selectPdf(i);
            list.add(select);
        }
//        System.out.println("list:" + list);

        BaseFont bfChinese = BaseFont.createFont("C:\\Windows\\Fonts\\simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);//1 不能省略
        Font fontTitle = new Font(bfChinese, 20, Font.NORMAL);
        Font fontChinese = new Font(bfChinese, 10, Font.NORMAL);
        //创建文件
        Document document = new Document();
        //建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));

//        System.out.println(num);

        //打开文件
        document.open();
        //添加内容

//        document.add(new Paragraph("HD content here"));

        // 3列的表.
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100); // 宽度100%填充
        table.setSpacingBefore(10f); // 前间距
        table.setSpacingAfter(10f); // 后间距

        List<PdfPRow> listRow = table.getRows();
        //设置列宽
        float[] columnWidths = {1f, 2f, 3f};
        table.setWidths(columnWidths);

        Paragraph paragraph = new Paragraph("职位数据表", fontTitle);//单元格内容
        paragraph.setAlignment(Element.ALIGN_CENTER);//水平居中

//        paragraph.setAlignment(Element.ALIGN_MIDDLE);//垂直居中
        document.add(paragraph);

        for (int i = 0; i < list.size(); i++) {
            //行1
            PdfPCell cells[] = new PdfPCell[3];
            PdfPRow row = new PdfPRow(cells);

            //单元格
            cells[0] = new PdfPCell(new Paragraph("序号：" + list.get(i).getId(), fontChinese));//单元格内容
            cells[0].setBorderColor(BaseColor.BLUE);//边框验证
            cells[0].setPaddingLeft(20);//左填充20
            cells[0].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cells[0].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中

            cells[1] = new PdfPCell(new Paragraph("职位名称：" + list.get(i).getName(), fontChinese));
            cells[2] = new PdfPCell(new Paragraph("职位描述：" + list.get(i).getRemark(), fontChinese));
            listRow.add(row);
        }

        document.add(table);

        //关闭文档
        document.close();
        //关闭书写器
        writer.close();

      File file = new java.io.File(filename);
       HttpHeaders headers = new HttpHeaders();
//            headers.setContentDispositionFormData("attachment", split[split.length-1]);
        headers.add("Content-Disposition", "attachment;filename=" + URLEncoder.encode("PDF文件" + UUID.randomUUID()+ ".pdf", "utf-8"));
        HttpStatus ok = HttpStatus.OK;
        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, ok);

    }


}
