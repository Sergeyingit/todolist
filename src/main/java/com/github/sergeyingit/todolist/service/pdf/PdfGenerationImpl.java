package com.github.sergeyingit.todolist.service.pdf;

import com.github.sergeyingit.todolist.entity.Task;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfGenerationImpl implements PdfGenerationService{
    @Override
    public InputStream prepareInputStrimPdf(List<Task> tasks) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        String title = "Your tasks";


        int fontSize = 18;
        File fontBoldFile = new ClassPathResource("fonts/Roboto-Bold.ttf").getFile();
        PDFont font = PDType0Font.load(document,fontBoldFile);
        contentStream.setFont(font, fontSize);
        float titleWidth = font.getStringWidth(title) / 1000 * fontSize;

        contentStream.beginText();
        contentStream.setFont(font, fontSize);
        contentStream.newLineAtOffset((page.getMediaBox().getWidth() - titleWidth) / 2,650);
        contentStream.showText(title);
        contentStream.newLine();
        contentStream.endText();

        createTaskPages(document, page, contentStream, tasks);

        document.save(byteArrayOutputStream);
        document.close();
        InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return inputStream;
    }

    private static void createTaskPages(PDDocument document, PDPage page, PDPageContentStream contentStream, List<Task> tasks) throws IOException {

        final int limitRowsTableInFirstPage = 11;
        final int limitRowsTableInSecondPage = 13;
        final int marginTableX = 20;
        final int marginTableFirstPageY = 600; //because the first page has a title
        final int marginTableSecondPageY = 700;
        int indexStartTaskList = 0;
        int indexEndTaskList = Math.min(tasks.size(), limitRowsTableInFirstPage);
        int countIteration = 1;
        do {

            if (countIteration == 1) {
                createTable(document, page, contentStream, marginTableX, marginTableFirstPageY, tasks.subList(indexStartTaskList, indexEndTaskList));
            } else {
                createTable(document ,page, contentStream, marginTableX, marginTableSecondPageY, tasks.subList(indexStartTaskList, indexEndTaskList));
            }

            contentStream.close();
            indexStartTaskList = indexEndTaskList;
            indexEndTaskList = Math.min(indexStartTaskList + limitRowsTableInSecondPage, tasks.size());
            countIteration++;

            if (indexStartTaskList >= tasks.size()) {
                break;
            }
            page = new PDPage();
            document.addPage(page);
            contentStream = new PDPageContentStream(document, page);

        } while (indexStartTaskList < tasks.size());
    }

    private static void createTable(PDDocument document, PDPage page, PDPageContentStream contentStream, float x, float y, List<Task> tasks)
            throws IOException {

        final int rows = tasks.size();
        final int cols = 3;
        final float rowHeight = 45f;
        final float tableWidth = page.getMediaBox().getWidth() - x * 2;
        final float tableHeight = rowHeight * rows;

        /*
         * setting proportions for columns
         */
        List<Float> colWidth = new ArrayList<>();
        final float col1 = tableWidth * 25 / 100;
        final float col2 = tableWidth * 55 / 100;
        final float col3 = tableWidth * 20 / 100;
        final float col4 = tableWidth * 25 / 100 + tableWidth * 55 / 100 + tableWidth * 20 / 100;
        colWidth.add(0,col1);
        colWidth.add(1,col2);
        colWidth.add(2,col3);
        colWidth.add(3,col4);

        /*
         * list for set coords start text
         */
        List<Float> coordsY = new ArrayList<>();
        List<Float> coordsX = new ArrayList<>();

        /*
         * setting horizontal lines
         */
        float nexty = y;
        for (int i = 0; i <= rows; i++) {
            contentStream.moveTo(x, nexty);
            contentStream.lineTo(x + tableWidth, nexty);
            contentStream.stroke();

            coordsY.add(nexty);

            nexty -= rowHeight;
        }

        /*
         * setting vertical lines
         */
        float nextx = x;
        for (int i = 0; i <= cols; i++) {
            contentStream.moveTo(nextx, y);
            contentStream.lineTo(nextx, y - tableHeight);
            contentStream.stroke();

            coordsX.add(nextx);

            nextx += colWidth.get(i);
        }

        insertText(document, contentStream, tasks, coordsX, coordsY);


    }

    private static void insertText (PDDocument document, PDPageContentStream contentStream, List<Task> tasks, List<Float> coordsX, List<Float> coordsY) throws IOException {
        /*
         * insert text
         */
        File fontRegularFile = new ClassPathResource("fonts/Roboto-Regular.ttf").getFile();
        PDFont fontTableText = PDType0Font.load(document,fontRegularFile);
        int sizeTableText = 10;
        contentStream.setFont(fontTableText, sizeTableText);

        /*
         * margin for coords start text
         */
        final int marginXTitleDateDescription = 10;
        final int marginYTitleDate = 12;
        final int marginYDescription = 32;
        final float lineSpacing = 13.5f;

        for (int i = 0; i < tasks.size(); i++) {

            Task task = tasks.get(i);

            if(!task.getTitle().isEmpty()) {
                contentStream.beginText();
                /*
                 * coordsY.get(i + 1) because first element is a top border table
                 */
                contentStream.newLineAtOffset(coordsX.get(0) + marginXTitleDateDescription, coordsY.get(i + 1) + marginYTitleDate);
                contentStream.showText(task.getTitle());
                contentStream.endText();
            }

            if(!task.getDescription().isEmpty()) {
                contentStream.beginText();
                contentStream.newLineAtOffset(coordsX.get(1) + marginXTitleDateDescription, coordsY.get(i + 1) + marginYDescription);
                String description = task.getDescription();

                final float rowWidth = fontTableText.getStringWidth(description) / 1000 * sizeTableText;
                final float columnDescriptionWidth = coordsX.get(2) - coordsX.get(1) - marginXTitleDateDescription;

                /*
                 * insert description
                 */
                if (columnDescriptionWidth > rowWidth) {
                    contentStream.showText(description);
                } else {

                    int currentRowString;
                    List<String> rowsString = new ArrayList<>();
                    rowsString.add("");

                    String[] stringArr = description.trim().split("\\s+");

                    for (String string : stringArr) {
                        StringBuilder stringBuilder = new StringBuilder();

                        currentRowString = rowsString.size() - 1;

                        stringBuilder.append(rowsString.get(currentRowString).trim());
                        stringBuilder.append(" " + string);

                        float stringBuilderWidth = fontTableText.getStringWidth(stringBuilder.toString()) / 1000 * sizeTableText;

                        if (columnDescriptionWidth >= stringBuilderWidth) {
                            rowsString.set(currentRowString, stringBuilder.toString());
                        } else {
                            rowsString.add(string);
                        }

                    }

                    for (String string : rowsString) {
                        contentStream.showText(string);
                        contentStream.setLeading(lineSpacing);
                        contentStream.newLine();
                    }

                }

                contentStream.endText();
            }

            if (task.getDate() != null) {
                contentStream.beginText();
                contentStream.newLineAtOffset(coordsX.get(2) + marginXTitleDateDescription, coordsY.get(i + 1) + marginYTitleDate);
                contentStream.showText(task.getDate().toString());
                contentStream.endText();
            }

        }
    }
}
