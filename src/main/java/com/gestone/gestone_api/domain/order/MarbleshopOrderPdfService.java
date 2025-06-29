package com.gestone.gestone_api.domain.order;

import com.gestone.gestone_api.domain.marbleshop.MarbleshopService;
import com.gestone.gestone_api.domain.marbleshop_item.MarbleshopItem;
import com.gestone.gestone_api.domain.miscellaneous_item.MiscellaneousItem;
import com.gestone.gestone_api.domain.payment.Payment;
import com.gestone.gestone_api.domain.payment.PaymentType;
import com.gestone.gestone_api.domain.quotation.Quotation;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
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
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
@Service
public class MarbleshopOrderPdfService {

    @Autowired
    private MarbleshopService marbleshopService;

    public String transformPaymentMethod(PaymentType paymentType) {
        switch (paymentType) {
            case PIX: return "Pix";
            case CASH: return "Dinheiro";
            case DEBIT: return "Débito";
            case CREDIT: return "Crédito";
        }
        return "Outros";
    }
    public byte[] generateOrderPdf(MarbleshopOrder order) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        pdfDoc.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfDoc);

        addHeader(document, order, dateFormatter); // Passa o dateFormatter
        addMarbleshopOrderInfos(document, order);

        // --- Seção de Itens Principais ---
        float[] itemTableCols = {220f, 120f, 20f, 70f, 70f, 70f};
        Table itemsTable = new Table(itemTableCols).useAllAvailableWidth();
        addTableHeader(itemsTable, "Itens Principais", 6); // Método auxiliar para cabeçalhos
        itemsTable.addCell(new Cell().add(new Paragraph("Item").simulateBold()));
        itemsTable.addCell(new Cell().add(new Paragraph("Detalhes").simulateBold()));
        itemsTable.addCell(new Cell().add(new Paragraph("Qtde.").simulateBold()).setTextAlignment(TextAlignment.CENTER));
        itemsTable.addCell(new Cell().add(new Paragraph("Área (m²)").simulateBold()).setTextAlignment(TextAlignment.CENTER));
        itemsTable.addCell(new Cell().add(new Paragraph("Valor Un.").simulateBold()).setTextAlignment(TextAlignment.CENTER));
        itemsTable.addCell(new Cell().add(new Paragraph("Valor Total").simulateBold()).setTextAlignment(TextAlignment.CENTER));

        for (MarbleshopItem item : order.getMarbleshopItems()) {
            itemsTable.addCell(item.getName() + " - " + String.format("%.3f", item.getMeasureX()) + " X " + String.format("%.3f", item.getMeasureY()) + " em " + item.getMarbleshopMaterial().getName());
            itemsTable.addCell(item.getDescription());
            itemsTable.addCell(String.valueOf(item.getQuantity())).setTextAlignment(TextAlignment.CENTER);
            itemsTable.addCell(String.format("%.3f", item.getTotalArea())).setTextAlignment(TextAlignment.CENTER);
            itemsTable.addCell(currencyFormatter.format(item.getUnitValue())).setTextAlignment(TextAlignment.CENTER);
            itemsTable.addCell(currencyFormatter.format(item.getTotalValue())).setTextAlignment(TextAlignment.CENTER);
        }
        itemsTable.setMarginBottom(10f);
        document.add(itemsTable);

        // --- Seção de Itens Diversos ---
        if (!order.getMiscellaneousItems().isEmpty()) {
            float[] miscellaneousTableCols = {190f, 100f, 120f, 20f, 70f, 70f};
            Table miscellaneousTable = new Table(miscellaneousTableCols).useAllAvailableWidth();
            addTableHeader(miscellaneousTable, "Itens Diversos", 6);
            miscellaneousTable.addCell(new Cell().add(new Paragraph("Item").simulateBold()));
            miscellaneousTable.addCell(new Cell().add(new Paragraph("Descrição").simulateBold()));
            miscellaneousTable.addCell(new Cell().add(new Paragraph("Detalhes Material").simulateBold())); // Nome mais claro
            miscellaneousTable.addCell(new Cell().add(new Paragraph("Qtde.").simulateBold()).setTextAlignment(TextAlignment.CENTER));
            miscellaneousTable.addCell(new Cell().add(new Paragraph("Valor Un.").simulateBold()).setTextAlignment(TextAlignment.CENTER));
            miscellaneousTable.addCell(new Cell().add(new Paragraph("Valor Total").simulateBold()).setTextAlignment(TextAlignment.CENTER));

            for (MiscellaneousItem item : order.getMiscellaneousItems()) {
                miscellaneousTable.addCell(item.getName());
                miscellaneousTable.addCell(item.getDetails());
                miscellaneousTable.addCell(item.getMiscellaneousMaterial().getDetails());
                miscellaneousTable.addCell(String.valueOf(item.getQuantity())).setTextAlignment(TextAlignment.CENTER);
                miscellaneousTable.addCell(currencyFormatter.format(item.getUnitValue())).setTextAlignment(TextAlignment.RIGHT);
                miscellaneousTable.addCell(currencyFormatter.format(item.getTotalValue())).setTextAlignment(TextAlignment.RIGHT);
            }
            miscellaneousTable.setMarginBottom(10f);
            document.add(miscellaneousTable);
        }

        // --- Seção de Pagamentos ---
        if (!order.getPayments().isEmpty()) {
            float[] paymentTableCols = {120f, 120f, 120f, 70f}; // Tipo, Valor, Data, Método, Observações
            Table paymentsTable = new Table(paymentTableCols).useAllAvailableWidth();
            addTableHeader(paymentsTable, "Pagamentos", 4);
            paymentsTable.addCell(new Cell().add(new Paragraph("Data").simulateBold()).setTextAlignment(TextAlignment.CENTER));
            paymentsTable.addCell(new Cell().add(new Paragraph("Valor Pago").simulateBold()).setTextAlignment(TextAlignment.CENTER));
            paymentsTable.addCell(new Cell().add(new Paragraph("Método").simulateBold()).setTextAlignment(TextAlignment.CENTER));
            paymentsTable.addCell(new Cell().add(new Paragraph("Observações").simulateBold()));

            for (Payment payment : order.getPayments()) {
                paymentsTable.addCell(payment.getPaymentDate().format(dateFormatter)).setTextAlignment(TextAlignment.CENTER);
                paymentsTable.addCell(currencyFormatter.format(payment.getPayedValue())).setTextAlignment(TextAlignment.CENTER);
                paymentsTable.addCell(transformPaymentMethod(payment.getPaymentType())).setTextAlignment(TextAlignment.CENTER); // Assumindo enum
                paymentsTable.addCell(payment.getDetails() != null && !payment.getDetails().isEmpty() ? payment.getDetails() : "-");
            }
            paymentsTable.setMarginBottom(10f);
            document.add(paymentsTable);
        }

        // --- Seção de Informações Finais e Totais ---
        Table finalQuotationInfos = new Table(2).useAllAvailableWidth().setTextAlignment(TextAlignment.RIGHT);

        // Área Total
        finalQuotationInfos.addCell(createFinalInfoCell("Área Total", String.format("%.3f", order.getTotalArea()) + " m²"));
        // Valor Total do Pedido (sem desconto)
        finalQuotationInfos.addCell(createFinalInfoCell("Valor Total do Pedido", currencyFormatter.format(order.getTotalValue())));

        // Desconto
        if (order.getDiscount() != null && order.getDiscount().intValue() > 0) {
            finalQuotationInfos.addCell(createFinalInfoCell("Desconto", currencyFormatter.format(order.getDiscount())));
        }

        // Valor Final (com desconto)
        finalQuotationInfos.addCell(createFinalInfoCell("Valor Final", currencyFormatter.format(order.getFinalValue()), true));

        // Total Pago
        finalQuotationInfos.addCell(createFinalInfoCell("Total Pago", currencyFormatter.format(order.getTotalPaid())));

        // Saldo a Pagar
        BigDecimal balanceDue = order.getFinalValue().subtract(order.getTotalPaid());
        finalQuotationInfos.addCell(createFinalInfoCell("Saldo a Pagar", currencyFormatter.format(balanceDue)));

        // Data de Instalação Estimada
        if (order.getEstimatedInstallmentDate() != null) {
            finalQuotationInfos.addCell(createFinalInfoCell("", ""));
            finalQuotationInfos.addCell(createFinalInfoCell("Estimado para", order.getEstimatedInstallmentDate().format(dateFormatter)));

        }

        // Data de Instalação Real (se houver)
        if (order.getInstallmentDate() != null) {
            finalQuotationInfos.addCell(createFinalInfoCell("Data de Instalação", order.getInstallmentDate().format(dateFormatter)));
        }

        Table notesTable = new Table(1).useAllAvailableWidth().setTextAlignment(TextAlignment.LEFT);
        // Observações
        if (order.getNotes() != null && !order.getNotes().trim().isEmpty()) {
            notesTable.addCell(new Cell(2, 1) // Ocupa duas colunas
                    .add(new Paragraph("Observações Gerais").simulateBold())
                    .add(new Paragraph(order.getNotes()))
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.LEFT)
            );
        }

        document.add(finalQuotationInfos);
        document.add(notesTable);
        document.close();

        return baos.toByteArray();
    }

    public void addHeader(Document document, MarbleshopOrder order, DateTimeFormatter dateFormatter) throws IOException {
        float[] threeColumWidth = {100f, 270f, 200f};
        Table table = new Table(threeColumWidth).useAllAvailableWidth();

        // Logo
        try {
            Resource imgResource = marbleshopService.getMarbleshopLogo(order.getMarbleshop().getId());
            ImageData imageData = ImageDataFactory.create(imgResource.getContentAsByteArray());
            Image logo = new Image(imageData);
            logo.setWidth(100);
            logo.setHeight(100);
            table.addCell(new Cell().add(logo).setBorder(Border.NO_BORDER));
        } catch (Exception e) {
            // Lidar com erro ao carregar logo (ex: logar erro, adicionar placeholder)
            table.addCell(new Cell().add(new Paragraph("Logo não disponível")).setBorder(Border.NO_BORDER));
            System.err.println("Erro ao carregar o logo da marmoraria: " + e.getMessage());
        }


        // Informações da Marmoraria (pode vir do objeto Marbleshop da Ordem)
        String companyName = "VIDMAR Vidros e Mármores"; // Exemplo
        String phone1 = "(11) 98953-5288 / (11) 98475-2473"; // Exemplo
        String email = order.getMarbleshop() != null ? order.getMarbleshop().getEmail() : "vidmarserv@outlook.com"; // Exemplo
        String address = "Av. Conde Francisco Matarazzo, 679";

        Cell companyInfoCell = new Cell().setBorder(Border.NO_BORDER);
        companyInfoCell.add(new Paragraph(companyName).setFontSize(15f).simulateBold());
        companyInfoCell.add(new Paragraph(phone1).setFontSize(10f));
        companyInfoCell.add(new Paragraph(email).setFontSize(10f));
        companyInfoCell.add(new Paragraph(address).setFontSize(10f));
        table.addCell(companyInfoCell);

        // Informações do Pedido (Número do Pedido, Data de Criação)
        Cell orderInfoCell = new Cell().setBorder(Border.NO_BORDER);
        orderInfoCell.add(new Paragraph("Pedido (" + (order.getId().toString().substring(0, 8)) + ")").setFontSize(20f).simulateBold()); // Usa localId se existir, senão parte do UUID
        orderInfoCell.add(new Paragraph("Data: " + order.getCreatedAt().format(dateFormatter)));
        table.addCell(orderInfoCell);

        table.setMarginBottom(10f);
        document.add(table);
    }

    /**
     * Adiciona as informações do cliente e da obra.
     *
     * @param document O objeto Document do iText7.
     * @param order O objeto MarbleshopOrder.
     */
    public void addMarbleshopOrderInfos(Document document, MarbleshopOrder order) {
        Table quotationInfosTable = new Table(new float[]{285f, 285f}).useAllAvailableWidth();

        // Tabela de informações do cliente
        Table customerInfoTable = new Table(new float[]{80f, 205f}); // Ajuste de largura para colunas
        customerInfoTable.addCell(createBoldLabelCell("Cliente:"));
        customerInfoTable.addCell(createValueCell(order.getCustomer().getName())).setTextAlignment(TextAlignment.LEFT);
        customerInfoTable.addCell(createBoldLabelCell("Contato:")).setTextAlignment(TextAlignment.LEFT);
        customerInfoTable.addCell(createValueCell(order.getCustomer().getPhone())).setTextAlignment(TextAlignment.LEFT);
        customerInfoTable.addCell(createBoldLabelCell("Email:")).setTextAlignment(TextAlignment.LEFT);
        customerInfoTable.addCell(createValueCell(
                (order.getCustomer().getEmail() != null && !order.getCustomer().getEmail().trim().isEmpty()) ?
                        order.getCustomer().getEmail() : "Não Informado"
        )).setTextAlignment(TextAlignment.LEFT);

        // Tabela de informações da obra
        Table quotationInfoTable = new Table(new float[]{80f, 205f}); // Ajuste de largura para colunas
        quotationInfoTable.addCell(createBoldLabelCell("Descrição:"));
        quotationInfoTable.addCell(createValueCell(
                (order.getNotes() != null && !order.getNotes().trim().isEmpty()) ?
                        order.getNotes() : "Não Informado"
        ));
        quotationInfoTable.addCell(createBoldLabelCell("Endereço:"));
        quotationInfoTable.addCell(createValueCell(order.getWorkAddress()));

        // Adiciona as tabelas aninhadas à tabela principal
        quotationInfosTable.addCell(new Cell()
                .add(new Paragraph("Informações do Cliente").simulateBold())
                .add(customerInfoTable).setBorder(Border.NO_BORDER)
        );
        quotationInfosTable.addCell(new Cell()
                .add(new Paragraph("Informações da Obra").simulateBold())
                .add(quotationInfoTable).setBorder(Border.NO_BORDER)
        );
        quotationInfosTable.setMarginBottom(10f);
        document.add(quotationInfosTable);
    }

    // --- Métodos Auxiliares para Reutilização de Estilos ---

    /**
     * Cria uma célula de cabeçalho de tabela com estilo padrão (fundo preto, texto branco, negrito).
     *
     * @param table O objeto Table ao qual o cabeçalho será adicionado.
     * @param headerText O texto do cabeçalho.
     * @param colSpan O número de colunas que o cabeçalho irá abranger.
     */
    private Cell addTableHeader(Table table, String headerText, int colSpan) {
        Cell headerCell = new Cell(1, colSpan)
                .add(new Paragraph(headerText))
                .setBackgroundColor(ColorConstants.BLACK)
                .setFontColor(ColorConstants.WHITE)
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .simulateBold();
        table.addHeaderCell(headerCell);
        return headerCell; // Retorna a célula, embora possa não ser usada diretamente
    }

    /**
     * Cria uma célula para um rótulo em negrito.
     * @param text O texto do rótulo.
     * @return Uma Cell configurada.
     */
    private Cell createBoldLabelCell(String text) {
        return new Cell().add(new Paragraph(text).simulateBold()).setBorder(Border.NO_BORDER);
    }

    /**
     * Cria uma célula para um valor.
     * @param text O texto do valor.
     * @return Uma Cell configurada.
     */
    private Cell createValueCell(String text) {
        return new Cell().add(new Paragraph(text)).setBorder(Border.NO_BORDER);
    }

    /**
     * Cria uma célula para informações finais (ex: Total, Saldo).
     *
     * @param label O rótulo da informação (ex: "Área Total").
     * @param value O valor da informação.
     * @param isProminent Se true, o valor será maior e em negrito.
     * @return Uma Cell configurada.
     */
    private Cell createFinalInfoCell(String label, String value, boolean isProminent) {
        Cell cell = new Cell().setBorder(Border.NO_BORDER);
        cell.add(new Paragraph(label).simulateBold());
        Paragraph valueParagraph = new Paragraph(value);
        if (isProminent) {
            valueParagraph.simulateBold().setFontSize(15f);
        }
        cell.add(valueParagraph);
        return cell;
    }

    /**
     * Overload para createFinalInfoCell com isProminent=false por padrão.
     */
    private Cell createFinalInfoCell(String label, String value) {
        return createFinalInfoCell(label, value, false);
    }
}
