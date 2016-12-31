package com.g11.g11reader.fileinput;

import android.media.MediaPlayer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Niskinator on 2016-12-30.
 */

public class cResourceLoader_sound extends cResourceLoader
{
    //--    Member Variables

    /**
     * Constructor
     */
    public cResourceLoader_sound()
    {
        // this resource loader can load files defined as type SOUND
        super( "SOUND" );
    }

    @Override
    public cResource _loadResource( String IDENTIFIER, BufferedReader BUFFEREDREADER ) {

        try
        {
            String l_line;
            StringBuffer l_filebuffer = new StringBuffer();
            cResource_sound p_resource = new cResource_sound(IDENTIFIER);

            // read opening bracket
            if ((l_line = BUFFEREDREADER.readLine()) != null)
                if (l_line != "{")
                    return null;

            // read all lines
            while ((l_line = BUFFEREDREADER.readLine()) != null) {
                // return loaded resource when the end of the file is found
                if (l_line == "}") {
                    //
                    //  TODO:
                    //  Load music data from l_filebuffer with Java library into cResoure_sound.m_sound
                    //

                    /*File l_tempFile = File.createTempFile( l_filebuffer.toString(), ".mp3" );
                    FileOutputStream l_out = new FileOutputStream( l_tempFile );
                    IOUtils.copy( in, l_out );
                    p_resource.m_mediaPlayer = new MediaPlayer();
                    p_resource.m_mediaPlayer.setDataSource( l_tempFile.getPath() );
                    p_resource.m_mediaPlayer.prepare();*/

                    return p_resource;
                }

                // load data from file into file buffer
                l_filebuffer.append(l_line).append("\n");
            }
        }
        catch (IOException E)
        {
            return null;
        }

        return null;
    }
}
