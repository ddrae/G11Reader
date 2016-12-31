package com.g11.g11reader.fileinput;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

/**
 * Created by Niskinator on 2016-12-15.
 */

public class cResourceLoader_image extends cResourceLoader
{
    //--    Member Variables

    /**
     * Construcotr
     */
    public cResourceLoader_image()
    {
        // this resource loader can load files defined as type IMAGE
        super( "IMAGE" );
    }

    /**
     * Load a resource from a BufferedReader object
     *
     * @param IDENTIFIER
     * @param BUFFEREDREADER
     * @return
     */
    @Override
    public cResource _loadResource( String IDENTIFIER, BufferedReader BUFFEREDREADER ) {

        try
        {
            String          l_line;
            StringBuffer    l_filebuffer = new StringBuffer();
            cResource_image p_resource   = new cResource_image( IDENTIFIER );

            // read opening bracket
            if( ( l_line = BUFFEREDREADER.readLine() ) != null )
                if( l_line != "{" )
                    return null;

            // read all lines
            while( ( l_line = BUFFEREDREADER.readLine() ) != null )
            {
                // return loaded resource when the end of the file is found
                if( l_line == "}" )
                {
                    //
                    //  TODO:
                    //  Load image data from l_filebuffer with Java library into cResoure_image.m_image
                    //

                    p_resource.m_image = BitmapFactory.decodeByteArray( l_filebuffer.toString().getBytes(),
                                                                        0,
                                                                        l_filebuffer.length() );


                    return p_resource;
                }

                // load data from file into file buffer
                l_filebuffer.append( l_line ).append( "\n" );
            }
        }
        catch( IOException E )
        {
            return null;
        }

        return null;
    }
}
