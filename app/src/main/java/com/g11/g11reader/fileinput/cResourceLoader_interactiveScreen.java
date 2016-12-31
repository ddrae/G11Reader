package com.g11.g11reader.fileinput;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Niskinator on 2016-12-30.
 */

public class cResourceLoader_interactiveScreen extends cResourceLoader
{
    //--    Member Variables

    /**
     * Constructor
     */
    public cResourceLoader_interactiveScreen()
    {
        // this resource loader can load files defined as type INTERACTIVE_SCREEN
        super( "INTERACTIVE_SCREEN" );
    }

    @Override
    public cResource _loadResource( String IDENTIFIER, BufferedReader BUFFEREDREADER ) {

        try
        {
            String                          l_line;
            StringBuffer                    l_filebuffer = new StringBuffer();
            cResource_interactiveScreen     p_resource   = new cResource_interactiveScreen( IDENTIFIER );

            // read opening bracket
            if( ( l_line = BUFFEREDREADER.readLine() ) != null )
                if( l_line != "{" )
                    return null;

            // read all data
            l_line = BUFFEREDREADER.readLine();
            p_resource.m_backgroundID   = l_line;
            l_line = BUFFEREDREADER.readLine();
            p_resource.m_musicID        = l_line;

            // read the rest of all lines
            while( ( l_line = BUFFEREDREADER.readLine() ) != null )
            {
                // return loaded resource when the end of the file is found
                if( l_line == "}" )
                    return p_resource;

                // load data from file into resource
                p_resource.m_elementsID.add( l_line );
            }

            return p_resource;
        }
        catch( IOException E )
        {
            return null;
        }
    }
}
