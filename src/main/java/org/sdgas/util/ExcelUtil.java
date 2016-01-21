package org.sdgas.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.sdgas.model.Contract;
import org.sdgas.service.ContractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC;


/**
 * 该类实现了将一组对象转换为Excel表格，并且可以从Excel表格中读取到一组List对象中
 * 该类利用了BeanUtils框架中的反射完成
 * 使用该类的前提，在相应的实体对象上通过ExcelReources来完成相应的注解
 *
 * @author Administrator
 */

@Service
@Transactional
public class ExcelUtil {

    private static ExcelUtil eu = new ExcelUtil();
    ChangeTime changeTime = new ChangeTime();
    private final static Logger logger = Logger.getLogger(ExcelUtil.class);

    private ContractService contractService;

    /**
     * 根据标题获取相应的方法名称
     *
     * @param eh
     * @return
     */
    private String getMethodName(ExcelHeader eh) {
        String mn = eh.getMethodName().substring(3);
        mn = mn.substring(0, 1).toLowerCase() + mn.substring(1);
        return mn;
    }

    private Workbook handleExcel(List objs, Class clz, boolean isXssf, String message) {
        XSSFWorkbook wb = null;
        try {
            if (isXssf) {
                XSSFWorkbook w = new XSSFWorkbook();
            } else {
                HSSFWorkbook w = new HSSFWorkbook();
            }
            wb = new XSSFWorkbook();
            XSSFDataFormat format = wb.createDataFormat();
            XSSFSheet sheet = wb.createSheet(message + "备份记录");   //取excel工作表对象
            XSSFCellStyle cellStyle = wb.createCellStyle();         //设置excel单元格样式
            XSSFCellStyle passwordCellStyle = wb.createCellStyle();   //设置密码单元格样式
            XSSFDataFormat passwordFormat = wb.createDataFormat();
            passwordCellStyle.setDataFormat(passwordFormat.getFormat(";;;"));
            List<ExcelHeader> headers = getHeaderList(clz);
            Collections.sort(headers);
            sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) (headers.size() - 1)));
            Row r0 = sheet.createRow(0);
            Cell cell = r0.createCell(0);
            r0.setHeightInPoints(28);
            cell.setCellValue(message + "备份记录");
            Row r = sheet.createRow(1);
            r.setHeightInPoints(25);
            cell.setCellStyle(cellStyle);
            //输出标题
            for (int i = 0; i < headers.size(); i++) {
                Cell cell1 = r.createCell(i);
                if (headers.get(i).getTitle().equals("密码"))
                    cell1.setCellStyle(passwordCellStyle);
                else
                    cell1.setCellStyle(cellStyle);
                cell1.setCellValue(headers.get(i).getTitle());
            }
            Object obj = null;
            //输出用户资料信息
            if (message.indexOf("用户资料 ") > 0) {
                sheet.setColumnWidth(3, 32 * 150);
                sheet.setColumnWidth(4, 32 * 110);
                sheet.setColumnWidth(7, 32 * 120);
                for (int i = 0; i < objs.size(); i++) {
                    r = sheet.createRow(i + 2);
                    obj = objs.get(i);
                    for (int j = 0; j < headers.size(); j++) {
                        Cell cell2 = r.createCell(j);
                        copyDefaultCellStyle(null, cell2, cellStyle, 0);
                        if (getMethodName(headers.get(j)).equals("nabled"))
                            cell2.setCellValue(BeanUtils.getProperty(obj, "enabled"));
                        else if (getMethodName(headers.get(j)).equals("password")) {
                            cell2.setCellStyle(passwordCellStyle);
                            cell2.setCellValue(BeanUtils.getProperty(obj, getMethodName(headers.get(j))));
                        } else
                            cell2.setCellValue(BeanUtils.getProperty(obj, getMethodName(headers.get(j))));
                    }
                }
            }
            //输出房间使用信息数据
            else {
                sheet.setColumnWidth(0, 32 * 80);
                sheet.setColumnWidth(2, 32 * 100);
                sheet.setColumnWidth(3, 32 * 190);
                sheet.setColumnWidth(4, 32 * 190);
                sheet.setColumnWidth(5, 32 * 190);
                sheet.setColumnWidth(10, 32 * 130);
                for (int i = 0; i < objs.size(); i++) {
                    r = sheet.createRow(i + 2);
                    obj = objs.get(i);
                    for (int j = 0; j < headers.size(); j++) {
                        Cell cell2 = r.createCell(j);
                        if (j == 3 || j == 4 || j == 5) {
                            XSSFCellStyle cs3 = wb.createCellStyle();
                            cell2.setCellValue(new Date());
                            copyDefaultCellStyle(format, cell2, cs3, 1);
                        }
                        if (j == 10) {
                            XSSFCellStyle cs2 = wb.createCellStyle();
                            copyDefaultCellStyle(format, cell2, cs2, 2);
                        }
                        copyDefaultCellStyle(null, cell2, cellStyle, 0);
                        cell2.setCellValue(BeanUtils.getProperty(obj, getMethodName(headers.get(j))));
                    }
                }
            }
            //设置行列的默认宽度和高度
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            logger.error(e);
        }
        return wb;
    }

    /**
     * 导出对象到Excel，直接新建一个Excel完成导出，基于路径的导出，不基于模板
     *
     * @param outPath 导出路径
     * @param objs    对象列表
     * @param clz     对象类型
     * @param isXssf  是否是2007版本
     */
    public void exportExcelByPath(String outPath, List objs, Class clz, boolean isXssf, String message) {
        Workbook wb = handleExcel(objs, clz, isXssf, message);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outPath);
            wb.toString().getBytes("GB2312");
            wb.write(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e);
        } finally {
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e);
            }
        }
    }

    /**
     * 导出对象到Excel，不是基于模板的，直接新建一个Excel完成导出，基于流
     *
     * @param os     输出流
     * @param objs   对象列表
     * @param clz    对象类型
     * @param isXssf 是否是2007版本
     */
    public void exportExcelByPath(OutputStream os, List objs, Class clz, boolean isXssf, String message) {
        try {
            Workbook wb = handleExcel(objs, clz, isXssf, message);
            wb.write(os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

    /**
     * 从文件路径读取相应的Excel文件到对象列表
     *
     * @param path     文件路径下的path
     * @param clz      对象类型
     * @param readLine 开始行，注意是标题所在行
     * @param tailLine 底部有多少行，在读入对象时，会减去这些行
     * @return
     */
    public List<Object> readExcelByPath(String path, Class clz, int readLine, int tailLine) {
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(new File(path));
            return readExcel(wb, clz, readLine, tailLine);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e);
        }
        return null;
    }

    /**
     * 从文件路径读取相应的Excel文件到对象列表，标题行为0，没有尾行
     *
     * @param path 路径
     * @param clz  类型
     * @return 对象列表
     */
    public List<Object> readExcelByPath(String path, Class clz) {
        return this.readExcelByPath(path, clz, 0, 0);
    }

    /**
     * 取对应单元格类型的值
     *
     * @param c 列数
     * @return 单元格的值
     */
    private String getCellValue(Cell c) {
        String o = null;
        switch (c.getCellType()) {
            case Cell.CELL_TYPE_BLANK:
                o = "";
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                o = String.valueOf(c.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA:
                o = String.valueOf(c.getCellFormula());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                o = String.valueOf(c.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING:
                o = c.getStringCellValue();
                break;
            default:
                o = null;
                break;
        }
        return o;
    }

    public List<Object> readExcel(Workbook wb, Class clz, int readLine, int tailLine) {
        Sheet sheet = wb.getSheetAt(0);     //取第一张表
        List<Object> objs = null;
        try {
            Row row = sheet.getRow(readLine);  //开始行，主题栏
            objs = new ArrayList<Object>();
            Map<Integer, String> maps = getHeaderMap(row, clz);   //设定对应的字段顺序与方法名
            if (maps == null || maps.size() <= 0) throw new RuntimeException("要读取的Excel的格式不正确，检查是否设定了合适的行");//与order顺序不符
            for (int i = readLine + 1; i <= sheet.getLastRowNum() - tailLine; i++) {     //取数据
                row = sheet.getRow(i);
                Object obj = clz.newInstance();        //   调用无参结构
                for (Cell c : row) {
                    int ci = c.getColumnIndex();
                    String mn = maps.get(ci).substring(3);  //消除get
                    mn = mn.substring(0, 1).toLowerCase() + mn.substring(1);
                    Map<String, Object> params = new HashMap<String, Object>();
                    BeanUtils.copyProperty(obj, mn, this.getCellValue(c));
                }
                objs.add(obj);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error(e);
        }
        return objs;
    }

    private List<ExcelHeader> getHeaderList(Class clz) {
        List<ExcelHeader> headers = new ArrayList<ExcelHeader>();
        //获取全部get/is方法
        Method[] ms = clz.getDeclaredMethods();
        for (Method m : ms) {
            String mn = m.getName();
            if (mn.startsWith("get") || mn.startsWith("is")) {
                if (m.isAnnotationPresent(ExcelResources.class)) {
                    ExcelResources er = m.getAnnotation(ExcelResources.class);
                    headers.add(new ExcelHeader(er.title(), er.order(), mn));
                }
            }
        }
        return headers;
    }

    /**
     * 判断excel标题与对象的标签标题是否一致
     *
     * @param titleRow 开始行
     * @param clz      对象
     * @return 判断类型
     */

    private Map<Integer, String> getHeaderMap(Row titleRow, Class clz) {
        List<ExcelHeader> headers = getHeaderList(clz);    //取后台标题
        Map<Integer, String> maps = new HashMap<Integer, String>();
        for (Cell c : titleRow) {
            String title = c.getStringCellValue();        //取excel的标题栏
            for (ExcelHeader eh : headers) {
                if (eh.getTitle().equals(title.trim())) {      //相等则设定对应的字段顺序与方法名
                    maps.put(c.getColumnIndex(), eh.getMethodName().replace("get", "set"));
                    break;
                }
            }
        }
        return maps;
    }

    private void copyDefaultCellStyle(XSSFDataFormat format, Cell cell, XSSFCellStyle cs, int i) {
        cs.setAlignment(XSSFCellStyle.ALIGN_CENTER_SELECTION);
        cs.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        cs.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);
        cs.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
        cs.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);
        cs.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
        if (i == 1)
            cs.setDataFormat(format.getFormat("yyyy-MM-dd HH:mm:s"));
        if (i == 2)
            cs.setDataFormat(format.getFormat("0"));
        cell.setCellStyle(cs);
    }

    public ExcelUtil() {
    }

    public static ExcelUtil getInstance() {
        return eu;
    }

    //生成归档合同明细excel
    public void createExcel(String date, String outPath) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("归档合同明细");   //取excel工作表对象

        //设置默认列宽
        sheet.setDefaultColumnWidth(14);
        //合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) 9));
        Row r0 = sheet.createRow(0);
        Cell cell = r0.createCell(0);
        r0.setHeightInPoints(28);

        cell.setCellValue(date + "归档合同明细");
        XSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 16); // 字体高度
        font.setFontName("宋体"); // 字体
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 宽度

        XSSFCellStyle cellStyle = wb.createCellStyle(); //设置excel单元格样式
        cellStyle.setFont(font);
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中
        cell.setCellStyle(cellStyle);

        //设置单元格样式
        font = wb.createFont();
        font.setFontHeightInPoints((short) 12); // 字体高度
        font.setFontName("宋体"); // 字体
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL); // 宽度
        cellStyle = wb.createCellStyle(); //设置excel单元格样式
        cellStyle.setFont(font);
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        Row r = sheet.createRow(1);
        cell = r.createCell(0);
        cell.setCellValue("合同名称");
        cell.setCellStyle(cellStyle);

        cell = r.createCell(1);
        cell.setCellValue("合同编号");
        cell.setCellStyle(cellStyle);

        cell = r.createCell(2);
        cell.setCellValue("签约对象");
        cell.setCellStyle(cellStyle);

        cell = r.createCell(3);
        cell.setCellValue("合同签订金额");
        cell.setCellStyle(cellStyle);

        cell = r.createCell(6);
        cell.setCellValue("经办人");
        cell.setCellStyle(cellStyle);

        cell = r.createCell(7);
        cell.setCellValue("合同原件份数");
        cell.setCellStyle(cellStyle);

        cell = r.createCell(8);
        cell.setCellValue("经办部门");
        cell.setCellStyle(cellStyle);

        cell = r.createCell(4);
        cell.setCellValue("合同生效日期");
        cell.setCellStyle(cellStyle);

        cell = r.createCell(5);
        cell.setCellValue("合同到期日期");
        cell.setCellStyle(cellStyle);

        cell = r.createCell(9);
        cell.setCellValue("归档月份");
        cell.setCellStyle(cellStyle);

        List<Contract> contracts = contractService.findContractByCloseDate(date);

        CellStyle cs = wb.createCellStyle();
        cs.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中
        cs.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cs.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cs.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cs.setAlignment(XSSFCellStyle.ALIGN_CENTER);

        int count = 2;
        for (Contract c : contracts) {
            r = sheet.createRow(count);

            try {
                cell = r.createCell(0);
                cell.setCellValue(c.getContractName().getContractName());
                cell.setCellStyle(cs);

                cell = r.createCell(1);
                cell.setCellValue(c.getContractId());
                cell.setCellStyle(cs);

                cell = r.createCell(2);
                cell.setCellValue(c.getContractSignCompany());
                cell.setCellStyle(cs);

                cell = r.createCell(3);
                cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                cell.setCellValue(c.getContractMoney());
                cell.setCellStyle(cs);

                cell = r.createCell(6);
                cell.setCellValue(c.getContractOperator());
                cell.setCellStyle(cs);

                cell = r.createCell(7);
                cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                cell.setCellValue(c.getCount());
                cell.setCellStyle(cs);

                cell = r.createCell(8);
                cell.setCellValue(c.getDepartment().getDepartmentName());
                cell.setCellStyle(cs);

                cell = r.createCell(4);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(" " + c.getContractBeginDate());
                cell.setCellStyle(cs);

                cell = r.createCell(5);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(" " + c.getContractEndDate());
                cell.setCellStyle(cs);

                cell = r.createCell(9);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(" " + c.getContractCloseDate());
                cell.setCellStyle(cs);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("导出数据异常，请与管理员联系");
            }

            count++;

        }
        //创建excel，并保存
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outPath);
            wb.toString().getBytes("GB2312");
            wb.write(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e);
        } finally {
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e);
            }
        }
    }

    //生成未归档合同明细excel
    public void createExcelV2(String outPath) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("未归档合同明细");   //取excel工作表对象

        //设置默认列宽
        sheet.setDefaultColumnWidth(14);
        //合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) 10));
        Row r0 = sheet.createRow(0);
        Cell cell = r0.createCell(0);
        r0.setHeightInPoints(28);

        Calendar cal = Calendar.getInstance();//使用日历类
        int year = cal.get(Calendar.YEAR);//得到年
        int month = cal.get(Calendar.MONTH) + 1;//得到月，从0开始的

        cell.setCellValue(year + "年" + month + "月" + "未归档合同明细");
        XSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 16); // 字体高度
        font.setFontName("宋体"); // 字体
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 宽度

        XSSFCellStyle cellStyle = wb.createCellStyle(); //设置excel单元格样式
        cellStyle.setFont(font);
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中
        cell.setCellStyle(cellStyle);

        //设置单元格样式
        font = wb.createFont();
        font.setFontHeightInPoints((short) 12); // 字体高度
        font.setFontName("宋体"); // 字体
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL); // 宽度
        cellStyle = wb.createCellStyle(); //设置excel单元格样式
        cellStyle.setFont(font);
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        Row r = sheet.createRow(1);
        cell = r.createCell(0);
        cell.setCellValue("合同名称");
        cell.setCellStyle(cellStyle);

        cell = r.createCell(1);
        cell.setCellValue("合同编号");
        cell.setCellStyle(cellStyle);

        cell = r.createCell(2);
        cell.setCellValue("签约对象");
        cell.setCellStyle(cellStyle);

        cell = r.createCell(3);
        cell.setCellValue("合同签订金额");
        cell.setCellStyle(cellStyle);

        cell = r.createCell(6);
        cell.setCellValue("经办人");
        cell.setCellStyle(cellStyle);

        cell = r.createCell(7);
        cell.setCellValue("合同原件份数");
        cell.setCellStyle(cellStyle);

        cell = r.createCell(8);
        cell.setCellValue("经办部门");
        cell.setCellStyle(cellStyle);

        cell = r.createCell(4);
        cell.setCellValue("合同生效日期");
        cell.setCellStyle(cellStyle);

        cell = r.createCell(5);
        cell.setCellValue("合同到期日期");
        cell.setCellStyle(cellStyle);

        cell = r.createCell(9);
        cell.setCellValue("申请日期");
        cell.setCellStyle(cellStyle);

        cell = r.createCell(10);
        cell.setCellValue("应归档日期");
        cell.setCellStyle(cellStyle);

        List<Contract> contracts = contractService.findContractByNOTClose();

        CellStyle cs = wb.createCellStyle();
        cs.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中
        cs.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cs.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cs.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cs.setAlignment(XSSFCellStyle.ALIGN_CENTER);

        int count = 2;
        for (Contract c : contracts) {
            r = sheet.createRow(count);

            try {
                cell = r.createCell(0);
                cell.setCellValue(c.getContractName().getContractName());
                cell.setCellStyle(cs);

                cell = r.createCell(1);
                cell.setCellValue(c.getContractId());
                cell.setCellStyle(cs);

                cell = r.createCell(2);
                cell.setCellValue(c.getContractSignCompany());
                cell.setCellStyle(cs);

                cell = r.createCell(3);
                cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                cell.setCellValue(c.getContractMoney());
                cell.setCellStyle(cs);

                cell = r.createCell(6);
                cell.setCellValue(c.getContractOperator());
                cell.setCellStyle(cs);

                cell = r.createCell(7);
                cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                cell.setCellValue(c.getCount());
                cell.setCellStyle(cs);

                cell = r.createCell(8);
                cell.setCellValue(c.getDepartment().getDepartmentName());
                cell.setCellStyle(cs);

                cell = r.createCell(4);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(" " + c.getContractBeginDate());
                cell.setCellStyle(cs);

                cell = r.createCell(5);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(" " + c.getContractEndDate());
                cell.setCellStyle(cs);

                cell = r.createCell(9);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(" " + c.getSignContractDate());
                cell.setCellStyle(cs);

                cell = r.createCell(10);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
                cell.setCellValue(" " + df.format(new Date(c.getContractCloseDate().getTime() + 30 * 24 * 60 * 60 * 1000)));
                cell.setCellStyle(cs);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("导出数据异常，请与管理员联系");
            }

            count++;

        }
        //创建excel，并保存
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outPath);
            wb.toString().getBytes("GB2312");
            wb.write(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e);
        } finally {
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e);
            }
        }
    }

    @Resource(name = "contractServiceImpl")
    public void setContractService(ContractService contractService) {
        this.contractService = contractService;
    }
}