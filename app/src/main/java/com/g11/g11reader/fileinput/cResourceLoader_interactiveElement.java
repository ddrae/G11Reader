package com.g11.g11reader.fileinput;

import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Niskinator on 2016-12-30.
 */

public class cResourceLoader_interactiveElement extends cResourceLoader
{
    //--    Member Variables

    /**
     * Constructor
     */
    public cResourceLoader_interactiveElement()
    {
        // this resource loader can load files defined as type INTERACTIVE_ELEMENT
        super( "INTERACTIVE_ELEMENT" );
    }

    @Override
    public cResource _loadResource( String IDENTIFIER, BufferedReader BUFFEREDREADER ) {

        try
        {
            String                          l_line;
            StringBuffer                    l_filebuffer = new StringBuffer();
            cResource_interactiveElement    p_resource   = new cResource_interactiveElement( IDENTIFIER );

            // read opening bracket
            if( ( l_line = BUFFEREDREADER.readLine() ) != null )
                if( l_line != "{" )
                    return null;

            // read all data
            l_line = BUFFEREDREADER.readLine();
            p_resource.m_boxX   = Integer.parseInt( l_line );
            l_line = BUFFEREDREADER.readLine();
            p_resource.m_boxY   = Integer.parseInt( l_line );
            l_line = BUFFEREDREADER.readLine();
            p_resource.m_boxW   = Integer.parseInt( l_line );
            l_line = BUFFEREDREADER.readLine();
            p_resource.m_boxH   = Integer.parseInt( l_line );
            l_line = BUFFEREDREADER.readLine();
            p_resource.m_soundID= l_line;
            l_line = BUFFEREDREADER.readLine();
            p_resource.m_textID = l_line;

            return p_resource;
        }
        catch( IOException E )
        {
            return null;
        }
    }
}
