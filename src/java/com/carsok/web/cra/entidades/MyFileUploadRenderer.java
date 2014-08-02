package com.carsok.web.cra.entidades;

import javax.faces.context.FacesContext;
import org.apache.commons.fileupload.FileItem;
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.component.fileupload.FileUploadRenderer;

public class MyFileUploadRenderer extends FileUploadRenderer{

    public MyFileUploadRenderer() {
        super();
    }

    @Override
    public void decodeSimple(FacesContext context, FileUpload fileUpload, FileItem file) {
        if (file!=null){
            super.decodeSimple(context, fileUpload, file); //To change body of generated methods, choose Tools | Templates.
            
        }
    }
    
}
