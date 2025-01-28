package com.gestone.gestone_api.domain.quotation;

import com.gestone.gestone_api.domain.marbleshop.MarbleshopService;
import com.gestone.gestone_api.domain.marbleshop_item.MarbleshopItem;
import com.gestone.gestone_api.domain.miscellaneous_item.MiscellaneousItem;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;


@Service
public class QuotationPdfService {
    @Autowired
    private MarbleshopService marbleshopService;

    /*
        public byte[] generatePdf(Quotation quotation) throws IOException {
            try (PDDocument document = new PDDocument(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                PDPage page = new PDPage();
                document.addPage(page);
                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    int pageWidth = (int) page.getMediaBox().getWidth();
                    int pageHeight = (int) page.getMediaBox().getHeight();
                    TextClass textClass = new TextClass(document, contentStream);
                    PDFont headerFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
                    PDFont italicFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA_OBLIQUE);
                    Resource imgResource = marbleshopService.getMarbleshopLogo(quotation.getMarbleshop().getId());
                    PDImageXObject pdImg = PDImageXObject.createFromByteArray(document, imgResource.getContentAsByteArray(), "marbleshopLogo");
                    contentStream.drawImage(pdImg, 2, pageHeight - 110, 100, 100);
                    String[] marbleshopDetails = new String[]{quotation.getMarbleshop().getName(), quotation.getMarbleshop().getPhone(), quotation.getMarbleshop().getEmail()};
                    textClass.addMultiLineText(marbleshopDetails, 18, pageWidth - pdImg.getWidth(), pageHeight - 25, headerFont, 16, Color.BLACK);
                    String quotationNumber = "Nº " + quotation.getLocalId();
                    var quotationDates = new String[]{"Data: " + quotation.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), "Prazo de entrega: " + quotation.getDeadlineDays() + " dias", "Validade: " + quotation.getDaysForDue() + " dias"};
                    textClass.addMultiLineText(quotationDates, 18, (int) (pageWidth - textClass.getTextWidth(quotationDates[1], headerFont, 16)), pageHeight - 25, headerFont, 16, Color.BLACK);
                    textClass.addSingleLineText(quotationNumber, (int) (pageWidth - textClass.getTextWidth(quotationNumber, headerFont, 24)), pageHeight - 100, headerFont, 24, Color.BLACK);
                    textClass.addSingleLineText("Vendedor: " + quotation.getUser().getName(), pageWidth - pdImg.getWidth(), pageHeight - 100, headerFont, 14, Color.BLACK);
                    String[] quotationDetails = new String[]{"Cliente: " + quotation.getCustomer().getName(), "Contato: " + quotation.getCustomer().getPhone(), "Endereço: " + quotation.getAddress(), "Descrição: " + quotation.getDetails()};
                    contentStream.addRect(0, pageHeight - 115, pageWidth, 2);
                    contentStream.fill();
                    textClass.addMultiLineText(quotationDetails, 18, pageWidth - pdImg.getWidth() - 100, pageHeight - 135, headerFont, 14, Color.BLACK);
                    textClass.addSingleLineText("Itens Principais", (int) ((pageWidth / 2) - (textClass.getTextWidth("Itens Principais", headerFont, 14)) / 2), pageHeight - 215, headerFont, 14, Color.BLACK);
                    TableClass tableClass = new TableClass(document, contentStream);
                    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"));
                    int[] itemsCellWidths = {30, 220, 80, 100, 60, 100};
                    tableClass.setTable(itemsCellWidths, 50, pageWidth - Arrays.stream(itemsCellWidths).sum() - 10, pageHeight - 270);
                    PDFont tableHeadFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
                    tableClass.setTableFont(tableHeadFont, 12, Color.WHITE);
                    Color tableHeadColor = Color.BLACK;
                    Color tableBodyColor = Color.LIGHT_GRAY;
                    tableClass.addCell(new String[]{"Nº"}, tableHeadColor);
                    tableClass.addCell(new String[]{"Descrição"}, tableHeadColor);
                    tableClass.addCell(new String[]{"Área"}, tableHeadColor);
                    tableClass.addCell(new String[]{"Valor"}, tableHeadColor);
                    tableClass.addCell(new String[]{"Qtde."}, tableHeadColor);
                    tableClass.addCell(new String[]{"Valor Total"}, tableHeadColor);
                    PDFont tableBodyFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
                    tableClass.setTableFont(tableBodyFont, 12, Color.BLACK);
                    for (int i = 0; i < quotation.getMarbleshopItems().size(); i++) {
                        MarbleshopItem item = quotation.getMarbleshopItems().get(i);
                        tableClass.addCell(new String[]{String.valueOf(i + 1)}, tableBodyColor);
                        tableClass.addCell(new String[]{item.getName() + " - " + item.getMeasureX() + "x" + item.getMeasureY(), item.getMarbleshopMaterial().getName()}, tableBodyColor);
                        tableClass.addCell(new String[]{item.getUnitArea() + " m²"}, tableBodyColor);
                        tableClass.addCell(new String[]{currencyFormatter.format(item.getUnitValue())}, tableBodyColor);
                        tableClass.addCell(new String[]{String.valueOf(item.getQuantity())}, tableBodyColor);
                        tableClass.addCell(new String[]{currencyFormatter.format(item.getTotalValue())}, tableBodyColor);

                    }
                    if (!quotation.getMiscellaneousItems().isEmpty()) {
                        textClass.addSingleLineText("Itens Diversos", (int) ((pageWidth / 2) - (textClass.getTextWidth("Itens Diversos", headerFont, 14)) / 2), tableClass.yPosition + 5, headerFont, 14, Color.BLACK);

                        tableClass.setTable(itemsCellWidths, 50, pageWidth - Arrays.stream(itemsCellWidths).sum() - 10, tableClass.yPosition - 50);
                        tableClass.setTableFont(tableHeadFont, 12, Color.WHITE);
                        tableClass.addCell(new String[]{"Nº"}, tableHeadColor);
                        tableClass.addCell(new String[]{"Descrição"}, tableHeadColor);
                        tableClass.addCell(new String[]{"Área"}, tableHeadColor);
                        tableClass.addCell(new String[]{"Valor"}, tableHeadColor);
                        tableClass.addCell(new String[]{"Qtde."}, tableHeadColor);
                        tableClass.addCell(new String[]{"Valor Total"}, tableHeadColor);

                        tableClass.setTableFont(tableBodyFont, 12, Color.BLACK);
                        for (int i = 0; i < quotation.getMiscellaneousItems().size(); i++) {
                            MiscellaneousItem item = quotation.getMiscellaneousItems().get(i);
                            tableClass.addCell(new String[]{String.valueOf(i + 1)}, tableBodyColor);
                            tableClass.addCell(new String[]{item.getName(), item.getMiscellaneousMaterial().getName()}, tableBodyColor);
                            tableClass.addCell(new String[]{"0.000 m²"}, tableBodyColor);
                            tableClass.addCell(new String[]{currencyFormatter.format(item.getUnitValue())}, tableBodyColor);
                            tableClass.addCell(new String[]{String.valueOf(item.getQuantity())}, tableBodyColor);
                            tableClass.addCell(new String[]{currencyFormatter.format(item.getTotalValue())}, tableBodyColor);
                        }
                    }

                    textClass.addSingleLineText("Total: " + currencyFormatter.format(quotation.getTotalValue()), (int) (pageWidth - textClass.getTextWidth("Total: R$ " + quotation.getTotalValue(), headerFont, 16)) -30, tableClass.yPosition - 20, headerFont, 16, Color.BLACK);
                    textClass.addSingleLineText("Condições de pagamento: Sinal de 40% do valor total, saldo em até 2x após entrega",(int) (pageWidth - textClass.getTextWidth("Condições de pagamento: Sinal de 40% do valor total, saldo em até 2x após entrega", headerFont, 14)) -30, tableClass.yPosition -60, headerFont, 14, Color.BLACK);

                }
                document.save(out);
                return out.toByteArray();
            } catch (IOException e) {
                throw new RuntimeException("Error trying to generate PDF", e);
            }
        }

        private static class TextClass {
            PDDocument document;
            PDPageContentStream contentStream;

            public TextClass(PDDocument document, PDPageContentStream contentStream) {
                this.document = document;
                this.contentStream = contentStream;
            }

            void addSingleLineText(String text, int xPosition, int yPosition, PDFont font, float fontSize, Color color) throws IOException {
                contentStream.beginText();
                contentStream.setFont(font, fontSize);
                contentStream.setNonStrokingColor(color);
                contentStream.newLineAtOffset(xPosition, yPosition);
                contentStream.showText(text);
                contentStream.endText();
                contentStream.moveTo(0, 0);
            }

            void addMultiLineText(String[] textArray, float leading, int xPosition, int yPosition, PDFont font, float fontSize, Color color) throws IOException {
                contentStream.beginText();
                contentStream.setFont(font, fontSize);
                contentStream.setNonStrokingColor(color);
                contentStream.setLeading(leading);
                contentStream.newLineAtOffset(xPosition, yPosition);
                for (String text : textArray) {
                    contentStream.showText(text);
                    contentStream.newLine();
                }
                contentStream.endText();
                contentStream.moveTo(0, 0);
            }

            float getTextWidth(String text, PDFont font, float fontSize) throws IOException {
                return font.getStringWidth(text) / 1000 * fontSize;
            }

        }

        private static class TableClass {
            PDDocument document;
            PDPageContentStream contentStream;
            private int[] colWidths;
            private int cellHeight;
            private int xPosition;
            private int yPosition;
            private int colPosition = 0;
            private int xInitialPosition;
            private float fontSize;
            private PDFont font;
            private Color fontColor;

            public TableClass(PDDocument document, PDPageContentStream contentStream) {
                this.document = document;
                this.contentStream = contentStream;
            }

            void setTable(int[] colWidths, int cellHeight, int xPosition, int yPosition) {
                this.colWidths = colWidths;
                this.cellHeight = cellHeight;
                this.xPosition = xPosition;
                this.yPosition = yPosition;
                this.xInitialPosition = xPosition;
            }

            void setTableFont(PDFont font, float fontSize, Color fontColor) throws IOException {
                this.font = font;
                this.fontSize = fontSize;
                this.fontColor = fontColor;
                contentStream.setFont(font, fontSize);
            }

            void addCell(String[] texts, Color fillColor) throws IOException {
                contentStream.setStrokingColor(1f);

                if (fillColor != null) {
                    contentStream.setNonStrokingColor(fillColor);
                }

                contentStream.addRect(xPosition, yPosition, colWidths[colPosition], cellHeight);

                if (fillColor == null) {
                    contentStream.stroke();
                } else {
                    contentStream.fillAndStroke();
                }

                contentStream.beginText();
                contentStream.setNonStrokingColor(fontColor);
                contentStream.newLineAtOffset(xPosition + 10, yPosition + 30);
                contentStream.showText(texts[0]);
                if (texts.length > 1) {
                    contentStream.newLine();
                    contentStream.showText(texts[1]);
                }

                contentStream.endText();

                xPosition = xPosition + colWidths[colPosition];
                colPosition++;

                if (colPosition == colWidths.length) {
                    colPosition = 0;
                    xPosition = xInitialPosition;
                    yPosition -= cellHeight;
                }
            }
        }*/
    public byte[] generateQuotationPdf(Quotation quotation) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"));
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        pdfDoc.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfDoc);
        addHeader(document, quotation);
        addQuotationInfos(document, quotation);
        float[] itemTableCols = {220f, 120f, 20f, 70f, 70f, 70f};
        float[] miscellaneousTableCols = {250f, 160f, 20f, 70f, 70f};
        Table itemsTable = new Table(itemTableCols).useAllAvailableWidth();
        Table miscellaneousTable = new Table(miscellaneousTableCols).useAllAvailableWidth();
        Cell itemTableHeaderCell = new Cell(1, 6)
                .add(new Paragraph("Itens Principais"))
                .setBackgroundColor(ColorConstants.BLACK)
                .setFontColor(ColorConstants.WHITE)
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .simulateBold();
        itemsTable.addHeaderCell(itemTableHeaderCell);
        itemsTable.addCell("Item");
        itemsTable.addCell("Detalhes");
        itemsTable.addCell("Qtde.");
        itemsTable.addCell("Área (m²)");
        itemsTable.addCell("Valor Un.");
        itemsTable.addCell("Valor Total");
        for (MarbleshopItem item : quotation.getMarbleshopItems()) {
            itemsTable.addCell(item.getName() + " - " + String.format("%.3f", item.getMeasureX()) + " X " + String.format("%.3f", item.getMeasureY()) + " em " + item.getMarbleshopMaterial().getName());
            itemsTable.addCell(item.getDescription());
            itemsTable.addCell(String.valueOf(item.getQuantity())).setTextAlignment(TextAlignment.CENTER);
            itemsTable.addCell(String.format("%.3f", item.getTotalArea()));
            itemsTable.addCell(currencyFormatter.format(item.getUnitValue()));
            itemsTable.addCell(currencyFormatter.format(item.getTotalValue()));
        }
        Cell miscellaneousTableHeaderCell = new Cell(1, 5)
                .add(new Paragraph("Itens Diversos"))
                .setBackgroundColor(ColorConstants.BLACK)
                .setFontColor(ColorConstants.WHITE)
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .simulateBold();
        miscellaneousTable.addHeaderCell(miscellaneousTableHeaderCell);
        miscellaneousTable.addCell("Item");
        miscellaneousTable.addCell("Detalhes");
        miscellaneousTable.addCell("Qtde.");
        miscellaneousTable.addCell("Valor Un.");
        miscellaneousTable.addCell("Valor Total");
        for (MiscellaneousItem item : quotation.getMiscellaneousItems()) {
            miscellaneousTable.addCell(item.getName());
            miscellaneousTable.addCell(item.getDetails());
            miscellaneousTable.addCell(String.valueOf(item.getQuantity())).setTextAlignment(TextAlignment.CENTER);
            miscellaneousTable.addCell(currencyFormatter.format(item.getUnitValue()));
            miscellaneousTable.addCell(currencyFormatter.format(item.getTotalValue()));
        }
        itemsTable.setMarginBottom(10f);
        document.add(itemsTable);
        miscellaneousTable.setMarginBottom(10f);
        if (!quotation.getMiscellaneousItems().isEmpty()) {
            document.add(miscellaneousTable);
        }
        Table finalQuotationInfos = new Table(2).useAllAvailableWidth().setTextAlignment(TextAlignment.RIGHT);
        finalQuotationInfos.addCell(new Cell()
                .add(new Paragraph("Área Total").simulateBold()).setBorder(Border.NO_BORDER)
                .add(new Paragraph(String.format("%.3f", quotation.getTotalArea())+ " m²" ))
        );
        finalQuotationInfos.addCell(new Cell()
                .add(new Paragraph("Valor Total").simulateBold()).setBorder(Border.NO_BORDER)
                .add(new Paragraph(currencyFormatter.format(quotation.getTotalValue())).simulateBold().setFontSize(15f)).setBorder(Border.NO_BORDER)
        );
        finalQuotationInfos.addCell(new Cell()
                .add(new Paragraph("Prazo de Entrega").simulateBold()).setBorder(Border.NO_BORDER)
                .add(new Paragraph(quotation.getDeadlineDays() + " dias")).setBorder(Border.NO_BORDER)
        );
        finalQuotationInfos.addCell(new Cell()
                .add(new Paragraph("Condições de Pagamento").simulateBold()).setBorder(Border.NO_BORDER)
                .add(new Paragraph((quotation.getPaymentCondition() != null || Objects.equals(quotation.getPaymentCondition(), "") ? quotation.getPaymentCondition() : "Não Informado"))).setBorder(Border.NO_BORDER)
        );

        document.add(finalQuotationInfos);
        document.close();

        return baos.toByteArray();
    }

    public void addHeader(Document document, Quotation quotation) throws IOException {
        float threeColumWidth[] = {100f, 270f, 200f};
        Resource imgResource = marbleshopService.getMarbleshopLogo(quotation.getMarbleshop().getId());
        ImageData imageData = ImageDataFactory.create(imgResource.getContentAsByteArray());
        Image logo = new Image(imageData);
        logo.setWidth(100);
        logo.setHeight(100);
        Table table = new Table(threeColumWidth);

        table.addCell(new Cell().add(logo).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("VIDMAR Vidros e Mármores").setFontSize(15f).simulateBold()).add(new Paragraph("(11) 9 8953-5288").setFontSize(10f)).add(new Paragraph("(11) 98475-2473").setFontSize(10f)).add(new Paragraph("marmorariavidmar.com.br").setFontSize(10f)).add(new Paragraph("vidmarserv@outlook.com").setFontSize(10f)).add(new Paragraph("Av. Conde Francisco Matarazzo, 679").setFontSize(10f)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("Orçamento Nº " + quotation.getLocalId()).setFontSize(20f).simulateBold()).add(new Paragraph("Data: " + quotation.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))).add(new Paragraph("Validade: " + quotation.getCreatedAt().plusDays(quotation.getDaysForDue()).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
        table.setMarginBottom(10f);
        document.add(table);

    }

    public void addQuotationInfos(Document document, Quotation quotation) {
        Table quotationInfosTable = new Table(new float[]{285f, 285f});
        Table customerInfoTable = new Table(2);
        Table quotationInfoTable = new Table(2);
        customerInfoTable.addCell(new Cell()
                .add(new Paragraph("Cliente:").simulateBold()).setBorder(Border.NO_BORDER)
        );
        customerInfoTable.addCell(new Cell()
                .add(new Paragraph(quotation.getCustomer().getName())).setBorder(Border.NO_BORDER)
        );
        customerInfoTable.addCell(new Cell()
                .add(new Paragraph("Contato:").simulateBold()).setBorder(Border.NO_BORDER)
        );
        customerInfoTable.addCell(new Cell()
                .add(new Paragraph(quotation.getCustomer().getPhone())).setBorder(Border.NO_BORDER)
        );
        customerInfoTable.addCell(new Cell()
                .add(new Paragraph("Email:").simulateBold()).setBorder(Border.NO_BORDER)
        );
        customerInfoTable.addCell(new Cell()
                .add(new Paragraph((quotation.getCustomer().getEmail() != null || Objects.equals(quotation.getCustomer().getEmail(), "")) ? quotation.getCustomer().getEmail(): "Não Informado")).setBorder(Border.NO_BORDER)
        );
        quotationInfoTable.addCell(new Cell()
                .add(new Paragraph("Nome:").simulateBold()).setBorder(Border.NO_BORDER)
        );
        quotationInfoTable.addCell(new Cell()
                .add(new Paragraph(quotation.getName())).setBorder(Border.NO_BORDER)
        );
        quotationInfoTable.addCell(new Cell()
                .add(new Paragraph("Descrição:").simulateBold()).setBorder(Border.NO_BORDER)
        );
        quotationInfoTable.addCell(new Cell()
                .add(new Paragraph(quotation.getDetails())).setBorder(Border.NO_BORDER)
        );
        quotationInfoTable.addCell(new Cell()
                .add(new Paragraph("Endereço:").simulateBold()).setBorder(Border.NO_BORDER)
        );
        quotationInfoTable.addCell(new Cell()
                .add(new Paragraph(quotation.getAddress())).setBorder(Border.NO_BORDER)
        );
        quotationInfosTable.addCell(new Cell()
                .add(new Paragraph("Informações do cliente").simulateBold())
                .add(customerInfoTable).setBorder(Border.NO_BORDER)
        );
        quotationInfosTable.addCell(new Cell()
                .add(new Paragraph("Informações da Obra").simulateBold()).setBorder(Border.NO_BORDER)
                .add(quotationInfoTable).setBorder(Border.NO_BORDER)
        );
        document.add(quotationInfosTable);
    }
}

