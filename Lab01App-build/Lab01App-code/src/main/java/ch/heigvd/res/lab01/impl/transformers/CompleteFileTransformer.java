package ch.heigvd.res.lab01.impl.transformers;

import java.io.Writer;
import ch.heigvd.res.lab01.impl.filters.*;

/**
 * This class returns a writer decorated with two filters: an instance of the
 * UpperCaseFilterWriter and an instance of the FileNumberingFilterWriter. When an
 * instance of this class is passed to a file system explorer, it will generate an
 * output file with 1) uppercase letters and 2) line numbers at the beginning of each
 * line.
 *
 * @author Olivier Liechti
 * @author Ciani Antony
 */
public class CompleteFileTransformer extends FileTransformer {

    @Override
    public Writer decorateWithFilters(Writer writer) {

        // First to uppercase then file numbering
        writer = new FileNumberingFilterWriter(new UpperCaseFilterWriter(writer));
        return writer;
    }

}
