package com.g11.g11reader.fileinput;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Niskinator on 2016-12-30.
 */

public class cResourceLoader_story extends cResourceLoader
{
    //--    Member Variables

    /**
     * Constructor
     */
    public cResourceLoader_story()
    {
        // this resource loader can load files defined as type STORY
        super( "STORY" );
    }

    @Override
    public cResource _loadResource( String IDENTIFIER, BufferedReader BUFFEREDREADER ) {

        try
        {
            String                          l_line;
            StringBuffer                    l_filebuffer = new StringBuffer();
            cResource_story                 p_resource   = new cResource_story( IDENTIFIER );

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
                p_resource.m_storyScreensID.add( l_line );
            }

            return p_resource;
        }
        catch( IOException E )
        {
            return null;
        }
    }
}
