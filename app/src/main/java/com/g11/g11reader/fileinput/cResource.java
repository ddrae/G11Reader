package com.g11.g11reader.fileinput;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Niskinator on 2016-12-14.
 */

public class cResource
{
    //--    Member Variables
    private String        m_identifier;

    /**
     * Constructor
     *
     * @param IDENTIFIER
     */
    public cResource( String IDENTIFIER )
    {
        m_identifier    = IDENTIFIER;
    }

    public final String _getIdentifier() {

        return m_identifier;
    }
}