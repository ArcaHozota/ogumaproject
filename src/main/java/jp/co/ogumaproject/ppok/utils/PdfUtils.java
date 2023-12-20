package jp.co.ogumaproject.ppok.utils;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PdfUtils {

	/**
	 * PDFファイル作成
	 *
	 * @param tmpFilePath 入力パス
	 */
	public static final void printPdf(final String tmpFilePath) {

		try {
			// PDFフォーム取得
			final PDDocument pdfDocument = PDDocument.load(new File(tmpFilePath));

			// 帳票フォーム内容を取得する
			final PDAcroForm acroForm = pdfDocument.getDocumentCatalog().getAcroForm();

			if (acroForm != null) {
				// 電話
				final PDTextField phone = (PDTextField) acroForm.getField("phone");
				phone.setValue("0101234567");
				// Fax
				final PDTextField fax = (PDTextField) acroForm.getField("fax");
				fax.setValue("0101234568");
				// Fax
				final PDTextField amount = (PDTextField) acroForm.getField("amount");
				amount.setValue("9999999");
			}

			// システム日時を取得する
			final LocalDateTime now = LocalDateTime.now();
			final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy-HHmmss-UTC9");
			final String sysDate = now.format(formatter);

			// PDF出力
			pdfDocument.save("D:/work/pdf/output/請求書_" + sysDate + ".pdf");
		} catch (final Exception e) {
			e.printStackTrace();
		}

	}
}