package com.github.sergeyingit.todolist.service.pdf;

import com.github.sergeyingit.todolist.entity.Task;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Service for Pdf Generation.
 */
public interface PdfGenerationService {

    /**
     * Create pdf
     * @param tasks is List<Task>
     * @return {@link InputStream} for response with pdf
     */
    InputStream prepareInputStrimPdf(List<Task> tasks) throws IOException;
}
