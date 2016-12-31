package com.g11.g11reader.fileinput;

import java.util.Vector;

/**
 * Created by Niskinator on 2016-12-30.
 */

public class cResource_interactiveScreen extends cResource
{
    //--    Member Variables
    public String   m_backgroundID;
    public String   m_musicID;
    public Vector< String > m_elementsID;

    /**
     * Constructor
     *
     * @param IDENTIFIER
     */
    public cResource_interactiveScreen( String IDENTIFIER )
    {
        super( IDENTIFIER );
    }
}
