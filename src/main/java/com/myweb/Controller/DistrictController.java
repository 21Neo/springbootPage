package com.myweb.Controller;
import com.myweb.domain.CustomType;
import com.myweb.domain.District;
import com.myweb.domain.Pager;
import com.myweb.service.DistrictService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class DistrictController {
    @Resource
    private DistrictService districtService;

    @RequestMapping(value = "/distrcts", method = RequestMethod.POST)
    public ResponseEntity<?> find(@RequestParam("name") String name,
                                  @RequestParam("page") Integer page,
                                  @RequestParam("size") Integer size) {
        // ---------------------- 调试数据 ----------------------
        System.out.println("关键字：" + name);
        System.out.println("当前页：" + page);
        System.out.println("每页条数：" + size);
        // ---------------------- 调试数据 ----------------------

        CustomType customType = new CustomType(400, "查无此数据！");
        Optional<Pager<District>> optionalPager =
                districtService.find(name, page, size);
        if (optionalPager.isPresent()) {
            customType.setCode(200);
            customType.setResult(optionalPager.get());
        }
        return ResponseEntity.ok(customType);
    }


    // 下载
    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public void download(
            HttpServletResponse response,
            @RequestParam(value = "name",required = false) String name){
        CustomType customType = new CustomType(200,"正确");
        System.out.println("...."+name);
        // 将数据生成Excel
        List<District> districts = districtService.findByParam(name);
        for(District district:districts){
            System.out.println("省名："+district.getDname());
        }
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFCellStyle style = wb.createCellStyle();
        HSSFSheet sheet =wb.createSheet("获取excel测试表格");
        HSSFRow row = null;

        row = sheet.createRow(0);
        row.setHeight((short)(26.25*20));
        row.createCell(0).setCellValue("用户信息列表");
        row.getCell(0).setCellStyle(getStyle(wb,style,6));//设置样式
        for(int i = 0;i <= 6;i++){
            row.createCell(i).setCellStyle(getStyle(wb,style,6));
        }
        CellRangeAddress rowRegion = new CellRangeAddress(0,0,0,6);
        sheet.addMergedRegion(rowRegion);

//        CellRangeAddress columnRegion = new CellRangeAddress(1,7,0,0);
//        sheet.addMergedRegion(columnRegion);

        row = sheet.createRow(1);
//        row.createCell(0).setCellStyle(getStyle(wb,style,3));
        row.setHeight((short)(22.50*20));
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("街道");
        row.createCell(2).setCellValue("市");
        row.createCell(3).setCellValue("省");
        row.createCell(4).setCellValue("邮编");
        row.createCell(5).setCellValue("地区号");
        row.createCell(6).setCellValue("orderId");
        for(int i = 0;i <= 6;i++){
            row.getCell(i).setCellStyle(getStyle(wb,style,0));
        }

        for(int i = 0;i<districts.size();i++){
            row = sheet.createRow(i+2);
            District district = districts.get(i);
            row.createCell(0).setCellValue(district.getDid());
            row.createCell(1).setCellValue(district.getDname());
            row.createCell(2).setCellValue(district.getCity().getCname());
            row.createCell(3).setCellValue(district.getCity().getProvince().getPname());
            row.createCell(4).setCellValue(district.getPostcode());
            row.createCell(5).setCellValue(district.getCity().getAreacode());
            row.createCell(6).setCellValue(district.getCity().getProvince().getOrderid());
//            HSSFCellStyle style = wb.createCellStyle();
            for(int j = 0;j <= 6;j++){
                row.getCell(j).setCellStyle(getStyle(wb,style,6));
            }
        }

        //默认行高
        sheet.setDefaultRowHeight((short)(16.5*20));
        //列宽自适应
        for(int i=0;i<=13;i++){
            sheet.autoSizeColumn(i);
        }

        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*return new ResponseEntity<>(customType,HttpStatus.OK);*/
    }

    /**
     * 获取样式
     * @param hssfWorkbook
     * @param styleNum
     * @return
     */
    public HSSFCellStyle getStyle(HSSFWorkbook hssfWorkbook,HSSFCellStyle style, Integer styleNum){
//        HSSFCellStyle style = hssfWorkbook.createCellStyle();
        style.setBorderRight(BorderStyle.THIN);//右边框
        style.setBorderBottom(BorderStyle.THIN);//下边框

        HSSFFont font = hssfWorkbook.createFont();
        font.setFontName("宋体");//设置字体为微软雅黑

//        HSSFPalette palette = hssfWorkbook.getCustomPalette();//拿到palette颜色板,可以根据需要设置颜色
        switch (styleNum){
            case(0):{
                style.setAlignment(HorizontalAlignment.CENTER_SELECTION);//跨列居中
                font.setBold(false);//粗体
                font.setFontHeightInPoints((short) 14);//字体大小
                style.setFont(font);
//                palette.setColorAtIndex(HSSFColor.BLUE.index,(byte)184,(byte)204,(byte)228);//替换颜色板中的颜色
//                style.setFillForegroundColor(HSSFColor.BLUE.index);
//                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            }
            break;
            case(1):{
                font.setBold(true);//粗体
                font.setFontHeightInPoints((short) 11);//字体大小
                style.setFont(font);
            }
            break;
            case(2):{
                font.setFontHeightInPoints((short)10);
                style.setFont(font);
            }
            break;
            case(3):{
                style.setFont(font);

//                palette.setColorAtIndex(HSSFColor.GREEN.index,(byte)0,(byte)32,(byte)96);//替换颜色板中的颜色
//                style.setFillForegroundColor(HSSFColor.GREEN.index);
//                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            }
            break;
        }

        return style;
    }

}
