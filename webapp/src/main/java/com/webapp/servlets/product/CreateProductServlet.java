/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.webapp.servlets.product;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapp.models.CreateProductModel;
import com.webapp.utils.HttpRequestUtils;
import com.webapp.utils.Response;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 *
 * @author LütfullahŞAHİN
 */
@WebServlet(name = "CreateProductServlet", urlPatterns = {"/urunler/yeni"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class CreateProductServlet extends HttpServlet {
    
    private static final Gson GSON = new GsonBuilder().create();

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/product/createProduct.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        CreateProductModel model = new CreateProductModel();
        model.setName(request.getParameter("name"));
        model.setDescription(request.getParameter("description"));
        model.setPrice(Double.parseDouble(request.getParameter("price")));
        model.setStock(Integer.parseInt(request.getParameter("stock")));
        
        List<Part> fileParts = request
                                .getParts()
                                .stream()
                                .filter(part -> "images".equals(part.getName()) && part.getSize() > 0)
                                .collect(Collectors.toList());
        
        ArrayList<String> fileNames = new ArrayList<String>();
        HashMap<String, String> fileNameMap = new HashMap<String, String>();
        
        boolean validFileSize = true;
        
        for(Part filePart : fileParts) {
            if(filePart.getSize() > 10 * 1000 * 1000) {
                validFileSize = false;
                request.setAttribute("imageError", "Dosya boyutu büyük(>10MB). Dosya Adı:" + filePart.getSubmittedFileName() + filePart.getContentType());
                request.getRequestDispatcher("/WEB-INF/product/createProduct.jsp").forward(request, response);
                break;
            }
            String fileName = filePart.getSubmittedFileName();
            int firstIndexOf = fileName.indexOf('.');
            int lastIndexOf = fileName.lastIndexOf('.');
            
            if(firstIndexOf == lastIndexOf)
            {
                String newFileName = UUID.randomUUID() + fileName.substring(lastIndexOf); 
                fileNames.add(newFileName);
                fileNameMap.put(fileName, newFileName);
            }
            else
                fileParts.remove(filePart);
        }
        
        if(validFileSize) {
            model.setImagePaths(fileNames);
            Response result = HttpRequestUtils.post("http://localhost:9080/product/create", model);

            if (result.getStatusCode() == 200) {
                String path = getServletContext().getRealPath("/uploads/");
                for(Part filePart : fileParts) {
                    filePart.write(path + fileNameMap.get(filePart.getSubmittedFileName()));
                }
            } else {
                response.setStatus(result.getStatusCode());
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
