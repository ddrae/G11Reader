package com.g11.g11reader.fileinput;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Movie;
import android.media.MediaPlayer;
import android.util.Log;

import com.g11.g11reader.backend.Book;
import com.g11.g11reader.backend.MediaData;
import com.g11.g11reader.backend.Page;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/**
 * Created by H on 2016-12-01.
 */

public class BookLoader {
    private BookLoader() {}

    public static Book loadBook(File file) {

        // skapa resurshanteraren
        cResourceManager l_resourceManager = new cResourceManager( file );
        l_resourceManager._addResourceLoader( new cResourceLoader_image() );
        l_resourceManager._addResourceLoader( new cResourceLoader_interactiveElement() );
        l_resourceManager._addResourceLoader( new cResourceLoader_interactiveScreen() );
        l_resourceManager._addResourceLoader( new cResourceLoader_music() );
        l_resourceManager._addResourceLoader( new cResourceLoader_sound() );
        l_resourceManager._addResourceLoader( new cResourceLoader_story() );
        l_resourceManager._addResourceLoader( new cResourceLoader_storyScreen() );
        l_resourceManager._addResourceLoader( new cResourceLoader_text() );
        l_resourceManager._openStream();
        l_resourceManager._loadFile();

        // tillfälliga datacontainers
        List<Page> pages;
        List<Bitmap> images = new ArrayList<>();
        List<Movie> animations = new ArrayList<>();
        List<MediaPlayer> sounds = new ArrayList<>();

        // kopiera data från resurshanteraren
        cResource_text      l_text      = l_resourceManager._getResource( "text0" );
        cResource_image     l_image     = l_resourceManager._getResource( "image0" );
        //cResource_music     l_music     = l_resourceManager._getResource( "music0" );
        images.add( l_image.m_image );

        // skapa data-container
        MediaData data = new MediaData();
        data.setImages(images);
        data.setAnimations(animations);
        data.setSounds(sounds);

        // skapa bok
        pages = ContentLoader.loadBook( l_resourceManager );
        Book l_book = new Book( pages, data );

        return l_book;
    }

    public static Book loadZipBook(File file) {
        List<Page> pages = new ArrayList<>();
        List<Bitmap> images = new ArrayList<>();
        List<Movie> animations = new ArrayList<>();
        List<MediaPlayer> sounds = new ArrayList<>();

        try {
            Map<String, Bitmap> imagesM = new TreeMap<>();
            Map<String, Movie> animationsM = new TreeMap<>();
            Map<String, MediaPlayer> soundsM = new TreeMap<>();

            ZipFile zFile = new ZipFile(file);
            Enumeration zipEntries = zFile.entries();
            while (zipEntries.hasMoreElements()) {
                try {
                    ZipEntry entry = (ZipEntry) zipEntries.nextElement();
                    String fileName = entry.getName();
                    if (fileName.endsWith(".gif")) {
                        Movie movie = Movie.decodeStream(zFile.getInputStream(entry));
                        animationsM.put(fileName, movie);
                    } else if ((fileName.endsWith(".png"))
                            || (fileName.endsWith(".jpg"))
                            || (fileName.endsWith(".jpeg"))) {
                        Bitmap bitmap = BitmapFactory.decodeStream(zFile.getInputStream(entry));
                        imagesM.put(fileName, bitmap);
                    } else if ((fileName.endsWith(".mp3"))) {
                        System.out.println(fileName);
                        InputStream in =  zFile.getInputStream(entry);
                        File tempFile = File.createTempFile(Integer.toString(fileName.hashCode()), ".mp3");
                        FileOutputStream out = new FileOutputStream(tempFile);
                        IOUtils.copy(in, out);
                        MediaPlayer mp = new MediaPlayer();
                        mp.setDataSource(tempFile.getPath());
                        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                mp.start();
                            }
                        });
                        soundsM.put(fileName, mp);
                    } else if (fileName.endsWith("content")) {
                        InputStream in =  zFile.getInputStream(entry);
                        BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                        pages = ContentLoader.loadBook(br);
                    }
                } catch (NumberFormatException e) {
                }
            }
            images = new ArrayList<>(imagesM.values());
            animations = new ArrayList<>(animationsM.values());
            sounds = new ArrayList<>(soundsM.values());
        } catch (ZipException e) {
            Log.e("BookLoader", "Zip file exception", e);
        } catch (IOException e) {
            Log.e("BookLoader", "IO exception", e);
        }

        MediaData data = new MediaData();
        data.setImages(images);
        data.setAnimations(animations);
        data.setSounds(sounds);
        Book book = new Book(pages, data);
        return book;
    }
}
