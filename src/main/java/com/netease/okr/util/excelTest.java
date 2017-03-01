package com.netease.okr.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

/**
 * POI操作Excel
 * 
 * @since: 1.0
 * @version:1.0
 * @createTime:2011年07月21日 10：47：20
 * @updateTime:2011年07月21日 10：47：20
 * @author niejy niejingyu@gmail.com
 * 
 */
public class excelTest {
 
    /**
     * 根据源Sheet样式复制新Sheet
     * 
     * @param fromsheetname
     * @param newsheetname
     * @param targetFile
     */
    public void copySheet(String fromsheetname, String newsheetname,
            String targetFile) {
        Workbook wb = null;
        try {
            FileInputStream fis = new FileInputStream(targetFile);
            wb = WorkbookFactory.create(fis);
            Sheet fromsheet = wb.getSheet(fromsheetname);
            if (fromsheet != null && wb.getSheet(newsheetname) == null) {
                Sheet newsheet = wb.createSheet(newsheetname);
                // 设置打印参数
                newsheet.setMargin(HSSFSheet.TopMargin,
                        fromsheet.getMargin(HSSFSheet.TopMargin));// 页边距（上）
                newsheet.setMargin(HSSFSheet.BottomMargin,
                        fromsheet.getMargin(HSSFSheet.BottomMargin));// 页边距（下）
                newsheet.setMargin(HSSFSheet.LeftMargin,
                        fromsheet.getMargin(HSSFSheet.LeftMargin));// 页边距（左）
                newsheet.setMargin(HSSFSheet.RightMargin,
                        fromsheet.getMargin(HSSFSheet.RightMargin));// 页边距（右
                newsheet.setMargin(Sheet.HeaderMargin, fromsheet.getMargin(Sheet.HeaderMargin));//页眉
                newsheet.setMargin(Sheet.FooterMargin, fromsheet.getMargin(Sheet.FooterMargin));//页脚
                 
                 
                PrintSetup ps = newsheet.getPrintSetup();
                ps.setLandscape(false); // 打印方向，true：横向，false：纵向(默认)
                ps.setVResolution((short) 600);
                ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); // 纸张类型
 
                File file = new File(targetFile);
                if (file.exists() && (file.renameTo(file))) {
                    copyRows(wb, fromsheet, newsheet,
                            fromsheet.getFirstRowNum(),
                            fromsheet.getLastRowNum());
                    FileOutputStream fileOut = new FileOutputStream(targetFile);
                    wb.write(fileOut);
                    fileOut.flush();
                    fileOut.close();
                    System.out.println("done");
                } else {
                    System.out.println("文件不存在或者正在使用,请确认...");
                }
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    /**
     * 复制srcSheet的页面设置到dstSheet
     * @param srcSheet
     * @param dstSheet
     * @author zhrb
     */
    public static void copyPrintSetup(Sheet srcSheet, Sheet dstSheet){
        if(srcSheet == null || dstSheet==null)
            throw new IllegalArgumentException("srcSheet or dstSheet must not be null!");
        // 设置打印参数
        dstSheet.setMargin(Sheet.TopMargin,
                srcSheet.getMargin(Sheet.TopMargin));// 页边距（上）
        dstSheet.setMargin(Sheet.BottomMargin,
                srcSheet.getMargin(Sheet.BottomMargin));// 页边距（下）
        dstSheet.setMargin(Sheet.LeftMargin,
                srcSheet.getMargin(Sheet.LeftMargin));// 页边距（左）
        dstSheet.setMargin(Sheet.RightMargin,
                srcSheet.getMargin(Sheet.RightMargin));// 页边距（右
        dstSheet.setMargin(Sheet.HeaderMargin, srcSheet.getMargin(Sheet.HeaderMargin));//页眉
        dstSheet.setMargin(Sheet.FooterMargin, srcSheet.getMargin(Sheet.FooterMargin));//页脚
         
        PrintSetup srcPrintSetup = srcSheet.getPrintSetup();
        PrintSetup ps = dstSheet.getPrintSetup();
        ps.setLandscape(srcPrintSetup.getLandscape()); // 打印方向，true：横向，false：纵向(默认)
        ps.setVResolution(srcPrintSetup.getVResolution());
        ps.setPaperSize(srcPrintSetup.getPaperSize()); // 纸张类型
         
    }
 
    /**
     * 
     * @param sheetCreat
     *            ,目标Sheet
     * @param sheet
     *            ,源Sheet
     */
    private void MergerRegion(Sheet sheetCreat, Sheet sheet) {
 
        int sheetMergerCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergerCount; i++) {
            CellRangeAddress mergedRegionAt = sheet.getMergedRegion(i);
            sheetCreat.addMergedRegion(mergedRegionAt);
        }
    }
 
    /**
     * 拷贝整张fromsheet
     * 
     * @param wb
     * @param fromsheet
     * @param newsheet
     * @param firstrow
     * @param lastrow
     */
    public void copyRows(Workbook wb, Sheet fromsheet, Sheet newsheet,
            int firstrow, int lastrow) {
        if ((firstrow == -1) || (lastrow == -1) || lastrow < firstrow) {
            return;
        }
 
        MergerRegion(newsheet, fromsheet);
 
        Row fromRow = null;
        Row newRow = null;
        Cell newCell = null;
        Cell fromCell = null;
        // 设置列宽
        for (int i = firstrow; i <= lastrow; i++) {
            fromRow = fromsheet.getRow(i);
            if (fromRow != null) {
                for (int j = fromRow.getLastCellNum(); j >= fromRow
                        .getFirstCellNum(); j--) {
                    int colnum = fromsheet.getColumnWidth((short) j);
                    if (colnum > 100) {
                        newsheet.setColumnWidth((short) j, (short) colnum);
                    }
                    if (colnum == 0) {
                        newsheet.setColumnHidden((short) j, true);
                    } else {
                        newsheet.setColumnHidden((short) j, false);
                    }
                }
                break;
            }
        }
        // 拷贝行并填充数据
        for (int i = 0; i <= lastrow; i++) {
            fromRow = fromsheet.getRow(i);
            if (fromRow == null) {
                continue;
            }
            newRow = newsheet.createRow(i - firstrow);
            newRow.setHeight(fromRow.getHeight());
            for (int j = fromRow.getFirstCellNum(); j < fromRow
                    .getPhysicalNumberOfCells(); j++) {
                fromCell = fromRow.getCell((short) j);
                if (fromCell == null) {
                    continue;
                }
                newCell = newRow.createCell((short) j);
                newCell.setCellStyle(fromCell.getCellStyle());
                int cType = fromCell.getCellType();
                newCell.setCellType(cType);
                switch (cType) {
                case HSSFCell.CELL_TYPE_STRING:
                    newCell.setCellValue(fromCell.getRichStringCellValue());
                    break;
                case HSSFCell.CELL_TYPE_NUMERIC:
                    newCell.setCellValue(fromCell.getNumericCellValue());
                    break;
                case HSSFCell.CELL_TYPE_FORMULA:
                    newCell.setCellFormula(fromCell.getCellFormula());
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN:
                    newCell.setCellValue(fromCell.getBooleanCellValue());
                    break;
                case HSSFCell.CELL_TYPE_ERROR:
                    newCell.setCellValue(fromCell.getErrorCellValue());
                    break;
                default:
                    newCell.setCellValue(fromCell.getRichStringCellValue());
                    break;
                }
            }
        }
    }
 
    /**
     * 拷贝某一行
     * @author someone
     * @param wb
     * @param fromsheet
     *            ,模板sheet
     * @param newsheet
     *            ,目标sheet
     * @param sourceRowNum
     *            ,模板sheet上要拷贝的行
     * @param destinationRowNum
     *            ,目标sheet上的目标行
     */
    public void copyRow(Workbook wb, Sheet fromsheet, Sheet newsheet,
            int sourceRowNum, int destinationRowNum) {
        if (sourceRowNum == -1) {
            return;
        }
         
        // Get the source / new row
 
        Row newRow = newsheet.createRow(destinationRowNum);
        Row sourceRow = fromsheet.getRow(sourceRowNum);
        newRow.setHeight(sourceRow.getHeight());
        for (int i = 0; i < sourceRow.getLastCellNum(); i++) {
 
            // Grab a copy of the old/new cell
            Cell oldCell = sourceRow.getCell(i);
            Cell newCell = newRow.createCell(i);
 
            // If the old cell is null jump to next cell
            if (oldCell == null) {
                newCell = null;
                continue;
            }
 
            // Copy style from old cell and apply to new cell
            CellStyle newCellStyle = wb.createCellStyle();
            newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
            newCell.setCellStyle(newCellStyle);
 
            // If there is a cell comment, copy
            if (oldCell.getCellComment() != null) {
                newCell.setCellComment(oldCell.getCellComment());
            }
 
            // If there is a cell hyperlink, copy
            if (oldCell.getHyperlink() != null) {
                newCell.setHyperlink(oldCell.getHyperlink());
            }
 
            // Set the cell data type
            newCell.setCellType(oldCell.getCellType());
 
            // Set the cell data value
            switch (oldCell.getCellType()) {
            case Cell.CELL_TYPE_BLANK:
                newCell.setCellValue(oldCell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                newCell.setCellValue(oldCell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_ERROR:
                newCell.setCellErrorValue(oldCell.getErrorCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA:
                newCell.setCellFormula(oldCell.getCellFormula());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                newCell.setCellValue(oldCell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING:
                newCell.setCellValue(oldCell.getRichStringCellValue());
                break;
            }
 
        }
 
        // 拷贝合并的单元格
        // If there are are any merged regions in the source row, copy
        // to new row
        for (int k = 0; k < fromsheet.getNumMergedRegions(); k++) {
            CellRangeAddress cellRangeAddress = fromsheet.getMergedRegion(k);
            if (cellRangeAddress.getFirstRow() == sourceRow.getRowNum()) {
                CellRangeAddress newCellRangeAddress = new CellRangeAddress(
                        newRow.getRowNum(),
                        (newRow.getRowNum() + (cellRangeAddress.getLastRow() - cellRangeAddress
                                .getFirstRow())), cellRangeAddress
                                .getFirstColumn(), cellRangeAddress
                                .getLastColumn());
                newsheet.addMergedRegion(newCellRangeAddress);
            }
        }
 
    }
 
    /**
     * @author someone
     * @param wb
     * @param fromsheet
     *            ,源sheet
     * @param newsheet
     *            ,目标sheet
     * @param srcFirstRow
     *            ,要复制的第一行号
     * @param dstFirstRow
     *            ,目标sheet上的目标行
     * @param n
     *            ,复制n行
     */
    public void copyRows(Workbook wb, Sheet fromsheet, Sheet newsheet,
            int srcFirstRow, int dstFirstRow, int n) {
 
        // 设置列宽
        Row fromRow = null;
        for (int i = srcFirstRow; i < srcFirstRow + n; i++) {
            fromRow = fromsheet.getRow(i);
            if (fromRow != null) {
                for (int j = fromRow.getLastCellNum(); j >= fromRow
                        .getFirstCellNum(); j--) {
                    int colnum = fromsheet.getColumnWidth((short) j);
                    if (colnum > 100) {
                        newsheet.setColumnWidth((short) j, (short) colnum);
                    }
                    if (colnum == 0) {
                        newsheet.setColumnHidden((short) j, true);
                    } else {
                        newsheet.setColumnHidden((short) j, false);
                    }
                }
                break;
            }
        }
 
        for (int i = srcFirstRow; i < srcFirstRow + n; i++) {
            copyRow(wb, fromsheet, newsheet, i, dstFirstRow++);
        }
        // System.out.println("共拷贝"+);
    }
 
    /**
     * @author someone
     * @param sheet
     * @param rowNum
     * @throws Exception
     */
    public void insertRowWithFormat(Sheet sheet, int rowNum) throws Exception {
        sheet.shiftRows(rowNum, rowNum + 1, 1);
        Row newRow = sheet.createRow(rowNum);
        Row oldRow = sheet.getRow(rowNum - 1);
        for (int i = oldRow.getFirstCellNum(); i < oldRow.getLastCellNum(); i++) {
            Cell oldCell = oldRow.getCell(i);
            if (oldCell != null) {
                CellStyle cellStyle = oldCell.getCellStyle();
                newRow.createCell(i).setCellStyle(cellStyle);
            }
        }
    }
     
    /**
     * @param cell,所要操作的cell
     * @param str,所要放置的文字
     * @param fonts,每个文字对应的设置的字体
     * @param placeholder,占位符
     */
    public void changeCellFont(Cell cell, List<String> strs,List<Font> fonts,String placeholder){
         
         
    }
    /**
     * 将cell中的placeholder的问题替换成dstStr，并且将字体改为dstFont
     * @author zhrb
     * @param cell,所要操作的cell
     * @param dstStr,所要放置的文字
     * @param dstFont,dstStr对应的要设置的字体
     * @param placeholder,占位符
     */
    public void changeCellFont(Cell cell,String dstStr,Font dstFont,String placeholder){
        Workbook wb = cell.getRow().getSheet().getWorkbook();
        String srcStr = cell.getStringCellValue();
        Font srcFont = wb.getFontAt(cell.getCellStyle().getFontIndex());
         
        int beginPos = srcStr.indexOf(placeholder);
        if(beginPos == -1)
            return;
         
        if (dstStr== null){
            dstStr="";
        }
        String newStr = srcStr.replace(placeholder, dstStr);
        int endPos = beginPos+dstStr.length();
         
        RichTextString tempRTS = null;
        if (cell.getRichStringCellValue() instanceof XSSFRichTextString){
            tempRTS = new XSSFRichTextString(newStr);
        }else{
            tempRTS = new HSSFRichTextString(newStr);
        }
         
        tempRTS.applyFont(srcFont);
         
        if (dstFont!=null){
            dstFont.setFontName(srcFont.getFontName());
            dstFont.setFontHeight(srcFont.getFontHeight());
            tempRTS.applyFont(beginPos, endPos, dstFont);
        }
        cell.setCellValue(tempRTS);
         
    }

     
    /**
     * 将cell中的placeholder的问题替换成dstStr,功能基本上同上一函数
     * @param cell
     * @param dstStr
     * @param placeholder
     */
    public void changeCellFont(Cell cell,String dstStr,String placeholder){
        changeCellFont(cell,dstStr,null,placeholder);
    }
 
    public static void main(String[] args) {
    	excelTest ew = new excelTest();
        ew.copySheet("报修单", "sheet1", "d:/网易公司新员工入职登记表.xlsx");
    }
}