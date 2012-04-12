/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ez_squeeze;

import java.io.File;
import java.io.IOException;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Stanish Family
 */

  public class RestrictedFileChooser extends FileSystemView
{
    private File[] rootDirectories;

    public RestrictedFileChooser(File rootDirectory)
    {
        this.rootDirectories = new File[] {rootDirectory};
    }

    public RestrictedFileChooser(File[] rootDirectories)
    {
        this.rootDirectories = rootDirectories;
    }

    @Override
    public File createNewFolder(File containingDir) throws IOException
    {       
        throw new UnsupportedOperationException("Unable to create directory");
    }

    @Override
    public File[] getRoots()
    {
        return rootDirectories;
    }
    @Override
    public File getHomeDirectory()
    {
        return rootDirectories[0];
    }


    @Override
    public boolean isRoot(File file)
    {
        for (File root : rootDirectories) {
            if (root.equals(file)) {
                return true;
            }
        }
        return false;
    }
}
/*
 * 
 */
