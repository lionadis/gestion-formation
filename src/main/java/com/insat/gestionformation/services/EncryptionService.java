package com.insat.gestionformation.services;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class EncryptionService {
    public String encrypt(String s){
        MessageDigest md=null;
        try{
            md= MessageDigest.getInstance("SHA-1");

        }catch (NoSuchAlgorithmException e){}
        return new String(md.digest(s.getBytes()));
    }
}
