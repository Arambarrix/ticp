package fr.istic.pdl.ticpbackend.strategy;

import com.flickr4java.flickr.FlickrException;

import java.io.InputStream;

public interface Strategy<T> {
    void savePhoto(Long id, InputStream photo,String titre) throws FlickrException;
}
