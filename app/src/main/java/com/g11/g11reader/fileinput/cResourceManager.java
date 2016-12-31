package com.g11.g11reader.fileinput;
import android.content.Context;
import android.graphics.Path;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.StringBuilder;
import java.util.HashMap;
import java.util.Vector;

// http://stackoverflow.com/questions/30417810/reading-from-a-text-file-in-android-studio-java
// http://stackoverflow.com/questions/2492076/android-reading-from-an-input-stream-efficiently
// http://www.javapractices.com/topic/TopicAction.do?Id=42

/**
 * Created by Niskinator on 2016-12-09.
 */

public class cResourceManager
{
    //--    Member Variables
    private File                                m_file;
    private FileReader                          m_fileReader;
    private BufferedReader                      m_bufferedReader;
    private HashMap< String, cResource >        m_resources;
    private HashMap< String, cResourceLoader >  m_loaders;

    //--    Member Methods

    /**
     * Constructor
     *
     * @param FILE
     */
    //public cResourceManager( Context CONTEXT ) {
    public cResourceManager( File FILE ) {

        m_file              = FILE;
        m_fileReader        = null;
        m_bufferedReader    = null;
        m_resources         = new HashMap< String, cResource >();
        m_loaders           = new HashMap< String, cResourceLoader>();
    }

    /**
     * Add a resource loader
     *
     * @param RESOURCE_LOADER
     */
    public void _addResourceLoader( cResourceLoader RESOURCE_LOADER ) {

        if( m_loaders.containsKey( RESOURCE_LOADER._getType() ) )
            return;

        m_loaders.put( RESOURCE_LOADER._getType(), RESOURCE_LOADER );
    }

    /**
     * Open a file to read from
     */
    public boolean _openStream() {

        try
        {
            m_fileReader        = new FileReader( m_file );
            m_bufferedReader    = new BufferedReader( m_fileReader );
        }
        catch( IOException E )
        {
            return false;
        }

        return true;
    }

    /**
     * Load all files from a book file into memory
     */
    public boolean _loadFile()
    {
        try
        {
            // load all resources in file one by one
            String l_line;
            while( ( l_line = m_bufferedReader.readLine() ) != null )
            {
                String l_words[];   // [0] contains type, [1] contains identifier
                l_words = l_line.split( " " );
                cResourceLoader p_loader = null;

                // if type does not have a dedicated resource loader
                if( !m_loaders.containsKey( l_words[ 0 ] ) )
                {
                    // create base class resource loader which creates an empty resource
                    p_loader = new cResourceLoader( "NONE" );
                }
                // get dedicated resource loader
                else
                {
                    p_loader = m_loaders.get( l_words[ 0 ] );
                }

                // load resource
                cResource p_resource = null;
                if( ( p_resource = p_loader._loadResource( l_words[ 1 ], m_bufferedReader ) ) != null )
                {
                    // add to resources if successfully loaded
                    _addResource( p_resource );
                }
                else
                {
                    return false;
                }
            }
        }
        catch( IOException E )
        {
            return false;
        }

        // successfully loaded all resources from file
        return true;
    }

    /**
     * Close an open file
     */
    public boolean _closeFile() {

        try
        {
            m_bufferedReader.close();
            m_fileReader.close();
        }
        catch( IOException E )
        {
            return false;
        }

        return true;
    }

    /**
     * Add a resource
     *
     * @param RESOURCE
     */
    public void _addResource( cResource RESOURCE ) {

        if( RESOURCE == null )
            return;

        if( m_resources.containsKey( RESOURCE._getIdentifier() ) )
            return;

        // add resource
        m_resources.put( RESOURCE._getIdentifier(), RESOURCE );
    }

    /**
     * Get pointer to loaded resource
     *
     * @param IDENTIFIER
     * @return
     */
    public <T> T _getResource( String IDENTIFIER ) {

        // if resource does not exist
        if( !m_resources.containsKey( IDENTIFIER ) )
            return null;

        // check resource type
        cResource p_resource = m_resources.get( IDENTIFIER );
        try
        {
            T l_object = (T)p_resource;
        }
        catch( ClassCastException E )
        {
            return null;
        }

        return (T)p_resource;
    }
}
