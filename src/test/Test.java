/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import database.JDBCutil;
import net.sf.jasperreports.engine.design.JasperDesign;
import java.sql.Connection;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author PC
 */
public class Test {
    public static void main(String[] args) {
        Connection connect = JDBCutil.getConnection();
        HashMap map = new HashMap();
        map.put("getorderId", 29);
        try {
            JasperDesign jasperD =
                    JRXmlLoader.load("C:\\Users\\PC\\OneDrive\\Máy tính\\JAVA BE\\java project\\CafeShop\\CafeShop\\src\\view\\report.jrxml");

            JasperReport jReport = JasperCompileManager.compileReport(jasperD);

            JasperPrint jPrint = JasperFillManager.fillReport(jReport, map, connect);

            JasperViewer.viewReport(jPrint, false);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
}
