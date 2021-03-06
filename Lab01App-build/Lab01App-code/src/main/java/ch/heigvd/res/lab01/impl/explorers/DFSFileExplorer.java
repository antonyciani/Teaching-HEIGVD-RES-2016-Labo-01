package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.io.FileFilter;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered node
 * (file and directory). When the explorer reaches a directory, it visits all files
 * in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 * @author Ciani Antony
 */
public class DFSFileExplorer implements IFileExplorer {

    // File filter to retrieve only files that are directories
    private FileFilter directoryFilter = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            if (pathname.isDirectory()) {
                return true;
            } else {
                return false;
            }
        }
    };

    // File filter to retrieve only files that are not directories
    private FileFilter fileFilter = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            if (pathname.isFile()) {
                return true;
            } else {
                return false;
            }
        }
    };

    @Override
    public void explore(File rootDirectory, IFileVisitor vistor) {

        // First visit the directory/file
        vistor.visit(rootDirectory);

        if (rootDirectory.isDirectory()) { // if the file is a directory

            File[] filesInDirectory = rootDirectory.listFiles(this.fileFilter);
            File[] subdirectories = rootDirectory.listFiles(this.directoryFilter);

            // First visit the files
            for (File f : filesInDirectory) {
                vistor.visit(f);
            }
            // Then explore the rest of the subdirectories
            for (File f : subdirectories) {
                explore(f, vistor);
            }
        }

    }

}
