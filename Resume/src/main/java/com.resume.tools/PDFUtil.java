package com.resume.tools;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.BytpeArrayOutputStream;
import java.io.InputStream;
import java.util.Map;

public class PDFUtil {
	public static ByteArrayOutputStream generate(InputStream in, Map data) throws Exception{
		PdfReader template = new PdfReader(in);
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			PdfStamper tamp = new PdfStamper(template, out);
			//获取pdf文件表单域
			AcroFields form = stamp.getAcroFields();
			//支持中文
			BaseFont font = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
			for (Object o: data.keySet()){
				String key = (String) o;
				String value = (String) data.get(key);
				form.setFieldProperty(key, "textfont", font, null);
				form.setField(key, value, value);
			}
			stamp.setFormFlattening(true);
			stamp.close();
			return out;
		} catch (Exception e){
			e.printStackTrace();
			throw e;
		} finally {
			template.close();
			in.close();
		}
	}
}
