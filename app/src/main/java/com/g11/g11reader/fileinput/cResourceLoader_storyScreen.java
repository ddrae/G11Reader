package com.g11.g11reader.fileinput;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Niskinator on 2016-12-30.
 */

public class cResourceLoader_storyScreen extends cResourceLoader
{
    //--    Member Variables

    /**
     * Constructor
     */
    public cResourceLoader_storyScreen()
    {
        // this resource loader can load files defined as type STORY_SCREEN
        super( "STORY_SCREEN" );
    }

    @Override
    public cResource _loadResource( String IDENTIFIER, BufferedReader BUFFEREDREADER ) {

        try
        {
            String                          l_line;
            StringBuffer                    l_filebuffer = new StringBuffer();
            cResource_storyScreen           p_resource   = new cResource_storyScreen( IDENTIFIER );

            // read opening bracket
            if( ( l_line = BUFFEREDREADER.readLine() ) != null )
                if( l_line != "{" )
                    return null;

            // read all data
            l_line = BUFFEREDREADER.readLine();
            p_resource.m_backgroundID   = l_line;
            l_line = BUFFEREDREADER.readLine();
            p_resource.m_musicID        = l_line;
            l_line = BUFFEREDREADER.readLine();
            p_resource.m_soundID        = l_line;

            return p_resource;
        }
        catch( IOException E )
        {
            return null;
        }
    }
}