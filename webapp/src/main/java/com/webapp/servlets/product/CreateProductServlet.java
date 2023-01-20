package com.webapp.servlets.product;

import com.webapp.models.product.CreateProductModel;
import com.webapp.servlets.BaseServlet;
import com.webapp.utils.RequestBuilder;
import com.webapp.utils.Result;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@WebServlet(name = "CreateProductServlet", urlPatterns = {"/urunler/yeni"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class CreateProductServlet extends BaseServlet {

    List<String> fileNames;
    Map<String, String> fileNameMap;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(getJSPPage()).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CreateProductModel model = new CreateProductModel();
        model.setName(request.getParameter("name"));
        model.setDescription(request.getParameter("description"));
        model.setPrice(Double.parseDouble(request.getParameter("price")));
        model.setStock(Integer.parseInt(request.getParameter("stock")));

        String images = "images";

        List<Part> fileParts = request
                .getParts()
                .stream()
                .filter(part -> part.getName().equals(images) && part.getSize() > 0)
                .collect(Collectors.toList());

        boolean validFileSize = true;

        fileNames = new ArrayList<>();
        fileNameMap = new HashMap<>();

        // Validate file sizes
        for (Part filePart : fileParts) {
            if (filePart.getSize() > 10 * 1000 * 1000) {
                validFileSize = false;
                StringBuilder builder = new StringBuilder("");
                builder.append("Dosya boyutu büyük(>10MB). Dosya Adı:")
                        .append(filePart.getSubmittedFileName())
                        .append(filePart.getContentType());
                request.setAttribute("imageError", builder.toString());
                request.getRequestDispatcher(getJSPPage()).forward(request, response);
                break;
            }
            String fileName = filePart.getSubmittedFileName();
            int firstIndexOf = fileName.indexOf('.');
            int lastIndexOf = fileName.lastIndexOf('.');

            // Validate file extension if there is more than 1 '.' its highly likely malicious ex(i-am-malicious.(php/bat/exe).png)
            if (firstIndexOf == lastIndexOf) {
                String newFileName = UUID.randomUUID() + fileName.substring(lastIndexOf);
                fileNames.add(newFileName);
                fileNameMap.put(fileName, newFileName);
            } else {
                fileParts.remove(filePart);
            }
        }

        if (validFileSize) {
            model.setProductImages(fileNames);

            HttpSession session = request.getSession();

            StringBuilder builder = new StringBuilder();

            builder.append("suppliers/")
                    .append(getUserId(session))
                    .append("/products");

            Result result = RequestBuilder.create()
                    .withURL(builder.toString())
                    .withToken(getToken(session))
                    .post(model);

            if (result.getStatusCode() < 300) {
                String path = getServletContext().getRealPath("/uploads/");
                for (Part filePart : fileParts) {
                    filePart.write(path + fileNameMap.get(filePart.getSubmittedFileName()));
                }
            }

            processResult(result, request, response);
        }
    }

    @Override
    protected String getJSPPage() {
        return "/WEB-INF/product/createProduct.jsp";
    }

    @Override
    protected void onSuccessfullResponse(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }

}
