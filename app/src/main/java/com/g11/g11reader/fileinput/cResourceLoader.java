package com.g11.g11reader.fileinput;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Niskinator on 2016-12-15.
 */

public class cResourceLoader
{
    //--    Member Variables
    private String  m_type; // what type of file this class will load

    /**
     *  Constructor
     */
    public cResourceLoader( String TYPE )
    {
        m_type  = TYPE;
    }

    /**
     * Load a resource from a BufferedReader object
     *
     * @param IDENTIFIER
     * @param BUFFEREDREADER
     * @return
     */
    public cResource _loadResource( String IDENTIFIER, BufferedReader BUFFEREDREADER ) {

        try
        {
            String l_line;

            // read opening bracket
            if( ( l_line = BUFFEREDREADER.readLine() ) != null )
                if( l_line != "{" )
                    return null;

            // find closing bracket
            while( ( l_line = BUFFEREDREADER.readLine() ) != null )
            {
                // return an enmpty resource object when the end of embedded file is found
                if( l_line == "}" )
                    return new cResource( IDENTIFIER );
            }
        }
        catch( IOException E )
        {
            return null;
        }

        return null;
    }

    /**
     * Get resource loader resource type
     *
     * @return
     */
    public final String _getType() {

        return m_type;
    }
}
