package com.g11.g11reader.fileinput;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Niskinator on 2016-12-15.
 */

public class cResourceLoader_text extends cResourceLoader
{
    //--    Member Variables

    /**
     * Constructor
     */
    public cResourceLoader_text()
    {
        // this resource loader can load files defined as type TEXT
        super( "TEXT" );
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
            String l_line;
            cResource_text p_resource = new cResource_text( IDENTIFIER );

            // read opening bracket
            if( ( l_line = BUFFEREDREADER.readLine() ) != null )
                if( l_line != "{" )
                    return null;

            // read all lines
            while( ( l_line = BUFFEREDREADER.readLine() ) != null )
            {
                // return loaded resource when the end of the file is found
                if( l_line == "}" )
                    return p_resource;

                // load data from file into resource
                p_resource.m_text.append( l_line ).append( "\n" );
            }
        }
        catch( IOException E )
        {
            return null;
        }

        return null;
    }

}
